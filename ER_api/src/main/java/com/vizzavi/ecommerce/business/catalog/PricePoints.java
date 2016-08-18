package com.vizzavi.ecommerce.business.catalog;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.log4j.Logger;

import com.vizzavi.ecommerce.business.common.ChargingMethod;
import com.vizzavi.ecommerce.business.common.Constants;


/**
 * This is a List&lt;PricePoint>, which can be associated with e.g. a package or a service. 
 *
 */
public class PricePoints extends ArrayList<PricePoint>
{
	private    static final long serialVersionUID = 8883197601513591653L;
	private static Logger logger = Logger.getLogger(PricePoints.class);

	
	/**
	 * There may be multiples ways to access the price points in a service or a package
	 * In order for fast access
	 * A set of hashmaps must be created for this purpose
	 * 
	 *   Map of price points.
	 *   Each price point uses the price point id as the key to the map.
	 */
	//@hud RFRFRF
	protected Map<String, PricePoint> mPricePointMapById = new HashMap<String, PricePoint>();
	protected Map<String, ArrayList<PricePoint>> mPricePointMapByPackageId = null;	// only for service price point, and create upon first hit

    

	
//	// sorted with price point comparator
//	protected PricePoint[] mPricePointArray = null;


	public PricePoints(){
		super();
	}

	/**
	 * @hud this method name is confusing!
	 * 
      * This find all of the content price points matching  a package
	 */
	public PricePoint[] getPackagePricePoints(String packageId)
	{
		return getPricePointsByPackageId(packageId);
	}
	
	public PricePoint[] getPricePointsByPackageId(String packageId)
	{
		ArrayList<PricePoint> retpps = null;
		
		if (mPricePointMapByPackageId == null) {
			mPricePointMapByPackageId = new HashMap<String, ArrayList<PricePoint>>();
		}
		
		if (mPricePointMapByPackageId.get(packageId) == null) {
			// we need to create one now
			retpps = new ArrayList<PricePoint>();
			
//			PricePoint[] ppAll = getAll();
//			if (ppAll != null) {
			for (PricePoint element : this) {
				if (element.getPackageId().equals(packageId)) {
					retpps.add(element);
				}
			}			
//			}
			
			if (retpps.size() > 0) {
				mPricePointMapByPackageId.put(packageId, retpps);
			}			
		}
		else {
			retpps = mPricePointMapByPackageId.get(packageId);			
		}
		
		return retpps.toArray(new PricePoint[retpps.size()]);
		
		
	
	}

	public PricePoints(Map<String, PricePoint> points)
	{
		super(points.values());
		mPricePointMapById = points;
		sort();
	}

	/**
        Method only used in pricing tool - TODO refactor pt a bit so it doesn't use this method.<br/>
        Returns all of the pricepoints, in the form of an array, but is probably quite slow since it creates a new array each time
        @deprecated - a PricePoints object is already a list of PricePoint objects
	 */
	@Deprecated
	public PricePoint[] getAll()
	{
		return toArray(new PricePoint[size()]);
	}
	
	private void sort()	{
		Collections.sort(this, new PricePointComparator());
	}

	/**
	 * get all the pricepoints which match the duration and chargingmethod supplied
	 * @param duration
	 * @param chargingMethod
	 * @return
	 */
	public PricePoint[] getAll(int duration, int chargingMethod)
	{
		//Iterator<PricePoint> iter = mPricePointMapById.values().iterator();
		List<PricePoint> rv = new ArrayList<PricePoint>();
		//while (iter.hasNext()) {
		//	PricePoint pt = iter.next();
		for(PricePoint pt: this)	{
			if (Constants.isMatchAll(duration) || Constants.isNotSet(duration) || duration == pt.getDuration()) {
				if (Constants.isMatchAll(chargingMethod) || Constants.isNotSet(chargingMethod) || chargingMethod == pt.getChargingMethod()) {
					rv.add(pt);
				}
			}
		}

		return rv.toArray(new PricePoint[rv.size()]);
	}

	/**
        Find the price point with the same price point id
        @param String the id of price point to find
        @return PricePoint null is returned if no matching price point found

	 */
	public PricePoint getPricePoint(String id)
	{
		return mPricePointMapById.get(id);
	}

	/**
        This finds the matching trial price point.
        The matching price point is the one that has the same duration value.

        @param PricePoint the price point to match
        @return PricePoint null if no matching price point otherwise the matching trial pricepoint
	 */
	public PricePoint findMatchingTrialPricePoint(PricePoint pt)
	{
//		PricePoint[] pts = getAll();
		PricePoint trial = null;
		PricePoint rv = null;
//		for (int index=0; pts!=null && index<pts.length; index++) {
//			if (pts[index].isTrial()) {
//				trial = pts[index];
//				if (pt.getDuration() == pts[index].getDuration()) {
//					rv = pts[index];
//					break;
//				}
//			}
//		}

		for(PricePoint ppt:this)	{
			if (pt.isTrial())	{
				trial=ppt;
				if (ppt.getDuration() == pt.getDuration())	{
					rv=ppt;
					break;
				}
			}
		}
		if (rv==null) {
			rv = trial;
		}

		return rv;
	}

	/**
        This finds the matching price point from the trial price point.
        The matching price point is the one that has the same duration value.

        @param PricePoint the price point to match
        @return PricePoint null if no matching price point otherwise the matching trial pricepoint
	 */
	public PricePoint findMatchingPricePointFromTrial(PricePoint pt)
	{
//		PricePoint[] pts = getAll();
		PricePoint trial = null;
		PricePoint rv = null;

//		for (int index=0; pts!=null && index<pts.length; index++) {
		for(PricePoint p: this)	{
			String code = p.getPromoCode();
			if (Constants.isMatchAll(code)) {
				trial = p;
				if (pt.getDuration() == p.getDuration()) {
					rv = p;
					break;
				}
			}
		}

		if (rv==null) {
			rv = trial;
		}

		return rv;
	}

	/**
	 * @description  This returns true if a matching UserGroup is found between the 2 Pricepoints being compared.
	 * @return   True if a matching UserGroup is found between the 2 Pricepoints being compared else false.
	 **/
	boolean isMatchingUserGroup(PricePoint undiscountPt, PricePoint pt){
		Object[] groups1 = undiscountPt.getNonMatchAllUserGroups();
		Object[] groups2 = pt.getNonMatchAllUserGroups();

		for (int i = 0 ; i < groups1.length; i++){
			for (Object element : groups2) {	//TODO is this a bug?  shouldn't it be element instead of groups2[i] below?
				if ( ((String)groups1[i]).equals(groups2[i]) )
					return true;
			}    		
		}    	
		return false;    	
	}

	/**
	 * @description  The purpose of this logic is to return the Undiscounted PricePoint for an input Price Point that has a promo code and a user group.
	 *               If the input PricePoint is Trial and Recurring, then return the linked price point. 
	 *               else return a non-promo price point (undiscounted one)that has the same attributes as the input price point.
	 * @param        PricePoint: the price point to match
	 * @return       PricePoint null if no matching price point otherwise the matching undiscounted pricepoint.
	 **/
	public PricePoint findMatchingUndiscountedPricePoint(PricePoint pt)
	{

		PricePoint undiscountPt = null;
		PricePoint rv = null;

		//If pricepoint is Trial and recurring, then get the linked pricepoint associated with it.
		if (pt.isTrial() && ChargingMethod.isRecurring(pt.getChargingMethod()) ) { 
			String linkedPt = pt.getPricepointIdLink();
			if (linkedPt != null) {
				rv = this.getPricePoint(linkedPt);    //Get the PricePoint belongs to this package. Don't need to search all catalog.
				logger.debug("Pricepoints.findMatchingUndiscountedPricePoint(): Return Linked Price Point ");
			} else {
				logger.debug("Pricepoints.findMatchingUndiscountedPricePoint(): Linked Price Point not found, Return null");
			}
		} else if (pt.isPromo() && pt.IsUserGroups() ) {//Get the undiscounted price for the same usergroup - Remedy 3645 
			//PricePoint[] pts = getAll();      //Returns all of the pricepoints of this package
			logger.debug("Pricepoints.findMatchingUndiscountedPricePoint(): Input Price Point is Promo and has a user group, trying to find undiscount PPT ");
//			for (int index=0; pts!=null && index<pts.length; index++) {
			for(PricePoint ppt: this)	{
				if ( !ppt.isPromo() && ppt.IsUserGroups()) {  
					undiscountPt = ppt;
					if ((undiscountPt.getDuration() == pt.getDuration())  &&
							(undiscountPt.getChargingMethod() == pt.getChargingMethod()) &&
							(undiscountPt.getPaymentType()== pt.getPaymentType()) &&
							(undiscountPt.getAccessDevice() == pt.getAccessDevice()) &&
							(undiscountPt.getPremiumLevel() == pt.getPremiumLevel()) &&
							(undiscountPt.getChannel() == pt.getChannel()) && isMatchingUserGroup(undiscountPt, pt) ) {
						rv = undiscountPt;
						break;
					}
				} //End IF (not promo)
			} 
		} else if (pt.isPromo()) {//Get the undiscounted price for * - Remedy 3645
//			PricePoint[] pts = getAll();      //Returns all of the pricepoints of this package
			logger.debug("Pricepoints.findMatchingUndiscountedPricePoint(): Input Price Point is Promo and has no user group, trying to find undiscount PPT ");
//			for (int index=0; pts!=null && index<pts.length; index++) {
			for(PricePoint ppt: this)	{
				if ( !ppt.isPromo() && !ppt.IsUserGroups()) {  
					undiscountPt = ppt;
					if ((undiscountPt.getDuration() == pt.getDuration())  &&
							(undiscountPt.getChargingMethod() == pt.getChargingMethod()) &&
							(undiscountPt.getPaymentType()== pt.getPaymentType()) &&
							(undiscountPt.getAccessDevice() == pt.getAccessDevice()) &&
							(undiscountPt.getPremiumLevel() == pt.getPremiumLevel()) &&
							(undiscountPt.getChannel() == pt.getChannel()) ) {
						rv = undiscountPt;
						break;
					}
				} //End IF (not promo)
			} 
		} else if (pt.IsUserGroups()) {//Get the undiscounted price for * - Remedy 6262
//			PricePoint[] pts = getAll();      //Returns all of the pricepoints of this package
			logger.debug("Pricepoints.findMatchingUndiscountedPricePoint(): Input Price Point is User group and has no promo, trying to find undiscount PPT ");
//			for (int index=0; pts!=null && index<pts.length; index++) {
			for(PricePoint ppt: this)	{
				if ( !ppt.isPromo() && !ppt.IsUserGroups()) {  
					undiscountPt = ppt;
					if ((undiscountPt.getDuration() == pt.getDuration())  &&
							(undiscountPt.getChargingMethod() == pt.getChargingMethod()) &&
							(undiscountPt.getPaymentType()== pt.getPaymentType()) &&
							(undiscountPt.getAccessDevice() == pt.getAccessDevice()) &&
							(undiscountPt.getPremiumLevel() == pt.getPremiumLevel()) &&
							(undiscountPt.getChannel() == pt.getChannel()) ) {
						rv = undiscountPt;
						break;
					}
				} //End IF (not promo)
			} 
		} else {//End else        
			logger.debug("Pricepoints.findMatchingUndiscountedPricePoint(): Input Price Point is not Promo OR don't belong to a user group, Return null");
		}
		return rv;
	}


	/** Added 29/09/2005 
	 * For all the price points in this package get the promocodes 
	 * 
	 **/ 
	public String[] getPromoCodes() { 
		ArrayList<String> codes = new ArrayList<String>(); 
		//PricePoint pts[] = getAll(); 
		//if(pts != null && pts.length > 0) { 
			//loop for all the price points and get the array of promocodes 
			for (PricePoint pt : this) { 
				String promo = pt.getPromoCode(); 
				codes.add(promo); 
			} 
		//} 
		return codes.toArray(new String[codes.size()]); 
	} 

	/** Added 29/09/2005 **/ 
	public String[] getUserGroups() { 
		ArrayList<String> groups = new ArrayList<String>(); 
//		PricePoint pts[] = getAll(); 
//		if(pts != null && pts.length > 0) { 
			//loop for all the price points and get the array of promocodes 
			for (PricePoint pt : this) { 
				String[] groupArray= pt.getUserGroups(); 
				if(groupArray != null) { 
					for (String group : groupArray) { 
						groups.add(group); 
					} 
				} 
			} 
//		} 
		groups.add(Constants.STRING_MATCH_ALL); 
		groups.add(Constants.STRING_NOT_SET); 
		return groups.toArray(new String[0]); 
	} 
	//CR1503aL. START
	public void putPricePoint(PricePoint p){
		this.add(p);
	}
	
	@Override
	public boolean add(PricePoint p)	{
		mPricePointMapById.put(p.getId(), p);
		boolean rv =  super.add(p);
		sort();
		return rv;
	}
	
	@Override
	public boolean remove(Object o)	{
		PricePoint p = (PricePoint)o;
		mPricePointMapById.remove(p.getId());
		boolean rv =  super.remove(p);
		sort();
		return rv;
	}
	//CR1503aL. END
	
	/** 
	 * a comma-separated list of the pricepoint Ids.
	 */
	public String toString()	{
		StringBuilder sb = new StringBuilder();
		for (PricePoint p: this)	{
			sb.append(p.getId()).append(", ");
		}
		return sb.toString();
	}
}
