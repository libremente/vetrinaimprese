package it.interlogic.vimp.data.dao;

import it.interlogic.vimp.amulti.LanguageContext;
import it.interlogic.vimp.data.jpa.model.PLFImpresaAllegatiEntity;
import it.interlogic.vimp.data.jpa.model.PLFImpresaAllegatiTranslationEntity;
import it.interlogic.vimp.data.jpa.repository.PLFImpresaAllegatiJpaRepository;
import it.interlogic.vimp.data.jpa.repository.PLFImpresaAllegatiTranslationJpaRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.i18n.LocaleContextHolder;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

@Component
public class PLFImpresaAllegatiTranslationDao
{

	@Autowired
	PLFImpresaAllegatiJpaRepository repository;

	@Autowired
	PLFImpresaAllegatiTranslationJpaRepository translationRepository;

	@Value("${locales}")
	String[] locales;

	//@Transactional
	public void saveOrUpdateAllegato(PLFImpresaAllegatiEntity t)
	{
		if (t.getIdImpresaAllegati() != null)
		{
			repository.save(t);
			translationRepository.save(t.getImpresaAllegatiTranslation());
		}
		else
		{
			PLFImpresaAllegatiTranslationEntity tr = t.getImpresaAllegatiTranslation();
			t.setImpresaAllegatiTranslation(null);
			repository.save(t);
			tr.setIdImpresaAllegati(t.getIdImpresaAllegati());
			String originalLan = LocaleContextHolder.getLocale().toString();
			for (String locale : locales)
			{
				LanguageContext.getInstance().setLanguage(locale);
				translationRepository.save(tr);
			}
			LanguageContext.getInstance().setLanguage(originalLan);
		}
	}

	//@Transactional
	public void deleteAllegatoTranslation(PLFImpresaAllegatiEntity t)
	{
		String originalLan = LocaleContextHolder.getLocale().toString();
		
		for (String locale : locales)
		{
			LanguageContext.getInstance().setLanguage(locale);
			translationRepository.delete(t.getIdImpresaAllegati());
		}
		repository.delete(t.getIdImpresaAllegati());
		
		LanguageContext.getInstance().setLanguage(originalLan);
	}
}
