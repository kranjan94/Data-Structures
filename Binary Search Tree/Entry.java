package bstree;

import list.Iterator;
import list.LinkedList;

/**
 * Class for holding dictionary entries. Comparing is performed based on key values.
 * @author 		Kushal Ranjan
 * @param <K>
 * @param <V>
 */
class Entry<K extends Comparable, V> implements Comparable<Entry<K, V>> {

	K key;
	V value;
	
	/**
	 * Constructor used for searching based on key.
	 */
	Entry(K key) {
		this.key = key;
	}
	
	/**
	 * Constructs a new Entry with specified key and value
	 * @param key	key to be used
	 * @param val	value of the entry
	 */
	Entry(K key, V val) {
		this.key = key;
		this.value = val;
	}
	
	/**
	 * Compares this entry to another by key.
	 */
	public int compareTo(Entry<K, V> o) {
		return key.compareTo(o.key);
	}
	
	/**
	 * Returns the string representation of the value of this entry.
	 */
	public String toString() {
		return value.toString();
	}
}
