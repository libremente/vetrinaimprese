package it.interlogic.vimp.data.dao;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.i18n.LocaleContextHolder;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import it.interlogic.vimp.amulti.LanguageContext;
import it.interlogic.vimp.data.jpa.model.PLFProgettiProdottiAllegatiEntity;
import it.interlogic.vimp.data.jpa.model.PLFProgettiProdottiAllegatiTranslationEntity;
import it.interlogic.vimp.data.jpa.repository.PLFProgettiProdottiAllegatiJpaRepository;
import it.interlogic.vimp.data.jpa.repository.PLFProgettiProdottiAllegatiTranslationJpaRepository;

@Component
public class PLFProgettiProdottiAllegatiTranslationDao {

    @Autowired
    PLFProgettiProdottiAllegatiJpaRepository repository;

    @Autowired
    PLFProgettiProdottiAllegatiTranslationJpaRepository translationRepository;

    @Value("${locales}")
    String[] locales;

    //@Transactional
    public void saveOrUpdateAllegatoTranslation(PLFProgettiProdottiAllegatiEntity t){
        if(t.getIdProgettiProdottiAllegati()!=null){
            repository.save(t);
            translationRepository.save(t.getProgettiProdottiAllegatiTranslation());
        } else {
        	PLFProgettiProdottiAllegatiTranslationEntity tr = t.getProgettiProdottiAllegatiTranslation();
            t.setProgettiProdottiAllegatiTranslation(null);
            repository.save(t);
            tr.setIdProgettiProdottiAllegati(t.getIdProgettiProdottiAllegati());
            String originalLan = LocaleContextHolder.getLocale().toString();
            for(String locale : locales){
                LanguageContext.getInstance().setLanguage(locale);
                translationRepository.save(tr);
            }
            LanguageContext.getInstance().setLanguage(originalLan);
        }
    }
    
    //@Transactional
    public void deleteAllegatoTranslation(PLFProgettiProdottiAllegatiEntity t) {
    	String originalLan = LocaleContextHolder.getLocale().toString();
    	for(String locale : locales){
    		LanguageContext.getInstance().setLanguage(locale);
    		translationRepository.delete(t.getIdProgettiProdottiAllegati());
    	}
    	repository.delete(t.getIdProgettiProdottiAllegati());
    	LanguageContext.getInstance().setLanguage(originalLan);
    }
    
    //@Transactional
    public PLFProgettiProdottiAllegatiTranslationEntity saveOrUpdateTranslation(PLFProgettiProdottiAllegatiTranslationEntity t) {
    	return translationRepository.save(t);
    }
}
