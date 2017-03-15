package cn.cherish.mboot.extra.weixinjs;

import cn.cherish.mboot.extra.weixin4j.WeixinConfig;
import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONException;
import com.alibaba.fastjson.JSONObject;
import com.google.common.base.Throwables;
import lombok.extern.slf4j.Slf4j;
import org.apache.http.HttpEntity;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.util.EntityUtils;

import java.io.IOException;

@Slf4j
public class WeixinJs {
	
	public static String getAccess_token() {
		return getAccess_token(WeixinConfig.getValue("appid"), WeixinConfig.getValue("secret"));
	}

	public static String getAccess_token(String appid, String appsecret) {

		// 凭证获取(GET)
		String token_url = "https://api.weixin.qq.com/cgi-bin/token?grant_type=client_credential&appid=APPID&secret=APPSECRET";
		String requestUrl = token_url.replace("APPID", appid).replace("APPSECRET", appsecret);
		// 发起GET请求获取凭证
		JSONObject jsonObject = httpsRequestGet(requestUrl);
		String access_token = null;
		if (null != jsonObject) {
			try {
				access_token = jsonObject.getString("access_token");
			} catch (JSONException e) {
				// 获取token失败
			}
		}
		return access_token;//
	}


	/**
	 * 调用微信JS接口的临时票据
	 * @param access_token  接口访问凭证
	 * @return
	 */
	public static String getJsApiTicket(String access_token) {
		String url = "https://api.weixin.qq.com/cgi-bin/ticket/getticket?access_token=ACCESS_TOKEN&type=jsapi";
		String requestUrl = url.replace("ACCESS_TOKEN", access_token);
		// 发起GET请求获取凭证
		JSONObject jsonObject = httpsRequestGet(requestUrl);
		String ticket = null;
		if (null != jsonObject) {
			try {
				ticket = jsonObject.getString("ticket");
			} catch (JSONException e) {
				// 获取token失败
				log.error("获取token失败", Throwables.getStackTraceAsString(e));
			}
		}
		return ticket;
	}

	private static JSONObject httpsRequestGet(String requestUrl) {
		CloseableHttpClient httpClient = HttpClients.createDefault();
		HttpGet httpGet = new HttpGet(requestUrl);
		CloseableHttpResponse response;
		try {
			response = httpClient.execute(httpGet);
			HttpEntity entity = response.getEntity();
			String result = EntityUtils.toString(entity);
			return JSON.parseObject(result);
		} catch (IOException | JSONException e) {
			log.error("httpsRequestGet失败", Throwables.getStackTraceAsString(e));
		}
		return null;
	}
	
	
}
