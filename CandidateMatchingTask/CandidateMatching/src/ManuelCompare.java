import org.eclipse.emf.ecore.EObject;

public class ManuelCompare implements IModelElementCompare {

	@Override
	public int compare(EObject objA, EObject objB) {
		if (objA.eClass() != objB.eClass()) {
			return 0;
		}

		String strA = "", strB = "";
		strA = CompareUtil.getName(objA);
		strB = CompareUtil.getName(objB);

		if ((strA == null) || (strB == null)) {
			return 0;
		}

		if ((strA.isEmpty()) || (strB.isEmpty())) {
			return 0;
		}
		
		return method1(strA, strB);
	}

	private int method1(String strA, String strB) {
		int fullValue = -1;
		int partialValue = -1;

		if (strA.contains(strB)) {
			fullValue = strA.length();
			partialValue = strB.length();
		} else if (strB.contains(strA)) {
			fullValue = strB.length();
			partialValue = strA.length();
		}

		if ((fullValue > 0) && (partialValue > 0)) {
			int diff=(int) (((double) partialValue / (double) fullValue) * 100.0);
			System.out.println(" amount of differences = "+ diff+"\n");
			return diff;
		} else {
			return 0;
		}
	}

	
}
