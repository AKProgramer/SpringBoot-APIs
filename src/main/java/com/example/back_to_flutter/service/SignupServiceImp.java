package com.example.back_to_flutter.service;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import com.example.back_to_flutter.model.SignupCredentials;
import com.example.back_to_flutter.repository.SignupRepo;

import lombok.AllArgsConstructor;

@Service
@AllArgsConstructor
public class SignupServiceImp implements SignupService{

    private SignupRepo signupRepo;
    BCryptPasswordEncoder encoder;

    @Override
    public SignupCredentials signup(SignupCredentials entity) {
        entity.setPassword(encoder.encode(entity.getPassword()));
       return signupRepo.save(entity);
    }
    @Override
    public void deleteAll() {
         signupRepo.deleteAll();
    }
    
    @Override
    public SignupCredentials getUserById(int id)
    {
        return signupRepo.findById(id).get();
    }
    @Override
    public Iterable<SignupCredentials> getAllUsers()
    {
        return signupRepo.findAll();
    }
    @Override
    public void deleteById(int id) {
        signupRepo.deleteById(id);
    }
    @Override
    public SignupCredentials getUserByUsername(String username) {
       System.out.println("sdfsdf" + signupRepo.findByUsername(username).getEmail());
        return signupRepo.findByUsername(username);
       
    }
    
}
