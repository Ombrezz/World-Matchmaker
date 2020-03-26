package LemMem.MurdercraftUtils;

public class QueueChecker extends Thread {
	
	private App app;
	
	public void initChecker(App _app)
	{
		app = _app;
	}
	
	public void run()
	{
		while (!Thread.currentThread().isInterrupted()) {
			app.match.CheckMoreQueue();
				try {
					Thread.sleep(app.cfg.getInt("QueueCheckTiming") * 1000);
				} catch (InterruptedException e) {
					Thread.currentThread().interrupt();
				}
			
		}
	}
}
