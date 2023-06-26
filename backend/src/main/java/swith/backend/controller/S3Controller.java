package swith.backend.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;
import swith.backend.service.FileService;
import swith.backend.service.S3UploadService;

import java.io.IOException;

@RestController
@RequiredArgsConstructor
public class S3Controller {

    private final S3UploadService s3UploadService;

//    @PostMapping("/upload")
//    public ResponseEntity<Object> uploadFiles() {
//
//    }
}
