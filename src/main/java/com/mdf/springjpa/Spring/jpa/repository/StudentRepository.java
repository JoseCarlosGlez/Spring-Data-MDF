package com.mdf.springjpa.Spring.jpa.repository;

import java.util.List;

import javax.persistence.StoredProcedureParameter;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.query.Procedure;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.mdf.springjpa.Spring.jpa.model.Student;

public interface StudentRepository extends JpaRepository<Student, Long> {
	
	public Student findByEmailId(String emailId);
	public List<Student> findByGuardianNameNotNull();
	@Procedure(name="GET_ALL_STUDENTS")
	@Transactional
	public List<Student>GET_ALL_STUDENTS();
	@Procedure(name="GET_ALL_STUDENTS_AUTHORITIES")
	@Transactional
	public List<Student> GET_ALL_STUDENTS_AUTHORITIES(String email);
}
