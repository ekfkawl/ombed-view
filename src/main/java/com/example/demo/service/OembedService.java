package com.example.demo.service;

import com.example.demo.model.template.OembedTemplate;

import java.lang.reflect.InvocationTargetException;

public interface OembedService {
    Object getOembed(Class<? extends OembedTemplate> provider, String url) throws IllegalAccessException, InvocationTargetException, NoSuchMethodException, InstantiationException;
}
