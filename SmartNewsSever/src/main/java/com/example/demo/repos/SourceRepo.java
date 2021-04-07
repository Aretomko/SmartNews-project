package com.example.demo.repos;

import com.example.demo.domain.Source;
import org.springframework.data.jpa.repository.JpaRepository;

public interface SourceRepo extends JpaRepository<Source, Long> {
}
