package com.example.dynamicrest;

import lombok.RequiredArgsConstructor;
import lombok.SneakyThrows;
import lombok.extern.slf4j.Slf4j;
import org.springframework.context.support.GenericApplicationContext;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.mvc.method.RequestMappingInfo;
import org.springframework.web.servlet.mvc.method.annotation.RequestMappingHandlerMapping;
import org.springframework.web.util.pattern.PathPatternParser;

import javax.annotation.PostConstruct;
import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.lang.reflect.Method;
import java.lang.reflect.ParameterizedType;


// Rejestracja dynamicznego beana z dynamicznym endpointem

@Slf4j
@Component
@RequiredArgsConstructor
public class Register {

    private final Generator generator;
    private final RequestMappingHandlerMapping handlerMapping;

    private final GenericApplicationContext context;


    @SneakyThrows
    public void registerFileController(String path){

        FileDynamicController fileController = generator.generateFileController(); // wygenerowanie instancji klasy dynamicznego kontrollera
        context.registerBean(path, FileDynamicController.class, fileController); // zarejestrowanie singletonowego beana w kontekście springa
        RequestMappingInfo.BuilderConfiguration options = new RequestMappingInfo.BuilderConfiguration(); // konfiguracja request mappingu dynamicznego endpointu
        options.setPatternParser(new PathPatternParser());
        handlerMapping.registerMapping(  // rejestracja mapingu
                RequestMappingInfo.paths("/file/"+path) // ściezka
                        .methods(RequestMethod.POST) // typ mappingu
                        .options(options) // defaultowe opcje
                        .produces(MediaType.APPLICATION_JSON_VALUE) // typ zwracany
                        .build(),
                fileController, // handler metody = dynamiczny bean
                fileController.getClass().getMethod("receiveFile", MultipartFile.class) // metoda obsługująca mapping
        );
        System.out.println("brake");

    }
}
