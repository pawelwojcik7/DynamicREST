package com.example.dynamicrest;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Optional;

@Component
@RequiredArgsConstructor
public class BeanProvider {

    private final List<BeanInterface> beanInterfaceList;


    public Optional<BeanInterface> getBean(String name){
        return beanInterfaceList.stream().filter(beanInterface -> beanInterface.getName().equals(name)).findFirst();
    }

}
