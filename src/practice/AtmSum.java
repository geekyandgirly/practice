package practice;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class AtmSum {
	
	public static List<List<Node>> getLists(int sum, int[] denom) {
		List<List<Node>> lists = new ArrayList();
		List<Node> list = new ArrayList();
		lists.add(list);
		
		getLists(sum, denom, lists, list);
		return lists;
	}
	
	private static void getLists(int sum, int[] denom, List<List<Node>> lists,
			List<Node> list) {
		for (int i = 0; i < denom.length; i++) {
    		int dollar = denom[i];
    		int maxCount = sum / dollar;
    		for (int count = 0; count <= maxCount; count++) {
    			Node node = new Node(dollar, count);
    			list.add(node);
    			sum =- (dollar * count);
    			
    			if (sum == 0) {
    				List<Node> newList = new ArrayList();
    				getLists(sum, Arrays.copyOfRange(denom, 1, denom.length-1), lists, newList);
    			} else {
    				getLists(sum, Arrays.copyOfRange(denom, 1, denom.length-1), lists, list);
    			}
    		}
		}	
	}

	static class Node {
		int dollar;
		int count;
		public Node(int dollar, int count) {
			this.dollar = dollar;
			this.count = count;
		}
	}

	public static void main(String[] args) {
		
	}
}
