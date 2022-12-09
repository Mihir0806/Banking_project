package com.banking.banking.services;

import com.banking.banking.entities.CommonDataModel;

import java.util.List;

public interface CommonDataModelService<T> {

    public List<T> findAll();

    public T save(T cdmObject);

    public T findById(String id);

    public void deleteById(String id);

    void delete(CommonDataModel cdm);
}
