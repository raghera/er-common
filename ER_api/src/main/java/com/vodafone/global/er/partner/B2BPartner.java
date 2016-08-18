package com.vodafone.global.er.partner;

/**
 * CR2255 Phase2 - add partner information
 * B2B Partner Attributes
 */

public class B2BPartner implements java.io.Serializable
{
	private	static final long serialVersionUID = -3001811828523649328L;
	
	/**
	 * The Partner Id
	 */
	private String  id;
	
	/**
	 * The Partner URL
	 */
	private String  url;
	
	/**
	 * The Partner Contact Information
	 */
	private String contactInfo;
	
	/**
	 * The Partner Email Address
	 */
	private String email;
	
	public B2BPartner()
	{
	
	}
	
	public B2BPartner(String partnerId, String partnerUrl, String partnerContactInfo, String partnerEmail) {
		this.id = partnerId;
		this.url = partnerUrl;
		this.contactInfo = partnerContactInfo;
		this.email = partnerEmail;
	}
	
	public String toString()
	{
		StringBuffer buf = new StringBuffer("{");
		
		buf.append("id=").append(id);
		buf.append("\nurl=").append(url);
		buf.append("\ncontactInfo=").append(contactInfo);
		buf.append("\nemail=").append(email);
		
		return buf.toString();
	}
	
	/**
	 * @return id
	 */
	public String getId() {
		return this.id;
	}
	
	
	/**
	 * @param partnerId, the id to set
	 */
	public void setId(String partnerId) {
		this.id = partnerId;
	}

	/**
	 * @return url
	 */
	public String getUrl() {
		return this.url;
	}
	
	
	/**
	 * @param partnerUrl, the url to set
	 */
	public void setUrl(String partnerUrl) {
		this.url = partnerUrl;
	}
	
	/**
	 * @return contactInfo
	 */
	public String getContactInfo() {
		return this.contactInfo;
	}
	
	/**
	 * @param partnerContactInfo, the contactInfo to set
	 */
	public void setContactInfo(String partnerContactInfo) {
		this.contactInfo = partnerContactInfo;
	}
	
	/**
	 * @return email
	 */
	public String getEmail() {
		return this.email;
	}
	
	/**
	 * @param partnerEmail, the email to set
	 */
	public void setEmail(String partnerEmail) {
		this.email = partnerEmail;
	}
	
}
