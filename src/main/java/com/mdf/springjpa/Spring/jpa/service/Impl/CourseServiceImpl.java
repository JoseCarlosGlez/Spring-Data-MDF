package com.mdf.springjpa.Spring.jpa.service.Impl;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.mdf.springjpa.Spring.jpa.model.Course;
import com.mdf.springjpa.Spring.jpa.model.Student;
import com.mdf.springjpa.Spring.jpa.repository.CourseRepository;
import com.mdf.springjpa.Spring.jpa.repository.StudentRepository;
import com.mdf.springjpa.Spring.jpa.service.ICourseService;

@Service
public class CourseServiceImpl implements ICourseService {
	
	@Autowired
	private StudentRepository _studentRepository;
	
	@Autowired
	private CourseRepository _courseRepository;

	@Override
	public boolean addCourse(Course course) {
		try {
			
		Student _student = _studentRepository
				.findById(course.getStudent().getStudentId())
				.orElseThrow(()->new RuntimeException("student_id doesnot exist"));
		
		System.out.println(_student);
		Course _newCourse = Course.builder()
							.title(course.getTitle())
							.credits(course.getCredits())
							.student(_student)
							.build();
			
		this._courseRepository.save(_newCourse);
		
		return true;
		} catch (Exception e) {
			// TODO: handle exception
			System.out.println(e);
			return false;
		}
		
		// TODO Auto-generated method stub

	}

}
