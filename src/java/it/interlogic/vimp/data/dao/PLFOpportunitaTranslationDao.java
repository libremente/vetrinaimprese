package it.interlogic.vimp.data.dao;

import it.interlogic.vimp.amulti.LanguageContext;
import it.interlogic.vimp.data.jpa.model.PLFOpportunitaEntity;
import it.interlogic.vimp.data.jpa.model.PLFOpportunitaTranslationEntity;
import it.interlogic.vimp.data.jpa.repository.PLFOpportunitaJpaRepository;
import it.interlogic.vimp.data.jpa.repository.PLFOpportunitaTranslationJpaRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.i18n.LocaleContextHolder;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

@Component
public class PLFOpportunitaTranslationDao {

    @Autowired
    PLFOpportunitaJpaRepository repository;

    @Autowired
    PLFOpportunitaTranslationJpaRepository translationRepository;

    @Value("${locales}")
    String[] locales;

    //@Transactional
    public void saveOrUpdateTranslation(PLFOpportunitaEntity t){
        if(t.getIdPlfOpportunita()!=null){
            repository.save(t);
            translationRepository.save(t.getOpportunitaTranslation());
        } else {
            PLFOpportunitaTranslationEntity tr = t.getOpportunitaTranslation();
            t.setOpportunitaTranslation(null);
            repository.save(t);
            tr.setIdPlfOpportunita(t.getIdPlfOpportunita());
            String originalLan = LocaleContextHolder.getLocale().toString();
            for(String locale : locales){
                LanguageContext.getInstance().setLanguage(locale);
                translationRepository.save(tr);
            }
            LanguageContext.getInstance().setLanguage(originalLan);
        }
    }

}
