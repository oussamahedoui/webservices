package com.techtest.mobilab.repository;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;
import org.springframework.web.bind.annotation.CrossOrigin;

import com.techtest.mobilab.datamodel.TransactionDocument;

@CrossOrigin("*")
@Repository
public interface TransactionRepository extends MongoRepository<TransactionDocument, String>{

}
