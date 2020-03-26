package com.example.hibernate.model;

import java.util.List;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToMany;
import javax.persistence.MapsId;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import javax.persistence.Transient;

import org.hibernate.annotations.Cache;
import org.hibernate.annotations.CacheConcurrencyStrategy;

import com.fasterxml.jackson.annotation.JsonIgnore;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

//this class illustrate all possible annotations

/*f you want to cache your @OneToMany or @ManyToOne relation - 
 * add @Cache annotation over this field as well. */

@Setter
@Getter
@ToString
@Entity
@NoArgsConstructor
@Table(name = "employee_info") // alternative table name manually
@javax.persistence.Cacheable
@org.hibernate.annotations.Cache(usage = CacheConcurrencyStrategy.READ_WRITE, region = "employee") // Provide cache
																									// strategy.
public class Employee {

	@Id
	private int id;

	private Name name; // since this is a object

	@Column(name = "employee_age") // change name of the column manually
	private int age;

	// Default JPA
	/*
	 * OneToMany: LAZY ManyToMany: LAZY ManyToOne: EAGER OneToOne: EAGER
	 */

	// When you load employee, by default, one-to-many
	// will not load addresses by default
	// By putting mappedBy here, address will have employee id
	// Cascading only makes sense only for Parent – Child associations
	// The most common Parent – Child association consists of a one-to-many
	// and a many-to-one relationship, where the cascade being useful for
	// the one-to-many side only:.
	// When employee is removed, also remove addresses
	@OneToMany(fetch = FetchType.LAZY, mappedBy = "emp", cascade = CascadeType.ALL, orphanRemoval = true)
	@JsonIgnore
	@Cache(usage = CacheConcurrencyStrategy.READ_WRITE)
	private List<Address> addresses;

	// When you load employee, it will not load benefits by default
	@ManyToMany(fetch = FetchType.LAZY, cascade = { CascadeType.PERSIST, CascadeType.MERGE })
	@JsonIgnore
	@Cache(usage = CacheConcurrencyStrategy.READ_WRITE)
	private Set<Benefit> benefits;

	@OneToMany(fetch = FetchType.LAZY, mappedBy = "emp", cascade = CascadeType.ALL, orphanRemoval = true)
	@JsonIgnore
	@Cache(usage = CacheConcurrencyStrategy.READ_WRITE)
	private List<Leave> leaves;

	@Transient
	private String something_we_do_not_put_into_object;

	// When you load employee, it will load user (for one-to-one)
	// by default
	@OneToOne(fetch = FetchType.LAZY, cascade = CascadeType.ALL, orphanRemoval = true)
	@JoinColumn(name = "user_id", referencedColumnName = "id") // (optional)this will create user id in employee table
	@JsonIgnore
	@MapsId // use the same id as user, will called emp_user_id
	// @org.hibernate.annotations.Cache(usage =
	// CacheConcurrencyStrategy.READ_WRITE) //Provide cache strategy.
	private User user;

	/*
	 * When an Employee entity object is removed the remove operation is cascaded to
	 * the referenced Address entity object. In this regard, orphanRemoval=true and
	 * cascade=CascadeType.REMOVE are identical, and if orphanRemoval=true is
	 * specified, CascadeType.REMOVE is redundant.
	 * 
	 * The difference between the two settings is in the response to disconnecting a
	 * relationship. For example, such as when setting the address field to null or
	 * to another Address object. If orphanRemoval=true is specified the
	 * disconnected Address instance is automatically removed. This is useful for
	 * cleaning up dependent objects (e.g. Address) that should not exist without a
	 * reference from an owner object (e.g. Employee). If only
	 * cascade=CascadeType.REMOVE is specified no automatic action is taken since
	 * disconnecting a relationship is not a remove operation.
	 *
	 * Say your User has one-to-many relation to Comment. If you are using
	 * cascade="remove", you can remove the reference for Comment from one User, and
	 * then attach that Comment to another User. When you persist them, they will be
	 * correctly saved. But if you are using orphanRemoval=true, even if you will
	 * remove given Comment from one User, and then attach to another User, this
	 * comment will be deleted during persist, because the reference has been
	 * deleted.
	 *
	 * The reason why CascadeType.ALL is still needed because of other operations
	 * such as persist
	 */

}
