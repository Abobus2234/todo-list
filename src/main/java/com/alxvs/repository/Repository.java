package com.alxvs.repository;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

public class Repository implements IRepository {
    @Override
    public String getLocalValue(String path) throws IOException {
        Path pathItemId = Paths.get(path);
        return Files.readString(pathItemId);
    }

    @Override
    public void setLocalValue(String path, String value) throws IOException {
        Path pathItemId = Paths.get(path);
        Files.write(pathItemId, value.getBytes());
    }
}
