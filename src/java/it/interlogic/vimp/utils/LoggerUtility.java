package it.interlogic.vimp.utils;

import org.apache.commons.lang.StringUtils;
import org.apache.log4j.Logger;

import com.thoughtworks.xstream.XStream;
import com.thoughtworks.xstream.io.xml.DomDriver;

public class LoggerUtility 
{

	private static final String LOGGER_NAME = "vimp.vimp";
	
	private static final XStream xstream = new XStream(new DomDriver());

	public static Logger getLogger(Class<?> classToLog)
	{
		if (classToLog==null)
			return Logger.getLogger(LOGGER_NAME);
		else
		{
			String loggerName = classToLog.getPackage().getName().substring("it.interlogic.".length());
		    return Logger.getLogger(loggerName);
		}		
	}
	
	public static void debug(String message) {
		StackTraceElement[] traceElements = new Throwable().getStackTrace();
		StackTraceElement traceElement = traceElements[1];
		String className = traceElement.getClassName();
		String methodName = traceElement.getMethodName();
		Logger logger = Logger.getLogger(LOGGER_NAME);
		if (logger.isDebugEnabled())
			logger.debug("[" + className + "::" + methodName + "] " + message);
	}

	public static void info(String message) {
		StackTraceElement[] traceElements = new Throwable().getStackTrace();
		StackTraceElement traceElement = traceElements[1];
		String className = traceElement.getClassName();
		String methodName = traceElement.getMethodName();
		Logger logger = Logger.getLogger(LOGGER_NAME);
		logger.info("[" + className + "::" + methodName + "] " + message);
	}

	public static void error(String message, Throwable ex) {
		StackTraceElement[] traceElements = new Throwable().getStackTrace();
		StackTraceElement traceElement = traceElements[1];
		String className = traceElement.getClassName();
		String methodName = traceElement.getMethodName();
		Logger logger = Logger.getLogger(LOGGER_NAME);
		logger.error("[" + className + "::" + methodName + "] " + message, ex);
		
	}

	public static void error(String message) {
		StackTraceElement[] traceElements = new Throwable().getStackTrace();
		StackTraceElement traceElement = traceElements[1];
		String className = traceElement.getClassName();
		String methodName = traceElement.getMethodName();
		Logger logger = Logger.getLogger(LOGGER_NAME);
		logger.error("[" + className + "::" + methodName + "] " + message);

	}

	public static void fatal(String message, Throwable ex) {
		StackTraceElement[] traceElements = new Throwable().getStackTrace();
		StackTraceElement traceElement = traceElements[1];
		String className = traceElement.getClassName();
		String methodName = traceElement.getMethodName();
		Logger logger = Logger.getLogger(LOGGER_NAME);
		logger.fatal("[" + className + "::" + methodName + "] " + message, ex);

	}

	public static void warn(String message) {
		StackTraceElement[] traceElements = new Throwable().getStackTrace();
		StackTraceElement traceElement = traceElements[1];
		String className = traceElement.getClassName();
		String methodName = traceElement.getMethodName();
		Logger logger = Logger.getLogger(LOGGER_NAME);
		logger.warn("[" + className + "::" + methodName + "] " + message);

	}

	/**
	 * traccia l'ingresso di un metodo su livello INFO
	 */
	public static void begin() {
		StackTraceElement[] traceElements = new Throwable().getStackTrace();
		StackTraceElement traceElement = traceElements[1];
		String className = traceElement.getClassName();
		String methodName = traceElement.getMethodName();
		Logger logger = Logger.getLogger(LOGGER_NAME);
		logger.info("[" + className + "::" + methodName + "] BEGIN");
	}

	/**
	 * traccia l'uscita di un metodo su livello INFO
	 */
	public static void end() {
		StackTraceElement[] traceElements = new Throwable().getStackTrace();
		StackTraceElement traceElement = traceElements[1];
		String className = traceElement.getClassName();
		String methodName = traceElement.getMethodName();
		Logger logger = Logger.getLogger(LOGGER_NAME);
		logger.info("[" + className + "::" + methodName + "] END");
	}
	
	public static void dumpObject (String message, Object o) {
		
		Logger logger = Logger.getLogger(LOGGER_NAME);
		if (logger.isDebugEnabled()) {
			StackTraceElement[] traceElements = new Throwable().getStackTrace();
			StackTraceElement traceElement = traceElements[1];
			String className = traceElement.getClassName();
			String methodName = traceElement.getMethodName();
			String s = xstream.toXML(o);
			logger.debug("[" + className + "::" + methodName + "] " + message + "\n" + s);
		}
	}
	
	public static String streamObject (Object o) {
		if (o == null) return "";
		return StringUtils.substring(xstream.toXML(o), 0 , 500);
	}

	public static void fatal(String message) {
		StackTraceElement[] traceElements = new Throwable().getStackTrace();
		StackTraceElement traceElement = traceElements[1];
		String className = traceElement.getClassName();
		String methodName = traceElement.getMethodName();
		Logger logger = Logger.getLogger(className);
		logger.fatal("[" + className + "::" + methodName + "] " + message);

		
	}

}
