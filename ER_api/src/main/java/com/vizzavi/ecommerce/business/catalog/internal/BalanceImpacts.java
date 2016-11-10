package com.vizzavi.ecommerce.business.catalog.internal;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.Date;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import com.vizzavi.ecommerce.business.common.ChargingResource;

/**
 * a thin wrapper around a List of BalanceImpacts
 * @author matt
 *
 */
public class BalanceImpacts  extends ArrayList<BalanceImpact>
{
    private static final long serialVersionUID = 1282300879887414938L; 
 

    public BalanceImpacts(Map<Object, BalanceImpact> data)
    {
        super(data.values());
    }

    public BalanceImpacts()
    {
        super();
    }

    public BalanceImpacts(BalanceImpacts impacts) {
       // mImpacts = new ArrayList<BalanceImpact>();

        if (impacts!=null) {
//            BalanceImpact[] impactsArr = impacts.getImpacts();
            for (BalanceImpact element : impacts) {
                BalanceImpact impact = new BalanceImpact(element);
                super.add(impact);
            }
        }
    }



    public BalanceImpacts(Collection<BalanceImpact> impacts) {
		super(impacts);
	}

	public BalanceImpact getImpact(String id)
    {
//        BalanceImpact[] impacts = getImpacts();
        BalanceImpact match = null;
//        for (int index=0; impacts!=null && index<impacts.length; index++) {
        for (BalanceImpact bi: this)	{
        	if (bi.getId().equals(id)) {
                match = bi;
            }
        }

        return match;
    }

    /**
     * DO NOT USE<br/>
     * TODO refactor pricing tool so it doesn't use this method<br/>
     * @deprecated A BalanceImpacts object is a List of BalanceImpact objects; why would you want an array?
     * @return
     */
    @Deprecated
    public BalanceImpact[] getImpacts()
    {
        return super.toArray(new BalanceImpact[size()]);
    }

    public BalanceImpact[] getImpacts(ChargingResource res)
    {
        Iterator<BalanceImpact> iter =  super.iterator();
        List<BalanceImpact> rv = new ArrayList<BalanceImpact>();
        while (iter.hasNext()) {
            BalanceImpact impact = iter.next();

            if (res==null || impact.getResource().getCode() == res.getCode() ) {
                rv.add(impact);
            }
        }

        return rv.toArray(new BalanceImpact[rv.size()]);
    }

    public BalanceImpact[] getCurrencyImpacts()
    {
        Iterator<BalanceImpact> iter =  super.iterator();
        List<BalanceImpact> rv = new ArrayList<BalanceImpact>();
        while (iter.hasNext()) {
            BalanceImpact impact = iter.next();
            if (impact.isCurrency()) {
                rv.add(impact);
            }
        }

        return rv.toArray(new BalanceImpact[rv.size()]);
    }

    public BalanceImpact[] getNonCurrencyImpacts()
    {
//        Iterator<BalanceImpact> iter =  super.iterator();
        List<BalanceImpact> rv = new ArrayList<BalanceImpact>();
//        while (iter.hasNext()) {
//            BalanceImpact impact = iter.next();
        for(BalanceImpact impact: this)	{
            if (!impact.isCurrency()) {
                rv.add(impact);
            }
        }

        return rv.toArray(new BalanceImpact[rv.size()]);
    }        

    //CR1564 -Utctimezone for diff region in country
    /**
     * @deprecated 
     */
    @Deprecated
	public BalanceImpact[] getCurrentNonCurrencyImpacts() {
        return getCurrentNonCurrencyImpacts(new Date());
    }

    //CR1564 -Utctimezone for diff region in country
    //CR1430 START
    public BalanceImpact[] getCurrentNonCurrencyImpacts(Date date)
    {
        BalanceImpact[] allImpacts = super.toArray(new BalanceImpact[size()]);
        HashMap<Integer, BalanceImpact> validImpacts = new HashMap<Integer, BalanceImpact>();
        //sort first.
        Arrays.sort(allImpacts);
        
        Date now = (date != null) ? date : new Date();
        for (int index=0; index<allImpacts.length; index++) {
        	if(!allImpacts[index].isCurrency()){
        		Date pcSD = allImpacts[index].getPriceChangeStartDate();
        		//get the correct rate depending on the start date
        		//as the array is sorted ascending by date, we add null date rates first,
        		//then if there is a date in the past, we'll replace with that
        		//and ignore the rate for any dates in the future.
        		if ( pcSD == null || (now.compareTo(pcSD) > 0)){
        			validImpacts.remove(allImpacts[index].getResource().getCode());
        			validImpacts.put(allImpacts[index].getResource().getCode(), allImpacts[index]);
        		}
        	}
        }
        
        return validImpacts.values().toArray(new BalanceImpact[validImpacts.size()]);
    }
    //CR1430 END


    public BalanceImpact[] getChargingResource()
    {
        Iterator<BalanceImpact> iter =  super.iterator();
        List<BalanceImpact> rv = new ArrayList<BalanceImpact>();
        while (iter.hasNext()) {
            BalanceImpact impact = iter.next();
            if (impact.isResource()) {
                rv.add(impact);
            }
        }

        return rv.toArray(new BalanceImpact[rv.size()]);
    }

    //CR1564 -Utctimezone for diff region in country
    /**
     * @deprecated 
     */
    @Deprecated
	public double getRate(ChargingResource res) {
        return getRate(res, 0.0, new Date());
    }

    //CR1564 -Utctimezone for diff region in country
    public double getRate(ChargingResource res, Date date)
    {
        return getRate(res, 0.0, date);
    }


	public double getRate(ChargingResource res, double volumeAmount) {
        return getRate(res, volumeAmount, null);
    }

	//CR1430START
    /**
     * CR1430
     * changed to get the current rate according to the date in the priceplan.
     * @param res
     * @param volumeAmount
     * @return
     */
    public double getRate(ChargingResource res, double volumeAmount, Date date)
    {
        BalanceImpact[] impacts = getImpacts(res);
        //sort first.
        Arrays.sort(impacts);        

        double amount = 0;
        Date now = (date != null) ? date : new Date();
        for (BalanceImpact impact : impacts) {
        	Date pcSD = impact.getPriceChangeStartDate();
        	//get the correct rate depending on the start date
        	//as the array is sorted ascending by date, we add null date rates first,
        	//then if there is a date in the past, we'll replace with that
        	//and ignore the rate for any dates in the future.
        	if ( pcSD == null || (now.compareTo(pcSD) > 0)){
        		amount = impact.getRate(volumeAmount);
        	}
// was:           amount = amount + impacts[index].getRate(volumeAmount); 
        	//which was, incidentally, misleading because previously, you could never have had >1 balance impact for a given charging resource type
        }            

        return amount;
    }
	//CR1430END

    //CR1564 -Utctimezone for diff region in country
    /**
     * @deprecated 
     */
    @Deprecated
	public double getRate() {
        return getRate(new Date());
    }

    public double getRate(Date date)
    {
        return getRate(0.0, date);
    }

    //CR1564 -Utctimezone for diff region in country
	//CR1430START
    /**
     * CR1430
     * changed to get the current rate according to the date in the priceplan.
     * @param volumeAmount
     * @return
     * @deprecated
     */
    @Deprecated
	public double getRate(double volumeAmount)
    {
        return getRate(volumeAmount, new Date());
    }
	//CR1430END

    /**
     * returns the ChargingResource of the first BalanceImpact which is a currency
     * @return
     */
    public ChargingResource getCurrency()
    {
//        ChargingResource rv = null;
//        Iterator<BalanceImpact> iter =  super.iterator();
//        while (iter.hasNext()) {
//            BalanceImpact impact = iter.next();
//
//            if (impact.isCurrency()) {
//                rv = impact.getResource();
//                break;
//            }
//        }
//
//        return rv;
    	for (BalanceImpact impact: this)	{
    		if (impact.isCurrency()) 
    			return impact.getResource();
    	}
    	return null;
    }

    /**
     * returns the LAST BalanceImpact in the List which is not a currency but which has +ve fixed or scaled amount
     * @return
     */
    public ChargingResource getNonCurrencyResource()
    {

        Iterator<BalanceImpact> iter =  super.iterator();
        ChargingResource rv = null;
        while (iter.hasNext()) {
            BalanceImpact impact = iter.next();

        	// if Non Currency Charging Resource should be +ve value 
            if (!impact.isCurrency() && (impact.getFixedAmount() >  0.0 || impact.getScaledAmount() > 0.0)) {
                rv = impact.getResource();
            }
        }


        return rv;
    }



    public ChargingResource[] getResources()
    {
        Iterator<BalanceImpact> iter =  super.iterator();
        Map<ChargingResource, BalanceImpact> rv = new HashMap<ChargingResource, BalanceImpact>();
        while (iter.hasNext()) {
            BalanceImpact impact = iter.next();

            if (!impact.isCurrency()) {
                rv.put(impact.getResource(), impact);
            }
        }

        return rv.keySet().toArray(new ChargingResource[rv.size()]);
    }

    public void addBalanceImpact(BalanceImpact impact) 
    {
//        deleteBalanceImpact(impact);//CR1430 removed deletion of BalanceImpact of same Charging Resource type to allow a new price in the future.
        super.add(impact);
    }

    public void deleteBalanceImpact(BalanceImpact impact)
    {
        BalanceImpact impactToDelete = getImpact(impact.getId());
        if (impactToDelete!=null) {
            super.remove(impactToDelete);
        }
    }

    public BalanceImpact[] getBalanceImpacts()
    {
        Iterator<BalanceImpact> iter =  super.iterator();
        List<BalanceImpact> rv = new ArrayList<BalanceImpact>();
        while (iter.hasNext()) {
            BalanceImpact impact = iter.next();

            rv.add(impact);
        }

        return rv.toArray(new BalanceImpact[rv.size()]);
    }

//    @Override
//	public String toString()
//    {
//        return super.toString();
//    }

    //CR1430 - Start
    public double getRate(double volumeAmount, Date rateDate) {
    	BalanceImpact[] impacts = getCurrencyImpacts();
    	//sort first.
    	Arrays.sort(impacts);

    	double amount = 0;
    	for (BalanceImpact impact : impacts) {
    		Date pcSD = impact.getPriceChangeStartDate();
    		//get the correct rate depending on the start date
    		//as the array is sorted ascending by date, we add null date rates first,
    		//then if there is a date in the past, we'll replace with that
    		//and ignore the rate for any dates in the future.
    		if ( pcSD == null || (rateDate.compareTo(pcSD) > 0)){
    			amount = impact.getRate(volumeAmount);
    		}
// was:     amount = amount + impacts[index].getRate(volumeAmount); 
    		//which was, incidentally, misleading because previously, you could never have had >1 balance impact for a given charging resource type
    	}

    	return amount;
    }
    //CR1430 - End

}
