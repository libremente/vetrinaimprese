package it.interlogic.vimp.data.dao;

import it.interlogic.vimp.amulti.LanguageContext;
import it.interlogic.vimp.data.jpa.model.PLFPacchettoServiziEntity;
import it.interlogic.vimp.data.jpa.model.PLFPacchettoServiziTranslationEntity;
import it.interlogic.vimp.data.jpa.repository.PLFPacchettoServiziJpaRepository;
import it.interlogic.vimp.data.jpa.repository.PLFPacchettoServiziTranslationJpaRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.i18n.LocaleContextHolder;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

@Component
public class PLFPacchettoServiziTranslationDao {

    @Autowired
    PLFPacchettoServiziJpaRepository repository;

    @Autowired
    PLFPacchettoServiziTranslationJpaRepository translationRepository;

    @Value("${locales}")
    String[] locales;

    //@Transactional
    public void saveOrUpdateTranslation(PLFPacchettoServiziEntity t){
        if(t.getIdPacchettoServizi()!=null){
            repository.save(t);
            translationRepository.save(t.getPacchettoServiziTranslation());
        } else {
            PLFPacchettoServiziTranslationEntity tr = t.getPacchettoServiziTranslation();
            t.setPacchettoServiziTranslation(null);
            repository.save(t);
            tr.setIdPacchettoServizi(t.getIdPacchettoServizi());
            String originalLan = LocaleContextHolder.getLocale().toString();
            for(String locale : locales){
                LanguageContext.getInstance().setLanguage(locale);
                translationRepository.save(tr);
            }
            LanguageContext.getInstance().setLanguage(originalLan);
        }
    }

}
