package practice;

public class RotateMatrix {
	
	public int[][] rotateSquareMatrix(int[][] mat, int n) {
		int[][] ret = new int[n][n];
		for (int row = 0; row < n; row++) {
			for (int col = 0; col < n; col++) {
				ret[col][n-row-1] = mat[row][col];
			}
		}
		return ret;
	}

}
