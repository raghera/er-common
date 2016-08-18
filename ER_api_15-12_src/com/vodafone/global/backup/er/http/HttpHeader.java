package com.vodafone.global.er.http;

import org.apache.commons.lang.StringUtils;
import org.apache.commons.lang.builder.HashCodeBuilder;
import org.apache.http.Header;
import org.apache.http.message.BasicHeader;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;


/**
 * an implementation of Header which overrides hashCode and equals based on the name and value only.
 * The comparison of name is case-insensitive
 * @author matt
 *
 */
public class HttpHeader extends BasicHeader {

	private static final Logger logger = LoggerFactory.getLogger(HttpHeader.class);
	
	/**
	 * an implementation of Header which overrides hashCode and equals based on the name and value only.
	 * The comparison of name is case-insensitive
	 * @author matt
	 *
	 */
	public HttpHeader(String name, String value) {
		super(name, value);
		if (StringUtils.isBlank(value))
			logger.info("creating a header with a name ({}) but no value", name);
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
		//return (getName()!=null?getName().toLowerCase().hashCode():0) * getValue().hashCode();
		return new HashCodeBuilder(11, 23).
			       append(getName()!=null?getName().toLowerCase():"").	//could getName ever really be null? probably not
			       append(getValue()).toHashCode();
	}
	
	@Override
	public String toString()	{
		return getName()+": "+getValue();
	}
	
}
