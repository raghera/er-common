package com.vodafone.global.er.decoupling.client;

import java.io.File;
import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashSet;
import java.util.List;
import java.util.Locale;
import java.util.Map;
import java.util.Set;

import org.apache.commons.lang.StringUtils;
import org.apache.http.Header;
import org.apache.http.message.BasicHeader;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.vizzavi.ecommerce.business.common.AccountNotFoundException;
import com.vizzavi.ecommerce.business.common.EcommerceException;
import com.vizzavi.ecommerce.business.common.TransactionNotFoundException;
import com.vizzavi.ecommerce.util.FileUtils;
import com.vodafone.application.util.CommonConfig;
import com.vodafone.global.er.common.ErCoreErrorId;
import com.vodafone.global.er.decoupling.PayloadConstants;
import com.vodafone.global.er.decoupling.binding.request.ErRequest;
import com.vodafone.global.er.decoupling.binding.request.ObjectFactory;
import com.vodafone.global.er.decoupling.binding.response.Error;
import com.vodafone.global.er.http.HttpHeader;
import com.vodafone.global.er.http.HttpHeaderAware;

abstract class BaseErApiDecouplingImpl implements HttpHeaderAware {

	protected final DecouplingMessageFactory _factory_;
	protected final DecouplingClient _client ;
	protected  Locale	locale;
	protected final DecouplingConverter converter;
	protected final ObjectFactory reqObjFactory = new ObjectFactory();
	public static boolean stubbed = CommonConfig.getPropertyAsBoolean("er.decoupling.stubbed", false);

	/**
	 * headers added by the client using {@link HttpHeaderAware#setHeaders}()
	 */
	private final List<Header> userHeaders = new ArrayList<>();
	/**the set of all locales for which we have instantiated any decoupling api impls*/
	protected static final Set<Locale> allLocales = new HashSet<>();
	private static final Logger	logger = LoggerFactory.getLogger(BaseErApiDecouplingImpl.class);

	protected  static final String date= "$Date: 2015/10/06 10:15:21 $";
	protected static final String version="$Revision: 1.4 $";

	public BaseErApiDecouplingImpl(Locale locale, String clientId)	{
		if (stubbed)	{
			System.err.println("ER core is STUBBED!  This client will not work properly in prod");
			System.err.println("           ^^^^^^^");
		}	
		logger.info("BaseErApiDecouplingImpl "+version+" dated "+date);
		this.locale= locale;
		_factory_ = new DecouplingMessageFactory();
		_client = new DecouplingClient(this.locale, clientId);
		this.converter = new DecouplingConverter(locale);
		allLocales.add(locale);
	}


	/**
	 * <p>Assembles the request, sends it to ER, and then returns the response type object expected.  Checks that the response is of the correct type, and not null.</p>
	 * In other words, combines {@link DecouplingMessageFactory#buildEnvelope}, DecouplingClient.getPayload(), and getResultFromPayload()
	 * <p>If the response is an Error, assembles an EcommerceException from the Error.  If it's null, or not the expected response type, throws an EcommerceException..</p>
	 * @see #getResultFromPayload
	 * @param requestTypeId eg {@link PayloadConstants.MODIFY_MSISDN}
	 * @param request a jaxb object, eg {@link com.vodafone.global.er.decoupling.binding.request.SelfcareFullSubscriptions}, with all necessary fields populated
	 * @param expectedResponse 
	 * @param clientId for the  client-application-id field in the request - can be null
	 * @return a jaxb response object, eg {@link com.vodafone.global.er.decoupling.binding.request.SelfcareFullSubscriptions}
	 * @throws EcommerceException if the response object doesn't match what was expected, or a jaxb exception is encountered assembling the request, or there is an 
	 * IOException talking to ER, or ER responds with an empty response
	 */
	protected <T> T  sendRequestAndGetResponse(int requestTypeId, Object request, Class<T> expectedResponse, String clientId) throws EcommerceException	{
		return sendRequestAndGetResponse(requestTypeId, request,
				expectedResponse, clientId, 1);
	}


	/**
	 * <p>Assembles the request, sends it to ER, and then returns the response type object expected.  Checks that the response is of the correct type, and not null.</p>
	 * In other words, combines {@link DecouplingMessageFactory#buildEnvelope}, DecouplingClient.getPayload(), and getResultFromPayload()
	 * <p>If the response is an Error, assembles an EcommerceException from the Error.  If it's null, or not the expected response type, throws an EcommerceException..</p>
	 * @see #getResultFromPayload
	 * @param requestTypeId eg {@link PayloadConstants.MODIFY_MSISDN}
	 * @param request a jaxb object, eg {@link com.vodafone.global.er.decoupling.binding.request.SelfcareFullSubscriptions}, with all necessary fields populated
	 * @param expectedResponse 
	 * @param clientId for the  client-application-id field in the request - can be null
	 * @param requestVersion TODO
	 * @return a jaxb response object, eg {@link com.vodafone.global.er.decoupling.binding.request.SelfcareFullSubscriptions}
	 * @throws EcommerceException if the response object doesn't match what was expected, or a jaxb exception is encountered assembling the request, or there is an 
	 * IOException talking to ER, or ER responds with an empty response
	 */
	protected <T> T  sendRequestAndGetResponse(int requestTypeId, Object request, Class<T> expectedResponse, String clientId, int requestVersion) throws EcommerceException	{
		logger.debug("Enter BaseErApiDecouplingImpl.sendRequestAndGetResponse");
		Object payload = null;
		final ErRequest element = _factory_.buildEnvelope(requestTypeId, request, clientId);
		if (requestVersion>1)
			element.setVersion(requestVersion);
		//TODO improve / replace this basic stub framework
		File stubFile=null;
		if (stubbed)	{
			stubFile = new File(requestTypeId+".xml");	//ideally we add msisdn or something in this filename
			logger.error("stub enabled - looking for response in file "+stubFile.getAbsolutePath());
		}
		if (stubbed && stubFile!=null && stubFile.canRead())	{
			try {
				String xml = FileUtils.getFile(stubFile.getAbsolutePath());
				logger.error("response xml FROM STUB FILE:\n {}", xml);
				payload= _client.getResponseObject(xml);
			} catch (Exception e) {
				logger.error(e.getMessage(), e);
			}
		}	else {
			logger.debug("Calling DecouplingClient.getPayload");
			payload = _client.getPayload(element, getHeaders(request));
		}
        logger.debug("Enter BaseErApiDecouplingImpl.sendRequestAndGetResponse response received." + payload);
		return getResultFromPayload(payload, expectedResponse);
	}
	
	/**
	 * <p>Assembles the request, sends it to ER, and then returns the response type object expected.  Checks that the response is of the correct type, and not null.</p>
	 * In other words, combines {@link DecouplingMessageFactory#buildEnvelope}, {@link DecouplingClient#getPayload}, and {@link #getResultFromPayload}
	 * <p>If the response is an Error, assembles an EcommerceException from the Error.  If it's null, or not the expected response type, throws an EcommerceException..</p>
	 * @see #getResultFromPayload
	 * @param requestTypeId eg {@link PayloadConstants.MODIFY_MSISDN}
	 * @param request a jaxb object, eg {@link com.vodafone.global.er.decoupling.binding.request.SelfcareFullSubscriptions}, with all necessary fields populated
	 * @param expectedResponse The expected class of the response 
	 * @return a jaxb response object, eg {@link com.vodafone.global.er.decoupling.binding.response.SelfcareFullSubscriptions}
	 * @throws EcommerceException if the response object doesn't match what was expected, or a jaxb exception is encountered assembling the request, or there is an 
	 * IOException talking to ER, or ER responds with an empty response
	 */
	protected <T> T  sendRequestAndGetResponse(int requestTypeId, Object request, Class<T> expectedResponse) throws EcommerceException	{
		return sendRequestAndGetResponse(requestTypeId, request,
				expectedResponse, 1);
	}


	/**
	 * <p>Assembles the request, sends it to ER, and then returns the response type object expected.  Checks that the response is of the correct type, and not null.</p>
	 * In other words, combines {@link DecouplingMessageFactory#buildEnvelope}, {@link DecouplingClient#getPayload}, and {@link #getResultFromPayload}
	 * <p>If the response is an Error, assembles an EcommerceException from the Error.  If it's null, or not the expected response type, throws an EcommerceException..</p>
	 * @see #getResultFromPayload
	 * @param requestTypeId eg {@link PayloadConstants.MODIFY_MSISDN}
	 * @param request a jaxb object, eg {@link com.vodafone.global.er.decoupling.binding.request.SelfcareFullSubscriptions}, with all necessary fields populated
	 * @param expectedResponse The expected class of the response 
	 * @param version decoupling API version
	 * @return a jaxb response object, eg {@link com.vodafone.global.er.decoupling.binding.response.SelfcareFullSubscriptions}
	 * @throws EcommerceException if the response object doesn't match what was expected, or a jaxb exception is encountered assembling the request, or there is an 
	 * IOException talking to ER, or ER responds with an empty response
	 */
	protected <T> T  sendRequestAndGetResponse(int requestTypeId, Object request, Class<T> expectedResponse, int version) throws EcommerceException	{
		return sendRequestAndGetResponse(requestTypeId, request, expectedResponse, null, version);
	}
	
	@SuppressWarnings("unchecked")
	protected <T> T createRequest(int requestTypeId)	{
			return (T) _factory_.createRequest(requestTypeId);
	}

	/**
	 * 
	 * checks for methods like 'getMsisdn()' on the object, then calls the method and sets its return value in a header called 'x-msisdn'
	 * @param request
	 * @return a list of http header objects ready to add to the http request
	 */
	protected List<Header> getHeaders(Object request)	{
		final List<Header> headersToAdd = new ArrayList<>();
		try {
			final Class<?> c = request.getClass();
			final Method[] methods = c.getMethods();
			final List<String> thingsToadd = Arrays.asList(new String[]{"msisdn", "service", "package", "partner", "client"});
			//no need to add 'country' header - that is added in the DecouplingClient
			for (final String thing: thingsToadd)	{
				String searchString = "get"+thing;	//eg getMsisdn, getPackage, etc - these are methods we want to find and invoke
				for (final Method method : methods) {
					//final Class<?> returnType = method.getReturnType();
					if (method.getParameterTypes().length==0 && method.getReturnType().equals(String.class) && (method.getName().toLowerCase().indexOf(searchString)>=0))	{
						final Object result  = method.invoke(request, new Object[]{});
						headersToAdd.add(new BasicHeader("x-"+thing, result!=null?result.toString():"null"));
					}
				}
			}
		} catch (final Exception e) {
			logger.warn(e.getMessage());
		}
		if (userHeaders!=null && !userHeaders.isEmpty())	{
			headersToAdd.addAll(userHeaders);
			//we don't want the same headers to stick around for the next request
			userHeaders.clear();
		}
		return headersToAdd;
	}

	/**
	 * checks if any of the supplied objects are null, and if so throws an IllegalArgumentException
	 */
	protected void checkNullParams(Object...objects ) throws IllegalArgumentException {
		for(Object object: objects){
			if (object ==null)	{
				throw new IllegalArgumentException("a required parameter was null");
			}
		}
	}

//	/**
//	 * checks if the msisdn is a valid mobile number for the locale.  Logs a message at warn level if not.
//	 * @param msisdn
//	 */
//	protected void checkMsisdn(String msisdn)	{
//		if (!MsisdnUtils.isValidMobileNumber(msisdn, locale))
//			logger.warn("msisdn {} is not a valid mobile number for {}", msisdn, locale);
//	}

	/**
	 * casts the payload to the expected class.  If it's an Error, assembles (and throws) an EcommerceException from the Error.  If it's null, throws an EcommerceException. 
	 *  If it's not null, not an error, but not the expected class, throws an EcommerceException
	 * @param responsePayload
	 * @param expectedClass
	 * @return
	 * @throws EcommerceException 
	 * @see #sendRequestAndGetResponse
	 */
	protected <T> T getResultFromPayload(Object responsePayload, Class<T> expectedClass) throws EcommerceException {
		logger.info("Enter BaseErApiDecouplingImpl.getResultFromPayload");
		if (responsePayload==null)
			throw new EcommerceException("response payload was null");
		//check for Error first, in case the client passed in expectedClass of Object
		if (Error.class.isAssignableFrom(responsePayload.getClass()))	{
			logger.warn("response {}, expected {}", responsePayload, expectedClass);
			throw buildExceptionFromError((Error) responsePayload);	
		}
		else if (expectedClass.isAssignableFrom(responsePayload.getClass()))	{
			@SuppressWarnings("unchecked")
			T result = (T) responsePayload;
			logger.info("Enter BaseErApiDecouplingImpl.sendRequestAndGetResponse returning result");
			return result;
		}
		else
			throw new EcommerceException("unexpected payload response type "+responsePayload.getClass().getSimpleName()+": expected "+expectedClass.getSimpleName());
	}

	/**
	 * populates an EcommerceException from the {@link com.vodafone.global.er.decoupling.binding.response.Error} passed in.  
	 * Will attempt to produce a subclass (eg AccountNotFoundException) where possible, based on the errorId
	 * Also logs the details at warn level
	 * @param error
	 * @return
	 */
	protected EcommerceException buildExceptionFromError(Error error)	{
		logger.warn("ErrorResponse:ID {}, desc {}, type {}, sysId {}, cause {}" , new Object[]{ error.getId(), 
				error.getDescription(), error.getType(), error.getSystemId(), error.getCause()});

		StringBuilder rv = new StringBuilder(error.getDescription());

		if (StringUtils.isNotBlank(error.getCause()))
			rv.append(", Cause ").append(error.getCause());

		int errorId=-1;
		try	{
			errorId=Integer.parseInt(error.getId());
		} catch(NumberFormatException nfe)	{
			logger.warn("error response {} has non-integer id {}", error, error.getId());
		}
		//TODO extend this to pick up any other kind of EcommerceException we can think of
		//eg PURCHASE_AUTHORIZATION_EXCEPTION
		if (errorId==ErCoreErrorId.C_MSISDN_NOT_FOUND)	{	//218438: account not found
			AccountNotFoundException anfe= new AccountNotFoundException(rv.toString());
			anfe.setSystemId(error.getSystemId());
			anfe.setErrorId(errorId);
			anfe.setErrorDescription(error.getDescription());
			return anfe;
		} else if (errorId == ErCoreErrorId.TRANSACTION_NULL.getCode())	{	//216446 transaction not found
			TransactionNotFoundException tnfe = new TransactionNotFoundException(rv.toString());
			tnfe.setSystemId(error.getSystemId());
			tnfe.setErrorId(errorId);
			tnfe.setErrorDescription(error.getDescription());
			return tnfe;
		} else	{
			logger.info("errorId {} returned: {}", errorId, rv);
			EcommerceException e = new EcommerceException(rv.toString());
			e.setSystemId(error.getSystemId());
			e.setErrorId(errorId);
			return e;
		}
	}

	/**
	 * {@inheritDoc}
	 * 
	 */
	@Override
	public void setHeaders(Map<String, String> headers) {
		//first clear the existing headers
		userHeaders.clear() ;
		for (String headerName: headers.keySet()){
			userHeaders.add(new HttpHeader(headerName, headers.get(headerName)));
		}
	}

	/**
	 * {@inheritDoc}
	 * 
	 */
	@Override
	public void setHeaders(List<Header> headers) {
		//first clear the existing headers
		userHeaders.clear() ;
		for (Header header: headers){
			userHeaders.add(header);
		}
	}
	
	public void addHeader(String name, String value)	{
		//userHeaders.clear() ;
		userHeaders.add(new HttpHeader(name, value));
	}
}
