package com.ddas.service;

import java.util.List;
import java.util.Optional;

import org.springframework.data.util.Streamable;
import org.springframework.stereotype.Service;

import com.ddas.model.User;
import com.ddas.repository.UserRepository;

@Service
public class UserService
{
    public UserService(UserRepository userRepository)
    {
        this.userRepository = userRepository;
    }

    public List<User> findAll()
    {
        return Streamable.of(userRepository.findAll()).toList();
    }

    public User findUserById(Long id)
    {
        Optional<User> user = userRepository.findById(id);

        return user.get();
    }

    private final UserRepository userRepository;    
}
