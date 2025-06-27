package com.pizzaria.app.repository;

import com.pizzaria.app.entity.User;
import java.util.List;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface UserRepository extends MongoRepository<User, String> {
    List<User> findByUsername(String username);
}
