/* Entry.java */

package dict;

/**
 *  A class for dictionary entries.
 **/

public class Entry<K, V> {

  protected K key;
  protected V value;
  
  /**
   * Entry() constructs a dictionary entry with key as parameter K key
   * and corresponding value as parameter V value
   */
  public Entry(K key, V value) {
	  this.key = key;
	  this.value = value;
  }
  
  /**
   * Getters for the key and value of this Entry
   */
  public K key() {
    return key;
  }

  public V value() {
    return value;
  }

}
