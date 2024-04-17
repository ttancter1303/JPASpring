package com.example.demolog.repo;

import com.example.demolog.Entity.School;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface SchoolRepository extends JpaRepository<School,Integer> {
    List<School> findSchoolBySchoolName(String SchoolName);
}
