package heap;
import list.*;

/**
 * Implementation of a binary heap using an array for a level-order representation of the heap.
 * Index 0 in the array is ignored so that the level-order representation is 1-indexed in the
 * array.
 * @author KushalRanjan
 *
 */
public class BinaryHeap {
	
	public Comparable[] contents;
	
	/**
	 * Test code.
	 */
	public static void main(String[] args) {
		Comparable[] in = new Comparable[15];
		for(int i = 0; i<in.length; i++) {
			in[i] = new Integer((int)(Math.random()*100));
		}
		BinaryHeap test = new BinaryHeap(in);
		Comparable[] print = test.contents;
		for(int i = 1; i < print.length; i++) {
			System.out.print(print[i] + " ");
		}
	}
	
	/**
	 * Constructor for a Comparable array.
	 */
	public BinaryHeap(Comparable[] in) {
		contents = construct(in);
	}
	
	/**
	 * Constructor for a Comparable LinkedList.
	 */
	public BinaryHeap(LinkedList<Comparable> in) {
		Comparable[] cons = new Comparable[in.size()];
		Iterator<Comparable> it = in.iterator();
		for(int i = 0; i<cons.length; i++) {
			cons[i] = it.next();
		}
	}
	
	/**
	 * Returns the size of this binary heap.
	 */
	public int size() {
		return contents.length - 1;
	}
	
	/**
	 * Returns the minimum object in the heap.
	 */
	public Comparable min() {
		if(contents.length < 2) {
			return null;
		}
		return contents[1];
	}
	
	/**
	 * Inserts a new item into the heap.
	 * @param c	new Comparable to be inserted
	 */
	public void insert(Comparable c) {
		Comparable[] newContents = new Comparable[contents.length + 1];
		for(int i = 0; i<contents.length; i++) {
			newContents[i] = contents[i];
		}
		int index = newContents.length - 1;
		newContents[index] = c;
		bubbleUp(index, newContents);
		contents = newContents;
	}
	
	/**
	 * Returns and removes the minimum entry in the heap.
	 * @return	the minimum entry in the heap
	 */
	public Comparable removeMin() {
		if(contents.length < 2) {
			return null;
		}
		Comparable ret = contents[1];
		contents[1] = contents[contents.length-1];
		bubbleDown(1, contents);
		Comparable[] newArr = new Comparable[contents.length - 1];
		for(int i = 0; i < newArr.length; i++) {
			newArr[i] = contents[i];
		}
		contents = newArr;
		return ret;
	}
	
	/**
	 * Initializes and populates an array representation of a a binary heap.
	 * @param in	Comparable[] of items to be used
	 * @return		a Comparable[] that represents the level-order traversal of a tree that
	 * 				preserves the heap-order property
	 */
	private Comparable[] construct(Comparable[] in) {
		Comparable[] out = new Comparable[in.length + 1];
		for(int i = 1; i < out.length; i++) {
			out[i] = in[i-1];
		}
		int lastNonLeaf = parent(out.length - 1);
		for(int j = lastNonLeaf; j >= 1; j--) {
			while((left(j) < out.length && out[left(j)].compareTo(out[j]) < 0)
					|| (right(j) < out.length && out[right(j)].compareTo(out[j]) < 0)) {
				int check = j;
				bubbleDown(check, out);
			}
		}
		return out;
	}
	
	/**
	 * The item at position index in arr is bubbled down until it is smaller than both of its
	 * children
	 * @param index	starting index of the item to be bubbled down
	 * @param arr	the array to use for the process
	 */
	private void bubbleDown(int index, Comparable[] arr) {
		while((left(index) < arr.length && arr[left(index)].compareTo(arr[index]) < 0)
				|| (right(index) < arr.length && arr[right(index)].compareTo(arr[index]) < 0)) {
			if(arr[left(index)].compareTo(arr[index]) < 0) { //Entry is larger than left
				Comparable temp = arr[left(index)];
				arr[left(index)] = arr[index];
				arr[index] = temp;
				index = left(index);
			} else { //Entry is larger than right
				Comparable temp = arr[right(index)];
				arr[right(index)] = arr[index];
				arr[index] = temp;
				index = right(index);
			}
		}
	}
	
	/**
	 * The item at position index in arr is bubbled up until it is smaller than both of its
	 * children
	 * @param index	starting index of the item to be bubbled down
	 * @param arr	the array to use for the process
	 */
	private void bubbleUp(int index, Comparable[] arr) {
		while(parent(index) != 0 
				&& arr[index].compareTo(arr[parent(index)]) < 0) {
			Comparable temp = arr[parent(index)];
			arr[parent(index)] = arr[index];
			arr[index] = temp;
			index = parent(index);
		}
	}
	
	/**
	 * Returns the index of the parent of the node with the specified index.
	 */
	private int parent(int index) {
		return index/2;
	}
	
	/**
	 * Returns the index of the left child of the node with the specified index.
	 */
	private int left(int index) {
		return index * 2;
	}
	
	/**
	 * Returns the index of the right child of the node with the specified index.
	 */
	private int right(int index) {
		return index * 2 + 1;
	}
}
