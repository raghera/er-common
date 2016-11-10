package com.vizzavi.ecommerce.business.catalog.internal;

import java.util.Locale;

import com.vizzavi.ecommerce.business.common.PromotionData;

/**
 * @author Ravi Aghera
 *
 */
public final class PromotionDataImpl implements PromotionData {

	private String catalogServiceId;
	private String clientApplicationId;
	private String msisdn;
	//private String requestType; //TODO Can be either checkPromotionSummary or not - maybe not required
	private Locale promotionLocale;
	private String[] packIds;

	public PromotionDataImpl() {
	}

	public PromotionDataImpl(final String catalogServiceId, final String clientApplicationId, final String msisdn,
			final String requestType, final Locale promotionLocale){

		this.catalogServiceId = catalogServiceId;
		this.clientApplicationId = clientApplicationId;
		this.msisdn = msisdn;
		//this.requestType = requestType;
		this.promotionLocale = promotionLocale;

	}

	/* (non-Javadoc)
	 * @see com.vodafone.global.er.transctrl.common.PromotionData#getCatalogServiceId()
	 */
	@Override
	public final String getCatalogServiceId() {
		return catalogServiceId;
	}

	/* (non-Javadoc)
	 * @see com.vodafone.global.er.transctrl.common.PromotionData#getClientApplicationId()
	 */
	@Override
	public final String getClientApplicationId() {
		return clientApplicationId;
	}

	/* (non-Javadoc)
	 * @see com.vodafone.global.er.transctrl.common.PromotionData#setCatalogServiceId(java.lang.String)
	 */
	@Override
	public final void setCatalogServiceId(final String catalogServiceId) {
		this.catalogServiceId = catalogServiceId;
	}

	/* (non-Javadoc)
	 * @see com.vodafone.global.er.transctrl.common.PromotionData#setClientApplicationId(java.lang.String)
	 */
	@Override
	public final void setClientApplicationId(final String clientApplicationId) {
		this.clientApplicationId = clientApplicationId;
	}

	@Override
	public final String getMsisdn() {
		return this.msisdn;
	}

	@Override
	public final void setMsisdn(final String msisdn) {
		this.msisdn = msisdn;
	}

	@Override
	public final Locale getPromotionLocale() {
		return this.promotionLocale;
	}

	@Override
	public final void setPromotionLocale(final Locale promotionLocale) {
		this.promotionLocale = promotionLocale;
	}

	@Override
	public String[] getPackIds() {
		return packIds;
	}

	@Override
	public void setPackIds(String[] packIds) {
		this.packIds = packIds;
	}

	//TODO Override toString and hashCode methods for use in collections

}
