package it.interlogic.vimp.data.dao;

import it.interlogic.vimp.constants.I18nConstants;
import it.interlogic.vimp.service.ConfigurazioneAmbiente;

import java.math.BigDecimal;
import java.util.Date;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.jdbc.core.simple.SimpleJdbcInsert;
import org.springframework.stereotype.Repository;



@Repository
public class DecodificaRestHelper
{
	
	@Autowired
	JdbcTemplate jt;
	
	@Autowired
	ConfigurazioneAmbiente ca;
	
	private static final String TABLE_ATECO = "PLF_T_ATECO";
	private static final String TABLE_TIPO_ALLEGATO = "PLF_T_TIPO_ALLEGATO";
	
	public List<Map<String, Object>> getActiveByTableName(final String tableName) {

		String sql = "SELECT * FROM " + tableName;
		
		if(!isAteco(tableName)) {
			sql += " WHERE DATA_FINE IS NULL";
		}
		
		sql += " ORDER BY DESCRIZIONE";
		
		return jt.queryForList(sql);
	}
	
	public Map<String, Object> getAllTranslationsById(final String tableName, String id) {
		
		String sql = "SELECT * FROM " + tableName;
		
		if(!isAteco(tableName)) {
			sql +=  " WHERE ID = '" + id + "' AND DATA_FINE IS NULL";
		} else {
			sql += " WHERE ID_PLF_T_ATECO = '" + id + "'";
		}
		
		
		Map<String, Object> res = jt.queryForMap(sql);
		
		return res;
	}
	
	private boolean isAteco(String tableName) {
		
		for(String l : ca.getSelectableLanguages().keySet()) {
			String suffix = "";
			
			if(!l.equals(I18nConstants.DEFAULT_LANGUAGE_CODE)) {
				suffix += "_" + l.toUpperCase();
			}
			
			if(tableName.equals(TABLE_ATECO + suffix)) {
				return true;
			}
		}
		
		return false;
		
	}
	
	private boolean isEmpty(Object o) {
		if (o == null) {
			return true;
		} 

		if (o instanceof String) {
			if(((String) o).trim().equals("")) {
				return true;
			}
		}
		
		return false;
	}
	
	//@Transactional
	@SuppressWarnings("unchecked")
	public int saveCodifiche(String tableName, List<Map<String, Object>> codifiche, int countCheck) {
		
		int count = 0;
		Map<String, Object> defaultCodifica = null;
		boolean isAteco = isAteco(tableName);
		
		// inserisco l'italiano per ottenere l'id
		for(Map<String, Object> singleRecord : codifiche) {
			if(singleRecord.get("locale").equals(I18nConstants.DEFAULT_LANGUAGE_CODE.toUpperCase())) {
			
				Map<String, Object> codifica = (Map<String, Object>) singleRecord.get("codifica");
				Map<String, Object> maxId;
				
				if(!isAteco) {
					maxId = jt.queryForMap("SELECT MAX(ID) AS max_id FROM " + tableName);
					BigDecimal newId;
					
					if(maxId.get("max_id") == null) {
						newId = new BigDecimal(0);
					} else {
						newId = new BigDecimal(maxId.get("max_id").toString());
					}
					newId = newId.add(BigDecimal.ONE);
					
					codifica.put("ID", newId);
					codifica.put("DATA_INIZIO", new Date());
				} else {
					maxId = jt.queryForMap("SELECT MAX(ID_PLF_T_ATECO) AS max_id FROM " + tableName);
					Long newId = new Long(maxId.get("max_id").toString());
					newId = newId + 1;
					
					codifica.put("ID_PLF_T_ATECO", newId);
					codifica.put("DT_MODIFICA", new Date());
				}
				
				defaultCodifica = codifica;
				
				count += new SimpleJdbcInsert(jt).withTableName(tableName).execute(codifica);
			}
		}
		
		if(count != 1) {
			throw new IllegalStateException();
		}
		
		for(Map<String, Object> singleRecord : codifiche) {
			
			if(!singleRecord.get("locale").equals(I18nConstants.DEFAULT_LANGUAGE_CODE.toUpperCase())) {
				String translatedName = tableName + "_" + singleRecord.get("locale");
				
				Map<String, Object> codifica = (Map<String, Object>) singleRecord.get("codifica");
				
				if(!isAteco) {
					codifica.put("ID", defaultCodifica.get("ID"));
					codifica.put("DATA_INIZIO", defaultCodifica.get("DATA_INIZIO"));
					
					if(isEmpty(codifica.get("CODICE"))) {
						codifica.put("CODICE", defaultCodifica.get("CODICE"));
					}
					
					if(tableName.equals("PLF_T_TIPO_ALLEGATO") && codifica.get("ESTENSIONE").equals("")) {
						codifica.put("ESTENSIONE", defaultCodifica.get("ESTENSIONE"));
					}
				} else {
					codifica.put("ID_PLF_T_ATECO", defaultCodifica.get("ID_PLF_T_ATECO"));
					codifica.put("DT_MODIFICA", defaultCodifica.get("DT_MODIFICA"));
					
					if(isEmpty(codifica.get("C_ATTIVITA"))) {
						codifica.put("C_ATTIVITA", defaultCodifica.get("C_ATTIVITA"));
					}
					
					if(isEmpty(codifica.get("T_CODIFICA"))) {
						codifica.put("T_CODIFICA", defaultCodifica.get("T_CODIFICA"));
					}
					
					if(isEmpty(codifica.get("C_FONTE"))) {
						codifica.put("C_FONTE", defaultCodifica.get("C_FONTE"));
					}
					
					if(isEmpty(codifica.get("SEZIONE"))) {
						codifica.put("SEZIONE", defaultCodifica.get("SEZIONE"));
					}
				}
				
				if(isEmpty(codifica.get("DESCRIZIONE"))) {
					codifica.put("DESCRIZIONE", defaultCodifica.get("DESCRIZIONE"));
				}
				
				count += new SimpleJdbcInsert(jt).withTableName(translatedName).execute(codifica);
			}
		}
		
		if(count != countCheck) {
			throw new IllegalStateException();
		}
		
		return count;
	}
	
	//@Transactional
	@SuppressWarnings("unchecked")
	public int updateCodifiche(String tableName, List<Map<String, Object>> codifiche, int countCheck) {
		
		int count = 0;
		String useTable = "";
		NamedParameterJdbcTemplate npjt = new NamedParameterJdbcTemplate(jt);
		
		for(Map<String, Object> singleRecord : codifiche) {
			if(singleRecord.get("locale").equals(I18nConstants.DEFAULT_LANGUAGE_CODE.toUpperCase())) {
				useTable = tableName; 
			} else {
				useTable = tableName + "_" + singleRecord.get("locale"); 
			}
			
			Map<String, Object> codifica = (Map<String, Object>) singleRecord.get("codifica");
			
			String sql = "UPDATE " + useTable + " SET ";
			
			if(!isAteco(tableName)) {
				sql += "CODICE = :CODICE, DESCRIZIONE = :DESCRIZIONE";
				
				if(tableName.equals(TABLE_TIPO_ALLEGATO)) {
					sql += ", ESTENSIONE = :ESTENSIONE";
				}
			
				sql += " WHERE ID = :ID";
				
			} else {
				
				codifica.put("DT_MODIFICA", new Date());
				
				sql += "C_ATTIVITA = " + codifica.get("C_ATTIVITA") + ", "
					+ "T_CODIFICA = " + codifica.get("T_CODIFICA") + ", "
					+ "C_FONTE = :C_FONTE, "
					+ "SEZIONE = :SEZIONE, "
					+ "DESCRIZIONE = :DESCRIZIONE, "
					+ "DT_MODIFICA = :DT_MODIFICA "
					+ "WHERE ID_PLF_T_ATECO = " + codifica.get("ID_PLF_T_ATECO");				
			}
			
			count += npjt.update(sql, codifica);
		}
		
		if(count != countCheck) {
			throw new IllegalStateException();
		}
		
		return count;
	}
	
	//@Transactional
	@SuppressWarnings("unchecked")
	public int disableCodifiche(String tableName, List<Map<String, Object>> codifiche, int countCheck) {
		
		int count = 0;
		String useTable = "";
		NamedParameterJdbcTemplate npjt = new NamedParameterJdbcTemplate(jt);
		
		for(Map<String, Object> singleRecord : codifiche) {
			if(singleRecord.get("locale").equals(I18nConstants.DEFAULT_LANGUAGE_CODE.toUpperCase())) {
				useTable = tableName; 
			} else {
				useTable = tableName + "_" + singleRecord.get("locale"); 
			}
			
			Map<String, Object> codifica = (Map<String, Object>) singleRecord.get("codifica");
			codifica.put("DATA_FINE", new Date());
			
			String sql = "UPDATE " + useTable + " SET " + 
			"DATA_FINE = :DATA_FINE " +
			"WHERE ID = :ID";
			
			count += npjt.update(sql, codifica);
		}
		
		if(count != countCheck) {
			throw new IllegalStateException();
		}
		
		return count;
	}
}
