package com.example.future.model;

/* Credits: github.com/swathisprasad/spring-boot-completable-future */

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.validation.constraints.NotNull;

import lombok.Data;

@Entity
@Data
public class Car {
	@Id
	@Column(name = "ID", nullable = false)
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private long id;
	
	@NotNull
	@Column(nullable = false)
	private String manufacturer;
	
	@NotNull
	@Column(nullable = false)
	private String model;
	
	@NotNull
	@Column(nullable = false)
	private String type;
}
