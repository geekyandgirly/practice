package practice;

import java.util.LinkedList;

import practice.ArrayQueue.ArrayQueueException;

/**
 * Implement a queue using int[] with size limit to 10. queue size is not limited.
 * @author luping
 *
 */
public class ArrayQueue {
	private static final int ARRAY_SIZE = 10;
	private int headIndex = 0;
	private int tailIndex = 0;
	LinkedList<Node> nodes = new LinkedList<>();
	
	public class ArrayQueueException extends Exception {
		private static final long serialVersionUID = 1L;

		public ArrayQueueException(String msg) {
			super(msg);
		}
	}
	
	class Node {
		int[] arr = new int[ARRAY_SIZE];
		int validValueCount = 0;
		
		int get(int index) throws ArrayQueueException {
			if (validValueCount > 0) {
				return arr[index];				
			}
			throw new ArrayQueueException("Cannot peek an empty queue");
		}
		
		int remove(int index) throws ArrayQueueException {
			if (validValueCount > 0) {
				validValueCount--;
				return arr[index];				
			}
			throw new ArrayQueueException("Cannot dequeue an empty queue");
		}
		
		void add(int index, int val) {
			arr[index] = val;
			validValueCount++;
		}
		
		void print() {
			for (int i = 0; i < ARRAY_SIZE; i++) {
				System.out.print(arr[i] + " ");
			}
			System.out.println();
		}
	}
	
	public void enqueue(int val) {
		synchronized(nodes) {
			if (nodes.isEmpty() ){
				nodes.add(new Node());
				headIndex = 0;
				tailIndex = 0;
			} else if (tailIndex == ARRAY_SIZE) {
				nodes.add(new Node());
				tailIndex = 0;
			}

			Node tailNode = nodes.getLast();
			tailNode.add(tailIndex++, val);
		}
	}

	public int dequeue() throws ArrayQueueException {
		synchronized(nodes) {
			if (nodes.isEmpty()) {
				throw new ArrayQueueException("Cannot dequeue an empty queue");
			}
			
			Node headNode = nodes.getFirst();
			int val = headNode.remove(headIndex++);
			if (headIndex == ARRAY_SIZE) {
				nodes.removeFirst();
				headIndex = 0;
			}
			
			if (nodes.size() == 1 && headIndex > tailIndex) {
				tailIndex = headIndex;
			}
			return val;
		}
	}
	
	public int peek() throws ArrayQueueException{
		if (nodes.isEmpty() ) {
			throw new ArrayQueueException("Cannot peek an empty queue");
		}
		Node headNode = nodes.getFirst();
		int val = headNode.get(headIndex);
		return val;
	}
	
	private void printQueue() {
		for (int i = 0; i < nodes.size(); i++) {
			Node node = nodes.get(i);
			node.print();
		}
	}
	
	public static void main(String[] args) throws ArrayQueueException {
		ArrayQueue q = new ArrayQueue();
		for (int i = 0; i < 15; i++) {
			q.enqueue(i);
		}
		System.out.println("********* equeue 0-15 ********");
		q.printQueue();
		
		System.out.println("********* dequeue 0-5 ********");
		for (int i = 0; i < 5; i++) {
			System.out.println("dequeue expect: " + i + ", actual: " + q.dequeue());			
		}
		q.printQueue();
			
		System.out.println("********* peek ********");
		System.out.println("peek expect: 5, actual: " + q.peek());

		for (int i = 15; i < 20; i++) {
			q.enqueue(i);
		}
		System.out.println("********* enqueue 15-20 ********");
		q.printQueue();

		System.out.println("*********dequeue  5-20 ********");
		for (int i = 5; i < 20; i++) {
			System.out.println("dequeue expect: " + i + ", actual: " + q.dequeue());			
		}
		q.printQueue();

		try {
			q.peek();
		} catch(ArrayQueueException e) {
			System.out.println(e.getMessage());
		}
		
		try {
			q.dequeue();
		} catch(ArrayQueueException e) {
			System.out.println(e.getMessage());
		}
	}
}
