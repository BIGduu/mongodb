package com.bigduu.mongodb.controller;


import com.bigduu.mongodb.domain.User;
import com.bigduu.mongodb.repository.UserRepository;
import lombok.extern.log4j.Log4j2;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Example;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;

import java.math.BigInteger;
import java.util.Optional;

@RestController
@RequestMapping("/user")
@Log4j2
public class UserController {

    @Autowired
    private UserRepository repository;

    @GetMapping("/{id}")
    @Transactional
    public Object getUserById(@PathVariable BigInteger id) {
        Optional<User> userOptional = repository.findById(id);
        if (userOptional.isPresent()) {
            return userOptional.get();
        } else {
            return "该用户不存在";
        }

    }

    @GetMapping("/example")
    public Object getUser(User user) {
        Example<User> userExample = Example.of(user);
        return repository.findAll(userExample);
    }

    @GetMapping("/all")
    public Object getAllUser() {
        return repository.findAll();
    }

    @PostMapping
    @Transactional
    public Object addUser(User user) {
        if (user.getId() == null) {
            return repository.save(user);
        } else {
            return "为传入正确参数";
        }
    }

    @PutMapping
    @Transactional
    public Object updateUser(User user) {
        if (hasUser(user)) {
            return repository.save(user);
        } else {
            return "传入参数错误";
        }
    }

    @DeleteMapping
    @Transactional
    public void deleteUser(User user){
        repository.delete(user);
    }

    private Boolean hasUser(User user) {
        if (user.getId() == null) {
            return false;
        } else {
            Optional<User> byId = repository.findById(user.getId());
            return byId.isPresent();
        }
    }

}
