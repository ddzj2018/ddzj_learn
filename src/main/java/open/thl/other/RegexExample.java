package open.thl.other;

import java.util.regex.Matcher;
import java.util.regex.Pattern;
import java.util.regex.PatternSyntaxException;
/**
 * ������ʽ��֤����
 * @author zhouchangwei
 *
 */
public class RegexExample {
	public static void main(String[] args) {
		System.out.println(judgeRegex("2Afd_2fd-2fd"));
	}

	/**
	 * �ж��ַ����Ƿ�ֻ��������1-9��ĸa-zA-Z�»���_�ͺ��-
	 * @param inputStr
	 * @return
	 */
	private static boolean judgeRegex(String inputStr) {
		try {
			String regex = "^[a-zA-Z0-9_-]+$";
			// ���� Pattern ����
			Pattern p = Pattern.compile(regex);
			// ���ڴ��� matcher ����
			Matcher m = p.matcher(inputStr);
			return m.matches();
		} catch (PatternSyntaxException e) {
			e.printStackTrace();
		}
		return false;
		
	}
}
