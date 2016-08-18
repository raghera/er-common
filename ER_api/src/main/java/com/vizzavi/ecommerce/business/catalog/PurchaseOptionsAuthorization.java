package com.vizzavi.ecommerce.business.catalog;

import java.io.Serializable;
import java.util.ArrayList;

import com.vizzavi.ecommerce.business.common.ReasonCode;

public class PurchaseOptionsAuthorization implements Serializable {

	private static final long serialVersionUID = 208445763770123059L;
	
	private boolean success;
	
	private ReasonCode reason;
	
	private ArrayList<CatalogPackage> mCatalogPackages = null;
	
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
