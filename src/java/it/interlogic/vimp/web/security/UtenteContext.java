package it.interlogic.vimp.web.security;

import it.interlogic.vimp.domain.UtenteDto;

public class UtenteContext
{

	public final static String USER_SESSION_KEY = "UtenteContext.currentUser";
	public final static String USER_SESSION_KEY_TEMPORANY = "UtenteContext.currentUser.temporany";
	
	public final static String USER_PERSONAL_LIST_KEY = "UtenteContext.personal.list";
	public final static String USER_PERSONAL_LIST_PARAMS_KEY = "UtenteContext.personal.list.params";

	private final static ThreadLocal<UtenteContext> threadInstance = new ThreadLocal<UtenteContext>()
	{
		@Override
		protected UtenteContext initialValue()
		{
			return new UtenteContext();
		}
	};

	public static UtenteContext getInstance()
	{
		return threadInstance.get();
	}

	public static void remove()
	{
		UtenteContext cf = threadInstance.get();
		cf.clear();
		threadInstance.remove();
	}

	UtenteContext()
	{
		super();
	}

	public void clear()
	{
		currentUser = null;
	}

	UtenteDto currentUser;

	public static UtenteDto getCurrentUser()
	{
		return getInstance().currentUser;
	}

	public void setCurrentUser(UtenteDto cu)
	{
		currentUser = cu;
	}

	public static boolean isUserLogger()
	{
		return getInstance().currentUser != null;
	}

}
