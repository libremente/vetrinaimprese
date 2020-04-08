package it.interlogic.vimp.web.filter;

import javax.servlet.http.HttpSession;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@Controller
public class CompressResponseHandler
{
	@RequestMapping(value = "/_compressEnable", method = RequestMethod.GET)
	public String compressEnable(HttpSession session)
	{
		session.setAttribute(CompressResponseFilter.RESPONSE_COMPRESS,true);
		return "redirect:/search";
	}
	
	@RequestMapping(value = "/_compressDisable", method = RequestMethod.GET)
	public String compressDisable(HttpSession session)
	{
		session.setAttribute(CompressResponseFilter.RESPONSE_COMPRESS,false);
		return "redirect:/search";
	}
	

	@RequestMapping(value = "/_clearSession", method = RequestMethod.GET)
	public String clearSession(HttpSession session)
	{
		session.invalidate();
		return "redirect:/search";
	}
}
