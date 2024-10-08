package com.example.demo.repository;

import com.example.demo.model.License;
import org.springframework.data.jpa.repository.JpaRepository;

public interface LicenseRepository extends JpaRepository<License, Long> {
    // You can add custom query methods if needed
}
