package org.example.firstrestapi.service;

import org.springframework.stereotype.Service;
import org.example.firstrestapi.model.User;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class UserService {

    private final List<User> users = new ArrayList<>();

    public UserService() {
        // Initial data
        users.add(new User("1", "John Doe", "john.doe@example.com"));
        users.add(new User("2", "Jane Smith", "jane.smith@example.com"));
    }

    public List<User> getAllUsers() {
        return users;
    }

    public Optional<User> getUserById(String id) {
        return users.stream().filter(user -> user.getId().equals(id)).findFirst();
    }

    public User createUser(User user) {
        users.add(user);
        return user;
    }

    public Optional<User> updateUser(String id, User updatedUser) {
        return getUserById(id).map(user -> {
            user.setName(updatedUser.getName());
            user.setEmail(updatedUser.getEmail());
            return user;
        });
    }

    public boolean deleteUser(String id) {
        return users.removeIf(user -> user.getId().equals(id));
    }
}

