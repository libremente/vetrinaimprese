package it.interlogic.vimp.web.filter;

import java.io.IOException;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.googlecode.htmlcompressor.compressor.HtmlCompressor;

@WebFilter(filterName = "CompressResponseFilter", urlPatterns = { "/*" })
public class CompressResponseFilter implements Filter
{
	private HtmlCompressor compressor;
	public static final String RESPONSE_COMPRESS = "RESPONSE_COMPRESS";

	@Override
	public void doFilter(ServletRequest req, ServletResponse resp, FilterChain chain) throws IOException, ServletException
	{
		boolean compress = false;
		try
		{
			HttpSession session = ((HttpServletRequest) req).getSession();
			if (session != null)
				compress = (Boolean) session.getAttribute(RESPONSE_COMPRESS);
			
			// TODO COMPRESSIONE
			compress = false;
			
		}
		catch (Exception err)
		{
			compress = false;
		}

		CharResponseWrapper responseWrapper = new CharResponseWrapper((HttpServletResponse) resp);
		chain.doFilter(req, responseWrapper);
		String servletResponse = new String(responseWrapper.toString());
		try
		{
			if (compress)
				resp.getWriter().write(compressor.compress(servletResponse));
			else
				resp.getWriter().write(servletResponse);
		}
		catch (Exception err)
		{
			//resp.getWriter().write(servletResponse);
		}
	}

	@Override
	public void init(FilterConfig config) throws ServletException
	{
		compressor = new HtmlCompressor();
		compressor.setCompressCss(true);
		compressor.setCompressJavaScript(true);
	}

	@Override
	public void destroy()
	{
	}

}