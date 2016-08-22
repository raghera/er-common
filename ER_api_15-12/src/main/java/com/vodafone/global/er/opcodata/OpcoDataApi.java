package com.vodafone.global.er.opcodata;

import java.util.Collection;
import java.util.List;

import com.vizzavi.ecommerce.business.common.EcommerceException;
import com.vizzavi.ecommerce.business.selfcare.UserGroup;

/**
 * 
 * @author vivek
 * 
 * Introduced as part of Jira ET-86 for the user group API call.<p/>
 * 
 * This Api interface has been created for calls like getUserGroups, which don't involve any user / account.
 * They can be called from the decoupling process layer or delegate layer directly, since they don't involve an EJB.
 *
 */

public interface OpcoDataApi extends java.io.Serializable {

	public Collection<UserGroup> getUserGroups(String clientId) throws EcommerceException;
}
