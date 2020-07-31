package it.interlogic.vimp.service.ws.aris.uisearchall;


public class TestMain {
	
	public static void main(String[] args) throws Exception
	{
		System.out.println("INIZIO");
		
		
		EnterprisePortTypeProxy service = new EnterprisePortTypeProxy("https://apipublictest.comune.genova.it:443/aris_uisearchall");
		
		
		service.addToHeader("Authorization", "Bearer dca52f84-8b02-3bc8-b240-5b4bedd896b6");

		
		UlSearchAllResult res = service.getULAll("02588250999");
		
		System.out.println(res);
		
		System.out.println("FINE");
	}

}
