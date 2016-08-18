/**
 * 
 */
package com.vizzavi.ecommerce.business.catalog;

import java.util.HashMap;
import java.util.Locale;

/**
 * @author hud
 * STKHREQ13076 SP ROAMING
 * This is more like an abstract class
 * 
 * Current implementation is OracleNetworkCodeLoader 
 */
public interface NetworkCodeLoader 
{
	public HashMap<Locale, NetworkCode[]> load();
}
