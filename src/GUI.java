import java.awt.BorderLayout;
import javax.swing.*;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.util.ArrayList;

import javax.swing.JFrame;

/**
 * The GUI class for the Sudoku Game
 * @author aydinitil
 *
 */
public class GUI implements ActionListener {
	
	private static GameScreen gameCard;
	private static JPanel menuCard;
	private static JPanel difficultyCard;
	private static JPanel loadCard;
	private static CardLayout cardLayout;
	private static JPanel cards;
	private static JButton mainMenu;
	private static JButton play;
	private static JButton back;
	private static JButton kids;
	private static JButton easy;
	private static JButton hard;
	private static JButton medium;
	private static JButton expert;
	private static JButton load;
	private static JButton loadFile;
	private static String file;
	
	public static void main(String[] args) {
		constructAndShowGUI();
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
		} else if(e.getSource() == mainMenu){
			gameCard.stopGame();
			cardLayout.show(cards, "Menu");
		} else if(e.getSource() == back){
			cardLayout.show(cards, "Menu");
		} else if(e.getSource() == play){
			cardLayout.show(cards, "Diff");
		} else if(e.getSource() == load){
			cardLayout.show(cards, "Load");
		} else if(e.getSource() == loadFile){
			cardLayout.show(cards, "GameScreen");
			gameCard.loadGame(Interpreter.loadGame(file));
		}
	}
	
	private static JPanel constructScreen(){
		
		JPanel screen = new JPanel();
		screen.setLayout(new BorderLayout());
		JLabel title = new JLabel("Sudoku");
		title.setFont(new Font("text", Font.PLAIN, 25));
		title.setHorizontalAlignment(SwingConstants.CENTER);
		screen.add(title, BorderLayout.NORTH);
		
		return screen;
	}
	
	private static void constructAndShowGUI(){
		JFrame frame = new JFrame("Interactive Sudoku");
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		JPanel pane = (JPanel) frame.getContentPane();
		cards = new JPanel();
		cardLayout = new CardLayout();
		cards.setLayout(cardLayout);
		
		//initialize cards
		gameCard = new GameScreen();
		menuCard = constructScreen();
		difficultyCard = constructScreen();
		
		//initialize buttons
		mainMenu = new JButton("Menu");
		kids = new JButton("Kids");
		easy = new JButton("Easy");
		medium = new JButton("Medium");
		hard = new JButton("Hard");
		expert = new JButton("Expert");
		back = new JButton("Back");
		play = new JButton("Play");
		load = new JButton("Load");
		loadFile = new JButton("Load File");
		JButton quit = new JButton("Quit");
		
		
		JPanel diffPanel = new JPanel();
		JPanel menuPanel = new JPanel();
		diffPanel.setLayout(new GridLayout(6, 1));
		menuPanel.setLayout(new GridLayout(3, 1));
		
		
		diffPanel.add(kids);
		diffPanel.add(easy);
		diffPanel.add(medium);
		diffPanel.add(hard);
		diffPanel.add(expert);
		diffPanel.add(back);
		gameCard.addButton(mainMenu);
	
		quit.addActionListener(new
				ActionListener(){
			public void actionPerformed(ActionEvent e){
		
				System.exit(0);
			}
		});
		menuPanel.add(play);
		menuPanel.add(load);
		menuPanel.add(quit);
		
		
		
		difficultyCard.add(diffPanel, BorderLayout.CENTER);
		//add buttons to menu
		menuCard.add(menuPanel, BorderLayout.CENTER);
		
		//HERE IS EMPTY LOAD CARD
		loadCard = constructScreen();
	
        ArrayList<String> savedGames = Interpreter.retrieveSavedGames();
        ListModel model = new DefaultListModel();
        for (String str : savedGames)
        {
        	((DefaultListModel) model).addElement(str);
        }
        
        JList loadList = new JList(model);
        loadList.setSelectionMode(ListSelectionModel.SINGLE_INTERVAL_SELECTION);
        loadList.setLayoutOrientation(JList.VERTICAL_WRAP);
        loadList.setVisibleRowCount(-1);

        file = (String)loadList.getSelectedValue();
        
        loadCard.add(loadFile, BorderLayout.SOUTH);
        loadCard.add(loadList, BorderLayout.CENTER);
        
		//add cards
		cards.add("GameScreen", gameCard);
		cards.add("Menu", menuCard);
		cards.add("Diff", difficultyCard);
		cards.add("Load", loadCard);
		
		// create listener for buttons
		ActionListener listener = new GUI();
		
		//add listeners to static buttons
		loadFile.addActionListener(listener);
		mainMenu.addActionListener(listener);
		kids.addActionListener(listener);
		easy.addActionListener(listener);
		medium.addActionListener(listener);
		hard.addActionListener(listener);
		expert.addActionListener(listener);
		back.addActionListener(listener);
		play.addActionListener(listener);
		load.addActionListener(listener);
		pane.add(cards);
		cardLayout.show(cards, "Menu");
		frame.setSize(650, 600);
		frame.setLocationRelativeTo(null);
		frame.setResizable(false);
		frame.setVisible(true);
	}
	
}

