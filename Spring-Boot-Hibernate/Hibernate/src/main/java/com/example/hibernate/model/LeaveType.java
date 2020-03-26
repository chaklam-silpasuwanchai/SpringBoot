package com.example.hibernate.model;

public enum LeaveType {
	SICK(Values.SICK), ANNUAL(Values.ANNUAL);
	
	private LeaveType (String val) {
	     // force equality between name of enum instance, and value of constant
	     if (!this.name().equals(val))
	        throw new IllegalArgumentException("Incorrect use of ELanguage");
	  }
	
	public static class Values{
		public static final String SICK = "SICK";
		public static final String ANNUAL = "ANNUAL";
	}
	
}
