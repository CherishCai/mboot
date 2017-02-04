/**
 * 蔡大哥在此
 * 联系方式：18826137274/785427346@qq.com
 * @date 2016年9月24日 下午12:54:13
 * @author Cherish
 * @version
 */
package cn.cherish.mboot.dal.vo.admin;

/**
 * 类描述：
 * 联系方式：18826137274/785427346@qq.com
 * @author Cherish
 * @date 2016年9月24日 下午12:54:13
 * @version 1.0
 */
public class WebsocketMessageDto implements java.io.Serializable {

	private static final long serialVersionUID = 1077158065924524348L;
	
	private Long orderId;
	private String operation;
	private String msg;
	
	public WebsocketMessageDto() { }
	
	public WebsocketMessageDto(Long orderId, String operation, String msg) {
		this.orderId = orderId;
		this.operation = operation;
		this.msg = msg;
	}
	
	public Long getOrderId() {
		return orderId;
	}
	public void setOrderId(Long orderId) {
		this.orderId = orderId;
	}
	public String getOperation() {
		return operation;
	}
	public void setOperation(String operation) {
		this.operation = operation;
	}
	public String getMsg() {
		return msg;
	}
	public void setMsg(String msg) {
		this.msg = msg;
	}

}
