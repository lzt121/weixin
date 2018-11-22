package com.auth.util;

import net.sf.json.JSONObject;
import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.util.EntityUtils;

public class AuthUtil {
    public static final String APPID = "wx6dc81dce285a474c";
    public static final String APPSECRET = "1327b887c77b74fa1730de9502d50cba";
    public static JSONObject doGetJson(String url) throws Exception{
        JSONObject jsonObject = null;
        DefaultHttpClient client = new DefaultHttpClient();
        HttpGet httpGet = new HttpGet(url);
        HttpResponse response  = client.execute(httpGet);
        HttpEntity entity = response.getEntity();
        if(entity != null ){
            String result = EntityUtils.toString(entity,"UTF-8");
            jsonObject = JSONObject.fromObject(result);
        }
        httpGet.releaseConnection();
        return  jsonObject;
    }
}
