package com.mdf.springjpa.Spring.jpa.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.mdf.springjpa.Spring.jpa.model.Course;

public interface CourseRepository extends JpaRepository<Course, Long> {

}
