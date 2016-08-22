package com.vizzavi.ecommerce.business.common;

/**
 * An exception to be thrown by an API when it can't find the requested Transaction
 * @author matt
 *
 */
public class TransactionNotFoundException extends EcommerceException {


	private static final long serialVersionUID = -3459072256949930782L;

	public TransactionNotFoundException() {
		// TODO Auto-generated constructor stub
	}

	public TransactionNotFoundException(String msg) {
		super(msg);
		// TODO Auto-generated constructor stub
	}

	public TransactionNotFoundException(String msg, Throwable thr) {
		super(msg, thr);
		// TODO Auto-generated constructor stub
	}

	public TransactionNotFoundException(Throwable thr) {
		super(thr);
		// TODO Auto-generated constructor stub
	}

}
