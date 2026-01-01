import java.util.*;

public class LRUCache<K, V> {
	private int capacity = 3;
	private final Map<K, Node<K, V>> cacheMap;
	private final DoublyLinkedLinked dll;

	public LRUCache() {
		this.cacheMap = new HashMap<>();
		this.dll = new DoublyLinkedLinked<>();
	}

	public LRUCache(int capacity) {
		this();
		this.capacity = capacity;
	}

	public void put(K key, V val) {	
 		if(cacheMap.containsKey(key)) {
			Node<K, V> node = cacheMap.get(key);
			node.val = val;
			 dll.moveToFront(node);
		} else {
			if(cacheMap.size() == capacity) {
				cacheMap.remove(dll.removeLast());
			}

			Node<K, V> node = new Node<>(key, val);
			dll.addFirst(node);
			cacheMap.put(key, node);
		}
	}

	public synchronized V get(K key) {
		if(!cacheMap.containsKey(key)) return null;

		Node<K, V> node = cacheMap.get(key);
		dll.moveToFront(node);

		return node.val;
	}

	public synchronized void remove(K key) {
		if(!cacheMap.containsKey(key)) return;

		dll.remove(cacheMap.get(key));
		cacheMap.remove(key);
	}
}