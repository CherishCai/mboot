package cn.cherish.mboot.arithmetic.datastructure;

public class LinkedList<T> {

	private LinkNode<T> first;
	
	private LinkNode<T> last;
	
	private int size;
	
	public LinkedList(){
		first = null;
		last = null;
		size = 0;
	}
	
	public void add(T value){
		LinkNode<T> node = new LinkNode<T>(value);
		if (isEmpty()) {
			//第一次添加
			last = first = node;
			size++;
		}else {
			//后续添加，只需要从后端加入
			LinkNode<T> oldlast = last;
			oldlast.setNext(node);
			last = node;
			size++;
		}
		
	}
	
	public void display(){
		if (!isEmpty()) {
			for (LinkNode<T> node = first;node != null;node = node.getNext()) {
				T val = (T) node.getValue();
				System.out.println(val);
			}
		}else{
			System.out.println("链表为空！");
		}
	}
	/**
	 * 清空链表
	 */
	public void empty(){
		first = null;
		last = null;
		size = 0;
	}
	
	public boolean isEmpty(){
		if (size == 0) {
			return true;
		}
		return false;
	}
	
	public int size(){
		return size;
	}
	
	
	public static void main(String[] args) {
		LinkedList<String> linkedList = new LinkedList<String>();
		linkedList.display();
		System.out.println(linkedList.size()+""+linkedList.isEmpty());
		
		linkedList.add("你妹");
		linkedList.display();
		System.out.println(linkedList.size()+""+linkedList.isEmpty());
		
		for (int i = 0; i < 100; i++) {
			linkedList.add("你"+i+"妹");
		}
		
		linkedList.display();
		System.out.println(linkedList.size()+""+linkedList.isEmpty());
		
	}
}
