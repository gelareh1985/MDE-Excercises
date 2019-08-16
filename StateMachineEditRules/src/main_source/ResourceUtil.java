package main_source;

import java.io.IOException;

import org.eclipse.emf.common.util.URI;
import org.eclipse.emf.ecore.EPackage;
import org.eclipse.emf.ecore.resource.Resource;
import org.eclipse.emf.ecore.resource.ResourceSet;
import org.eclipse.emf.ecore.resource.impl.ResourceSetImpl;
import org.eclipse.emf.ecore.xmi.impl.XMIResourceFactoryImpl;

import stateMachineEditRules.StateMachineEditRulesPackage;

public class ResourceUtil {
	 
//    private static Vector<Arc> checkedArcs = new Vector<Arc>();
//    private static Vector<Node> checkedNodes = new Vector<Node>();
     
    static {
        Resource.Factory.Registry.INSTANCE.getExtensionToFactoryMap().put("xmi", new XMIResourceFactoryImpl());
    }
 
    /**
     * 
     * @param uri
     * @return
     */
    protected static StateMachineEditRulesPackage load(URI uri) throws IOException{
        // create resource set and resource
        ResourceSet resourceSet = new ResourceSetImpl();
                         
        // Register meta-model
        EPackage datamodelPackage =stateMachineEditRules.StateMachineEditRulesPackage.eINSTANCE;
        resourceSet.getPackageRegistry().put(datamodelPackage.getNsURI(), datamodelPackage);
                 
        // Create Resource
        Resource resource = resourceSet.createResource(uri);
        assert resource != null : "resource is null";
 
        // Load Model
        resource.load(null);
 
        // Ensure we have not an empty resource
        assert !resource.getContents().isEmpty() : "resource is empty";
 
        // Ensure we have model element of type StateChart
        assert resource.getContents().get(0) instanceof StateMachineEditRulesPackage : "model element must be of type ERModell";
 
        return (StateMachineEditRulesPackage) resource.getContents().get(0);    
    }
// 
//    /**
//     * 
//     * @param petriNet
//     * @return
//     */
//    // ***** getAllTriggers ---> getAllArcs
//    protected static Vector<String> getAllArcs(PetriNetwork petriNet){
//        Vector<String> arcVec = new Vector<String>();
//         
//        checkedArcs.clear();
//        checkedNodes.clear();
//         
//        for(Node node : petriNet.getNodes()){
//            if(node instanceof Place) {
//                Place tempPlace = (Place) node;
//                //get outgoing transitions
//                List<Arc> arcs = tempPlace.getOutgoing();
//                //get all triggers from transitions
//                for(Arc arc : arcs){
//                    checkedArcs.add(arc);
//                    //if(!(arc.getTokensNo()==0) && (arc.getTokensNo()==0))
//                        arcVec.add(arc.getArc_name());
//                }
//            }else if(node instanceof Transition) {
//                Transition tempCompositeState = (Transition) node;
//                List<Arc> arcs = tempCompositeState.getOutgoing();
//                for(Arc arc : arcs){
//                    checkedArcs.add(arc);
//                    //if(!(arc.getTrigger()==null) && !arc.getTrigger().isEmpty())
//                    arcVec.add(arc.getArc_name());
//                }
//                //arcVec.addAll(ResourceUtil.getTriggerFromCompositeStates(tempCompositeState));
//            }
//        }
//        return arcVec;
//    }
     
     
    /**
     * 
     * @param compositeState
     * @return
     */
/*  protected static Vector<String> getTriggerFromCompositeStates(CompositeState compositeState){
        Vector<String> tempVec = new Vector<String>();
         
        for(State state: compositeState.getSubStates()) {
            if(state instanceof NormalState) {
                NormalState tempNormalState = (NormalState) state;
                //get outgoing transitions
                List<Transition> transitions = tempNormalState.getOutgoing();
                //get all triggers from transitions
                for(Transition trans : transitions){
                    checkedTransitions.add(trans);
                    if(!trans.getTrigger().isEmpty())
                        tempVec.add(trans.getTrigger());
                }
            }else if(state instanceof CompositeState) {
                CompositeState tempCompositeState = (CompositeState) state;
                tempVec.addAll(ResourceUtil.getTriggerFromCompositeStates(tempCompositeState));
                List<Transition> transitions = tempCompositeState.getOutgoing();
                for(Transition trans : transitions){
                    checkedTransitions.add(trans);
                    if(!trans.getTrigger().isEmpty())
                        tempVec.add(trans.getTrigger());
                }
            }
             
        }
         
        return tempVec;
    }*/
}
