package com.vizzavi.ecommerce.business.common;

import java.util.Hashtable;
import java.util.Locale;

/**
    This is a convenience class to retrieve the default payment type for a country.

    To retrieve the synchronous payment type,
<code>
<pre>
    Locale locale = Locale.GERMANY;
    int paymentType = paymentSystemType.getSyncPaymentType( locale );
</pre>
</code>


*/
public class PaymentSystemType
{
    public static final int TOKENS = 0;
    public static final int CREDITCARD = 1;
    public static final int OPCO_ASYNC = 2;
    public static final int OPCO_SYNC = 3;
    public static final int UNKNOWN = -1;

    /**
    * SIMPAY Changes - Aitor Elorza - May '04
    * New payment type for register Simpay Transactions
    * For demo handler will return UNKNOWN
    */
    public static final int SIMPAY = 100000;

    public static final int PECS_PAY_TYPE_TOKENS = 0;
    public static final int PECS_PAY_TYPE_CC_START_RANGE = 1;
    public static final int PECS_PAY_TYPE_CC_END_RANGE                      =999;
    public static final int PECS_PAY_TYPE_OPCO_ASYNC_START_RANGE            =1000;
    public static final int PECS_PAY_TYPE_OPCO_ASYNC_END_RANGE              =9999;
    public static final int PECS_PAY_TYPE_OPCO_SYNC_START_RANGE             =10000;
    public static final int PECS_PAY_TYPE_OPCO_SYNC_END_RANGE               =99999;

    private static Hashtable<String, Integer> SC_PSPS = new Hashtable<String, Integer>(16);
    private static Hashtable<String, Integer> CC_PSPS = new Hashtable<String, Integer>(16);

    private static final int DEFAULT_CC_VALUE = 2;
    private static final int DEFAULT_SC_VALUE = 3;
    private static final int DEFAULT_SYNC_VALUE = 10000;
    private static final int DEFAULT_ASYNC_VALUE = 2000;

    /**
        Retrieves the tyep from the payment type.
        This should not be used.
    */
    public static int getTypeFromPspCode( int thePspCode )  {
        if( thePspCode == 0 ) return TOKENS;
        else if( thePspCode <= PECS_PAY_TYPE_CC_END_RANGE ) return CREDITCARD;
        else if( thePspCode <= PECS_PAY_TYPE_OPCO_ASYNC_END_RANGE ) return OPCO_ASYNC;
        else if( thePspCode <= PECS_PAY_TYPE_OPCO_SYNC_END_RANGE ) return OPCO_SYNC;
        else return UNKNOWN;
    }

    private static int getValue( Hashtable<String, Integer> hash, String isoCountryCode, int defaultValue )  {
        Integer code = hash.get( isoCountryCode );
        return code == null ? defaultValue : code.intValue();
    }

    /**
    * Return the payment system integer code for the supplied locale.
    */
    public static int getSyncPaymentType( Locale locale )  {
        return getSyncPaymentType( locale.getCountry() );
    }

    /**
    * Return the payment system integer code for country isoCountryCode.
    */
    public static int getSyncPaymentType( String isoCountryCode ) {
        return getValue( CountryCode.SYNC_PSPS, isoCountryCode, DEFAULT_SYNC_VALUE ); //CQ14113 - @lle - refactoring
    }

    /**
    * Return the payment system integer code for the supplied locale.
    */
    public static int getAsyncPaymentType( Locale locale ) {
        return getAsyncPaymentType( locale.getCountry() );
    }

    /**
    * Return the payment system integer code for country isoCountryCode.
    */
    public static int getAsyncPaymentType( String isoCountryCode )  {
    	// - @lle - refactoring, reading payment system type from countriescode.xml
    	return getValue( CountryCode.ASYNC_PSPS, isoCountryCode, DEFAULT_ASYNC_VALUE ); 
    }

    /**
    * Return the payment system integer code for the supplied locale.
    */
    public static int getCreditCardPaymentType( Locale locale ) {
        return getCreditCardPaymentType( locale.getCountry() );
    }

    /**
    * Return the payment system integer code for country isoCountryCode.
    */
    public static int getCreditCardPaymentType( String isoCountryCode ) {
        return getValue( CC_PSPS, isoCountryCode, DEFAULT_CC_VALUE );
    }

    /**
    * Return the payment system integer code for the supplied locale.
    */
    public static int getScratchCardPaymentType( Locale locale ) {
        return getScratchCardPaymentType( locale.getCountry() );
    }

    /**
    * Return the payment system integer code for country isoCountryCode.
    */
    public static int getScratchCardPaymentType( String isoCountryCode ) {
        return getValue( SC_PSPS, isoCountryCode, DEFAULT_SC_VALUE );
    }

}
