package it.interlogic.vimp.service.ws.aris.uisearchall;

import java.util.Hashtable;

public class EnterprisePortTypeProxy implements EnterprisePortType {
  private String _endpoint = null;
  private EnterprisePortType enterprisePortType = null;
  
  public EnterprisePortTypeProxy() {
    _initEnterprisePortTypeProxy();
  }
  
  public EnterprisePortTypeProxy(String endpoint) {
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
  
  private void _initEnterprisePortTypeProxy() {
    try {
      enterprisePortType = (new UlSearchAllLocator()).getEnterprisePort();
      if (enterprisePortType != null) {
        if (_endpoint != null)
          ((javax.xml.rpc.Stub)enterprisePortType)._setProperty("javax.xml.rpc.service.endpoint.address", _endpoint);
        else
          _endpoint = (String)((javax.xml.rpc.Stub)enterprisePortType)._getProperty("javax.xml.rpc.service.endpoint.address");
      }
      
    }
    catch (javax.xml.rpc.ServiceException serviceException) {}
  }
  
  public String getEndpoint() {
    return _endpoint;
  }
  
  public void setEndpoint(String endpoint) {
    _endpoint = endpoint;
    if (enterprisePortType != null)
      ((javax.xml.rpc.Stub)enterprisePortType)._setProperty("javax.xml.rpc.service.endpoint.address", _endpoint);
    
  }
  
  public EnterprisePortType getEnterprisePortType() {
    if (enterprisePortType == null)
      _initEnterprisePortTypeProxy();
    return enterprisePortType;
  }
  
  public UlSearchAllResult getULAll(java.lang.String partitaIVA_codiceFiscale) throws java.rmi.RemoteException{
    if (enterprisePortType == null)
      _initEnterprisePortTypeProxy();
    return enterprisePortType.getULAll(partitaIVA_codiceFiscale);
  }
  
  
}