import java.util.*;
class Rendezo implements Comparator {

	//a felulirando compare metodus
	public final int compare(Object a, Object b) {
		return( (String)a ).compareTo((String)b);
	}
	public static List order(ArrayList lst) {
		Collections.sort(lst, new Rendezo());
		return lst;
	}
}