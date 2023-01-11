package com.example.dynamicrest;


import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

@Service("fileService")
public class FileService {

    public String processFile(MultipartFile file){

        return file.getOriginalFilename();

    }
}
