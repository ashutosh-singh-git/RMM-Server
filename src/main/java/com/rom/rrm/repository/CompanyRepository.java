package com.rom.rrm.repository;

import com.rom.rrm.document.Company;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.mongodb.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface CompanyRepository extends MongoRepository<Company, String> {

}
