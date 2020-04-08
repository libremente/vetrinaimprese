package it.interlogic.vimp.web.filter.xss;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.Enumeration;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.log4j.Logger;
import org.apache.oro.text.regex.MalformedPatternException;
import org.apache.oro.text.regex.Pattern;
import org.apache.oro.text.regex.PatternCompiler;
import org.apache.oro.text.regex.PatternMatcher;
import org.apache.oro.text.regex.Perl5Compiler;
import org.apache.oro.text.regex.Perl5Matcher;

/**
 * Anti cross-site scripting (XSS) filter by AM
 * 
 * JDK 1.3+
 * 
 * dependency : Jakarta ORO
 * 
 * ivy.xml:
 * 
 * <dependency org="apache" name="jakarta-oro" rev="2.0.8"> <artifact
 * name="jakarta-oro-2.0.8" type="jar" /> </dependency>
 * 
 * 
 * web.xml
 * 
 * <filter> <filter-name>XSSFilter</filter-name>
 * <filter-class>it.csi.sipe.util.XSSFilter</filter-class> </filter>
 * <filter-mapping> <filter-name>XSSFilter</filter-name>
 * <servlet-name>action</servlet-name> <url-pattern>*.do</url-pattern>
 * </filter-mapping>
 *
 * @author Agostino Moronese
 * @version 1.0
 * @since 2016-02-04
 */
public class XSSFilter implements Filter
{
	@SuppressWarnings("unused")
	private static final Logger LOGGER = Logger.getLogger(XSSFilter.class);

	private static final String SQL_INJECTION_REGEX = "[\\t\\r\\n]|(--[^\\r\\n]*)|(\\/\\*[\\w\\W]*?(?=\\*)\\*\\/)";

	public static boolean testSQLInjection(String string)
	{
		try
		{
			PatternCompiler compiler = new Perl5Compiler();
			PatternMatcher matcher = new Perl5Matcher();

			Pattern scriptPattern = compiler.compile(SQL_INJECTION_REGEX, Perl5Compiler.CASE_INSENSITIVE_MASK);
			boolean match = matcher.contains(string, scriptPattern);
			if (match) return true;

			scriptPattern = compiler.compile(" and ", Perl5Compiler.CASE_INSENSITIVE_MASK);
			match = matcher.contains(string, scriptPattern);
			if (match) return true;

			scriptPattern = compiler.compile(" or ", Perl5Compiler.CASE_INSENSITIVE_MASK);
			match = matcher.contains(string, scriptPattern);
			if (match) return true;
		}
		catch (MalformedPatternException ex)
		{
			ex.printStackTrace();
			// throw new IOException("XSS attack!");
			return true;

		}
		return false;
	}

	public XSSFilter()
	{
	}

	public void init(FilterConfig fConfig) throws ServletException
	{
		// implementation method
	}

	public void destroy()
	{
		// implementation method
	}

	public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException
	{
		// LOGGER.fatal("XSS FILTER IN EXECUTION");
		HttpServletRequest httpServletRequest = (HttpServletRequest) request;

		String method = httpServletRequest.getMethod();
		if (!"post".equalsIgnoreCase(method) && !"get".equalsIgnoreCase(method))
		{
			((HttpServletResponse) response).sendError(403);
		}
		else
		{
			if (testXSS(httpServletRequest)) doFilterError(request, response);
			else
				chain.doFilter(request, response);
		}
	}

	// ===========================================================================
	private void doFilterError(ServletRequest servletRequest, ServletResponse servletResponse) throws IOException, ServletException
	{
		servletResponse.setContentType("text/html");
		PrintWriter out = servletResponse.getWriter();
		out.println("<br/><br/>Anti cross-site scripting (XSS) filter by AM<br/><br/>");
	}

	private boolean testXSS(HttpServletRequest servletRequest)
	{
		try
		{
			String paramValue = null;
			Enumeration<?> enumeration = servletRequest.getParameterNames();
			while (enumeration.hasMoreElements())
			{
				String element = (String) enumeration.nextElement();

				if (textXSS(element)) return true;

				paramValue = servletRequest.getParameter(element);
				// LOGGER.fatal(element + " => " + paramValue);

				if (textXSS(paramValue)) return true;
			}

			String headerValue = null;
			enumeration = servletRequest.getHeaderNames();
			while (enumeration.hasMoreElements())
			{
				String elementHeader = (String) enumeration.nextElement();

				if (textXSS(elementHeader)) return true;

				headerValue = servletRequest.getParameter(elementHeader);

				if (textXSS(headerValue)) return true;
			}

			String jSessionIdValue = servletRequest.getRequestedSessionId();
			// LOGGER.fatal("jSessionIdValue => " + jSessionIdValue);
			if (textXSS(jSessionIdValue)) return true;
		}
		catch (IOException ex)
		{
			return true;
		}

		return false;
	}

	private boolean textXSS(String valueInput) throws IOException
	{
		if (valueInput != null)
		{
			String value = valueInput;
			value = value.replaceAll("\n", "");
			value = value.replaceAll("`", "'");
			value = value.replaceAll(" ", "");
			value = value.replace("	", "");

			// System.out.println(value);

			try
			{
				// Avoid null characters

				PatternCompiler compiler = new Perl5Compiler();
				PatternMatcher matcher = new Perl5Matcher();

				// Avoid anything between script tags
				Pattern scriptPattern = compiler.compile("<script>(.*?)</script>", Perl5Compiler.CASE_INSENSITIVE_MASK);
				boolean match = matcher.contains(value, scriptPattern);
				if (match) return true;
				// LOGGER.fatal("Value (\"" + value + "\")Does not match: " +
				// "<script>(.*?)</script>");

				// Avoid anything in a src='...' type of expression
				scriptPattern = compiler.compile("src[\r\n]*=[\r\n]*\\\'(.*?)\\\'", Perl5Compiler.CASE_INSENSITIVE_MASK | Perl5Compiler.MULTILINE_MASK);
				match = matcher.contains(value, scriptPattern);
				if (match) return true;
				// LOGGER.fatal("Value (\"" + value + "\")Does not match: " +
				// "src[\r\n]*=[\r\n]*\\\'(.*?)\\\'");

				scriptPattern = compiler.compile("src[\r\n]*=[\r\n]*\\\"(.*?)\\\"", Perl5Compiler.CASE_INSENSITIVE_MASK | Perl5Compiler.MULTILINE_MASK);
				match = matcher.contains(value, scriptPattern);
				if (match) return true;
				// LOGGER.fatal("Value (\"" + value + "\")Does not match: " +
				// "src[\r\n]*=[\r\n]*\\\"(.*?)\\\"");

				// Remove any lonesome </script> tag
				scriptPattern = compiler.compile("</script>", Perl5Compiler.CASE_INSENSITIVE_MASK | Perl5Compiler.MULTILINE_MASK);
				match = matcher.contains(value, scriptPattern);
				if (match) return true;
				// LOGGER.fatal("Value (\"" + value + "\")Does not match: " +
				// "</script>");

				// Remove any lonesome <script ...> tag
				scriptPattern = compiler.compile("<script(.*?)>", Perl5Compiler.CASE_INSENSITIVE_MASK | Perl5Compiler.MULTILINE_MASK);
				match = matcher.contains(value, scriptPattern);
				if (match) return true;
				// LOGGER.fatal("Value (\"" + value + "\")Does not match: " +
				// "<script(.*?)>");

				// Remove any lonesome <script ...> tag
				scriptPattern = compiler.compile("</script", Perl5Compiler.CASE_INSENSITIVE_MASK | Perl5Compiler.MULTILINE_MASK);
				match = matcher.contains(value, scriptPattern);
				if (match) return true;
				// LOGGER.fatal("Value (\"" + value + "\")Does not match: " +
				// "</script");

				// Avoid eval(...) expressions
				scriptPattern = compiler.compile("eval\\((.*?)\\)", Perl5Compiler.CASE_INSENSITIVE_MASK | Perl5Compiler.MULTILINE_MASK);
				match = matcher.contains(value, scriptPattern);
				if (match) return true;
				// LOGGER.fatal("Value (\"" + value + "\")Does not match: " +
				// "eval\\((.*?)\\)");

				// Avoid expression(...) expressions
				scriptPattern = compiler.compile("expression\\((.*?)\\)", Perl5Compiler.CASE_INSENSITIVE_MASK | Perl5Compiler.MULTILINE_MASK);
				match = matcher.contains(value, scriptPattern);
				if (match) return true;
				// LOGGER.fatal("Value (\"" + value + "\")Does not match: " +
				// "expression\\((.*?)\\)");

				// Avoid expression(...) xss
				scriptPattern = compiler.compile("xss:.*?\\((.*?)\\)", Perl5Compiler.CASE_INSENSITIVE_MASK | Perl5Compiler.MULTILINE_MASK);
				match = matcher.contains(value, scriptPattern);
				if (match) return true;
				// LOGGER.fatal("Value (\"" + value + "\")Does not match: " +
				// "xss:.*?\\((.*?)\\)");

				// Avoid expression(...) iFrame
				scriptPattern = compiler.compile("iframe.*?", Perl5Compiler.CASE_INSENSITIVE_MASK | Perl5Compiler.MULTILINE_MASK);
				match = matcher.contains(value, scriptPattern);
				if (match) return true;
				// LOGGER.fatal("Value (\"" + value + "\")Does not match: " +
				// "iframe.*?");

				// Avoid javascript:... expressions
				scriptPattern = compiler.compile("javascript:", Perl5Compiler.CASE_INSENSITIVE_MASK);
				match = matcher.contains(value, scriptPattern);
				if (match) return true;
				// LOGGER.fatal("Value (\"" + value + "\")Does not match: " +
				// "javascript:");

				// Avoid vbscript:... expressions
				scriptPattern = compiler.compile("vbscript:", Perl5Compiler.CASE_INSENSITIVE_MASK);
				match = matcher.contains(value, scriptPattern);
				if (match) return true;
				// LOGGER.fatal("Value (\"" + value + "\")Does not match: " +
				// "vbscript:");

				// Avoid onload= expressions
				scriptPattern = compiler.compile("onload(.*?)=", Perl5Compiler.CASE_INSENSITIVE_MASK | Perl5Compiler.MULTILINE_MASK);
				match = matcher.contains(value, scriptPattern);
				if (match) return true;
				// LOGGER.fatal("Value (\"" + value + "\")Does not match: " +
				// "onload(.*?)=");

				// Avoid onmouseover
				scriptPattern = compiler.compile("onmouse*(.*?)=", Perl5Compiler.CASE_INSENSITIVE_MASK | Perl5Compiler.MULTILINE_MASK);
				match = matcher.contains(value, scriptPattern);
				if (match) return true;
				// LOGGER.fatal("Value (\"" + value + "\")Does not match: " +
				// "onmouse*(.*?)=");

				// Avoid onfocus
				scriptPattern = compiler.compile("onfocus(.*?)=", Perl5Compiler.CASE_INSENSITIVE_MASK | Perl5Compiler.MULTILINE_MASK);
				match = matcher.contains(value, scriptPattern);
				if (match) return true;
				// LOGGER.fatal("Value (\"" + value + "\")Does not match: " +
				// "onfocus(.*?)=");

				// Avoid alert
				scriptPattern = compiler.compile("windows", Perl5Compiler.CASE_INSENSITIVE_MASK | Perl5Compiler.MULTILINE_MASK);
				match = matcher.contains(value, scriptPattern);
				if (match) return true;

				// Avoid alert
				scriptPattern = compiler.compile("window", Perl5Compiler.CASE_INSENSITIVE_MASK | Perl5Compiler.MULTILINE_MASK);
				match = matcher.contains(value, scriptPattern);
				if (match) return true;

				// Avoid alert
				scriptPattern = compiler.compile("@import", Perl5Compiler.CASE_INSENSITIVE_MASK | Perl5Compiler.MULTILINE_MASK);
				match = matcher.contains(value, scriptPattern);
				if (match) return true;
				// LOGGER.fatal("Value (\"" + value + "\")Does not match: " +
				// "@import");

				scriptPattern = compiler.compile("%40import", Perl5Compiler.CASE_INSENSITIVE_MASK | Perl5Compiler.MULTILINE_MASK);
				match = matcher.contains(value, scriptPattern);
				if (match) return true;
				// LOGGER.fatal("Value (\"" + value + "\")Does not match: " +
				// "%40import");

			}
			catch (MalformedPatternException ex)
			{
				ex.printStackTrace();
				// throw new IOException("XSS attack!");
				return true;

			}
			finally
			{
			}
		}
		return false;
	}

	public static void main_(String[] args) throws Exception
	{

		String valueInput = ";jsessionid=BA2EFC6A6443689F01913E07420C1F65.instance502/?\"><script/>eval(/ale/.source+/rt/.source+/(2290)/.source)</script>?cod_servizio=EOPEWEB&fromPortal=INTERNET_RUPAR&idx=0";

		XSSFilter xss = new XSSFilter();

		boolean res = xss.textXSS(valueInput);

		System.out.println(res);

	}

	public static void main(String[] args) throws Exception
	{
		String valueInput = "vistrorio%25%27+and+%27f%25%27%3D%27f";

		boolean res = testSQLInjection(valueInput);

		if (!res)
		{

			PatternCompiler compiler = new Perl5Compiler();
			PatternMatcher matcher = new Perl5Matcher();

			// Avoid anything between script tags
			Pattern scriptPattern = compiler.compile("and", Perl5Compiler.CASE_INSENSITIVE_MASK);
			res = matcher.contains(valueInput, scriptPattern);

			scriptPattern = compiler.compile("or", Perl5Compiler.CASE_INSENSITIVE_MASK);
			res = matcher.contains(valueInput, scriptPattern);
		}

		System.out.println(res);

	}

}