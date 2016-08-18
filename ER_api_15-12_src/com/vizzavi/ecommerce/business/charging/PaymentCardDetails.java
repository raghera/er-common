package com.vizzavi.ecommerce.business.charging;

import java.util.Date;

/**
        This is used to send extra details to the payment handler for Credit Card payments.
        The payment type must be less than a 1000 for this to be used.
*/
public class PaymentCardDetails implements java.io.Serializable
{
   private    static final long serialVersionUID = -188878046666982400L;
    String creditCardNo = "";
    String creditCardName = "UNUSED";
    String creditCardType = "UNUSED";
    Date startDate = new Date();
    Date expiryDate = new Date();
    String issueNo = "0";

	@Override
	public String toString() {
		StringBuffer buf = new StringBuffer();
		buf.append("{PaymentCardDetails\n");
		if (creditCardNo!=null) buf.append("creditCardNo=" + creditCardNo + "\n");
		else buf.append("creditCardNo=null\n");
		if (creditCardName!=null) buf.append("creditCardName=" + creditCardName + "\n");
		else buf.append("creditCardName=null\n");
		if (creditCardType!=null) buf.append("creditCardType=" + creditCardType + "\n");
		else buf.append("creditCardType=null\n");
		if (startDate!=null) buf.append("startDate=" + startDate.toGMTString() + "\n");
		else buf.append("startDate=null\n");
		if (expiryDate!=null) buf.append("expiryDate=" + expiryDate.toGMTString()+"\n");
		else buf.append("expiryDate=null");
 	    buf.append("}");
		return buf.toString();
	}



    /**
        The credit card number
    */


    public String getCardNumber()
    {
        return this.creditCardNo;
    }

    public void setCardNumber(String val)
    {
        this.creditCardNo = val;
    }

    /**
        The type of card ie. Visa, Mastercard
    */
    public String getCardType()
    {
        return this.creditCardType;
    }

    public void setCardType(String type)
    {
        this.creditCardType = type;
    }

    /**
        The name of the credit card user
    */
    public String getCardName()
    {
        return this.creditCardName;
    }

    public void setCardName(String val)
    {
        this.creditCardName = val;
    }

    /**
        The credit card start date
    */
    public Date getStartDate()
    {
        return this.startDate;
    }

    public void setStartDate( Date val)
    {
        this.startDate = val;
    }

    /**
        The credit card expiry date
    */
    public Date getExpiryDate()
    {
        return this.expiryDate;
    }

    public void setExpiryDate(Date val)
    {
        this.expiryDate = val;
    }

    /**
        The credit card issue number
    */
    public String getIssueNumber()
    {
        return this.issueNo;
    }

    public void setIssueNumber(String val)
    {
        this.issueNo = val;
    }
    



}
