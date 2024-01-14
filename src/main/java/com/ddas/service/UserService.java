package com.ddas.service;

import org.springframework.stereotype.Service;

import com.ddas.exception.model.UserNotFoundException;
import com.ddas.model.domain.User;
import com.ddas.repository.UserRepository;

import lombok.AllArgsConstructor;

@AllArgsConstructor
@Service
public class UserService
{
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

    public boolean existsByEmail(String email)
    {
        return userRepository.existsByEmail(email);
    }

    public void deleteUser(User user)
    {
        userRepository.delete(user);
    }

    private final UserRepository userRepository;
}
