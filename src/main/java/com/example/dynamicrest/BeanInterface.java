package com.example.dynamicrest;

import net.bytebuddy.implementation.bind.annotation.Argument;
import net.bytebuddy.implementation.bind.annotation.RuntimeType;
import org.springframework.web.multipart.MultipartFile;

public interface BeanInterface {

    @RuntimeType
   String processFile(@Argument(0) MultipartFile file);

    String getName();
}
