package com.ddas.repository;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.ddas.model.domain.File;
import com.ddas.model.domain.User;

@Repository
public interface FileRepository extends CrudRepository<File, Long>
{
    Page<File> findAll(Pageable pageable);
    List<File> findAllByUserId(long userId);    
    List<File> findAllByUserEmail(String userEmail);
    Page<File> findByUserAndNameContaining(User user, String name, Pageable pageable);
}
