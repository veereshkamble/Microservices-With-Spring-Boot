package com.learn.rest.webservices.restfulwebservices.user;

import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.Date;
import java.util.Iterator;
import java.util.List;

@Component
public class UserDaoService {
    private static List<User> users = new ArrayList<>();

    private static int usersCount = 3;

    static {
        users.add(new User(1, "Ronaldo", new Date()));
        users.add(new User(2, "Messi", new Date()));
        users.add(new User(3, "Neymar", new Date()));
    }

    // public List<User> findAll() - to return a list of all Users
    public List<User> findAll() {
        return users;
    }

    // public User save(User user) - to create a new User and save to the database
    // one of the important things is when you create a new user, the id is calculate by the backend, the datebase determines the id
    public User save(User user) {
        if(user.getId() == null) {
            user.setId(++usersCount);
        }
        users.add(user);
        return user;
    }

    // public User findOne(int id) - to return a single User
    public User findOne(int id) {
        for(User user : users) {
            if(user.getId() == id) {
                return user;
            }
        }
        return null;
    }

    public User deleteById(int id) {
        Iterator<User> iterator = users.iterator();
        while(iterator.hasNext()) {
            User user = iterator.next();
            if(user.getId() == id) {
                iterator.remove();;
                return user;
            }
        }
        return null;
    }
}
