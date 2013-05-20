package bstree;
import list.*;

/**
 * Implementation of a basic binary search tree.
 * @author Kushal Ranjan
 * @param <K>	type of key objects to be used
 * @param <V>	type of value objects to be used
 */
public class BinarySearchTree<K extends Comparable, V> {

	TreeNode<Entry<K, V>> root;
	int size;
	
	/**
	 * Test code.
	 */
	public static void main(String[] args) {
		BinarySearchTree<Integer, String> tree = new BinarySearchTree<Integer, String>();
		tree.insert(3, "my");
		tree.insert(1, "Hello");
		tree.insert(5, "is");
		tree.insert(7, "Kushal");
		tree.insert(4, "name");
		tree.insert(2, "there");
		System.out.println(tree + " " + tree.size());
		tree.insert(6, "not");
		tree.insert(7, "Kushal");
		System.out.println(tree + " " + tree.size());
		tree.remove(3);
		System.out.println(tree + " " + tree.size());
		tree.insert(3, "your");
		System.out.println(tree + " " + tree.size());
		tree.rotateCounterClockwise();
		System.out.println(tree);
		System.out.println(tree.preOrder());
		System.out.println(tree.postOrder());
		System.out.println(tree.inOrder());
	}
	
	/**
	 * Constructs a new binary search tree with no entries.
	 */
	public BinarySearchTree() {
		root = null;
		size = 0;
	}
	
	/**
	 * Returns the number of entries in this tree.
	 */
	public int size() {
		return size;
	}
	
	/**
	 * Finds the value associated with the specified key.
	 * @param key	the key to search for
	 * @return		the value associated with key; null if key is not found in this tree
	 */
	public V find(K key) {
		if(root == null) return null;
		Entry<K, V> keyEnt = new Entry<K, V>(key);
		TreeNode<Entry<K, V>> node = root.findNode(keyEnt);
		if(node == null) {
			return null;
		} else {
			return node.obj.value;
		}
	}
	
	/**
	 * Inserts an Entry into this BST; if an Entry with the key already exists, the Entry
	 * is updated with the new value.
	 * @param key	key of Entry to insert
	 * @param value	value of Entry to insert
	 */
	public void insert(K key, V value) {
		Entry<K, V> ins = new Entry<K,V>(key, value);
		if(root == null) {
			root = new TreeNode<Entry<K,V>>(ins, null);
			size++;
		} else {
			if(root.insert(ins)) //Only update size if a new Entry was inserted
				size++;
		}
	}
	
	/**
	 * Removes the entry with the specified key from the BST; returns the value associated
	 * with the key, or null if the key was not found.
	 * @param key	the key of the Entry to search for
	 * @return		the value associated with key; null if key is not in the tree
	 */
	public V remove(K key) {
		TreeNode<Entry<K,V>> node = root.findNode(new Entry<K,V>(key));
		if(node == null) return null;
		if(node.left == null && node.right == null) {
			if(node.parent == null) {
				root = null;
			} else if(node.parent.right == node) {
				node.parent.right = null;
			} else {
				node.parent.left = null;
			}
		} else if(node.left == null) {
			node.obj = node.right.obj;
			node.left = node.right.left;
			node.right = node.right.right;
		} else if(node.right == null) {
			node.obj = node.left.obj;
			node.left = node.left.left;
			node.right = node.left.right;
		} else {
			TreeNode<Entry<K,V>> replace = node.right;
			while(replace.left != null) {
				replace = replace.left;
			}
			node.obj = replace.obj;
			if(replace == replace.parent.left) {
				replace.parent.left = replace.right;
			} else {
				node.right = node.right.right;
			}
		}
		size--;
		return node.obj.value;
	}
	
	/**
	 * Performs a clockwise rotation of this BST.
	 */
	public void rotateClockwise() {
		if(root.left != null) {
			root.parent = root.left;
			root.left = root.left.right;
			root.parent.right = root;
			root = root.parent;
		}
	}
	
	/**
	 * Performs a counterclockwise rotation of this BST.
	 */
	public void rotateCounterClockwise() {
		if(root.right != null) {
			root.parent = root.right;
			root.right = root.right.left;
			root.parent.left = root;
			root = root.parent;
		}
	}
	
	/**
	 * Returns the values of the in-order traversal of this tree.
	 */
	public LinkedList<V> inOrder() {
		LinkedList<Entry<K, V>> trav = new LinkedList<Entry<K, V>>();
		if(root != null) {
			root.inOrder(trav);
		}
		return extractValues(trav);
	}

	/**
	 * Returns the values of the pre-order traversal of this tree.
	 */
	public LinkedList<V> preOrder() {
		LinkedList<Entry<K, V>> trav = new LinkedList<Entry<K, V>>();
		if(root != null) {
			root.preOrder(trav);
		}
		return extractValues(trav);
	}
	
	/**
	 * Returns the values of the post-order traversal of this tree.
	 */
	public LinkedList<V> postOrder() {
		LinkedList<Entry<K, V>> trav = new LinkedList<Entry<K, V>>();
		if(root != null) {
			root.postOrder(trav);
		}
		return extractValues(trav);
	}
	
	/**
	 * Extracts the values of the Entries in the given list and returns them in a new list.
	 * @param ents	list of Entries
	 * @return		list of the values of the Entries in ent
	 */
	private LinkedList<V> extractValues(LinkedList<Entry<K,V>> ents) {
		Iterator<Entry<K,V>> entIt = ents.iterator();
		LinkedList<V> out = new LinkedList<V>();
		while(entIt.hasNext()) {
			out.add(entIt.next().value);
		}
		return out;
	}
	
	/**
	 * Returns a String representation of this BST; displays values of nodes.
	 * (<left-subtree>) root (<right-subtree>)
	 */
	public String toString() {
		if(root == null) {
			return "<empty>";
		} else {
			return root.toString();
		}
	}
}
