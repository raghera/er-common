package com.vodafone.global.er.util;

import javax.management.MBeanServer;
import javax.management.ObjectName;
import javax.resource.spi.work.Work;
import javax.resource.spi.work.WorkManager;

import org.jboss.mx.util.MBeanServerLocator;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * some utility methods for running background / async tasks <b>in jboss only</b>
 * @author matt
 *
 */
public class ThreadUtil {

	/**
	 * the JNDI name of the WorkManager to be used - defaults to jboss.jca:service=WorkManager<br/>
	 * Defined in APPSERVER_JBOSS_501/resources/startup/jboss/deploy/jca-jboss-beans.xml 
	 */
	public static String jbossWorkManagerJNDIname = "jboss.jca:service=WorkManager";
	static final Logger logger = LoggerFactory.getLogger(ThreadUtil.class);
	
	/**
	 * Trigger the asynchronous execution of a {@link Work} task by the jboss thread pool.
	 * If this method is called from outside jboss (jetty, junit, etc) the jobToRun is started in a new (asynchronous) thread.
	 * @param jobToRun 
	 * @throws RuntimeException in the event of a AttributeNotFoundException, InstanceNotFoundException, MalformedObjectNameException, MBeanException, ReflectionException, NullPointerException, WorkException
	 */
	public static void startJob(final Work jobToRun) 	{
		try	{	
			MBeanServer server = MBeanServerLocator.locateJBoss();
			WorkManager wm = (WorkManager) server.getAttribute(new ObjectName(jbossWorkManagerJNDIname), "Instance");
			wm.startWork(jobToRun); 
		} catch (IllegalStateException ise)	{
			//No 'jboss' MBeanServer found! - ie junit, jetty, etc
			logger.warn("can't run this thread via a work manager - running task [{}] Asynchronously", jobToRun);
			new Thread()	{

				@Override
				public void run() {
					jobToRun.run();
				}
				
			}.start();
			logger.warn("done");
		}	catch (Exception e){
			logger.error(e.getMessage());
			throw new RuntimeException(e);
		}
	}
	
	
	/**
	 * Trigger the asynchronous execution of a {@link Thread} by the jboss thread pool.
	 * If this method is called from outside jboss (jetty, junit, etc) the jobToRun is started in a new (asynchronous) thread.
	 * @param jobToRun 
	 * @throws RuntimeException in the event of a AttributeNotFoundException, InstanceNotFoundException, MalformedObjectNameException, MBeanException, ReflectionException, NullPointerException, WorkException
	 */
	public static void startJob(final Thread jobToRun) 	{
		
		Work work = new Work()	{

			@Override
			public void run() {
				jobToRun.start();
			}

			@Override
			public void release() {
				logger.info("release called");
			}
			
		};
		
		startJob(work); 

	}
	
	/**
	 * Trigger the asynchronous execution of a {@link Runnable} task by the jboss thread pool.
	 * If this method is called from outside jboss (jetty, junit, etc) the jobToRun is started in a new (asynchronous) thread.
	 * @param jobToRun 
	 * @throws RuntimeException in the event of a AttributeNotFoundException, InstanceNotFoundException, MalformedObjectNameException, MBeanException, ReflectionException, NullPointerException, WorkException
	 */
	public static void startJob(final Runnable jobToRun) 	{
		
		Work work = new Work()	{

			@Override
			public void run() {
				jobToRun.run();
			}

			@Override
			public void release() {
				logger.info("release called");
			}
			
		};
		
		startJob(work); 
	}
	
}
