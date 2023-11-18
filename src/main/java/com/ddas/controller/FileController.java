package com.ddas.controller;

import java.util.List;

import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import com.ddas.exception.model.ApiResponse;
import com.ddas.model.domain.File;
import com.ddas.service.FileService;

import lombok.AllArgsConstructor;

@AllArgsConstructor
@RestController
@RequestMapping("/api/v1/file")
public class FileController
{
    @PostMapping("/upload")
    public ResponseEntity<ApiResponse<String>> upload(MultipartFile file, @RequestParam String name)
    {
        fileService.upload(file, name);

        return ApiResponse.success("File uploaded successfully!");
    }

    @GetMapping(value = "/download")
    public ResponseEntity<byte[]> download(@RequestParam long id)
    {
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_PDF);
        headers.set("Content-Disposition", "attachment; filename=filename.pdf");

        return new ResponseEntity<>(fileService.findById(id).getData(), headers, HttpStatus.OK);
    }

    @GetMapping("/all/user")
    public ResponseEntity<ApiResponse<List<File>>> findAllByUserEmail(@RequestParam String userEmail)
    {
        return ApiResponse.success(fileService.findAllByUserEmail(userEmail));
    }

    private final FileService fileService;
}
