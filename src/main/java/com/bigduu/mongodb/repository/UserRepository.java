package com.bigduu.mongodb.repository;


import com.bigduu.mongodb.domain.User;
import org.springframework.data.mongodb.repository.MongoRepository;

import java.math.BigInteger;
import java.util.List;

public interface UserRepository extends MongoRepository<User, BigInteger> {
    User findByFirstName(String firstName);
    List<User> findByLastName(String lastName);

}
