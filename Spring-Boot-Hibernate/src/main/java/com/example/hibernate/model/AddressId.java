package com.example.hibernate.model;

import java.io.Serializable;

import javax.persistence.Embeddable;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Embeddable
@Getter
@Setter
@ToString
public class AddressId implements Serializable{
	
	private static final long serialVersionUID = 1L;

	private String houseNo;
	private String streetAddress;
	private String city;
	private String zipcode;
}
