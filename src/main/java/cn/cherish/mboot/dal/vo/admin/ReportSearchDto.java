/**
 * 蔡大哥在此
 * 联系方式：18826137274/785427346@qq.com
 * @date 2016年8月18日 下午4:43:09
 * @author Cherish
 * @version
 */
package cn.cherish.mboot.dal.vo.admin;

import java.io.Serializable;

/**
 * 类描述：
 * 联系方式：18826137274/785427346@qq.com
 * @author Cherish
 * @date 2016年8月18日 下午4:43:09
 * @version 1.0
 */
public class ReportSearchDto implements Serializable {

	private static final long serialVersionUID = 5041733975871292288L;
	
	private String options;
	private Short type;
	
	public String getOptions() {
		return options;
	}
	public void setOptions(String options) {
		this.options = options;
	}
	public Short getType() {
		return type;
	}
	public void setType(Short type) {
		this.type = type;
	}

}
