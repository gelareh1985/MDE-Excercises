package application;

import java.util.Iterator;
import java.util.Vector;

import org.eclipse.emf.common.util.URI;
import org.eclipse.emf.ecore.EAttribute;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.ecore.resource.Resource;
import org.eclipse.emf.ecore.resource.impl.ResourceSetImpl;
import org.eclipse.equinox.app.IApplication;
import org.eclipse.equinox.app.IApplicationContext;

import exercises.ExercisesPackage;
import exercises.State;
import exercises.Transition;

public class DFAToTextGeneratorApplication implements IApplication{

//	private static Vector<State> states=new Vector<State>();
//	private static Vector<Transition> transitions = new Vector<Transition>();
	private String pathName1="/home/pi/gmeidanipour/git/MDE-Excercises/Exercises/dynamicInstances/DFA.xmi";
	private String pathName2="/home/pi/gmeidanipour/git/MDE-Excercises/Exercises/dynamicInstances/exampleUML.uml";
	private Resource modelResource1,modelResource2;

	@Override
	public Object start(IApplicationContext context) throws Exception {
		// TODO Auto-generated method stub
		modelResource1 = new ResourceSetImpl().getResource(URI.createFileURI(pathName1), true);
		modelResource2 = new ResourceSetImpl().getResource(URI.createFileURI(pathName2), true);

		System.out.println("**************** DFA OutPut (EMF Approach) ****************");
		for (Iterator<EObject> iterator = modelResource1.getAllContents(); iterator.hasNext();) {

			EObject element = iterator.next();
			//			System.out.println(element.eClass());
			//			System.out.println(element.eClass().getName());
			//			System.out.println(element.eClass().getEAllAttributes());
			//			System.out.println(element.eGet(ExercisesPackage.eINSTANCE.getState_IsStart())+" = True");

			dfaSpecificTextGenerator(element);
		}

		System.out.println("**************** DFA OutPut (Generic Approach) ****************");
		for (Iterator<EObject> iterator = modelResource2.getAllContents(); iterator.hasNext();) {

			EObject element = iterator.next();

			genericEcoreTextGenerator(element);
		}

		//		stop();

		return null;
	}

	private void genericEcoreTextGenerator(EObject element) {

		System.out.println(" : " + element.eClass().getName());

		for (EAttribute eat : element.eClass().getEAllAttributes()) {
			System.out.println(eat.getName() + " = " + "\"" + element.eGet(eat) + "\"");
		}

	}

	private void dfaSpecificTextGenerator(EObject element) {

		//		if (element instanceof State)
		if (element.eClass() == ExercisesPackage.eINSTANCE.getState()) {

			System.out.println(" : " + element.eClass().getName());
			//				((State) element).getId()

			// Better:
			System.out.println(ExercisesPackage.eINSTANCE.getState_Id().getName() + " = "+"\""+element.eGet(ExercisesPackage.eINSTANCE.getState_Id())+"\"");
			System.out.println( ExercisesPackage.eINSTANCE.getState_IsStart().getName()+" = "+"\""+element.eGet(ExercisesPackage.eINSTANCE.getState_IsStart())+"\"");
			System.out.println( ExercisesPackage.eINSTANCE.getState_IsEnd().getName()+" = "+"\""+element.eGet(ExercisesPackage.eINSTANCE.getState_IsEnd())+"\""+"\n");

		} else if (element.eClass() == ExercisesPackage.eINSTANCE.getTransition()) {

			System.out.println(" : " + element.eClass().getName());

			// Better:
			System.out.println(ExercisesPackage.eINSTANCE.getTransition_Input().getName() + " = "+"\""+element.eGet(ExercisesPackage.eINSTANCE.getTransition_Input())+"\""+"\n");
		}
	}

	@Override
	public void stop() {
		// TODO Auto-generated method stub
		System.out.println("DFAToTextGeneratorApplication.stop()");
	}

}
