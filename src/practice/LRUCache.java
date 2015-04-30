package practice;

import java.util.HashMap;

/**
 * Implement LinkedHashMap as LRU cache.
 */
public class LRUCache {

  private static final int NOT_FOUND = Integer.MIN_VALUE;
	private final HashMap<Integer, Node> keyToNodeMap;
  private final int capacity;
  
  private Node head, tail;
  
  public LRUCache(int capacity) {
  	if (capacity < 3) {
  		throw new IllegalArgumentException("cache size should be at least 3");
  	}
  	keyToNodeMap = new HashMap<Integer, Node>(capacity);
  	this.capacity = capacity;
  }
  
  public int get(int key) {
    Node node = keyToNodeMap.get(key);
    if (node != null) {
    	moveNodeToTail(node);
    	return node.value;
    }
    return NOT_FOUND;
  }
  
  public void set(int key, int value) {
  	if (head == null) {
  		head = new Node(value);
  		keyToNodeMap.put(key, head);
  	} else if (tail == null) {
  		tail = new Node(value);
  		head.next = tail;
  		tail.prev = head;
  		keyToNodeMap.put(key, tail);
  	} else {  	
	  	Node node = keyToNodeMap.get(key);
	  	
			// Need to insert new key.
	  	if (node == null) {
		  	// If we reach our capacity already
		  	if (keyToNodeMap.size() == capacity) {
		  		head = head.next;
		  		head.prev = null;
		  	}
		  	
		  	node = new Node(value);
		  	attachNodeToTail(node);
	  	} else {
	    	node.value = value;
	    	moveNodeToTail(node);
	  	}
	  	keyToNodeMap.put(key, node);
  	}  	
  }
  
  private void moveNodeToTail(Node node) {
  	
  	if (node != tail) {
    	// If node is tail already we don't need to do anything
  		node.next.prev = node.prev;
  		if (node.prev != null) {
  			node.prev.next = node.next;
  		} else {
  			// node is head
  			head = head.next;
  			head.prev = null;
  		}
      attachNodeToTail(node);
  	}
  }

  private void attachNodeToTail(Node node) {
  	Node temp = tail;
  	temp.next = node;
  	node.prev = temp;
  	tail = node;
  }
  
  int getHeadValueForTest() {
  	return head.value;
  }
  
  int getTailValueForTest() {
  	return tail.value;
  }
  
  private class Node {
  	private int value;
  	Node prev;
  	Node next;
  	
  	public Node(int value) {
  		this.value = value;
  	}
  }
}