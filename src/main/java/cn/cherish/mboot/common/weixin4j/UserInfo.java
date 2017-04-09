package cn.cherish.mboot.common.weixin4j;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class UserInfo {

	private String openid;

	private String nickname;

	private Integer sex;

	private String province;

	private String city;

	private String country;

	private String headimgurl;

}
