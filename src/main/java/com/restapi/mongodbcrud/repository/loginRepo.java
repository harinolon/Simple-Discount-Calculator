package com.restapi.mongodbcrud.repository;

import java.util.Optional;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.mongodb.repository.Query;
import org.springframework.stereotype.Repository;

import com.restapi.mongodbcrud.crudModel.dataFinding;

@Repository
public interface loginRepo extends MongoRepository<dataFinding, String> {

   @Query(value = "{'username': {$regex : ?0, $options: 'i'}}")
   Optional<dataFinding> findByUsername(String username);
}
