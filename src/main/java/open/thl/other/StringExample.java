package open.thl.other;
/**
 * �ַ�������
 * @author zhouchangwei
 *
 */
public class StringExample {
	/**
	 * ����ַ����еĿո�
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
