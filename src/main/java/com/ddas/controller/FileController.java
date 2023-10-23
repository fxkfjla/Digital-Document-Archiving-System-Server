package com.ddas.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import com.ddas.exception.model.ApiResponse;
import com.ddas.service.FileService;

import lombok.AllArgsConstructor;

@AllArgsConstructor
@RestController
@RequestMapping("/api/v1/file")
public class FileController
{
    @PostMapping("/upload")
    public ResponseEntity<ApiResponse<String>> upload(MultipartFile file)
    {
        // TODO: file name has to be "file" in order to file != null

        fileService.upload(file);
        return ApiResponse.success("File uploaded successfully!");
    }

    private final FileService fileService;
}
