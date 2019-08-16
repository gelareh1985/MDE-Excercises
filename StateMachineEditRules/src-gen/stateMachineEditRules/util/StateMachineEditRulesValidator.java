/**
 */
package stateMachineEditRules.util;

import java.util.Map;

import org.eclipse.emf.common.util.Diagnostic;
import org.eclipse.emf.common.util.DiagnosticChain;
import org.eclipse.emf.common.util.ResourceLocator;

import org.eclipse.emf.ecore.EPackage;

import org.eclipse.emf.ecore.util.EObjectValidator;

import stateMachineEditRules.*;

/**
 * <!-- begin-user-doc -->
 * The <b>Validator</b> for the model.
 * <!-- end-user-doc -->
 * @see stateMachineEditRules.StateMachineEditRulesPackage
 * @generated
 */
public class StateMachineEditRulesValidator extends EObjectValidator {
	/**
	 * The cached model package
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public static final StateMachineEditRulesValidator INSTANCE = new StateMachineEditRulesValidator();

	/**
	 * A constant for the {@link org.eclipse.emf.common.util.Diagnostic#getSource() source} of diagnostic {@link org.eclipse.emf.common.util.Diagnostic#getCode() codes} from this package.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see org.eclipse.emf.common.util.Diagnostic#getSource()
	 * @see org.eclipse.emf.common.util.Diagnostic#getCode()
	 * @generated
	 */
	public static final String DIAGNOSTIC_SOURCE = "stateMachineEditRules";

	/**
	 * A constant with a fixed name that can be used as the base value for additional hand written constants.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	private static final int GENERATED_DIAGNOSTIC_CODE_COUNT = 0;

	/**
	 * A constant with a fixed name that can be used as the base value for additional hand written constants in a derived class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	protected static final int DIAGNOSTIC_CODE_COUNT = GENERATED_DIAGNOSTIC_CODE_COUNT;

	/**
	 * Creates an instance of the switch.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public StateMachineEditRulesValidator() {
		super();
	}

	/**
	 * Returns the package of this validator switch.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	protected EPackage getEPackage() {
		return StateMachineEditRulesPackage.eINSTANCE;
	}

	/**
	 * Calls <code>validateXXX</code> for the corresponding classifier of the model.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	protected boolean validate(int classifierID, Object value, DiagnosticChain diagnostics,
			Map<Object, Object> context) {
		switch (classifierID) {
		case StateMachineEditRulesPackage.TRANSITION:
			return validateTransition((Transition) value, diagnostics, context);
		case StateMachineEditRulesPackage.STATE:
			return validateState((State) value, diagnostics, context);
		case StateMachineEditRulesPackage.DFA:
			return validateDFA((DFA) value, diagnostics, context);
		default:
			return true;
		}
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public boolean validateTransition(Transition transition, DiagnosticChain diagnostics, Map<Object, Object> context) {
		return validate_EveryDefaultConstraint(transition, diagnostics, context);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public boolean validateState(State state, DiagnosticChain diagnostics, Map<Object, Object> context) {
		return validate_EveryDefaultConstraint(state, diagnostics, context);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public boolean validateDFA(DFA dfa, DiagnosticChain diagnostics, Map<Object, Object> context) {
		if (!validate_NoCircularContainment(dfa, diagnostics, context))
			return false;
		boolean result = validate_EveryMultiplicityConforms(dfa, diagnostics, context);
		if (result || diagnostics != null)
			result &= validate_EveryDataValueConforms(dfa, diagnostics, context);
		if (result || diagnostics != null)
			result &= validate_EveryReferenceIsContained(dfa, diagnostics, context);
		if (result || diagnostics != null)
			result &= validate_EveryBidirectionalReferenceIsPaired(dfa, diagnostics, context);
		if (result || diagnostics != null)
			result &= validate_EveryProxyResolves(dfa, diagnostics, context);
		if (result || diagnostics != null)
			result &= validate_UniqueID(dfa, diagnostics, context);
		if (result || diagnostics != null)
			result &= validate_EveryKeyUnique(dfa, diagnostics, context);
		if (result || diagnostics != null)
			result &= validate_EveryMapEntryUnique(dfa, diagnostics, context);
		if (result || diagnostics != null)
			result &= validateDFA_atLeastOneState(dfa, diagnostics, context);
		if (result || diagnostics != null)
			result &= validateDFA_exactlyOneStateMustHaveIsStartTrue(dfa, diagnostics, context);
		if (result || diagnostics != null)
			result &= validateDFA_atLeastOneFinalState(dfa, diagnostics, context);
		if (result || diagnostics != null)
			result &= validateDFA_transitionShouldNotBeLabeledNullOrEmpty(dfa, diagnostics, context);
		return result;
	}

	/**
	 * The cached validation expression for the atLeastOneState constraint of '<em>DFA</em>'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	protected static final String DFA__AT_LEAST_ONE_STATE__EEXPRESSION = "self.states->size() > 0";

	/**
	 * Validates the atLeastOneState constraint of '<em>DFA</em>'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public boolean validateDFA_atLeastOneState(DFA dfa, DiagnosticChain diagnostics, Map<Object, Object> context) {
		return validate(StateMachineEditRulesPackage.Literals.DFA, dfa, diagnostics, context,
				"http://www.eclipse.org/emf/2002/Ecore/OCL/Pivot", "atLeastOneState",
				DFA__AT_LEAST_ONE_STATE__EEXPRESSION, Diagnostic.ERROR, DIAGNOSTIC_SOURCE, 0);
	}

	/**
	 * The cached validation expression for the exactlyOneStateMustHaveIsStartTrue constraint of '<em>DFA</em>'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	protected static final String DFA__EXACTLY_ONE_STATE_MUST_HAVE_IS_START_TRUE__EEXPRESSION = "self.states->select(s|s.isStart)->size()=1";

	/**
	 * Validates the exactlyOneStateMustHaveIsStartTrue constraint of '<em>DFA</em>'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public boolean validateDFA_exactlyOneStateMustHaveIsStartTrue(DFA dfa, DiagnosticChain diagnostics,
			Map<Object, Object> context) {
		return validate(StateMachineEditRulesPackage.Literals.DFA, dfa, diagnostics, context,
				"http://www.eclipse.org/emf/2002/Ecore/OCL/Pivot", "exactlyOneStateMustHaveIsStartTrue",
				DFA__EXACTLY_ONE_STATE_MUST_HAVE_IS_START_TRUE__EEXPRESSION, Diagnostic.ERROR, DIAGNOSTIC_SOURCE, 0);
	}

	/**
	 * The cached validation expression for the atLeastOneFinalState constraint of '<em>DFA</em>'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	protected static final String DFA__AT_LEAST_ONE_FINAL_STATE__EEXPRESSION = "self.states->select(s|s.isEnd)->size()>0";

	/**
	 * Validates the atLeastOneFinalState constraint of '<em>DFA</em>'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public boolean validateDFA_atLeastOneFinalState(DFA dfa, DiagnosticChain diagnostics, Map<Object, Object> context) {
		return validate(StateMachineEditRulesPackage.Literals.DFA, dfa, diagnostics, context,
				"http://www.eclipse.org/emf/2002/Ecore/OCL/Pivot", "atLeastOneFinalState",
				DFA__AT_LEAST_ONE_FINAL_STATE__EEXPRESSION, Diagnostic.ERROR, DIAGNOSTIC_SOURCE, 0);
	}

	/**
	 * The cached validation expression for the transitionShouldNotBeLabeledNullOrEmpty constraint of '<em>DFA</em>'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	protected static final String DFA__TRANSITION_SHOULD_NOT_BE_LABELED_NULL_OR_EMPTY__EEXPRESSION = "self.transitions->forAll(t|not t.input.oclIsUndefined() and t.input<>'')";

	/**
	 * Validates the transitionShouldNotBeLabeledNullOrEmpty constraint of '<em>DFA</em>'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public boolean validateDFA_transitionShouldNotBeLabeledNullOrEmpty(DFA dfa, DiagnosticChain diagnostics,
			Map<Object, Object> context) {
		return validate(StateMachineEditRulesPackage.Literals.DFA, dfa, diagnostics, context,
				"http://www.eclipse.org/emf/2002/Ecore/OCL/Pivot", "transitionShouldNotBeLabeledNullOrEmpty",
				DFA__TRANSITION_SHOULD_NOT_BE_LABELED_NULL_OR_EMPTY__EEXPRESSION, Diagnostic.ERROR, DIAGNOSTIC_SOURCE,
				0);
	}

	/**
	 * Returns the resource locator that will be used to fetch messages for this validator's diagnostics.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public ResourceLocator getResourceLocator() {
		// TODO
		// Specialize this to return a resource locator for messages specific to this validator.
		// Ensure that you remove @generated or mark it @generated NOT
		return super.getResourceLocator();
	}

} //StateMachineEditRulesValidator
