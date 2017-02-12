package cn.cherish.mboot.arithmetic.datastructure;


import cn.cherish.mboot.arithmetic.exception.MyException;

/**
 * 蔡梦缘之队列接口
 * @author caimengyuan
 */
public interface Queue<T> {
	
	/**
	 * 插入队尾
	 * @throws MyException 
	 */
	public void insert(T t) throws MyException;
	
	/**
	 * 删除队列头
	 * @return
	 * @throws MyException 
	 */
	public T remove() throws MyException;
	
	/**
	 * 查看队列头
	 * @return
	 * @throws MyException 
	 */
	public T peek() throws MyException;
	
	/**
	 * 判断是否为空
	 * @return
	 */
	public boolean isEmpty();
	
	/**
	 * 返回队列的长度
	 * @return
	 */
	public int size();
	
}
