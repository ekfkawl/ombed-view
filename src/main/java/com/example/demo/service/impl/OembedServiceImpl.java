package com.example.demo.service.impl;

import com.example.demo.model.template.OembedTemplate;
import com.example.demo.service.OembedService;
import org.springframework.stereotype.Service;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

@Service
public class OembedServiceImpl implements OembedService {

    @Override
    public Object getOembed(Class<? extends OembedTemplate> provider, String url) throws IllegalAccessException, InvocationTargetException, NoSuchMethodException, InstantiationException {
        Method f = provider.getDeclaredMethod("getOembed", String.class);
        return f.invoke(provider.getDeclaredConstructor().newInstance(), url);
    }
}
