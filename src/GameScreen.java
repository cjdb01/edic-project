import javax.swing.*;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

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
	private boolean paused;
	private final TimerField timer;
	private final SudokuGrid grid;
	private Difficulty difficulty;
	private JPanel bottom;
	
	public GameScreen (){
		super();
		clear = new JButton("Clear");
		validate = new JButton("Validate");
		pause = new JButton("Pause");
		assist = new JButton("Assist");
		resume = new JButton("Resume");
		newGame = new JButton("New Problem");
		grid = new SudokuGrid();
		timer = new TimerField();
		message = new JLabel(" ");

		clear.addActionListener(new
				ActionListener(){
			public void actionPerformed(ActionEvent event)
			{
				grid.clear();
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
				remainingAssists.setText("Remaining Assists: " + grid.getAssists() + "     ");
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
		bottom.add(clear);
		bottom.add(newGame);
		bottom.add(resume);
		bottom.add(pause);
		bottom.add(timer);
		bottom.add(assist);
		bottom.add(validate);
		
		top = new JPanel();
		top.setLayout(new BoxLayout(top, BoxLayout.X_AXIS));
		remainingAssists = new JLabel();
		message.setFont(new Font("text", Font.PLAIN, 26));
		message.setHorizontalAlignment(SwingConstants.CENTER);
		top.add(remainingAssists);
		top.add(message);
		
		this.setLayout(new BorderLayout());
		this.add(grid, BorderLayout.CENTER);
		this.add(bottom, BorderLayout.SOUTH);
		this.add(top, BorderLayout.NORTH);
		resume.setVisible(false);	
	}
	
	public Difficulty getDifficulty(){
		return difficulty;
	}
	
	public void playNewGame(){
		grid.getNewGame(difficulty);
		remainingAssists.setText("Remaining Assists: " + grid.getAssists() + "     ");
		timer.start();
		resumeButtonMode();
	}
	
	public void setDifficulty(Difficulty difficulty){
		this.difficulty = difficulty;
	}
	
	public void addButton(JButton button){
		bottom.add(button);
	}
	
	public void stopGame(){
		timer.restart();
		timer.pause();
		message.setText(" ");
	}	
	
	private void pauseButtonMode(){
		
		clear.setVisible(false);
		pause.setVisible(false);
		assist.setVisible(false);
		validate.setVisible(false);
		resume.setVisible(true);

	}
	
	private void resumeButtonMode(){
		clear.setVisible(true);
		pause.setVisible(true);
		assist.setVisible(true);
		validate.setVisible(true);
		resume.setVisible(false);
	}
	
}