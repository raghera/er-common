package com.vizzavi.ecommerce.business.selfcare;

import java.io.Serializable;


/**
 * represents a bar for a user.  A user can have several bars in place, where the name is a unique name for the bar type, a description is something human readable, and a value indicates whether the bar is active.
 * <br/>PPMID113010 ET197 DE CTB Migration.  
 * @author matt
 *
 */
public class Bar implements Serializable{

	private String name;
	private String description;
	private boolean value;

	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	
	public String getDescription() {
		return description;
	}
	public void setDescription(String description) {
		this.description = description;
	}
	
	public boolean isActive() {
		return value;
	}
	public void setActive(boolean value) {
		this.value = value;
	}
	
	
	@Override
	public boolean equals(Object other)	{
		if (other == null) 
			return false;
		if (other == this) 
			  return true;
		if (other instanceof Bar){
			Bar bar = (Bar)other;
    		return bar.getName().equals(getName());
    	}
    	return false;
    }	
    
    @Override
	public int hashCode()	{
    	return new Long(getName()).hashCode();
    }
	
}
