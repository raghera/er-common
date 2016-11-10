package com.vodafone.global.er.business.selfcare;




// VEF [18/9/2005] start 	
// ACEpackage
// modify the class to implement Serializable
//public class BalanceFilter {  
public class BalanceFilter implements java.io.Serializable{ 
// VEF [18/9/2005] end 	

  private    static final long serialVersionUID = 9215525698977333771L; // added - ACEpackage

	
	public boolean mOnlyAceAttribute;
	
	
	
	/**
	 * @return
	 */
	public boolean isOnlyAcePackage(){
		return mOnlyAceAttribute;
	}
	
	
	
	/**
	 * @param onlyAcePackage
	 */
	public void setOnlyAcePackage(boolean onlyAcePackage){
		mOnlyAceAttribute = onlyAcePackage;
		
	}
	
	
	
}
