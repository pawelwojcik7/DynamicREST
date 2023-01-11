package com.example.dynamicrest;

import org.springframework.web.multipart.MultipartFile;

public class Bean1 implements BeanInterface{
    @Override
    public String processFile(MultipartFile file) {
        return file.getName();
    }

    @Override
    public String getName() {
        return this.getClass().getSimpleName();
    }
}
