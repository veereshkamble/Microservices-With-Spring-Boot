package com.learn.rest.webservices.restfulwebservices.user;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.hateoas.Resource;
import org.springframework.hateoas.mvc.ControllerLinkBuilder;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;
import static org.springframework.hateoas.mvc.ControllerLinkBuilder.*;

import javax.validation.Valid;
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
    public Resource<User> retrieveUser(@PathVariable int id) {
        User user = service.findOne(id);
        if(user == null) {
            throw new UserNotFoundException("id-" + id);
        }

        //HATEOAS
        //"all-users", SERVER_PATH + "/users"
        //retireveAllUsers
        Resource<User> resource = new Resource<User>(user);
        ControllerLinkBuilder linkTo = linkTo(methodOn(this.getClass()).retrieveAllIsers());
        resource.add(linkTo.withRel("all-users"));

        return resource;
    }

    //CREATED
    // input - deatils of the users
    // output - CREATED and Return the created URI
    @PostMapping("/users")
    public ResponseEntity<Object> ceateUser(@Valid @RequestBody User user) {
        User savedUser = service.save(user);

        // return status CREATED
        // set URI /user/4 in response

        URI location = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}").buildAndExpand(savedUser.getId()).toUri();
        return ResponseEntity.created(location).build();
    }

    @DeleteMapping("/users/{id}")
    public void deleteUser(@PathVariable int id) {
        User user = service.deleteById(id);

        if(user == null) {
            throw new UserNotFoundException("id-" + id);
        }
    }
}
