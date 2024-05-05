package com.example.demolog.repo;

import com.example.demolog.Entity.Tag;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface TagRepository extends JpaRepository<Tag,Integer> {
    List<Tag> findByTagName (String tagName);
}
