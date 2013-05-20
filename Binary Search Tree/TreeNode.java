package bstree;
import list.*;

/**
 * Represents a single tree node within a binary search tree. The object held must be of a
 * Comparable type.
 * @author 		Kushal Ranjan
 * @param <E>	Comparable class of objects held in this node
 */
class TreeNode<E extends Comparable> {

	E obj;
	TreeNode<E> left;
	TreeNode<E> right;
	TreeNode<E> parent;
	
	/**
	 * Constructs a new TreeNode without any children.
	 * @param obj		object to store in this TreeNode
	 * @param parent	parent node of this node
	 */
	TreeNode(E obj, TreeNode<E> parent) {
		this.obj = obj;
		this.parent = parent;
	}
	
	/**
	 * Constructs a new TreeNode with children.
	 * @param obj		object to store in this TreeNode
	 * @param parent	parent node of this node
	 * @param left		left child
	 * @param right		right child
	 */
	TreeNode(E obj, TreeNode<E> parent, 
			TreeNode<E> left, TreeNode<E> right) {
		this(obj, parent);
		this.left = left;
		this.right = right;
	}
	
	/**
	 * Finds the node with key matching the specified key.
	 * @param obj	key of node to search for
	 * @return		the node contining key obj; null if no such node exists
	 */
	TreeNode<E> findNode(E obj) {
		int comp = ((Comparable) this.obj).compareTo(obj);
		if(comp == 0) {
			return this;
		} else if(comp > 0) { //Check left subtree
			if(left != null) {
				return left.findNode(obj);
			} else {
				return null;
			}
		} else { //Check right subtree
			if(right != null) {
				return right.findNode(obj);
			} else {
				return null;
			}
		}
	}
	
	/**
	 * Inserts an object into the BST containing this node, or updates the object if it
	 * is already in the tree.
	 * @param newObj	new object to be inserted
	 * @return			true if a new object was inserted; false otherwise
	 */
	boolean insert(E newObj) {
		int comp = ((Comparable) obj).compareTo(newObj);
		if(comp == 0) {
			obj = newObj;
			return false;
		} else if(comp > 0) {
			if(left != null) {
				return left.insert(newObj);
			} else {
				left = new TreeNode<E>(newObj, this);
				return true;
			}
		} else {
			if(right != null) {
				return right.insert(newObj);
			} else {
				right = new TreeNode<E>(newObj, this);
				return true;
			}
		}
	}
	
	/**
	 * Populates the list soFar with the inorder traversal of the tree from this node.
	 * @param soFar	The traversal so far
	 */
	void inOrder(LinkedList<E> soFar) {
		if(left != null) {
			left.inOrder(soFar);
		}
		soFar.add(obj);
		if(right != null) {
			right.inOrder(soFar);
		}
	}
	
	/**
	 * Populates the list soFar with the preorder traversal of the tree from this node.
	 * @param soFar	The traversal so far
	 */
	void preOrder(LinkedList<E> soFar) {
		soFar.add(obj);
		if(left != null) {
			left.inOrder(soFar);
		}
		if(right != null) {
			right.inOrder(soFar);
		}
	}
	
	/**
	 * Populates the list soFar with the postorder traversal of the tree from this node.
	 * @param soFar	The traversal so far
	 */
	void postOrder(LinkedList<E> soFar) {
		if(left != null) {
			left.inOrder(soFar);
		}
		if(right != null) {
			right.inOrder(soFar);
		}
		soFar.add(obj);
	}
	
	/**
	 * Returns a String representation of the BST containing this node and its subtrees.
	 * (<left-subtree>) this (<right-subtree>)
	 */
	public String toString() {
		String out = obj.toString();
		if(left != null) {
			out = "(" + left.toString() + ")" + out;
		}
		if(right != null) {
			out += "(" + right.toString() + ")";
		}
		return out;
	}
}
