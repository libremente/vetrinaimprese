package it.interlogic.vimp.data.dao;

import it.interlogic.vimp.amulti.LanguageContext;
import it.interlogic.vimp.data.jpa.model.PLFProgettiProdottiEntity;
import it.interlogic.vimp.data.jpa.model.PLFProgettiProdottiTranslationEntity;
import it.interlogic.vimp.data.jpa.repository.PLFProgettiProdottiJpaRepository;
import it.interlogic.vimp.data.jpa.repository.PLFProgettiProdottiTranslationJpaRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.i18n.LocaleContextHolder;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

@Component
public class PLFProgettiProdottiTranslationDao
{

	@Autowired
	PLFProgettiProdottiJpaRepository repository;

	@Autowired
	PLFProgettiProdottiTranslationJpaRepository translationRepository;

	@Value("${locales}")
	String[] locales;

	//@Transactional
	public void saveOrUpdateTranslation(PLFProgettiProdottiEntity t)
	{

		if (t.getIdPlfProgettiProdotti() != null)
		{
			repository.save(t);
			translationRepository.save(t.getProgettiProdottiTranslation());
		}
		else
		{
			PLFProgettiProdottiTranslationEntity tr = t.getProgettiProdottiTranslation();
			t.setProgettiProdottiTranslation(null);
			repository.save(t);
			tr.setIdPlfProgettiProdotti(t.getIdPlfProgettiProdotti());
			
			String originalLan = LocaleContextHolder.getLocale().toString();
			
			for (String locale : locales)
			{
				LanguageContext.getInstance().setLanguage(locale);
				translationRepository.save(tr);
			}
		
            LanguageContext.getInstance().setLanguage(originalLan);
		}

	}

}
