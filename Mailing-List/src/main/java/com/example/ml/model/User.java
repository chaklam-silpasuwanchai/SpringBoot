package com.example.ml.model;

import java.util.Set;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToMany;
import javax.validation.constraints.Email;

import com.fasterxml.jackson.annotation.JsonManagedReference;

import lombok.Getter;
import lombok.Setter;

@Entity
@Setter
@Getter
public class User {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int id;
	
	@Column(nullable = false)
	private String username;
	
	@Column(nullable = false)
	private String password;
	
	@Email
	private String email;
	
	private String role;
	
	private boolean active;
	
	//if users are deleted, mailing list should not be deleted
	@ManyToMany(mappedBy = "users") //users are defined in MailingList.java
	@JsonManagedReference   //forward part of reference -the one that get serialized
	private Set<MailingList> mailinglists;
	//which data type for to-many associations List vs Set
	//Set is more efficient for handling many-to-many associations, otherwise, use List
	
}
