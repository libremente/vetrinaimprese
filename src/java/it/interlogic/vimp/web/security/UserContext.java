package it.interlogic.vimp.web.security;

public class UserContext
{

	public final static String USER_SESSION_KEY = "UserContext.currentUser";

	private final static ThreadLocal<UserContext> threadInstance = new ThreadLocal<UserContext>()
	{
		@Override
		protected UserContext initialValue()
		{
			return new UserContext();
		}
	};

	public static UserContext getInstance()
	{
		return threadInstance.get();
	}

	public static void remove()
	{
		UserContext cf = threadInstance.get();
		cf.clear();
		threadInstance.remove();
	}

	UserContext()
	{
		super();
	}

	public void clear()
	{
		currentUser = null;
	}

	InfoUtente currentUser;

	public static InfoUtente getCurrentUser()
	{
		return getInstance().currentUser;
	}

	public void setCurrentUser(InfoUtente cu)
	{
		currentUser = cu;
	}

	public static boolean isUserLogger()
	{
		return getInstance().currentUser != null;
	}

}
