/**
 * 蔡大哥在此
 * 联系方式：18826137274/785427346@qq.com
 * @date 2016年8月18日 下午7:45:20
 * @author Cherish
 * @version
 */
package cn.cherish.mboot.dal.vo.admin;

import java.io.Serializable;
import java.util.Date;

import com.fasterxml.jackson.annotation.JsonFormat;

/**
 * 类描述：
 * 联系方式：18826137274/785427346@qq.com
 * @author Cherish
 * @date 2016年8月18日 下午7:45:20
 * @version 1.0
 */
public class OrderSearchResultDto implements Serializable {
	
	private static final long serialVersionUID = -6171863842739571073L;
	
	private Long id;
	private String nickname;
	private String telephone;
	private String serviceNo;
	private String orderSn;
	private String options;
	@JsonFormat(shape = JsonFormat.Shape.STRING, pattern="yyyy-MM-dd HH:mm:ss", timezone="UTC")
	private Date subscribetime;
	private String memo;
	private short status;
	@JsonFormat(shape = JsonFormat.Shape.STRING, pattern="yyyy-MM-dd HH:mm:ss", timezone="UTC")
	private Date createtime;
	@JsonFormat(shape = JsonFormat.Shape.STRING, pattern="yyyy-MM-dd HH:mm:ss", timezone="UTC")
	private Date modifytime;
	private String comment;
	private float fee;
	
	public OrderSearchResultDto() { }
	
	public OrderSearchResultDto(Long id, String nickname, String telephone, String serviceNo, String orderSn,
			String options, Date subscribetime, String memo, short status, Date createtime, Date modifytime,
			String comment, float fee) {
		this.id = id;
		this.nickname = nickname;
		this.telephone = telephone;
		this.serviceNo = serviceNo;
		this.orderSn = orderSn;
		this.options = options;
		this.subscribetime = subscribetime;
		this.memo = memo;
		this.status = status;
		this.createtime = createtime;
		this.modifytime = modifytime;
		this.comment = comment;
		this.fee = fee;
	}
	
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
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
	public String getServiceNo() {
		return serviceNo;
	}
	public void setServiceNo(String serviceNo) {
		this.serviceNo = serviceNo;
	}
	public String getOrderSn() {
		return orderSn;
	}
	public void setOrderSn(String orderSn) {
		this.orderSn = orderSn;
	}
	public String getOptions() {
		return options;
	}
	public void setOptions(String options) {
		this.options = options;
	}
	public Date getSubscribetime() {
		return subscribetime;
	}
	public void setSubscribetime(Date subscribetime) {
		this.subscribetime = subscribetime;
	}
	public String getMemo() {
		return memo;
	}
	public void setMemo(String memo) {
		this.memo = memo;
	}
	public short getStatus() {
		return status;
	}
	public void setStatus(short status) {
		this.status = status;
	}
	public Date getCreatetime() {
		return createtime;
	}
	public void setCreatetime(Date createtime) {
		this.createtime = createtime;
	}
	public Date getModifytime() {
		return modifytime;
	}
	public void setModifytime(Date modifytime) {
		this.modifytime = modifytime;
	}
	public String getComment() {
		return comment;
	}
	public void setComment(String comment) {
		this.comment = comment;
	}
	public float getFee() {
		return fee;
	}
	public void setFee(float fee) {
		this.fee = fee;
	}
	
	
}
