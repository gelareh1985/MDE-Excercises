package stateMachineEditRules;

import java.io.BufferedReader;
import java.io.Console;
import java.io.IOException;
import java.io.InputStreamReader;
import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Iterator;
import java.util.List;

import org.eclipse.emf.common.util.URI;
import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.EDataType;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.ecore.resource.Resource;
import org.eclipse.emf.ecore.resource.ResourceSet;
import org.eclipse.emf.ecore.resource.impl.ResourceSetImpl;
import org.eclipse.emf.henshin.interpreter.Assignment;
import org.eclipse.emf.henshin.interpreter.EGraph;
import org.eclipse.emf.henshin.interpreter.Engine;
import org.eclipse.emf.henshin.interpreter.RuleApplication;
import org.eclipse.emf.henshin.interpreter.impl.EGraphImpl;
import org.eclipse.emf.henshin.interpreter.impl.EngineImpl;
import org.eclipse.emf.henshin.interpreter.impl.MatchImpl;
import org.eclipse.emf.henshin.interpreter.impl.RuleApplicationImpl;
import org.eclipse.emf.henshin.model.Module;
import org.eclipse.emf.henshin.model.Node;
import org.eclipse.emf.henshin.model.Parameter;
import org.eclipse.emf.henshin.model.Rule;
import org.eclipse.emf.henshin.model.Unit;
import org.eclipse.equinox.app.IApplication;
import org.eclipse.equinox.app.IApplicationContext;
import org.sidiff.common.emf.modelstorage.EMFStorage;

public class ApplicationExecutorApp {

	private int counter = 0;
	private DFA stateGraph;
	private EGraph graph;
	private Engine engine;
	private Module mdu;
	private String stateMachineUri = "/home/gmeidanipour/git/MDE-Excercises/StateMachineEditRules/model/DFA_Rules3.xmi";

	private ArrayList<Rule> rules = new ArrayList<>();
	private int ruleNumber = 0;

//	private static ArrayList<Node> nodesArr=new ArrayList<Node>();

	public void start() {
		System.out.println("ApplicationExecutor.start()");

		// +++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++

		ResourceSet resourceSet = new ResourceSetImpl();

		String henshinFileAddress = "/StateMachineEditRules/EditRules/edit.rules.dfa.henshin";// StateMachineEditRulesPackage.eNS_URI;
		URI uri = URI.createPlatformPluginURI(henshinFileAddress, true);
		try {
			mdu = EditRulesUtil.load(uri, resourceSet, Module.class);
		} catch (IOException e) {
			e.printStackTrace();
		}

		try {
			stateGraph = EditRulesUtil.load(URI.createFileURI(stateMachineUri), resourceSet, DFA.class);
		} catch (IOException e) {
			e.printStackTrace();
		}

//		List<EObject> graphObj = new ArrayList<>();
//
//		for (Iterator<EObject> iterator = stateGraph.eAllContents(); iterator.hasNext();) {
//			EObject element = iterator.next();
//			graphObj.add(element);
//			System.out.println("+++++++++++++++  " + element.eClass() + "  +++++++++++++++");
//		}

		System.out.println("\n\n");

		for (Unit rule_module : mdu.getUnits()) {
			if (rule_module instanceof Rule) {
//				transformRule((Rule) rule_module);
				rules.add((Rule) rule_module);
			}
		}

		
		System.out.println("+++++++++++Please Select a Rule from following List: +++++++++++ \n\n");
		int r=0;
		for (Rule rl : rules) {
			System.out.println(String.valueOf(r)+ " : " + rl + "\n");
			r++;
		}

		
		
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		try {
			ruleNumber = Integer.parseInt(br.readLine());

		} catch (NumberFormatException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		// +++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++
	
		// initialize the Henshin interpreter
		
		graph = new EGraphImpl();
		graph.addTree(stateGraph);
		engine = new EngineImpl();
		
		if (ruleNumber < rules.size()) {
			doSimulation(rules.get(ruleNumber));
		}
	}

	// TODO: testing consistency preserving edit rules

	private void doSimulation(Rule rule) {

		Assignment assignment = null;

		// TODO: Set parameter values:
		assignment = new MatchImpl(rule);

		System.out.println("\n\n");
		
		
		ArrayList<Node> nodesArr=new ArrayList<Node>();
		for (Node node : rule.getLhs().getNodes()) {

			if (node.getName() != null) {
				System.out.println("lhs nodes: " + node);
				nodesArr.add(node);
			}
		}
		System.out.println("\n\n");

		
		for (Parameter parameter : rule.getParameters()) {
			
			System.out.println("parameters of the rule: "+ parameter+"\n\n");

			if (parameter.getType() instanceof EDataType) {
				// Value parameters: parameter.getType().getName()
				System.out.println("Enter value for parameter " + parameter.getName() + " of type "
						+ parameter.getType().getName() + " : ");

				InputParamsUtil paramType = new InputParamsUtil();
				String str = paramType.inputString();

				// TODO: Set value in the rule assignment:
				assignment.setParameterValue(parameter, str);

			} else if (parameter.getType() instanceof EClass) {

				// TODO: Print all object of the parameter type with index numbers:
				// Object parameters: parameter.getType().getName()
				List<EObject> objectValues = graph.getDomain((EClass) parameter.getType(), false);

				int c=0;
				
				System.out.println("Select object for parameter " + parameter.getName() + " of type "
						+ parameter.getType().getName() + " : in following List \n");
				
				for(EObject eObj:objectValues) {
				System.out.println(String.valueOf(c)+ " : " + eObj);
				c++;
				}
				
				InputParamsUtil paramType = new InputParamsUtil();
				int paramNumber = paramType.inputNumber();

				// TODO: Set the object in the rule assignment:
				if (paramNumber < nodesArr.size()) {
					assignment.setParameterValue(parameter, objectValues.get(paramNumber));
				} else {
					System.out.println("index out of bounds ");
				}
			} else {
				System.err.println("Unsupported Parameter Type: " + parameter);
			}

		}

//			assignment.setParameterValue(rule.getParameter("par_id"), "HalloWelt");
//			assignment.setParameterValue(rule.getParameter(str1), str2);
		
		RuleApplication ruleApplication = new RuleApplicationImpl(engine, graph, rule, assignment);
		boolean success = ruleApplication.execute(null);

//			System.out.println(rule.getName() + ": "+ success);

		saveStateMachine(stateGraph.eResource(), counter);

	}

	public static void transformRule(Rule in) {
//
//		int count=0;
//		ArrayList<Node> nodesArr=new ArrayList<Node>();

		for (Node node : in.getLhs().getNodes()) {

			if (node.getName() != null) {
				System.out.println("lhs nodes: " + node);
//				nodesArr.add(node);
			}
		}
		System.out.println("\n\n");
	}

	private void saveStateMachine(Resource resource, int counter) {

//		DecimalFormat df = new DecimalFormat("00");

		String fileName = resource.getURI().trimFileExtension().lastSegment();
		String fileExtension = resource.getURI().fileExtension();

		URI uri = resource.getURI().trimSegments(2).appendSegment("Results")
				.appendSegment(fileName + "0" + String.valueOf(counter)).appendFileExtension(fileExtension);

		if (!EMFStorage.uriToFile(uri).exists()) {
			System.out.println("file not exists");
			resource.setURI(uri);
			System.out.println(uri);

		} else if (EMFStorage.uriToFile(uri).exists()) {

			System.out.println("file exists");
			URI urii = resource.getURI().trimSegments(2).appendSegment("Results")
					.appendSegment(fileName + "0" + String.valueOf(counter++)).appendFileExtension(fileExtension);
			resource.setURI(urii);
			System.out.println(uri);

		}

		try {
			resource.save(Collections.EMPTY_MAP);
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

}
