import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class GameScreen extends JPanel{
	
	private static final long serialVersionUID = 1L;
	private final JLabel message;
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
				clear.setVisible(false);
				pause.setVisible(false);
				resume.setVisible(true);
				assist.setVisible(false);
			}
		});
		resume.addActionListener(new
				ActionListener(){
			public void actionPerformed(ActionEvent event)
			{
				timer.start();
				paused = false;
				grid.showResume();
				clear.setVisible(true);
				pause.setVisible(true);
				resume.setVisible(false);
				assist.setVisible(true);
			}
		});
		
		assist.addActionListener(new
				ActionListener(){
			public void actionPerformed(ActionEvent event)
			{
				grid.displayAssist();
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
					clear.setVisible(true);
					pause.setVisible(true);
					resume.setVisible(false);
					assist.setVisible(true);
					message.setText(" ");
				}
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
		
		JPanel panel = new JPanel(); 
		message.setFont(new Font("text", Font.PLAIN, 26));
		message.setHorizontalAlignment(SwingConstants.CENTER);
		JPanel panel3 = new JPanel();
		
		this.setLayout(new BorderLayout());
		this.add(grid, BorderLayout.CENTER);
		this.add(bottom, BorderLayout.SOUTH);
		this.add(panel, BorderLayout.WEST);
		this.add(message, BorderLayout.NORTH);
		this.add(panel3, BorderLayout.EAST);
		resume.setVisible(false);	
	}
	
	public Difficulty getDifficulty(){
		return difficulty;
	}
	
	public void playNewGame(){
		grid.getNewGame(difficulty);
		timer.start();
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
}