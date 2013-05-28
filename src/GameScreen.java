import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class GameScreen extends JPanel{
	
	private JButton clear;
	private JButton validate;
	private JButton pause;
	private JButton assist;
	private JButton exit;
	private JButton newGame;
	private JButton resume;
	private boolean paused;
	private final TimerField timer;
	private final SudokuGrid grid;
	
	public GameScreen (){
		super();
		clear = new JButton("Clear");
		validate = new JButton("Validate");
		pause = new JButton("Pause");
		assist = new JButton("Assist");
		exit = new JButton("Exit");
		resume = new JButton("Resume");
		newGame = new JButton("New Problem");
		grid = new SudokuGrid(Generator.constructBoard(Difficulty.EXPERT));
		timer = new TimerField();
		timer.start();

		clear.addActionListener(new
				ActionListener(){
			public void actionPerformed(ActionEvent event)
			{
				grid.clear();
			}
		});
		
		validate.addActionListener(new
				ActionListener(){
			public void actionPerformed(ActionEvent event)
			{
				grid.validate();
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
				grid.getNewGame(Generator.constructBoard(Difficulty.EXPERT));
				timer.restart();
				if(paused == true){
					timer.start();
					clear.setVisible(true);
					pause.setVisible(true);
					resume.setVisible(false);
					assist.setVisible(true);
				}
			}
		});
		
		
		JPanel bottom = new JPanel();
		bottom.add(clear);
		bottom.add(resume);
		bottom.add(pause);
		bottom.add(timer);
		bottom.add(newGame);
		bottom.add(assist);
		
		this.setLayout(new BorderLayout());
		this.add(grid, BorderLayout.CENTER);
		this.add(bottom, BorderLayout.SOUTH);
		resume.setVisible(false);
		
	}
	
}