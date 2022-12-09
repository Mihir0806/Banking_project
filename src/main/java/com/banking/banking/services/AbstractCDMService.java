package com.banking.banking.services;

import com.banking.banking.entities.CommonDataModel;
import com.banking.banking.repositories.CDMRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

public class  AbstractCDMService<A extends CommonDataModel> implements CommonDataModelService<A> {

    AbstractCDMService(CDMRepository<A,String> repository){
        this.repository = repository;
    }
    private final Logger logger = LoggerFactory.getLogger(this.getClass());

    private CDMRepository<A, String> repository;

    @Override
    public A save(A cdmObject){
        cdmObject = fillCommonAttributes(cdmObject);
        logger.info("Created a new instance of CDM");
        return repository.save(cdmObject);
    }

    @Override
    public A findById(String id) {
        Optional<A> cdmObj =  repository.findById(id);
        return cdmObj.orElse(null);
    }

    @Override
    public void deleteById(String id) {
        Optional<A> deleteObj = repository.findById(id);
        if(deleteObj.isEmpty()){
            throw new RuntimeException();
        }
    }

    @Override
    public void delete(CommonDataModel cdm) {
        if(cdm.getId() == null){
            logger.warn("Id for the CDM object is null or empty!");
        }else{
            deleteById(cdm.getId());
        }
    }


    @Override
    public List<A> findAll(){
        return repository.findAll();
    }

    public A fillCommonAttributes(A cdmObject){
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
