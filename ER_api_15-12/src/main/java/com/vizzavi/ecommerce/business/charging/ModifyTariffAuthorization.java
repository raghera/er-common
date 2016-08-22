package com.vizzavi.ecommerce.business.charging;

import java.io.Serializable;
import java.util.ArrayList;

import com.vizzavi.ecommerce.business.common.ReasonCode;

/**
 * CR-0978 Location Services
 * Modify Tariff Authorization returned when calling modify tariff API.
*/

public class ModifyTariffAuthorization implements Serializable {
	
	private static final long serialVersionUID = -3776580600009455506L;
	
	private boolean success;
	
	private ReasonCode reason;
	
	private ArrayList<PurchaseTariffAuthorization> purchaseTariffAuths = null;
	
	private ArrayList<InactivateTariffAuthorization> inactivateTariffAuths = null;
	
	
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
	
	public void addPurchaseTariffAuthorization(PurchaseTariffAuthorization purchaseTariffAuth) {
		if (purchaseTariffAuths == null) {
			purchaseTariffAuths = new ArrayList<PurchaseTariffAuthorization>();
		}
		
		purchaseTariffAuths.add(purchaseTariffAuth);
	}
	
	public PurchaseTariffAuthorization[] getPurchaseTariffAuthorizations() {
		if (purchaseTariffAuths != null && purchaseTariffAuths.size() > 0) {
			return purchaseTariffAuths.toArray((new PurchaseTariffAuthorization[]{}));
		}
		else {
			return null;
		}
	}
	
	public void addInactivateTariffAuthorization(InactivateTariffAuthorization inactivateTariffAuth) {
		if (inactivateTariffAuths == null) {
			inactivateTariffAuths = new ArrayList<InactivateTariffAuthorization>();
		}
		
		inactivateTariffAuths.add(inactivateTariffAuth);
	}
	
	public InactivateTariffAuthorization[] getInactivateTariffAuthorizations() {
		if (inactivateTariffAuths != null && inactivateTariffAuths.size() > 0) {
			return inactivateTariffAuths.toArray((new InactivateTariffAuthorization[]{}));
		}
		else {
			return null;
		}
	}
}
