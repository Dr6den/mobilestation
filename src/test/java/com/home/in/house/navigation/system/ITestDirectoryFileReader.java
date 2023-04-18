package com.home.in.house.navigation.system;

import java.io.InputStreamReader;
import java.io.Reader;
import org.springframework.core.io.DefaultResourceLoader;
import org.springframework.core.io.Resource;
import org.springframework.core.io.ResourceLoader;
import org.springframework.util.FileCopyUtils;

public interface ITestDirectoryFileReader {

    public static String resourceAsString(String path) throws Exception {

        ResourceLoader resourceLoader = new DefaultResourceLoader();
        Resource resource = resourceLoader.getResource(path);
        Reader reader = new InputStreamReader(resource.getInputStream(), "UTF-8");
        return FileCopyUtils.copyToString(reader);

    }
}
