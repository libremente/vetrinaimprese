package it.interlogic.vimp.web.tags;

import java.io.IOException;
import java.util.HashMap;

import javax.servlet.ServletContext;
import javax.servlet.jsp.JspException;
import javax.servlet.jsp.JspWriter;
import javax.servlet.jsp.PageContext;

import freemarker.template.Template;

public class PaginazioneFooterTag extends PaginazioneTag
{

	public void doTag() throws JspException, IOException
	{
		try
		{
			JspWriter out = getJspContext().getOut();
			PageContext pc = (PageContext) getJspContext();
			ServletContext sc = pc.getServletContext();
			Template template = getFreeMarkerLoaderFromWeb(sc).getTemplate("PaginazioneFooterTag.freemarker");
			HashMap<String, Object> parametriTemplate = buildTemplateModel();

			template.process(parametriTemplate, out);
		}
		catch (Exception e)
		{

			e.printStackTrace();
		}
	}

}
