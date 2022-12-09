package com.banking.banking.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.NoRepositoryBean;

import java.io.Serializable;

@NoRepositoryBean
public interface CDMRepository<T,E extends Serializable> extends JpaRepository<T,E> {
}
