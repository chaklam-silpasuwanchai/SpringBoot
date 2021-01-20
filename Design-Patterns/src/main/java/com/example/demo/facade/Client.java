package com.example.demo.facade;

import java.text.SimpleDateFormat;
import java.util.Date;

public class Client {

	public static void main(String[] args) {
		SimpleDateFormat formatter = new SimpleDateFormat("dd/MM/yyyy HH:mm:ss");  
	    Date date = new Date();  

	    //generate Sale PDF report and Logistics CSV report using Facade
	    Facade.generateReport(Facade.Types.SALE, Facade.ReportTypes.PDF, date);
	    Facade.generateReport(Facade.Types.LOGISTIC, Facade.ReportTypes.PDF, date);
	}

}
