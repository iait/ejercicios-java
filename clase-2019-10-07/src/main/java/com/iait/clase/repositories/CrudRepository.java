package com.iait.clase.repositories;

import java.util.List;
import java.util.Optional;

public interface CrudRepository<T, I> {
    
    Optional<T> findById(I id);
    
    List<T> findAll();
}
