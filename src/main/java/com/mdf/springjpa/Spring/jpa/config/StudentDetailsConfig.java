package com.mdf.springjpa.Spring.jpa.config;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.mdf.springjpa.Spring.jpa.model.Authority;
import com.mdf.springjpa.Spring.jpa.model.Student;
import com.mdf.springjpa.Spring.jpa.repository.StudentRepository;

@Service
public class StudentDetailsConfig implements AuthenticationProvider {

	@Autowired
	private StudentRepository _studentRepository;

	@Autowired
	private PasswordEncoder _passwordEncoder;
/*
	@Override
	public Authentication loadUserByUsername(String username) throws UsernameNotFoundException {
		// TODO Auto-generated method stub
		String userName, password = null;
		List<GrantedAuthority> authorities = null;
		Student students = this._studentRepository.findByEmailId(username);
		System.out.println(students);
		if (students == null) {
			throw new UsernameNotFoundException("User details was not founded");

		} else {
			if (_passwordEncoder.matches(password, students.getPassword())) {
				authorities = new ArrayList<>();
				authorities.add(new SimpleGrantedAuthority(students.getRole()));
			}

		}

		System.out.println(userName);

		return new User(userName, password, authorities);
	}
*/
	@Override
	@Transactional
	public Authentication authenticate(Authentication authentication) throws AuthenticationException {
		// TODO Auto-generated method stub
		String userName = authentication.getName();
		String password = authentication.getCredentials().toString();
		List<Student> _student = this._studentRepository.GET_ALL_STUDENTS_AUTHORITIES(userName);
		System.out.println(_student.get(0).getAuthorities());
		if (_student.size()>0) {

			if (_passwordEncoder.matches(password, _student.get(0).getPassword())) {
				List<GrantedAuthority> authorities = new ArrayList<>();
				authorities.add(new SimpleGrantedAuthority(_student.get(0).getRole()));
				return new UsernamePasswordAuthenticationToken(userName, password, getGrantedAuthorities(_student.get(0).getAuthorities()));
			}

			throw new BadCredentialsException("No user registered with those credentials");

		}
		throw new BadCredentialsException("No user registered with those credentials");
	}
	
	
	private List<GrantedAuthority> getGrantedAuthorities (Set<Authority> authorities){
		List<GrantedAuthority> grantedAuthorities= new ArrayList<>();
		
		for(Authority authority: authorities) {
			grantedAuthorities.add(new SimpleGrantedAuthority(authority.getName()));
		}
		
		return grantedAuthorities;
	}

	@Override
	public boolean supports(Class<?> authentication) {
		// TODO Auto-generated method stub
		return (UsernamePasswordAuthenticationToken.class.isAssignableFrom(authentication));
	}

}
