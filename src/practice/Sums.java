package practice;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class Sums {
	
	
	
	public static Set<Set<Integer>> sums3(int[] arr) {
		Set<Triplet> set = new HashSet();
		return null;
	}
	
	private static class Triplet {
		private final int num1, num2, num3;
		
		public Triplet(int n1, int n2, int n3) {
		  num1 = Math.min(n1,  Math.min(n2, n3));
		  num2 = (num1 == n1) ? Math.min(n2, n3)
				  : ((num1 == n2) ? Math.min(n1, n3) : Math.min(n1, n2));
		  num3 = (num1 == n1) ? Math.max(n2,  n3)
				  : (num1 == n2 ? Math.max(n1, n3) : Math.max(n1,  n2));
		}
		
		@Override
		public int hashCode() {
			return String.join("-",
					String.valueOf(num1), String.valueOf(num2), String.valueOf(num3)).hashCode();
		}
		
		@Override
		public boolean equals(Object o) {
			if (o == null) return false;
			if (!(o instanceof Triplet)) {
				return false;
			}
			Triplet t = (Triplet) o;
			return (num1 == t.num1 && num2 == t.num2 && num3 == t.num3);
		}
	}
}
