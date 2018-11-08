/**
 */
package exercises;

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
 *   <li>{@link exercises.DFA#getStates <em>States</em>}</li>
 *   <li>{@link exercises.DFA#getTransition <em>Transition</em>}</li>
 * </ul>
 *
 * @see exercises.ExercisesPackage#getDFA()
 * @model
 * @generated
 */
public interface DFA extends EObject {
	/**
	 * Returns the value of the '<em><b>States</b></em>' containment reference list.
	 * The list contents are of type {@link exercises.State}.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>States</em>' containment reference list isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>States</em>' containment reference list.
	 * @see exercises.ExercisesPackage#getDFA_States()
	 * @model containment="true"
	 * @generated
	 */
	EList<State> getStates();

	/**
	 * Returns the value of the '<em><b>Transition</b></em>' containment reference list.
	 * The list contents are of type {@link exercises.Transition}.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Transition</em>' containment reference list isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Transition</em>' containment reference list.
	 * @see exercises.ExercisesPackage#getDFA_Transition()
	 * @model containment="true"
	 * @generated
	 */
	EList<Transition> getTransition();

} // DFA
