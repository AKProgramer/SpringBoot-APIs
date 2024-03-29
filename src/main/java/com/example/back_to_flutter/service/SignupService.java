package com.example.back_to_flutter.service;

import com.example.back_to_flutter.model.SignupCredentials;

public interface SignupService {
    SignupCredentials signup(SignupCredentials entity);
    void deleteAll();
    SignupCredentials getUserById(int id);
    SignupCredentials getUserByUsername(String email);
    Iterable<SignupCredentials> getAllUsers();
    void deleteById(int id);

}
