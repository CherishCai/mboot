package cn.cherish.mboot.datastructure;


import cn.cherish.mboot.datastructure.exception.DataStructureException;

public class LinkQueue<T> implements Queue<T> {
	
	private LinkNode<T> first;
	private LinkNode<T> last;
	private int size;
	
	public LinkQueue(){
		first = null;
		last = null;
	}

	public void insert(T value) {
		LinkNode<T> newNode = new LinkNode<T>(value);
		if (isEmpty()) {
			first = newNode;
			last = newNode;
		}else {
			LinkNode<T> oldLast = last;
			oldLast.setNext(newNode);
			last = newNode;
		}
		size++;
	}

	public T remove() throws DataStructureException {
		if (isEmpty()) {
			throw new DataStructureException("队列为空");
		}
		LinkNode<T> oldFirst = first;
		first = oldFirst.getNext();
		size--;
		return oldFirst.getValue();
	}

	public T peek() throws DataStructureException {
		if (isEmpty()) {
			throw new DataStructureException("队列为空!");
		}
		return first.getValue();
	}

	public boolean isEmpty() {
		if(first == null){
			return true;
		}
		return false;
	}

	public int size() {
		return size;
	}
	
	public static void main(String[] args) {
		LinkQueue<String> queue = new LinkQueue<String>();
		for (int i = 0; i < 10000; i++) {
			queue.insert("你妹"+(1+i));
		}
		System.out.println(queue.size());
		try {
			for (int i = 0; i < 5000; i++) {
				queue.remove();
			}
			System.out.println(queue.peek());
			System.out.println(queue.remove());
			System.out.println(queue.peek());
		} catch (DataStructureException e) {
			e.printStackTrace();
		}
		System.out.println(queue.size());
	}
}
