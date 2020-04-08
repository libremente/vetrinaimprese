package it.interlogic.vimp.data.dao;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.i18n.LocaleContextHolder;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import it.interlogic.vimp.amulti.LanguageContext;
import it.interlogic.vimp.data.jpa.model.PLFServiziEntity;
import it.interlogic.vimp.data.jpa.model.PLFServiziTranslationEntity;
import it.interlogic.vimp.data.jpa.repository.PLFServiziJpaRepository;
import it.interlogic.vimp.data.jpa.repository.PLFServiziTranslationJpaRepository;

@Component
public class PLFServiziTranslationDao {

    @Autowired
    PLFServiziJpaRepository repository;

    @Autowired
    PLFServiziTranslationJpaRepository translationRepository;

    @Value("${locales}")
    String[] locales;

    //@Transactional
    public void saveOrUpdateTranslation(PLFServiziEntity t){
        if(t.getIdServizi()!=null){
            repository.save(t);
            translationRepository.save(t.getServiziTranslation());
        } else {
            PLFServiziTranslationEntity tr = t.getServiziTranslation();
            t.setServiziTranslation(null);
            repository.save(t);
            tr.setIdServizi(t.getIdServizi());
            
            
            String originalLan = LocaleContextHolder.getLocale().toString();
            
            for(String locale : locales){
                LanguageContext.getInstance().setLanguage(locale);
                translationRepository.save(tr);
            }
            
            LanguageContext.getInstance().setLanguage(originalLan);
        }
    }

}
