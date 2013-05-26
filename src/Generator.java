import java.util.Collections;
import java.util.LinkedList;

/**
 * Generates a new sudoku Board given parameters
 * I think this would be fine to have static methods?
 * @author nattyboy
 *
 */
public class Generator {
	public static Sudoku constructBoard(Difficulty difficulty){
		int toSolve[][] = {
				{NOT_SET, NOT_SET, NOT_SET, NOT_SET, NOT_SET, 3, NOT_SET, 7, NOT_SET},
				{4, NOT_SET, 7, NOT_SET, 9, NOT_SET, 5, NOT_SET, 6},
				{1, 2, NOT_SET, 6, 5, NOT_SET, NOT_SET, NOT_SET, 4},
				{5, 8, 6, 7, NOT_SET, NOT_SET, 2, NOT_SET, NOT_SET},
				{NOT_SET, NOT_SET, 3, NOT_SET, NOT_SET, NOT_SET, 9, NOT_SET, NOT_SET},
				{NOT_SET, NOT_SET, 2, NOT_SET, NOT_SET, 8, 7, 5, 1},
				{8, NOT_SET, NOT_SET, NOT_SET, 7, 2, NOT_SET, 3, 9},
				{2, NOT_SET, 4, NOT_SET, 3, NOT_SET, 8, NOT_SET, 5},
				{NOT_SET, 6, NOT_SET, 1, NOT_SET, NOT_SET, NOT_SET, NOT_SET, NOT_SET}
		};
		int grid[][] = {
				{NOT_SET, NOT_SET, NOT_SET, NOT_SET, NOT_SET, NOT_SET, NOT_SET, NOT_SET, NOT_SET},
				{NOT_SET, NOT_SET, NOT_SET, NOT_SET, NOT_SET, NOT_SET, NOT_SET, NOT_SET, NOT_SET},
				{NOT_SET, NOT_SET, NOT_SET, NOT_SET, NOT_SET, NOT_SET, NOT_SET, NOT_SET, NOT_SET},
				{NOT_SET, NOT_SET, NOT_SET, NOT_SET, NOT_SET, NOT_SET, NOT_SET, NOT_SET, NOT_SET},
				{NOT_SET, NOT_SET, NOT_SET, NOT_SET, NOT_SET, NOT_SET, NOT_SET, NOT_SET, NOT_SET},
				{NOT_SET, NOT_SET, NOT_SET, NOT_SET, NOT_SET, NOT_SET, NOT_SET, NOT_SET, NOT_SET},
				{NOT_SET, NOT_SET, NOT_SET, NOT_SET, NOT_SET, NOT_SET, NOT_SET, NOT_SET, NOT_SET},
				{NOT_SET, NOT_SET, NOT_SET, NOT_SET, NOT_SET, NOT_SET, NOT_SET, NOT_SET, NOT_SET},
				{NOT_SET, NOT_SET, NOT_SET, NOT_SET, NOT_SET, NOT_SET, NOT_SET, NOT_SET, NOT_SET}
		};
		generateSolved(grid, 0, 0);
		int solved[][] = {
				{6, 9, 5, 8, 4, 3, 1, 7, 2},
				{4, 3, 7, 2, 9, 1, 5, 8, 6},
				{1, 2, 8, 6, 5, 7, 3, 9, 4},
				{5, 8, 6, 7, 1, 9, 2, 4, 3},
				{7, 1, 3, 5, 2, 4, 9, 6, 8},
				{9, 4, 2, 3, 6, 8, 7, 5, 1},
				{8, 5, 1, 4, 7, 2, 6, 3, 9},
				{2, 7, 4, 9, 3, 6, 8, 1, 5},
				{3, 6, 9, 1, 8, 5, 4, 2, 7}
		};
		return new Sudoku(toSolve, solved, 3);
	}
	
	/**
	 * Generates a random solved 9x9 sudoku as a 2d array. 
	 * Pretty quick, can generate 10000 games in about 1.7s.
	 * Also, none of the 10000 games are repeated.
	 * 
	 * @param grid the grid on which the solved sudoku is generated (initialized as NOT_SET).
	 * @param curRow the current row of the grid (initialized as 0).
	 * @param curCol the current column of grid (initialized as 0).
	 * @return true if the sudoku has been generated properly.
	 */
	public static boolean generateSolved(int[][] grid, int curRow, int curCol){
		// a list containing numbers between 1 and 9 (included) which have yet to be tried
		LinkedList<Integer> toTry = new LinkedList<Integer>();
		for(int i = 1; i <= 9; i++){
			toTry.add(i);
		}
		while(!toTry.isEmpty()){
			// generate a random number between 1 and 9 which hasn't been previously tried.
			Collections.shuffle(toTry);
			int attempt = toTry.poll();
			if(noConflicts(grid, curRow, curCol, attempt)){
				grid[curRow][curCol] = attempt;
				// if this is at the bottom right corner, then all other values are assigned
				// and the sudoku has been generated
				if(curRow == 8 && curCol == 8){
					return true;
				}
				// mark the value as tried
				int newRow = curRow;
				int newCol = (curCol+1)%9;
				if(curCol == 8){
					newRow = curRow+1;
				}
				if(generateSolved(grid,newRow, newCol)){
					return true;
				}
				// if it gets here, means the attempt was unsuccessful
				grid[curRow][curCol] = NOT_SET;
			}
		}
		return false;
	}

	/**
	 * Checks if a value can be placed in a position on the grid without producing conflict.
	 * 
	 * @param grid the sudoku grid on which the check takes place.
	 * @param row the row number of the specified position, where row 0 is the top row.
	 * @param column the column number of the specified position, where column 0 is the left column.
	 * @param value the value to be checked, where 0 < value < 10.
	 * @return true if placing the value in the specified position produces no conflicts; false otherwise.
	 */
	public static boolean noConflicts(int[][] grid, int row, int column, int value){
		//checking along row
		for(int i = 0; i < grid[row].length; i++){
			if(i != column && grid[row][i] == value){
				return false;
			}
		}
		
		// checking along column
		for(int i = 0; i < grid.length; i++){
			if(i != row && grid[i][column] == value){
				return false;
			}
		}
		
		// checking in box
		// first find which box (row,column) is in
		int boxRow = row/3;
		int boxCol = column/3;
		for(int i = 0; i < 3; i ++){
			for(int j = 0; j< 3; j++){
				// if the current cell is not the specified position and the cell already contains the value
				if((i + 3*boxRow != row || j + 3*boxCol != column) && grid[3*boxRow+i][3*boxCol+j] == value){
					return false;
				}
			}
		}
		return true;
	}
	
	public final static int NOT_SET = 0;
}

