package com.ddas.repository;

import java.util.Date;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.ddas.model.domain.JwtBlacklist;

@Repository
public interface JwtBlacklistRepository extends CrudRepository<JwtBlacklist, Long> 
{
    boolean existsByToken(String token); 
    void deleteByExpirationDateBefore(Date expirationDate);
}
