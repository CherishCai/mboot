/**
 * 蔡大哥在此
 * 联系方式：18826137274/785427346@qq.com
 * @date 2016年8月26日 下午4:00:38
 * @author Cherish
 * @version
 */
package cn.cherish.mboot.dal.vo.admin;

/**
 * 类描述：预约订单编辑信息的传输
 * 联系方式：18826137274/785427346@qq.com
 * @author Cherish
 * @date 2016年8月26日 下午4:00:38
 * @version 1.0
 */
public class OrderEditDto implements java.io.Serializable{
	
	private static final long serialVersionUID = 4972186650473435941L;
	
	private Long id;
	private Short status;
	private String comment;
	private Float fee;
	
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	public Short getStatus() {
		return status;
	}
	public void setStatus(Short status) {
		this.status = status;
	}
	public String getComment() {
		return comment;
	}
	public void setComment(String comment) {
		this.comment = comment;
	}
	public Float getFee() {
		return fee;
	}
	public void setFee(Float fee) {
		this.fee = fee;
	}

}
