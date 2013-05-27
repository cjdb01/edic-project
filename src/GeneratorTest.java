import static org.junit.Assert.*;

import java.util.ArrayList;
import java.util.Arrays;

import org.junit.Test;


public class GeneratorTest {

	@Test
	// note: for these tests it is sometimes necessary to change the private methods to public
	public void test() {
//		ArrayList<int[][]> grids = new ArrayList<int[][]>();
//		for(int k = 0; k < 100000; k++){
//			int grid[][] = {
//					{Generator.NOT_SET, Generator.NOT_SET, Generator.NOT_SET, Generator.NOT_SET, Generator.NOT_SET, Generator.NOT_SET, Generator.NOT_SET, Generator.NOT_SET, Generator.NOT_SET},
//					{Generator.NOT_SET, Generator.NOT_SET, Generator.NOT_SET, Generator.NOT_SET, Generator.NOT_SET, Generator.NOT_SET, Generator.NOT_SET, Generator.NOT_SET, Generator.NOT_SET},
//					{Generator.NOT_SET, Generator.NOT_SET, Generator.NOT_SET, Generator.NOT_SET, Generator.NOT_SET, Generator.NOT_SET, Generator.NOT_SET, Generator.NOT_SET, Generator.NOT_SET},
//					{Generator.NOT_SET, Generator.NOT_SET, Generator.NOT_SET, Generator.NOT_SET, Generator.NOT_SET, Generator.NOT_SET, Generator.NOT_SET, Generator.NOT_SET, Generator.NOT_SET},
//					{Generator.NOT_SET, Generator.NOT_SET, Generator.NOT_SET, Generator.NOT_SET, Generator.NOT_SET, Generator.NOT_SET, Generator.NOT_SET, Generator.NOT_SET, Generator.NOT_SET},
//					{Generator.NOT_SET, Generator.NOT_SET, Generator.NOT_SET, Generator.NOT_SET, Generator.NOT_SET, Generator.NOT_SET, Generator.NOT_SET, Generator.NOT_SET, Generator.NOT_SET},
//					{Generator.NOT_SET, Generator.NOT_SET, Generator.NOT_SET, Generator.NOT_SET, Generator.NOT_SET, Generator.NOT_SET, Generator.NOT_SET, Generator.NOT_SET, Generator.NOT_SET},
//					{Generator.NOT_SET, Generator.NOT_SET, Generator.NOT_SET, Generator.NOT_SET, Generator.NOT_SET, Generator.NOT_SET, Generator.NOT_SET, Generator.NOT_SET, Generator.NOT_SET},
//					{Generator.NOT_SET, Generator.NOT_SET, Generator.NOT_SET, Generator.NOT_SET, Generator.NOT_SET, Generator.NOT_SET, Generator.NOT_SET, Generator.NOT_SET, Generator.NOT_SET}
//			};
//			boolean ans = Generator.generateSolved(grid, 0, 0);
//			/*System.out.println("ans = "+ ans);
//			for(int[] row : grid){
//				System.out.println(Arrays.toString(row));
//			}*/
//			assertTrue(ans);
//			for(int i = 0; i < 9; i++){
//				for(int j = 0; j < 9; j++){
//					assertTrue(Generator.noConflicts(grid, i, j, grid[i][j]));
//				}
//			}
//			grids.add(grid);
//		}
//		
//		// checking for duplicates in grid
//		for(int[][] grid: grids){
//			for(int[][] other: grids){
//				if(other != grid && Arrays.deepEquals(grid,other)){
//					System.out.println("Duplicate!!");
//				}
//			}
//		}
		
//		int grid[][] = {
//				{6, 0, 0, 0, 4, 3, 1, 7, 2},
//				{4, 3, 7, 2, 9, 1, 0, 0, 6},
//				{1, 2, 8, 6, 0, 7, 3, 0, 4},
//				{0, 0, 6, 7, 1, 0, 2, 4, 3},
//				{7, 1, 3, 0, 2, 4, 0, 6, 0},
//				{0, 4, 2, 3, 6, 0, 7, 0, 1},
//				{0, 5, 1, 4, 7, 2, 6, 3, 0},
//				{2, 7, 4, 0, 3, 6, 0, 1, 0},
//				{3, 6, 0, 1, 0, 0, 4, 2, 7}
//		};
//		assertTrue(Generator.noConflicts(grid,1,1,3));
//		assertFalse(Generator.noConflicts(grid,1,1,8));
//		assertFalse(Generator.noConflicts(grid,1,1,9));
//		assertFalse(Generator.noConflicts(grid,1,1,5));
		
//		// testing solver
//		int toSolve[][] = {
//				{Generator.NOT_SET, Generator.NOT_SET, Generator.NOT_SET, Generator.NOT_SET, Generator.NOT_SET, 3, Generator.NOT_SET, 7, Generator.NOT_SET},
//				{4, Generator.NOT_SET, 7, Generator.NOT_SET, 9, Generator.NOT_SET, 5, Generator.NOT_SET, 6},
//				{1, 2, Generator.NOT_SET, 6, 5, Generator.NOT_SET, Generator.NOT_SET, Generator.NOT_SET, 4},
//				{5, 8, 6, 7, Generator.NOT_SET, Generator.NOT_SET, 2, Generator.NOT_SET, Generator.NOT_SET},
//				{Generator.NOT_SET, Generator.NOT_SET, 3, Generator.NOT_SET, Generator.NOT_SET, Generator.NOT_SET, 9, Generator.NOT_SET, Generator.NOT_SET},
//				{Generator.NOT_SET, Generator.NOT_SET, 2, Generator.NOT_SET, Generator.NOT_SET, 8, 7, 5, 1},
//				{8, Generator.NOT_SET, Generator.NOT_SET, Generator.NOT_SET, 7, 2, Generator.NOT_SET, 3, 9},
//				{2, Generator.NOT_SET, 4, Generator.NOT_SET, 3, Generator.NOT_SET, 8, Generator.NOT_SET, 5},
//				{Generator.NOT_SET, 6, Generator.NOT_SET, 1, Generator.NOT_SET, Generator.NOT_SET, Generator.NOT_SET, Generator.NOT_SET, Generator.NOT_SET}
//				};
//		
//		Generator.solutionCounter = 0;
//		boolean unique = Generator.solve(toSolve, 0, 0);
//		System.out.println("Solved");
//		System.out.println("Multiple Solutions = " + unique);
//		System.out.println(Generator.solutionCounter);
//		for(int[] row : toSolve){
//			System.out.println(Arrays.toString(row));
//		}
//		int answer[][] = {
//				{6, 9, 5, 8, 4, 3, 1, 7, 2},
//				{4, 3, 7, 2, 9, 1, 5, 8, 6},
//				{1, 2, 8, 6, 5, 7, 3, 9, 4},
//				{5, 8, 6, 7, 1, 9, 2, 4, 3},
//				{7, 1, 3, 5, 2, 4, 9, 6, 8},
//				{9, 4, 2, 3, 6, 8, 7, 5, 1},
//				{8, 5, 1, 4, 7, 2, 6, 3, 9},
//				{2, 7, 4, 9, 3, 6, 8, 1, 5},
//				{3, 6, 9, 1, 8, 5, 4, 2, 7}
//				};
//		System.out.println("Answer");
//		for(int[] row : answer){
//			System.out.println(Arrays.toString(row));
//		}
		
		// testing generation of problem:
		System.out.println("Generating solved sudoku");
		int solved[][] = {
				{Generator.NOT_SET, Generator.NOT_SET, Generator.NOT_SET, Generator.NOT_SET, Generator.NOT_SET, Generator.NOT_SET, Generator.NOT_SET, Generator.NOT_SET, Generator.NOT_SET},
				{Generator.NOT_SET, Generator.NOT_SET, Generator.NOT_SET, Generator.NOT_SET, Generator.NOT_SET, Generator.NOT_SET, Generator.NOT_SET, Generator.NOT_SET, Generator.NOT_SET},
				{Generator.NOT_SET, Generator.NOT_SET, Generator.NOT_SET, Generator.NOT_SET, Generator.NOT_SET, Generator.NOT_SET, Generator.NOT_SET, Generator.NOT_SET, Generator.NOT_SET},
				{Generator.NOT_SET, Generator.NOT_SET, Generator.NOT_SET, Generator.NOT_SET, Generator.NOT_SET, Generator.NOT_SET, Generator.NOT_SET, Generator.NOT_SET, Generator.NOT_SET},
				{Generator.NOT_SET, Generator.NOT_SET, Generator.NOT_SET, Generator.NOT_SET, Generator.NOT_SET, Generator.NOT_SET, Generator.NOT_SET, Generator.NOT_SET, Generator.NOT_SET},
				{Generator.NOT_SET, Generator.NOT_SET, Generator.NOT_SET, Generator.NOT_SET, Generator.NOT_SET, Generator.NOT_SET, Generator.NOT_SET, Generator.NOT_SET, Generator.NOT_SET},
				{Generator.NOT_SET, Generator.NOT_SET, Generator.NOT_SET, Generator.NOT_SET, Generator.NOT_SET, Generator.NOT_SET, Generator.NOT_SET, Generator.NOT_SET, Generator.NOT_SET},
				{Generator.NOT_SET, Generator.NOT_SET, Generator.NOT_SET, Generator.NOT_SET, Generator.NOT_SET, Generator.NOT_SET, Generator.NOT_SET, Generator.NOT_SET, Generator.NOT_SET},
				{Generator.NOT_SET, Generator.NOT_SET, Generator.NOT_SET, Generator.NOT_SET, Generator.NOT_SET, Generator.NOT_SET, Generator.NOT_SET, Generator.NOT_SET, Generator.NOT_SET}
		};
		Generator.generateSolved(solved, 0, 0);
		for(int[] row : solved){
			System.out.println(Arrays.toString(row));
		}
		System.out.println();
		System.out.println("Generating problem");
		int[][] problem = Generator.generateProblem(solved, 81
				
				);
		for(int[] row : problem){
			System.out.println(Arrays.toString(row));
		}
		

	}

}
