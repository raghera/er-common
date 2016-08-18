package com.vizzavi.ecommerce.business.common;

import java.util.Locale;
/**
 * use CountryCode or preferably ErCountry instead
 *
 */
@Deprecated
public class LocaleMapper {

    public static Locale get( String cc ) {
        //CQ14113 - @lle - refactoring to get local from CountryCode instead of hard-coded
        return CountryCode.getLocale(cc);        
    }

}
