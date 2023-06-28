package swith.backend.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import swith.backend.dto.S3DeleteDto;
import swith.backend.service.S3UploadService;

import java.util.List;

@RestController
@RequestMapping("/file")
@RequiredArgsConstructor
public class S3Controller {

    private final S3UploadService s3UploadService;

    @PostMapping("/{postId}/upload")
    public ResponseEntity<Object> uploadFiles(
            @PathVariable("postId") Long postId,
            @RequestParam(value = "fileType", defaultValue = "image") String fileType,
            @RequestPart(value = "files") List<MultipartFile> multipartFiles) {

        return ResponseEntity
                .status(HttpStatus.OK)
                .body(s3UploadService.uploadFiles(postId, fileType, multipartFiles));
    }

    @DeleteMapping("/delete")
    public ResponseEntity<Object> deleteFile(
            @RequestBody S3DeleteDto s3DeleteDto) {
        return ResponseEntity
                .status(HttpStatus.OK)
                .body(s3UploadService.deleteFile(s3DeleteDto.getUploadFilePath(), s3DeleteDto.getUploadFileName()));
    }
}
