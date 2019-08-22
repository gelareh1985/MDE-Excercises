package stateMachineEditRules;

import java.io.IOException;

import org.eclipse.emf.common.util.URI;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.ecore.resource.Resource;
import org.eclipse.emf.ecore.resource.ResourceSet;

public class EditRulesUtil {

	@SuppressWarnings("unchecked")
	public static <T> T load(URI uri, ResourceSet resourceSet, Class<T> modelType) throws IOException {

		// Create Resource
		Resource resource = resourceSet.createResource(uri);
		assert resource != null : "resource is null";

		// Load Model
		resource.load(null);

		// Ensure we have not an empty resource
		assert !resource.getContents().isEmpty() : "resource is empty";

		// Ensure we have model element of given type
		assert modelType.isInstance(resource.getContents().get(0)) : "model element must be of type Module";

		EObject model = resource.getContents().get(0);

		return (T) model;
	}

}
