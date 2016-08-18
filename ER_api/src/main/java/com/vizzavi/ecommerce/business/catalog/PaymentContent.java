package com.vizzavi.ecommerce.business.catalog;

import static org.apache.commons.lang.StringUtils.isBlank;
/**
* Represents data needed by external payment systems.
*
* @author Periasamy
*/
public class PaymentContent implements java.io.Serializable
{
   private    static final long serialVersionUID = -6588340122653532150L;
    /**
     * Category
     */
    protected String category;
    /**
     * Description
     */
    protected String description;
    /**
     * Merchant
     */
    protected String merchant;
    /**
     * Description of Merchant
     */
    protected String merchantDescription;
    /**
     * Item Volume
     */
    protected String itemVolume;
    /**
     * Type of service
     */
    protected String serviceType;
    /**
     * Value of promotion
     */
    protected String promotion;

    /**
     * default empty constructor
     */
    public PaymentContent()
    {
    }

    public PaymentContent(String category, String description, String merchant, String merchantDescription, String itemVolume, String serviceType, String promotion)	{
    	this.category = category;
        this.description = description;
        this.merchant = merchant;
        this.merchantDescription = merchantDescription;
        this.itemVolume = itemVolume;
        this.serviceType = serviceType;
        this.promotion = promotion;
    }
    /**
     * Constructor with the Payment Content object
     * @param pay Payment content object
     */
    public PaymentContent(PaymentContent pay)
    {
        this.category = pay.getCategory();
        this.description = pay.getDescription();
        this.merchant = pay.getMerchant();
        this.merchantDescription = pay.getMerchantDescription();
        this.itemVolume = pay.getItemVolume();
        this.serviceType = pay.getServiceType();
        this.promotion = pay.getPromotion();
    }

    /**
     * Override method of the toString method.
     * @return Payment content object in string format
     */
    @Override
	public String toString()
    {
        StringBuffer sb = new StringBuffer();

        sb.append( category );
        sb.append( " " );

        sb.append( description );
        sb.append( " " );

        sb.append( merchant );
        sb.append( " " );

        sb.append( merchantDescription );
        sb.append( " " );

        sb.append( itemVolume );
        sb.append( " " );

        sb.append( serviceType );
        sb.append( " " );

        sb.append( promotion );
        sb.append( " " );

        return sb.toString();
    }

    /**
     * Return the category.
     * @return Category
     */
    public String getCategory() {
        return category;
    }

    /**
     * Return the description.
     * @return Description
     */
    public String getDescription() {
        return description;
    }

    /**
     * Return the merchant.
     * @return Merchant
     */
    public String getMerchant() {
        return merchant;
    }

    /**
     * Return the merchantDescription.
     * @return Description of Merchant
     */
    public String getMerchantDescription() {
        return merchantDescription;
    }

    /**
     * Return the itemVolume.
     * @return Item Volume
     */
    public String getItemVolume() {
        return itemVolume;
    }

    /**
     * Return the serviceType.
     * @return type of service
     */
    public String getServiceType() {
        return serviceType;
    }

    /**
     * Return the promotion.
     * @return promotion
     */
    public String getPromotion() {
        return promotion;
    }
    
    /**
     * ie does this object have no data in it?
     * @return false if there is some useful data, true otherwise
     */
    public boolean isEmpty()	{
    	return isBlank(category) && isBlank(description) && isBlank(itemVolume) && isBlank(merchant) 
    			&& isBlank(merchantDescription) && isBlank(promotion) && isBlank(serviceType);
    }

}
