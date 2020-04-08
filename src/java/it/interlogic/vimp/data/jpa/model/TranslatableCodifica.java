package it.interlogic.vimp.data.jpa.model;

import java.math.BigDecimal;
import java.util.Date;

public interface TranslatableCodifica
{
	
	BigDecimal getId();
	
	void setId(BigDecimal id);
	
	String getCodice();
	
	void setCodice(String codice);
	
	String getDescrizione();
	
	void setDescrizione(String descrizione);
	
	public Date getDataFine();

	public void setDataFine(Date dataFine);
	
	public Date getDataInizio();

	public void setDataInizio(Date dataInizio);

}
