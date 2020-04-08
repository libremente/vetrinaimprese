package it.interlogic.vimp.web.tags;

import it.interlogic.vimp.utils.I18nUtility;

import java.io.IOException;
import java.text.MessageFormat;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Properties;

import javax.servlet.ServletContext;
import javax.servlet.jsp.JspException;
import javax.servlet.jsp.JspWriter;
import javax.servlet.jsp.PageContext;
import javax.servlet.jsp.tagext.BodyContent;
import javax.servlet.jsp.tagext.SimpleTagSupport;

import freemarker.template.Configuration;
import freemarker.template.Template;

public class PaginazioneTag extends SimpleTagSupport
{

	public String PUNTINI = "&hellip;";

	protected boolean numeroRisultatoVisibile = false;
	protected int numeroRecordTotale = 0;
	protected int numeroRecordPerPagina = 20;
	protected String language = "";

	public void doTag() throws JspException, IOException
	{
		try
		{
			JspWriter out = getJspContext().getOut();
			PageContext pc = (PageContext) getJspContext();
			ServletContext sc = pc.getServletContext();
			Template template = getFreeMarkerLoaderFromWeb(sc).getTemplate("PaginazioneTag.freemarker");
			HashMap<String, Object> parametriTemplate = buildTemplateModel();

			template.process(parametriTemplate, out);
		}
		catch (Exception e)
		{

			e.printStackTrace();
		}
	}

	protected HashMap<String, Object> buildTemplateModel()
	{
		HashMap<String, Object> templateModel = new HashMap<String, Object>();

		if (totalePagine ==null || totalePagine.trim().length()<=0)
			totalePagine ="0";
		
		if (paginaCorrente ==null|| paginaCorrente.trim().length()<=0)
			paginaCorrente ="0";
		
		Integer numPagine = Integer.parseInt(totalePagine);
		Integer numPagCorr = Integer.parseInt(paginaCorrente);

		// Imposto i parametri del template
		boolean disabilitatoIndietro = (numPagCorr == 1);
		templateModel.put("disabilitatoIndietro", disabilitatoIndietro);
		boolean disabilitatoAvanti = (numPagCorr == numPagine);
		templateModel.put("disabilitatoAvanti", disabilitatoAvanti);

		ArrayList<InfoPulsante> listaPulsanti = new ArrayList<InfoPulsante>();
		for (int i = 1; i <= numPagine; i++)
		{
			boolean corrente = (i == numPagCorr);
			InfoPulsante pulsante = new InfoPulsante(Integer.toString(i), "#", corrente);
			listaPulsanti.add(pulsante);
		}
		templateModel.put("listapulsanti", listaPulsanti);
		templateModel.put("paginaCorrente", numPagCorr);
		templateModel.put("totalePagine", numPagine);

		templateModel.put("numeroRecordDa", ((numPagCorr - 1) * numeroRecordPerPagina) + 1);
		templateModel.put("numeroRecordA", Math.min(((numPagCorr - 1) * numeroRecordPerPagina) + numeroRecordPerPagina, numeroRecordTotale));
		templateModel.put("numeroRisultatoVisibile", numeroRisultatoVisibile);
		templateModel.put("numeroRecordTotale", numeroRecordTotale);
		
		Properties paginazioneTagsProperties = I18nUtility.getLanguageProperties("tags", "paginazioneTag");
		templateModel.put("risultati",
				MessageFormat.format(paginazioneTagsProperties.getProperty("results"),
						((numPagCorr - 1) * numeroRecordPerPagina) + 1,
						Math.min(((numPagCorr - 1) * numeroRecordPerPagina) + numeroRecordPerPagina, numeroRecordTotale),
						numeroRecordTotale
				)
		);
		templateModel.put("avanti", paginazioneTagsProperties.getProperty("next"));
		templateModel.put("indietro", paginazioneTagsProperties.getProperty("previous"));

		return templateModel;
	}

	// private RequestContext requestContext;
	protected BodyContent bodyContent;

	public String getPaginaCorrente()
	{
		return paginaCorrente;
	}

	public void setPaginaCorrente(String paginaCorrente)
	{
		this.paginaCorrente = paginaCorrente;
	}

	public String getTotalePagine()
	{
		return totalePagine;
	}

	public void setTotalePagine(String totalePagine)
	{
		this.totalePagine = totalePagine;
	}

	String paginaCorrente;
	String totalePagine;

	static String webPath = "WEB-INF/tags/";

	protected transient static Configuration freeMarkerWebLoader = null;

	protected static Configuration getFreeMarkerLoaderFromWeb(ServletContext servletContext)
	{
		if (freeMarkerWebLoader == null)
		{
			// ServletContext servletContext = ;

			freeMarkerWebLoader = new Configuration();

			freeMarkerWebLoader.setServletContextForTemplateLoading(servletContext, webPath);
		}
		return freeMarkerWebLoader;
	}

	public boolean isNumeroRisultatoVisibile()
	{
		return numeroRisultatoVisibile;
	}

	public void setNumeroRisultatoVisibile(boolean numeroRisultatoVisibile)
	{
		this.numeroRisultatoVisibile = numeroRisultatoVisibile;
	}

	public int getNumeroRecordTotale()
	{
		return numeroRecordTotale;
	}

	public void setNumeroRecordTotale(int numeroRecordTotale)
	{
		this.numeroRecordTotale = numeroRecordTotale;
	}

	public int getNumeroRecordPerPagina()
	{
		return numeroRecordPerPagina;
	}

	public void setNumeroRecordPerPagina(int numeroRecordPerPagina)
	{
		this.numeroRecordPerPagina = numeroRecordPerPagina;
	}

}
