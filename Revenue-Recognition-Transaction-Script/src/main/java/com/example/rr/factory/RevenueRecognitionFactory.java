package com.example.rr.factory;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.time.LocalDate;

import javax.money.CurrencyUnit;
import javax.money.Monetary;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.rr.tabegateway.RevenueRecognitionTableDataGateway;

@Service
public class RevenueRecognitionFactory {

	@Autowired
	RevenueRecognitionTableDataGateway recognitionGateway;
	
	public static final CurrencyUnit CURRENCY = Monetary.getCurrency("USD");
	
	public void calculateRevenueRecognitions(String type, int contractId, 
			BigDecimal totalRevenue, LocalDate dateSigned) {
		if ("WORDPROCESSOR".equals(type)) {
			recognitionGateway.insert(contractId, totalRevenue, dateSigned);
		} else if ("SPREADSHEET".equals(type)) {
			BigDecimal allocations[] = allocate(totalRevenue, 3);
			// recognize 1/3 today
			recognitionGateway.insert(contractId, allocations[0], dateSigned);
			// recognize 1/3 after 60 days
			recognitionGateway.insert(contractId, allocations[1], dateSigned.plusDays(60));
			// recognize 1/3 after 90 days
			recognitionGateway.insert(contractId, allocations[2], dateSigned.plusDays(90));
		} else if ("DATABASE".equals(type)) {
			BigDecimal allocations[] = allocate(totalRevenue, 3);
			// recognize 1/3 today
			recognitionGateway.insert(contractId, allocations[0], dateSigned);
			// recognize 1/3 after 30 days
			recognitionGateway.insert(contractId, allocations[1], dateSigned.plusDays(30));
			// recognize 1/3 after 60 days
			recognitionGateway.insert(contractId, allocations[2], dateSigned.plusDays(60));
		} else {
			throw new RuntimeException(String.format("Unknown product type [%s]", type));
		}
	}
	
	private BigDecimal[] allocate(BigDecimal totalRevenue, int count) {
		int defaultFractionDigits = CURRENCY.getDefaultFractionDigits();
		//divide the total revenue according to the count
		//HALF_EVEN round as usual, but for .5, it will round to nearest even
		//e.g., if it is 2.5 it will round to 2, but for 5.5, it will round to 6
		//Round even is useful for statistical analysis
		BigDecimal onePart = totalRevenue.divide(new BigDecimal(count), RoundingMode.HALF_EVEN)
				.setScale(defaultFractionDigits, RoundingMode.HALF_EVEN);
		BigDecimal allocations[] = new BigDecimal[count];
		BigDecimal newTotal = BigDecimal.ZERO.setScale(defaultFractionDigits, RoundingMode.HALF_EVEN);
		for (int i = 0; i < count; i++) {
			allocations[i] = onePart;
			newTotal = newTotal.add(onePart);
		}
		
		//in case there is something left, add that small amount to the last allocation
		if (!newTotal.equals(totalRevenue)) {
			// Adjust last allocation to achieve total revenue
			BigDecimal lastAllocation = allocations[count - 1];
			lastAllocation = lastAllocation.add(totalRevenue.subtract(newTotal));
			allocations[count - 1] = lastAllocation;
		}
		return allocations;
	}
}
