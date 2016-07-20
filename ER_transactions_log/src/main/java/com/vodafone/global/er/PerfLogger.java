package com.vodafone.global.er;

import java.util.HashMap;
import java.util.Map;

import org.perf4j.log4j.Log4JStopWatch;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.vodafone.global.er.translog.TransLogManager.Attr;
import com.vodafone.global.er.translog.TransLogManagerFactory;
import static org.apache.commons.lang.StringUtils.isNotBlank;

/**
 * A Performance Logger.  <br/>
 * Use this to measure & log performance, primarily of external calls (to database, http calls, etc).  
 * <p>As per enhancement request MQC 7665, the code being measured should be atomic.  For example, if a single method calls the database twice, each call should be timed separately. 
 *  In the call to the {@link #start(Type, String, Object)} method, supply a Type identifier (ie is this a database call,
 *  an http call, some internal measurement etc), and some unique identifier (eg msisdn, accountId) to help debugging</p>
 * This class is a wrapper for the perf4j framework which allows us to change the implementation if necessary
 * @author matt
 *
 */
public class PerfLogger {

	//private static final ThreadLocal<Log4JStopWatch> timers = new ThreadLocal<Log4JStopWatch>();
	private static final Logger logger = LoggerFactory.getLogger(PerfLogger.class);
	
	private final static ThreadLocal<Map<String, Log4JStopWatch>> timerMap = new ThreadLocal<>();
	
	/**What kind of log entry - eg database call, http call, etc */
	public enum Type{/** database calls*/DB, /** decoupling calls*/DI, /** AAA calls*/AAA,
		/** internal call - eg priceplan loading*/INT, HTTP, OTHER}
	
	private PerfLogger() {
		//we don't want people to instantiate this class
	}
	
	/**
	 * Starts a perf4j stopwatch to log in the performance log.  The log entry will consist of the methodName passed in prepended with the calling class, and the execution time.<br/>
	 * <b>Don't forget to call {@link stop()} to stop the timer</b>, preferably in the finally block of your code<br/>
	 * NB The methodName <b>must be unique</b> within the class.  If your calling method is overloaded, use a different methodName for each. <br/>
	 * <b>You can't start timing 2 methods simultaneously in the same class</b>.  For example, if you start timing method A, then method A calls method B, you can't have timing in method B as well (if they are both in the same class). 
	 * @param type what {@link Type}  of thing we are timing: a db call? a decoupling call? etc 
	 * @param methodName the String you want to appear in the log file.  Class name will be prepended (ie pass in 'update' and it will show up as 'AccountDaoImpl.update')
	 * @param uniqueId a unique identifier for debugging - eg msisdn, accountId, subscriptionId, etc.  can be primitive or object
	 * @see {@link #lapTime()}
	 * @see {@link #stop()}
	 */
	public static void start(Type type, String methodName, Object uniqueId){
		start(null, type, methodName, uniqueId);
	}
	
	
	/**
	 * Starts a perf4j stopwatch to log in the performance log.  The log entry will consist of the methodName passed in prepended with the calling class, and the execution time.<br/>
	 * <b>Don't forget to call {@link stop()} to stop the timer</b>, preferably in the finally block of your code<br/>
	 * NB The methodName <b>must be unique</b> within the class.  If your calling method is overloaded, use a different methodName for each. <br/>
	 * <b>You can't start timing 2 methods simultaneously in the same class</b>.  For example, if you start timing method A, then method A calls method B, you can't have timing in method B as well (if they are both in the same class). 
	 * @param caller the calling class (pass in 'this')
	 * @param methodName the String you want to appear in the log file.  Class name will be prepended (ie pass in 'update' and it will show up as 'AccountDaoImpl.update')
	 * @param uniqueId a unique identifier for debugging - eg msisdn, accountId, subscriptionId, etc.  can be primitive or object
	 * @param type what {@link Type}  of thing we are timing: a db call? a decoupling call? etc 
	 * @see {@link #lapTime()}
	 * @see {@link #stop()}
	 */
	public static void start(Object caller, Type type, String methodName, Object uniqueId){
		
		if (methodName == null)
			throw new IllegalArgumentException("methodName is required");
		String msisdn =TransLogManagerFactory.getInstance().getAttribute(Attr.CUSTOMER_ID);
		String extTxId =TransLogManagerFactory.getInstance().getAttribute(Attr.VF_EXT_TRACE_ID);
		String extbpId =TransLogManagerFactory.getInstance().getAttribute(Attr.VF_EXT_BP_ID);

		String callerClass=getCallerClassName();

		String tag = type+"; "+callerClass + "."+ methodName;
		StringBuilder message = new StringBuilder();
		message.append(uniqueId);
		if (isNotBlank(msisdn))
			message.append("; msisdn=").append(msisdn);
		if (isNotBlank(extTxId))
			message.append("; xtid=").append(extTxId);
		if (isNotBlank(extbpId))
			message.append("; extbpId=").append(extbpId);		
//		String message = uniqueId+"; msisdn="+msisdn+"; xtid="+extTxId+"; extbpId="+extbpId;
		Log4JStopWatch timer = getTimer(callerClass);
		if (timer == null)	{
			setTimer(callerClass, new Log4JStopWatch(tag, message.toString()));
		}	else	{	
			logger.warn("trying to start a new timer from {}.{} without first stopping the existing one [{}]", new Object[]{callerClass, methodName, timer.getTag()});
			timer.start(tag, message.toString());
		}
	}
	
	private static void setTimer(String className, Log4JStopWatch log4jStopWatch) {
	
		Map<String, Log4JStopWatch> stopWatchesOnThisThread=timerMap.get();
		if (stopWatchesOnThisThread==null)	{
			stopWatchesOnThisThread = new HashMap<>();
		}
		stopWatchesOnThisThread.put(className, log4jStopWatch);
		timerMap.set(stopWatchesOnThisThread);

	}

	/**
	 * Starts a perf4j stopwatch to log in the performance log.  The log entry will consist of the methodName passed in prepended with the calling class, and the execution time.<br/>
	 * <b>Don't forget to call {@link stop()} to stop the timer</b>, preferably in the finally block of your code<br/>
	 * NB The methodName <b>must be unique</b> within the class.  If your calling method is overloaded, use a different methodName for each. <br/>
	 * <b>You can't start timing 2 methods simultaneously in the same class</b>.  For example, if you start timing method A, then method A calls method B, you can't have timing in method B as well (if they are both in the same class). 
	 * @param caller the calling class (pass in 'this')
	 * @param methodName the String you want to appear in the log file.  Class name will be prepended (ie pass in 'update' and it will show up as 'AccountDaoImpl.update')
	 * @param type what {@link Type}  of thing we are timing: a db call? a decoupling call? etc 
	 * @see {@link #lapTime()}
	 * @see {@link #stop()}
	 */
	public static void start(Object caller, Type type, String methodName){
		start(caller, type, methodName, null);
	}
	
	/**
	 * Starts a perf4j stopwatch to log in the performance log.  The log entry will consist of the methodName passed in prepended with the calling class, and the execution time.<br/>
	 * <b>Don't forget to call {@link stop()} to stop the timer</b>, preferably in the finally block of your code<br/>
	 * NB The methodName <b>must be unique</b> within the class.  If your calling method is overloaded, use a different methodName for each. <br/>
	 * <b>You can't start timing 2 methods simultaneously in the same class</b>.  For example, if you start timing method A, then method A calls method B, you can't have timing in method B as well (if they are both in the same class). 
	 * @param methodName the String you want to appear in the log file.  Class name will be prepended (ie pass in 'update' and it will show up as 'AccountDaoImpl.update')
	 * @param type what {@link Type}  of thing we are timing: a db call? a decoupling call? etc 
	 * @see {@link #lapTime()}
	 * @see {@link #stop()}
	 */	
	public static void start(Type type, String methodName){
		start(null, type, methodName, null);

	}

	
	/**
	 * Stops the perf4j stopwatch you started, appending the new message to the existing message.
	 * @param caller the calling class (pass in 'this')
	 */
	public static void stop(Object caller)	{
		stop(caller, null);
	}
	
	/**
	 * Stops the perf4j stopwatch you started. 
	 * @throws NullPointerException if you haven't started a stopwatch yet (try calling {@link #start(Type, String, Object)} first)
	 */
	public static void stop()	{
		stop(null, null);
	}
	
	/**
	 * Stops the perf4j stopwatch you started, appending the new message to the existing message.
	 * @param message use this to put info about the timing breakdown
	 */
	public static void stop(String message)	{
		stop(null, message);
	}
	
	/**
	 * Stops the perf4j stopwatch you started, appending the new message to the existing message.
	 *  @param caller the calling class (pass in 'this')
	 *  @param message use this to put info about the timing breakdown
	 */
	public static void stop(Object caller, String message)	{
		String className=getCallerClassName();
		Log4JStopWatch timer = getTimer(className);
		if (timer!=null)	{
			timer.stop(timer.getTag(), message==null?timer.getMessage():timer.getMessage()+"; "+message);
			removeTimer(className);
		}	else	{
			logger.warn("calling stop without first calling start - see details at info level");
			Exception e = new Exception("calling stop without first calling start");
			logger.info(e.getMessage(),e);
		}
	}
	
	/**
	 * call this if you want to break down timing info - eg during looping, or different DB operations in a single method, etc
	 * @param message use this to put info about the timing breakdown e.g. "finished loading durations"
	 */
	public static void lap(String message)	{
		lap(null, message);
	}
	
	/**
	 * call this if you want to break down timing info - eg during looping, or different DB operations in a single method, etc
	 *  @param caller the calling class (pass in 'this')
	 *  @param message use this to put info about the timing breakdown e.g. "finished loading durations"
	 */
	public static void lap(Object caller, String message)	{
		Log4JStopWatch timer = getTimer();
		if (timer!=null)	{
			String originalMessage = timer.getMessage();
			timer.lap(timer.getTag(), originalMessage+"; "+ message);
			//re-instate it
			timer.setMessage(originalMessage);
		}
		else	{
			logger.warn("calling lap without first calling start - see details at info level");
			Exception e = new Exception("calling lap without first calling start");
			logger.info(e.getMessage(),e);	//for the stack trace
		}
	}	
	
	
	/**
	 * has a performance impact
	 * @return
	 */
	private static String getCallerClassName()	{

		
		//for performance of this method, see 
		// http://stackoverflow.com/questions/421280/how-do-i-find-the-caller-of-a-method-using-stacktrace-or-reflection
		
		String caller="[unknown class]";
		
		//	REFLECTION (fastest but not cross-platform)
		
//		int i = 2;	//don't really want to include this class in the trace
//		while (i < 10)	{	// should be enough
// 			Class<?> thisClass=sun.reflect.Reflection.getCallerClass(i);
// 			if (thisClass!=null){
// 				caller =thisClass.getSimpleName();
// 				if (caller !=null && !caller.equals("PerfLogger") && !caller.equals("BaseDAO") )
// 					return caller;
// 			}	
//			i++;
//		}
//		logger.info("no class found");
		
		// CURRENT THREAD STACKTRACE (slow)
		
//		StackTraceElement[] stackTraceElements = Thread.currentThread().getStackTrace();
//		//start at 1 - don't really want to include this class in the trace
//		for (int i=1; i<stackTraceElements.length; i++)	{
//			String callerClassName = stackTraceElements[i].getClassName();
//			if (isNotBlank(callerClassName) && !caller.equals("PerfLogger") && !caller.equals("BaseDAO") )
//				return callerClassName;	
//		}
		
		//	SECURITY MANAGER (in between)
		
		MySecurityManager mySecurityManager =  new MySecurityManager();
		int i = 3;	//don't really want to include this class in the trace
		while (i < 10)	{	// should be enough
 			caller = mySecurityManager.getCallerClassName(i);
 				if (caller !=null && !caller.equals("PerfLogger") && !caller.equals("BaseDAO") )
 					return caller;
			i++;
		}
		
		logger.info("no class found");
		return caller;
	}

	private static class MySecurityManager extends SecurityManager {
        public String getCallerClassName(int callStackDepth) {
            String name= getClassContext()[callStackDepth].getName();
            return name.substring(name.lastIndexOf('.')+1);
        }
    }

	private static Log4JStopWatch getTimer(String className){
		//this is a bit messy.  We need to store these stopwatches so they can be retrieved
		//but if we use a threadlocal stopwatches, and a single thread starts 2 one after the other, 
		//the first one is lost.
		//so we need a map with a 'compound key' - a threadlocal map indexed by caller class
		//(so we can't have 2 concurrent stopwatches in the same thread, in the same class)
		
		Map<String, Log4JStopWatch> stopWatchesOnThisThread=timerMap.get();
		if (stopWatchesOnThisThread==null)	{
			stopWatchesOnThisThread = new HashMap<>();
			timerMap.set(stopWatchesOnThisThread);
		}
		Log4JStopWatch timer= stopWatchesOnThisThread.get(className);
		
		return timer;
	}
	
	/**
	 * has a performance hit
	 * @return
	 */
	private static Log4JStopWatch getTimer(){
		return getTimer(getCallerClassName());
	}
	
	
	
	private static void removeTimer(String className)	{
		Map<String, Log4JStopWatch> stopWatchesOnThisThread=timerMap.get();
		if (stopWatchesOnThisThread==null)
			return;
		
		Log4JStopWatch timer = stopWatchesOnThisThread.get(className);
		if (timer == null)
			return;
		
		stopWatchesOnThisThread.remove(className);
	}
	
//	/**
//	 * remove a timer from memory.  has a performance hit
//	 * @param methodName
//	 */
//	private static void removeTimer()	{
//		removeTimer(getCallerClassName());
//	}
	
}
