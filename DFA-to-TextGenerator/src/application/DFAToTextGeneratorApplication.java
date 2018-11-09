package application;

import java.util.Iterator;
import java.util.Vector;

import org.eclipse.emf.common.util.URI;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.ecore.resource.Resource;
import org.eclipse.emf.ecore.resource.impl.ResourceSetImpl;
import org.eclipse.equinox.app.IApplication;
import org.eclipse.equinox.app.IApplicationContext;

import exercises.ExercisesPackage;
import exercises.State;
import exercises.Transition;

public class DFAToTextGeneratorApplication implements IApplication{

	private static Vector<State> states=new Vector<State>();
	private static Vector<Transition> transitions = new Vector<Transition>();
	private String pathName="/home/pi/gmeidanipour/git/MDE-Excercises/Exercises/dynamicInstances/DFA.xmi";
	
	@Override
	public Object start(IApplicationContext context) throws Exception {
		// TODO Auto-generated method stub
		Resource modelResource = new ResourceSetImpl().getResource(URI.createFileURI(pathName), true);
		
		for (Iterator<EObject> iterator = modelResource.getAllContents(); iterator.hasNext();) {
			EObject element = iterator.next();
			System.out.println(element.eClass());
			System.out.println(element.eClass().getName());
			System.out.println(element.eClass().getEAllAttributes());
			
//			if (element instanceof State)
			if (element.eClass() == ExercisesPackage.eINSTANCE.getState()) {
				System.out.println(element);
//				((State) element).getId()
				System.out.println("id=" + element.eGet(ExercisesPackage.eINSTANCE.getState_Id()));
			}
		}

		return null;
	}

	@Override
	public void stop() {
		// TODO Auto-generated method stub
		System.out.println("DFAToTextGeneratorApplication.stop()");
	}

}
