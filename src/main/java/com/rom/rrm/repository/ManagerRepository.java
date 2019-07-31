package com.rom.rrm.repository;

import com.rom.rrm.document.Manager;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.mongodb.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ManagerRepository extends MongoRepository<Manager, String> {
    List<Manager> findByCompanyId(String companyId);
    @Query(value = "{'name': {$regex : ?0, $options: 'i'}}")
    List<Manager> findByNameRegex(String regex);
    @Query(value = "{'name': {$regex : ?0, $options: 'i'}, 'companyId': ?1 }")
    List<Manager> findByNameRegexAndCompanyId(String regex, String companyId);
}
