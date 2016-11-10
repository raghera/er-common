package com.vizzavi.ecommerce.business.catalog.internal;

import java.util.ArrayList;
import java.util.List;

import com.vizzavi.ecommerce.business.catalog.CatalogPackage;
import com.vizzavi.ecommerce.business.catalog.PricePoint;
import com.vodafone.global.er.util.CatalogUtil;
/**
    The package identifier consists of

    <Simple package id>__X__<Simple price point id>__Y__<Simple tier id>__Z__<model id>
    is now:
    <Simple package id>__X__<Simple price point id>__<SCID>__Y__<Simple tier id>__Z__<model id>
*/
public class CatalogPackageIdentifier
{

    /**
     * <p>pass in either a full package Id OR a pricepoint ID and get the simple package Id back.</p>
     * e.g. pass in 'p001__X__package:p001_TAX_3_2_10010_999_*' or 'package:p001_TAX_3_2_10010_999_*' and get 'p001'<br/>
     * TODO for this to work, we must enforce no underscores in packageId and no package Ids containing the string 'package'
     * MQC 8385
    */
    public final static String getSimplePackageId(String rateIdentifier)
    {
    	
    	String packageId = rateIdentifier;
    	int index = rateIdentifier.indexOf(CatalogPackage.PACKAGE_DELIMITER);
        //if (rateIdentifier != null && >=0) {
          if (index >= 0)  {
            	packageId = rateIdentifier.substring(0, index);
        }	else if (rateIdentifier.indexOf(PricePoint.PACKAGE_PRICEPOINT_ID_PREFIX)==0)		{//ie this is a pricepoint starting 'package:....'
        	//eg 'package:p001_TAX_3_2_10010_999_*'
        	packageId = CatalogUtil.getPackageIdFromPricepoint(rateIdentifier);
        	//log.debug("decoded pricepoint {} to packageId {}", rateIdentifier, packageId);
        }
        return packageId;
    }

    /**
     * MQC 8385
     * TODO work out whether this returns e.g. p001_TAX_3_2_10010_999_*, package:p001_TAX_3_2_10010_999_*, or p001__X__package:p001_TAX_3_2_10010_999_*
    */
    public final static String getPricePointId(String id)
    {
        String retId = null;
        //int index = id.indexOf(CatalogPackage.PACKAGE_DELIMITER);
		String id1 = removeSuperCreditIDFromID(id);
		//retId = id1;
		
		//@hud RFRFRF
		// check if it's a rate identifier
		int i = id1.indexOf(CatalogPackage.PRICEPOINT_ID_STARTER);
		if (i > 0 ) {

			i = id1.indexOf(CatalogPackage.PACKAGE_DELIMITER);
			if (i >= 0)  {
	            //rv = id.substring(index+CatalogPackage.PACKAGE_DELIMITER.length());
	        	retId = id1.substring(i + CatalogPackage.PACKAGE_DELIMITER.length());
	        }
			else {
				retId = id1;
			}
		}
        return retId;
    }
    
    /**
     * @hud RFRFRF
     * 
     */
    /*
    public final static String getMapPricePointId(String rateIdentifier)
    {
    	String retId = null;
    	
		String id1 = removeSuperCreditIDFromID(rateIdentifier);
    	
    	
    	return retId;
    }
	*/

    /**
        Returns the <Simple price point id>. It assumes that the package id has already been removed.
    */
    public final static String getSimplePricePointIdRemovingPackageId(String id)
    {
        String id1 = removeSuperCreditIDFromID(id);

        //String ppId = getPricePointId(id);
        String ppId = getPricePointId(id1);
        if (ppId==null) {
            //ppId = id;
            ppId = id1;
        }
        int index = ppId.indexOf(CatalogPackage.TIER_DELIMITER);
        if (index>=0)  {
            ppId = ppId.substring(0, index);
        }
        return ppId;
    }

    /**
        Returns the <Simple price point id>. It assumes that the package id has already been removed.
    */
    public final static String getSimplePricePointId(String id)
    {
/*
        String ppId = getPricePointId(id);
        if (ppId==null) {
            ppId = id;
        }
*/
        //String ppId = id;
		//String id1 = removeSuperCreditIDFromID(new String (id));
    	String id1 = removeSuperCreditIDFromID(id);
        String ppId = id1;
        int index = ppId.indexOf(CatalogPackage.TIER_DELIMITER);
        if (index>=0)  {
            ppId = ppId.substring(0, index);
        }
        return ppId;
    }
    
    //@hud RFRFRF 
    public final static String calcPackagePricePointId(String simplePackageId, String ppId)
    {
    	StringBuffer sb = new StringBuffer();
    	sb.append(simplePackageId).append(CatalogPackage.PACKAGE_DELIMITER)
    		.append(ppId);
    	
    	return sb.toString();
    }

    /**
        Returns the <Simple tier id>
    */
    public final static String getTier(String id)
    {
        String rv = null;
        int index = id.lastIndexOf(CatalogPackage.TIER_DELIMITER);
        if (index>=0)  {
            rv = id.substring(index+CatalogPackage.TIER_DELIMITER.length());
        }
        return rv;
    }


    /**
        This splits the id into its component parts
        It is assumed that you know whether it is package id or pricepoint id
    */
    //public final static String[] getIdentifiers(String id)
    public final static String[] getIdentifiers(String id1)
    {
        List<String> rv = new ArrayList<String>();
        
        String id = removeSuperCreditIDFromID(id1);
        
        int index = id.indexOf(CatalogPackage.PACKAGE_DELIMITER);
        if (index>=0) {
            rv.add(id.substring(0, index));
            id = id.substring(index+CatalogPackage.PACKAGE_DELIMITER.length());
        }
        index = id.indexOf(CatalogPackage.TIER_DELIMITER);
        if (index>=0) {
            rv.add(id.substring(0, index));
            id = id.substring(index+ CatalogPackage.TIER_DELIMITER.length());
        }
        index = id.indexOf(CatalogPackage.MODEL_DELIMITER);
        if (index>=0) {
            rv.add(id.substring(0, index));
            id = id.substring(index + CatalogPackage.MODEL_DELIMITER.length());
        }
        if (id!=null && id.length()>0) {
            rv.add(id);
        }

        return rv.toArray(new String[rv.size()]);
    }


    /**
     * This method parses the pricepoint rate identifier to return the pricepoint
     * @param ppId
     * @return
     */
    public static String getPricePointFromIdentifier(String ppId)
    {
        String _ppId = ppId;
        int index = ppId.indexOf(CatalogPackage.PACKAGE_DELIMITER);

        
        /*
         * MQC 5013 / 5032 Start
         *
         * This may have got here from a usage so there may not be a package delimiter
         * If so return the same string.  Added index > -1
         */
        if (index > -1 )
        {
        	_ppId = _ppId.substring(index + CatalogPackage.PACKAGE_DELIMITER.length());
        }

        return _ppId;
    }


    public static String getPricePointIdentifier(String ppId, String tierId, String modelId)
    {
        String rv = ppId;

        if (tierId!=null && tierId.equals("")==false) {
            rv = rv + CatalogPackage.TIER_DELIMITER + tierId + CatalogPackage.MODEL_DELIMITER + modelId;
        }

        return rv;
    }

//[VFE][ER7 PHASE III]SUPER CREDITS[START]
    //@hud RFRFRF BADLY IMPLEMENTED
    // This method gets called very frequently, we MUST optimize it
	public static String removeSuperCreditIDFromID(String ppId)
	{
		//return var
		String retId;
		
		
		// firstly, check if super credit delimiter exists
		int iSuper = ppId.indexOf(CatalogPackage.SUPERCREDIT_DELIMITER);
		if (iSuper == -1) {
			retId = ppId;
		}
		else {
			int iTier = ppId.indexOf(CatalogPackage.TIER_DELIMITER);
			int iPack = ppId.indexOf(CatalogPackage.PACKAGE_DELIMITER);
			String id1;
			
			if (iPack == -1) {
				if (iTier == -1) {
					id1 = ppId;
				}
				else {
					id1 = ppId.substring(0, iTier);
				}
			}
			else {
				if (iTier == -1) {
					id1 = ppId.substring(iPack + CatalogPackage.PACKAGE_DELIMITER.length());
				}
				else {
					id1 = ppId.substring(iPack + CatalogPackage.PACKAGE_DELIMITER.length(), iTier);
				}
			}
			
			iSuper = id1.indexOf(CatalogPackage.SUPERCREDIT_DELIMITER);
			if (iSuper != -1) {
				String id2 = id1.substring(iSuper);
				StringBuffer sb = new StringBuffer(ppId);
				iSuper = ppId.indexOf(id2);
				sb = sb.delete(iSuper, iSuper + id2.length());
				retId = sb.toString(); 
			}
			else {
				retId = ppId;
			}
		}
		
		return retId;
		
		/* original impl
		String result = "";
		String SCID = "";
		int indexX = anyID.indexOf(CatalogPackage.PACKAGE_DELIMITER);
		int indexY = anyID.indexOf(CatalogPackage.TIER_DELIMITER);
		String SimpleId__SCID= anyID.substring((indexX == -1)?0:indexX+CatalogPackage.PACKAGE_DELIMITER.length(), 
				(indexY == -1)?anyID.length():indexY);
		int i = 0;
		//if(-1!=(i = SimpleId__SCID.indexOf("__")))
		if(-1!=(i = SimpleId__SCID.indexOf(CatalogPackage.SUPERCREDIT_DELIMITER)))
		{
			SCID = SimpleId__SCID.substring(i);
			StringBuffer resultBuffer = new StringBuffer(anyID);
			resultBuffer = resultBuffer.delete(i=anyID.indexOf(SCID), i+SCID.length());
            result = resultBuffer.toString(); 
		}
		else
			result = anyID;
		return result;
		*/		
	}

	public static String getSuperCreditIDFromID(String anyID)
	{
		String result = "-1";
		//String SCID = "";
		int indexX = anyID.indexOf(CatalogPackage.PACKAGE_DELIMITER);
		int indexY = anyID.indexOf(CatalogPackage.TIER_DELIMITER);
		String SimpleId__SCID= anyID.substring((indexX == -1)?0:indexX+CatalogPackage.PACKAGE_DELIMITER.length(), (indexY == -1)?anyID.length():indexY);
		int i = 0;
		//if(-1!=(i = SimpleId__SCID.indexOf("__")))
		if(-1!=(i = SimpleId__SCID.indexOf(CatalogPackage.SUPERCREDIT_DELIMITER)))
			result = SimpleId__SCID.substring(i+ CatalogPackage.SUPERCREDIT_DELIMITER.length());

		if(!isNumericalVal(result))
			result = "-1";
		return result;		
	}    
	
	public static boolean isNumericalVal(String anyID)
	{
		boolean isNumerical = true;
		if(anyID != null && !"".equals(anyID)){
			for(int i=0;i<anyID.length();i++)
		 	{
				if(!Character.isDigit(anyID.charAt(i)))
				{
					if(i ==0 && (anyID.charAt(i) == '-'));
					else{
						isNumerical = false;
						break;
					}
				}
		 	}
		}
		return isNumerical;	
	}  
//[VFE][ER7 PHASE III]SUPER CREDITS[END]

}
