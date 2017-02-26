package cn.cherish.mboot.datastructure;

class LinkNode<V> {

	private V value;
	
	private LinkNode<V> next;
	
	public LinkNode(V value){
		this.value = value;
		this.next = null;
	}
	
	public LinkNode(V value, LinkNode<V> next){
		this.value = value;
		this.next = next;
	}

	public V getValue() {
		return (V)value;
	}

	public void setValue(V value) {
		this.value = value;
	}

	public LinkNode<V> getNext() {
		return next;
	}

	public void setNext(LinkNode<V> next) {
		this.next = next;
	}
	
	
}
