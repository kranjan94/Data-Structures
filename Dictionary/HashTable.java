package dict;
import list.*;

public class HashTable<K, V> {

	LinkedList<Entry<K, V>>[] table;
	private int n; //Size
	private int N; //Number of buckets
	
	//Used in compression function
	private int a; //Random integer on [1,N-1]
	private int b; //Random integer on [0,N-1]
	private int p; //Prime greater than N
	
	private int numCollisions;

  /** 
   *  Construct a new empty hash table intended to hold roughly sizeEstimate
   *  entries.  (The precise number of buckets is up to you, but we recommend
   *  you use a prime number, and shoot for a load factor between 0.5 and 1.)
   **/

  public HashTable(int sizeEstimate) {
    N = (int)(((double)sizeEstimate)/0.7);
    while(!isPrime(N)){
    	N++;
    }
    n = 0;
    table = new LinkedList[N];
    
    
    p = 10651;
    a = (int)(Math.random()*(double)(p-1)) + 1;
    b = (int)(Math.random()*(double)p);
    numCollisions = 0;
  }

  /**
   * Returns true iff n is prime.
   * @param n	integer to be checked.
   * @return	true if n is prime; false otherwise.
   */
  private boolean isPrime(int n) {
	  for(int i = 2; i < Math.sqrt(n); i++){
		  if(n % i == 0){
			  return false;
		  }
	  }
	  return true;
  }
  

  /** 
   *  Construct a new empty hash table with a default size.  Say, a prime in
   *  the neighborhood of 100.
   **/
  public HashTable() {
	  this(70); // Uses other constructor; 70/.7 = 100, and 101 is the first prime >100
  }

  /**
   *  Converts a hash code in the range Integer.MIN_VALUE...Integer.MAX_VALUE
   *  to a value in the range 0...(size of hash table) - 1.
   **/
  int compFunction(int code) {
    int out = ((Math.abs(a*code) + b) % p) % N;
    if(out < 0){
    	out += N;
    }
    return out;
  }

  /** 
   *  Returns the number of entries stored in the dictionary.  Entries with
   *  the same key (or even the same key and value) each still count as
   *  a separate entry.
   *  @return 	number of entries in the dictionary.
   **/
  public int size() {
    return n;
  }

  /** 
   *  Tests if the dictionary is empty.
   *
   *  @return true if the dictionary has no entries; false otherwise.
   **/
  public boolean isEmpty() {
    return n == 0;
  }

  /**
   *  Create a new Entry object referencing the input key and associated value,
   *  and insert the entry into the dictionary.  Return a reference to the new
   *  entry.  Multiple entries with the same key (or even the same key and
   *  value) can coexist in the dictionary.
   *
   *  @param key the key by which the entry can be retrieved.
   *  @param value an arbitrary object.
   *  @return an entry containing the key and value.
   **/
  public Entry<K, V> insert(K key, V value) {
	if(((double)n/(double)N) > 0.7) {
		resize();
	}
    int bucket = compFunction(key.hashCode());
    Entry<K, V> newEntry = new Entry<K, V>(key, value);
    if(table[bucket] == null){
    	table[bucket] = new LinkedList<Entry<K,V>>();
    } else {
    	numCollisions++;
    }
    table[bucket].add(newEntry);
    n++;
    return newEntry;
  }
  
  /** 
   *  Search for an entry with the specified key.  If such an entry is found,
   *  return it; otherwise return null.  If several entries have the specified
   *  key, choose one arbitrarily and return it.
   *
   *  @param key the search key.
   *  @return an entry containing the key and an associated value, or null if
   *          no entry contains the specified key.
   **/
  public Entry<K, V> find(K key) {
	  int bucket = compFunction(key.hashCode());
	  if(table[bucket] == null) {
		  return null;
	  }
	  Iterator<Entry<K,V>> entries = table[bucket].iterator();
	  while(entries.hasNext()) {
		  Entry<K,V> ent = entries.next();
		  if(ent.key.equals(key)) {
			  return ent;
		  }
	  }
	  return null;
  }

  /** 
   *  Remove an entry with the specified key.  If such an entry is found,
   *  remove it from the table and return it; otherwise return null.
   *  If several entries have the specified key, choose one arbitrarily, then
   *  remove and return it.
   *
   *  @param key the search key.
   *  @return an entry containing the key and an associated value, or null if
   *          no entry contains the specified key.
   */
  public Entry<K,V> remove(Object key) {
	  int bucket = compFunction(key.hashCode());
	  if(table[bucket] == null || table[bucket].size() == 0){
		  return null;
	  }
	  Iterator<Entry<K,V>> entries = table[bucket].iterator();
	  while(entries.hasNext()) {
		  Entry<K,V> ent = entries.next();
		  if(ent.key.equals(key)) {
			  entries.remove();
			  n--;
			  return ent;
		  }
	  }
	  return null;
  }

  /**
   * Remove all entries from the dictionary.
   */
  public void makeEmpty() {
	  table = new LinkedList[N];
	  n = 0;
  }
  
  /**
   * Resize this HashTable
   */
  private void resize() {
		N *= 2;
		while(!isPrime(N)) {
			N++;
		}
	    p *= 2;
	    while(!isPrime(p)) {
	      p++;
	    }
	    a = (int)(Math.random()*(double)(p-1)) + 1;
	    b = (int)(Math.random()*(double)p);

		  LinkedList<Entry<K,V>>[] temp = table;
		  table = new LinkedList[N];
		  for(int i = 0; i<temp.length; i++) {
			  Iterator<Entry<K,V>> listIt = temp[i].iterator();
			  while(listIt.hasNext()){
				  Entry<K,V> curr = listIt.next();
				  insert(curr.key, curr.value);
			  }
		  }
	  }
}
