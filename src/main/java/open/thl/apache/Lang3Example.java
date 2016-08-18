package open.thl.apache;

import java.util.Date;
import java.util.Iterator;
import java.util.Map;

import junit.framework.Test;

import org.apache.commons.lang3.ArrayUtils;
import org.apache.commons.lang3.ClassUtils;
import org.apache.commons.lang3.RandomStringUtils;
import org.apache.commons.lang3.StringEscapeUtils;
import org.apache.commons.lang3.StringUtils;
import org.apache.commons.lang3.SystemUtils;
import org.apache.commons.lang3.math.NumberUtils;
import org.apache.commons.lang3.time.DateFormatUtils;
import org.apache.commons.lang3.time.DateUtils;

 
/**
 * Apache Commons Lang��Apache��������JAVA��
 * (GitHub�ϵĴ����)�����Ƕ�java.lang�ĺܺ���չ�������˴����ǳ�ʵ�õĹ�����
 * �������õ�������StringUtils��DateUtils��NumberUtils
 * 
 * @author zhouchangwei
 *
 */
public class Lang3Example {
	/**
	 * common use method
	 */
	public static Object funTest(String param) {
		Boolean is=StringUtils.isNotBlank(param);
		return is;
	}
	public static void main(String[] args) {
		String[] test = { "33", "ddffd" }; 
        String[] test2 = { "33", "ddffd" }; 
        String[] test1 = { "ddffd", "33" }; 
 
        // 1.�ж����������Ƿ����, ���������ͬ�� ˳����ͬ �򷵻� �� 
        System.out.println("�ж����������Ƿ���ͬ: " + ArrayUtils.isEquals(test, test2)); 
        System.out.println("�ж��������Ƿ����һ������: " + ArrayUtils.contains(test, "33")); 
         
        // 2.{33,ddffd} ������������{,}��ʽ����� 
        System.out.println("��������е�����: "+ArrayUtils.toString(test)); 
         
        System.out.println("��һ����ά����ת����MAP...."); 
        Map map = ArrayUtils.toMap(new String[][] { { "RED", "#FF0000" }, { "GREEN", "#00FF00" }, { "BLUE", "#0000FF" } }); 
        // 3.toMap һ�����飬��ÿ��Ԫ�� Each element of the array 
        // must be either a {@link java.util.Map.Entry} or an Array, 
        // ��ʽһ �����Ǳ���map�ķ�ʽ��ȡ����keySet.iterator(); 
        Iterator it = map.keySet().iterator(); 
        while (it.hasNext()) { 
            String key = (String) it.next(); 
            // it.next()ֻ����key 
            System.out.println("key:" + key + "value:" + map.get(key)); 
        } 
        System.out.println("��һ����ά����ת����MAP ��ӡ����...."); 
        // ��ʽ��,ȡ����entrySet()����, 
        Iterator it1 = map.entrySet().iterator(); 
        while (it.hasNext()) { 
            Map.Entry entry = (Map.Entry) it1.next(); 
            // it1.next()�а���key��value 
            System.out.println("key :" + entry.getKey() + "value :" + entry.getValue()); 
        } 
 
        // 4.ȡ������ 
        System.out.println("ȡ��һ���������: "+ ClassUtils.getShortClassName(Test.class)); 
        // ȡ������� 
        System.out.println("ȡ��һ����İ���: "+ ClassUtils.getPackageName(Test.class)); 
        // 5.NumberUtils 
        System.out.println("��һ���ַ���ת��������: "+ NumberUtils.toInt("6")); 
        System.out.println("��һ���ַ���ת��������, ����һ��Ĭ�ϲ���: "+ NumberUtils.toInt("7", 10));// ����7 �����һ������Ϊ null ����10  
         
        // 6.��λ�������ĸ������ 
        System.out.println("ȡ�������ĸ������: "+RandomStringUtils.randomAlphanumeric(15)); 
        // 7.StringEscapeUtils 
        System.out.println(StringEscapeUtils.unescapeHtml3("</html>")); 
        // ������Ϊ&lt;html&gt; 
        System.out.println(StringEscapeUtils.escapeJava("String")); 
        // 8.StringUtils,�ж��Ƿ��ǿո��ַ� 
        System.out.println("�ж�һ���ַ����Ƿ��� �ո�: "+StringUtils.isBlank("   ")); 
        // �������е�������,�ָ� 
        System.out.println("�������е�������,�ָ�: "+ StringUtils.join(test, ",")); 
        // ���ұ߼����ַ�,ʹ֮�ܳ���Ϊ6 
        System.out.println("���ұ߼����ַ�,ʹ֮�ܳ���Ϊ6: "+ StringUtils.rightPad("abc", 6, 'T')); 
        // ����ĸ��д 
        System.out.println("����ĸ��д: "+ StringUtils.capitalize("abc")); 
        // Deletes all whitespaces from a String ɾ�����пո� 
        System.out.println("ɾ�����пո� : "+ StringUtils.deleteWhitespace("   ab  c  ")); 
        // �ж��Ƿ��������ַ� 
        System.out.println("�ж��Ƿ��������ַ� : "+ StringUtils.contains("abc", "ab")); 
        // ��ʾ��������ַ� 
        System.out.println("ȡ��һ���ַ�����ߵ������ַ�: "+ StringUtils.left("abc", 2)); 
        System.out.println("ȡ��һ���ַ����ұߵ������ַ� : "+ StringUtils.right("abcd", 3)); 
         
         
        System.out.println("��һ���ַ���ת��ΪBigDecimal����: " + NumberUtils.createBigDecimal("0.25")); 
        System.out.println("�ҳ����ֵ: " + NumberUtils.max(new int[]{1,2,3})); 
        System.out.println("JavaHome: " + SystemUtils.getJavaHome()); 
        System.out.println("��ʱĿ¼λ��: " + SystemUtils.getJavaIoTmpDir()); 
         
         
        System.out.println("���ڸ�ʽ����: " + DateFormatUtils.format(new Date(), "yyyy-MM-dd HH:mm:ss")); 
        System.out.println("���ڼ� 7��: " + DateFormatUtils.format(DateUtils.addDays(new Date(), 7), "yyyy-MM-dd HH:mm:ss")); 
         
    } 
}
