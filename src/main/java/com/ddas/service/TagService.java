package com.ddas.service;

import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Service;

import com.ddas.model.domain.Tag;
import com.ddas.repository.TagRepository;

import jakarta.transaction.Transactional;
import lombok.AllArgsConstructor;

@AllArgsConstructor
@Service
public class TagService
{
    @Transactional
    public void save(Tag tag)
    {
        tagRepository.save(tag);
    }

    public Optional<Tag> findByName(String tag)
    {
        return tagRepository.findByName(tag);
    }

    public List<Tag> findAllByNameIn(List<String> tags)
    {
        return tagRepository.findAllByNameIn(tags);
    }

    private final TagRepository tagRepository;
}
