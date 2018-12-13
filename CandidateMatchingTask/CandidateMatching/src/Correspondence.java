import org.eclipse.emf.ecore.EObject;

public class Correspondence {

	private EObject modelElement1;
	private EObject modelElement2;

	public Correspondence(EObject modelElement1, EObject modelElement2) {
		super();
		this.modelElement1 = modelElement1;
		this.modelElement2 = modelElement2;
	}

	public EObject getModelElement1() {
		return modelElement1;
	}

	public void setModelElement1(EObject modelElement1) {
		this.modelElement1 = modelElement1;
	}

	public EObject getModelElement2() {
		return modelElement2;
	}

	public void setModelElement2(EObject modelElement2) {
		this.modelElement2 = modelElement2;
	}

	@Override
	public String toString() {
		return "Correspondence [modelElement1=" + modelElement1 + ", modelElement2=" + modelElement2 + "]";
	}
	
	

}
