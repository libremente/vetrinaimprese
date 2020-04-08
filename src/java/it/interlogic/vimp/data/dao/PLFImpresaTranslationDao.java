package it.interlogic.vimp.data.dao;

import it.interlogic.vimp.amulti.LanguageContext;
import it.interlogic.vimp.data.jpa.model.PLFImpresaEntity;
import it.interlogic.vimp.data.jpa.model.PLFImpresaTranslationEntity;
import it.interlogic.vimp.data.jpa.repository.PLFImpresaJpaRepository;
import it.interlogic.vimp.data.jpa.repository.PLFImpresaTranslationJpaRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.i18n.LocaleContextHolder;
import org.springframework.stereotype.Component;

@Component
public class PLFImpresaTranslationDao {

    @Autowired
    PLFImpresaJpaRepository repository;

    @Autowired
    PLFImpresaTranslationJpaRepository translationRepository;

    @Value("${locales}")
    String[] locales;

    //@Transactional
    public void saveOrUpdateTranslation(PLFImpresaEntity t){
        if(t.getIdPlfImpresa()!=null){
            repository.save(t);
            translationRepository.save(t.getImpresaTranslation());
        } else {
            PLFImpresaTranslationEntity tr = t.getImpresaTranslation();
            t.setImpresaTranslation(null);
            repository.save(t);
            tr.setIdPlfImpresa(t.getIdPlfImpresa());
            String originalLan = LocaleContextHolder.getLocale().toString();
            for(String locale : locales){
                LanguageContext.getInstance().setLanguage(locale);
                translationRepository.save(tr);
            }
            LanguageContext.getInstance().setLanguage(originalLan);
        }
    }
}
