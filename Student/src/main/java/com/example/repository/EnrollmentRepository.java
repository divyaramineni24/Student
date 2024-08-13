package com.example.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.model.Enrollment;

public interface EnrollmentRepository extends JpaRepository<Enrollment, Long> {
}
