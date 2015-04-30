package practice;

public class SpiralMatric {
	
	public static void spiralPrint(int[][] matrix) {
		if (!sanityCheck(matrix)) return;
		
		int startX = 0, startY = 0;
		int x = startX, y = startY;
		int endX = matrix[0].length - 1, endY = matrix.length - 1; 
		System.out.println("matrix endX=" + endX + " endY=" + endY);
		
		while (startX <= endX && startY <= endY) {
			for (x = startX; x <= endX; x++) {
				printElement(matrix, x, y);
			}
			startY++;
			x--;
			
			for (y = startY; y <= endY; y++) {
				printElement(matrix, x, y);
			}
			endX--;
			y--;
			
			for (x = endX; x >= startX; x--) {
				printElement(matrix, x, y);
			}
			endY--;
			x++;
			
			for (y = endY; y >= startY; y--) {
				printElement(matrix, x, y);
			}
			startX ++;
			y++;
		}
	}
	
	private static boolean sanityCheck(int[][] m) {
		return true;
	}
	
	private static void printElement(int[][] matrix, int x, int y) {
		System.out.print(matrix[y][x]);
		System.out.print(", ");
	}
	
	public static void main(String[] args) {
		int[][] m = new int[][] {
		  new int[]{1, 2, 3, 4},
		  new int[]{5, 6, 7, 8},
		  new int[]{9, 10, 11, 12},
		  new int[]{13, 14, 15, 16},
		  new int[]{17,18,19,20},		  
		};
		
		spiralPrint(m);
	}

}
