package com.rom.rrm.repository;

import com.rom.rrm.document.Review;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ReviewRepository extends MongoRepository<Review, String> {
    List<Review> findByManagerId(String managerId);
    Review findFirstByManagerId(String managerId);
}
