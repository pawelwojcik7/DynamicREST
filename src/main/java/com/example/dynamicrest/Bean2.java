package com.example.dynamicrest;

import org.springframework.web.multipart.MultipartFile;

public class Bean2 implements BeanInterface {
  @Override
  public String processFile(MultipartFile file) {
    return file.getContentType();
  }

  @Override
  public String getName() {
    return this.getClass().getSimpleName();
  }
}
