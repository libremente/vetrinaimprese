package it.interlogic.vimp.service.ws.aris.uisearchall;

public class UlSearchAllLocator extends org.apache.axis.client.Service implements UlSearchAll {

/**
 * Elenco Imprese in Liguria, unita locali comprensivo di albo artigiano
 */

    public UlSearchAllLocator() {
    }


    public UlSearchAllLocator(org.apache.axis.EngineConfiguration config) {
        super(config);
    }

    public UlSearchAllLocator(java.lang.String wsdlLoc, javax.xml.namespace.QName sName) throws javax.xml.rpc.ServiceException {
        super(wsdlLoc, sName);
    }

    // Use to get a proxy class for EnterprisePort
    private java.lang.String EnterprisePort_address = "http://imprese.regione.liguria.it/cocoon/ds/webService/getULAll";

    public java.lang.String getEnterprisePortAddress() {
        return EnterprisePort_address;
    }

    // The WSDD service name defaults to the port name.
    private java.lang.String EnterprisePortWSDDServiceName = "EnterprisePort";

    public java.lang.String getEnterprisePortWSDDServiceName() {
        return EnterprisePortWSDDServiceName;
    }

    public void setEnterprisePortWSDDServiceName(java.lang.String name) {
        EnterprisePortWSDDServiceName = name;
    }

    public EnterprisePortType getEnterprisePort() throws javax.xml.rpc.ServiceException {
       java.net.URL endpoint;
        try {
            endpoint = new java.net.URL(EnterprisePort_address);
        }
        catch (java.net.MalformedURLException e) {
            throw new javax.xml.rpc.ServiceException(e);
        }
        return getEnterprisePort(endpoint);
    }


    
    public it.interlogic.vimp.service.ws.aris.uisearchall.EnterprisePortType getEnterprisePort(java.net.URL portAddress) throws javax.xml.rpc.ServiceException {
        try {
            it.interlogic.vimp.service.ws.aris.uisearchall.EnterpriseSoapBindingStub _stub = new it.interlogic.vimp.service.ws.aris.uisearchall.EnterpriseSoapBindingStub(portAddress, this);
            _stub.setPortName(getEnterprisePortWSDDServiceName());
            return _stub;
        }
        catch (org.apache.axis.AxisFault e) {
            return null;
        }
    }
    

    public void setEnterprisePortEndpointAddress(java.lang.String address) {
        EnterprisePort_address = address;
    }

    /**
     * For the given interface, get the stub implementation.
     * If this service has no port for the given interface,
     * then ServiceException is thrown.
     */
    public java.rmi.Remote getPort(Class serviceEndpointInterface) throws javax.xml.rpc.ServiceException {
        try {
            if (EnterprisePortType.class.isAssignableFrom(serviceEndpointInterface)) {
                EnterpriseSoapBindingStub _stub = new EnterpriseSoapBindingStub(new java.net.URL(EnterprisePort_address), this);
                _stub.setPortName(getEnterprisePortWSDDServiceName());
                return _stub;
            }
        }
        catch (java.lang.Throwable t) {
            throw new javax.xml.rpc.ServiceException(t);
        }
        throw new javax.xml.rpc.ServiceException("There is no stub implementation for the interface:  " + (serviceEndpointInterface == null ? "null" : serviceEndpointInterface.getName()));
    }

    /**
     * For the given interface, get the stub implementation.
     * If this service has no port for the given interface,
     * then ServiceException is thrown.
     */
    public java.rmi.Remote getPort(javax.xml.namespace.QName portName, Class serviceEndpointInterface) throws javax.xml.rpc.ServiceException {
        if (portName == null) {
            return getPort(serviceEndpointInterface);
        }
        java.lang.String inputPortName = portName.getLocalPart();
        if ("EnterprisePort".equals(inputPortName)) {
            return getEnterprisePort();
        }
        else  {
            java.rmi.Remote _stub = getPort(serviceEndpointInterface);
            ((org.apache.axis.client.Stub) _stub).setPortName(portName);
            return _stub;
        }
    }

    public javax.xml.namespace.QName getServiceName() {
        return new javax.xml.namespace.QName("http://imprese.regione.liguria.it/cocoon/ds/webService/UlSearchAll.wsdl", "UlSearchAll");
    }

    private java.util.HashSet ports = null;

    public java.util.Iterator getPorts() {
        if (ports == null) {
            ports = new java.util.HashSet();
            ports.add(new javax.xml.namespace.QName("http://imprese.regione.liguria.it/cocoon/ds/webService/UlSearchAll.wsdl", "EnterprisePort"));
        }
        return ports.iterator();
    }

    /**
    * Set the endpoint address for the specified port name.
    */
    public void setEndpointAddress(java.lang.String portName, java.lang.String address) throws javax.xml.rpc.ServiceException {
        
if ("EnterprisePort".equals(portName)) {
            setEnterprisePortEndpointAddress(address);
        }
        else 
{ // Unknown Port Name
            throw new javax.xml.rpc.ServiceException(" Cannot set Endpoint Address for Unknown Port" + portName);
        }
    }

    /**
    * Set the endpoint address for the specified port name.
    */
    public void setEndpointAddress(javax.xml.namespace.QName portName, java.lang.String address) throws javax.xml.rpc.ServiceException {
        setEndpointAddress(portName.getLocalPart(), address);
    }

}
