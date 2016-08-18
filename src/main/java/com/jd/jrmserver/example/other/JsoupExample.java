package com.jd.jrmserver.example.other;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.select.Elements;

/**
 * jSOUP�ṩ��һ�����ⲿ����������ҳ��HTML�����н�����API���ܹ���ʹ���߷ǳ������ ����CSSѡ����������HTMLҳ�棬�Ӷ���ȡ��Ҫ�����ݡ�
 * 
 * @author zhouchangwei
 *
 */
public class JsoupExample {
	public static void main(String[] args) {
		try {
			Document doc = Jsoup.connect("http://www.baidu.com/").get();
//			System.out.println(doc);
			Elements newsHeadlines = doc.select("#u1");
			System.out.println(newsHeadlines);
		} catch (Exception e) {
			e.printStackTrace();
		}
		
	}
}
