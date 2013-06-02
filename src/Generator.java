import java.util.Collections;
import java.util.LinkedList;

/**
 * Generates a new sudoku Board given parameters
 * I think this would be fine to have static methods?
 * @author nattyboy
 *
 */
public class Generator {
	public static Sudoku constructBoard(Difficulty difficulty) throws IllegalArgumentException {
		int numSpaces;
		int numHints;
		// these are just arbitrary numbers atm
		// can do other things with difficulty later
		switch(difficulty) {
		case KIDS:	
			numSpaces = 10;
			numHints = 5;
			break;
		case EASY:
			numSpaces = 20;
			numHints = 3;
			break;
		case MEDIUM:
			numSpaces = 40;
			numHints = 1;
			break;
		case HARD:
			numSpaces = 50;
			numHints = 0;
			break;
		case EXPERT:
			// will generate a minimal sudoku problem
			// no unique sudoku puzzles have been found with under 17 givens
			numSpaces = 65;
			numHints = 0;
			break;
		default:
			throw(new IllegalArgumentException("Invalid difficulty"));
		}
		int solved[][] = {
				{Sudoku.NOT_SET, Sudoku.NOT_SET, Sudoku.NOT_SET, Sudoku.NOT_SET, Sudoku.NOT_SET, Sudoku.NOT_SET, Sudoku.NOT_SET, Sudoku.NOT_SET, Sudoku.NOT_SET},
				{Sudoku.NOT_SET, Sudoku.NOT_SET, Sudoku.NOT_SET, Sudoku.NOT_SET, Sudoku.NOT_SET, Sudoku.NOT_SET, Sudoku.NOT_SET, Sudoku.NOT_SET, Sudoku.NOT_SET},
				{Sudoku.NOT_SET, Sudoku.NOT_SET, Sudoku.NOT_SET, Sudoku.NOT_SET, Sudoku.NOT_SET, Sudoku.NOT_SET, Sudoku.NOT_SET, Sudoku.NOT_SET, Sudoku.NOT_SET},
				{Sudoku.NOT_SET, Sudoku.NOT_SET, Sudoku.NOT_SET, Sudoku.NOT_SET, Sudoku.NOT_SET, Sudoku.NOT_SET, Sudoku.NOT_SET, Sudoku.NOT_SET, Sudoku.NOT_SET},
				{Sudoku.NOT_SET, Sudoku.NOT_SET, Sudoku.NOT_SET, Sudoku.NOT_SET, Sudoku.NOT_SET, Sudoku.NOT_SET, Sudoku.NOT_SET, Sudoku.NOT_SET, Sudoku.NOT_SET},
				{Sudoku.NOT_SET, Sudoku.NOT_SET, Sudoku.NOT_SET, Sudoku.NOT_SET, Sudoku.NOT_SET, Sudoku.NOT_SET, Sudoku.NOT_SET, Sudoku.NOT_SET, Sudoku.NOT_SET},
				{Sudoku.NOT_SET, Sudoku.NOT_SET, Sudoku.NOT_SET, Sudoku.NOT_SET, Sudoku.NOT_SET, Sudoku.NOT_SET, Sudoku.NOT_SET, Sudoku.NOT_SET, Sudoku.NOT_SET},
				{Sudoku.NOT_SET, Sudoku.NOT_SET, Sudoku.NOT_SET, Sudoku.NOT_SET, Sudoku.NOT_SET, Sudoku.NOT_SET, Sudoku.NOT_SET, Sudoku.NOT_SET, Sudoku.NOT_SET},
				{Sudoku.NOT_SET, Sudoku.NOT_SET, Sudoku.NOT_SET, Sudoku.NOT_SET, Sudoku.NOT_SET, Sudoku.NOT_SET, Sudoku.NOT_SET, Sudoku.NOT_SET, Sudoku.NOT_SET}
		};
		generateSolved(solved, 0, 0);
		int [][]toSolve = generateProblem(solved, numSpaces);
		return new Sudoku(toSolve, solved, numHints);
	}
	
	/**
	 * Generates a random solved 9x9 sudoku as a 2d array. 
	 * Pretty quick, can generate 10000 games in about 1.7s.
	 * Also, when 100000 games are generated, none are repeated.
	 * 
	 * @param grid the grid on which the solved sudoku is generated (initialized as Sudoku.NOT_SET).
	 * @param curRow the current row of the grid (initialized as 0).
	 * @param curCol the current column of grid (initialized as 0).
	 * @return true if the sudoku has been generated properly.
	 */
	//TODO change to private
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
				grid[curRow][curCol] = Sudoku.NOT_SET;
			}
		}
		return false;
	}

	// creates an initial problem from a solved sudoku board
	// numSpaces is the number of empty spaces the problem should have
	// will make the problem have as close to numSpaces empty spaces as possible
	// TODO change to private
	public static int[][] generateProblem(int[][] solved, int numSpaces){
		// copy the solved array so it doesn't get changed
		int[][] problem = new int[solved.length][];
		for(int i = 0; i < solved.length; i++){
			problem[i] = new int[solved[i].length];
			System.arraycopy(solved[i], 0, problem[i], 0, solved[i].length);
		}
		// the number of spaces in the current board
		int curSpaces = 0;
		// make a list of all possible cell indexes from which a number can be removed
		LinkedList<Integer> toTry = new LinkedList<Integer>();
		for(int i = 0; i < 81; i++){
			toTry.add(i);
		}
		// shuffle the list randomly
		Collections.shuffle(toTry);
		while(!toTry.isEmpty() && curSpaces < numSpaces){
			int attempt = toTry.poll();
			int rowIndex = attempt/9;
			int colIndex = attempt%9;
			if(problem[rowIndex][colIndex] != Sudoku.NOT_SET){
				int temp = problem[rowIndex][colIndex];
				problem[rowIndex][colIndex] = Sudoku.NOT_SET;
				curSpaces ++;
				// if its not unique, revert
				if(!uniqueSolution(problem)){
					problem[rowIndex][colIndex] = temp;
					curSpaces --;
				}
			}
		}
		return problem;
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
	//TODO change to private
	private static boolean noConflicts(int[][] grid, int row, int column, int value){
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
	
	// returns true if an unique solution exists
	private static boolean uniqueSolution(int[][] problem){
		solutionCounter = 0;
		solve(problem, 0, 0);
		if(solutionCounter == 1){
			return true;
		}
		return false;
	}
	
	// basic bruteforce, backtracking algorithm, 
	// will stop after 2 solutions have been found
	// will change problem during the algorithm, but should be set back to original after
	// requires solutionCounter to be set to 0 before calling
	// returns a true if multiple solutions have been found, false otherwise
	private static boolean solve(int[][] problem, int row, int col){
		// advance row and col to the next empty spot in the problem array 
		int index = 9*row + col;
		while(row < problem.length && problem[row][col] != Sudoku.NOT_SET){
			index += 1;
			row = index/9;
			col = index%9;
		}
		// if couldn't find anything, then a proper solution is assigned
		if(row == problem.length){
			solutionCounter += 1;
			return true;
		}
	
		for(int num = 1; num <= 9; num ++){
			if(noConflicts(problem, row, col, num)){
				problem[row][col] = num;
				// recur, if more than one solution has been found, stop
				//System.out.printf("Row: %d, Col: %d\n", row, col);
				if(solve(problem, row, col) && solutionCounter > 1){
					problem[row][col] = Sudoku.NOT_SET;// change it back to initial problem
					return true;
				}
				problem[row][col] = Sudoku.NOT_SET;
			}
		}
		// backtrack
		return false;
	}
	
	/*
	//TODO change to private
	public DLinksNode generateMatrix(int[][] problem){
		DLinksNode root = new RootNode();
		// these point to the start of their relevant section of constraint headers
		DLinksNode rowcol = root;
		DLinksNode rownum = root;
		DLinksNode colnum = root;
		DLinksNode boxnum = root;
		//iterate through all cells
		for(int i = 0; i < problem.length; i++){
			for(int j = 0; j < problem[i].length; j++){
				if(problem[i][j] == Sudoku.NOT_SET){
					// generate rows 1-9 representing possibilities
					// need to generate suitable columns, if they haven't already been created
					
				}
			}
		}
	}
	*/
	private static int solutionCounter;
	
}

