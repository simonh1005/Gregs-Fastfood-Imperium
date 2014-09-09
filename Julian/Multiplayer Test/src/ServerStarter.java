import Server.ServerWin;


public class ServerStarter extends Thread
{

	public ServerStarter()
	{
		// TODO Auto-generated constructor stub
	}

	@Override
	public void run()
	{
	//	ServerWin server = new ServerWin();
		ServerWin server = ServerWin.getInstance();
		System.out.println("Server closed");
	}
}
