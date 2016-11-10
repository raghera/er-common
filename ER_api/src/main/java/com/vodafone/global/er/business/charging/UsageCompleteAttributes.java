package com.vodafone.global.er.business.charging;

//import com.vizzavi.ecommerce.business.charging.ServiceUsageInstance;

public class UsageCompleteAttributes  implements java.io.Serializable{
	
	private static final long serialVersionUID = -8933013082918696440L;
	protected int mDeliveryStatus = 0;
  protected Double mEventUnits;
//	protected ServiceUsageInstance mServiceUsageInstance = null;
	protected String sessionId ;

	//CR-0869 start
	/**
	 * Partner ID of the transaction
	 */
	protected String mPartnerId = null;
	
	/**
	 * Aggregator ID of the transaction
	 */
	protected String mAggregatorId = null;
	//CR-0869 end
	
	
	/**
	 * @return Returns the mDeliveryStatus.
	 */
	public int getDeliveryStatus() {
		return mDeliveryStatus;
	}
	/**
	 * @param deliveryStatus The mDeliveryStatus to set.
	 */
	public void setDeliveryStatus(int deliveryStatus) {
		mDeliveryStatus = deliveryStatus;
	}
	/**
	 * @return Returns the mEventUnits.
	 */
	public Double getEventUnits() {
		return mEventUnits;
	}

//	/**
//	 * @return Returns the mServiceUsageInstance.
//	 */
//	public ServiceUsageInstance getServiceUsageInstance() {
//		return mServiceUsageInstance;
//	}

	/**
	 * @param eventUnits The mEventUnits to set.
	 */
	public void setEventUnits(Double eventUnits) {
		mEventUnits = eventUnits;
	}

//	/**
//	 * @param eventUnits The mServiceUsageInstance to set.
//	 */
//	public void setServiceUsageInstance(ServiceUsageInstance serviceUsageInstance) {
//		mServiceUsageInstance = serviceUsageInstance;
//	}
	public String getSessionId() {
		return sessionId;
	}
	public void setSessionId(String sessionId) {
		this.sessionId = sessionId;
	}

	//CR-0869 Start
	/**
	 * Gets transaction partner ID
	 * @return String Partner ID
	 */
	public String getPartnerId() {
		return mPartnerId;

	}      

	/**
	 * Sets partner ID of the transaction
	 * @param partnerId The partner ID
	 */
	public void setPartnerId(String partnerId) {
		mPartnerId = partnerId;
	}

	/**
	 * Gets the aggregator of the transaction
	 * @return String Aggregator
	 */
	public String getAggregatorId() {
		return mAggregatorId;
	}

	/**
	 * Sets aggregator ID of the transaction
	 * @param aggregatorId The aggregator ID of the transaction
	 */
	public void setAggregatorId(String aggregatorId) {
		mAggregatorId = aggregatorId;
	}
	//CR-0869 End

}
