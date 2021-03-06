package cn.cherish.mboot.datastructure;


import cn.cherish.mboot.datastructure.exception.DataStructureException;

/**
 * 蔡梦缘实现的栈
 * @author caimengyuan
 *
 */
public class Stack<T> {

	private int capacity;
	private int top = -1;
	private Object[] objects;
	
	/**
	 * 
	 */
	public Stack(){
		capacity = 20;
		objects = new Object[capacity];
	}
	/**
	 * @param capacity
	 */
	public Stack(int capacity){
		this.capacity = capacity;
		objects = new Object[capacity];
	}
	
	/**
	 * @return
	 */
	public int size(){
		return top + 1;
	}
	
	/**
	 * @return
	 * @throws DataStructureException
	 */
	@SuppressWarnings("unchecked")
	public T peek() throws DataStructureException {
		if (!isEmpty()) {
			return (T) objects[top];
		}else {
			throw new DataStructureException("栈为空！");
		}
	}
	/**
	 * @param object
	 * @throws DataStructureException
	 */
	public void push(T object) throws DataStructureException {
		if (!isFill()) {
			objects[++top] = object;
		}else {
			throw new DataStructureException("不好意思，栈已满！");
		}
	}
	/**
	 * @return
	 * @throws DataStructureException
	 */
	@SuppressWarnings("unchecked")
	public T pop() throws DataStructureException {
		if (!isEmpty()) {
			T object = (T) objects[top];
			top--;
			return object;
		}else {
			throw new DataStructureException("栈为空！");
		}
	}
	/**
	 * @return
	 */
	public boolean isEmpty(){
		if (top == -1) {
			return true;
		}
		return false;
	}
	/**
	 * @return
	 */
	public boolean isFill(){
		if (top == capacity-1) {
			return true;
		}
		return false;
	}
	
	
	
	
	public static void main(String[] args) {
		Stack<String> stack = new Stack<String>();
		for (int i = 0; i < 23; i++) {
			try {
				stack.push(i+"个");
			} catch (DataStructureException e) {
				e.printStackTrace();
			}
		}
		try {
			System.out.println(stack.peek());
			System.out.println(stack.pop());
			System.out.println(stack.pop());
			System.out.println(stack.peek());
		} catch (DataStructureException e) {
			e.printStackTrace();
		}
		System.out.println("栈里含有："+stack.size());
	}
}
 
