/**
 */
package stateMachineEditRules;

import org.eclipse.emf.common.util.EList;

import org.eclipse.emf.ecore.EObject;

/**
 * <!-- begin-user-doc -->
 * A representation of the model object '<em><b>DFA</b></em>'.
 * <!-- end-user-doc -->
 *
 * <p>
 * The following features are supported:
 * </p>
 * <ul>
 *   <li>{@link stateMachineEditRules.DFA#getStates <em>States</em>}</li>
 *   <li>{@link stateMachineEditRules.DFA#getTransitions <em>Transitions</em>}</li>
 * </ul>
 *
 * @see stateMachineEditRules.StateMachineEditRulesPackage#getDFA()
 * @model
 * @generated
 */
public interface DFA extends EObject {
	/**
	 * Returns the value of the '<em><b>Transitions</b></em>' containment reference list.
	 * The list contents are of type {@link stateMachineEditRules.Transition}.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Transitions</em>' containment reference list.
	 * @see stateMachineEditRules.StateMachineEditRulesPackage#getDFA_Transitions()
	 * @model containment="true"
	 * @generated
	 */
	EList<Transition> getTransitions();

	/**
	 * Returns the value of the '<em><b>States</b></em>' containment reference list.
	 * The list contents are of type {@link stateMachineEditRules.State}.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>States</em>' containment reference list.
	 * @see stateMachineEditRules.StateMachineEditRulesPackage#getDFA_States()
	 * @model containment="true"
	 * @generated
	 */
	EList<State> getStates();

} // DFA
