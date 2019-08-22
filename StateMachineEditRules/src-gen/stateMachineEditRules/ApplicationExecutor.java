package stateMachineEditRules;

import java.io.IOException;
import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Iterator;
import java.util.List;

import org.eclipse.emf.common.util.URI;
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
import org.eclipse.emf.henshin.model.Rule;
import org.eclipse.emf.henshin.model.Unit;
import org.eclipse.equinox.app.IApplication;
import org.eclipse.equinox.app.IApplicationContext;

public class ApplicationExecutor implements IApplication {

	private int counter = 0;
	private DFA stateGraph;
	private EGraph graph;
	private Engine engine;
	private Module mdu;
	private String stateMachineUri = "/home/gmeidanipour/git/MDE-Excercises/StateMachineEditRules/model/DFA_Rules2.xmi";

	private ArrayList<Rule> rules = new ArrayList<>();

	@Override
	public Object start(IApplicationContext context) throws Exception {
		System.out.println("ApplicationExecutor.start()");

		ApplicationExecutor appExe = new ApplicationExecutor();
		appExe.init();
		appExe.doSimulation();

		return null;
	}

	private void init() {
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

		List<EObject> graphObj = new ArrayList<>();

		for (Iterator<EObject> iterator = stateGraph.eAllContents(); iterator.hasNext();) {
			EObject element = iterator.next();
			graphObj.add(element);
			System.out.println("+++++++++++++++  " + element.eClass() + "  +++++++++++++++");
		}

		System.out.println("\n\n");

		for (Unit rule_module : mdu.getUnits()) {
			if (rule_module instanceof Rule) {
				transformRule((Rule) rule_module);
				rules.add((Rule) rule_module);

//    		   mdu.getUnits().add(rule); 
//    		   System.out.println(((Rule) rule_module).getLhs().getNodes().get(0).getName());
			}

		}

		// initialize the henshin interpreter
		graph = new EGraphImpl();
		graph.addTree(stateGraph);
//		graph.addAll(graphObj);
		engine = new EngineImpl();

	}

	private void doSimulation() {
//		while (terminateCondition()) {
//			Rule rule = randomlyGetRule();
			Rule rule = rules.get(0);
			
			Assignment assignment = null; 
			
			// TODO: Set parameter values:
			assignment = new MatchImpl(rule);
			assignment.setParameterValue(rule.getParameter("par_id"), "HalloWelt");
			
			RuleApplication ruleApplication = new RuleApplicationImpl(engine, graph, rule, assignment);
			boolean success = ruleApplication.execute(null);
			
			System.out.println(rule.getName() + ": "+ success);
			
			saveStateMachine(stateGraph.eResource(), counter);
			// Informationen ueber durchgefuehrten Spielzug ausgeben
//			if (success) {
//				// Regel wurde angewendet, d.h. es wurde ein Match gefunden
//				counter++;
//
//				// occurences in the LHS in G:
//				String info = "Spielzug " + counter + ": " + rules.get(0).getName() + "\n";
//				info += ruleApplication.getCompleteMatch().toString();
//				System.out.println(info);
//
//				// Aktuelle Spielsituation speichern
//				saveStateMachine(stateMachineResource, counter);
//			}
//		}
	}

	private Rule randomlyGetRule() {
		// TODO Auto-generated method stub
		int idx = (int) (Math.random() * (double) rules.size());
		return rules.get(idx);
	}

	private boolean terminateCondition() {
		for (EObject root : graph.getRoots()) {
			for (Iterator<EObject> iterator = root.eAllContents(); iterator.hasNext();) {
				EObject obj = iterator.next();
				if (obj.eClass().getName() == null) {
					return false;
				}
			}
		}

		// Kein PacMan-Objekt mehr gefunden
		return true;
	}

	@Override
	public void stop() {
		// TODO Auto-generated method stub

	}

	public static void transformRule(Rule in) {

		for (Node node : in.getLhs().getNodes()) {

			if (node.getName() != null) {
				node.setName("TestName");
				System.out.println("lhs nodes: " + node);
			}
		}
		System.out.println("\n\n");
	}

	private void saveStateMachine(Resource resource, int counter) {
		DecimalFormat df = new DecimalFormat("00");

		String fileName = resource.getURI().trimFileExtension().lastSegment();
		String fileExtension = resource.getURI().fileExtension();
		URI uri = resource.getURI().trimSegments(2).appendSegment("Results")
				.appendSegment(fileName + df.format(counter)).appendFileExtension(fileExtension);

		resource.setURI(uri);

		try {
			resource.save(Collections.EMPTY_MAP);
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

}
