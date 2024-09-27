package com.sks.market.util;

import org.springframework.core.io.Resource;
import org.springframework.core.io.support.PathMatchingResourcePatternResolver;
import org.springframework.core.io.support.ResourcePatternResolver;
import org.springframework.stereotype.Component;

import java.io.IOException;
import java.io.InputStream;
import java.nio.charset.StandardCharsets;

@Component
public class Utility {
    public String readFile(String path) throws IOException {
        ClassLoader classLoader = this.getClass().getClassLoader();
        ResourcePatternResolver rpr = new PathMatchingResourcePatternResolver(classLoader);
        Resource resource = rpr.getResource(path);
        InputStream inputStream = resource.getInputStream();
        return new String(inputStream.readAllBytes(), StandardCharsets.UTF_8);
    }
}
