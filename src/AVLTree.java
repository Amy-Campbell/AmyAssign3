

//Amy Campbell
//id: 611919

public class AVLTree<E extends Comparable<E>> extends BinarySearchTree<E> {
	
	
	public AVLTree() {
		super();
	}
	
	@Override
	public boolean insert(E e) {
		BTNodeInt<E> newNode = createNewNode(e);
	    if (root == null)
	      root = newNode; // Create a new root
	    else {
	      // Locate the parent node
	    	BTNodeInt<E> parent = null;
	    	BTNodeInt<E> current = root;
	      while (current != null)
	        if (e.compareTo(current.getElement()) < 0) {
	          parent = current;
	          current = current.getLeft();
	        } else if (e.compareTo(current.getElement()) > 0) {
	          parent = current;
	          current = current.getRight();
	        } else
	          return false; // Duplicate node not inserted

	      // Create the new node and attach it to the parent node
	      ((AVLTreeNode<E>)newNode).setParent((AVLTreeNode<E>)parent);
	      if (e.compareTo(parent.getElement()) < 0)
	        parent.setLeft(newNode);
	      else
	        parent.setRight(newNode);
	    }

	    size++;
	    balance((AVLTreeNode<E>)newNode);
	    
	    return true; // Element inserted successfully
	  }
	
	 
	@Override
	protected BTNodeInt<E> createNewNode(E e) {
		AVLTreeNode<E> newNode = new AVLTreeNode<E>(e);
		return newNode;
	}

	private void balance(AVLTreeNode<E> node) {

	    System.out.println("balancing " + node.getElement());
		int balanceFactor = node.getBalanceFactor();
		int balanceFactorLeft = node.getLeft() == null ? 0 : node.getLeft().getBalanceFactor();
		int balanceFactorRight = node.getRight() == null ? 0 : node.getRight().getBalanceFactor();
		
		if (node.getLeft() == null) {
			balanceFactorLeft = 0;
		}
		else {
			balanceFactorLeft = node.getLeft().getBalanceFactor();
		}
		
		if (node.getRight() == null) {
			balanceFactorRight = 0;
		}
		else {
			balanceFactorRight = node.getRight().getBalanceFactor();
		}

		// check if left-heavy
		if (balanceFactor == -2) {
			

			// right rotate
			if (balanceFactorLeft == 1) {
				leftRightRotate(node);
			} else {
				rightRotate(node);
			}

			//check if right-heavy
		} else if (balanceFactor == 2) {
			if (balanceFactorRight == -1) {
				rightLeftRotate(node);
			} else {
				leftRotate(node);
			}
			

		}
		if (node != root) {
			balance(node.getParent());
		}
		

	}

	public void updateBalanceFactor(AVLTreeNode<E> node) {
		
	}
	public void leftRotate(AVLTreeNode<E> node) {
		System.out.println("left rotation");
		AVLTreeNode<E> newParent = node.getRight();
		AVLTreeNode<E> newRightChild = newParent.getLeft();
		
		//rotation
		node.setRight(newRightChild);
		node.setParent(newParent);
		root = newParent;
		newParent.setLeft(node);
	}
	public void rightRotate(AVLTreeNode<E> node) {
		System.out.println("Right rotation");
		AVLTreeNode<E> newParent = node.getLeft();
		AVLTreeNode<E> newLeftChild = newParent.getRight();
		
		//rotation
		node.setLeft(newLeftChild);
		node.setParent(newParent);
		root = newParent;
		newParent.setRight(node);
	
		
		
		
	}
	public void leftRightRotate(AVLTreeNode<E> node) {
		System.out.println("left right rotation ");
		
		AVLTreeNode<E> fakeRoot = node.getLeft();
		AVLTreeNode<E> newParent = fakeRoot.getRight();
		AVLTreeNode<E> newRightChild = newParent.getLeft();
		
		//left rotation
		fakeRoot.setRight(newRightChild);
		fakeRoot.setParent(newParent);
		AVLTreeNode<E> tempRoot = newParent;
		root.setLeft(newParent);
		newParent.setLeft(fakeRoot);
		
		//right rotation
		rightRotate(node);
	}
	public void rightLeftRotate(AVLTreeNode<E> node) {
		System.out.println("right left rotation");
		
		
		AVLTreeNode<E> fakeRoot = node.getRight();
		AVLTreeNode<E> newParent = fakeRoot.getLeft();
		AVLTreeNode<E> newLeftChild = newParent.getRight();
		
		//right rotation
		fakeRoot.setLeft(newLeftChild);
		fakeRoot.setParent(newParent);
		AVLTreeNode<E> tempRoot = newParent;
		root.setRight(newParent);
		newParent.setRight(fakeRoot);
		
		//left rotation
		leftRotate(node);
		
		
	}
	
	@Override
	public boolean delete(E e) {
		
		  BTNodeInt<E> parent = null;
		  BTNodeInt<E> current = root;
	    while (current != null) {
	      if (e.compareTo(current.getElement()) < 0) {
	        parent = current;
	        current = current.getLeft();
	      } else if (e.compareTo(current.getElement()) > 0) {
	        parent = current;
	        current = current.getRight();
	      } else
	        break;
	    }

	    if (current == null)
	      return false; // Element is not in the tree

	    // Case 1: current has no left child
	    if (current.getLeft() == null) {
	      // Connect the parent with the right child of the current node
	      if (parent == null) {
	        root = current.getRight();
	        ((AVLTreeNode<E>)root).setParent(null);
	        balance((AVLTreeNode<E>)parent);
	      } else {
	        if (e.compareTo(parent.getElement()) < 0) {
	          parent.setLeft(current.getRight());
	        balance((AVLTreeNode<E>)parent);
	        }
	        else {
	          parent.setRight(current.getRight());
	          balance((AVLTreeNode<E>)parent);
	        }
	        
	      }
	      balance((AVLTreeNode<E>)parent);
	    } else {
	      // Case 2: The current node has a left child
	      // Locate the rightmost node in the left subtree of
	      // the current node and also its parent
	    	BTNodeInt<E> parentOfRightMost = current;
	    	BTNodeInt<E> rightMost = current.getLeft();

	      while (rightMost.getRight() != null) {
	        parentOfRightMost = rightMost;
	        rightMost = rightMost.getRight(); // Keep going to the right
	      }

	      // Replace the element in current by the element in rightMost
	      current.setElement(rightMost.getElement());
	      balance((AVLTreeNode<E>)parent);

	      // Eliminate rightmost node
	      if (parentOfRightMost.getRight() == rightMost) {
	        parentOfRightMost.setRight(rightMost.getLeft());
	        balance((AVLTreeNode<E>)parent);
	      }
	      else {
	        // Special case: parentOfRightMost == current
	        parentOfRightMost.setLeft(rightMost.getLeft());
	        balance((AVLTreeNode<E>)parent);
	      }
	      balance((AVLTreeNode<E>)parent);
	    }

	    size--;
	    balance((AVLTreeNode<E>)parent);
	    return true; // Element deleted successfully
	  }
	
}
