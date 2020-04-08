package it.interlogic.vimp.service.ws.toponomastica;

import java.io.Serializable;
import java.math.BigDecimal;
import java.net.URLEncoder;

import com.google.gson.annotations.SerializedName;

public class Civico implements Serializable
{

	private static final long serialVersionUID = 1L;

	@SerializedName("CODICE_STRADA")
	private String codiceSrada;

	@SerializedName("NOME_VIA")
	private String nomeVia;

	@SerializedName("LETTERA_CIVICO")
	private String lettera;

	@SerializedName("COLORE_CIVICO")
	private String colore;

	@SerializedName("NUMERO_CIVICO")
	private String numero;

	@SerializedName("LONGITUDINE")
	private float lon;

	@SerializedName("LATITUDINE")
	private float lat;

	@SerializedName("CAP")
	private float cap;

	@SerializedName("SEZIONE_CENSIMENTO_2011")
	private BigDecimal sezioneCensimento2011;
	@SerializedName("SEZIONE_ELETTORALE")
	private BigDecimal sezioneElettorale;
	@SerializedName("CODICE_MUNICIPIO")
	private String codiceMunicipio;
	@SerializedName("NOME_MUNICIPIO")
	private String nomeMunicipio;
	@SerializedName("CODICE_CIRCOSCRIZIONE")
	private String codiceCircoscrizione;
	@SerializedName("NOME_CIRCOSCRIZIONE")
	private String nomeCircoscrizione;
	@SerializedName("CODICE_UNITA_URBANISTICA")
	private String codiceUnitaUrbanistica;
	@SerializedName("NOME_UNITA_URBANISTICA")
	private String nomeUnitaUrbanistica;

	@SuppressWarnings("unused")
	private String query;

	public String getCodiceSrada()
	{
		return codiceSrada;
	}

	public void setCodiceSrada(String codiceSrada)
	{
		this.codiceSrada = codiceSrada;
	}

	public String getNomeVia()
	{
		return nomeVia;
	}

	public void setNomeVia(String nomeVia)
	{
		this.nomeVia = nomeVia;
	}

	public String getLettera()
	{
		return lettera;
	}

	public void setLettera(String lettera)
	{
		this.lettera = lettera;
	}

	public String getColore()
	{
		return colore;
	}

	public void setColore(String colore)
	{
		this.colore = colore;
	}

	public String getNumero()
	{
		return numero;
	}

	public void setNumero(String numero)
	{
		this.numero = numero;
	}

	public float getLon()
	{
		return lon;
	}

	public void setLon(float lon)
	{
		this.lon = lon;
	}

	public float getLat()
	{
		return lat;
	}

	public void setLat(float lat)
	{
		this.lat = lat;
	}

	public String getQuery()
	{
		// return query;
		try
		{
			return "CODICE_STRADA=" + URLEncoder.encode(codiceSrada, "UTF-8") + "&NUMERO_CIVICO=" + URLEncoder.encode(numero, "UTF-8") + "&LETTERA_CIVICO="
					+ URLEncoder.encode(lettera, "UTF-8") + "&COLORE_CIVICO=" + URLEncoder.encode(colore, "UTF-8");
		}
		catch (Exception err)
		{
		}

		return "";
	}

	public void setQuery(String query)
	{
		this.query = query;
	}

	public float getCap()
	{
		return cap;
	}

	public void setCap(float cap)
	{
		this.cap = cap;
	}

	public BigDecimal getSezioneCensimento2011()
	{
		return sezioneCensimento2011;
	}

	public void setSezioneCensimento2011(BigDecimal sezioneCensimento2011)
	{
		this.sezioneCensimento2011 = sezioneCensimento2011;
	}

	public BigDecimal getSezioneElettorale()
	{
		return sezioneElettorale;
	}

	public void setSezioneElettorale(BigDecimal sezioneElettorale)
	{
		this.sezioneElettorale = sezioneElettorale;
	}

	public String getCodiceMunicipio()
	{
		return codiceMunicipio;
	}

	public void setCodiceMunicipio(String codiceMunicipio)
	{
		this.codiceMunicipio = codiceMunicipio;
	}

	public String getNomeMunicipio()
	{
		return nomeMunicipio;
	}

	public void setNomeMunicipio(String nomeMunicipio)
	{
		this.nomeMunicipio = nomeMunicipio;
	}

	public String getCodiceCircoscrizione()
	{
		return codiceCircoscrizione;
	}

	public void setCodiceCircoscrizione(String codiceCircoscrizione)
	{
		this.codiceCircoscrizione = codiceCircoscrizione;
	}

	public String getNomeCircoscrizione()
	{
		return nomeCircoscrizione;
	}

	public void setNomeCircoscrizione(String nomeCircoscrizione)
	{
		this.nomeCircoscrizione = nomeCircoscrizione;
	}

	public String getCodiceUnitaUrbanistica()
	{
		return codiceUnitaUrbanistica;
	}

	public void setCodiceUnitaUrbanistica(String codiceUnitaUrbanistica)
	{
		this.codiceUnitaUrbanistica = codiceUnitaUrbanistica;
	}

	public String getNomeUnitaUrbanistica()
	{
		return nomeUnitaUrbanistica;
	}

	public void setNomeUnitaUrbanistica(String nomeUnitaUrbanistica)
	{
		this.nomeUnitaUrbanistica = nomeUnitaUrbanistica;
	}

	@Override
	public String toString()
	{
		return "Civico [codiceSrada=" + codiceSrada + ", nomeVia=" + nomeVia + ", lettera=" + lettera + ", colore=" + colore + ", numero=" + numero + ", lon=" + lon + ", lat="
				+ lat + ", cap=" + cap + ", sezioneCensimento2011=" + sezioneCensimento2011 + ", sezioneElettorale=" + sezioneElettorale + ", codiceMunicipio=" + codiceMunicipio
				+ ", nomeMunicipio=" + nomeMunicipio + ", codiceCircoscrizione=" + codiceCircoscrizione + ", nomeCircoscrizione=" + nomeCircoscrizione
				+ ", codiceUnitaUrbanistica=" + codiceUnitaUrbanistica + ", nomeUnitaUrbanistica=" + nomeUnitaUrbanistica + "]";
	}

}
