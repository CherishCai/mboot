package cn.cherish.mboot.arithmetic.datastructure;

class TreeNode {

	private long value;
	private TreeNode left;
	private TreeNode right;
	
	public TreeNode(long value) {
		this.value = value;
		this.left = null;
		this.right = null;
	}
	
	public TreeNode(long value, TreeNode left, TreeNode right) {
		this.value = value;
		this.left = left;
		this.right = right;
	}

	public long getValue() {
		return value;
	}

	public void setValue(long value) {
		this.value = value;
	}

	public TreeNode getLeft() {
		return left;
	}

	public void setLeft(TreeNode left) {
		this.left = left;
	}

	public TreeNode getRight() {
		return right;
	}

	public void setRight(TreeNode right) {
		this.right = right;
	}
	
	public boolean hasLeft(){
		if (left != null) {
			return true;
		}
		return false;
	}
	
	public boolean hasRight(){
		if (right != null) {
			return true;
		}
		return false;
	}
	
}
