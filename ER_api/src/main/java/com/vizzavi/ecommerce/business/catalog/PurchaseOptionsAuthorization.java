package com.vizzavi.ecommerce.business.catalog;

import java.io.Serializable;
import java.util.ArrayList;

import com.vizzavi.ecommerce.business.common.ReasonCode;

public class PurchaseOptionsAuthorization implements Serializable {

	private static final long serialVersionUID = 208445763770123059L;
	
	private boolean success;
	
	private ReasonCode reason;
	
	private ArrayList<CatalogPackage> mCatalogPackages = null;
	
	//MQC 13096 - DE Opco needs more information in response for the case of Account Not 
	//validated by the external system, so adding the below fields will serve the purpose - start
	
	private String errId;
	private String errDescription;
	
	public String getErrId() {
		return errId;
	}

	public void setErrId(String errId) {
		this.errId = errId;
	}

	public String getErrDescription() {
		return errDescription;
	}

	public void setErrDescription(String errDescription) {
		this.errDescription = errDescription;
	}

	//MQC 13096 code ends

	public boolean isSuccess() {
		return success;
	}
	
	public void setSuccess(boolean success) {
		this.success = success;
	}
	
	public ReasonCode getReasonCode() {
		return this.reason;
	}
	
	public void setReasonCode(ReasonCode reason) {
		this.reason = reason;
	}
	
	public void setCatalogPackages(CatalogPackage[] catalogPackages) {
		if (catalogPackages != null && catalogPackages.length > 0) {
			if (mCatalogPackages == null) {
				mCatalogPackages = new ArrayList<CatalogPackage>();
			}
			for (int i=0; i < catalogPackages.length; i++) {
				mCatalogPackages.add(catalogPackages[i] );
			}
		}
	}

    public CatalogPackage[] getCatalogPackages() {
    	if (mCatalogPackages != null && mCatalogPackages.size() > 0) {
    		return mCatalogPackages.toArray((new CatalogPackage[]{}));
    	}
    	else {
    		return null;
    	}
	}

}
