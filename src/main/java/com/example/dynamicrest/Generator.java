package com.example.dynamicrest;

import lombok.RequiredArgsConstructor;
import lombok.SneakyThrows;
import lombok.extern.slf4j.Slf4j;
import net.bytebuddy.ByteBuddy;
import net.bytebuddy.description.annotation.AnnotationDescription;
import net.bytebuddy.implementation.MethodDelegation;
import net.bytebuddy.implementation.bind.annotation.Argument;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.DependsOn;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.RequestPart;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import java.lang.reflect.Modifier;


@Slf4j
@Component
@DependsOn("fileService")
@RequiredArgsConstructor
public class Generator {


    private final ApplicationContext applicationContext;
    private final BeanProvider beanProvider;


    @SneakyThrows
    public FileDynamicController generateFileController(String beanName) {

        FileControllerMethodImplementation.fileService = applicationContext.getBean(FileService.class); // wstrzyknięcie beana do FileControllerMethodImplementation
        BeanInterface beanInterface = beanProvider.getBean(beanName).get();
        System.out.println(beanInterface.getClass());

        return new ByteBuddy() // nowa reprezentacja klasy
                .subclass(FileDynamicController.class) // typ : FileDynamicController
                .name("FileDynamicController") // nazwa klasy
                .annotateType(AnnotationDescription.Builder // oznaczenie klasy jako RestController
                        .ofType(RestController.class)
                        .build())
                .defineMethod("processFile", String.class, Modifier.PUBLIC) // Dynamiczne zdefiniowanie metody publiocznej o nazwie receiveFile, zwracającej typ String
                .withParameter(MultipartFile.class, "file") // metoda przyjmuje jako parametr obiekt klasy MultipartFile o nazwie file
                .annotateParameter(AnnotationDescription.Builder.ofType(RequestPart.class) // file oznaczony jest parametrem @RequestPart
                        .define("value", "file") // @RequestPart(value="file")
                        .build())
                .intercept(MethodDelegation.to(beanInterface))// odesłanie do implementacji metody receiveFile
                .make()
                .load(getClass().getClassLoader())
                .getLoaded()
                .newInstance();
    }

    public static class FileControllerMethodImplementation {

        private static FileService fileService;

        public static String receiveFile(@Argument(0) MultipartFile file) // implementacja metody dynamicznego kontrolera
        {

            return fileService.processFile(file);
        }

    }

}

