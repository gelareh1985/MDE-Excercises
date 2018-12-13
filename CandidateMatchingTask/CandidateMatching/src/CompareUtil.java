import org.eclipse.emf.ecore.EAttribute;
import org.eclipse.emf.ecore.EObject;

public class CompareUtil {

	public static String getName(EObject element) {
		for (EAttribute et : element.eClass().getEAllAttributes()) {
			Object obj = element.eGet(et);
			if (obj instanceof String) {
				String str = (String) obj;
				return str;

			}
		}
		return null;
	}
	
}
