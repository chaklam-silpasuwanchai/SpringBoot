package com.example.hibernate.model;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToOne;

import org.hibernate.annotations.CacheConcurrencyStrategy;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Setter
@Getter
@NoArgsConstructor
@javax.persistence.Cacheable
@org.hibernate.annotations.Cache(
		usage = CacheConcurrencyStrategy.READ_WRITE) 
//Provide cache strategy.
public class User{

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int id;
	
	@Column(nullable = false)
	private String username;
	
	@Column(nullable = false)
	private String password;
	
	private String role;
	
	private boolean active;
	
	//Default JPA
	/*
	   OneToMany: LAZY
	   ManyToMany: LAZY
	   ManyToOne: EAGER
       OneToOne: EAGER
	 */
	
	//When you load user, it will not load employee by default
	//Cascading only makes sense only for Parent – Child associations 
	//(the Parent entity state transition being cascaded to 
	//its Child entities).
	//if User is removed, then also remove employee (same for save, update, detach, refresh)
	//this mapping will be mapped by Employee table which will have user_id
	@OneToOne(mappedBy = "user", cascade = CascadeType.ALL, orphanRemoval = true)
	//@org.hibernate.annotations.Cache(usage = 
	//CacheConcurrencyStrategy.READ_WRITE)
	private Employee emp;

	public User(String username, String password, String role, boolean active, Employee emp) {
		super();
		this.username = username;
		this.password = password;
		this.role = role;
		this.active = active;
		this.emp = emp;
	}
	
}
