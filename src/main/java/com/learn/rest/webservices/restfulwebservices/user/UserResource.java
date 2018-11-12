package com.learn.rest.webservices.restfulwebservices.user;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;
import java.util.ArrayList;
import java.util.List;

@RestController
public class UserResource {

    @Autowired
    private UserDaoService service;

    //GET /users - retrieveAllUsers
    @GetMapping("/users")
    public List<User> retrieveAllIsers() {
        return service.findAll();
    }

    //GET /user/{id} - retrieveUser(int id)
    @GetMapping("/users/{id}")
    public User retrieveUser(@PathVariable int id) {
        return service.findOne(id);
    }

    //CREATED
    // input - deatils of the users
    // output - CREATED and Return the created URI
    @PostMapping("/users")
    public ResponseEntity<Object> ceateUser(@RequestBody User user) {
        User savedUser = service.save(user);

        // return status CREATED
        // set URI /user/4 in response

        URI location = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}").buildAndExpand(savedUser.getId()).toUri();
        return ResponseEntity.created(location).build();
    }

}
