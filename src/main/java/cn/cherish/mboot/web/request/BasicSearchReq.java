/**
 * 蔡大哥在此
 * 联系方式：18826137274/785427346@qq.com
 * @date 2016年8月18日 下午5:01:05
 * @author Cherish
 * @version
 */
package cn.cherish.mboot.web.request;

import java.io.Serializable;

/**
 * 类描述：
 * 联系方式：18826137274/785427346@qq.com
 * @author Cherish
 * @date 2016年8月18日 下午5:01:05
 * @version 1.0
 */
public class BasicSearchReq implements Serializable {

	/**
	 * 判断本对象是否下列属性均为空
	 * @return boolean
	 * @date 2016年9月25日 下午5:27:06
	 */
	public boolean isBlank(){
		
		if( null != this.getStartIndex() )
			return false;
		if( null != this.getPageSize() )
			return false;
		
		return true;
	}
	
	@Override
	public String toString() {
		return "BasicSearchDto [startIndex=" + startIndex + ", pageSize=" + pageSize + ", draw=" + draw + "]";
	}
	private static final long serialVersionUID = -1707407604592617342L;

	//orderColumn=name&orderDir=desc&
	private String orderColumn;
	private String orderDir;
	//startIndex=0&pageSize=10&draw=2
	private Integer startIndex = 0;
	private Integer pageSize = 20;
	private String draw;
	
	public String getOrderColumn() {
		return orderColumn;
	}
	public void setOrderColumn(String orderColumn) {
		this.orderColumn = orderColumn;
	}
	public String getOrderDir() {
		return orderDir;
	}
	public void setOrderDir(String orderDir) {
		this.orderDir = orderDir;
	}
	
	public Integer getStartIndex() {
		return startIndex;
	}
	public void setStartIndex(Integer startIndex) {
		this.startIndex = startIndex;
	}
	public Integer getPageSize() {
		return pageSize;
	}
	public void setPageSize(Integer pageSize) {
		this.pageSize = pageSize;
	}
	public String getDraw() {
		return draw;
	}
	public void setDraw(String draw) {
		this.draw = draw;
	}
	
}
