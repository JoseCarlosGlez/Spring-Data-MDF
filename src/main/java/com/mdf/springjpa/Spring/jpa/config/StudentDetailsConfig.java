package com.mdf.springjpa.Spring.jpa.config;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;

import com.mdf.springjpa.Spring.jpa.model.Student;
import com.mdf.springjpa.Spring.jpa.repository.StudentRepository;

public class StudentDetailsConfig implements UserDetailsService {
	
	@Autowired
	private StudentRepository _studentRepository;

	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		// TODO Auto-generated method stub
		String userName, password = null;
		List <GrantedAuthority> authorities = null;
		Student students = this._studentRepository.findByEmailId(username);
		
		if(students==null) {
			throw new UsernameNotFoundException("User details was not founded");
		}else{
			
			userName = students.getEmailId();
			password = students.getPassword();
			authorities = new ArrayList<>();
			authorities.add(new SimpleGrantedAuthority(students.getRole()));
		}
		
		System.out.println(userName);
		
		return new User(userName,password,authorities);
	}
	
	

}
