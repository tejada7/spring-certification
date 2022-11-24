package com.ftm.vcp.resources;

import org.springframework.core.io.FileSystemResourceLoader;
import org.springframework.core.io.ResourceLoader;
import org.springframework.stereotype.Component;

import java.io.IOException;
import java.io.UncheckedIOException;
import java.nio.file.Files;
import java.util.stream.Collectors;

@Component
public class ResourceBean {

    private final ResourceLoader resourceLoader;

    public ResourceBean(final ResourceLoader resourceLoader) {
        this.resourceLoader = resourceLoader;
    }

    public String readResourceFromClassPath() {
        try (final var lines = Files.lines(resourceLoader
                                                   .getResource("classpath:resource/file.log").getFile().toPath())) {
            return lines.collect(Collectors.joining());
        } catch (IOException e) {
            throw new UncheckedIOException(e);
        }
    }

    public String readResourceFromFileSystem() {
        try (final var lines = Files.lines(new FileSystemResourceLoader()
                                                   .getResource("file:resource/file.log").getFile().toPath())) {
            return lines.collect(Collectors.joining());
        } catch (IOException e) {
            throw new UncheckedIOException(e);
        }
    }
}
