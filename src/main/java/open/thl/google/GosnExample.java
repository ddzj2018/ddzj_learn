package open.thl.google;

import open.thl.domain.User;

import com.google.gson.Gson;

public class GosnExample {
	public static String gosnTest(){
		Gson gosn=new Gson();
		User user=new User("xiaoMing", 10, 10.2);
		String jsonString=gosn.toJson(user);
		System.out.println(jsonString);
		return "";
	}
	public static void main(String[] args) {
		gosnTest();
	}
}
