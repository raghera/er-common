package com.vizzavi.ecommerce.business.common;

/**
 * An exception to be thrown by an API when it can't find the requested Subscription
 * @author matt
 *
 */
public class SubscriptionNotFoundException extends EcommerceException {

	/**
	 * 
	 */
	private static final long serialVersionUID = -3863101711681006714L;

	public SubscriptionNotFoundException() {
		super();
		// TODO Auto-generated constructor stub
	}

	public SubscriptionNotFoundException(String msg, Throwable thr) {
		super(msg, thr);
		// TODO Auto-generated constructor stub
	}

	public SubscriptionNotFoundException(String msg) {
		super(msg);
		// TODO Auto-generated constructor stub
	}

	public SubscriptionNotFoundException(Throwable thr) {
		super(thr);
		// TODO Auto-generated constructor stub
	}

}
