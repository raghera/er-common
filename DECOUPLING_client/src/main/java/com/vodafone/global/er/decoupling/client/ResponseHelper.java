//package com.vodafone.global.er.decoupling.client;
//
//import java.io.UnsupportedEncodingException;
//
//import javax.xml.bind.JAXBException;
//
//import org.apache.commons.lang.StringUtils;
//import org.slf4j.Logger;
//import org.slf4j.LoggerFactory;
//
//import com.vizzavi.ecommerce.business.common.AccountNotFoundException;
//import com.vizzavi.ecommerce.business.common.EcommerceException;
//import com.vizzavi.ecommerce.business.common.TransactionNotFoundException;
//import com.vodafone.global.er.common.ErCoreErrorId;
//import com.vodafone.global.er.decoupling.ErrorConstants;
//import com.vodafone.global.er.decoupling.binding.response.ErResponse;
//import com.vodafone.global.er.decoupling.binding.response.Error;
//import com.vodafone.global.er.decoupling.util.xml.JAXBResponseHelper;
//
//class ResponseHelper {
//
//	private static final Logger	logger = LoggerFactory.getLogger(ResponseHelper.class);
//
//	/**
//	 * parses an ER response string, and returns the object inside the payload
//	 * @param xmlResponse
//	 * @return
//	 * @throws JAXBException
//	 * @throws UnsupportedEncodingException
//	 */
//	static Object getResponseObject(String xmlResponse) throws JAXBException, UnsupportedEncodingException{
//		ErResponse r =JAXBResponseHelper.getInstance().bind(xmlResponse);
//		return r.getPayload().getAny();
//	}
//
//
//
//	/**
//	 * populates an EcommerceException from the {@link com.vodafone.global.er.decoupling.binding.response.Error} passed in.
//	 * Will attempt to produce a subclass (eg AccountNotFoundException) where possible, based on the errorId
//	 * Also logs the details at warn level
//	 * @param error can be null
//	 * @return an EcommerceException or subclass
//	 */
//	static EcommerceException buildExceptionFromError(Error error)	{
//		if (error==null)
//			return new EcommerceException();
//
//		logger.warn("ErrorResponse:ID {}, desc {}, type {}, sysId {}, cause {}" , new Object[]{ error.getId(),
//				error.getDescription(), error.getType(), error.getSystemId(), error.getCause()});
//
//		StringBuilder rv = new StringBuilder(error.getDescription());
//
//		if (StringUtils.isNotBlank(error.getCause()))
//			rv.append(", Cause ").append(error.getCause());
//
//		int errorId=-1;
//		try	{
//			errorId=error.getId();
//		} catch(NumberFormatException nfe)	{
//			logger.warn("error response {} has non-integer id {}", error, error.getId());
//		}
//		//TODO extend this to pick up any other kind of EcommerceException we can think of
//		//eg PURCHASE_AUTHORIZATION_EXCEPTION
//		if (errorId==ErCoreErrorId.C_MSISDN_NOT_FOUND)	{	//218438: account not found
//			AccountNotFoundException anfe= new AccountNotFoundException(rv.toString());
//			anfe.setSystemId(error.getSystemId());
//			anfe.setErrorId(errorId);
//			anfe.setErrorDescription(error.getDescription());
//			return anfe;
//		} else if (errorId == ErCoreErrorId.TRANSACTION_NULL.getCode())	{	//216446 transaction not found
//			TransactionNotFoundException tnfe = new TransactionNotFoundException(rv.toString());
//			tnfe.setSystemId(error.getSystemId());
//			tnfe.setErrorId(errorId);
//			tnfe.setErrorDescription(error.getDescription());
//			return tnfe;
//		}	else if (errorId == ErrorConstants.ERROR_ID_UNMARSHALL_EXCEPTION)	{
//			//validation exception
//			EcommerceException e = new EcommerceException(rv.toString());
//			e.setErrorId(ErrorConstants.ERROR_ID_UNMARSHALL_EXCEPTION);
//			e.setErrorDescription(error.getType());
//			return e;
//		} else	{
//			logger.info("errorId {} returned: {}", errorId, rv);
//			EcommerceException e = new EcommerceException(rv.toString());
//			e.setSystemId(error.getSystemId());
//			e.setErrorId(errorId);
//			e.setErrorDescription(error.getDescription());
//			return e;
//		}
//	}
//
//
//
//
//
//	/**
//	 * parses an ER response string, and returns the object inside the payload
//	 * @param <T>
//	 * @param xmlResponse
//	 * @return null in the event of any exception - the object of type T otherwise
//	 */
//	@SuppressWarnings("unchecked")
//	static <T> T getResponseObject(String xmlResponse, Class<T> expectedClass) {
//		T rv = null;
//		try	{
//			ErResponse r =JAXBResponseHelper.getInstance().bind(xmlResponse);
//			rv = (T) r.getPayload().getAny();
//		}	catch(Exception e)	{
//			logger.warn("couldn't get a "+expectedClass.getCanonicalName() +" from  response: "+  e.getMessage(), e);
//			logger.info(xmlResponse);
//		}
//		return rv;
//	}
//}
