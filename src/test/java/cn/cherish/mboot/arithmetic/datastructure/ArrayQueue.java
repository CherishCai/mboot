package cn.cherish.mboot.arithmetic.datastructure;


import cn.cherish.mboot.arithmetic.exception.MyException;

@Deprecated
public class ArrayQueue<T> implements Queue<T> {

	private int capacity = 20;
	private Object[] values;
	private int size;
	private int front;
	private int rear;
	
	public ArrayQueue(){
		values = new Object[capacity];
	}
	
	public ArrayQueue(int capacity) {
		this.capacity = capacity;
		this.values = new Object[capacity];
	}

	public void insert(T value) throws MyException {
		if (!isFull()) {
			values[rear] = value;
			rear++;
			size++;
		}else {
			throw new MyException("队列已满");
		}
	}

	@SuppressWarnings("unchecked")
	public T remove() throws MyException {
		if (isEmpty()) {
			throw new MyException("队列为空");
		}
		T oldValue = (T)values[front];
		front++;
		size--;
		return oldValue;
	}

	@SuppressWarnings("unchecked")
	public T peek() throws MyException {
		if (isEmpty()) {
			throw new MyException("队列为空!");
		}
		return (T)values[front];
	}

	public boolean isEmpty() {
		if(size == 0){
			return true;
		}
		return false;
	}

	public boolean isFull() {
		if (capacity == size) {
			return true;
		}
		return false;
	}

	public int size() {
		return size;
	}

	public static void main(String[] args) {
		Queue<String> arrayQueue = new ArrayQueue<String>();
		for (int i = 0; i < 22; i++) {
			try {
				arrayQueue.insert("你妹" + (i + 1));
			} catch (MyException e) {
				e.printStackTrace();
			}
		}
		try {
			System.out.println(arrayQueue.remove());
			
			System.out.println(arrayQueue.peek());
			System.out.println(arrayQueue.peek());
		} catch (MyException e) {
			e.printStackTrace();
		}

	}
}
