/**
 * 蔡大哥在此
 * 联系方式：18826137274/785427346@qq.com
 * @date 2016年8月30日 下午3:56:09
 * @author Cherish
 * @version
 */
package cn.cherish.mboot.dal.vo.admin;

import java.util.Date;

import com.fasterxml.jackson.annotation.JsonFormat;

/**
 * 类描述：健康报告查询结果
 * 联系方式：18826137274/785427346@qq.com
 * @author Cherish
 * @date 2016年8月30日 下午3:56:09
 * @version 1.0
 */
public class ReportSearchResultDto implements java.io.Serializable{
	
	private static final long serialVersionUID = -357694858601830618L;
	private Long id;
	private String serviceNo;
	private short type;
	private String options;
	private String content;
	private float price;
	@JsonFormat(shape = JsonFormat.Shape.STRING, pattern="yyyy-MM-dd HH:mm:ss", timezone="UTC")
	private Date createtime;
	@JsonFormat(shape = JsonFormat.Shape.STRING, pattern="yyyy-MM-dd HH:mm:ss", timezone="UTC")
	private Date modifytime;
	
	public ReportSearchResultDto() { }
	
	public ReportSearchResultDto(Long id, String serviceNo, short type, String options, String content, float price,
			Date createtime, Date modifytime) {
		this.id = id;
		this.serviceNo = serviceNo;
		this.type = type;
		this.options = options;
		this.content = content;
		this.price = price;
		this.createtime = createtime;
		this.modifytime = modifytime;
	}
	
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
	public short getType() {
		return type;
	}
	public void setType(short type) {
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
	public float getPrice() {
		return price;
	}
	public void setPrice(float price) {
		this.price = price;
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
	
}
