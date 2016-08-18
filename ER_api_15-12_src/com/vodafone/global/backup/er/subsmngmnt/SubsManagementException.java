package com.vodafone.global.er.subsmngmnt;

import com.vizzavi.ecommerce.business.common.EcommerceException;

/**
 *
 * moved from ER_CORE_api to ER_api to reduce dependencies between modules
 */
public class SubsManagementException extends EcommerceException {

   private    static final long serialVersionUID = -1972425304257799765L;
    public SubsManagementException() {
        super();
    }

    public SubsManagementException(String msg) {
        super(msg);
    }

    public SubsManagementException(String msg, Throwable thr) {
        super(msg, thr);
    }

	public SubsManagementException(Throwable thr) {
		super(thr);
	}


    /*public SubsManagementException(ErCoreErrorId errorId) {
        super();
        this.setErrorId(errorId.getCode());
        this.setErrorDescription(errorId.getResourceName());
    }*/


}
