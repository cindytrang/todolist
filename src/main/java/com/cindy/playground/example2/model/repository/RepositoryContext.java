package com.cindy.playground.example2.model.repository;

import com.cindy.playground.example2.model.domain.Task;

import java.io.IOException;
import java.io.NotSerializableException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.List;

// Context
public class RepositoryContext<T, ID> implements Repository<T, ID> {
    private Repository<T, ID> repository;

    // setStrategy
    public void setRepository(Repository<T, ID> repository) {
        this.repository = repository;
    }

    // doSomething
    public void save(T obj) {
        repository.save(obj);
    }

    // doSomething
    @Override
    public List<T> findAll() {
        return repository.findAll();
    }

    @Override
    public T findById(ID id) {
        return repository.findById(id);
    }

    // doSomething
    @Override
    public T update(T obj) {
        return repository.update(obj);
    }

    // doSomething
    @Override
    public T delete(T obj) {
        return repository.delete(obj);
    }

    private void writeObject(ObjectOutputStream out) throws IOException {
        throw new NotSerializableException("DieselEngine isn't serializable!");
    }

    private void readObject(ObjectInputStream in) throws IOException {
        throw new NotSerializableException("DieselEngine isn't serializable!");
    }
}
