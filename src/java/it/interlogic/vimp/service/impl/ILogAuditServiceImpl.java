package it.interlogic.vimp.service.impl;

import it.interlogic.vimp.data.jpa.model.PLFTLogAuditEntity;
import it.interlogic.vimp.data.jpa.repository.PLFTLogAuditJpaRepository;
import it.interlogic.vimp.service.ILogAuditService;

import java.math.BigDecimal;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service("iLogAuditService")
public class ILogAuditServiceImpl implements ILogAuditService
{
	
	@Autowired
	PLFTLogAuditJpaRepository auditRepository;

	@Override
	public BigDecimal insertLog(PLFTLogAuditEntity log)
	{
		return auditRepository.save(log).getIdLogAudit();
	}

}
