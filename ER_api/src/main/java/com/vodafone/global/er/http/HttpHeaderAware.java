package com.vodafone.global.er.http;

import java.util.List;
import java.util.Map;

import org.apache.http.Header;

/**
 * This allows decoupling clients to set headers.  They can either pass in a list of headers, OR a map of key value pairs.  
 * Don't do both, since the second call will over-write the headers supplied by the first
 *
 */
public interface HttpHeaderAware {
	
	/**
	 * set a list of key value pairs which will be added as http headers on the next request only.  Each time you call this method, it replaces the previously added headers. The decoupling client will add some other headers itself (e.g. content-type)
	 * The headers are valid for one request only, after which they are cleared.<br/>
	 * May over-write any headers set previously by {@link #setHeaders(List headers)}
	 * @see {@link #setHeaders(List)}
	 */
	public void setHeaders(Map<String, String> headers);
	
	/**
	 * set the list of headers which will be added on the next request only.  May over-write any headers set previously.  The decoupling client will add some other headers itself (e.g. content-type)
	 * @param headers
	 * @see {@link #setHeaders(Map)}
	 */
	public void setHeaders(List<Header> headers);

	/**
	 * add a single header, for the next request only
	 * @param name
	 * @param value
	 */
	public void addHeader(String name, String value);
	
}
