package com.example.demo.pattern.facade;

import java.sql.Connection;
import java.util.Date;

public class LogisticsReportHelper {
	
	public static Connection geLogisticsDBConnection(){
		return null;
	}
	
	public void generatePDFReport(Date date) {
		System.out.println("Generating logistics report in PDF for " + date);
		
	}
		
	public void generateCSVReport(Date date){
		System.out.println("Generating logistics report in csv format for " + date);
	}
}
