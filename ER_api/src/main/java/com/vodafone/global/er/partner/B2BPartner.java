package com.vodafone.global.er.partner;

import java.io.Serializable;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Embedded;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.Id;
import javax.persistence.IdClass;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import com.vizzavi.ecommerce.business.catalog.B2BPartnerPrimaryKey;
import com.vizzavi.ecommerce.business.catalog.PartnerTradingLimit;
import com.vizzavi.ecommerce.business.selfcare.Subscription;

/**
 * CR2255 Phase2 - add partner information
 * B2B Partner Attributes
 */
@Entity
@IdClass(B2BPartnerPrimaryKey.class)
@Table(name="ER_PARTNER_TRANSACTION_LIMIT")
public class B2BPartner implements Serializable	{
	private	static final long serialVersionUID = -3001811828523649328L;
	
	
	protected List<Subscription> subscriptions;
	
	/**
	 * The Partner Id
	 */
	private String  id;
	/**
	 * The Country Id
	 */
	private int  countryId;
	
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
	
	private PartnerTradingLimit partnerTradingLimit;
	
	//ET 68 : Addition of partner-name field in ER starts here
	/**
	 * The Partner Name
	 */
	private String partnerName;
	
	private Integer partnerActive;
	
	public B2BPartner()
	{
	
	}
	
	public B2BPartner(String partnerId, String partnerUrl, String partnerContactInfo, String partnerEmail) {
		this(partnerId, partnerUrl, partnerContactInfo, partnerEmail, null);
	}
	
	public B2BPartner(String partnerId, String partnerUrl, String partnerContactInfo, String partnerEmail, String partnerName) {
		this.id = partnerId;
		this.url = partnerUrl;
		this.contactInfo = partnerContactInfo;
		this.email = partnerEmail;
		this.partnerName = partnerName;
	}
	
	//ET 68 : Addition of partner-name field in ER ends here
	
	public String toString()
	{
		StringBuffer buf = new StringBuffer("{");
		
		buf.append("id=").append(id);
		buf.append("\nurl=").append(url);
		buf.append("\ncontactInfo=").append(contactInfo);
		buf.append("\nemail=").append(email);
		buf.append("\npartnerName=").append(partnerName);
		
		return buf.toString();
	}
	
	/**
	 * @return id
	 */
	@Id
	@Column(name="PARTNER_ID")
	public String getPartnerId() {
		return this.id;
	}
	
	
	/**
	 * @param partnerId, the id to set
	 * 
	 */
	public void setPartnerId(String partnerId) {
		this.id = partnerId;
	}

	/**
	 * @return url
	 */
	@Column(name = "PARTNER_URL")
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
	@Column(name = "PARTNER_CONTACT_INFO")
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
	@Column(name = "PARTNER_EMAIL")
	public String getEmail() {
		return this.email;
	}
	
	/**
	 * @param partnerEmail, the email to set
	 */
	public void setEmail(String partnerEmail) {
		this.email = partnerEmail;
	}

	//ET 68 : Addition of partner-name field in ER starts here
	/**
	 * @return name
	 */
	
	@Column(name = "PARTNER_NAME")
	public String getPartnerName() {
		return partnerName;
	}

	/**
	 * @param partner name, the name to set
	 */
	public void setPartnerName(String partnerName) {
		this.partnerName = partnerName;
	}
	@Id
	@Column(name="COUNTRY_OBJ_ID")
	public int getCountryId() {
		return countryId;
	}

	public void setCountryId(int countryId) {
		this.countryId = countryId;
	}
	//ET 68 : Addition of partner-name field in ER ends here
	@OneToMany( mappedBy="b2BPartner",	fetch=FetchType.LAZY)
	public List<Subscription> getSubscriptions() {
		return subscriptions;
	}

	public void setSubscriptions(List<Subscription> subscriptions) {
		this.subscriptions = subscriptions;
	}
	
	
	@Embedded
	public PartnerTradingLimit getPartnerTradingLimit() {
		return partnerTradingLimit;
	}

	public void setPartnerTradingLimit(PartnerTradingLimit partnerTradingLimit) {
		this.partnerTradingLimit = partnerTradingLimit;
	}

	@Column(name="PARTNER_ACTIVE")
	public Integer getPartnerActive() {
		return partnerActive;
	}

	public void setPartnerActive(Integer partnerActive) {
		this.partnerActive = partnerActive;
	}
	
	
	
}
