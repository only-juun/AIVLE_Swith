package swith.backend.controller;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import swith.backend.dto.S3DeleteDto;
import swith.backend.service.S3Service;

import java.io.IOException;
import java.util.List;

@RestController
@RequestMapping("/file")
@RequiredArgsConstructor
@Tag(name = "file", description = "첨부파일 API")
public class S3Controller {

    private final S3Service s3Service;

    /**
     * 파일 업로드
     */
    @Operation(summary = "upload files", description = "첨부파일 업로드하기")
    @PostMapping("/{postId}/upload")
    public ResponseEntity<Object> uploadFiles(
            @PathVariable("postId") Long postId,
            @RequestParam(value = "fileType", defaultValue = "image") String fileType,
            @RequestPart(value = "files") List<MultipartFile> multipartFiles) {

        return ResponseEntity
                .status(HttpStatus.OK)
                .body(s3Service.uploadFiles(postId, fileType, multipartFiles));
    }

    /**
     * 파일 삭제
     */
    @Operation(summary = "delete files", description = "첨부파일 삭제하기")
    @DeleteMapping("/delete")
    public ResponseEntity<Object> deleteFile(
            @RequestBody S3DeleteDto s3DeleteDto) {
        return ResponseEntity
                .status(HttpStatus.OK)
                .body(s3Service.deleteFile(s3DeleteDto.getUploadFilePath(), s3DeleteDto.getUploadFileName()));
    }

    /**
     * 파일 다운로드
     */
    @Operation(summary = "download files", description = "첨부파일 다운로드하기")
    @GetMapping("/download")
    public ResponseEntity<byte[]> downloadFile(@RequestParam("uploadFileName") String uploadFileName, @RequestParam("uploadFilePath") String uploadFilePath) throws IOException {
        return s3Service.getFile(uploadFileName, uploadFilePath);
    }
}
