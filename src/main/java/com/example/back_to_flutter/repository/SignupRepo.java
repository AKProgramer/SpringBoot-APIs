package com.example.back_to_flutter.repository;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.example.back_to_flutter.model.SignupCredentials;


@Repository
public interface SignupRepo extends CrudRepository<SignupCredentials, Integer>{

    SignupCredentials findByEmail(String email);
    SignupCredentials findByUsername(String username);
    
    
}
