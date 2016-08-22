package com.vizzavi.ecommerce.util;

import java.util.Collection;
import java.util.Enumeration;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.apache.http.Header;


/**
 * class to do useful things with headers
 * @author matt
 *
 */
public class HeaderUtil {



	/**
	 * sifts through all the headers looking for the first header of the given name, and returns its value
	 * @param header case-insensitive as per the http spec
	 * @param request
	 * @return String value of the requested header, or "" if no such header is found
	 */
	public String getHeader(String header, HttpServletRequest request)	{
		Enumeration<String> headerNames = request.getHeaderNames();

		String headerName;
		while(headerNames.hasMoreElements()){
			headerName = headerNames.nextElement();
			if (header.equalsIgnoreCase(headerName))	{
				return request.getHeader(headerName);
			}
		}
		return "";
	}
	
	/**
	 * sifts through all the headers looking for the first header of the given name, and returns its value
	 * @param name case-insensitive as per the http spec
	 * @param headers
	 * @return Header the requested header, or null if no such header is found
	 */
	public Header getHeader(String name, Collection<Header> headers)	{
		for (Header h: headers)	{
			if (h.getName().equalsIgnoreCase(name))
				return h;
		}
		return null;
	}

	
	/**
	 * Extracts a Map of headers (name value pairs) from a request
	 * @param request
	 * @return
	 */
	public Map<String, String> getHeaders(HttpServletRequest request) {
		final Enumeration<String> enumeration = request.getHeaderNames();
        Map<String, String> headerMap = new HashMap<>();
        while (enumeration.hasMoreElements() ) {
        	String name = enumeration.nextElement();
        	String value = request.getHeader(name);
        	headerMap.put(name, value);
        }
		return headerMap;
	}


	/**
	 * turns a list of {@link org.apache.http.Header}s into a map of name value pairs
	 * @param headers
	 * @return
	 */
	public Map<String, String> getHeaderMap(List<Header> headers) {
        Map<String, String> headerMap = new HashMap<>();
        for (Header h: headers)	{
        	headerMap.put(h.getName(), h.getValue());
        }
        return headerMap;
	}
	
	
}
