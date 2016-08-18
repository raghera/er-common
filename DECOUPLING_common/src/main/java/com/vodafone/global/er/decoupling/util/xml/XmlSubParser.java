package com.vodafone.global.er.decoupling.util.xml;

public class XmlSubParser {
	
	private String header = null;
	private String body = null;
	private String innerPayload = null;
	private String footer = null;
	
	//private XmlSubParser(){}
	
	public XmlSubParser(String tag, String xml)	{
		int start = xml.indexOf("<" + tag);
		if(start < 0)
			throw new RuntimeException("Start tag \"" + tag + "\" was not found in the xml.");
		
		int end = xml.indexOf("</" + tag + ">");
		if(end < 0)
			throw new RuntimeException("End tag \"" + tag + "\" was not found in the xml.");
		
		int closeStart = xml.indexOf(">", start);
		int closeEnd = xml.indexOf(">", end);

		if(xml.charAt(closeStart - 1)=='/')
		{
			body = xml.substring(start,  closeStart + 1);
			innerPayload = null;
			header = xml.substring(0, closeStart + 1);
			footer = xml.substring(closeStart + 1, xml.length());
		}
		else
		{
			body = xml.substring(start, closeEnd + 1);
			innerPayload = xml.substring(closeStart + 1, end);
			header = xml.substring(0, closeStart + 1);
			footer = xml.substring(end, xml.length());
		}
	}
	
	public String getOuter(){
		return header + footer;
	}
	
	public String getInner(){
		return innerPayload;
	}
	
	public String getBody(){
		return body;
	}
}
