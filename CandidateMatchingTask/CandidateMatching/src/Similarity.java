import org.eclipse.emf.ecore.EObject;

public class Similarity {

	private int similarity = -1;
	
	private EObject modelElement;

	public Similarity(int similarity, EObject modelElement) {
		this.similarity = similarity;
		this.modelElement = modelElement;
	}

	public int getSimilarity() {
		return similarity;
	}

	public void setSimilarity(int similarity) {
		this.similarity = similarity;
	}

	public EObject getModelElement() {
		return modelElement;
	}

	public void setModelElement(EObject modelElement) {
		this.modelElement = modelElement;
	}
	
}
