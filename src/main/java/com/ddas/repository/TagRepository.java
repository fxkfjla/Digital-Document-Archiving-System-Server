package com.ddas.repository;

import java.util.Collection;
import java.util.List;
import java.util.Optional;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.ddas.model.domain.Tag;

@Repository
public interface TagRepository extends CrudRepository<Tag, Long>
{
    public List<Tag> findAllByNameIn(Collection<String> tags);
    public Optional<Tag> findByName(String name);
}
