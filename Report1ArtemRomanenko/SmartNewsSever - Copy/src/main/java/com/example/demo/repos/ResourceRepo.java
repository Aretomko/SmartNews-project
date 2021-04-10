package com.example.demo.repos;

import com.example.demo.domain.Resource;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ResourceRepo extends JpaRepository<Resource, Long> {
}
