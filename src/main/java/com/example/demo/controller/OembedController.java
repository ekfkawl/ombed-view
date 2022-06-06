package com.example.demo.controller;

import com.example.demo.exception.ApiException;
import com.example.demo.exception.enums.ExceptionEnum;
import com.example.demo.model.template.OembedTemplate;
import com.example.demo.service.OembedService;
import com.example.demo.util.OembedUtils;
import com.google.gson.JsonObject;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;

import java.io.IOException;
import java.lang.reflect.InvocationTargetException;
import java.net.URL;
import java.util.Objects;
import java.util.Optional;

import static com.example.demo.util.UrlUtils.isValidUrl;

@RestController
@RequiredArgsConstructor
public class OembedController {

    private final OembedService oembedService;
    private final OembedUtils oembedUtils;

    @GetMapping("/")
    public ModelAndView index() {
        return new ModelAndView("index");
    }

    @GetMapping("/api/oembed")
    public JsonObject oembed(@RequestParam Optional<String> url) throws IOException, InvocationTargetException, IllegalAccessException, NoSuchMethodException, InstantiationException {
        if (!url.isPresent()) {
            throw new ApiException(ExceptionEnum.RUNTIME_EXCEPTION.setMessage("url empty"));
        }

        String contentsUrl = url.get();

        if (!isValidUrl(contentsUrl)) {
            throw new ApiException(ExceptionEnum.RUNTIME_EXCEPTION.setMessage("url not valid"));
        }

        Class<? extends OembedTemplate> provider = oembedUtils.getProvider(new URL(contentsUrl).getHost());
        if (Objects.isNull(provider)) {
            throw new ApiException(ExceptionEnum.RUNTIME_EXCEPTION.setMessage("not support provider"));
        }

        JsonObject oembed = (JsonObject)oembedService.getOembed(provider, contentsUrl);

        return oembed;
    }
}
