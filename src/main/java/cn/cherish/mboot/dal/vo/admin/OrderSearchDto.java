/**
 * 蔡大哥在此
 * 联系方式：18826137274/785427346@qq.com
 * @date 2016年8月18日 下午4:43:09
 * @author Cherish
 * @version
 */
package cn.cherish.mboot.dal.vo.admin;

import java.io.Serializable;
import java.util.Date;

import org.apache.commons.lang3.StringUtils;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.format.annotation.DateTimeFormat.ISO;

/**
 * 类描述：预约订单搜索条件
 * 联系方式：18826137274/785427346@qq.com
 * @author Cherish
 * @date 2016年8月18日 下午4:43:09
 * @version 1.0
 */
public class OrderSearchDto implements Serializable {
	
	/**
	 * 判断本对象是否所有字段均为空
	 * @return boolean
	 * @date 2016年9月25日 下午5:27:06
	 */
	public boolean isBlank(){
		
		if( StringUtils.isNotBlank(this.getOrderSn()) )
			return false;
		if( StringUtils.isNotBlank(this.getTelephone()) )
			return false;
		if( null != this.getStatus() )
			return false;
		if( null != this.getFrom() && null != this.getTo() )
			return false;
		if( StringUtils.isNotBlank(this.getNickname()) )
			return false;
		
		return true;
	}
	
	private static final long serialVersionUID = -5637664376170373391L;
	
	public Date getFrom() {
		return from;
	}
	public void setFrom(Date fromtime) {
		this.from = fromtime;
	}
	public Date getTo() {
		return to;
	}
	public void setTo(Date totime) {
		this.to = totime;
	}
	public String getNickname() {
		return nickname;
	}
	public void setNickname(String nickname) {
		this.nickname = nickname;
	}
	public String getTelephone() {
		return telephone;
	}
	public void setTelephone(String telephone) {
		this.telephone = telephone;
	}
	public Short getStatus() {
		return status;
	}
	public void setStatus(Short status) {
		this.status = status;
	}
	public String getOrderSn() {
		return orderSn;
	}
	public void setOrderSn(String orderSn) {
		this.orderSn = orderSn;
	}
	
	private String orderSn;
	private String nickname;
	private String telephone;
	@DateTimeFormat(pattern="yyyy-MM-dd HH:mm",iso = ISO.DATE_TIME)
	private Date from;
	@DateTimeFormat(pattern="yyyy-MM-dd HH:mm",iso = ISO.DATE_TIME)
	private Date to;
	private Short status;
	

}
