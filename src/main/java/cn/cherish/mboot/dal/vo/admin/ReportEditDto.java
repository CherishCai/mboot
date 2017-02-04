/**
 * 蔡大哥在此
 * 联系方式：18826137274/785427346@qq.com
 * @date 2016年8月26日 下午4:00:38
 * @author Cherish
 * @version
 */
package cn.cherish.mboot.dal.vo.admin;

/**
 * 类描述：健康报告修改信息的传输
 * 联系方式：18826137274/785427346@qq.com
 * @author Cherish
 * @date 2016年8月26日 下午4:00:38
 * @version 1.0
 */
public class ReportEditDto implements java.io.Serializable{
	
	private static final long serialVersionUID = 5912994820034137937L;
	private Long id;
	private String serviceNo;
	private Short type;
	private String options;
	private String content;
	private Float price;

	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	public String getServiceNo() {
		return serviceNo;
	}
	public void setServiceNo(String serviceNo) {
		this.serviceNo = serviceNo;
	}
	public Short getType() {
		return type;
	}
	public void setType(Short type) {
		this.type = type;
	}
	public String getOptions() {
		return options;
	}
	public void setOptions(String options) {
		this.options = options;
	}
	public String getContent() {
		return content;
	}
	public void setContent(String content) {
		this.content = content;
	}
	public Float getPrice() {
		return price;
	}
	public void setPrice(Float price) {
		this.price = price;
	}

}
