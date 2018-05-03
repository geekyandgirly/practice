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
	
	public void rotateSquareMatrixInplace(int[][] m) {
		
		int n = m.length;
		int lastVal = m[n-1][0];

//		for (int row = 0; row <= n/2; row ++) {
//			for (int col = row; col < n - row -1; col++) {
//				int startRow = row, startCol = col;
//				int currRow = row, currCol = col;
//				boolean done = false;
//				while (!done) {
//					int currVal = m[currRow][currCol];
//					m[currRow][currCol] = lastVal;
//					lastVal = currVal;
//					
//					this.printMatrix(m, "[" + currRow + "]" + "[" + currCol + "]");
//
//					
//					currCol = n - currRow - 1;
//					currRow = currCol;
//					if (row == startRow && col == startCol) {
//						done = true;
//					}
//				}				
//			}
//		}
//		for(int i=0;i<n/2;i++) {
//			for(int j=i;j<n-i-1;j++) {
//				int v = m[i][j];
//				int ii = j;
//				int jj = n-i-1;
//				boolean go = true;
//				while (go) {
//					int u = m[ii][jj];
//					System.out.println(i+" "+j+" : "+ii+" "+jj+" : "+v+" "+u);
//					try {
//						Thread.sleep(1000);
//					} catch (InterruptedException e) {
//						// TODO Auto-generated catch block
//						e.printStackTrace();
//					}
//					m[ii][jj] = v;
//					go = (ii != i) || (jj != j);
//					int iii = ii;
//					ii = jj;
//					jj = n-iii-1;
//					v = u;
//					//this.printMatrix(m, "[" + ii + "]" + "[" + jj + "]");
//				} 
//			}
//		}
		for(int i=0;i<n/2;i++) for(int j=i;j<n-i-1;j++) {
			int v = m[n-j-1][i]; //prior value
			int ii = i, jj = j; // target index
			do {
				int u = m[ii][jj];m[ii][jj] = v;v = u; //replace values
				int iii = ii;ii = jj;jj = n-iii-1; // next index
			} while ((ii != i) || (jj != j)); // loop until back to start
		}
	}
	
	public int[][] initMatrix(int n) {
		int[][] mat = new int[n][n];
		for (int i = 0; i < n; i++) for (int j = 0; j < n; j++) {
				mat[i][j] = i * n  + j + 1;				
		}
		return mat;		
	}
	
	public void printMatrix(int[][] mat, String prefix) {
		System.out.println(prefix);
		int n = mat.length;
		for (int i = 0; i < n; i++) {
			for (int j = 0; j < n; j++) {
				System.out.print(mat[i][j] + "\t");				
			}
			System.out.println();
		}		
	}
	
	public static void main(String[] args) {
		RotateMatrix rotate = new RotateMatrix();
		
		int[][] mat = rotate.initMatrix(4);				
//		int[][] mat1 = rotate.initMatrix(5);
				
//		int[][] ret = rotate.rotateSquareMatrix(mat, 4);
//		rotate.printMatrix(ret, "rotate 4");

		rotate.rotateSquareMatrixInplace(mat);
		rotate.printMatrix(mat, "rotate inplace 4");

//		int[][] ret1 = rotate.rotateSquareMatrix(mat1, 5);
//		rotate.printMatrix(ret1, "rotate 4");

//		rotate.rotateSquareMatrixInplace(mat1);
//		rotate.printMatrix(mat1, "rotate inplace 4");
	}
}
