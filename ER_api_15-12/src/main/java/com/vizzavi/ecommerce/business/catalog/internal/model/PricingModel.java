package com.vizzavi.ecommerce.business.catalog.internal.model;

import java.io.Serializable;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Date;
import java.util.List;
/**
    This represents the date time model that is used by ECOM_SERVICES_api

    A date time model has
        Start date
        End date
        Day Range
        Month Range
        Time Range

        A range can have a start and end range (seperated by hypen
        Or a list of allowed values (seperated by commas)

*/
public class PricingModel implements Serializable
{
   private    static final long serialVersionUID = -5993296324946437421L;
    private String mCreatedBy;
    private String mModifiedBy;
    private Date mModifiedDate;
    private char mActiveStatus;
    private final String mId;
    private String mName;
    private Date mStartDate;
    private Date mEndDate;
    private final List<Tier> mTiers = new ArrayList<Tier>();

    public PricingModel(String id)
    {
        mId = id;
    }
   public PricingModel(String id, String createdBy, String modifiedBy, Date modifiedDate, char activeStatus)
    {
        mCreatedBy = createdBy;
		mModifiedBy = modifiedBy;
        mModifiedDate = modifiedDate;
    	mActiveStatus = activeStatus;

        mId = id;
    }
    public String getCreatedBy() {
        return mCreatedBy;
    }

    public String getModifiedBy() {
        return mModifiedBy;
    }

    public Date getModifiedDate() {
        return mModifiedDate;
    }

    public char getActiveStatus() {
        return mActiveStatus;
    }

	public void setCreatedBy(String createdBy) {
        mCreatedBy = createdBy;
    }

    public void setModifiedBy(String modifiedBy) {
        mModifiedBy = modifiedBy;
    }

    public void setModifiedDate(Date modifiedDate) {
        mModifiedDate = modifiedDate;
    }

    public void setActiveStatus(char activeStatus) {
    	mActiveStatus = activeStatus;
    }

    public String getId()
    {
        return mId;
    }

    public String getName()
    {
        return mName;
    }

    public void setName(String name)
    {
        mName = name;
    }

    public Date getStartDate()
    {
        return mStartDate;
    }

    public Date getEndDate()
    {
        return mEndDate;
    }

    // This will be overridden by the price point
    public void setStartDate(Date val)
    {
        mStartDate = val;
    }

    public void setEndDate(Date val)
    {
        mEndDate = val;
    }

    public Tier[] getTiers()
    {
        Collections.sort(mTiers, new TierOrderComparator());
        return mTiers.toArray(new Tier[mTiers.size()]);
    }

    public void deleteTier(String id)
    {
        Tier val = getTier(id);
        if (val!=null) {
            //int index = mTiers.indexOf(val);
            mTiers.remove(val);
        }
    }

    public void addTier(int index, Tier tier)
    {
        mTiers.add(index, tier);
    }

    public void addTier(Tier tier)
    {
        Tier val = getTier(tier.getId());
        if (val==null) {
            mTiers.add(tier);
        } else {
            int index = mTiers.indexOf(val);
            mTiers.add(index, tier);
        }
    }

    public Tier getTier(String id)
    {
        Tier[] tiers = getTiers();
        Tier rv = null;
        for (int index=0; tiers!=null && index<tiers.length; index++) {
            if (tiers[index].getId().equals(id)) {
                rv = tiers[index];
                break;
            }
        }

        return rv;
    }

    private static SimpleDateFormat sFormat = new SimpleDateFormat("dd/MM/yyyy");

    public static String formatDate(Date dat)
    {
        synchronized(sFormat) {
            return sFormat.format(dat);
        }
    }

    public static Date parseDate(String val) throws ParseException
    {
        synchronized(sFormat) {
            return sFormat.parse(val);
        }
    }

    public boolean isActive(Date currentTime)
    {
        boolean rv = true;
        if (mStartDate!=null && currentTime.before(mStartDate)) {
            rv = false;
        } else if (mEndDate!=null  && currentTime.after(mEndDate)) {
            rv = false;
        }
        return rv;
    }

	@Override
	public String toString()
	{
		StringBuffer buf = new StringBuffer();
		buf.append("{");

		buf.append(" Pricing Model Id = "+ mId);
		buf.append(" Name = "+ mName);
		if (mStartDate != null) {
			buf.append(" StartDate = "+ mStartDate.toString());
		}
		if (mEndDate != null) {
			buf.append(" EndDate = "+ mEndDate.toString());
		}
		buf.append("}");
		return buf.toString();
	}

}
