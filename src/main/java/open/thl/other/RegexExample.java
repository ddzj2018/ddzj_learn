package open.thl.other;

import java.util.regex.Matcher;
import java.util.regex.Pattern;
import java.util.regex.PatternSyntaxException;
/**
 * 正则表达式验证样例
 * @author zhouchangwei
 *
 */
public class RegexExample {
	public static void main(String[] args) {
		System.out.println(judgeRegex("2Afd_2fd-2fd"));
	}

	/**
	 * 判断字符串是否只包含数字1-9字母a-zA-Z下划线_和横杠-
	 * @param inputStr
	 * @return
	 */
	private static boolean judgeRegex(String inputStr) {
		try {
			String regex = "^[a-zA-Z0-9_-]+$";
			// 创建 Pattern 对象
			Pattern p = Pattern.compile(regex);
			// 现在创建 matcher 对象
			Matcher m = p.matcher(inputStr);
			return m.matches();
		} catch (PatternSyntaxException e) {
			e.printStackTrace();
		}
		return false;
		
	}
}
