package com.vizzavi.ecommerce.business.selfcare;

import java.text.SimpleDateFormat;

import javax.persistence.PostLoad;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.vizzavi.ecommerce.business.catalog.CatalogApi;
import com.vizzavi.ecommerce.business.catalog.CatalogPackage;
import com.vizzavi.ecommerce.business.catalog.PricePoint;
import com.vizzavi.ecommerce.business.common.Constants;
import com.vizzavi.ecommerce.business.common.EcomApiFactory;
import com.vizzavi.ecommerce.business.common.EcommerceException;
import com.vizzavi.ecommerce.business.common.RatingAttributes;
import com.vodafone.config.ConfigProvider;

/**
 * https://docs.jboss.org/ejb3/docs/tutorial/1.0.7/html/Callbacks_and_Callback_Handlers.html
 * Refer chapter 6 in the above link:Introduction to Callbacks and Callback Handlers in EJB3
 * 
 * @PostLoad method is used in case of Entity Beans
 *
 */
public class SubscriptionEntityListener {
	private static final Logger logger = LoggerFactory
			.getLogger(SubscriptionEntityListener.class);


	
	@PostLoad
	public void subsPostConstruct(Subscription sub) {

		// create a catalog using locale
		CatalogApi catalog = null;
		try {
			catalog = EcomApiFactory.getCatalogApi(sub.getCountry().getLocale());
		} catch (EcommerceException e) {
			e.printStackTrace();
			return;
		}

		// set the catalogpackage in the sub
		CatalogPackage cpack = catalog.getPackage(sub.getPackageId());
		sub.setPackage(cpack);

		// logic for setting the rating attributes in the Sub
		Options opts = sub.getOptions();

		RatingAttributes ratingAttrs = new RatingAttributes();

		String userGroup;
		String promoCode;
		String tariff;
		
		if(opts!=null){
			Integer iChannel = opts.getChannel();
			if (iChannel != null && iChannel > 0) {
				ratingAttrs.setChannel(iChannel);
			}
	
			Integer iPaymentType = opts.getPaymentType();
			if (iPaymentType != null) {
				ratingAttrs.setPaymentType(iPaymentType);
				sub.mPaymentType = opts.getPaymentType();
				sub.mContentPaymentType = opts.getPaymentType();
			}
	
			userGroup = opts.getUserGroup();
			if (userGroup != null) {
				ratingAttrs.setUserGroup(userGroup);// setUserGroups(new String[]
													// {userGroup});
			}
	
			promoCode = opts.getPromoCode();
			if (promoCode != null) {
				ratingAttrs.setPromoCodes(new String[] { promoCode });
			}
	
			tariff = opts.getTariff();
			if (tariff == null || tariff.trim().equals("")) {
				ratingAttrs.setTariff(Constants.STRING_MATCH_ALL);
			} else {
				ratingAttrs.setTariff(tariff);
			}
	
			ratingAttrs.setDuration(opts.getDuration());
	
			Integer iChargingMethod = opts.getChargingMethod();
			if (iChargingMethod != null && iChargingMethod >= 0) {
				ratingAttrs.setChargingMethod(iChargingMethod);
			}
	
			Integer iAccessDevice = opts.getAccessDevice();
			if (iAccessDevice != null && iAccessDevice >= 0) {
				ratingAttrs.setDeviceType(iAccessDevice);
				sub.mDeviceType = opts.getAccessDevice();
			}
	
		}
		sub.setRatingAttributes(ratingAttrs);
		// set the pricepoint in the sub
		if ((sub.getPackage() != null && !sub.getPackage().isDefault())
				|| sub.getPricePoint() == null) {
			setPricePoint(sub);
		}
		sub.setMsisdn(sub.getAccount().getMsisdn());

	}

	private void setPricePoint(Subscription sub) {
		logger.debug("Enter in method setPricePoint");
		boolean matchFound = false;
		// REMEDY 5685
		int noOfMatches = 0;
		PricePoint tmpNonHistoricPricePoint = null;
		PricePoint tmpHistoricPricePoint = null;
		// MQC 8393 - reopened, add pricepoint when matched for multiple
		// historic pricepoints with date ranges
		PricePoint tmpMultipleHistoricPricePoint = null;

		// Remedy 6647 part 2, Bruno Meseguer, method ERsubscription.isRecurring
		// returning misleading result
		boolean historicExistsInPricePlan = false;
		boolean isUserGroup = false;
		PricePoint userGroupPricePoint = null;

		// Remedy 6647 part 2, Bruno Meseguer, method ERsubscription.isRecurring
		// returning misleading result
		if (sub.getPackage() != null && sub.getRatingAttributes() != null
				&& sub.getRatingAttributes().getUserGroup() != null
				&& !sub.getRatingAttributes().getUserGroup().equals("") &&
				// MQC 6051 - user group should also not match wildcard
				!sub.getRatingAttributes().getUserGroup().equals("*"))
			isUserGroup = true;

		if ((sub.getPackage() != null)
				&& (sub.getPackage().getPricePoints() != null)) {

			// PricePoint[] currentPricePoints =
			// sub.getPackage().getPricePoints().getAll();

			// if (currentPricePoints!=null) {
			for (PricePoint currentPricePoint : sub.getPackage()
					.getPricePoints()) {
				if (matchSubPricePoint(sub, currentPricePoint)) {
					sub.setPricePoint(currentPricePoint);
					matchFound = true;
					// MQC 6051 - if historic pricepoint set the historic in
					// priceplan flag
					if (currentPricePoint.isHistoric()) {
						historicExistsInPricePlan = true;
					}
					// Remedy 6647 part 2, Bruno Meseguer, method
					// ERsubscription.isRecurring returning misleading result
					if (isUserGroup
							&& sub.getRatingAttributes().getUserGroup()
									.equals(currentPricePoint.getUserGroup()))
						userGroupPricePoint = currentPricePoint;

					SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMdd");
					int startDate = 0;
					int endDate = 0;
					int subStartDate = 0;

					// parse date to same format for comparison
					if (currentPricePoint.getStartDate() != null) {
						startDate = Integer.parseInt(sdf
								.format(currentPricePoint.getStartDate()));
						if (logger.isDebugEnabled())
							logger.debug("startdate: " + startDate);
					}
					if (currentPricePoint.getExpiryDate() != null) {
						endDate = Integer.parseInt(sdf.format(currentPricePoint
								.getExpiryDate()));
						if (logger.isDebugEnabled())
							logger.debug("enddate: " + endDate);
					}
					if (sub.getStartDate() != null) {
						subStartDate = Integer.parseInt(sdf.format(sub
								.getStartDate()));
						if (logger.isDebugEnabled())
							logger.debug("subscriptionStartDate: "
									+ subStartDate);
					}

					if (currentPricePoint.isHistoric()
							&& (subStartDate >= startDate)
							&& (subStartDate <= endDate)) {
						tmpHistoricPricePoint = currentPricePoint;
						// MQC 8393 reopened - set the multiple historic
						// pricepoint
						tmpMultipleHistoricPricePoint = currentPricePoint;
					} else if (currentPricePoint.isHistoric()
							&& (startDate == 0 || endDate == 0)) {
						tmpHistoricPricePoint = currentPricePoint;
						// MQC 8393 - reopened, set the non-historic pricepoint
						// if pricepoint is non historic
					} else if (!currentPricePoint.isHistoric()
							&& sub.hasHasHistoricPricepoint()) {
						tmpNonHistoricPricePoint = currentPricePoint;
					}
					// else {
					// tmpNonHistoricPricePoint = currentPricePoint;
					// }
					noOfMatches++;
				}
			} // end loop through pricepoints
		} else {
			if (logger.isDebugEnabled())
				logger.info("currentPricePoints NULL - no pricepoints found for subid: "
						+ sub.getSubscriptionId());
		}


		// REMEDY 5685
		if (matchFound == true && noOfMatches == 1) {
			logger.debug("setPricePoint: MATCH FOUND for subid: {}",
					sub.getSubscriptionId());
		}

		// Remedy 6647 part 2, Bruno Meseguer, method ERsubscription.isRecurring
		// returning misleading result
		else if (matchFound == true && noOfMatches > 1) {
			if (!sub.hasHasHistoricPricepoint() && isUserGroup
					&& userGroupPricePoint != null) {
				// from the multiple PPs, the userGroup mathed is the one to set
				sub.setPricePoint(userGroupPricePoint);
			}
			// This scenatio needs to take in account the historic PricePoints,
			// along with UserGroup's PricePoints
			else if (sub.hasHasHistoricPricepoint() && isUserGroup
					&& userGroupPricePoint != null) {
				logger.warn("setPricePoint: (Remedy 6647) scenario implementation not completed, unexpected results, please contact ER team");

				// our guess is that the pricepoint to set is the UserGroup
				// matched
				sub.setPricePoint(userGroupPricePoint);
			} else {
				// MQC 8393 reopened - set the multiple historic pricepoint if
				// set
				if (tmpMultipleHistoricPricePoint != null) {
					logger.debug("setPricePoint: MATCH FOUND setting multiple historic one");
					sub.setPricePoint(tmpMultipleHistoricPricePoint);
				} else if (historicExistsInPricePlan
						&& sub.hasHasHistoricPricepoint()) {
					logger.debug("setPricePoint: MATCH FOUND setting non historic one");

					// MQC8312 - rollback
					sub.setPricePoint(tmpNonHistoricPricePoint);
					// sub.getPackage().setPricePoint(tmpHistoricPricePoint);
				} else {
					logger.debug("setPricePoint: MATCH FOUND setting historic one");

					// MQC8312 - rollback
					sub.setPricePoint(tmpHistoricPricePoint);
					// sub.getPackage().setPricePoint(tmpNonHistoricPricePoint);

				}
			}
		}

		else {
			logger.info("setPricePoint: MATCH NOT FOUND");
		}
		logger.debug("Exit method setPricePoint");
	}

	private boolean matchSubPricePoint(Subscription sub, PricePoint pt) {
		if (logger.isDebugEnabled())
			logger.debug("Enter in method matchSubPricePoint for subid "
					+ sub.getSubscriptionId() + " and pricepoint " + pt.getId());

		// MQC 6016 - move retrieval of property to local method of where it is
		// used
		boolean perform_bearer_check = ConfigProvider.getPropertyAsBoolean(
				"RATING_BEARER_CHECK", true);
		if ((sub.getRatingAttributes() != null)
				&& (sub.getRatingAttributes().getDuration() == pt.getDuration())
				&& (sub.getRatingAttributes().getChargingMethod() == pt
						.getChargingMethod())
				&& (sub.getRatingAttributes().getPromoCode().equals(pt
						.getPromoCode()))
				&&
				// CR-0978 Location Services
				(sub.getRatingAttributes().getTariff().equals(pt.getTariff()))
				&&
				// Remedy 6647 part 2, Bruno Meseguer, method
				// ERsubscription.isRecurring returning misleading result
				(sub.getRatingAttributes().getUserGroup()
						.equals(pt.getUserGroup()) || (sub
						.getRatingAttributes().getUserGroup() != null
						&& !sub.getRatingAttributes().getUserGroup().equals("") && pt
						.getUserGroup().equals("*")))
				// start MQC5385 -goranssm
				// MQC 5604 - take into account bearer check flag in
				// er2.properties
				&& (perform_bearer_check ? sub.getRatingAttributes()
						.getBearer().equals(pt.getBearer()) : true)
				// end MQC5385
				// MQC 6388 - also check for express flag match
				&& (sub.getRatingAttributes().getExpressFlag() == pt
						.getExpressFlag())
				// MQC 7193 - also check the channel attribute
				&& (sub.getRatingAttributes().getChannel() == pt.getChannel() || ((Constants
						.isNotSet(sub.getRatingAttributes().getChannel())
						|| Constants.isMatchAll(sub.getRatingAttributes()
								.getChannel()) || sub.getRatingAttributes()
						.getChannel() == 0) && Constants.isMatchAll(pt
						.getChannel())))

		) {
			logger.debug("DID match sub {} for pricepoint {}",
					sub.getSubscriptionId(), pt.getId());
			logger.debug("pricepoint=\n" + pt.toString());
			return true;
		} else {
			logger.debug("NOT matched sub {} for pricepoint {}",
					sub.getSubscriptionId(), pt.getId());
			return false;
		}
	}
}
