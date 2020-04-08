package it.interlogic.vimp.amulti;

import it.interlogic.vimp.service.IDecodificheService;

import org.springframework.context.annotation.AnnotationConfigApplicationContext;

public class BatchPersistent
{
	private AnnotationConfigApplicationContext context;
	private IDecodificheService decodificaService;

	public BatchPersistent()
	{
		context = new AnnotationConfigApplicationContext(TestConfig.class);
		decodificaService = context.getBean(IDecodificheService.class);
	}

	public void closeContext()
	{
		context.close();
	}

	public IDecodificheService getDecodificaService()
	{
		return decodificaService;
	}
}