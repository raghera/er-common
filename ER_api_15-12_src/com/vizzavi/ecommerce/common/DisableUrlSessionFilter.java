package com.vizzavi.ecommerce.common;

import java.io.IOException;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpServletResponseWrapper;
import javax.servlet.http.HttpSession;

/**
 * MQC6635 - security audit - Session ID in URL - used for batch webapp, but not really necessary since batch webapp is not publicly exposed...
 */
public class DisableUrlSessionFilter implements Filter {

    /* (non-Javadoc)
     * @see javax.servlet.Filter#destroy()
     */
    @Override
	public void destroy() {
        // TODO Auto-generated method stub

    }

    /* (non-Javadoc)
     * @see javax.servlet.Filter#doFilter(javax.servlet.ServletRequest, javax.servlet.ServletResponse, javax.servlet.FilterChain)
     */
    @Override
	public void doFilter(ServletRequest request, ServletResponse response,
            FilterChain chain) throws IOException, ServletException {
        // TODO Auto-generated method stub
        if(!(request instanceof HttpServletRequest)){
            chain.doFilter(request, response);
        }
        HttpServletRequest httpRequest = (HttpServletRequest) request;
        HttpServletResponse httpResponse = (HttpServletResponse) response;
        
        if(httpRequest.isRequestedSessionIdFromURL()){
            HttpSession session = httpRequest.getSession();
            if(session != null) session.invalidate();
        }
        
        HttpServletResponseWrapper wrapperResponse = new HttpServletResponseWrapper(httpResponse){
            
            @Override
            public String encodeRedirectUrl(String url) { return url;}
            
            @Override
            public String encodeRedirectURL(String url) { return url;}
        
            @Override
            public String encodeUrl(String url) { return url;}

            @Override
            public String encodeURL(String url) { return url;}

        };
        
        //Process
        chain.doFilter(request, wrapperResponse);
    }

    /* (non-Javadoc)
     * @see javax.servlet.Filter#init(javax.servlet.FilterConfig)
     */
    @Override
	public void init(FilterConfig arg0) throws ServletException {
        // TODO Auto-generated method stub

    }

}
