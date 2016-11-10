package com.vodafone.global.er.decoupling.util.xml;


public class DatatypeConverter  {
	
	public static Integer parseInteger(String val)	{
		return Integer.parseInt(val);
	}
	
	public static String printInteger(Integer i)	{
		return i==null?null:i.toString();
	}

}
