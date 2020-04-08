package it.interlogic.vimp.web.dto;

import java.io.Serializable;
import java.util.List;

public class ControllsListItem implements Serializable
{
	private static final long serialVersionUID = 1L;

	public static ControllsListItem getInstance(String idAffidamento, List<ControllsMessageItem> messages)
	{
		ControllsListItem ret = new ControllsListItem();
		ret.setIdAffidamento(idAffidamento);
		ret.setMessages(messages);
		ret.setHasMessage(messages != null && messages.size()>0);
		return ret;
	}
	
	public static ControllsListItem getInstance(String idAffidamento)
	{
		ControllsListItem ret = new ControllsListItem();
		ret.setIdAffidamento(idAffidamento);
		ret.setMessages(null);
		ret.setHasMessage(false);
		return ret;
	}

	private String idAffidamento;
	private List<ControllsMessageItem> messages;
	private boolean hasMessage = false;

	public String getIdAffidamento()
	{
		return idAffidamento;
	}

	public void setIdAffidamento(String idAffidamento)
	{
		this.idAffidamento = idAffidamento;
	}

	public List<ControllsMessageItem> getMessages()
	{
		return messages;
	}

	public void setMessages(List<ControllsMessageItem> messages)
	{
		this.messages = messages;
	}

	public boolean isHasMessage()
	{
		return hasMessage;
	}

	public void setHasMessage(boolean hasMessage)
	{
		this.hasMessage = hasMessage;
	}
	
	
	public boolean hasAllOnlyWarning()
	{
		if (messages != null)
		{
			for (ControllsMessageItem controllsMessageItem : messages)
			{
				if (controllsMessageItem.getTypeMessage()==ControllsMessageItem.ERROR)
				return false;
			}
		}
		return true;
	}

}
