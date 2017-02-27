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
 * Apache Commons Lang是Apache最著名的JAVA库
 * (GitHub上的代码库)，它是对java.lang的很好扩展，包含了大量非常实用的工具类
 * ，其中用的最多的有StringUtils，DateUtils，NumberUtils
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
 
        // 1.判断两个数据是否相等, 如果内容相同， 顺序相同 则返回 真 
        System.out.println("判断两个数组是否相同: " + ArrayUtils.isEquals(test, test2)); 
        System.out.println("判断数组中是否包含一个对象: " + ArrayUtils.contains(test, "33")); 
         
        // 2.{33,ddffd} 将数组内容以{,}形式输出． 
        System.out.println("输出数组中的数据: "+ArrayUtils.toString(test)); 
         
        System.out.println("讲一个二维数组转换成MAP...."); 
        Map map = ArrayUtils.toMap(new String[][] { { "RED", "#FF0000" }, { "GREEN", "#00FF00" }, { "BLUE", "#0000FF" } }); 
        // 3.toMap 一个数组，但每个元素 Each element of the array 
        // must be either a {@link java.util.Map.Entry} or an Array, 
        // 方式一 下面是遍历map的方式，取得其keySet.iterator(); 
        Iterator it = map.keySet().iterator(); 
        while (it.hasNext()) { 
            String key = (String) it.next(); 
            // it.next()只包含key 
            System.out.println("key:" + key + "value:" + map.get(key)); 
        } 
        System.out.println("讲一个二维数组转换成MAP 打印结束...."); 
        // 方式二,取得其entrySet()集合, 
        Iterator it1 = map.entrySet().iterator(); 
        while (it.hasNext()) { 
            Map.Entry entry = (Map.Entry) it1.next(); 
            // it1.next()中包含key和value 
            System.out.println("key :" + entry.getKey() + "value :" + entry.getValue()); 
        } 
 
        // 4.取得类名 
        System.out.println("取得一个类的名称: "+ ClassUtils.getShortClassName(Test.class)); 
        // 取得其包名 
        System.out.println("取得一个类的包名: "+ ClassUtils.getPackageName(Test.class)); 
        // 5.NumberUtils 
        System.out.println("将一个字符串转换成数字: "+ NumberUtils.toInt("6")); 
        System.out.println("将一个字符串转换成数字, 输入一个默认参数: "+ NumberUtils.toInt("7", 10));// 返回7 如果第一个参数为 null 返回10  
         
        // 6.五位的随机字母和数字 
        System.out.println("取得随机字母和数字: "+RandomStringUtils.randomAlphanumeric(15)); 
        // 7.StringEscapeUtils 
        System.out.println(StringEscapeUtils.unescapeHtml3("</html>")); 
        // 输出结果为&lt;html&gt; 
        System.out.println(StringEscapeUtils.escapeJava("String")); 
        // 8.StringUtils,判断是否是空格字符 
        System.out.println("判断一个字符串是否是 空格: "+StringUtils.isBlank("   ")); 
        // 将数组中的内容以,分隔 
        System.out.println("将数组中的内容以,分隔: "+ StringUtils.join(test, ",")); 
        // 在右边加下字符,使之总长度为6 
        System.out.println("在右边加下字符,使之总长度为6: "+ StringUtils.rightPad("abc", 6, 'T')); 
        // 首字母大写 
        System.out.println("首字母大写: "+ StringUtils.capitalize("abc")); 
        // Deletes all whitespaces from a String 删除所有空格 
        System.out.println("删除所有空格 : "+ StringUtils.deleteWhitespace("   ab  c  ")); 
        // 判断是否包含这个字符 
        System.out.println("判断是否包含这个字符 : "+ StringUtils.contains("abc", "ab")); 
        // 表示左边两个字符 
        System.out.println("取得一个字符串左边的两个字符: "+ StringUtils.left("abc", 2)); 
        System.out.println("取得一个字符串右边的三个字符 : "+ StringUtils.right("abcd", 3)); 
         
         
        System.out.println("把一个字符串转换为BigDecimal对象: " + NumberUtils.createBigDecimal("0.25")); 
        System.out.println("找出最大值: " + NumberUtils.max(new int[]{1,2,3})); 
        System.out.println("JavaHome: " + SystemUtils.getJavaHome()); 
        System.out.println("临时目录位置: " + SystemUtils.getJavaIoTmpDir()); 
         
         
        System.out.println("日期格式处理: " + DateFormatUtils.format(new Date(), "yyyy-MM-dd HH:mm:ss")); 
        System.out.println("日期加 7天: " + DateFormatUtils.format(DateUtils.addDays(new Date(), 7), "yyyy-MM-dd HH:mm:ss")); 
         
    } 
}
