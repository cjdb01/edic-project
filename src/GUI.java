import java.awt.BorderLayout;
import javax.swing.*;
import java.awt.*;

import javax.swing.JFrame;

public class GUI {
	
	Generator generator;
	SudokuGrid grid;
	JFrame frame;
	
	public GUI(){
	
		grid = new SudokuGrid(Generator.constructBoard(null));
		frame = new JFrame("Sudoku Solver");
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setLayout(new BorderLayout());
		frame.add(grid, BorderLayout.CENTER);
		frame.setResizable(false);
		frame.setVisible(true);
		frame.pack();
	}

	public static void main(String[] args) {
		new GUI();
	}
}