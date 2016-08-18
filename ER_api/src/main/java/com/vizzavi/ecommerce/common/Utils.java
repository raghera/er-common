package com.vizzavi.ecommerce.common;

import java.lang.reflect.Method;
import java.net.InetAddress;
import java.net.UnknownHostException;
import java.text.DecimalFormat;
import java.util.*;

import javax.naming.Binding;
import javax.naming.Context;
import javax.naming.NamingEnumeration;
import javax.naming.NamingException;

import org.apache.http.Header;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class Utils {

	private static final Logger logger = LoggerFactory.getLogger(Utils.class);
	private static final String PREPAY		= "PRE";
	private static final String POSTPAY		= "POST";

	/**
	 * returns true if the supplied string is null, empty, 'pre', or 'post' (case-insensitive).<br/>
	 * How that's supposed to help tell if a user is prepay or not, god alone knows.
	 * @param isPrepay
	 * @return
	 */
	public static boolean checkPrepayValue(String isPrepay)
	{

		boolean validate 	= false;

		if (null == isPrepay || isPrepay.equals(""))
		{
			validate = true;
		}
		else if (isPrepay.trim().toUpperCase().equals(PREPAY) || isPrepay.trim().toUpperCase().equals(POSTPAY))
		{
			validate = true;
		}
		else
		{
			return validate;
		}

		return validate;
	}
	// CR 1643 - Ends

	/**
	 * calls InetAddress.getLocalHost().toString()
	 * @return a String representation of the local InetAddress (eg somehost.vodafone.com/145.230.58.93 )
	 */
	public static String getLocalHostName(){

		try {
			if ( InetAddress.getLocalHost() != null ){
				return InetAddress.getLocalHost().toString();
			}
		}catch (UnknownHostException ex) {
			logger.error("LocalHost name could not be obtained: Message:{} " , ex );
			logger.debug("Returning null");
			//NetworkInterface.getNetworkInterfaces();
		}
		return null;

	}

	/**
	 * compares a hostname String of the form somehost.vodafone.com/145.230.58.93 
	 * (which you can retrieve by calling getLocalHostName()) to the localhost IP address.
	 * @param hostname 
	 * @return true if the IP address matches the local IP address (ignoring the hostname part)
	 */
	public static boolean amI(String hostname)	{
		logger.debug("am I {}?", hostname);
		if (hostname != null && hostname.indexOf("/")>0)	{
			String ipAddress = hostname.split("/")[1];
			String myIpAddress="";
			try {
				myIpAddress = InetAddress.getLocalHost().getHostAddress();
			} catch (UnknownHostException e) {
				logger.warn(e.getMessage());
			}
			logger.debug("my ip address is {} - comparing with {}", myIpAddress, ipAddress);
			if (myIpAddress.equals(ipAddress))
				return true;
		}

		return false;
	}

	/**
	 * TODO rewrite this using java.lang.reflect<br/>
	 * Returns a list of the calling classes in order, with consecutive duplicates removed.  Also logs it at info level in the logger of the class from which you're calling this method
	 * @param callStackDepth how far back you want to check.  
	 * @return a List<String> of class names - ie duplicate classnames are removed
	 */
	public static List<String> getCallerClassNames(int callStackDepth) {
		return getCallerClassNames(callStackDepth, true);
	}

	/**
	 * Returns a list of the calling classes in order, with consecutive duplicates removed. <br/>
	 * NB this relies on the sun jvm 
	 * @param callStackDepth how far back you want to check.  
	 * @param fullClassNames do you want com.vodafone.Class or just Class
	 * @return a List<String> of class names - ie duplicate classnames are removed
	 */
	public static List<String> getCallerClassNames(int callStackDepth, boolean fullClassNames) {

		List<String> trace = new ArrayList<String>();
		int i = 2;	//don't really want to include the Utils class in the trace
		while (trace.size() < callStackDepth){
 			Class<?> thisClass=sun.reflect.Reflection.getCallerClass(i);
			if(thisClass != null	//now check that the last one in the list is not the same
				&& ((trace.size()==0 ) || 
				!(trace.get(trace.size()-1).contains(thisClass.getSimpleName()))))	{
				if (fullClassNames)
					trace.add(sun.reflect.Reflection.getCallerClass(i).getName());
				else
					trace.add(sun.reflect.Reflection.getCallerClass(i).getSimpleName());
				
			}	//else
				//break;
			i++;
		}

		return trace;
	}


	/**
	 * Throws an exception some of the time.  Use for testing only ... obviously
	 * @param percentage what percentage of the time you want to throw an exception
	 * @throws Exception at random
	 */
	public static void throwExceptionSometimes(int percentage)	{
		logger.error("shall I throw an exception?  Hmmm. maybe.");
		Random generator = new Random();
		int result = generator.nextInt(100);	//number between 0 and 100
		if (result < percentage){
			logger.error("throwing a random exception!");
			throw new RuntimeException("random exception");
		}
		logger.error("I decided not to throw an exception");
	}
	
	/**
	 * Concatenates a List or Set of Objects into a String, separating tokens with the specified char.  Uses toString() on each object.
	 * Returns zero-length string if the collection is empty or null.
	 * @param thingy and Collection of objects
	 * @param char the separator
	 * @return 
	 */
	public static String StringifyList(Collection<?> thingy, char separator){	
		return StringifyList(thingy, new String(new char[]{separator}));
	}
	
	/**
	 * Concatenates a List or Set of Objects into a String, separating tokens with the specified string.  Uses toString() on each object.
	 * Returns zero-length string if the collection is empty or null.
	 * @param thingy and Collection of objects
	 * @param String the separator
	 * @return 
	 */
	public static String StringifyList(Collection<?> thingy, String separator){
		if (thingy==null ||thingy.size() == 0)
			return "";
//		if (thingy.size()==1)	{
//			Iterator<?> it =thingy.iterator();
//			Object next= it.next();
//			if (next!=null)
//				return  next.toString();
//			else 
//				return "null";
//		}
		StringBuffer sbf = new StringBuffer();
		for (Object thisObj: thingy)	{
			sbf.append(thisObj).append(separator);
		}
		//cut off the last separator
		return sbf.substring(0,  sbf.length()-separator.length()).toString();	
	}

	/**
	 * Concatenates a List or Set into a String, separating tokens with a single space.  Uses toString() on each object.
	 * Returns zero-length string if the collection is empty or null.
	 * @param thingy
	 * @return
	 */
	public static String StringifyList(Collection<?> thingy)	{

		return StringifyList(thingy, ' ');
	}


	/**
	 * When an account is inactivated, the msisdn is renamed to something like "447717512961_Wed Oct 24 13:35:01 BST 2012"<br/>
	 * Pass in this modified string and this method will return the original msisdn.<br/>
	 * If the msisdn has not been modified (ie doesn't contain '_') then the original msisdn is returned.
	 * @param msisdn
	 * @return
	 */
	public static String fixInactivatedMsisdn(String msisdn)	{
		
		int index = msisdn.indexOf('_');
		if (index<0)
			return msisdn;
		else
			return msisdn.substring(0, index);
	}
	
	/**
	 * Concatenates a List or Set into a String, separating tokens with a single space.  Uses toString() on each object.
	 * Returns zero-length string if the collection is empty.
	 * @param thingy
	 * @return
	 */
	public static String StringifyList(Object[] things) {
		return stringifyList(things, " ");
	}
	
	public static String stringifyList(Header[] headers) {
		if (headers==null ||headers.length==0)
			return "";
		StringBuilder rv = new StringBuilder();
		for(Header h: headers)	{
			rv.append(h.getName()).append(": ").append(h.getValue()).append(", ");
		}
		return rv.toString();
	}
	
	/**
	 * Concatenates a List or Set into a String, separating tokens with a single space.  Uses toString() on each object.
	 * Returns zero-length string if the collection is empty.
	 * @param thingy
	 * @return
	 */
	public static String stringifyList(Object[] things, String separator) {
		if (things==null ||things.length==0)
			return "";
		return StringifyList(Arrays.asList(things), separator);
	}
	
	/**
	 * returns a new List containing only the objects which are in both lists
	 * @param list1
	 * @param list2
	 * @return
	 */
	public static <T> List<T> getIntersection(Collection<T> list1, Collection<T> list2){
		Set<T> intersection = new HashSet<T>(list1);
	    intersection.retainAll(new HashSet<T>(list2));
	    return new ArrayList<T>(intersection);
	}
	
	/**
	 * does what it says on the tin...
	 * @param list
	 * @return
	 */
	public static <T> List<T> removeDuplicatesFromList(List<T> list){

		Set<T> set = new TreeSet<T>(list);
		List<T> rv = new ArrayList<T>(set);
		return rv;
	}
	
	/**
	 * splits a comma-separated list of tokens to a List&lt;String><br/>
	 * Returns an empty list if a null or empty string is passed in
	 * @param tokens
	 * @return
	 */
	public static List<String> parseList(String tokens){
		List<String> l = new ArrayList<String>();
		if (tokens ==null || tokens.length()==0)
			return l;
		String[]co = tokens.split(",");

		for (String element : co) {
			String c = element.trim();
			l.add(c)	;			
		}
		return l;
	}
	
	/**
	 * splits a comma-separated list of tokens to a Set&lt;String><br/>
	 * Returns an empty Set if a null or empty string is passed in
	 * @param tokens
	 * @return
	 */
	public static Set<String> parseSet(String tokens){
		Set<String> l = new HashSet<String>();
		if (tokens ==null || tokens.length()==0)
			return l;
		String[]co = tokens.split(",");

		for (String element : co) {
			String c = element.trim();
			l.add(c)	;			
		}
		return l;
	}
	
	/**
	 * formats a number to 2 decimal places
	 * @param value
	 * @return
	 */
	public static String format(double value)	{
		DecimalFormat df = new DecimalFormat("#0.00");
		String result = df.format(value);
		return result;
	}
	
	/**
	 * rounds a number to 2 decimal places
	 * @param value
	 * @return
	 */
	public static double round(double value)	{
		double result = value * 100;
		result = Math.round(result);
		result = result / 100;
		return result;
	}
	
	/**
	 * Recursively list the JNDI tree, outputting to logger.error
	 */
	private static final void listContext(Context ctx, String indent) {
		//String indent="\t";
		if (ctx ==null)	{
			logger.error("null context - can't list anything");
			return;
		}
		try {
			NamingEnumeration<Binding> list = ctx.listBindings("");

			while (list.hasMore()) {
				Binding item = list.next();
				String className = item.getClassName();
				String name = item.getName();
				logger.error( indent + name+": "+ className );
				Object o = item.getObject();
				if (o instanceof javax.naming.Context) {
					Context ctx2 = (Context)o;
					logger.error("Context "+ctx2.getNameInNamespace());
					listContext(ctx2, indent + "   ");
				}
			}
		} catch (NamingException ex) {
			//logger.log(Level.WARNING, "JNDI failure: ", ex);
		}
	}
	
	/**
	 * Recursively list the JNDI tree, outputting to logger.error
	 */
	public static final void listContext(Context ctx)	{
		listContext(ctx, "");
	}
	
	
	/**
	 * <p>Calls all getters on the object passed in and puts the names and values in a Map</p>
	 * calls all methods on the payload which begin with 'get' or 'is', take no parameters, and return something other than void. <br/>
	 *  For example,
	 * if you have an object with a getId() method, and the value is 'fred', it will return a map with one key 'Id' and value 'fred'. 
	 * If it has a method 'isOpen', with value true, then the map will also contain a key 'Open' with value 'true'.<br/>
	 * NB the map values can be null
	 * @param object
	 * @return
	 */
	public static Map<String, Object> getValuesFromObject(Object object)	{
		Method m[] = object.getClass().getMethods();
		Map<String, Object> fields = new HashMap<String, Object>();
		for (Method method : m) {
			Class<?> returnType = method.getReturnType();
			if (!returnType.equals(Void.TYPE) && (method.getName().startsWith("get") || method.getName().startsWith("is"))
					&& method.getParameterTypes().length==0 )	{
				Object result = null;
				try {
					result = method.invoke(object, new Object[]{});
				} catch (Exception e) {
					logger.warn(e.getMessage(), e);
				}	//keyName calculated based on method name (getXXX or isXXX) 
				String keyName = method.getName().startsWith("get")?method.getName().substring(3):method.getName().substring(2);
				fields.put(keyName,result);
			}
		}
		return fields;
	}
	
}