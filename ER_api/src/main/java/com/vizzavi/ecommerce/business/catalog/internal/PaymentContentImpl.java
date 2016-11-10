package com.vizzavi.ecommerce.business.catalog.internal;

import com.vizzavi.ecommerce.business.catalog.PaymentContent;

/**
* Represents data needed by external payment systems.
*
* @author Periasamy
*/
public class PaymentContentImpl extends PaymentContent implements java.io.Serializable
{
   private    static final long serialVersionUID = -7773539209477323582L;

    public PaymentContentImpl() {
        category = "";
        description = "";
        merchant = "";
        merchantDescription = "";
        itemVolume = "";
        serviceType = "";
        promotion = "";
    }

    public PaymentContentImpl(
        String pcategory,
        String pdescription,
        String pmerchant,
        String pmerchantDescription,
        String pitemVolume,
        String pserviceType,
        String ppromotion )
    {
        category = pcategory;
        description = pdescription;
        merchant = pmerchant;
        merchantDescription = pmerchantDescription;
        itemVolume = pitemVolume;
        serviceType = pserviceType;
        promotion = ppromotion;
    }

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
    * Set the category.
    * @param category
    */
    public void setCategory( String category ) {
        this.category = category;
    }

    /**
    * Set the description.
    * @param description
    */
    public void setDescription( String description ) {
        this.description = description;
    }

    /**
    * Set the merchant.
    * @param merchant
    */
    public void setMerchant( String merchant ) {
        this.merchant = merchant;
    }

    /**
    * Set the merchantDescription.
    * @param merchantDescription
    */
    public void setMerchantDescription( String merchantDescription ) {
        this.merchantDescription = merchantDescription;
    }

    /**
    * Set the itemVolume.
    * @param itemVolume
    */
    public void setItemVolume( String itemVolume ) {
        this.itemVolume = itemVolume;
    }

    /**
    * Set the serviceType.
    * @param serviceType
    */
    public void setServiceType( String serviceType ) {
        this.serviceType = serviceType;
    }

    /**
    * Set the promotion.
    * @param promotion
    */
    public void setPromotion( String promotion ) {
        this.promotion = promotion;
    }

}
