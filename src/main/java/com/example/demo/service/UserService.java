package com.example.demo.service;

import com.example.demo.exception.ResourceNotFoundException;
import com.example.demo.model.User;
import com.example.demo.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class UserService {

    @Autowired
    private UserRepository userRepository;

    public List<User> getAllUsers() {
        return userRepository.findAll();
    }

    public User getUser(Long id) {
        try {
            return userRepository.findById(id);
        } catch (EmptyResultDataAccessException e) {
            throw new ResourceNotFoundException("User not found with id: " + id);
        }
    }

    public void createUser(User user) {
        userRepository.save(user);
    }

    public void updateUser(User user) {
        getUser(user.getId()); // to check existence
        userRepository.update(user);
    }

    public void deleteUser(Long id) {
        getUser(id); // to check existence
        userRepository.delete(id);
    }
}
