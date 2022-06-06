package com.example.demo.model.template;

import com.google.gson.JsonObject;
import com.google.gson.JsonParser;
import org.apache.http.HttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.HttpClientBuilder;
import org.apache.http.util.EntityUtils;

import java.io.IOException;

public abstract class OembedTemplate {

    public JsonObject getJson(String oembedUrl) throws IOException {
        HttpResponse response = HttpClientBuilder.create().build().execute(new HttpGet(oembedUrl));
        JsonObject asJsonObject = JsonParser.parseString(EntityUtils.toString(response.getEntity())).getAsJsonObject();

        return asJsonObject;
    }

    public abstract JsonObject getOembed(String contentsUrl) throws IOException, NoSuchMethodException;
}
