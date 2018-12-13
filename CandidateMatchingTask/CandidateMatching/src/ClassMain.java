import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashSet;
import java.util.Iterator;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;

import org.eclipse.emf.common.util.URI;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.ecore.resource.Resource;
import org.eclipse.emf.ecore.resource.impl.ResourceSetImpl;
import org.eclipse.equinox.app.IApplication;
import org.eclipse.equinox.app.IApplicationContext;

public class ClassMain implements IApplication {

	private String pathName1 = "/home/pi/gmeidanipour/git/MDE-Excercises/Exercises/dynamicInstances/DFA1.xmi";
	private String pathName2 = "/home/pi/gmeidanipour/git/MDE-Excercises/Exercises/dynamicInstances/DFA2.xmi";

	private String pathName3 = "/home/pi/gmeidanipour/git/MDE-Excercises/Exercises/dynamicInstances/exampleUML.uml";
	private String pathName4 = "/home/pi/gmeidanipour/git/MDE-Excercises/Exercises/dynamicInstances/exampleUML2.uml";

	private Resource model1, model2, model3, model4;

	/* **************************************** Main Method ****************************************** */
	@Override
	public Object start(IApplicationContext context) throws Exception {

		model1 = new ResourceSetImpl().getResource(URI.createFileURI(pathName1), true);
		model2 = new ResourceSetImpl().getResource(URI.createFileURI(pathName2), true);

		model3 = new ResourceSetImpl().getResource(URI.createFileURI(pathName3), true);
		model4 = new ResourceSetImpl().getResource(URI.createFileURI(pathName4), true);

		System.out.println("**************** Compare Models OutPut (EMF Approach) ****************");

		Map<EObject, List<Similarity>> candidates = callCandidateMatching(model1, model2, new ManuelCompare());
//		Map<EObject, List<Similarity>> candidates = callCandidateMatching(model1, model2, new GelarehCompare());
		calculateCorrespondences(candidates);

		return null;
	}

	/* ************* This method finds a unique pairing (matching) between model A and model B ****************
	 * 
	 *   i.e. an element of model A should only have one corresponding element in model B 
	 *   and an element of model B should only have one corresponding element in model A.
	 *   
	 *   Implementation: 
	 *   =================
	 *   Store the calculated candidates of model B for each element of model A.
	                Map<EObject, List<EObject>> canditates
	                
	 *   Sort all Elements in the List<EObject> based on the similarity starting with 100%
	 *   Iterate over all elements of model A and assign the best, still available/not already paired element, 
	     element of the model B (List<EObject>)
	   
	 *   Create a Class Correspondence which stores a paired element of model A and B.
	 *   Store all Correspondences in a List<Correspondence> and print them.   
	 * 
	 * ****************************************************************************************************** */

	private List<Correspondence> calculateCorrespondences(Map<EObject, List<Similarity>> candidatesAtoB) {
		System.out.println("ClassMain.calculateCorrespondences()");

		/* sorting the List of Candidates based on their calculated similarities using Java Util Function 'Comparator'
		 * to do so we take advantage of Collections of JavaUtil in which the 'Comparator' Function is embedded.
		 * so we pass the Similarity Objects as the input arguments of the @Overriden method called 'Compare'*/
		for (List<Similarity> listB : candidatesAtoB.values()) {
			Collections.sort(listB, new Comparator<Similarity>() {

				@Override
				public int compare(Similarity similarity1, Similarity similarity2) {
					return similarity2.getSimilarity() - similarity1.getSimilarity();
				}
			});

			// Lambda compare implementation...
			// Collections.sort(listB, (Similarity similarity1, Similarity similarity2) ->
			//		similarity2.getSimilarity() - similarity1.getSimilarity());
		}

		/* in this method we utilize the Map of Keys and Values from the previous method 'callCandidateMatching'
		 * in order to make the list of final pairing sequences of elements (it's like 1-to-1 pairing)
		 * the output of this function is the final list of selected correspondences (just single unique ones)*/

		List<Correspondence> listOfCorrespondencies = new ArrayList<>(); // the output
//		Set<EObject> alreadyCorrespondingElementsOfModelB = new HashSet<>(); 									// *(1): optimized alternative which needs more memory

		Set<EObject> elementOfModelAWithCandidates = candidatesAtoB.keySet();

		/* looping through all elements of ModelA which are embedded as keys of the map for finding the final correspondences (unique pairing of values) by comparing 
		 * the remained non-Paired elements of modelB. If so, they'll be added to the list of Correspondences*/

		for (EObject elementOfModelA : elementOfModelAWithCandidates) {

			for (Similarity similarityB : candidatesAtoB.get(elementOfModelA)) {
				EObject currentElementOfModelB = similarityB.getModelElement();

//				if (!alreadyCorrespondingElementsOfModelB.contains(currentElementOfModelB)) { 					// *(1)
				if (!isAlreadyCorresponding(listOfCorrespondencies, currentElementOfModelB)) {
//					alreadyCorrespondingElementsOfModelB.add(currentElementOfModelB);							// *(1)
					Correspondence elementPair = new Correspondence(elementOfModelA, currentElementOfModelB);
					printElementPairing(elementPair);
					listOfCorrespondencies.add(elementPair);
					break;
				}
			}
		}
		return listOfCorrespondencies;
	}
	
	private boolean isAlreadyCorresponding(List<Correspondence> listOfCorrespondencies, EObject elementB) {
		for (Correspondence correspondence : listOfCorrespondencies) {
			if (correspondence.getModelElement2() == elementB) {
				return true;
			}
		}
		return false;
	}

	/* *************************** This method prints the final paired elements ***************************** */

	private void printElementPairing(Correspondence elementPair) {
		System.out.println(elementPair);
	}

	/* ******************* This method performs Model Matching between model A and model B *********************
	 * 
	 *   i.e. it's needed to find candidates of elements in model A and model B which can be matched   
	     based on the calculation of difference (or delta) achieved by their comparison.
	 *   
	 *   Implementation: 
	 *   =================
	 *   Create a Plug-in Project
	 *   Create an Eclipse IApplication (adding the Extension, creating the run-configuration)
	 *   Load two model instances (modelA and modelB)
	 *   Implement a method: int compare(EObject elementA, EObject elementB) ---->  Returns a value between 0-100
	     0 means no similarity between both elements ; 100 maximum similarity between both elements
	 *    
	 *   Check if the objects are of the same type (.eClass()); otherwise return 0
	 *   Get both String values (nameA and nameB) of the attribute called <name>
	 *   If the attribute does not exist or is <null> return 0
	 *   check if nameA is contained in nameB or vice versa ---->  if no overlap is found return 0; 
	 *   otherwise calculate and return the percentage overlap in terms of characters in both names
			* nameA = “1234567891”
			* nameB = “12345” => 50%
			* nameB = “78” => 20%
	 *   Implement a loop that compares every element of modelA and modelB
	 *   Print the the similar modelB elements for every element of modelA that are different from 0 
	 * 
	 * ******************************************************************************************************* */

	private Map<EObject, List<Similarity>> callCandidateMatching(Resource modelA, Resource modelB,
			IModelElementCompare compareMethod) {

		System.out.println("ClassMain.callCandidateMatching()");
		
		// Map of candidates(ListB) with their corresponding ModelElement(ElementA) is created
		/* List of candidates is created by the constructor class which is responsible to get/set the ModelElement 
		   based on Similarity between an ElementA and all other relating elements of the ModelB. it's like 1-to-many matching*/

		Map<EObject, List<Similarity>> canditatesAtoB = new LinkedHashMap<>();

		for (Iterator<EObject> iteratorA = modelA.getAllContents(); iteratorA.hasNext();) {
			EObject elementA = iteratorA.next();

			// create the candidate list:
			List<Similarity> listB = new ArrayList<>();
			canditatesAtoB.put(elementA, listB);

			for (Iterator<EObject> iteratorB = modelB.getAllContents(); iteratorB.hasNext();) {
				EObject elementB = iteratorB.next();

				int compareResult = compareMethod.compare(elementA, elementB);

				if (compareResult > 0) {
					System.out.println("Result of comparison (" + compareMethod.getClass().getSimpleName() + "): "
							+ compareResult + "\n\tA: " + elementB + "\n\tB: " + elementA + "\n");
					// fill the candidate List based the calculated similarity mentioned above
					listB.add(new Similarity(compareResult, elementB));
				}
			}

		}

		// the returned output of this method is a Map containing the all elements of ModelA as keys and their corresponding List of candidates as values per each 
		return canditatesAtoB;
	}

	@Override
	public void stop() {
	}

}
