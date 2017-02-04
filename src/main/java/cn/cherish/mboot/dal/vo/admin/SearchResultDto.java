/**
 * 蔡大哥在此
 * 联系方式：18826137274/785427346@qq.com
 * @date 2016年8月18日 下午6:57:35
 * @author Cherish
 * @version
 */
package cn.cherish.mboot.dal.vo.admin;

import java.io.Serializable;
import java.util.List;

/**
 * 类描述：
 * 联系方式：18826137274/785427346@qq.com
 * @author Cherish
 * @date 2016年8月18日 下午6:57:35
 * @version 1.0
 */
public class SearchResultDto implements Serializable {

	private static final long serialVersionUID = -5885569857755759552L;
	private String draw;
	private Long total;
	private List<?> pageData;
	
	/**
	 * // 1.每页显示数量(everyPage)  
    private int everyPage;  
    // 3.总页数(totalPage)  
    private int totalPage;  
    // 4.当前页(currentPage)  
    private int currentPage;  
    // 5.起始点(beginIndex)  
    private int beginIndex;  
    // 6.是否有上一页(hasPrePage)  
    private boolean hasPrePage;  
    // 7.是否有下一页(hasNextPage)  
    private boolean hasNextPage;  
	 */
	
	@Override
	public String toString() {
		return "SearchResultDto [draw=" + draw + ", totle=" + total + ", pageData=" + pageData + "]";
	}
	public String getDraw() {
		return draw;
	}
	public void setDraw(String draw) {
		this.draw = draw;
	}
	public Long getTotal() {
		return total;
	}
	public void setTotal(Long total) {
		this.total = total;
	}
	public List<?> getPageData() {
		return pageData;
	}
	public void setPageData(List<?> pageData) {
		this.pageData = pageData;
	}
	

}
