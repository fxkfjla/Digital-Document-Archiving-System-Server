package com.ddas.repository;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
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
    @Query("SELECT DISTINCT f FROM File f " +
       "LEFT JOIN f.tags t " +
       "WHERE f.user = :user " +
       "AND (f.name LIKE %:searchString% OR t.name LIKE %:searchString%)")
    Page<File> findByUserAndSearchString(@Param("user") User user, @Param("searchString") String searchString, Pageable pageable);
}
