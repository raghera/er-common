package com.vizzavi.ecommerce.common;

import java.lang.reflect.Method;
import java.net.InetAddress;
import java.net.UnknownHostException;
import java.rmi.RemoteException;
import java.text.DecimalFormat;
import java.util.*;

import javax.naming.Binding;
import javax.naming.Context;
import javax.naming.NamingEnumeration;
import javax.naming.NamingException;

import org.apache.commons.lang.StringUtils;
import org.apache.http.Header;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;


public class Utils {

	private static final Logger logger = LoggerFactory.getLogger(Utils.class);
	private static final String PREPAY		= "PRE";
	private static final String POSTPAY		= "POST";

	/**
	 * returns true if the supplied string is null, empty, 'pre', or 'post' (case-insensitive).<br/>
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
	 * @return a String representation of the local InetAddress (eg somehost.vodafone.com/145.230.58.93 ) or null in the event of an exception
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
	 * Returns a list of the calling classes in order, with consecutive duplicates removed.  Also logs it at info level in the logger of the class from which you're calling this method
	 * @param callStackDepth how far back you want to check.  
	 * @return a List<String> of class names - ie duplicate classnames are removed
	 */
	public static List<String> getCallerClassNames(int callStackDepth) {
		//TODO ideally we rewrite this using java.lang.reflect but it doesn't support getCallerClassName
		return getCallerClassNames(callStackDepth, true);
	}

	private static class MySecurityManager extends SecurityManager {
        String getCallerClassName(int callStackDepth) {
            String name= getClassContext()[callStackDepth].getSimpleName();
            return name;	//.substring(name.lastIndexOf('.')+1);
        }
    }
	
//	/**
//	 * has a performance impact
//	 * @return
//	 */
//	private static String getCallerClassName()	{
//
//		
//		//for performance of this method, see 
//		// http://stackoverflow.com/questions/421280/how-do-i-find-the-caller-of-a-method-using-stacktrace-or-reflection
//		
//		String caller="[unknown class]";
//		
//		//	REFLECTION (fastest but not cross-platform)
//		
////		int i = 2;	//don't really want to include this class in the trace
////		while (i < 10)	{	// should be enough
//// 			Class<?> thisClass=sun.reflect.Reflection.getCallerClass(i);
//// 			if (thisClass!=null){
//// 				caller =thisClass.getSimpleName();
//// 				if (caller !=null && !caller.equals("PerfLogger") && !caller.equals("BaseDAO") )
//// 					return caller;
//// 			}	
////			i++;
////		}
////		logger.info("no class found");
//		
//		// CURRENT THREAD STACKTRACE (slow)
//		
////		StackTraceElement[] stackTraceElements = Thread.currentThread().getStackTrace();
////		//start at 1 - don't really want to include this class in the trace
////		for (int i=1; i<stackTraceElements.length; i++)	{
////			String callerClassName = stackTraceElements[i].getClassName();
////			if (isNotBlank(callerClassName) && !caller.equals("PerfLogger") && !caller.equals("BaseDAO") )
////				return callerClassName;	
////		}
//		
//		//	SECURITY MANAGER (in between)
//		
//		MySecurityManager mySecurityManager =  new MySecurityManager();
//		int i = 3;	//don't really want to include this class in the trace
//		while (i < 10)	{	// should be enough
// 			caller = mySecurityManager.getCallerClassName(i);
// 				if (caller !=null && !caller.equals("PerfLogger") && !caller.equals("BaseDAO") )
// 					return caller;
//			i++;
//		}
//		
//		logger.info("no class found");
//		return caller;
//	}
	
	/**
	 * Returns a list of the calling classes in order, with consecutive duplicates removed. <br/>
	 * NB this has a performance impact
	 * @param callStackDepth how far back you want to check.  
	 * @param fullClassNames do you want com.vodafone.Class or just Class
	 * @return a List<String> of class names, of length {@code callStackDepth}.  Duplicate classnames are removed
	 */
	public static List<String> getCallerClassNames(int callStackDepth, boolean fullClassNames) {

		List<String> trace = new ArrayList<>();
		MySecurityManager mySecurityManager =  new MySecurityManager();
		callStackDepth=3;
		int i = 2;	//don't really want to include the Utils class in the trace
//		while (trace.size() < callStackDepth){
// 			Class<?> thisClass=sun.reflect.Reflection.getCallerClass(i);
//			if(thisClass != null	//now check that the last one in the list is not the same
//				&& ((trace.size()==0 ) || 
//				!(trace.get(trace.size()-1).contains(thisClass.getSimpleName()))))	{
//				if (fullClassNames)
//					trace.add(sun.reflect.Reflection.getCallerClass(i).getName());
//				else
//					trace.add(sun.reflect.Reflection.getCallerClass(i).getSimpleName());
//				
//			}	//else
//				//break;
//			i++;
//		}
		while (trace.size() < callStackDepth)	{	// should be enough
 			String caller = mySecurityManager.getCallerClassName(i);
 			if (caller !=null && //now check that the last one in the list is not the same
 					((trace.size()==0 ) || 
 							!(trace.get(trace.size()-1).equals(caller))))	{
 				trace.add(caller);
 			}
			i++;
		}

		return trace;
	}


	/**
	 * Throws an exception some of the time.  Use for testing only ... obviously.
	 * @param percentage what percentage of the time you want to throw an exception
	 * @throws RuntimeException at random
	 */
	public static void throwExceptionSometimes(int percentage)	{
		logger.error("shall I throw an exception?  Hmmm. maybe. {} times out of 100", percentage);
		int result = new Random().nextInt(100);	//number between 0 and 100
		if (result < percentage){
			logger.error("throwing a random exception!");
			throw new RuntimeException("random exception");
		}
		logger.error("I decided not to throw an exception");
	}
	
	public static void throwRemoteExceptionSometimes(int percentage) throws RemoteException	{
		try {
			throwExceptionSometimes(percentage);
		} catch (Throwable e) {
			throw new RemoteException();
		}
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
		return StringUtils.join(thingy, separator);
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
	 * Concatenates an array into a String, separating tokens with a single space.  Uses toString() on each object.
	 * Returns zero-length string if the array is null or empty.
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
	 * Concatenates an array into a String, separating tokens with the separator supplied.  Uses toString() on each object.
	 * Returns zero-length string if the array is null or empty.
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
		Set<T> intersection = new HashSet<>(list1);
	    intersection.retainAll(new HashSet<>(list2));
	    return new ArrayList<>(intersection);
	}
	
	/**
	 * does what it says on the tin...
	 * @param list
	 * @return
	 */
	public static <T> List<T> removeDuplicatesFromList(List<T> list){

		Set<T> set = new TreeSet<>(list);
		List<T> rv = new ArrayList<>(set);
		return rv;
	}
	
	/**
	 * splits a comma-separated list of tokens to a List&lt;String><br/>
	 * Returns an empty list if a null or empty string is passed in.  Will trim each string for whitespace
	 * @param tokens
	 * @return
	 */
	public static List<String> parseList(String tokens){
		List<String> l = new ArrayList<>();
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
		Set<String> l = new HashSet<>();
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
	 * WARNING THIS METHOD IS PRETTY SLOW!
	 * <br/>Recursively list the JNDI tree, outputting to logger.error
	 */
	public static final void listContext(Context ctx)	{
		listContext(ctx, "");
	}
	
	
	/**
	 * <p>Calls all getters on the object passed in and puts the names and values in a Map</p>
	 * calls all methods on the object which begin with 'get' or 'is', take no parameters, and return something other than void. <br/>
	 *  For example,
	 * if you have an object with a getId() method, and the value is 'fred', it will return a map with one key 'Id' and value 'fred'. 
	 * If it has a method 'isOpen', with value true, then the map will also contain a key 'Open' with value 'true'.<br/>
	 * NB the map values can be null
	 * @param object
	 * @return
	 */
	public static Map<String, Object> getValuesFromObject(Object object)	{
		Method m[] = object.getClass().getMethods();
		Map<String, Object> fields = new HashMap<>();
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
	
	/**
	 * join a series of Lists of anything
	 * @param <T>
	 * @param lists
	 * @return
	 */
	@SafeVarargs
	public static <T> List<T> union(List<? extends T>... lists){
		List<T> result = new ArrayList<>();
		if (lists !=null && lists.length>0)	{
			for (List<? extends T> list: lists){
				result.addAll(list);
			}
		}
		return result;
	}
	
	/**
	 * chop a list up into a series of lists, each of maximum size L
	 * @param list
	 * @param length maximum length of each list
	 * @return a list of lists, each of max length L
	 */
	public static <T> List<List<T>> chop(List<T> list, final int length) {
	    List<List<T>> parts = new LinkedList<>();
	    final int N = list.size();
	    for (int i = 0; i < N; i += length) {
	        parts.add(new LinkedList<>(
	            list.subList(i, Math.min(N, i + length)))
	        );
	    }
	    return parts;
	}
	
}