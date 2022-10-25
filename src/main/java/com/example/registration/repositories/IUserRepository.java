package com.example.registration.repositories;

import com.example.registration.entities.User;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface IUserRepository extends MongoRepository<User,String> {
    User findByUsername(String username);
}
