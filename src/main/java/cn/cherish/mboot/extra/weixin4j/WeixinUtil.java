package cn.cherish.mboot.extra.weixin4j;

import com.alibaba.fastjson.JSON;
import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.google.common.base.Throwables;
import lombok.extern.slf4j.Slf4j;
import org.apache.http.HttpEntity;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.util.EntityUtils;

@Slf4j
public class WeixinUtil {

	private static final ObjectMapper mapper;
	static {
		mapper = new ObjectMapper();
		// 设置输入时忽略在JSON字符串中存在但Java对象实际没有的属性
		mapper.disable(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES);
	}
	
	/**
	 * 通过授权的code取得openid
	 * @param code 授权的code
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
			oAuthInfo = mapper.readValue(EntityUtils.toString(entity,"UTF-8"),
					OAuthInfo.class);
		} catch (Exception e) {
            log.error("getOAuthOpenid Fail ",Throwables.getStackTraceAsString(e));
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
			userInfo = mapper.readValue(EntityUtils.toString(entity,"UTF-8"),
					UserInfo.class);
		} catch (Exception e) {
            log.error("getUserInfo Fail ",Throwables.getStackTraceAsString(e));
		}
		return userInfo;
	}
	
	public static String getLoginUrl(){
		//String url=WeixinConfig.getValue("weixinAuthURL")+"?appid="+WeixinConfig.getValue("appid")+"&redirect_uri="+WeixinConfig.getValue("redirectURI")+"&response_type=code&scope=snsapi_base&state=123#wechat_redirect";
		String url=WeixinConfig.getValue("weixinAuthURL")+"?appid="+WeixinConfig.getValue("appid")+"&redirect_uri="+WeixinConfig.getValue("redirectURI")+"&response_type=code&scope=snsapi_userinfo&state=123#wechat_redirect";
       return url;
	}
	
}
