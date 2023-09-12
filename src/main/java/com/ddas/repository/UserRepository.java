package com.ddas.repository;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.ddas.model.User;

@Repository
public interface UserRepository extends CrudRepository<User, Long>
{
    
}
