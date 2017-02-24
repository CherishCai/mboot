package cn.cherish.mboot.extra.weixin4j;

import cn.cherish.mboot.util.JsonMapper;
import org.apache.http.HttpEntity;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.util.EntityUtils;

public class WeixinUtil {
	
	private static JsonMapper jsonMapper = JsonMapper.INSTANCE;
	
	/**
	 * 通过授权的code取得openid
	 * @param code
	 * @return OAuthInfo
	 * @date 2016年8月16日 下午12:47:44
	 */
	public static OAuthInfo getOAuthOpenid(String code) {
		OAuthInfo oAuthInfo = null;
		CloseableHttpClient httpClient = HttpClients.createDefault();
		HttpGet httpGet = new HttpGet(WeixinConfig.getValue("accessTokenURL")
				+ "?appid=" + WeixinConfig.getValue("appid") + "&secret="
				+ WeixinConfig.getValue("secret") + "&code=" + code
				+ "&grant_type=authorization_code");
		try {
			CloseableHttpResponse response = httpClient.execute(httpGet);
			HttpEntity entity = response.getEntity();
			oAuthInfo = jsonMapper.fromJson(EntityUtils.toString(entity,"UTF-8"),
					OAuthInfo.class);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return oAuthInfo;
	}
	
	public static UserInfo getUserInfo(String openid,String accessToken) {
		UserInfo userInfo = null;
		CloseableHttpClient httpClient = HttpClients.createDefault();
		HttpGet httpGet = new HttpGet(WeixinConfig.getValue("userInfoURL")
				+ "?access_token=" + accessToken + "&openid="
				+ openid + "&lang=zh_CN");
		try {
			CloseableHttpResponse response = httpClient.execute(httpGet);
			HttpEntity entity = response.getEntity();
			//System.out.println(EntityUtils.toString(entity,"UTF-8"));
			userInfo = jsonMapper.fromJson(EntityUtils.toString(entity,"UTF-8"),
					UserInfo.class);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return userInfo;
	}
	
	public static String getLoginUrl(){
		//String url=WeixinConfig.getValue("weixinAuthURL")+"?appid="+WeixinConfig.getValue("appid")+"&redirect_uri="+WeixinConfig.getValue("redirectURI")+"&response_type=code&scope=snsapi_base&state=123#wechat_redirect";
		String url=WeixinConfig.getValue("weixinAuthURL")+"?appid="+WeixinConfig.getValue("appid")+"&redirect_uri="+WeixinConfig.getValue("redirectURI")+"&response_type=code&scope=snsapi_userinfo&state=123#wechat_redirect";
       return url;
	}	//jsonMapper.fromJson({"openid":"o5klAtyzeg07mU5I6VNsfzsAjLaM","nickname":"小天","sex":0,"language":"zh_CN","city":"","province":"","country":"中国","headimgurl":"http:\/\/wx.qlogo.cn\/mmopen\/fA2qed8mr0ycLGCGn9rhBKvKxFZvURERiaeRKEoX7ZqoABicGI9euJ533NuZ9bWe38dy6HqCPy2Dc6rM44M9Gmu2LIaxaicOicD\/0","privilege":[],"unionid":"ofX80uMCvdpi9cvrLl7MvI4Lqf8Y"},UserInfo.class)
	
}
