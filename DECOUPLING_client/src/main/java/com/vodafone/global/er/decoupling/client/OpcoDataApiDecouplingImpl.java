package com.vodafone.global.er.decoupling.client;

import java.util.ArrayList;
import java.util.List;
import java.util.Locale;

import com.vizzavi.ecommerce.business.common.EcommerceException;
import com.vizzavi.ecommerce.business.selfcare.UserGroup;
import com.vodafone.global.er.decoupling.PayloadConstants;
import com.vodafone.global.er.decoupling.binding.request.GetUserGroups;
import com.vodafone.global.er.decoupling.binding.response.GetUserGroupsResponse;
import com.vodafone.global.er.opcodata.OpcoDataApi;

public class OpcoDataApiDecouplingImpl extends BaseErApiDecouplingImpl implements OpcoDataApi{
	
//	private static final Logger logger = LoggerFactory.getLogger(OpcoDataApiDecouplingImpl.class);
	
	public OpcoDataApiDecouplingImpl(Locale locale, String clientId) {
		super(locale, clientId);
	}
	
	public OpcoDataApiDecouplingImpl(Locale locale) {
		this(locale, "ecom wrapper");
	}

	//JIRA ET86 get user groups list from the core DB
	@Override
	public List<UserGroup> getUserGroups(
			String clientId)
			throws EcommerceException {
		
		GetUserGroups request = createRequest(PayloadConstants.GET_USER_GROUPS_REQUEST_PAYLOAD);
		request.setClientId(clientId);
		
		GetUserGroupsResponse response = 
				sendRequestAndGetResponse(PayloadConstants.GET_USER_GROUPS_REQUEST_PAYLOAD, request, GetUserGroupsResponse.class);
		
		List<com.vodafone.global.er.decoupling.binding.response.GetUserGroupsResponse.UserGroup> usrGrpList = response.getUserGroup();
		
		List<UserGroup> responseList = new ArrayList<>();
		for(com.vodafone.global.er.decoupling.binding.response.GetUserGroupsResponse.UserGroup usgGrp: usrGrpList){
			responseList.add(new UserGroup(usgGrp.getName(), usgGrp.getDescription()));
		}
		
		return responseList;
	}

}
