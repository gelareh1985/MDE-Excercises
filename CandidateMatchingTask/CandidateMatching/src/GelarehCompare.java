import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.eclipse.emf.ecore.EObject;

public class GelarehCompare implements IModelElementCompare {

	@Override
	public int compare(EObject objA, EObject objB) {
		
		if (objA.eClass() != objB.eClass()) {
			return 0;
		}

		String strA = "", strB = "";
		strA = CompareUtil.getName(objA);
		strB = CompareUtil.getName(objB);
		
		int lenA=strA.length();
		int lenB=strB.length();

		if ((strA == null) || (strB == null)) {
			return 0;
		}

		if ((strA.isEmpty()) || (strB.isEmpty())) {
			return 0;
		}
//////////////////////////////////////////////////////////////////////////////////////////////

	    int [] compareResultAtoB= new int[]{lenA,lenB,method2(strA, strB)};
	    int [] compareResultBtoA= new int[]{lenB,lenA,method2(strB, strA)};	

	    //		System.out.println("  returned Values of Compare Method " + compareResult1[0] + "  "
	    //		+ compareResult1[1] + "  " + compareResult1[2] + "  ");
	    int d1 = 0, d2 = 0;
	    
	    if (compareResultAtoB[0] != 0) {
	    	d1 = ((compareResultAtoB[1] * compareResultAtoB[2]) * 100) / compareResultAtoB[0];
	    	d2 = ((compareResultBtoA[1] * compareResultBtoA[2]) * 100) / compareResultBtoA[0];
	    }

	    if (d1 + d2 > 0) {

	    	//    d = (compareResult1[2] > compareResult2[2]) ? compareResult1[2] : compareResult2[2];
	    	if (d1 > d2) {
	    		System.out.println("Result of comparison(%):(Method2) " + d1 + "  " + objB + "\n\n\n");
	    		return d1;
	    	} else {
	    		System.out.println("Result of comparison(%):(Method2)" + d2 + "  " + objB + "\n\n\n");
	    		return d2;
	    	}

	    }
		
	    return 0;
	}

	private int method2(String strA, String strB) {
		// TODO Auto-generated method stub
		Pattern pattern = Pattern.compile(strB);
		Matcher matcher = pattern.matcher(strA);

		int count = 0;

		int pos = 0;

		while (matcher.find(pos))
		{
			count++;
			pos = matcher.start() + 1;
		}

		System.out.println(" number of occurences = "+ count+"\n");
		return count;
	}

}
