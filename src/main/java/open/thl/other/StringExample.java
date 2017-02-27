package open.thl.other;
/**
 * 字符串操作
 * @author zhouchangwei
 *
 */
public class StringExample {
	/**
	 * 清楚字符串中的空格
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
