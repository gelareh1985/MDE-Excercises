package application;

import org.eclipse.equinox.app.IApplication;
import org.eclipse.equinox.app.IApplicationContext;

public class DFAToTextGeneratorApplication implements IApplication{

	
	@Override
	public Object start(IApplicationContext context) throws Exception {
		// TODO Auto-generated method stub
		System.out.println("DFAToTextGeneratorApplication.start()");
		System.out.println("Hello World!");
		return null;
		
	}

	@Override
	public void stop() {
		// TODO Auto-generated method stub
		System.out.println("DFAToTextGeneratorApplication.stop()");
	}

}
