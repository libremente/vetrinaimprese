package it.interlogic.vimp.service.impl;

import it.interlogic.vimp.data.jpa.model.PLFLogEntity;
import it.interlogic.vimp.data.jpa.repository.PLFLogJpaRepository;
import it.interlogic.vimp.service.ILogService;

import java.math.BigDecimal;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service("iLogService")
public class ILogServiceImpl implements ILogService
{
	@Autowired
	PLFLogJpaRepository logRepository;

	@Override
	public void riscriviLog(List<PLFLogEntity> logs)
	{
		if (logs != null && logs.size() > 0)
		{
			logRepository.deleteAll();
			for (PLFLogEntity log : logs)
				logRepository.save(log);
		}
	}

	@Override
	public PLFLogEntity getLogImpresa(BigDecimal idImpresa)
	{
		List<PLFLogEntity> logs = logRepository.findLogImpresa(idImpresa);
		if (logs != null && logs.size() > 0)
			return logs.get(0);
		return null;
	}
	
	@Override
	public PLFLogEntity salvaLog(PLFLogEntity log)
	{
		return logRepository.save(log);
	}
}
