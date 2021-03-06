package com.christiancarey.bsslogin.api;

import java.security.Principal;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.christiancarey.bsslogin.models.User;
import com.christiancarey.bsslogin.repositories.UserJpaRepository;

@RestController
@RequestMapping(value = "/user")
public class UserController {
    private UserJpaRepository userRepository;
    
    @Autowired
    public UserController(UserJpaRepository repository) {
        userRepository = repository;
    }
    
    @RequestMapping(method = RequestMethod.GET)
    ResponseEntity<User> user(Principal currentUser) throws Exception {
    	if (currentUser == null) { return new ResponseEntity<>(HttpStatus.FORBIDDEN); }
        User user = getCurrentUserData(currentUser);
        return new ResponseEntity<>(user, HttpStatus.OK);
    }

    @RequestMapping(method = RequestMethod.PUT)
    ResponseEntity<User> user(Principal currentUser, @RequestBody User input) throws Exception {
        User user = getCurrentUserData(currentUser);
        user.setNickName(input.getNickName());
        userRepository.save(user);
        return new ResponseEntity<>(user, HttpStatus.OK);
    }
    
    private User getCurrentUserData(Principal currentUser) throws Exception {
        String username = currentUser.getName();
        // TODO: throw an exception if there are problems, like more than one
        return userRepository.findByUsername(username).get();
    }
}
