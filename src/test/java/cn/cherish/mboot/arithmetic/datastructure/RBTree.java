package cn.cherish.mboot.arithmetic.datastructure;

import cn.cherish.mboot.arithmetic.exception.MyException;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;


/**
 * 蔡梦缘之红黑树实现平衡树
 * @author caimengyuan
 *
 */
public class RBTree {

	private Node root;
	private int size;
	
	public RBTree(){
		root = null;
		size = 0;
	}
	
	public boolean isEmpty(){
		if (root == null) {
			return true;
		}
		return false;
	}
	
	public void addNode(long val){
		Node node = new Node(val);
		if (root == null) {
			root = node;
		}else {
			Node currentNode = root;
			while(true){
				if (val < currentNode.value) {
					//左节点
					if (currentNode.left == null) {
						//不存在左节点
						currentNode.left = node;
						break;
					}else {
						//存在左节点
						currentNode = currentNode.left;
						continue;
					}
				}else {
					//右节点
					if (currentNode.right == null) {
						//不存在右左点
						currentNode.right = node;
						break;
					}else {
						//存在右节点
						currentNode = currentNode.right;
						continue;
					}
				}
			}//end while
		}
		size++;
	}
	public boolean removeNode(long val) throws MyException {
		if (root == null) {
			throw new MyException("树为空！");
		}else {
			Node currentNode = root;
			Node parentNode = root;
			boolean isLeftChild = false;
			while(true){
				if (val == currentNode.value) {
					//找到要删除的节点，执行删除
					//要判断它的子节点
					if (currentNode.left == null) {
						if (currentNode.right == null) {
							//左右子节点皆空
							if (currentNode == root) {
								root = null;
							}else if (isLeftChild) {
								parentNode.left = null;
							}else {
								parentNode.right = null;
							}
						}else {
							//左子节点为空，右子节点非空
							if (currentNode == root) {
								root = currentNode.right;
							}else if (isLeftChild) {
								parentNode.left = currentNode.right;
							}else {
								parentNode.right = currentNode.right;
							}
							
						}
					}else {
						if (currentNode.right == null) {
							//左子节点非空，右子节点为空
							if (currentNode == root) {
								root = currentNode.left;
							}else if (isLeftChild) {
								parentNode.left = currentNode.left;
							}else {
								parentNode.right = currentNode.left;
							}
						}else {
							//左右子节点皆非空,这个最复杂
							//要找到后继节点补位
							Node successor = getSuccessor(currentNode);
							
							if (currentNode == root) {
								root = successor;
							}else if (isLeftChild) {
								parentNode.left = successor;
							}else {
								parentNode.right = successor;
							}
							
							//被删除节点的左子作为后继左子
							successor.left = currentNode.left;
							
						}
					}
					
					size--;
					return true;
				}else if (val < currentNode.value) {
					//左节点
					if (currentNode.left == null) {
						//不存在左节点
						return false;
					}else {
						//存在左节点
						parentNode = currentNode;
						isLeftChild = true;
						currentNode = currentNode.left;
						continue;
					}
				}else {
					//右节点
					if (currentNode.right == null) {
						//不存在右左点
						return false;
					}else {
						//存在右节点
						parentNode = currentNode;
						isLeftChild = false;
						currentNode = currentNode.right;
						continue;
					}
				}
			}//end while
		
		}
	}

	private Node getSuccessor(Node currentNode) {
		Node successorParent = currentNode;
		Node successor = currentNode;
		Node loopNode = currentNode.right;
		
		//找最小的左子作后继
		while(loopNode != null){
			successorParent = successor;
			successor = loopNode;
			loopNode = loopNode.left;
		}
		
		//处理后继
		if(successor != currentNode.right){
			//successor.right有可能是null，也是对的
			successorParent.left = successor.right;
			
			//successor.right现在是被删除节点右子
			successor.right = currentNode.right;
		}
		
		return successor;
	}

	@SuppressWarnings("unused")
	private void changeColor(Node currentNode){
		
	}
	
	public long minimum(){
		Node currentNode = root;
		if (currentNode == null) {
			return Long.MIN_VALUE;
		}else {
			while(currentNode.left != null){
				currentNode = currentNode.left;
			}
			return currentNode.value;
		}
	}
	
	public long maximum(){
		Node currentNode = root;
		if (currentNode == null) {
			return Long.MAX_VALUE;
		}else {
			while(currentNode.right != null){
				currentNode = currentNode.right;
			}
			return currentNode.value;
		}
	}
	
	/**
	 * 从小到大排序
	 * @return
	 */
	public List<Node> sequence(){
		List<Node> nodeList = new ArrayList<Node>();
		middleSearch(nodeList, root);
		
		return nodeList;
	}
	
	private void middleSearch(List<Node> nodelist, Node pivot){
		if (pivot == null) {
			return;
		}else {
			middleSearch(nodelist, pivot.left);
			nodelist.add(pivot);;
			middleSearch(nodelist, pivot.right);
		}
	}
	
	public int size(){
		return size;
	}
	
	private class Node {

		long value;
		Node left;
		Node right;
		boolean isRed;
		
		public Node(long value) {
			this.value = value;
			this.left = null;
			this.right = null;
			this.isRed = true;
		}
		
	}//class Node
	
	public static void main(String[] args) {
		RBTree rbTree = new RBTree();
		long start0 = System.currentTimeMillis();
		for (int j = 0; j < 100; j++) {
			rbTree.addNode((long)(Math.random()*50));
//			rbTree.addNode(j);
		}
		long end0 = System.currentTimeMillis();
		System.out.println("插入耗时："+(end0-start0));
		long start1 = System.currentTimeMillis();
		List<Node> nodeList = rbTree.sequence();
		Iterator<Node> it = nodeList.iterator();
		while(it.hasNext()){
			System.out.print(it.next().value + "  ");
		}
		System.out.println();
		long end1 = System.currentTimeMillis();
		System.out.println("中序取出耗时："+(end1-start1));
		
		System.out.print("最小值："+ rbTree.minimum());
		System.out.println("   最大值："+ rbTree.maximum());
		
		try {
			System.out.println("删除6行不行？"+rbTree.removeNode(6));
			System.out.println("删除7行不行？"+rbTree.removeNode(7));
			System.out.println("删除18行不行？"+rbTree.removeNode(18));
		} catch (MyException e) {
			e.printStackTrace();
		}
		
		nodeList = rbTree.sequence();
		Iterator<Node> iterator = nodeList.iterator();
		while(iterator.hasNext()){
			System.out.print(iterator.next().value + "  ");
		}
		System.out.println();
		System.out.println("size:"+rbTree.size());
	}
}
