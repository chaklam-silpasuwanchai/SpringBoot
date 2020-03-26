package com.example.rr.model;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.time.LocalDate;
import java.util.LinkedList;
import java.util.List;

import javax.money.CurrencyUnit;
import javax.money.Monetary;
import javax.money.MonetaryAmount;
import javax.money.MonetaryAmountFactory;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.PostLoad;
import javax.persistence.PrePersist;
import javax.persistence.Transient;

import lombok.Getter;
import lombok.ToString;

@Getter
@Entity
@ToString
public class Contract {

	//JPA will not persist any static final field
	public static final CurrencyUnit CURRENCY = Monetary.getCurrency("USD");
	
	//variables not going to include in the database marked "Transient"
	@Transient
	private MonetaryAmount revenue;
	
	@Transient
	private MonetaryAmountFactory<?> amountFactory = Monetary.getDefaultAmountFactory();
	
	@Transient
	private String currencyCode = CURRENCY.getCurrencyCode();

	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private int id;

	@ManyToOne
	private Product product;
	
	//10 digits precision and 2 digits after decimal
	@Column(name="revenue", precision=10, scale=2)
	private BigDecimal revenue_;
	
	private LocalDate dateSigned;

	//orphanRemoval = child cannot exists without its parent entity
	@OneToMany(cascade=CascadeType.ALL, orphanRemoval=true)
	@JoinColumn(name="contract_id", updatable=false)
	private List<RevenueRecognition> revenueRecognitions;

	public Contract(Product product, MonetaryAmount revenue, LocalDate dateSigned) {
		this.product = product;
		this.revenue = revenue;
		this.dateSigned = dateSigned;
		this.revenueRecognitions = new LinkedList<>();
	}
	
	
	/* BUSINESS LOGIC FOR CONTRACTS */
	public void calculateRevenueRecognitions() {
		product.calculateRevenueRecognition(this);
	}

	public void addRevenueRecognition(MonetaryAmount monetaryAmount, LocalDate dateRecognized) {
		revenueRecognitions.add(
				new RevenueRecognition(this, dateRecognized, monetaryAmount));
	}

	public MonetaryAmount recognizedRevenue(LocalDate asOf) {
		MonetaryAmount result =
				amountFactory.setNumber(0).setCurrency(CURRENCY).create();
		for (RevenueRecognition revenueRecognition : revenueRecognitions) {
			if (revenueRecognition.isRecognizableBy(asOf)) {
				result = result.add(revenueRecognition.getAmount());
			}
		}
		return result;
	}

	/* JPA PRE AND POST LOAD */
	@PrePersist
	protected void onPrePersist() {
		currencyCode = revenue.getCurrency().getCurrencyCode();
		revenue_ = revenue.getNumber()
				.numberValue(BigDecimal.class)
				.setScale(revenue.getCurrency().getDefaultFractionDigits(),
						RoundingMode.HALF_EVEN);
	}

	@PostLoad
	protected void onPostLoad() {
		this.revenue =
				Monetary.getDefaultAmountFactory()
					.setNumber(revenue_)
					.setCurrency(currencyCode)
					.create();
	}

	protected Contract() { /* as needed by JPA/ORM */ }

}
