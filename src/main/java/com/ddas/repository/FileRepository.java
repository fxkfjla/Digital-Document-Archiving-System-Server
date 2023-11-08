package com.ddas.repository;

import java.util.List;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.ddas.model.domain.File;

@Repository
public interface FileRepository extends CrudRepository<File, Long>
{
    List<File> findAllByUserId(long userId);    
    List<File> findAllByUserEmail(String userEmail);
}
