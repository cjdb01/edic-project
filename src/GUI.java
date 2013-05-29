import java.awt.BorderLayout;
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JFrame;

public class GUI implements ActionListener {
	
	static GameScreen gameCard;
	static JPanel menuCard;
	static CardLayout cardLayout;
	static JPanel cards;
	static JButton quit;
	static JButton kids;
	static JButton easy;
	static JButton hard;
	static JButton medium;
	static JButton expert;
	public static Difficulty difficulty;
	
	public static void main(String[] args) {

		JFrame frame = new JFrame();
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		JPanel pane = (JPanel) frame.getContentPane();
		cards = new JPanel();
		cardLayout = new CardLayout();
		cards.setLayout(cardLayout);
		
		// create listener for buttons
		ActionListener listener = new GUI();
		
		//initialise cards
		gameCard = new GameScreen();
		menuCard = new JPanel();
		menuCard.setLayout(new GridBagLayout());
		
		//initialise buttons
		quit = new JButton("Quit");
		kids = new JButton("Kids");
		easy = new JButton("Easy");
		medium = new JButton("Medium");
		hard = new JButton("Hard");
		expert = new JButton("Expert");
		
		JPanel rightPanel = new JPanel();
		JPanel leftPanel = new JPanel();
		leftPanel.setPreferredSize(new Dimension(500, 500));
		rightPanel.setLayout(new BoxLayout(rightPanel, BoxLayout.Y_AXIS));
		leftPanel.setLayout(new BoxLayout(leftPanel, BoxLayout.Y_AXIS));
		
		rightPanel.add(kids);
		rightPanel.add(easy);
		rightPanel.add(medium);
		rightPanel.add(hard);
		rightPanel.add(expert);
		
		//Menu buttons
		JButton play = new JButton("Play");
		JButton options = new JButton("Options");
		JButton help = new JButton("Help");
		JButton credits = new JButton("Credits");
		play.setPreferredSize(new Dimension(100, 100));
		
		leftPanel.add(play);
		leftPanel.add(options);
		leftPanel.add(help);
		leftPanel.add(credits);
		
		//add static buttons to cards
		gameCard.addButton(quit);
		menuCard.add(rightPanel);
		//add buttons to menu
		menuCard.add(leftPanel);
		
		//add cards
		cards.add("GameScreen", gameCard);
		cards.add("Menu", menuCard);
		
		//add listeners to static buttons
		quit.addActionListener(listener);
		kids.addActionListener(listener);
		easy.addActionListener(listener);
		medium.addActionListener(listener);
		hard.addActionListener(listener);
		expert.addActionListener(listener);
		
		play.addActionListener(new
				ActionListener(){
			public void actionPerformed(ActionEvent e){
				showDifficultyButtons();
			}
		});
		
		hideDifficultyButtons();
		pane.add(cards);
		cardLayout.show(cards, "Menu");
		frame.setSize(650, 500);
		frame.setLocationRelativeTo(null);
		frame.setResizable(false);
		frame.setVisible(true);
	}
	public void actionPerformed(ActionEvent e){
		if(e.getSource() == kids){
			gameCard.setDifficulty(Difficulty.KIDS);
			gameCard.playNewGame();
			cardLayout.show(cards, "GameScreen");
		} else if(e.getSource() == easy){
			gameCard.setDifficulty(Difficulty.EASY);
			gameCard.playNewGame();
			cardLayout.show(cards, "GameScreen");
		} else if(e.getSource() == medium){
			gameCard.setDifficulty(Difficulty.MEDIUM);
			gameCard.playNewGame();
			cardLayout.show(cards, "GameScreen");
		} else if (e.getSource() == hard){
			gameCard.setDifficulty(Difficulty.HARD);
			gameCard.playNewGame();
			cardLayout.show(cards, "GameScreen");
		}
		else if (e.getSource() == expert){
			gameCard.setDifficulty(Difficulty.EXPERT);
			gameCard.playNewGame();
			cardLayout.show(cards, "GameScreen");
		} else if(e.getSource() == quit){
			gameCard.stopGame();
			cardLayout.show(cards, "Menu");
		}
	}
	
	private static void hideDifficultyButtons(){
		kids.setVisible(false);
		easy.setVisible(false);
		medium.setVisible(false);
		hard.setVisible(false);
		expert.setVisible(false);
	}
	
	private static void showDifficultyButtons(){
		kids.setVisible(true);
		easy.setVisible(true);
		medium.setVisible(true);
		hard.setVisible(true);
		expert.setVisible(true);
	}
	
}
