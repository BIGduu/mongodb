package com.bigduu.mongodb;


import com.bigduu.mongodb.domain.User;

import com.bigduu.mongodb.repository.UserRepository;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.List;

@RunWith(SpringRunner.class)
@SpringBootTest
public class MongodbApplicationTests {

    @Autowired
    private UserRepository repository;

    @Test
    public void contextLoads() {
        User build = User.builder().firstName("test").lastName("test_lastName").build();
        repository.save(build);
        List<User> all = repository.findAll();
        System.out.println(all);
    }

}
