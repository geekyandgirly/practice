package practice;

import java.util.HashMap;

public class Collatz {
	private final HashMap<Integer, Integer> map;

	public Collatz() {
		map = new HashMap<>();
	}
	
	public int getLongestSteps(int num) {
		int maxSteps = 0;
		
		if (num < 0) {
			return 0;
		}
		
		for (int i = 0; i <= num; i++) {
			int steps = getSteps(i);
			map.put(i, steps);
			maxSteps = Math.max(steps, maxSteps);
		}
		return maxSteps;
	}
	
	private int getSteps(int num) {
		if (num <= 1) return 1;
		if (map.containsKey(num)) return map.get(num);
		
		if (num % 2 == 0) {
			return 1 + getSteps(num / 2);
		} else {
			return 1 + getSteps(num * 3 + 1);
		}
	}
	
	public static void main(String[] args) {
		Collatz co = new Collatz();
		int num = 128;
		System.out.println("longest number of steps for number from 0 to " + num + " is " + co.getLongestSteps(num));
	}
}
