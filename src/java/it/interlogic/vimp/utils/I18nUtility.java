package it.interlogic.vimp.utils;

import java.io.IOException;
import java.io.InputStream;
import java.util.HashMap;
import java.util.Map;
import java.util.Properties;

import org.springframework.context.i18n.LocaleContextHolder;

public class I18nUtility {
	
	private static Map<String, Properties> results = new HashMap<String, Properties>();
	
	public static Properties getLanguageProperties(String fileFolder, String fileName) {
		
		String language = "_" + LocaleContextHolder.getLocale().toString();
		String cachedPropertyFile = fileName + language;
		
		if(alreadyExists(fileName)) {
			return results.get(cachedPropertyFile);
		}
		
		Properties result = new Properties();
		InputStream is = null;
		
		try{
			is = I18nUtility.class.getClassLoader().getResourceAsStream("i18n/" + fileFolder + "/" + fileName + language + ".properties");
			result.load(is);
			
			if(results == null) {
				results = new HashMap<String, Properties>();
			}
			
			results.put(cachedPropertyFile, result);
			
		} catch (Exception e) {
			LoggerUtility.error(e.getMessage(),e);
		} finally {
			if(is!=null) 
			{
				try
				{
					is.close();
				}
				catch (IOException e){}
			}
		}
		
		return result;
	}
	
	private static boolean alreadyExists(String fileName) {
		
		String language = "_" + LocaleContextHolder.getLocale().toString();
		String toSearch = fileName + language;
		
		return results.get(toSearch) != null; // results != null && 
	}

}
