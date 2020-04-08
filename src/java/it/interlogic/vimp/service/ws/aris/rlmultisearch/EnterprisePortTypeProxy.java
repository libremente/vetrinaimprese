package it.interlogic.vimp.service.ws.aris.rlmultisearch;

import java.util.Hashtable;

public class EnterprisePortTypeProxy implements it.interlogic.vimp.service.ws.aris.rlmultisearch.EnterprisePortType
{
	private String _endpoint = null;
	private it.interlogic.vimp.service.ws.aris.rlmultisearch.EnterprisePortType enterprisePortType = null;

	public EnterprisePortTypeProxy()
	{
		_initEnterprisePortTypeProxy();
	}

	public EnterprisePortTypeProxy(String endpoint)
	{
		_endpoint = endpoint;
		_initEnterprisePortTypeProxy();
	}

	public void addToHeader(String name, String value)
	{
		if (enterprisePortType == null)
			_initEnterprisePortTypeProxy();

		javax.xml.rpc.Stub stub = ((javax.xml.rpc.Stub) enterprisePortType);

		@SuppressWarnings("unchecked")
		Hashtable<String, String> headersStub = (Hashtable<String, String>) stub._getProperty(org.apache.axis.transport.http.HTTPConstants.REQUEST_HEADERS);

		if (headersStub == null)
		{
			headersStub = new Hashtable<String, String>();
			stub._setProperty(org.apache.axis.transport.http.HTTPConstants.REQUEST_HEADERS, headersStub);
		}
		headersStub.put(name, value);
	}

	private void _initEnterprisePortTypeProxy()
	{
		try
		{
			enterprisePortType = (new it.interlogic.vimp.service.ws.aris.rlmultisearch.RLSearchLocator()).getEnterprisePort();
			if (enterprisePortType != null)
			{
				if (_endpoint != null)
					((javax.xml.rpc.Stub) enterprisePortType)._setProperty("javax.xml.rpc.service.endpoint.address", _endpoint);
				else
					_endpoint = (String) ((javax.xml.rpc.Stub) enterprisePortType)._getProperty("javax.xml.rpc.service.endpoint.address");
			}

		}
		catch (javax.xml.rpc.ServiceException serviceException)
		{
		}
	}

	public String getEndpoint()
	{
		return _endpoint;
	}

	public void setEndpoint(String endpoint)
	{
		_endpoint = endpoint;
		if (enterprisePortType != null)
			((javax.xml.rpc.Stub) enterprisePortType)._setProperty("javax.xml.rpc.service.endpoint.address", _endpoint);

	}

	public it.interlogic.vimp.service.ws.aris.rlmultisearch.EnterprisePortType getEnterprisePortType()
	{
		if (enterprisePortType == null)
			_initEnterprisePortTypeProxy();
		return enterprisePortType;
	}

	public it.interlogic.vimp.service.ws.aris.rlmultisearch.RLSearchResult getMultiRL(java.lang.String partitaIVA_codiceFiscale, java.lang.String cognomeRL,
			java.lang.String cfRL, java.lang.String rl_sn) throws java.rmi.RemoteException
	{
		if (enterprisePortType == null)
			_initEnterprisePortTypeProxy();
		return enterprisePortType.getMultiRL(partitaIVA_codiceFiscale, cognomeRL, cfRL, rl_sn);
	}

}