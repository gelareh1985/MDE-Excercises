package stateMachineEditRules;

import org.eclipse.equinox.app.IApplication;
import org.eclipse.equinox.app.IApplicationContext;

public class ApplicationExecutor implements IApplication {

	@Override
	public Object start(IApplicationContext context) throws Exception {
		
		ApplicationExecutorApp appExe = new ApplicationExecutorApp();
		appExe.start();
		
		return null;
	}

	@Override
	public void stop() {
	}
}
