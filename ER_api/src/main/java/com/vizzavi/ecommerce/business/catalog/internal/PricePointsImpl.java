package com.vizzavi.ecommerce.business.catalog.internal;


import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import org.apache.log4j.Logger;

import com.vizzavi.ecommerce.business.catalog.PricePoint;
import com.vizzavi.ecommerce.business.catalog.PricePoints;
import com.vizzavi.ecommerce.business.common.Constants;
import com.vizzavi.ecommerce.business.common.RatingAttributes;

public class PricePointsImpl extends PricePoints{

	private    static final long serialVersionUID = 6878422193564842740L;
	private static Logger log = Logger.getLogger(PricePointsImpl.class);

	/* ER 7 Compliant */
    //protected Map<String, PricePoint> mPricePoints = new HashMap<String, PricePoint>();

	public PricePointsImpl(){
		super();
	}

	public PricePointsImpl(Map<String, PricePoint> points)
	{
		super(points);
	}

	public PricePointsImpl(List<PricePoint>  pts)
	{
		for (PricePoint ppt: pts) {
			PricePointImpl pt = new PricePointImpl(ppt);
			addPricePoint(pt);
		}
	}

//	public PricePointsImpl(PricePoints  copy)
//	{
//		this(copy);
//	}

	public void addPricePoints(PricePoint[] pts)
	{
//		for (int index=0; pts!=null && index<pts.length; index++) {
		for(PricePoint pt: pts){
			mPricePointMapById.put(pt.getId(), pt);
		}
	}



	
	public void addPricePoint(PricePoint pt)
	{
		super.putPricePoint(pt);
//		mPricePointMapById.put(pt.getId(), pt);
		// Also need to add to the packageid map
		if (mPricePointMapByPackageId == null){
			mPricePointMapByPackageId = new HashMap<String, ArrayList<PricePoint>>();
		}
		
		ArrayList<PricePoint> pps = mPricePointMapByPackageId.get(pt.getPackageId());
		if (pps == null){
			pps = new ArrayList<PricePoint>();
			this.mPricePointMapByPackageId.put(pt.getPackageId(), pps);
		}
		
		pps.add(pt);
	}


	public void deletePricePoint(PricePoint pt)
	{
                /* ER 7 Compiant */
               // mPricePoints.remove(pt.getId());
		remove(pt);
		mPricePointMapById.remove(pt.getId());
		
		if (mPricePointMapByPackageId != null){
			ArrayList<PricePoint> pps = mPricePointMapByPackageId.get(pt.getPackageId());
			if (pps != null && pps.size() > 0){
				for (int i = 0; i < pps.size(); i++){
					PricePointImpl pricePoint = (PricePointImpl)pps.get(i);
					if (pricePoint.getServiceIdentifier().equals(((PricePointImpl)pt).getServiceIdentifier())){
						pps.remove(pricePoint);
					}
				}
			}
		}
	}

	@Override
	public int size()
	{
		if (log.isInfoEnabled() && super.size() != mPricePointMapById.size())
			log.error("super.size() != mPricePointMapById.size()");
		return mPricePointMapById.size();
	}

	@Override
	public String toString()
	{
		StringBuffer buf = new StringBuffer();

		buf.append(mPricePointMapById.toString());
		return buf.toString();
	}

    //CR1564 -Utctimezone for diff region in country
    /**
     * @deprecated 
     */
    @Deprecated
	public PricePoint[] getPricePoints(String userGroup, List<String> promoCodes, boolean onlyActive) {
        return getPricePoints(userGroup, promoCodes, onlyActive, new Date());
    }

    //CR1564 -Utctimezone for diff region in country
	public PricePoint[] getPricePoints(String userGroup, List<String> promoCodes, boolean onlyActive, Date date)
	{
		return getPricePoints(userGroup, promoCodes.toArray(new String[promoCodes.size()]), true, date);
	}

    //CR1564 -Utctimezone for diff region in country
    /**
     * @deprecated 
     */
    @Deprecated
	public PricePoint[] getPricePoints(String userGroup, List<String> promoCodes) {
        return getPricePoints(userGroup, promoCodes, new Date());
    }

    //CR1564 -Utctimezone for diff region in country
    public PricePoint[] getPricePoints(String userGroup, List<String> promoCodes, Date date)
	{
		return getPricePoints(userGroup, promoCodes, true, date);
	}

    //CR1564 -Utctimezone for diff region in country
    /**
     * @deprecated 
     */
    @Deprecated
	public PricePoint[] getPricePoints(String[] userGroups, String[] promoCodes, boolean onlyActive) {
        return getPricePoints(userGroups, promoCodes, onlyActive, new Date());
    }

    //CR1564 -Utctimezone for diff region in country
	public PricePoint[] getPricePoints(String[] userGroups, String[] promoCodes, boolean onlyActive, Date date)
	{
		PricePoint[] rv = null;
		for (int index=0; userGroups!=null && index<userGroups.length; index++) {
			rv = getPricePoints(userGroups[index], promoCodes, onlyActive, date);
		}
		return rv;
	}

    //CR1564 -Utctimezone for diff region in country
    /**
     * @deprecated 
     */
    @Deprecated
	public PricePoint[] getPricePoints(String userGroup, String[] promoCodes, boolean onlyActive) {
        return getPricePoints(userGroup, promoCodes, onlyActive, new Date());
    }

	public PricePoint[] getPricePoints(String userGroup, String[] promoCodes, boolean onlyActive, Date date)
	{
		List<PricePoint> rv = new ArrayList<PricePoint>();
		if (mPricePointMapById.size()>0) {
			Iterator<PricePoint> iter = mPricePointMapById.values().iterator();
			while (iter.hasNext()) {
				PricePoint pt = iter.next();
				if (onlyActive==false || pt.isActive(date)) {

					// find the normal ones
					if (userGroup==null || userGroup.equals("") || userGroup.equals("*")) {
						if (pt.getPromoCode().equals("*") || pt.isTrial()) {
							rv.add(pt);
						} else if (isAttributeInArray(pt.getPromoCode(), promoCodes)) {
							rv.add(pt);
						} else {
							log.debug("Price point is a promo code price point " + pt.getPromoCode());
						}
					} else if (isAttributeEqual( userGroup, pt.getUserGroup())) {
						if (pt.getPromoCode().equals("*") || pt.isTrial()) {
							rv.add(pt);
						} else if (isAttributeInArray(pt.getPromoCode(), promoCodes)) {
							rv.add(pt);
						} else {
							log.debug("Price point is a promo code price point " + pt.getPromoCode());
						}
					} else {
						log.debug("Price point is not in user group " + userGroup + ":" + pt.getUserGroup());
					}
				} else {
					log.debug("Price point is not active " + pt.getId());
				}
			}
		}
		return rv.toArray(new PricePoint[rv.size()]);
	}

	private boolean isAttributeInArray(String val, String[] arr)
	{
		boolean rv = false;
		for (int index=0; arr!=null && index<arr.length; index++) {
			if (val.equals(arr[index])) {
				rv = true;
				break;
			}
		}

		return rv;
	}

	private boolean isAttributeEqual(String val1, String val2)
	{
		boolean rv = false;
		if (val1.equals("*")) {
			rv = true;
		} else if (val1.equals(val2)) {
			rv = true;
		}

		return rv;
	}

	/**
        This checks the following attributes
            Promo code
            Duration
            Access Device
            Payment Type
	 */
	public PricePoint findMatchingPackagePricePoint(PricePoint ptToFind)
	{

		PricePointImpl ptToFindImpl = (PricePointImpl)ptToFind;
		PricePoint match = null;
		if (mPricePointMapById.size()>0) {
			Iterator<PricePoint> iter = mPricePointMapById.values().iterator();
			while (iter.hasNext()) {
				PricePointImpl pt = (PricePointImpl)iter.next();
				ptToFindImpl.setTaxCode(pt.getTaxCode());
				String findId = ptToFindImpl.getPackageIdentifier();

				String packageIdentifier = pt.getPackageIdentifier();
				if (findId.equals(packageIdentifier)) {
					match = pt;
					break;
				}
			}
		}
		return match;
	}
	
	
	//CR MPP - start
	/**
	 * @param pricepointId
	 * @return
	 */
	public PricePoint findLastHistoricPricePoint(String pricepointId){
		
		Date lastEndDate=null;
		Date endDate=null;
		PricePoint match = null;
		
		if(mPricePointMapById.size()>0){
			
			Iterator<PricePoint> it = mPricePointMapById.values().iterator();
			
			while (it.hasNext()) {
				PricePointImpl pt = (PricePointImpl)it.next();
								
				if (pricepointId.equals(pt.getId())) {
					continue; //if same id skip
				
				}
				
				//only process if charging method=3, historic flag is true, start date and end date not empty
				if(pt.getChargingMethod()==3 && pt.isHistoric() && pt.getStartDate()!=null && pt.getExpiryDate()!=null){
									
						endDate = pt.getExpiryDate();
						
						if(lastEndDate==null){
							lastEndDate = endDate;
							match = pt;
						}else if (endDate.after(lastEndDate)){
							lastEndDate = endDate;
							match = pt;
						}	
														

				}
			}
		}
		return match;	
	}
	//CR MPP - end


	/**
        This checks the following attributes
            Package Id
            Content Id
            Channel
            Access Device
            Payment Type
            Supplier Id
	 */
	public PricePoint findMatchingServicePricePoint(PricePoint ptToFind)
	{

		PricePointImpl ptToFindImpl = (PricePointImpl)ptToFind;
		PricePoint match = null;
		//List rv = new ArrayList();
		if (mPricePointMapById.size()>0) {
			Iterator<PricePoint> iter = mPricePointMapById.values().iterator();
			while (iter.hasNext()) {
				PricePointImpl pt = (PricePointImpl)iter.next();
				String findId = ptToFindImpl.getServiceIdentifier();

				String serviceIdentifier = pt.getServiceIdentifier();
				if (findId.equals(serviceIdentifier)) {
					match = pt;
					break;
				}
			}
		}
		return match;
	}


	/**
        Find the price point with the same price point id
        @param String the id of price point to find
        @return PricePoint null is returned if no matching price point found

	 */
	@Override
	public PricePoint getPricePoint(String id)
	{
		String ptId = CatalogPackageIdentifier.getSimplePricePointId(id);
		PricePointImpl match = null;
		if (ptId!=null) {
			match = (PricePointImpl)mPricePointMapById.get(ptId);
		}

		return match;
	}

    //CR1564 -Utctimezone for diff region in country
    /**
     * @deprecated 
     */
    @Deprecated
	public BalanceImpact[] getCustomResourceBalanceImpact(String packageId) {
        return getCustomResourceBalanceImpact(packageId, new Date());
    }

    //CR1564 -Utctimezone for diff region in country
	public BalanceImpact[] getCustomResourceBalanceImpact(String packageId, Date date)
	{
		PricePoint[] arr = getPackagePricePoints(packageId);

		BalanceImpact[] rv = null;
		for (int index=0; arr!=null && index<arr.length;) {
			PricePointImpl pt = (PricePointImpl)arr[index];
			//CR1430 - changed to only retrieve the currently active impacts
			rv = pt.getAllBalanceImpacts().getCurrentNonCurrencyImpacts(date);
			break;
		}
		return rv;
	}

	/**
        @deprecated
	 */
	 @Deprecated
	public PricePointImpl getServicePricePoint(String packageId)
	 {
		 return getServicePricePoint(packageId, new Date());
	 }

	 public PricePointImpl getServicePricePoint(String packageId, Date dat)
	 {
		 PricePoint[] arr = getPricePointsByPackageId(packageId);

		 PricePointImpl rv = null;
		 if (arr != null) {
			 for (PricePoint element : arr) {
				 PricePointImpl pt = (PricePointImpl)element;
				 if (pt.isActive(dat)) {
					 rv = pt;
					 break;
				 }
			 }
		 }
		 return rv;
	 }

    //CR1564 -Utctimezone for diff region in country
    /**
     * @deprecated 
     */
    @Deprecated
	public BalanceImpact[] getCustomResourceBalanceImpact(int duration, int method) {
        return getCustomResourceBalanceImpact(duration, method, new Date());
    }

     //CR1564 -Utctimezone for diff region in country
	 public BalanceImpact[] getCustomResourceBalanceImpact(int duration, int method, Date date)
	 {
//		 PricePoint [] arr = getAll();
		 BalanceImpact[] rv = null;
//		 for (int index=0; arr!=null && index<arr.length; index++) {
		for(PricePoint ppt: this)	{
			if (ppt.getDuration() == duration && ppt.getChargingMethod()==method) {
				 PricePointImpl pt = (PricePointImpl)ppt;
				//CR1430 - changed to only retrieve the currently active impacts
				 rv = pt.getAllBalanceImpacts().getCurrentNonCurrencyImpacts(date);
				 break;
			 }
		 }
		 return rv;
	 }

    //CR1564 -Utctimezone for diff region in country
    /**
     * @deprecated 
     */
    @Deprecated
	public BalanceImpact[] getCustomResourceBalanceImpact(int duration) {
        return getCustomResourceBalanceImpact(duration, new Date());
    }

     //CR1564 -Utctimezone for diff region in country
	 public BalanceImpact[] getCustomResourceBalanceImpact(int duration, Date date)
	 {
//		 PricePoint [] arr = getAll();
		 BalanceImpact[] rv = null;
//		 for (int index=0; arr!=null && index<arr.length; index++) {
		 for(PricePoint p: this)	{
			 if (p.getDuration() == duration) {
				 PricePointImpl pt = (PricePointImpl)p;
 				 //CR1430 - changed to only retrieve the currently active impacts
				 rv = pt.getAllBalanceImpacts().getCurrentNonCurrencyImpacts(date);
				 break;
			 }
		 }
		 return rv;
	 }

    //CR1564 -Utctimezone for diff region in country
    /**
     * @deprecated 
     */
    @Deprecated
	public BalanceImpact[] getCustomResourceBalanceImpact(RatingAttributes rAttrs) {
        return getCustomResourceBalanceImpact(rAttrs, new Date());
    }

     //CR1564 -Utctimezone for diff region in country
	 //find the balance impact taking in account all relevant rating attributes,
	 //should only ever return one balance impact
	 public BalanceImpact[] getCustomResourceBalanceImpact(RatingAttributes rAttrs, Date date)
	 {
//		 PricePoint [] arr = getAll();
		 BalanceImpact[] rv = null;
//		 for (int index=0; arr!=null && index<arr.length; index++) {
		 for(PricePoint ppt: this)	{
			if ( (ppt.getDuration() == rAttrs.getDuration()) &&
					 (ppt.getChargingMethod() == rAttrs.getChargingMethod()) &&
					 (isEqualRatingStringValues(ppt.getPromoCode(),rAttrs.getPromoCode())) &&
					 (isEqualRatingStringValues(ppt.getUserGroup(),rAttrs.getUserGroup())) &&
					 (isEqualRatingIntValues(ppt.getPaymentType(),rAttrs.getPaymentType())) &&
					 (isEqualRatingIntValues(ppt.getChannel(),rAttrs.getChannel())) &&
					 //MQC 5964 - also check the tariff attribute
					 (isEqualRatingStringValues(ppt.getTariff(),rAttrs.getTariff())) ) {

				 PricePointImpl pt = (PricePointImpl)ppt;
				 //CR1430 - changed to only retrieve the currently active impacts
				 rv = pt.getAllBalanceImpacts().getCurrentNonCurrencyImpacts(date);
				 
				 break;
			 }
		 }
		 return rv;
	 }

	 private boolean isEqualRatingStringValues(String catalogValue, String rAttrsValue) {
		 if ( (catalogValue == null) || catalogValue.equals("") ) {
			 catalogValue = Constants.STRING_MATCH_ALL;
		 }
		 if ( (rAttrsValue == null) || rAttrsValue.equals("") ) {
			 rAttrsValue = Constants.STRING_MATCH_ALL;
		 }
		 return catalogValue.equals(rAttrsValue);
	 }

	 private boolean isEqualRatingIntValues(int catalogValue, int rAttrsValue) {
		 if (catalogValue==Constants.INT_NOT_SET || catalogValue==Constants.INT_MATCH_ALL)
		 {
			 return true;
		 }
		 else {
			 return catalogValue==rAttrsValue;
		 }
	 }
}
