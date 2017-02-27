package open.thl.other;

import java.io.IOException;

import org.apache.http.HttpEntity;
import org.apache.http.client.config.RequestConfig;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.ContentType;
import org.apache.http.entity.mime.MultipartEntityBuilder;
import org.apache.http.entity.mime.content.StringBody;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.util.EntityUtils;

import com.google.common.base.Charsets;

public class Test {
public static void main(String[] args) {
//	Set<String> set=new HashSet<String>();
//	for(int i=0;i<1000000;i++){
//		set.add("xxxxxxxxxxxxxxxxx"+i);
//	}
//	long a=System.currentTimeMillis();
//	for(String str:set){
//		System.out.println(str);
//	}
//	long b=System.currentTimeMillis();
//	
//	Iterator<String> it = set.iterator();
//	while (it.hasNext()) {
//	  System.out.println(it.next());
//	}
//	long c=System.currentTimeMillis();
//	
//	System.out.println(b-a);
//	System.out.println(c-b);
//	Set<String> set=new HashSet<String>();
//	System.out.println(set.contains("sdf"));
//	set.add("sdf");
//	System.out.println(set);
//	System.out.println("====");
//	set.clear();
//	System.out.println(set);
//	Gson gson=new Gson();
//	String sjString="{\"planId\":\"null\",\"code\":1002,\"processCounts\":null,\"arriveCounts\":null,\"openCounts\":null,\"success\":false}";
//	Map<String, Object> map=gson.fromJson(sjString, new TypeToken<HashMap<String,Object>>(){}.getType());
//	System.out.println(map);
	getJdPushResult("f3911af7e65e45b89a698a98a2403c60");
	
}
public static String getJdPushResult(String planId) {
	StringBody token=new StringBody("cfb0736fe6874241a2ac19960085279d",ContentType.create("text/plain", Charsets.UTF_8));
	StringBody planIdBody=new StringBody(planId,ContentType.create("text/plain", Charsets.UTF_8));
	
    CloseableHttpClient httpClient=HttpClients.createDefault();
	HttpPost httpPost=new HttpPost("http:///stats/msgcenter/stats-msg");
	CloseableHttpResponse respose=null;
	String result=null;
	try {
		RequestConfig requestConfig=RequestConfig.custom().setConnectTimeout(3000).build();
		httpPost.setConfig(requestConfig);
		HttpEntity requestEntity=MultipartEntityBuilder.create()
				.addPart("planId", planIdBody)
				.addPart("token", token)
				.build();
		httpPost.setEntity(requestEntity);
		respose=httpClient.execute(httpPost);
		int responseStatus=respose.getStatusLine().getStatusCode();
		if(responseStatus==200){
			HttpEntity resposeEntity=respose.getEntity();
			result=EntityUtils.toString(resposeEntity, Charsets.UTF_8);
		}else{
			result="{\"isSuccess\":false}";
			System.out.println("请求wmpmsg single接口失败，错误代码为: "+responseStatus);
		}
		EntityUtils.consume(requestEntity); //关闭requestEntity流
	} catch (Exception e) {
		System.out.println("==get JdPush Result exception:"+e);
	}finally{
		try {
			httpClient.close();
			if(respose!=null){
				respose.close();
			}
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	System.out.println("==get jdPush result:"+result);
	return result;
	
}
}
