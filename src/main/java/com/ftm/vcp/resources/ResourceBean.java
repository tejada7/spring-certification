package com.ftm.vcp.resources;

import org.springframework.core.io.FileSystemResourceLoader;
import org.springframework.core.io.ResourceLoader;
import org.springframework.stereotype.Component;

import java.io.IOException;
import java.nio.file.Files;
import java.util.stream.Collectors;

@Component
public class ResourceBean {

    private final ResourceLoader resourceLoader;

    public ResourceBean(final ResourceLoader resourceLoader) {
        this.resourceLoader = resourceLoader;
    }

    public String readResourceFromClassPath() throws IOException {
        try (final var lines = Files.lines(resourceLoader
                                                   .getResource("classpath:resource/file.log").getFile().toPath())) {
            return lines.collect(Collectors.joining());
        }
    }

    public String readResourceFromFileSystem() throws IOException {
        try (final var lines = Files.lines(new FileSystemResourceLoader()
                                                   .getResource("classpath:resource/file.log").getFile().toPath())) {
            return lines.collect(Collectors.joining());
        }
    }
}
