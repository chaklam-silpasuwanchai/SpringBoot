package com.example.demo.facade;

import java.sql.Connection;
import java.util.Date;

public class Facade {
	public static void generateReport(Types type, ReportTypes reportType, Date date){
		Connection conn = null;
		switch (type){
		case SALE: 
			conn = SalesReportHelper.geSaleDBConnection();
			SalesReportHelper saleHelper = new SalesReportHelper();
			switch(reportType){
			case CSV:
				saleHelper.generateCSVReport(date);
				break;
			case PDF:
				saleHelper.generatePDFReport(date);
				break;
			}
			break;
		case LOGISTIC: 
			conn = LogisticsReportHelper.geLogisticsDBConnection();
			LogisticsReportHelper logisticHelper = new LogisticsReportHelper();
			switch(reportType){
			case CSV:
				logisticHelper.generateCSVReport(date);
				break;
			case PDF:
				logisticHelper.generatePDFReport(date);
				break;
			}
			break;
		}
		
	}
	
	public static enum Types{
		SALE,LOGISTIC;
	}
	
	public static enum ReportTypes{
		PDF,CSV;
	}
}
