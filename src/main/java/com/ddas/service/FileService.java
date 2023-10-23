package com.ddas.service;

import java.io.IOException;

import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import com.ddas.exception.model.FileException;
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
            throw new FileException("File is empty or there is no file at all!");
        }

        try
        {
            User user = authService.getCurrentUser(); 

            File fileToStore = new File(user, file.getName(), "", file.getBytes());

            fileRepository.save(fileToStore);
        }
        catch(IOException e)
        {
            throw new FileException("Failed to upload the file!");
        }
    }

    private final AuthService authService;
    private final FileRepository fileRepository;
}
