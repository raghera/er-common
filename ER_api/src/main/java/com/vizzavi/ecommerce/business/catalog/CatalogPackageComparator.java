package com.vizzavi.ecommerce.business.catalog;


import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import com.vodafone.config.ConfigProvider;

public class CatalogPackageComparator implements OrderableComparator<CatalogPackage>
{
	private List<String>    mAttributes = new ArrayList<String>();
	private boolean mAscendingOrder = true;

	public static String ORDER_BY_ID = "id";
	public static String ORDER_BY_NAME = "name";
	public static String ORDER_BY_PRIORITY = "priority";

	/**
        This is used to sort the packages according to the order
        1) any trials/promotions
        2) subscription packages ordered by duration
        3) event packages
	 */
	public static String ORDER_BY_PURCHASE_OPTIONS = "purchase_order";



//	public CatalogPackageComparator()
//	{
//	}

	@Override
	public int compare(CatalogPackage o1, CatalogPackage o2)
	{
		CatalogPackage s1 = o1;
		CatalogPackage s2 = o2;

		int rv = 0;

		if (mAttributes.size()==0) {
			//REMEDY 6449 - sort by configurable parameter in er2.properties
			int packageSorting 	= ConfigProvider.getPropertyAsInteger("GETPACKAGES_SORTING", 0);
			if (packageSorting == 1) {
				mAttributes.add(ORDER_BY_ID);
			}
			else {
				mAttributes.add(ORDER_BY_NAME);
			}
		}
		Iterator<String> iter = mAttributes.iterator();

		while (iter.hasNext()) {
			String attribute = iter.next();

			if (attribute.equals(ORDER_BY_ID)) {
				
				//MQC 8385 start - CatalogPackage packageId refactor
				
				//rv = s1.getId().toLowerCase().compareTo(s2.getId().toLowerCase());
				if ( s1.getFullPackagePricepointId() != null && s2.getFullPackagePricepointId() != null  ) {
					
					rv = s1.getFullPackagePricepointId().toLowerCase().compareTo(s2.getFullPackagePricepointId().toLowerCase());
				
				}
				else {
					rv = s1.getSimplePackageId().toLowerCase().compareTo(s2.getSimplePackageId().toLowerCase());
				}
				
				//MQC 8385 end
				
				if (rv!=0) {
					break;
				}
			} else if (attribute.equals(ORDER_BY_NAME)) {
				rv = s1.getName().toLowerCase().compareTo(s2.getName().toLowerCase());
				if (rv!=0) {
					break;
				}
			} else if (attribute.equals(ORDER_BY_PRIORITY)) {
				rv = new Integer(s1.getPriority()).compareTo(new Integer(s2.getPriority()));
				if (rv!=0) {
					break;
				}   
			} else if (attribute.equals(ORDER_BY_PURCHASE_OPTIONS)) {
				/**
                    This is used to sort the packages according to the order
                    1) any trials/promotions
                    2) subscription packages
                    3) event packages
				 */
				PricePoint pt1 = s1.getPricePoint();
				PricePoint pt2 = s2.getPricePoint();

				if (pt1!=null && pt2!=null) {
					String promoCode1 = pt1.getPromoCode();
					String promoCode2 = pt2.getPromoCode();
					int chargingMethod1 = pt1.getChargingMethod();
					int chargingMethod2 = pt2.getChargingMethod();
					int duration1 = pt1.getDuration();
					int duration2 = pt2.getDuration();
					
					if (isValueSet(promoCode1) && isValueSet(promoCode2)==false) {
						rv = -16;
						break;
					}
					if (isValueSet(promoCode1)==false && isValueSet(promoCode2)) {
						rv = 16;
						break;
					}

					if (chargingMethod1==1 && chargingMethod2>1 || chargingMethod1==2 && chargingMethod2==3) {
						rv = 8;
						break;
					}
					if (chargingMethod2==1 && chargingMethod1>1 || chargingMethod2==2 && chargingMethod1==3) {
						rv = -8;
						break;
					}
					if (duration1<duration2) {
						rv = -4;
						break;
					} else if (duration1>duration2) {
						rv = 4;
						break;
					}

				} else if (pt1==null) {
					rv = 8;
					break;
				} else if (pt2==null) {
					rv = -8;
					break;
				}
			}

		}

		if (!mAscendingOrder) {
			rv = rv * -1;
		}
		return rv;
	}



	@Override
	public void orderBy(List<String> attributes) {
		mAttributes = attributes;
	}

	public void setOrderAscending(boolean val)
	{
		mAscendingOrder = val;
	}


	private boolean isValueSet(String val)
	{
		boolean rv = false;
		if (val!=null && val.equals("")==false && val.equals("*")==false && val.equals("999")==false) {
			rv = true;
		}
		return rv;
	}
}
