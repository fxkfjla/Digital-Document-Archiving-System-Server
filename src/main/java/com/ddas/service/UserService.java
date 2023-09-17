package com.ddas.service;

import org.springframework.stereotype.Service;

import com.ddas.model.exception.UserNotFoundException;
import com.ddas.model.domain.User;
import com.ddas.repository.UserRepository;

@Service
public class UserService
{
    public UserService(UserRepository userRepository)
    {
        this.userRepository = userRepository;
    }

    public void save(User user)
    {
        userRepository.save(user);
    }

    public User findById(Long id)
    {
        return userRepository.findById(id)
        .orElseThrow(() -> new UserNotFoundException("User with id: " + id + " not found!"));
    }

    public User findByEmail(String email)
    {
        return userRepository.findByEmail(email)
        .orElseThrow(() -> new UserNotFoundException("User with email: " + email + " not found!"));
    }

    public void deleteUser(User user)
    {
        userRepository.delete(user);
    }

    private final UserRepository userRepository;
}
