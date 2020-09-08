package it.interlogic.vimp.service;

import java.math.BigDecimal;
import java.util.List;
import java.util.Map;

import it.interlogic.vimp.data.jpa.model.*;


public interface IDecodificheService
{

	/**
	 * @return
	 */
	List<PLFTTipoProgrammaEntity> getTipoProgramma();

	/**
	 * @param codice
	 * @return
	 */
	List<PLFTTipoProgrammaEntity> getTipoProgrammaByCodice(String codice);

	/**
	 * @param idTipoProgramma
	 * @return
	 */
	PLFTTipoProgrammaEntity getTipoProgramma(BigDecimal idTipoProgramma);

	/**
	 * @return
	 */
	List<PLFTTipoProgettoEntity> getTipoProgetto();

	/**
	 * @param codProgetto
	 * @return
	 */
	List<PLFTTipoProgettoEntity> getTipoProgettoByCodice(String codProgetto);

	/**
	 * @param idTipoProgetto
	 * @return
	 */
	PLFTTipoProgettoEntity getTipoProgetto(BigDecimal idTipoProgetto);

	/**
	 * @return
	 */
	List<PLFTTipoOrganizzazioneEntity> getTipoOrganizzazione();

	/**
	 * @param codice
	 * @return
	 */
	List<PLFTTipoOrganizzazioneEntity> getTipoOrganizzazionePerCodice(String codice);

	/**
	 * @param idTipoOrganizzazione
	 * @return
	 */
	PLFTTipoOrganizzazioneEntity getTipoOrganizzazione(BigDecimal idTipoOrganizzazione);

	/**
	 * @return
	 */
	List<PLFTStatoProgettoEntity> getStatoProgetto();

	/**
	 * @param codice
	 * @return
	 */
	List<PLFTStatoProgettoEntity> getStatoProgettoByCodice(String codice);

	/**
	 * @param idStatoProgetto
	 * @return
	 */
	PLFTStatoProgettoEntity getStatoProgetto(BigDecimal idStatoProgetto);

	/**
	 * @return
	 */
	List<PLFTSoggAmmissibiliEntity> getSoggAmmissibili();

	/**
	 * @param codice
	 * @return
	 */
	List<PLFTSoggAmmissibiliEntity> getSoggAmmissibiliByCodice(String codice);

	/**
	 * @param idSoggAmmissibili
	 * @return
	 */
	PLFTSoggAmmissibiliEntity getSoggAmmissibili(BigDecimal idSoggAmmissibili);

	/**
	 * @return
	 */
	List<PLFTSettoreImpresaEntity> getSettoreImpresa();

	/**
	 * @param codSettore
	 * @return
	 */
	List<PLFTSettoreImpresaEntity> getSettoreImpresaPerCodice(String codSettore);
	
	/**
	 * @param descrizione
	 * @return
	 */
	List<PLFTSettoreImpresaEntity> getSettoreImpresaPerDescrizione(String descrizione);

	/**
	 * @param idSettore
	 * @return
	 */
	PLFTSettoreImpresaEntity getSettoreImpresa(BigDecimal idSettore);

	/**
	 * @return
	 */
	List<PLFTSettoreProgettiEntity> getSettoreProgetti();

	/**
	 * @param codSettore
	 * @return
	 */
	List<PLFTSettoreProgettiEntity> getSettoreProgettiPerCodice(String codSettore);

	/**
	 * @param idSettore
	 * @return
	 */
	PLFTSettoreProgettiEntity getSettoreProgetti(BigDecimal idSettore);

//	List<PLFTAteco2007Entity> getAteco2007();
//
//	List<PLFTAteco2007Entity> getAteco2007PerCodice(String codice);
//
//	List<PLFTAteco2007Entity> getAteco2007PerDescrizione(String descrizione);
//
//	PLFTAteco2007Entity getAteco2007(BigDecimal idAteco2007);

	/**
	 * @return
	 */
	List<PLFTAtecoEntity> getAteco();

	/**
	 * @param attivita
	 * @return
	 */
	List<PLFTAtecoEntity> getAtecoPerAttivita(BigDecimal attivita);

	/**
	 * @param codifica
	 * @return
	 */
	List<PLFTAtecoEntity> getAtecoPerDescrizione(BigDecimal codifica);

	/**
	 * @param idAteco
	 * @return
	 */
	PLFTAtecoEntity getAteco(BigDecimal idAteco);

	// =============

	/**
	 * @return
	 */
	List<PLFTRegioneEntity> getRegione();

	/**
	 * @param idRegione
	 * @return
	 */
	PLFTRegioneEntity getRegione(BigDecimal idRegione);

	/**
	 * @param idRegione
	 * @return
	 */
	List<PLFTProvinciaEntity> getProvinciaByRegione(BigDecimal idRegione);

	/**
	 * @param codRegione
	 * @return
	 */
	List<PLFTProvinciaEntity> getProvinciaByCodRegione(String codRegione);

	/**
	 * @param idProvincia
	 * @return
	 */
	PLFTProvinciaEntity getProvincia(BigDecimal idProvincia);

	/**
	 * @param idProvincia
	 * @return
	 */
	List<PLFTComuneEntity> getComuneByProvincia(BigDecimal idProvincia);
	
	/**
	 * @param nomeComune
	 * @return
	 */
	List<PLFTComuneEntity> getComuneByNome(String nomeComune);
	
	/**
	 * @param codiceComune
	 * @return
	 */
	List<PLFTComuneEntity> getComuneByCodice(String codiceComune);
	
	PLFTComuneEntity getComuneByCodiceIstat(String codiceComune);

	/**
	 * @param codProvincia
	 * @return
	 */
	List<PLFTComuneEntity> getComuneByCodProvincia(String codProvincia);

	/**
	 * @return
	 */
	List<PLFTComuneEntity> getComuneStatiEsteri();

	/**
	 * @param idComune
	 * @return
	 */
	PLFTComuneEntity getComune(BigDecimal idComune);

	/**
	 * @return
	 */
	List<PLFTComuneEntity> getComune();

	/**
	 * @return
	 */
	List<PLFTNazioneEntity> getNazione();

	/**
	 * @param codiceNazione
	 * @return
	 */
	PLFTNazioneEntity getNazioneByCodice(String codiceNazione);

	/**
	 * @return
	 */
	PLFTNazioneEntity getNazioneItalia();

	/**
	 * @return
	 */
	List<PLFTRuoloEntity> getRuolo();
	
	/**
	 * @param id
	 * @param descrizione
	 * @return
	 */
	int updateRuolo(BigDecimal id, String descrizione);

	/**
	 * @return
	 */
	List<PLFTControlliRichiestaEntity> getControlliRichiesta();

	/**
	 * @param codice
	 * @return
	 */
	List<PLFTControlliRichiestaEntity> getControlliRichiestaByCodice(String codice);

	/**
	 * @param idControlliRichiesta
	 * @return
	 */
	PLFTControlliRichiestaEntity getControlliRichiesta(BigDecimal idControlliRichiesta);

	/**
	 * @return
	 */
	List<PLFTLogMessaggiEntity> getLogMessaggi();

	/**
	 * @param codice
	 * @return
	 */
	List<PLFTLogMessaggiEntity> getLogMessaggiByCodice(String codice);

	/**
	 * @param idLogMessaggi
	 * @return
	 */
	PLFTLogMessaggiEntity getLogMessaggi(BigDecimal idLogMessaggi);

	/**
	 * @return
	 */
	List<PLFTOrigineImpresaEntity> getOrigineImpresa();

	/**
	 * @param codice
	 * @return
	 */
	List<PLFTOrigineImpresaEntity> getOrigineImpresaByCodice(String codice);

	/**
	 * @param idOrigineImpresa
	 * @return
	 */
	PLFTOrigineImpresaEntity getOrigineImpresa(BigDecimal idOrigineImpresa);

	/**
	 * @return
	 */
	List<PLFTSezioneSpecialeImpresaEntity> getSezioneSpecialeImpresa();

	/**
	 * @param codice
	 * @return
	 */
	List<PLFTSezioneSpecialeImpresaEntity> getSezioneSpecialeImpresaByCodice(String codice);

	/**
	 * @param idSezioneSpecialeImpresa
	 * @return
	 */
	PLFTSezioneSpecialeImpresaEntity getSezioneSpecialeImpresa(BigDecimal idSezioneSpecialeImpresa);

	/**
	 * @return
	 */
	List<PLFTStatoImpresaEntity> getStatoImpresa();

	/**
	 * @param codice
	 * @return
	 */
	List<PLFTStatoImpresaEntity> getStatoImpresaByCodice(String codice);

	/**
	 * @param idStatoImpresa
	 * @return
	 */
	PLFTStatoImpresaEntity getStatoImpresa(BigDecimal idStatoImpresa);

	/**
	 * @return
	 */
	List<PLFTStatoRichiestaEntity> getStatoRichiesta();

	/**
	 * @param codice
	 * @return
	 */
	List<PLFTStatoRichiestaEntity> getStatoRichiestaByCodice(String codice);

	/**
	 * @param idStatoRichiestaa
	 * @return
	 */
	PLFTStatoRichiestaEntity getStatoRichiesta(BigDecimal idStatoRichiestaa);

	/**
	 * @return
	 */
	List<PLFTTipoAllegatoEntity> getTipoAllegato();

	/**
	 * @param codice
	 * @return
	 */
	List<PLFTTipoAllegatoEntity> getTipoAllegatoByCodice(String codice);

	/**
	 * @param idTipoAllegato
	 * @return
	 */
	PLFTTipoAllegatoEntity getTipoAllegato(BigDecimal idTipoAllegato);

	/**
	 * @return
	 */
	List<PLFTTipoImpresaEntity> getTipoImpresa();

	/**
	 * @param codice
	 * @return
	 */
	List<PLFTTipoImpresaEntity> getTipoImpresaByCodice(String codice);

	/**
	 * @param idTipoImpresa
	 * @return
	 */
	PLFTTipoImpresaEntity getTipoImpresa(BigDecimal idTipoImpresa);

	/**
	 * @return
	 */
	List<PLFTTipoProgettiProdottiEntity> getTipoProgettiProdotti();

	/**
	 * @param codice
	 * @return
	 */
	List<PLFTTipoProgettiProdottiEntity> getTipoProgettiProdottiByCodice(String codice);

	/**
	 * @param idTipoProgettiProdotti
	 * @return
	 */
	PLFTTipoProgettiProdottiEntity getTipoProgettiProdotti(BigDecimal idTipoProgettiProdotti);

	/**
	 * @return
	 */
	List<PLFTTipologiaProgettoEntity> getTipologiaProgetto();

	/**
	 * @param codice
	 * @return
	 */
	List<PLFTTipologiaProgettoEntity> getTipologiaProgettoByCodice(String codice);

	/**
	 * @param idTipologiaProgetto
	 * @return
	 */
	PLFTTipologiaProgettoEntity getTipologiaProgetto(BigDecimal idTipologiaProgetto);

	/**
	 * @return
	 */
	List<PLFTClasseAddettiEntity> getClasseAddetti();

	/**
	 * @param codice
	 * @return
	 */
	List<PLFTClasseAddettiEntity> getClasseAddettiByCodice(String codice);

	/**
	 * @param idClasseAddetti
	 * @return
	 */
	PLFTClasseAddettiEntity getClasseAddetti(BigDecimal idClasseAddetti);

	/**
	 * @return
	 */
	List<PLFTClasseProduzioneEntity> getClasseProduzione();

	/**
	 * @param codice
	 * @return
	 */
	List<PLFTClasseProduzioneEntity> getProduzioneByCodice(String codice);

	/**
	 * @param idClasseProduzione
	 * @return
	 */
	PLFTClasseProduzioneEntity getClasseProduzione(BigDecimal idClasseProduzione);

	/**
	 * @return
	 */
	List<PLFTClasseCapitaleEntity> getClasseCapitale();

	/**
	 * @param codice
	 * @return
	 */
	List<PLFTClasseCapitaleEntity> getClasseCapitaleByCodice(String codice);

	/**
	 * @param idClasseCapitale
	 * @return
	 */
	PLFTClasseCapitaleEntity getClasseCapitale(BigDecimal idClasseCapitale);

	/**
	 * @return
	 */
	List<PLFTPrevalenzaEntity> getPrevalenza();

	/**
	 * @param codice
	 * @return
	 */
	List<PLFTPrevalenzaEntity> getPrevalenzaByCodice(String codice);

	/**
	 * @param idPrevalenza
	 * @return
	 */
	PLFTPrevalenzaEntity getPrevalenza(BigDecimal idPrevalenza);

	/**
	 * @return
	 */
	List<PLFTAreeCompetenzaEntity> getAreeCompetenza();

	/**
	 * @param codice
	 * @return
	 */
	List<PLFTAreeCompetenzaEntity> getAreeCompetenzaByCodice(String codice);
	List<PLFTAreeCompetenzaEntity> getAreeCompetenzaByDescrizione(String descrizione);

	/**
	 * @param idAreaCompetenza
	 * @return
	 */
	PLFTAreeCompetenzaEntity getAreeCompetenza(BigDecimal idAreaCompetenza);

	/**
	 * @return
	 */
	List<PLFTTipoNewsEntity> getTipoNews();

	/**
	 * @param codice
	 * @return
	 */
	List<PLFTTipoNewsEntity> getTipoNewsByCodice(String codice);

	/**
	 * @param idTipoNews
	 * @return
	 */
	PLFTTipoNewsEntity getTipoNews(BigDecimal idTipoNews);

	/**
	 * @return
	 */
	List<PLFTNaturaGiuridicaEntity> getNaturaGiuridica();

	/**
	 * @param codice
	 * @return
	 */
	List<PLFTNaturaGiuridicaEntity> getNaturaGiuridicaByCodice(String codice);
	
	/**
	 * @param descrizione
	 * @return
	 */
	List<PLFTNaturaGiuridicaEntity> getNaturaGiuridicaByDescrizione(String descrizione);

	/**
	 * @param idNaturaGiuridica
	 * @return
	 */
	PLFTNaturaGiuridicaEntity getNaturaGiuridica(BigDecimal idNaturaGiuridica);

	/**
	 * @return
	 */
	List<PLFTRuoloAziendaleEntity> getRuoloAziendale();

	/**
	 * @param codice
	 * @return
	 */
	List<PLFTRuoloAziendaleEntity> getRuoloAziendaleByCodice(String codice);

	/**
	 * @param idRuoloAziendale
	 * @return
	 */
	PLFTRuoloAziendaleEntity getRuoloAziendale(BigDecimal idRuoloAziendale);

	/**
	 * @return
	 */
	List<PLFTInnovazioneEntity> getInnovazione();

	/**
	 * @param codice
	 * @return
	 */
	List<PLFTInnovazioneEntity> getInnovazioneByCodice(String codice);

	/**
	 * @param idInnovazione
	 * @return
	 */
	PLFTInnovazioneEntity getInnovazione(BigDecimal idInnovazione);

	/**
	 * @return
	 */
	List<PLFTMercatiEntity> getMercati();

	/**
	 * @param codice
	 * @return
	 */
	List<PLFTMercatiEntity> getMercatiByCodice(String codice);

	/**
	 * @param idMercati
	 * @return
	 */
	PLFTMercatiEntity getMercati(BigDecimal idMercati);

	/**
	 * @return
	 */
	List<PLFImpresaEntity> getStakeholder();

	/**
	 * @return
	 */
	List<PLFTTipoServizioEntity> getTipoServizio();
	List<PLFTTipoServizioEntity> getTipoServizioByDescrizione(String descrizione);
	
	/**
	 * @return
	 */
	List<PLFTTagEntity> getTags();
	
	/**
	 * @param idTipoServizio
	 * @return
	 */
	PLFTTipoServizioEntity getTipoServizio(BigDecimal idTipoServizio);

	/**
	 * @return
	 */
	List<PLFTSettoreProgettiProdottiEntity> getSettoriProgettiProdotti();

	/**
	 * @return
	 */
	List <PLFTSettoreTecnologieEntity> getSettoriTecnologie();
	
	/**
	 * @param table
	 * @return
	 */
	List<Map<String, Object>> getCodificheByTable(String table);

	/**
	 * @param table
	 * @param codifiche
	 * @return
	 */
	Map<String, List<String>> checkDuplicates(String table, List<Map<String, Object>> codifiche);
	
	/**
	 * @param table
	 * @param id
	 * @return
	 */
	Map<String, Object> getTranslatedCodificheByid(String table, String id);
	
	/**
	 * @param tableName
	 * @param codifiche
	 * @param countCheck
	 * @return
	 */
	int saveCodifiche(String tableName, List<Map<String, Object>> codifiche, int countCheck);
	
	/**
	 * @param tableName
	 * @param codifiche
	 * @param countCheck
	 * @return
	 */
	int updateCodifiche(String tableName, List<Map<String, Object>> codifiche, int countCheck);
	
	/**
	 * @param tableName
	 * @param codifiche
	 * @param countCheck
	 * @return
	 */
	int disableCodifiche(String tableName, List<Map<String, Object>> codifiche, int countCheck);
	
	
	/* Servizi */
	
	/**
	 * @return
	 */
	List<PLFTDenominazioneServiziEntity> getDenominazioniServizi();
	
	/**
	 * @return
	 */
	List<PLFTModalitaErogazioneServizioEntity> getModalitaErogazioneServizio();
	
	
	List<PLFTModalitaErogazioneServizioEntity> getModalitaErogazioneServizioPerDescrizione(String codSettore);
	
	/**
	 * @return
	 */
	List<PLFTTipoErogazioneServizioEntity> getTipiErogazioneServizio();
	List<PLFTTipoErogazioneServizioEntity> getTipiErogazioneServizioPerDescrizione(String codSettore);
	
	/**
	 * @return
	 */
	List<PLFTMacroareaServiziEntity> getMacroareeServizio();
	List<PLFTMacroareaServiziEntity> getMacroareeServizioByDescrizione(String descrizione);

	/**
	 * @return
	 */
	List<PLFTModalitaErogazionePacchettoEntity> getModalitaErogazionePacchetto();

	/**
	 * @param id
	 * @return
	 */
	PLFTModalitaErogazionePacchettoEntity getModalitaErogazionePacchettoById(BigDecimal id);

}
