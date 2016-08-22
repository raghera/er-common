package com.vizzavi.ecommerce.business.catalog;

public class DefaultSpendLimits extends SpendLimits {
	
	private static final long serialVersionUID = 7008179302946653001L;

	public String toString() {
		StringBuffer strBuf = new StringBuffer();
		strBuf.append("DefaultSpendLimits: ");
		strBuf.append("\nperTxLimit: ");strBuf.append(perTxLimit);
		strBuf.append("\nperDayLimit: ");strBuf.append(perDayLimit);
		strBuf.append("\nperMonthLimit: ");strBuf.append(perMonthLimit);
		return strBuf.toString();
	}

}
