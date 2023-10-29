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
    public ResponseEntity<ApiResponse<String>> upload(MultipartFile file)
    {
        // TODO: file name has to be "file" in order to file != null

        fileService.upload(file);
        return ApiResponse.success("File uploaded successfully!");
    }

    @GetMapping(value = "/download", produces = MediaType.APPLICATION_PDF_VALUE)
    public ResponseEntity<byte[]> download(@RequestParam long id, @RequestParam String name)
    {
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_PDF);
        // headers.setContentDispositionFormData("attachment", name);

        return new ResponseEntity<>(fileService.findById(id).getData(), headers, HttpStatus.OK);
    }

    @GetMapping("/display")
    public ResponseEntity<ApiResponse<byte[]>> display(@RequestParam long id)
    {
        return ApiResponse.success(fileService.findById(id).getData());
    }

    @GetMapping
    public ResponseEntity<ApiResponse<File>> findById(@RequestParam long id)
    {
        return ApiResponse.success(fileService.findById(id));
    }

    @GetMapping("/all/user")
    public ResponseEntity<ApiResponse<List<File>>> findAllByUserId(@RequestParam long userId)
    {
        return ApiResponse.success(fileService.findAllByUserId(userId));
    }

    private final FileService fileService;
}
