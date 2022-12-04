package com.banking.banking.services;

import ch.qos.logback.core.util.TimeUtil;
import com.banking.banking.entities.CommonDataModel;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.UUID;

public class AbstractCDMService<T extends CommonDataModel> {

    private final Logger logger = LoggerFactory.getLogger(this.getClass());

    protected JpaRepository<T, String> repository;

    public T save(T cdmObject){
        cdmObject = fillCommonAttributes(cdmObject);
        logger.info("Created a new instance of CDM");
        return repository.save(cdmObject);
    }
    public List<T> findAll(){
        return repository.findAll();
    }

    public T fillCommonAttributes(T cdmObject){
        // Set default Id as UUID.
        if(cdmObject.getId() == null){
            cdmObject.setId(UUID.randomUUID().toString());
        }
        // Set default state as active
        if(cdmObject.getActiveStatus() == null){
            cdmObject.setActiveStatus(CommonDataModel.activeStatus.ACTIVE);
        }
        return cdmObject;
    }
}
