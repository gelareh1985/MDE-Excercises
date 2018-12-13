import org.eclipse.emf.ecore.EObject;

public interface IModelElementCompare {

	/**
	 * @param objA
	 * @param objB
	 * @return 
	 */
	int compare(EObject objA, EObject objB);
	
}
