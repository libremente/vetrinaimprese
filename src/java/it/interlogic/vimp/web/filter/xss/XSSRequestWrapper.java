package it.interlogic.vimp.web.filter.xss;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletRequestWrapper;

import org.apache.oro.text.regex.Pattern;
import org.apache.oro.text.regex.PatternMatcher;
import org.apache.oro.text.regex.Perl5Compiler;
import org.apache.oro.text.regex.Perl5Matcher;
import org.apache.oro.text.regex.StringSubstitution;
import org.apache.oro.text.regex.Util;

@SuppressWarnings("unused")
public class XSSRequestWrapper extends HttpServletRequestWrapper
{
	private PatternMatcher matcher;
	
	private Pattern patternSingleQuote;
	private Pattern patternEscapeSingleQuote;
	private StringSubstitution substitutionSingleQuote;
	
	private Pattern patternDoubleQuote;
	private Pattern patternEscapeDoubleQuote;
	private StringSubstitution substitutionDoubleQuote;
	
	private Pattern patternAlert;
	private StringSubstitution substitutionAlert;
	
	private Perl5Compiler compiler;

	public XSSRequestWrapper(HttpServletRequest servletRequest)
	{
		super(servletRequest);
		

		compiler = new Perl5Compiler();
		matcher = new Perl5Matcher();
		substitutionSingleQuote = new StringSubstitution("&rsquo;");
		substitutionDoubleQuote = new StringSubstitution("&rdquo;");
		substitutionAlert = new StringSubstitution("noalert");
		
		try
		{
			patternEscapeSingleQuote = compiler.compile("%27", Perl5Compiler.CASE_INSENSITIVE_MASK);
			patternSingleQuote = compiler.compile("[\']", Perl5Compiler.CASE_INSENSITIVE_MASK);
			
			patternEscapeDoubleQuote = compiler.compile("%22", Perl5Compiler.CASE_INSENSITIVE_MASK);
			patternDoubleQuote = compiler.compile("[\"]", Perl5Compiler.CASE_INSENSITIVE_MASK);
			
			patternAlert = compiler.compile("alert", Perl5Compiler.CASE_INSENSITIVE_MASK);
		}
		catch (Exception err)
		{
		}
	}

	public String[] getParameterValues(String parameter)
	{
		String[] values = super.getParameterValues(parameter);

		if (values == null)
		{
			return null;
		}

		int count = values.length;
		String[] encodedValues = new String[count];
		for (int i = 0; i < count; i++)
		{
			encodedValues[i] = stripXSS(values[i]);
		}

		return encodedValues;
	}

	public String getParameter(String parameter)
	{
		String value = super.getParameter(parameter);

		return stripXSS(value);
	}

	public String getHeader(String name)
	{
		String value = super.getHeader(name);
		return stripXSS(value);
	}

	private String stripXSS(String value)
	{
		if (value != null && patternAlert != null)
		{
			//value = Util.substitute(matcher, patternEscapeSingleQuote, substitutionSingleQuote, value, Util.SUBSTITUTE_ALL);
			//value = Util.substitute(matcher, patternSingleQuote, substitutionSingleQuote, value, Util.SUBSTITUTE_ALL);
			
			//value = Util.substitute(matcher, patternEscapeDoubleQuote, substitutionDoubleQuote, value, Util.SUBSTITUTE_ALL);
			//value = Util.substitute(matcher, patternDoubleQuote, substitutionDoubleQuote, value, Util.SUBSTITUTE_ALL);
			
			value = Util.substitute(matcher, patternAlert, substitutionAlert, value, Util.SUBSTITUTE_ALL);
		}
		return value;
	}
}