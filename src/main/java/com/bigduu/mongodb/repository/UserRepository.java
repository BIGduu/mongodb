package com.bigduu.mongodb.repository;


import com.bigduu.mongodb.domain.User;
import org.springframework.data.mongodb.repository.MongoRepository;

import java.math.BigInteger;
import java.util.Collection;
import java.util.List;
import java.util.Optional;

public interface UserRepository extends MongoRepository<User, Long> {
    Optional<User> findByFirstName(String firstName);
    Collection<User> findByLastName(String lastName);
    User findTopByOrderByIdDesc();


}
