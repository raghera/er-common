package com.vodafone.global.er.http;

import org.apache.commons.lang.StringUtils;
import org.apache.commons.lang.builder.HashCodeBuilder;
import org.apache.http.Header;
import org.apache.http.message.BasicHeader;


/**
 * an implementation of {@link org.apache.http.Header} which overrides hashCode and equals based on the name and value only.
 * The comparison of name is case-insensitive, as per the specification. 
 * @see <a href="http://www.w3.org/Protocols/rfc2616/rfc2616-sec4.html#sec4.2"> 
 * http://www.w3.org/Protocols/rfc2616/rfc2616-sec4.html#sec4.2
 * </a>
 * @author matt
 *
 */
public class HttpHeader extends BasicHeader {

	
	/**
	 * an implementation of Header which overrides hashCode and equals based on the name and value only.
	 * The comparison of name is case-insensitive, as per the specification
	 * @author matt
	 *
	 */
	public HttpHeader(String name, String value) {
		super(name, value);
	}

	@Override
	public boolean equals(Object other)	{
		if (other == null) 
			return false;
		if (other == this) 
			  return true;
		if (other instanceof Header){
    		Header otherHeader = (Header)other;
    		return StringUtils.equalsIgnoreCase(otherHeader.getName(), getName()) &&
    				StringUtils.equals(otherHeader.getValue(), getValue());
    	}
    	return false;
	}
	

	@Override
	public int hashCode() {
		return new HashCodeBuilder(11, 23).
			       append(getName()!=null?getName().toLowerCase():"").	//could getName ever really be null? probably not
			       append(getValue()).toHashCode();
	}
	
	/**
	 * [Header name]: [header value]
	 */
	@Override
	public String toString()	{
		return getName()+": "+getValue();
	}
	
}
