package com.ddas.controller;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import com.ddas.exception.model.ApiResponse;
import com.ddas.model.domain.File;
import com.ddas.model.dto.FileRequest;
import com.ddas.service.FileService;

import lombok.AllArgsConstructor;

@AllArgsConstructor
@RestController
@RequestMapping("/api/v1/file")
public class FileController
{
    @PutMapping("/edit/{id}")
    public ResponseEntity<ApiResponse<String>> editFile(
            @PathVariable Long id,
            @RequestBody FileRequest req
    ) {
        fileService.editFile(id, req.name(), req.description(), req.tags());

        return ApiResponse.success("File edited successfully!");
    }

    @PostMapping("/upload")
    public ResponseEntity<ApiResponse<String>> upload
    (
        MultipartFile file, 
        @RequestParam String name,
        @RequestParam(defaultValue = "") String description,
        @RequestParam(defaultValue = "") List<String> tags
    ) {
        fileService.upload(file, name, description, tags);

        return ApiResponse.success("File uploaded successfully!");
    }

    @GetMapping("/download")
    public ResponseEntity<byte[]> download(@RequestParam long id)
    {
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_PDF);
        headers.set("Content-Disposition", "attachment; filename=filename.pdf");

        return new ResponseEntity<>(fileService.findById(id).getData(), headers, HttpStatus.OK);
    }

    @GetMapping("/delete")
    public ResponseEntity<ApiResponse<String>> delete(@RequestParam long id)
    {
        fileService.delete(id);

        return ApiResponse.success("File deleted successfully!");
    }

    @GetMapping("/all")
    public ResponseEntity<ApiResponse<Page<File>>> findAllForUser
    (
        @RequestParam(defaultValue = "lastModified") String sortBy,
        @RequestParam(defaultValue = "asc") String sortDirection,
        @RequestParam(defaultValue = "0") int page,
        @RequestParam(defaultValue = "12") int size
    ) {
        return ApiResponse.success(fileService.findAllForUser(sortBy, sortDirection, page, size));
    }

    @GetMapping("/search")
    public ResponseEntity<ApiResponse<Page<File>>> search
    (
        @RequestParam String name,
        @RequestParam(defaultValue = "lastModified") String sortBy,
        @RequestParam(defaultValue = "asc") String sortDirection,
        @RequestParam(defaultValue = "0") int page,
        @RequestParam(defaultValue = "12") int size
    ) {
        return ApiResponse.success(fileService.search(name, sortBy, sortDirection, page, size));
    }

    private final FileService fileService;
}
