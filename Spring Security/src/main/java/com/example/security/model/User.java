package com.example.security.model;

import java.util.Set;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToMany;
import javax.persistence.Transient;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;

import com.fasterxml.jackson.annotation.JsonBackReference;

import lombok.Getter;
import lombok.Setter;

@Entity
@Getter
@Setter
public class User {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int id;
	
	@Column(nullable = false)
	@NotBlank(message="This field is required.")
	private String username;
	
	@NotBlank(message="This field is required.")
	@Column(nullable = false)
	private String password;
	
	@NotBlank(message="This field is required.")
	@Transient
	private String passwordConfirm;
	
	@NotBlank(message="This field is required.")
	@Email(message="Email should be valid")
	private String email;
	
	private boolean active;
	
	@ManyToMany(fetch=FetchType.EAGER)
	@JsonBackReference 
	private Set<Role> roles;
}
