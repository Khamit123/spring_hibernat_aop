package spring.dao;

import java.util.List;

public interface DAO <T>{
    public List<T> getAll();
    public void delete(int id);
    public void update(T obj);
    public void add(T obj);
    public T getById(int id);


}
