package com.jd.jrmserver.example.other;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.select.Elements;

/**
 * jSOUP提供了一套与外部互联网的网页（HTML）进行交互的API，能够让使用者非常方便地 利用CSS选择器来解析HTML页面，从而获取需要的内容。
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
