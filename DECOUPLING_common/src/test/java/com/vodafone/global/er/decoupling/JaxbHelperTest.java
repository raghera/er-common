package com.vodafone.global.er.decoupling;

import static org.junit.Assert.*;

import java.io.StringWriter;
import java.io.UnsupportedEncodingException;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBElement;
import javax.xml.bind.JAXBException;

import org.junit.Test;

import com.vodafone.global.er.decoupling.binding.response.ErResponse;
import com.vodafone.global.er.decoupling.binding.response.PayloadType;
import com.vodafone.global.er.decoupling.binding.response.v2.ChargingId;
import com.vodafone.global.er.decoupling.binding.response.v2.FullAccountV2;
import com.vodafone.global.er.decoupling.binding.response.v2.ObjectFactory;
import com.vodafone.global.er.decoupling.util.xml.JAXBResponseHelper;


public class JaxbHelperTest {

	
	@Test
	public void testFullAccount() throws JAXBException	{
		FullAccountV2 acc = new FullAccountV2();//ObjectFactory().createFullAccountV2(null);
//		FullAccountV2Type acc = new ObjectFactory().createFullAccountV2Type();
//		JAXBElement<FullAccountV2Type> obj = new ObjectFactory().createFullAccountV2(acc);
//		FullAccountsResponse respo = new ObjectFactory().createFullAccountsResponse();
//		respo.getFullAccountV2().add(acc);
		ErResponse resp = new ErResponse();
		resp.setPayload(new PayloadType());
		resp.getPayload().setAny(acc);
		String xml = toString(resp);
		assertTrue(xml.contains("<full-account-v2>"));
	}
	
	String toString(ErResponse r) throws JAXBException	{
//		return JAXBResponseHelper.getInstance().toString(r);
		JAXBContext jc = 
				JAXBContext.newInstance("com.vodafone.global.er.decoupling.binding.response:com.vodafone.global.er.decoupling.binding.response.v2");
//				JAXBContext.newInstance(FullAccountV2.class, ErResponse.class, PayloadType.class);
		StringWriter sw = new StringWriter();
		jc.createMarshaller().marshal(r, sw);
		return sw.toString();
	}
	
	@Test
	public void parseResponse() throws UnsupportedEncodingException, JAXBException	{
		String xml = "<?xml version='1.0' encoding='UTF-8'?><er-response id=\"100058\" version=\"2\">  <payload>   "
				+ " <full-account-v2>      <ban>BAN_P146058461158163004</ban>      "
				+ "<charging-id type=\"msisdn\">P146058461158163</charging-id>      "
				+ "<status>401</status>      <billing-cycle-date>13</billing-cycle-date>      "
				+ "<country>GB</country>      <last-validate-date>2016-04-13T22:56:51+01:00</last-validate-date>    "
				+ "  <suppress-courtesy-notifications>false</suppress-courtesy-notifications>    "
				+ "</full-account-v2>  </payload></er-response>";
		ErResponse response = JAXBResponseHelper.getInstance().bind(xml);
		Object account = response.getPayload().getAny();
		assertFalse(account.toString(), account instanceof JAXBElement);
	}

	@Test
	public void testJavaToXml() throws JAXBException	{
		FullAccountV2 account = new FullAccountV2();
		account.setBan("some value ban");
		ChargingId ci = new ChargingId();
		ci.setType("pstn");
		ci.setValue("1340234234");
		account.setChargingId(ci);
		account.setBillingCycleDate(27);
		ErResponse response = new ErResponse();
		response.setPayload(new PayloadType());
		response.getPayload().setAny(account);
		//now marshall to xml
//		JAXBResponseHelper helper =JAXBResponseHelper.getInstance();
		String xml = JAXBResponseHelper.getInstance().toString(response);
		assertTrue(xml.contains("pstn"));
		assertTrue(xml.contains("1340234234"));

	}
	
	
}
