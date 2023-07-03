package swith.backend.service;

import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;
import swith.backend.exception.FileException;
import swith.backend.exception.FileExceptionType;

import java.io.File;
import java.io.IOException;
import java.util.UUID;

@Service
@Transactional
@RequiredArgsConstructor
public class FileServiceImpl implements FileService{

    @Value("${file.dir}")//application.yml 파일에 있는 file.dir의 내용을 가져옴
    private String fileDir;


    @Override
    public String save(MultipartFile multipartFile)  {


        int extIdx = multipartFile.getOriginalFilename().lastIndexOf(".");
        String extension = multipartFile.getOriginalFilename().substring(extIdx+1);


        String filePath = fileDir + UUID.randomUUID()+"."+extension;
        try {
            multipartFile.transferTo(new File(filePath));
        }catch (IOException e){
            //파일 저장 에러!
            throw new FileException(FileExceptionType.FILE_CAN_NOT_SAVE);
        }

        return filePath;
    }

    @Override
    public void delete(String filePath) {
        File file = new File(filePath);

        if(!file.exists()) return;

        if(!file.delete()) throw new FileException(FileExceptionType.FILE_CAN_NOT_DELETE);

    }
}
