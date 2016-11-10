package com.vizzavi.ecommerce.business.catalog;

public class DefaultGoodwillCreditLimits extends GoodwillCreditLimits {
	
	public String toString() {
		StringBuffer strBuf = new StringBuffer();
		strBuf.append("DefaultGoodwillCreditLimits: ");
		strBuf.append("\nperTxLimit: ");strBuf.append(perTxLimit);
		strBuf.append("\nperDayLimit: ");strBuf.append(perDayLimit);
		strBuf.append("\nperMonthLimit: ");strBuf.append(perMonthLimit);
		return strBuf.toString();
	}

}
