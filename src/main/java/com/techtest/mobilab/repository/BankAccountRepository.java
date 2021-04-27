package com.techtest.mobilab.repository;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;
import org.springframework.web.bind.annotation.CrossOrigin;

import com.techtest.mobilab.datamodel.BankAccountDocument;

@CrossOrigin("*")
@Repository
public interface BankAccountRepository extends MongoRepository<BankAccountDocument, String> {

	BankAccountDocument findByAccountNumber(String accountNumber);

	BankAccountDocument findById(Long id);

}
