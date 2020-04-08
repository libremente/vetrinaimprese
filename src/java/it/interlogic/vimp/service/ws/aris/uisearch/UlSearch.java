package it.interlogic.vimp.service.ws.aris.uisearch;

public interface UlSearch extends javax.xml.rpc.Service {

/**
 * Elenco Imprese in Liguria, unita locali
 */
    public java.lang.String getEnterprisePortAddress();

    public it.interlogic.vimp.service.ws.aris.uisearch.EnterprisePortType getEnterprisePort() throws javax.xml.rpc.ServiceException;

    public it.interlogic.vimp.service.ws.aris.uisearch.EnterprisePortType getEnterprisePort(java.net.URL portAddress) throws javax.xml.rpc.ServiceException;
}
