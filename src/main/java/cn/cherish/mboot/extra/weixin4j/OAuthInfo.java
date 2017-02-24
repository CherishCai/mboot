package cn.cherish.mboot.extra.weixin4j;

import com.fasterxml.jackson.annotation.JsonProperty;

public class OAuthInfo {

	@JsonProperty("access_token")
	private String accessToken;
	@JsonProperty("expires_in")
	private String expiresIn;
	@JsonProperty("refresh_token")
	private String refreshToken;
	private String openid;
	private String scope;

	public String getAccessToken() {
		return accessToken;
	}

	public void setAccessToken(String accessToken) {
		this.accessToken = accessToken;
	}

	public String getExpiresIn() {
		return expiresIn;
	}

	public void setExpiresIn(String expiresIn) {
		this.expiresIn = expiresIn;
	}

	public String getRefreshToken() {
		return refreshToken;
	}

	public void setRefreshToken(String refreshToken) {
		this.refreshToken = refreshToken;
	}

	public String getOpenid() {
		return openid;
	}

	public void setOpenid(String openId) {
		this.openid = openId;
	}

	public String getScope() {
		return scope;
	}

	public void setScope(String scope) {
		this.scope = scope;
	}

}
