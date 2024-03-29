package com.example.back_to_flutter.web;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.example.back_to_flutter.model.SignupCredentials;
import com.example.back_to_flutter.service.SignupServiceImp;



@RestController
public class SignupController {
    
    @Autowired
    SignupServiceImp signupService;

    @GetMapping("/")
    public ResponseEntity<String> test(){
        return new ResponseEntity<>("Hello", HttpStatus.OK);
    }
    @CrossOrigin
    @PostMapping("/signup")
    public ResponseEntity<SignupCredentials> signup(@RequestBody SignupCredentials signupCredentials)
     {
        try{
            return new ResponseEntity<>(signupService.signup(signupCredentials), HttpStatus.CREATED);
        }
        catch(Exception e){
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
       
    }

    @GetMapping("/getUser/{id}")
    public ResponseEntity<SignupCredentials> getUser(@PathVariable int id)
    {
        try
        {
            return new ResponseEntity<>(signupService.getUserById(id), HttpStatus.OK);
        }
        catch(Exception e){
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }
    @CrossOrigin
    @GetMapping("/getAllUsers")
    public ResponseEntity<Iterable<SignupCredentials>> getAllUsers()
    {
        try
        {
            return new ResponseEntity<>(signupService.getAllUsers(), HttpStatus.OK);
        }
        catch(Exception e){
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
    @DeleteMapping("/deleteAllUsers")
    public ResponseEntity<String> deleteAllUsers()
    {
        try
        {
                signupService.deleteAll();
            return  ResponseEntity.ok("All users deleted successfully");
        }
        catch(Exception e)
        {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("An error occured while deleting all users "+ e.getMessage());
        }
        
    }
    @DeleteMapping("/deleteUser/{id}")
    public ResponseEntity<String> deleteById(@PathVariable int id)
    {
        try{
            signupService.deleteById(id);
            return ResponseEntity.ok("User with id "+ id +" has been deleted successfully");
        }
        catch(Exception e)
        {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("An error occured while deleting user with id "+id +" "+ e.getMessage());
        }
      
    }
    
}
