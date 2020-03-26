package com.example.rr.strategy;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.time.LocalDate;

import javax.money.Monetary;
import javax.money.MonetaryAmount;
import javax.money.MonetaryAmountFactory;

import com.example.rr.model.Contract;

public class ThreeWayRecognitionStrategy implements RecognitionStrategy {

	private final int firstRecognitionOffset;
	private final int secondRecognitionOffset;
	private final MonetaryAmountFactory<?> amountFactory;
	
	public ThreeWayRecognitionStrategy(
			int firstRecognitionOffset,
			int secondRecognitionOffset) {
		this.firstRecognitionOffset = firstRecognitionOffset;
		this.secondRecognitionOffset = secondRecognitionOffset;
		this.amountFactory = Monetary.getDefaultAmountFactory();
	}

	@Override
	public void calculateRevenueRecognitions(Contract contract) {
		LocalDate dateSigned = contract.getDateSigned();
		// MonetaryAmount onePart = contract.getRevenue().divide(3);
		// The above throws ArithmeticException!
		MonetaryAmount revenue = contract.getRevenue();
		BigDecimal allocations[] = allocate(
				revenue.getNumber().numberValue(BigDecimal.class).setScale(
						revenue.getCurrency().getDefaultFractionDigits(),
						RoundingMode.HALF_EVEN), 3);
		contract.addRevenueRecognition(
				amountFactory
					.setCurrency(Contract.CURRENCY)
					.setNumber(allocations[0])
					.create(),
				dateSigned);
		contract.addRevenueRecognition(
				amountFactory
					.setCurrency(Contract.CURRENCY)
					.setNumber(allocations[1])
					.create(),
				dateSigned.plusDays(firstRecognitionOffset));
		contract.addRevenueRecognition(
				amountFactory
					.setCurrency(Contract.CURRENCY)
					.setNumber(allocations[2])
					.create(),
				dateSigned.plusDays(secondRecognitionOffset));
	}
	
	private BigDecimal[] allocate(BigDecimal totalRevenue, int count) {
		int defaultFractionDigits = Contract.CURRENCY.getDefaultFractionDigits();
		BigDecimal onePart =
				totalRevenue.divide(new BigDecimal(count), RoundingMode.HALF_EVEN);
		BigDecimal allocations[] = new BigDecimal[count];
		BigDecimal newTotal = BigDecimal.ZERO.setScale(
				defaultFractionDigits, RoundingMode.HALF_EVEN);
		for (int i = 0; i < count; i++) {
			allocations[i] = onePart;
			newTotal = newTotal.add(onePart);
		}
		if (! newTotal.equals(totalRevenue)) {
			// Adjust last allocation to achieve total revenue
			BigDecimal lastAllocation = allocations[count - 1];
			lastAllocation = lastAllocation.add(totalRevenue.subtract(newTotal));
			allocations[count - 1] = lastAllocation;
		}
		return allocations;
	}

}
