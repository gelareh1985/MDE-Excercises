/**
 */
package stateMachineEditRules;

import org.eclipse.emf.common.util.EList;

import org.eclipse.emf.ecore.EObject;

/**
 * <!-- begin-user-doc -->
 * A representation of the model object '<em><b>State</b></em>'.
 * <!-- end-user-doc -->
 *
 * <p>
 * The following features are supported:
 * </p>
 * <ul>
 *   <li>{@link stateMachineEditRules.State#getId <em>Id</em>}</li>
 *   <li>{@link stateMachineEditRules.State#isIsStart <em>Is Start</em>}</li>
 *   <li>{@link stateMachineEditRules.State#isIsEnd <em>Is End</em>}</li>
 *   <li>{@link stateMachineEditRules.State#getIncomingTransitions <em>Incoming Transitions</em>}</li>
 *   <li>{@link stateMachineEditRules.State#getOutgoingTransitions <em>Outgoing Transitions</em>}</li>
 * </ul>
 *
 * @see stateMachineEditRules.StateMachineEditRulesPackage#getState()
 * @model
 * @generated
 */
public interface State extends EObject {
	/**
	 * Returns the value of the '<em><b>Id</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Id</em>' attribute.
	 * @see #setId(String)
	 * @see stateMachineEditRules.StateMachineEditRulesPackage#getState_Id()
	 * @model transient="true"
	 * @generated
	 */
	String getId();

	/**
	 * Sets the value of the '{@link stateMachineEditRules.State#getId <em>Id</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Id</em>' attribute.
	 * @see #getId()
	 * @generated
	 */
	void setId(String value);

	/**
	 * Returns the value of the '<em><b>Is Start</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Is Start</em>' attribute.
	 * @see #setIsStart(boolean)
	 * @see stateMachineEditRules.StateMachineEditRulesPackage#getState_IsStart()
	 * @model
	 * @generated
	 */
	boolean isIsStart();

	/**
	 * Sets the value of the '{@link stateMachineEditRules.State#isIsStart <em>Is Start</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Is Start</em>' attribute.
	 * @see #isIsStart()
	 * @generated
	 */
	void setIsStart(boolean value);

	/**
	 * Returns the value of the '<em><b>Is End</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Is End</em>' attribute.
	 * @see #setIsEnd(boolean)
	 * @see stateMachineEditRules.StateMachineEditRulesPackage#getState_IsEnd()
	 * @model
	 * @generated
	 */
	boolean isIsEnd();

	/**
	 * Sets the value of the '{@link stateMachineEditRules.State#isIsEnd <em>Is End</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Is End</em>' attribute.
	 * @see #isIsEnd()
	 * @generated
	 */
	void setIsEnd(boolean value);

	/**
	 * Returns the value of the '<em><b>Incoming Transitions</b></em>' reference list.
	 * The list contents are of type {@link stateMachineEditRules.Transition}.
	 * It is bidirectional and its opposite is '{@link stateMachineEditRules.Transition#getTo <em>To</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Incoming Transitions</em>' reference list.
	 * @see stateMachineEditRules.StateMachineEditRulesPackage#getState_IncomingTransitions()
	 * @see stateMachineEditRules.Transition#getTo
	 * @model opposite="to"
	 * @generated
	 */
	EList<Transition> getIncomingTransitions();

	/**
	 * Returns the value of the '<em><b>Outgoing Transitions</b></em>' reference list.
	 * The list contents are of type {@link stateMachineEditRules.Transition}.
	 * It is bidirectional and its opposite is '{@link stateMachineEditRules.Transition#getFrom <em>From</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Outgoing Transitions</em>' reference list.
	 * @see stateMachineEditRules.StateMachineEditRulesPackage#getState_OutgoingTransitions()
	 * @see stateMachineEditRules.Transition#getFrom
	 * @model opposite="from"
	 * @generated
	 */
	EList<Transition> getOutgoingTransitions();

} // State
