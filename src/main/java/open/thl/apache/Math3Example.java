package open.thl.apache;

import org.apache.commons.math3.util.MathUtils;

public class Math3Example {
	/**
	 * common use method
	 */
	public static Object funTest(int param) {
		int is =MathUtils.hash(param);
		Boolean bl=MathUtils.equals(1, 2);
		System.out.println(bl);
		return is;
	}
	public static void main(String[] args) {
		System.out.println(funTest(10));
	}
}
