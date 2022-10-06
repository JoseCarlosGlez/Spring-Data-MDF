package com.mdf.springjpa.Spring.jpa.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.mdf.springjpa.Spring.jpa.model.Student;


public interface StudentRepository extends JpaRepository<Student, Long> {
	
	public Student findByEmailId(String emailId);
	public List<Student> findByGuardianNameNotNull();
	
}
