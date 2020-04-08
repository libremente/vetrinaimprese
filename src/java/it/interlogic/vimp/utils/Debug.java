package it.interlogic.vimp.utils;

import java.io.PrintWriter;
import java.io.StringWriter;
import java.io.Writer;
import java.util.StringTokenizer;

import org.apache.log4j.Logger;


public final class Debug {

	public static final String APPLICATION_CODE = "batchVimp";
	  /**
	   * Gets the last stack.
	   *
	   * @return the last stack
	   */
	  private static String getLastStack() {
		final Writer stw = new StringWriter();
		new Throwable("").printStackTrace(new PrintWriter(stw));
		final String stackTrace = stw.toString();
		final StringTokenizer st = new StringTokenizer(stackTrace, "\n");
		String lastMethodCalled ="";
		if(st.countTokens() > 3) {
		  lastMethodCalled = st.nextToken();
		  lastMethodCalled = st.nextToken();
		  lastMethodCalled = st.nextToken();
		  lastMethodCalled = st.nextToken();
		  lastMethodCalled = st.nextToken();
		  lastMethodCalled = st.nextToken();
		  if(lastMethodCalled.length() > 0)
			lastMethodCalled = lastMethodCalled.substring(0,lastMethodCalled.length()-1).trim();
		  if(lastMethodCalled.length() > 3)
			lastMethodCalled = lastMethodCalled.substring(3,lastMethodCalled.length());

		  return lastMethodCalled.trim();
		} else {
		  return "";
		}
	  }

 
  /**
   * Prints the.
   *
   * @param e the e
   */
  public static void print(Exception e) {
	print(e, null);
  }

  /**
   * Prints the.
   *
   * @param e the e
   * @param msg the msg
   */
  public static void print(Exception e, String msg) {
	print( (Throwable) e, msg);
  }

  /**
   * Prints the.
   *
   * @param msg the msg
   */
  public static void print(String msg) {
	final String fullClassName = getLastStack().trim();
	final Logger logger = getLogger(fullClassName);
	logger.debug(msg);
  }



  /**
   * Prints the.
   *
   * @param t the t
   */
  public static void print(Throwable t) {
	print(t, null);
  }

  /**
   * Prints the.
   *
   * @param t the t
   * @param msg the msg
   */
  public static void print(Throwable t, String msg) {
	final String fullClassName = getLastStack().trim();
	final Logger logger = getLogger(fullClassName);
	logger.error("Received ChainedException with Message: " + t.getMessage());
	if (msg != null) {
	  logger.error(t);
	}
  }

  /**
   * Prints the debug.
   *
   * @param msg the msg
   * @param srcPath the src path
   */
  public static void printDebug(String msg, String srcPath) {
	final Logger logger = getLogger(srcPath);
	logger.debug(msg);
  }

  /**
   * Prints the error.
   *
   * @param msg the msg
   * @param srcPath the src path
   */
  public static void printError(String msg, String srcPath) {
	final Logger logger = Logger.getLogger(APPLICATION_CODE + "." + srcPath);
	logger.error(msg);
  }
  public static void printError(String msg, Throwable t, String srcPath) {
		final Logger logger = getLogger(srcPath);
		logger.error(msg, t);
	  }
  /**
   * Prints the info.
   *
   * @param msg the msg
   * @param srcPath the src path
   */
  public static void printInfo(String msg, String srcPath) {
	final Logger logger = getLogger(srcPath);
	logger.info(msg);
  }

  /**
   * Println.
   */
  public static void println() {
	print("\n");
  }

  /**
   * Println.
   *
   * @param msg the msg
   */
  public static void println(String msg) {
	print(msg);
  }


  /**
   * Instantiates a new debug.
   */
  private Debug(){
	  //solo per non rendere creabile istanze di questa classe
  }
  
  private static Logger getLogger(String srcPath)
  {
	  //return Logger.getLogger(APPLICATION_CODE + "." + srcPath);
	  return Logger.getLogger(APPLICATION_CODE);
  }
}
