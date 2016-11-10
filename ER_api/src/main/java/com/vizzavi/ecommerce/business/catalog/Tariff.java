package com.vizzavi.ecommerce.business.catalog;

import java.io.Serializable;
import java.util.ArrayList;

/**
 * CR-0978 Location Services
 * Tariff Object.
*/

public class Tariff implements Serializable {

	private static final long serialVersionUID = -1295284186019562398L;
	
	private String name;
	
	private ArrayList<String> packageIds = null;
	
	public Tariff (String name) {
		this.name = name;
	}
	
	@Override
	public String toString()
	{
		 StringBuffer buf = new StringBuffer();
		 buf.append("name=" + name + "\n");
		 
		 if (packageIds == null || packageIds.size() == 0 ) {
			 buf.append("packageIds=none");
		 }
		 else {
			 for (int i=0; i<packageIds.size(); i++) {
				 String id = packageIds.get(i);
				 buf.append("pricepointid" + i + ":" + id + "\n");
			 }
		 }
		 
		 return buf.toString();
	}
	
	public String getName() {
		return this.name;
	}
	
	
	public void setName(String name) {
		this.name = name;
	}
	
	public void addPackageId(String packageId) {
		if (packageIds == null) {
			packageIds = new ArrayList<String>();
		}
		
		packageIds.add(packageId);
	}
	
	public String[] getTariffPackageIds() {
		return packageIds.toArray((new String[]{}));
	}
	
}
