import static org.junit.Assert.*;

import java.util.ArrayList;
import java.util.Arrays;

import org.junit.Test;


public class GeneratorTest {

	@Test
	public void test() {
		ArrayList<int[][]> grids = new ArrayList<int[][]>();
		for(int k = 0; k < 100000; k++){
			int grid[][] = {
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
			boolean ans = Generator.generateSolved(grid, 0, 0);
			/*System.out.println("ans = "+ ans);
			for(int[] row : grid){
				System.out.println(Arrays.toString(row));
			}*/
			assertTrue(ans);
			for(int i = 0; i < 9; i++){
				for(int j = 0; j < 9; j++){
					assertTrue(Generator.noConflicts(grid, i, j, grid[i][j]));
				}
			}
			grids.add(grid);
		}
		
		// checking for duplicates in grid
		for(int[][] grid: grids){
			for(int[][] other: grids){
				if(other != grid && Arrays.deepEquals(grid,other)){
					System.out.println("Duplicate!!");
				}
			}
		}
		/*
		int grid[][] = {
				{6, 0, 0, 0, 4, 3, 1, 7, 2},
				{4, 3, 7, 2, 9, 1, 0, 0, 6},
				{1, 2, 8, 6, 0, 7, 3, 0, 4},
				{0, 0, 6, 7, 1, 0, 2, 4, 3},
				{7, 1, 3, 0, 2, 4, 0, 6, 0},
				{0, 4, 2, 3, 6, 0, 7, 0, 1},
				{0, 5, 1, 4, 7, 2, 6, 3, 0},
				{2, 7, 4, 0, 3, 6, 0, 1, 0},
				{3, 6, 0, 1, 0, 0, 4, 2, 7}
		};
		assertTrue(Generator.noConflicts(grid,1,1,3));
		assertFalse(Generator.noConflicts(grid,1,1,8));
		assertFalse(Generator.noConflicts(grid,1,1,9));
		assertFalse(Generator.noConflicts(grid,1,1,5)); */

	}

}
