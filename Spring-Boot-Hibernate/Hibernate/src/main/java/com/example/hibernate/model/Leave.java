package com.example.hibernate.model;

import java.time.LocalDate;
import java.util.Date;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.DiscriminatorColumn;
import javax.persistence.DiscriminatorType;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Inheritance;
import javax.persistence.InheritanceType;
import javax.persistence.ManyToOne;

import org.hibernate.annotations.CacheConcurrencyStrategy;

import com.fasterxml.jackson.annotation.JsonIgnore;

import lombok.Getter;
import lombok.NoArgsConstructor;

@Entity
@Inheritance(strategy = InheritanceType.SINGLE_TABLE)
@Getter
@NoArgsConstructor
@javax.persistence.Cacheable
@org.hibernate.annotations.Cache(usage = CacheConcurrencyStrategy.READ_WRITE) //Provide cache strategy.
@DiscriminatorColumn(name = "LEAVE_TYPE", discriminatorType = DiscriminatorType.STRING)
public class Leave {
	
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private int id;
		
	private LocalDate start;
	private LocalDate end;
	String remarks;
	private boolean approved;
	
	@Column(name = "LEAVE_TYPE", nullable = false, insertable = false, updatable = false) 
	//only Sickleave or Annual will update or create this field
    @Enumerated(EnumType.STRING)
    public LeaveType leaveType;
	
	@ManyToOne(fetch = FetchType.LAZY, cascade = {CascadeType.PERSIST, CascadeType.MERGE})
	@JsonIgnore
	private Employee emp;

	public Leave(Employee emp, boolean approved, String remarks, LocalDate start, LocalDate end) {
		super();
		this.emp = emp;
		this.approved = approved;
		this.remarks = remarks;
		this.start = start;
		this.end = end;
	}
	
	

}
