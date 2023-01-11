package com.example.dynamicrest;

import org.springframework.web.multipart.MultipartFile;

public interface BeanInterface {

    String processFile(MultipartFile file);
    String getName();
}
