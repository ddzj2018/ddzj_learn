package open.thl.other;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.apache.commons.lang3.StringUtils;
import org.apache.http.HttpEntity;
import org.apache.http.NameValuePair;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.message.BasicNameValuePair;
import org.apache.http.util.EntityUtils;

public class HttpClientExample {
	final static private String charset = "UTF-8";
	/**
	 * get 请求
	 * @param url
	 * @return
	 */
	public static String httpGetRequest(String url) {
		CloseableHttpClient httpclient = HttpClients.createDefault();
		String result = null;
		try {
			HttpGet get = new HttpGet(url);
			CloseableHttpResponse response = httpclient.execute(get);
			if (response != null) {
				HttpEntity resEntity = response.getEntity();
				if (resEntity != null) {
					result = EntityUtils.toString(resEntity, charset);
				}
			}
			response.close();
			httpclient.close();
		} catch (Exception e) {
			System.out.println(e);
		}
		return result;
	}
	/**
	 * post 请求
	 * @param url
	 * @param jsonParam "{\"qmIds\":[12,12,12]}"
	 * @param formMap
	 * @return
	 */
	public static String httpPostRequest(String url,String jsonParam,Map<String,Object> formMap) {
		CloseableHttpClient httpclient = HttpClients.createDefault();
		String result = null;
		try {
			HttpPost post = new HttpPost(url);
			if(StringUtils.isNotBlank(jsonParam)){
				post.setEntity(new StringEntity(jsonParam, charset));
			}else if(null!=formMap&&formMap.size()>0){
				List<NameValuePair> formparams = new ArrayList<NameValuePair>();
				for(Map.Entry<String, Object> map:formMap.entrySet()){
					formparams.add(new BasicNameValuePair(map.getKey(), map.getValue().toString()));
				}
				UrlEncodedFormEntity uefEntity = new UrlEncodedFormEntity(formparams, charset);
				post.setEntity(uefEntity);
			}
			CloseableHttpResponse response = httpclient.execute(post);
			if (response != null) {
				HttpEntity resEntity = response.getEntity();
				if (resEntity != null) {
					result = EntityUtils.toString(resEntity, charset);
				}
			}
			response.close();
			httpclient.close();
		} catch (Exception e) {
			System.out.println(e);
		}
		return result;
	}

	public static void main(String[] args) {
//		httpGetRequest("http://www.baidu.com/");
		System.out.println(httpPostRequest("http://www.baidu.com/","{\"qmIds\":[539,607,147]}",null));
		
	}
}
