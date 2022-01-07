package com.chrisgya.springjpagraddle.batch;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.NoRepositoryBean;

import java.io.Serializable;
import java.util.List;

@NoRepositoryBean
public interface BatchRepository<T, ID extends Serializable> extends JpaRepository<T, ID> {
    <S extends T> void saveInBatch(List<S> entities);
    <S extends T> List<S> saveInBatch2(List<S> entities);
}
