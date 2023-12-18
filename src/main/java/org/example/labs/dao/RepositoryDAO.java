package org.example.labs.dao;

import java.sql.SQLDataException;
import java.util.List;

public interface RepositoryDAO<T> {
    public Long insert(T o) throws SQLDataException;
    public void update(T o) throws SQLDataException;
    public void delete(T o) throws SQLDataException;
    public T findById(Long id) throws SQLDataException;
    public List<T> findAll() throws SQLDataException;
}
