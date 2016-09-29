package open.thl.other;
/**
 * ×Ö·û´®²Ù×÷
 * @author zhouchangwei
 *
 */
public class StringExample {
	/**
	 * Çå³þ×Ö·û´®ÖÐµÄ¿Õ¸ñ
	 * @param inputStr
	 * @return
	 */
	private static String clearSpace(String inputStr) {
		return inputStr.replaceAll("\\s*", "");
	}
	public static void main(String[] args) {
		System.out.println(clearSpace("sdf dfsdfsdd dd"));
	}
}
