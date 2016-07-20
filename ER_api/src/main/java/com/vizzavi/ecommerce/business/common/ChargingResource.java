package com.vizzavi.ecommerce.business.common;

import java.io.InputStream;
import java.io.Serializable;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.persistence.*;
import javax.xml.bind.JAXBContext;
import javax.xml.bind.Unmarshaller;

import com.vizzavi.ecommerce.business.common.generated.currency.Currencies;
import com.vizzavi.ecommerce.business.common.generated.currency.Currency;
import com.vizzavi.ecommerce.business.selfcare.ResourceBalance;

/**
* Represents the resources that customers use to pay for goods.
* This might be either "tokens" or "euros".
* Convenience class for resources used in ER2.
*/
@Entity
@Table(name="er_resources")
public final class ChargingResource implements Serializable	{
   
	private static final long serialVersionUID = 8025626721680256094L;

    /*
     * STATIC VARIABLES.
     */
    public static ChargingResource GBP = null;
    public static ChargingResource EUR = null;       
    
    
    public final static ChargingResource PAY_TOKEN =
        new ChargingResource( 1100011, "PAY_TOKEN" );

    public final static ChargingResource USAGE_TOKEN =
        new ChargingResource( 1100012, "USAGE_TOKEN" );


    
    /*
     * INSTANCE VARIABLES.
     */

    /**the DB id (resource_obj_id)*/
	//@GeneratedValue(strategy=GenerationType.SEQUENCE, generator="resseq")	
	//probably not necessary since we aren't creating any in code
	//@SequenceGenerator(name="resseq", sequenceName="er_resources_seq", allocationSize=1)
	@Id
	@Column(name="resource_obj_id", insertable=false, updatable=false)
    int id;
    
	@Column(name="country_obj_id", insertable=false, updatable=false)
    int countryId;
    
	public int getCountryId() {
		return countryId;
	}

	public void setCountryId(int countryId) {
		this.countryId = countryId;
	}

	@Column(name="resource_code", insertable=false, updatable=false)
    int code;

	@Transient
	String name;    

	@Column(name="description", insertable=false, updatable=false)
    String description;
	
	@OneToMany( mappedBy="resource",	fetch=FetchType.LAZY, cascade=CascadeType.ALL)
	protected List<ResourceBalance> resourceBalanceList;
	

	ChargingResource(){}
	
    private static Map<Integer, ChargingResource> sResources = new HashMap<>();
    private static Map<String, ChargingResource>  customResources = new HashMap<>();
    
    static {
    	//CQ14114 - @lle - refactoring
    	try {
    		InputStream currencyCodeFile = CountryCode.class.getClassLoader().getResourceAsStream("ecom/currencycodes.xml");
    		
    		//ET-38 refactor countrycodes and currencies to use jaxb
    		// changes from castor to JAXB implementation
 
    		JAXBContext jaxbContext = JAXBContext.newInstance(Currencies.class);
    		Unmarshaller unmarshaller = jaxbContext.createUnmarshaller();
    		Currencies currencies = (Currencies) unmarshaller.unmarshal(currencyCodeFile);
    		for (Currency aCurrency : currencies.getCurrency()){
    			ChargingResource cr = new ChargingResource(aCurrency);
    			sResources.put(new Integer(aCurrency.getCode()), cr);
    			customResources.put(aCurrency.getName(), cr);
    		}
    	} catch (Exception e) {
    		e.printStackTrace();    		
    	}
        
        sResources.put(new Integer(ChargingResource.PAY_TOKEN.getCode()), ChargingResource.PAY_TOKEN);
        sResources.put(new Integer(ChargingResource.USAGE_TOKEN.getCode()), ChargingResource.USAGE_TOKEN);
        GBP = sResources.get(826);
		EUR = sResources.get(978);	 
    }
    /*
     * INSTANCE METHODS.
     */

   
    /**
    *
    */
    public ChargingResource(int code, String name) {
        this.code = code;
        this.name = name;
    }
    
    public ChargingResource(Currency currency) {
    	this.code = currency.getCode();
    	this.name = currency.getName();
    	this.description = currency.getDescription();
    }

    /**
    */
    public String getDescription() {
        return this.description;
    }

    /**
    */
    public void setDescription(String s) {
        this.description = s;
    }

    /** This method is rubbish.  It returns null if the name begins with "ChargingResource", otherwise it returns the name.
     * If you want the resource name, use getResourceName() instead
     * TODO fix this
    */
    public String getName() {

        String resName = "";

        //REMEDY 3783 - get Resource Name from description if not available in ChargingResourceTranslator
        if(!isEmpty())
        {
            resName = this.name;
        }
        else
        {
            resName = this.description;
        }

        return resName;
//    	return name;
    }

    /**
    */
    public void setName(String s) {
        this.name = s;
    }

    /**
	*	get the ChargingResource corresponding to this id.  If it doesn't exist, build a new one with the name ""
    */
    public static ChargingResource getResource(int id)    {
        ChargingResource res = sResources.get(new Integer(id));
        if (res == null) {
            res = new ChargingResource(id, "");
        }
        return res;
    }

    public static ChargingResource getResource(String name)    {
        ChargingResource res = customResources.get(name);
        if (res == null) {
            res = new ChargingResource(0, name);
        }
        return res;
    }

    /** eg 978 for euros
    */
    public int getCode() {
        return this.code;
    }

    /** eg 978 for euros
    */
    protected void setCode(int c) {
        this.code = c;
    }

    public boolean equals(ChargingResource res)    {
       return getCode() == res.getCode() ;
    }

    /**
     * REMEDY 3783 
     * Check if the name is not empty or if not ChargingResource
     */
    private boolean isEmpty()
    {
        boolean empty = true;

        int iPos = -2;

        //If the name exists 
        if(this.name != null && this.name.length() > 0)
        {
            iPos = this.name.indexOf("ChargingResource");

            if(iPos == -1)
            {
                empty = false;
            }     
        }

        return empty;
    }

    /**
    */
    @Override
	public String toString()
    {
        StringBuffer sb = new StringBuffer();

        sb.append(  "{" );

        sb.append(  "code=" );
        sb.append(  code );

        sb.append(  " name=" );
        sb.append(  name );

        sb.append(  "}" );

        return sb.toString();
    }

    /**
     * is a usage or payment token?
     * @return
     * @deprecated
     */
    public boolean isToken()
    {
        return isUsageToken() || isPayToken();
    }

    /**
     * is the code 1100012?
     * @return
     * @deprecated
     */
    public boolean isUsageToken()    {
        if (USAGE_TOKEN.getCode() == getCode()) {
            return true;
        } else {
            return false;
        }
    }

    /**
     * is the code 1100011?
     * @return
     */
    public boolean isPayToken()    {
        if (PAY_TOKEN.getCode() == getCode()) {
            return true;
        } else {
            return false;
        }
    }

    /**
     * false if it's a currency or a token, true otherwise
     * @return
     */
    public boolean isResource()    {
        if (isCurrency() || isToken()) {
            return false;
        } else {
            return true;
        }
    }
    
    
    public final static boolean isCurrencyResource(int code)    {
        return (code>1 && code<1000) ;
    }

    public boolean isCurrency()    {
        return isCurrencyResource(getCode());
    }

    /**
     * e.g."ChargingResource_1000035"
     * @return
     */
    public String getResourceName()    {
        return "ChargingResource_" + getCode();
    }

    public String getResourceSymbol()    {
        return "ChargingResource_Symbol_" + getCode();
    }

    

    @Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + code;
		result = prime * result
				+ ((description == null) ? 0 : description.hashCode());
		result = prime * result + ((name == null) ? 0 : name.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (!(obj instanceof ChargingResource))
			return false;
		ChargingResource other = (ChargingResource) obj;
		if (code != other.code)
			return false;
		if (description == null) {
			if (other.description != null)
				return false;
		} else if (!description.equals(other.description))
			return false;
		if (name == null) {
			if (other.name != null)
				return false;
		} else if (!name.equals(other.name))
			return false;
		return true;
	}
    
}
