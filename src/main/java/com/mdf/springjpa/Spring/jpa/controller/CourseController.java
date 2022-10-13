package com.mdf.springjpa.Spring.jpa.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.mdf.springjpa.Spring.jpa.model.Course;
import com.mdf.springjpa.Spring.jpa.service.ICourseService;

@RestController
@RequestMapping("/api/course")
public class CourseController {

	@Autowired
	private ICourseService _courseServiceImpl;

	@PostMapping
	public ResponseEntity<Boolean> AddCourse(@RequestBody Course course) {

		boolean success = this._courseServiceImpl.addCourse(course);

		if (success)
			return new ResponseEntity<>(success, HttpStatus.OK);
		return new ResponseEntity<>(success, HttpStatus.BAD_REQUEST);

	}

}
