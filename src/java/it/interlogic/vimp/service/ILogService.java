package it.interlogic.vimp.service;

import it.interlogic.vimp.data.jpa.model.PLFLogEntity;

import java.math.BigDecimal;
import java.util.List;

public interface ILogService
{
	/**
	 * @param logs
	 */
	void riscriviLog(List<PLFLogEntity> logs);

	/**
	 * @param idImpresa
	 * @return
	 */
	PLFLogEntity getLogImpresa(BigDecimal idImpresa);

	/**
	 * @param log
	 * @return
	 */
	PLFLogEntity salvaLog(PLFLogEntity log);
}
