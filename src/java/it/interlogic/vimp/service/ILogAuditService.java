package it.interlogic.vimp.service;

import it.interlogic.vimp.data.jpa.model.PLFTLogAuditEntity;

import java.math.BigDecimal;

public interface ILogAuditService
{
	/**
	 * @param log
	 * @return
	 */
	public BigDecimal insertLog(PLFTLogAuditEntity log);
}
