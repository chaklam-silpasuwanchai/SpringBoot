package com.example.rr.strategy;

import com.example.rr.model.Contract;

public class CompleteRecognitionStrategy implements RecognitionStrategy {

	@Override
	public void calculateRevenueRecognitions(Contract contract) {
		contract.addRevenueRecognition(
				contract.getRevenue(), contract.getDateSigned());
	}

}
