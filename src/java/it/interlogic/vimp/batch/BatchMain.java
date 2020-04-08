package it.interlogic.vimp.batch;

import it.interlogic.vimp.utils.LoggerUtility;

public class BatchMain
{

	public static void main(String[] args)
	{
		String pathFilePMI = null;
		String pathFileStartup = null;
		if (args == null || args.length <= 0)
		{
			
			pathFilePMI = "/Users/Data/job/prologic/genova/doc/batch/ridotti/pminnovative.xls";
			pathFileStartup = "/Users/Data/job/prologic/genova/doc/batch/ridotti/startup.xls";
			//pathFilePMI = "/Users/Data/job/prologic/genova/doc/batch/collaudo/pminnovative.xls";
			//pathFileStartup = "/Users/Data/job/prologic/genova/doc/batch/ridotti/startup.xls";
			
			if (pathFilePMI == null || pathFilePMI.trim().length()<0)
			{
				LoggerUtility.error("Impostare i nomi il percorso dei file in ordine prima PMI e dopo Startup");
				System.exit(-1);
			}
		}
		else
		{
			pathFilePMI = args[0];
			pathFileStartup = args[1];
		}

		Importer engine = new Importer();
		engine.start(pathFilePMI, pathFileStartup);
	}

}
