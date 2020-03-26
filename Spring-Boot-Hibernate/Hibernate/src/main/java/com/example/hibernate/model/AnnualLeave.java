package com.example.hibernate.model;

import java.time.LocalDate;

import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;

import lombok.Getter;
import lombok.Setter;

@Entity
@Setter
@Getter
@DiscriminatorValue(value = LeaveType.Values.ANNUAL)
public class AnnualLeave extends Leave {
	
	private int daysAllowed = 15;
		
	public AnnualLeave(Employee emp, boolean approved, String remarks, LocalDate from, LocalDate to) {
		super(emp, approved, remarks, from, to);
	}
	
		
}
