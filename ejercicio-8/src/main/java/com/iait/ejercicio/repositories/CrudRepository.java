package com.iait.ejercicio.repositories;

import java.util.List;
import java.util.Optional;

public interface CrudRepository<T, I> {

    Optional<T> findById(I id);
    
    Optional<I> maxId();
    
    List<T> findAll();
    
    void save(T entity);
    
    Optional<T> deleteById(I id);
}
