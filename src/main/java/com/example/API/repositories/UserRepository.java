package com.example.API.repositories;

import com.example.API.models.User;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserRepository extends CrudRepository<User, Long> {

    User findByUsername(String username);
    User findByUsernameAndPassword(String username,String password);
}
