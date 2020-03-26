package com.example.hibernate.model;

import java.time.LocalDate;

import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;

import lombok.Getter;
import lombok.Setter;

@Entity
@Setter
@Getter
@DiscriminatorValue(value = LeaveType.Values.SICK)
public class SickLeave extends Leave {
	
	private int daysAllowed = 30;
	
	public SickLeave(Employee emp, boolean approved, String remarks, LocalDate start, LocalDate end) {
		super(emp, approved, remarks, start, end);
	}
		

}