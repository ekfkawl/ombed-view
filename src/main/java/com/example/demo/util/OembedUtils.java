package com.example.demo.util;

import com.example.demo.annotation.Oembed;
import com.example.demo.model.template.OembedTemplate;
import org.reflections.Reflections;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

import java.util.Set;

@Component
public class OembedUtils {
    private final Logger logger = LoggerFactory.getLogger(this.getClass());

    private final Reflections reflections = new Reflections("com.example.demo.model");

    public Class<? extends OembedTemplate> getProvider(String providerUrl) {
        Set<Class<? extends OembedTemplate>> subTypes = reflections.getSubTypesOf(OembedTemplate.class);
        for (Class<? extends OembedTemplate> type : subTypes) {
            if (providerUrl.equals(type.getAnnotation(Oembed.class).providerUrl())) {
                logger.info("providerUrl : {} / subTypes : {}", providerUrl, type);
                return type;
            }
        }
        return null;
    }
}
