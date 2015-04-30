package practice;

public class DecodeWays {
	
	public static int decodeWays(int[] arr) {
		int[] ways = new int[arr.length + 1];
		ways[0] = 1;
		ways[1] = 1;
		for (int i = 2; i < ways.length; i ++) {

			if (canDecode(arr[i-1])) {
				ways[i] = ways[i-1];
				if (canDecode(arr[i-2], arr[i-1])) {
					ways[i] += ways[i-2];
				}
			} else {
				// i is 0
				if (canDecode(arr[i-2], arr[i-1])) {
				  ways[i] = ways[i-2];
				} else {
					throw new IllegalArgumentException("should not have "
							+ arr[i-2] + "" + arr[i-1] + " in the array");	
				}
			}
		}
		System.out.print("ways: ");
		printArray(arr);
		System.out.println();
		
		return ways[ways.length-1];		
	}

	private static void printArray(int[] arr) {
		for (int i : arr) {
			System.out.print(i + ", ");
		}
	}
	private static boolean canDecode(int i) {
		return i > 0 && i < 10;
	}
	private static boolean canDecode(int iFirst, int iSecond) {
		return (0 < iFirst && iFirst < 3) && (iSecond >= 0 && iSecond < 27);
	}
	
	public static void main(String[] args) {
		int[] arr = new int[] {1,2,3,4};
		System.out.println(DecodeWays.decodeWays(arr));
		
		
	}
}
