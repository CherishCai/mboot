package cn.cherish.mboot.datastructure;


import cn.cherish.mboot.datastructure.exception.DataStructureException;

/**
 * 蔡梦缘之队列接口
 * @author caimengyuan
 */
public interface Queue<T> {
	
	/**
	 * 插入队尾
	 * @throws DataStructureException
	 */
	public void insert(T t) throws DataStructureException;
	
	/**
	 * 删除队列头
	 * @return
	 * @throws DataStructureException
	 */
	public T remove() throws DataStructureException;
	
	/**
	 * 查看队列头
	 * @return
	 * @throws DataStructureException
	 */
	public T peek() throws DataStructureException;
	
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
