package com.example.demo.model;

import com.example.demo.annotation.Oembed;
import com.example.demo.model.template.OembedTemplate;
import com.google.gson.JsonObject;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import java.io.IOException;

@Oembed(providerUrl = "www.instagram.com")
@Component
public class Instagram extends OembedTemplate {

    private final Logger logger = LoggerFactory.getLogger(this.getClass());
    private static String url;

    @Value("${oembed.url.instagram}")
    void setUrl(String url) {
        this.url = url;
    }

    @Override
    public JsonObject getOembed(String contentsUrl) throws IOException {
        logger.info("instagram");

        return super.getJson(url + contentsUrl);
    }

}
