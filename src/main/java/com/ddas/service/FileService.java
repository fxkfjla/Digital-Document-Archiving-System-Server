package com.ddas.service;

import java.io.IOException;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

import org.apache.commons.text.WordUtils;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import com.ddas.exception.model.FileAccessDeniedException;
import com.ddas.exception.model.FileNotFoundException;
import com.ddas.exception.model.FileUploadException;
import com.ddas.model.domain.File;
import com.ddas.model.domain.Tag;
import com.ddas.model.domain.User;
import com.ddas.repository.FileRepository;

import jakarta.transaction.Transactional;
import lombok.AllArgsConstructor;

@AllArgsConstructor
@Service
public class FileService
{
    public void editFile(Long id, String name, String description, List<String> tags)
    {
        tags.replaceAll(tag -> WordUtils.capitalizeFully(tag.toLowerCase()));

        List<Tag> existingTags = tagService.findAllByNameIn(tags);

        Set<Tag> fileTags = tags.stream().map(tagName -> 
            existingTags.stream().filter(tag -> tag.getName().equals(tagName)).findAny().orElseGet(() -> {
                Tag newTag = new Tag(tagName);
                tagService.save(newTag);
                return newTag;
            })
        ).collect(Collectors.toSet());

        File existingFile = findById(id);

        existingFile.getTags().forEach(tag -> { if (!fileTags.contains(tag)) tag.getFiles().remove(existingFile); });
        fileTags.forEach(tag -> tag.getFiles().add(existingFile));

        existingFile.setName(name);
        existingFile.setDescription(description);
        existingFile.setTags(fileTags);

        fileRepository.save(existingFile);
    }

    @Transactional
    public void upload(MultipartFile file, String name, String description, List<String> tags)
    {
        if(file == null || file.isEmpty())
        {
            throw new FileUploadException("File is empty or there is no file at all!");
        }

        try
        {
            User user = authService.getCurrentUser(); 

            tags.replaceAll(tag -> WordUtils.capitalizeFully(tag.toLowerCase()));
            List<Tag> existingTags = tagService.findAllByNameIn(tags);

            Set<Tag> fileTags = tags.stream().map(tagName -> 
                existingTags.stream().filter(tag -> tag.getName().equals(tagName)).findAny().orElseGet(() -> new Tag(tagName))
            ).collect(Collectors.toSet());

            File fileToStore = new File(user, name, description, file.getBytes(), file.getSize(), fileTags);

            fileRepository.save(fileToStore);
        }
        catch(IOException e)
        {
            throw new FileUploadException("Failed to upload the file!");
        }
    }

    public void delete(long id)
    {
        fileRepository.deleteById(id);
    }

    public File findById(long id)
    {
        File file = fileRepository.findById(id).orElseThrow(() -> new FileNotFoundException("File with id: " + id + " not found!"));

        if(authService.getCurrentUser().getId() != file.getUser().getId())
            throw new FileAccessDeniedException("Access to file denied!");

        return file;
    }

    public List<File> findAllForUser()
    {
        String email = authService.getCurrentUser().getEmail();

        return fileRepository.findAllByUserEmail(email);
    }

    public Page<File> findAllForUser(String sortBy, String sortDirection, int page, int size)
    {
        Sort sort = Sort.by(Sort.Direction.fromString(sortDirection), sortBy);
        Pageable pageable = PageRequest.of(page, size, sort);

        return fileRepository.findAll(pageable);
    }

    public Page<File> search(String name, String sortBy, String sortDirection, int page, int size)
    {
        Sort sort = Sort.by(Sort.Direction.fromString(sortDirection), sortBy);
        Pageable pageable = PageRequest.of(page, size, sort);

        User user = authService.getCurrentUser();

        return fileRepository.findByUserAndSearchString(user, name, pageable);
    }

    private final AuthService authService;
    private final TagService tagService;
    private final FileRepository fileRepository;
}
