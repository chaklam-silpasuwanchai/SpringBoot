package com.example.ml.model;

import java.util.Set;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;

import com.fasterxml.jackson.annotation.JsonBackReference;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Setter
@Getter
public class MailingList {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(updatable = false, nullable = false)
	private int id;
	
	@Column(nullable=false)
	private String title;
	
	@Column(nullable=false)
	private AccessType type;

	@ManyToMany()
	@JoinTable(name = "subscribing_list", 
				joinColumns = { @JoinColumn(name = "lid") }, 
				inverseJoinColumns = {@JoinColumn(name = "uid") })
	@JsonBackReference
	private Set<User> users;


}
