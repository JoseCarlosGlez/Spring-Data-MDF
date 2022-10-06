package com.mdf.springjpa.Spring.jpa.model;

import javax.persistence.Column;
import javax.persistence.Embedded;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;
import javax.persistence.UniqueConstraint;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@NoArgsConstructor
@AllArgsConstructor
@Data
@Builder
@Table(name = "tbl_students", uniqueConstraints = @UniqueConstraint(name = "email_unique", columnNames = "email_address"))
public class Student {
	@Id
	@SequenceGenerator(name = "student_sequence", sequenceName = "student_sequence", allocationSize = 1)
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "student_sequence")
	private Long studentId;
	@Column(columnDefinition = "varchar(50)")
	@NotBlank(message = "field 'firstName' is mandatory")
	@Size(min = 1, max = 25,message = "The name needs to have atleast 2 letters")
	//@Pattern(regexp = "^[a-zA-Z]+$",message = "Name just allow letters ")
	private String firstName;
	private String lastName;
	@Column(name = "email_address", nullable = false)
	@NotBlank(message = "field Email is mandatory")
	//@Pattern(regexp =  "^[A-Za-z0-9+_.-]+@(.+)$", message = "Email format is not correct")
	private String emailId;

	@Embedded
	private Guardian guardian;

}
