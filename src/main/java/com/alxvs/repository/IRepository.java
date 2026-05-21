package com.alxvs.repository;

import java.io.IOException;

public interface IRepository {
    String getLocalValue(String value) throws IOException;
    void setLocalValue(String path, String value) throws IOException;
}
