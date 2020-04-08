package it.interlogic.vimp.service.ws.aris.rlmultisearch;
public interface RLSearch extends javax.xml.rpc.Service {

/**
 * Elenco Rappresentanti Legali Impresa
 */
    public java.lang.String getEnterprisePortAddress();

    public it.interlogic.vimp.service.ws.aris.rlmultisearch.EnterprisePortType getEnterprisePort() throws javax.xml.rpc.ServiceException;

    public it.interlogic.vimp.service.ws.aris.rlmultisearch.EnterprisePortType getEnterprisePort(java.net.URL portAddress) throws javax.xml.rpc.ServiceException;
}
