package com.example.dynamicrest;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RestController;

@Slf4j
@RestController
@RequiredArgsConstructor
public class Api {

    private final Register register;

    @PutMapping("/create/endpoint/{path}/{beanName}")
    public ResponseEntity<?> createDynamicEndopint(@PathVariable(value = "path") String path, @PathVariable String beanName) {

       register.registerFileController(path, beanName);
        return ResponseEntity.ok().build();
    }
}