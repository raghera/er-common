package com.vizzavi.ecommerce.business.charging;


public class UsageSubscriptionAttributes implements java.io.Serializable {

	private    static final long serialVersionUID = 7719503860949755198L;
	
	private String msisdn;
	
	private String serviceId;
	
	private String packageIds;
	
	private boolean optionsRequired;
	
	private boolean packageBalancesRequired;
	
	private boolean microServiceType;
	
	//MQC 6067 - indicate whether we need a check for the service id in the ER_PURCHASED_SERVICES table
	private boolean purchasedServiceRequired;
	
	//MQC 6085: Duplicate charging using inactive / closed subscriptions
	private boolean lastClosedSubRequired;
	
	//MQC 6085: Duplicate charging using inactive / closed subscriptions
	private String assetId;
	
	//MQC 6085: Duplicate charging using inactive / closed subscriptions
	private long duplicateUsgMaxSecs = 0;
	
	public String getMsisdn() {
		return this.msisdn;
    }
	
	public void setMsisdn(String userMsisdn) {
		this.msisdn = userMsisdn;
    }
	
	public String getServiceId() {
		return this.serviceId;
	}
		
	public void setServiceId(String userServiceId) {
		this.serviceId = userServiceId;
    }
	
	public String getPackageIds() {
		return this.packageIds;
	}
		
	public void setPackageIds(String servicePackageIds) {
		this.packageIds = servicePackageIds;
    }
	
	public boolean isOptionsRequired() {
		return this.optionsRequired;
	}
			
	public void setOptionsRequired(boolean options) {
		this.optionsRequired = options;
    }
	
	public boolean isPackageBalancesRequired() {
		return this.packageBalancesRequired;
	}
			
	public void setPackageBalancesRequired(boolean packageBalances) {
		this.packageBalancesRequired = packageBalances;
    }
	
	public boolean isMicroServiceType() {
		return this.microServiceType;
	}
			
	public void setMicroServiceType(boolean microService) {
		this.microServiceType = microService;
    }
	
	//MQC 6067 - indicate whether we need a check for the service id in the ER_PURCHASED_SERVICES table
	public boolean isPurchasedServiceRequired() {
		return this.purchasedServiceRequired;
	}
	
	//MQC 6067 - indicate whether we need a check for the service id in the ER_PURCHASED_SERVICES table
	public void setPurchasedServiceRequired(boolean purchasedService) {
		this.purchasedServiceRequired = purchasedService;
    }
	
	//MQC 6085: Duplicate charging using inactive / closed subscriptions
	public boolean isLastClosedSubRequired() {
		return this.lastClosedSubRequired;
	}
	
	//MQC 6085: Duplicate charging using inactive / closed subscriptions
	public void setLastClosedSubRequired(boolean lastClosedSub) {
		this.lastClosedSubRequired = lastClosedSub;
    }
	
	//MQC 6085: Duplicate charging using inactive / closed subscriptions
	public String getAssetId() {
		return this.assetId;
    }
	
	//MQC 6085: Duplicate charging using inactive / closed subscriptions
	public void setAssetId(String asset) {
		this.assetId = asset;
    }
	
	//MQC 6085: Duplicate charging using inactive / closed subscriptions
	public long getDuplicateUsgMaxSecs() {
		return this.duplicateUsgMaxSecs;
    }
	
	//MQC 6085: Duplicate charging using inactive / closed subscriptions
	public void setDuplicateUsgMaxSecs(long maxSecs) {
		this.duplicateUsgMaxSecs = maxSecs;
    }
}
