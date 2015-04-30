package practice;

public class Paths {
	/**
	 * Given a nxm grid, find all possible path from [0][0] to [n][m]
	 */
	public static void printPaths(int rows, int cols) {
		int row = 0; int col = 0;
		printPath("->R", row, col + 1, rows, cols);
		printPath("->D", row + 1, col, rows, cols);
	}

	private static void printPath(String p, int row, int col, int rows, int cols) {
		if ((row == rows -1) && (col == cols -1)) {
			System.out.println(p);
		}
		
		if (col < cols - 1) {
			printPath(p + "->R", row, col + 1, rows, cols);
		}
		
		if (row < rows - 1) {
			printPath(p+ "->D", row + 1, col, rows, cols);			
		}
	}
	
	public static void main(String[] args) {
		System.out.println("3x3");
		Paths.printPaths(3, 3);
		
		System.out.println("4x5");
		Paths.printPaths(4, 5);
	}
}
