package it.interlogic.vimp.service.ws.aris.rlmultisearch;


public class TestMain {
	
	public static void main(String[] args) throws Exception
	{
		System.out.println("INIZIO");
		
		
		EnterprisePortTypeProxy service = new EnterprisePortTypeProxy("https://apipublictest.comune.genova.it:443/aris_rlmultisearch/1.0.0");
		service.addToHeader("Authorization", "Bearer dca52f84-8b02-3bc8-b240-5b4bedd896b6");

		
		RLSearchResult res = service.getMultiRL("03279700102","", "", "si");
		
		
		System.out.println(res);
		
		System.out.println("FINE");
	}

}
