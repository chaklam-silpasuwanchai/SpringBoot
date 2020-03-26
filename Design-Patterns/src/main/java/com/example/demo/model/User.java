package com.example.demo.model;

import javax.persistence.Entity;
import javax.persistence.Id;

import lombok.Builder;
import lombok.ToString;

@Builder
@ToString
@Entity
public class User {

	@Id
	private int uid;
	private String name;
	private String nationality;
	private String email;

}
