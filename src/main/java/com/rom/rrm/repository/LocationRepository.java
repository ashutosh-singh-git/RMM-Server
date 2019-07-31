package com.rom.rrm.repository;

import com.rom.rrm.document.Location;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface LocationRepository extends MongoRepository<Location, String> {
//    Location findById(String id);
}
