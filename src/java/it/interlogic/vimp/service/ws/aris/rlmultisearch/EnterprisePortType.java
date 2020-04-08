package it.interlogic.vimp.service.ws.aris.rlmultisearch;

public interface EnterprisePortType extends java.rmi.Remote {
    public RLSearchResult getMultiRL(java.lang.String partitaIVA_codiceFiscale, java.lang.String cognomeRL, java.lang.String cfRL, java.lang.String rl_sn) throws java.rmi.RemoteException;
}
