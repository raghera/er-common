package com.vizzavi.ecommerce.business.common;

import java.io.InputStream;
import java.io.Serializable;
import java.util.HashMap;
import java.util.Map;

import org.exolab.castor.xml.Unmarshaller;
import org.xml.sax.InputSource;

import com.vizzavi.ecommerce.business.common.generated.Currencies;
import com.vizzavi.ecommerce.business.common.generated.Currency;

/**
* Represents the resources that customers use to pay for goods.
* This might be either "tokens" or "euros".
* Convenience class for resources used in ER2.
*/
public final class ChargingResource implements Serializable
{
   private    static final long serialVersionUID = 8025626721680256094L;

    /*
     * STATIC VARIABLES.
     */
    public static ChargingResource GBP = null;
    public static ChargingResource EUR = null;       
    
    
    public final static ChargingResource PAY_TOKEN =
        new ChargingResource( 1100011, "PAY_TOKEN" );

    public final static ChargingResource USAGE_TOKEN =
        new ChargingResource( 1100012, "USAGE_TOKEN" );

    private boolean superCredit = false;
    
    /*
     * INSTANCE VARIABLES.
     */

    int code;
    String name;    
    //REMEDY 3783 - Resource name not picked up from database
    String description;
    //CQ14113
    //private String symbol;

    private static Map<Integer, ChargingResource> sResources = new HashMap<Integer, ChargingResource>();

    static {
    	//CQ14114 - @lle - refactoring
    	try {
    		InputStream currencyCodeFile = CountryCode.class.getClassLoader().getResourceAsStream("ecom/currencycodes.xml");
    		Unmarshaller unmarshaller = new Unmarshaller(Currencies.class);
    		Currencies currencies = (Currencies) unmarshaller.unmarshal(new InputSource(currencyCodeFile));
    		for (int i=0;i<currencies.getCurrencyCount();i++) {
    			Currency aCurrency = currencies.getCurrency(i);
    			ChargingResource cr = new ChargingResource(aCurrency);
    			sResources.put(new Integer(aCurrency.getCode()), cr);
    		}    		   	
    	} catch (Exception e) {
    		e.printStackTrace();    		
    	}
        
        sResources.put(new Integer(ChargingResource.PAY_TOKEN.getCode()), ChargingResource.PAY_TOKEN);
        sResources.put(new Integer(ChargingResource.USAGE_TOKEN.getCode()), ChargingResource.USAGE_TOKEN);
        GBP = sResources.get(new Integer(826));
		EUR = sResources.get(new Integer(978));	 
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
    	//this.symbol = currency.getSymbol();    	
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

    */
    public static ChargingResource getResource(int id)
    {
        ChargingResource res = sResources.get(new Integer(id));
        if (res == null) {
            res = new ChargingResource(id, "");
        }
        return res;
    }

    

    /**
    */
    public int getCode() {
        return this.code;
    }

    /**
    */
    protected void setCode(int c) {
        this.code = c;
    }

    public boolean equals(ChargingResource res)
    {
        boolean rv = false;
        if (getCode() == res.getCode()) {
            rv = true;
        }
        return rv;
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

    public boolean isToken()
    {
        return isUsageToken() || isPayToken();
    }

    public boolean isUsageToken()
    {
        if (USAGE_TOKEN.getCode() == getCode()) {
            return true;
        } else {
            return false;
        }
    }

    public boolean isPayToken()
    {
        if (PAY_TOKEN.getCode() == getCode()) {
            return true;
        } else {
            return false;
        }
    }

    public boolean isResource()
    {
        if (isCurrency() || isToken()) {
            return false;
        } else {
            return true;
        }
    }
    public final static boolean isCurrencyResource(int code)
    {
        boolean rv = false;
        if (code>1 && code<1000) {
            rv = true;
        }
        return rv;
    }

    public boolean isCurrency()
    {
        return isCurrencyResource(getCode());
    }

    /**
     * e.g."ChargingResource_1000035"
     * @return
     */
    public String getResourceName()
    {
        return "ChargingResource_" + getCode();
    }

    public String getResourceSymbol()
    {
        return "ChargingResource_Symbol_" + getCode();
    }
    
    public boolean isSuperCredit(){
    	return superCredit;
    }
    
    public void setSuperCredit(boolean val){
    	superCredit = val;
    }    
}
