public class DoublyLinkedLinked<K, V> {
	private Node<K, V> head;
	private Node<K, V> tail;


	public void addFirst(Node <K, V> node) {
		if(head == null){
			head = tail = node;
		} else {
			node.next = head;
			head.prev = node;
			head = node;
		}
	}

	public Node remove(Node<K, V> node) {
		if(node == head) {
			if(head == tail){
				head = tail = null;
				return node;
			} else {
				head = head.next;
				node.next = null;
				head.prev = null;
			}
		} else{
			if(node.prev != null) node.prev.next = node.next;
			if(node.next != null) node.next.prev = node.prev;
		}

		return node;
	}

	public void moveToFront(Node<K, V> node) {
		if(node == head) return;

		addFirst(remove(node));
	}

	public Node removeLast() {
		var last = tail;

		if(head == tail) {
			head = tail = null;
		} else {
			tail = tail.prev;
			tail.next = null;
		}

		return last;


	}
}