/*
 * Created on 25-Apr-2005
 *
 *
 * TODO To change the template for this generated file go to
 * Window - Preferences - Java - Code Style - Code Templates
 */
package com.vizzavi.ecommerce.business.catalog.internal;

import com.vizzavi.ecommerce.business.catalog.CatalogPackage;
import com.vizzavi.ecommerce.business.catalog.PricePoint;
import com.vizzavi.ecommerce.business.catalog.SuperCreditPricePoint;
/**
 *
 *
 *
 */
public class SuperCreditPricePointImpl extends SuperCreditPricePoint {

 private static final long serialVersionUID = 337978816972154814L;
		
 public SuperCreditPricePointImpl() {
        super();
    }

 public SuperCreditPricePointImpl(PricePoint pricepoint, BalanceImpact balImpact) {
  this.mId = pricepoint.getId() + CatalogPackage.SUPERCREDIT_DELIMITER + balImpact.getResource().getCode();
  this.mResource = balImpact.getResource();
  this.mResource.setSuperCredit(true);
  this.mRateWithoutTax = balImpact.getRate();
  this.setSuperCreditTextList(balImpact.getSuperCreditTextList());
  this.mActive = true;
 }

 public void setActive(boolean active) {
  this.mActive = active;
 }

 public void setRateIdentifier(String rateIdentifier) {
  this.mRateIdentifier = rateIdentifier;
 }
 public void setValidSuperCreditOption(boolean active) {
  super.mValidSuperCredit = active;
 }

}