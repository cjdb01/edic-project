import javax.swing.*;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Date;

/**
 *  A class of the Game Screen component
 * @author aydinitil
 *
 */
public class GameScreen extends JPanel{
	
	private static final long serialVersionUID = 1L;
	private final JLabel message;
	private final JLabel remainingAssists;
	private final JPanel top;
	private JButton clear;
	private JButton validate;
	private JButton pause;
	private JButton assist;
	private JButton newGame;
	private JButton resume;
	private JButton saveGame;
	private boolean paused;
	private final TimerField timer;
	private final SudokuGrid grid;
	private Difficulty difficulty;
	private JPanel bottom;
	
	/**
	 * Constructs the Game Screen
	 */
	public GameScreen (){
		super();
		clear = new JButton("Clear");
		validate = new JButton("Validate");
		pause = new JButton("Pause");
		assist = new JButton("Assist");
		resume = new JButton("Resume");
		newGame = new JButton("New Problem");
		saveGame = new JButton("Save");
		grid = new SudokuGrid();
		timer = new TimerField();
		timer.setMaximumSize(new Dimension(200, 50));
		message = new JLabel(" ");

		clear.addActionListener(new
				ActionListener(){
			public void actionPerformed(ActionEvent event)
			{
				grid.clear();
			}
		});
		
		saveGame.addActionListener(new
				ActionListener(){
			public void actionPerformed(ActionEvent event)
			{
				message.setText( "Saved: " + saveGame());
			}
		});
		
		pause.addActionListener(new
				ActionListener(){
			public void actionPerformed(ActionEvent event)
			{
				timer.pause();
				paused = true;
				grid.showPause();
				pauseButtonMode();
			}
		});
		resume.addActionListener(new
				ActionListener(){
			public void actionPerformed(ActionEvent event)
			{
				timer.start();
				paused = false;
				grid.showResume();
				resumeButtonMode();
			}
		});
		
		assist.addActionListener(new
				ActionListener(){
			public void actionPerformed(ActionEvent event)
			{
				grid.displayAssist();
				remainingAssists.setText("Remaining Assists: " + Integer.toString(grid.getAssists()) + "        ");
			}
		});
		
		newGame.addActionListener(new
				ActionListener(){
			public void actionPerformed(ActionEvent event)
			{
				grid.getNewGame(getDifficulty());
				timer.restart();
				if(paused == true){
					timer.start();
					resumeButtonMode();
					paused = false;
				}
				message.setText(" ");
				timer.start();
			}
		});	
		validate.addActionListener(new
				ActionListener(){
			public void actionPerformed(ActionEvent event)
			{
				grid.updateSudoku();
				message.setText(grid.getStatus());
				if(grid.getStatus().equals("You WIN!")){
					timer.pause();
				}
			}
		});	
		bottom = new JPanel();
		bottom.setLayout(new BoxLayout(bottom, BoxLayout.Y_AXIS));
		bottom.add(assist);
		bottom.add(clear);
		bottom.add(newGame);
		bottom.add(validate);
		bottom.add(pause);
		bottom.add(resume);
		bottom.add(timer);
		bottom.add(saveGame);
		
		top = new JPanel();
		top.setLayout(new BoxLayout(top, BoxLayout.X_AXIS));
		remainingAssists = new JLabel();
		message.setFont(new Font("text", Font.PLAIN, 26));
		message.setHorizontalAlignment(SwingConstants.CENTER);
		top.add(remainingAssists);
		
		this.setLayout(new BorderLayout());
		this.add(message, BorderLayout.NORTH);
		this.add(grid, BorderLayout.CENTER);
		this.add(bottom, BorderLayout.WEST);
		this.add(top, BorderLayout.SOUTH);
		resume.setVisible(false);	
	}
	
	public Difficulty getDifficulty(){
		return difficulty;
	}
	
	/**
	 * Begins a new game
	 */
	public void playNewGame(){
		grid.getNewGame(difficulty);
		remainingAssists.setText("Remaining Assists: " + Integer.toString(grid.getAssists()) + "        ");
		timer.start();
		resumeButtonMode();
	}
	
	public void setDifficulty(Difficulty difficulty){
		this.difficulty = difficulty;
	}
	
	/**
	 * Adds the button to the button panel
	 * @param button The Button to be added
	 */
	public void addButton(JButton button){
		bottom.add(button);
	}
	
	/**
	 * Ends the current game
	 */
	public void stopGame(){
		timer.restart();
		timer.pause();
		message.setText(" ");
	}	
	
	/*
	 * Saves a game with a timestamp filename
	 */
	private String saveGame(){
		Date now = new Date();
		Sudoku game = grid.getGame();
		Interpreter.saveGame(game, timer.getTime(), now.toString());
		return now.toString();
	}
	
	/*
	 * Hides and reveals buttons to represent pause mode
	 */
	private void pauseButtonMode(){
		
		clear.setVisible(false);
		pause.setVisible(false);
		assist.setVisible(false);
		validate.setVisible(false);
		resume.setVisible(true);

	}
	
	/*
	 * Hides and reveals buttons to represent resume mode
	 */
	private void resumeButtonMode(){
		clear.setVisible(true);
		pause.setVisible(true);
		assist.setVisible(true);
		validate.setVisible(true);
		resume.setVisible(false);
	}
	
}