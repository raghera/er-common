package com.vodafone.global.er.business.catalog;

import com.vizzavi.ecommerce.business.catalog.PricePoint;

/**
 * BasePricePoint Class
 * Added in ER8, Phase II
 * VFE-PS, ER8 P2: Pre-Sign on Pricing
 */
public class BasePrice implements java.io.Serializable {
	
  private static final long serialVersionUID = -1590031070574807396L;;
	
  protected PricePoint mPackagePricePoint;
  protected String mServiceId;

  /**
   * BasePricePoint Constructor
   * @param serviceId Service Id
   * @param pt PricePoint
   */
  public BasePrice(String serviceId, PricePoint pt) {
    mServiceId = serviceId;
    mPackagePricePoint = pt;
  }
	
  /**
   * Getter method for the PricePoint
   * @return The PricePoint
   */
	public PricePoint getPricePoint(){
    return mPackagePricePoint;
	}
	
	
	/**
   * Getter method for the Service Id.
   * @return Service Id.
	 */
  public String getServiceId() {
    return mServiceId;
	}
	
	
	
}
