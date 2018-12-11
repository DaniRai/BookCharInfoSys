//----------Packages & Imports----------//
package controllers;



/**
 * @author Daniel Quinlan
 * @studentNumber 01133940
 * @course Entertainment Systems Yr 2
 * 
 * @param <K>
 * @param <V>
 */
public class HashedMap<K,V> {

	private Pair<K,V>[] map;
	private int cap = 29; //initial capacity

//----------Inner Pair Class----------//
	
	@SuppressWarnings("hiding")
	private class Pair<K,V> {
		K key;
		V value;
		Pair<K, V> tail;
		
		public Pair(K key, V value, Pair<K,V> tail) {
			this.key = key;
			this.value = value;
			this.tail = tail;
		}
	}
	
	@SuppressWarnings("unchecked")
	public HashedMap() {
		map = new Pair[cap];
	}

//----------Hash Function----------//
	
	/**
	 * Method implementing hash functionality.
	 * Generates index to store data
	 * 
	 * @param key
	 */
	private int hash(K key) {
		return Math.abs(key.hashCode()) % cap;
	}
	
//----------Map Functions----------//
	
	/**
	 * Method to allow adding of a key-value pair into the hashed map
	 * if a new value is inserted for a used key the value is overwritten
	 * Generic implementation allows custom objects as key
	 * 
	 * @param newKey
	 * @param newValue
	 */
	public void push(K newKey, V newValue) {
		if(newKey == null)
			return; //does not allow null values
		
		//Calculate Hash
		int hash=hash(newKey); 
		//Create Pair
		Pair<K,V> newPair = new Pair<K,V>(newKey, newValue, null);
		
		//if index is empty store data
		if(map[hash] == null) {
			map[hash] = newPair;
		}else {
			Pair<K,V> prev = null;
			Pair<K,V> cur = map[hash];
			
			while(cur != null) { //reached end of array
				if(cur.tail.equals(newKey)) {
					if(prev==null) { //insert at head of array
						newPair.tail=cur.tail;
						map[hash]=newPair;
						return;
					}else {
						newPair.tail = cur.tail;
						prev.tail=newPair;
						return;
					}
				}
				prev=cur;
				cur=cur.tail;
			}
			prev.tail=newPair;
		}
	}
	
	/**
	 * Method return value associated to key
	 * 
	 * @param key
	 */
	public V get(K key) {
		int hash = hash(key);
		if(map[hash] == null) {
			return null;
		}else {
			Pair<K,V> temp = map[hash];
			while(temp != null) {
				if(temp.key.equals(key))
					return temp.value;
				temp = temp.tail; //return value associated to key
			}
			return null; //return null if not found
		}
	}
	
	/**
	 * Method to erase a key-value pairing from Hashed Map
	 * @param eraseKey
	 * @return
	 */
	public boolean erase(K eraseKey) {
		int hash = hash(eraseKey);
		
		if(map[hash] == null) {
			return false;
		}else {
			Pair<K,V> prev = null;
			Pair<K,V> cur = map[hash];
			
			while(cur != null) { //reached end of array
				if(cur.key.equals(eraseKey)) {
					if(prev==null) { //delete first pair
						map[hash]=map[hash].tail;
						return true;
					}else {
						prev.tail=cur.tail;
						return true;
					}
				}
				prev=cur;
				cur=cur.tail;
			}
			return false;
		}
	}
}
	
	
					
				
			
		
	

