package open.thl.google;

import java.util.HashMap;
import java.util.Map;

import open.thl.domain.User;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

public class GosnExample {
	public static String gosnTest(){
		Gson gosn=new Gson();
		User user=new User("xiaoMing", 10, 10.2);
		String jsonString=gosn.toJson(user);
		System.out.println(jsonString);
		return "";
	}
	public void typeToken() {
		String result="{\"planId\" : \"2241bf464b3e4f1599c13d01549dfbc6\",\"success\" : true}";
		Gson gson=new Gson();
		Map<String, Object> resMap=gson.fromJson(result, new TypeToken<HashMap<String,Object>>(){}.getType());
		System.out.println(resMap);
	}
	public static void main(String[] args) {
		gosnTest();
	}
}
