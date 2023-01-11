package com.example.dynamicrest;

import java.util.List;
import java.util.Optional;

public class BeanProvider {

    private List<BeanInterface> beanInterfaceList;


    private Optional<BeanInterface> getBean(String name){
        return beanInterfaceList.stream().filter(beanInterface -> beanInterface.getName().equals(name)).findFirst();
    }

}
