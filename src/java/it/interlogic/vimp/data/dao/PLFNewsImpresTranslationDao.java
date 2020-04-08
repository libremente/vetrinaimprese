package it.interlogic.vimp.data.dao;

import it.interlogic.vimp.amulti.LanguageContext;
import it.interlogic.vimp.data.jpa.model.PLFNewsImpresaEntity;
import it.interlogic.vimp.data.jpa.model.PLFNewsImpresaTranslationEntity;
import it.interlogic.vimp.data.jpa.repository.PLFNewsImpresaJpaRepository;
import it.interlogic.vimp.data.jpa.repository.PLFNewsImpresaTranslationJpaRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.i18n.LocaleContextHolder;
import org.springframework.stereotype.Component;

@Component
public class PLFNewsImpresTranslationDao
{

	@Autowired
	PLFNewsImpresaJpaRepository repository;

	@Autowired
	PLFNewsImpresaTranslationJpaRepository translationRepository;

	@Value("${locales}")
	String[] locales;

	public void saveOrUpdateTranslation(PLFNewsImpresaEntity t)
	{
		if (t.getIdNewsImpresa() != null)
		{
			repository.save(t);
			translationRepository.save(t.getNewsImpresaTranslation());
		}
		else
		{
			PLFNewsImpresaTranslationEntity tr = t.getNewsImpresaTranslation();
			t.setNewsImpresaTranslation(null);
			repository.save(t);
			tr.setIdNewsImpresa(t.getIdNewsImpresa());
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
