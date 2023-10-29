package com.ddas.service;

import java.io.IOException;
import java.util.List;

import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import com.ddas.exception.model.FileUploadException;
import com.ddas.exception.model.FileNotFoundException;
import com.ddas.model.domain.File;
import com.ddas.model.domain.User;
import com.ddas.repository.FileRepository;

import lombok.AllArgsConstructor;

@AllArgsConstructor
@Service
public class FileService
{
    public void upload(MultipartFile file)
    {
        if(file == null || file.isEmpty())
        {
            throw new FileUploadException("File is empty or there is no file at all!");
        }

        try
        {
            User user = authService.getCurrentUser(); 

            File fileToStore = new File(user, file.getName(), "", file.getBytes());

            fileRepository.save(fileToStore);
        }
        catch(IOException e)
        {
            throw new FileUploadException("Failed to upload the file!");
        }
    }

    public File findById(long id)
    {
        return fileRepository.findById(id)
        .orElseThrow(() -> new FileNotFoundException("File with id: " + id + " not found!"));
    }

    public List<File> findAllByUserId(long userId)
    {
        return fileRepository.findAllByUserId(userId);
    }

    private final AuthService authService;
    private final FileRepository fileRepository;
}
