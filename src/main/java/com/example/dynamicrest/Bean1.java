package com.example.dynamicrest;

import net.bytebuddy.implementation.bind.annotation.Argument;
import net.bytebuddy.implementation.bind.annotation.RuntimeType;
import org.springframework.stereotype.Component;
import org.springframework.web.multipart.MultipartFile;

@Component
public class Bean1 implements BeanInterface{
    @Override
    @RuntimeType
    public String processFile(@Argument(0) MultipartFile file) {
        return file.getName();
    }

    @Override
    public String getName() {
        return this.getClass().getSimpleName();
    }
}
