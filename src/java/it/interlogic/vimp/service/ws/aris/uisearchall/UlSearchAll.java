package it.interlogic.vimp.service.ws.aris.uisearchall;

public interface UlSearchAll extends javax.xml.rpc.Service {

/**
 * Elenco Imprese in Liguria, unita locali comprensivo di albo artigiano
 */
    public java.lang.String getEnterprisePortAddress();

    public EnterprisePortType getEnterprisePort() throws javax.xml.rpc.ServiceException;

    public EnterprisePortType getEnterprisePort(java.net.URL portAddress) throws javax.xml.rpc.ServiceException;
}
