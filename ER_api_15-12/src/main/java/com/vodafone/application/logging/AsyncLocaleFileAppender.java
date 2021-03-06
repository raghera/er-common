package com.vodafone.application.logging;

import org.apache.log4j.AsyncAppender;
import org.apache.log4j.Category;
import org.apache.log4j.spi.Filter;
import org.apache.log4j.spi.LoggingEvent;

import com.vodafone.config.util.RequestUtil;

public class AsyncLocaleFileAppender extends AsyncAppender {

	
	 @Override
    public synchronized void doAppend(LoggingEvent event) {
        if (closed) {
            // log.error("Attempted to append to closed appender named ["+ name + "].");
            return;
        }
        
        if (!isAsSevereAsThreshold(event.getLevel())) {
            return;
        }
        
        String countryCode = RequestUtil.getRequestingCountry();

        String locale = ((countryCode == null) || countryCode.equals("")) ? " LOCALE: "
                : " LOCALE:" + countryCode.toUpperCase();
        
        LoggingEvent env = new LoggingEvent(event.fqnOfCategoryClass,
                new Category(event.getLoggerName()) {}, 
                event.timeStamp, 
                event.getLevel(), 
                event.getMessage() + locale, 
                event.getThrowableInformation()!=null?event.getThrowableInformation().getThrowable():null);
        
        Filter f = this.headFilter;
        FILTER_LOOP: while (f != null) {
            switch (f.decide(env)) {
            case Filter.DENY:
                return;
            case Filter.ACCEPT:
                break FILTER_LOOP;
            case Filter.NEUTRAL:
                f = f.next;
            }
        }
        append(env);
    }
	

}
