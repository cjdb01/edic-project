import java.awt.BorderLayout;
import javax.swing.*;
import java.awt.*;

import javax.swing.JFrame;

public class GUI {
	
	JFrame frame;
	GameScreen game;
	
	public GUI(){

		game = new GameScreen();
		frame = new JFrame("Sudoku Game");
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setLayout(new BorderLayout());
		frame.add(game, BorderLayout.CENTER);
		//time.startTimer();
		
		//frame.setResizable(false);
		frame.setVisible(true);
		frame.pack();
	}

	public static void main(String[] args) {
		new GUI();
	}
}