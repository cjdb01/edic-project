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
	public final static int NOT_SET = 0;
}

