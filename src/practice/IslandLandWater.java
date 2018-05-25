package practice;

/** 
 * https://leetcode.com/problems/max-area-of-island/
 * 
 **/

public class IslandLandWater {
	
    public int maxAreaOfIsland(int[][] grid) {
        int cols = grid[0].length;
        int rows = grid.length;
        boolean[][] visited = new boolean[rows][cols];
        int max = 0;
        
        for (int row = 0; row < rows; row++) {
            for (int col = 0; col < cols; col++) {
                if (grid[row][col] == 1 && !visited[row][col]) {
                    max = Math.max(max, dfs(row, col, grid, visited, rows, cols)); 
                }
            }
        }
        return max;
    }
    
    private int dfs(int row, int col, int[][] grid, boolean[][] visited, int rows, int cols) {
        if (row < 0 || col < 0 || row >= rows || col >= cols) {
            return 0;
        }
        if (grid[row][col] == 0 || visited[row][col]) {
            return 0;
        }
        visited[row][col] = true;

        return 1 + dfs(row - 1, col, grid, visited, rows, cols) // up
        	+ dfs(row, col + 1, grid, visited, rows, cols) // right
        	+ dfs(row + 1, col, grid, visited, rows, cols) // down
        	+ dfs(row, col - 1, grid, visited, rows, cols); // left
    }
}