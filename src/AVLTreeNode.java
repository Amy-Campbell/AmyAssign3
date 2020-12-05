

//Amy Campbell
//id: 611919

public class AVLTreeNode<E> extends TreeNode<E> {
	private int balanceFactor;
	private int height;
	private AVLTreeNode<E> parent;
	
	public AVLTreeNode(E e) {
		super(e);
		balanceFactor = 0;
		height = 0;
		parent = null;
	}
	
	public AVLTreeNode(E e, AVLTreeNode<E> p) {
		super(e);
		balanceFactor = 0;
		height = 0;
		parent = p;
	}
	
	public int getHeight(AVLTreeNode<E> nd) {
		if (nd == null) {
			height = -1;
		}
		else {
			height = Math.max(this.getHeight(nd.getLeft()), this.getHeight(nd.getRight())) + 1;
		}
		
		return height;
		
		
	}
	
	public int getBalanceFactor() {
			return getHeight(this.getRight()) - getHeight(this.getLeft());
		
			
		
		
	}
	
	@Override
	public AVLTreeNode<E> getRight(){
		return (AVLTreeNode<E>)right;
	}
	
	@Override
	public AVLTreeNode<E> getLeft(){
		return (AVLTreeNode<E>)left;
	}
	
	@Override
	public void setRight(BTNodeInt<E> node) {
		right = (AVLTreeNode<E>) node;
	}
	
	public void updateBalanceFactor() {
		
	}
	
	public void setParent(AVLTreeNode<E> p) {
		parent = p;
	}
	public AVLTreeNode<E> getParent(){
		return parent;
	}
	
	public E getElement() {
		return element;
	}
	
	
	/*
	 * public int getHeight() { return height; }
	 * 
	 * public int getBalanceFactor() { return balanceFactor; }
	 * 
	 * public void updateBalanceFactor() {
	 * 
	 * }
	 */
	/*
	 * @Override public AVLTreeNode<E> getRight() { return getRight(); }
	 * 
	 * @Override public AVLTreeNode<E> getLeft() { return getLeft(); }
	 * 
	 * public AVLTreeNode<E> getParent(){ return this.getParent(); }
	 * 
	 * public void setParent(AVLTreeNode<E> p) { parent = p; }
	 */
	
}


