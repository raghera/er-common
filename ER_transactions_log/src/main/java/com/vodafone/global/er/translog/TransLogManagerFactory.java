package com.vodafone.global.er.translog;

public class TransLogManagerFactory {
	private static TransLogManager transLogManager;
	static {
		transLogManager = new TransLog4jManagerImpl();
	}

	private TransLogManagerFactory() {
	}

	public static TransLogManager getInstance() {
		return transLogManager;
	}
}
