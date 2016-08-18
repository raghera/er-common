/*
 * Created on Nov 3, 2004
 *
 * To change the template for this generated file go to
 * Window&gt;Preferences&gt;Java&gt;Code Generation&gt;Code and Comments
 */
package com.vodafone.global.er.subscriptionmanagement;
/**
 * @author VignonA
 *
 * To change the template for this generated type comment go to
 * Window&gt;Preferences&gt;Java&gt;Code Generation&gt;Code and Comments
 */
public interface DBVendorConnectionInterface {
	public java.sql.Connection getVendorConnection(java.sql.Connection jdbcConnection) throws java.sql.SQLException;
}
