package com.vodafone.global.er.util;

import org.junit.Test;

import static com.vodafone.global.er.util.CatalogUtil.getTaxCodeFromPricePointId;
import static org.junit.Assert.assertEquals;

/**
 * Created by Ravi Aghera
 */
public class CatalogUtilTest {

    @Test
    public void shouldGetTaxCode() {

        String testStr = "content:pAlt_TAX_sAlt_1_999_*_999_999";
        String result = getTaxCodeFromPricePointId(testStr);
        assertEquals("TAX", result);
    }
    @Test
    public void shouldReturnNullWhenCrazyString() {

        String testStr = "20934832092";
        String result = getTaxCodeFromPricePointId(testStr);
        assertEquals(null, result);
    }
    @Test
    public void shouldReturnNullWhenCrazyString2() {
        String testStr = "__4321_________";
        String result = getTaxCodeFromPricePointId(testStr);
        assertEquals(null, result);
    }
    @Test
    public void shouldReturnNullWhenNoTaxReturned() {

        String testStr = "content:pAlt__sAlt_1_999_*_999_999";
        String result = getTaxCodeFromPricePointId(testStr);
        assertEquals(null, result);
    }
    @Test
    public void shouldReturnNullWhenEmptyInput() {

        String testStr = "20934832092";
        String result = getTaxCodeFromPricePointId(testStr);
        assertEquals(null, result);
    }
}
