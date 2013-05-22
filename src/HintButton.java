import java.awt.BorderLayout;
import javax.swing.*;
import java.awt.*;

import javax.swing.JFrame;

public class GUI {
	
	SudokuGrid grid;
	HintButton hButton;
	JFrame frame;
	
	public GUI(){
        
		grid = new SudokuGrid(Generator.constructBoard(Difficulty.MEDIUM));
		hButton = new HintButton(grid);
		frame = new JFrame("Sudoku Game");
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setLayout(new BorderLayout());
		frame.add(grid, BorderLayout.CENTER);
		frame.add(hButton, BorderLayout.SOUTH);
		
		frame.setResizable(false);
		frame.setVisible(true);
		frame.pack();
	}
    
	public static void main(String[] args) {
		new GUI();
	}
}