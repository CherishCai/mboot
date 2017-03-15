package cn.cherish.mboot.web;

import cn.cherish.mboot.dal.entity.WeixinUser;
import cn.cherish.mboot.extra.weixin4j.OAuthInfo;
import cn.cherish.mboot.extra.weixin4j.UserInfo;
import cn.cherish.mboot.extra.weixin4j.WeixinConfig;
import cn.cherish.mboot.extra.weixin4j.WeixinUtil;
import cn.cherish.mboot.extra.weixinjs.Sign;
import cn.cherish.mboot.service.CustomerService;
import cn.cherish.mboot.service.WeixinUserService;
import cn.cherish.mboot.util.SessionUtil;
import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.persistence.NoResultException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.util.Date;
import java.util.Map;

@Controller
@RequestMapping(value = "/api")
public class ApiController extends ABaseController {


	private WeixinUserService weixinUserService;
	private CustomerService customerService;

	/**
	 * 提起授权
	 * @param response
	 * @date 2016年8月16日 下午1:33:28
	 */
	@RequestMapping(value = "/toAuth")
	public void toAuth(HttpServletResponse response) {
		response.setContentType("text/html;charset=utf-8");
		try {
			if (SessionUtil.getCustomer() != null) {
				response.sendRedirect(WeixinConfig.getValue("indexURL"));
			} else {
				response.sendRedirect(WeixinUtil.getLoginUrl());
			}
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	/**
	 * 授权回调接口，获取微信用户信息保存到数据库
	 * @param code
	 * @param session
	 * @param response
	 * @date 2016年8月16日 下午1:13:47
	 */
	@RequestMapping("/authCallback")
	public void authCallback(String code, HttpSession session, HttpServletResponse response) {
		
		OAuthInfo oAuthInfo = WeixinUtil.getOAuthOpenid(code);
		WeixinUser weixinUser = null;
		try {
			weixinUser = weixinUserService.findByOpenid(oAuthInfo.getOpenid());
		} catch (NoResultException e1) {
			LOGGER.info("openid:{}第一次登陆本系统,数据库没有对应的weixinUser数据", oAuthInfo.getOpenid());
			e1.printStackTrace();
		}

		try {
			if (weixinUser == null || weixinUser.getId() == null) {
				UserInfo userInfo = WeixinUtil.getUserInfo(oAuthInfo.getOpenid(), oAuthInfo.getAccessToken());
				weixinUser = new WeixinUser();
				weixinUser.setOpenid(oAuthInfo.getOpenid());
				if (userInfo != null) {
					weixinUser.setCity(userInfo.getCity());
					weixinUser.setHeadimgurl(userInfo.getHeadimgurl());
					weixinUser.setNickname(userInfo.getNickname());
					weixinUser.setSex(userInfo.getSex().shortValue());
					weixinUser.setSubscribeTime(new Date());
				}
				LOGGER.info("openid:{} 保存weixinUser到数据库", oAuthInfo.getOpenid());
				weixinUser = weixinUserService.save(weixinUser);
			}

			SessionUtil.addWeixinUser(weixinUser);

			//如果还没有关联用户的，要去注册一个客户作为绑定
			if (customerService.findByWeixinUserId(weixinUser.getId()) != null) {
				response.sendRedirect(WeixinConfig.getValue("registerURL"));
			} else {
				response.sendRedirect(WeixinConfig.getValue("indexURL"));
			}
		} catch (Exception e) {
			e.printStackTrace();
			LOGGER.error("openid: {} -> {}", oAuthInfo.getOpenid(), e.getMessage());
		}
	}
	
	/**
	 * 获取JssdkInitData
	 * @return Map<String,String>
	 * @date 2016年8月17日 上午10:31:15
	 */
	@RequestMapping(value = "/getJsSdkInitData")
	@ResponseBody
	public Map getJssdkInitData(HttpServletRequest request, String url) {
		Map<String, String> data =  null;

		//TODO 该做token缓存
		if(StringUtils.isNotBlank(url))
			data = Sign.sign(url);
		else
			data = Sign.sign(WeixinConfig.getValue("indexURL"));
		
		return data;
	}


}
