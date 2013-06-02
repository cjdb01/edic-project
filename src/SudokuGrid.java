import javax.swing.*;
import java.awt.*;
import java.util.Scanner;
import javax.swing.JOptionPane;
import javax.swing.event.DocumentEvent;
import javax.swing.event.DocumentListener;
import javax.swing.text.Document;

/**
 * A Textbox based Sudoku Grid class that stores a sudoku problem
 * @author aydinitil
 *
 */
public class SudokuGrid extends JPanel {

	JTextField[][] fields;
	Sudoku sudoku;
	String[][] userBoard;
	final int SIZE = 9;
	final int FIELD_WIDTH = 2;
	final Font LARGE_FONT = new Font("text", Font.PLAIN, 18); // font for numbers given in problem
	private static final long serialVersionUID = 1L;

	
	/**
	 * Constructs a SudokuGrid
	 */
	public SudokuGrid() {
		super();
		fields = new JTextField[SIZE][SIZE];
		this.setLayout(new GridLayout(SIZE, SIZE));
		userBoard = new String[SIZE][SIZE];
		// paint each subgrid with a different colour
		for (int i = 0; i < SIZE; i++) {
			for (int j = 0; j < SIZE; j++) {
				fields[i][j] = new JTextField(FIELD_WIDTH);
				fields[i][j].setBorder(BorderFactory
						.createLineBorder(Color.black));
				fields[i][j].setFont(LARGE_FONT);
				fields[i][j].setHorizontalAlignment(JTextField.CENTER);
				fields[i][j].getDocument().addDocumentListener(new 
						TextDocumentListener(fields[i][j]));
				
				if (j > 2 && j < 6) {
					if (i < 3 || (i > 5 && i < 9)) {
						fields[i][j].setBackground(new Color(200, 200, 200));
					}
				}
				if (i > 2 && i < 6) {
					if (j < 3 || (j > 5 && j < 9)) {
						fields[i][j].setBackground(new Color(200, 200, 200));
					}
				}
				this.add(fields[i][j]);	
			}
		}
		setBorder(BorderFactory.createLineBorder(Color.black));
	}

	/**
	 * Displays an assist on the grid
	 */
	public void displayAssist() {
		Vector3D hint = sudoku.getAssist();
		if (hint != null) {
			fields[hint.getX()][hint.getY()].setText(Integer.toString(hint
					.getZ()));
		} else {
			JOptionPane.showMessageDialog(this, "No more assists!");

		}
	}


	public String getStatus() {

		if (sudoku.validate()) {
			if(sudoku.isComplete())
				return "You WIN!";
			else 
			return "Everything is OK, keep going!";
		} else {
			return "Something is wrong!";
		}
	}

	/**
	 * Clears all editable fields of the grid
	 */
	public void clear() {
		for (int i = 0; i < SIZE; i++) {
			for (int j = 0; j < SIZE; j++) {
				if (fields[i][j].isEditable()) {
					fields[i][j].setText("");
					fields[i][j].setFont(LARGE_FONT);
				}
			}
		}

	}

	/**
	 * Updates the sudoku to match the grid
	 */
	public void updateSudoku(){
		for(int i = 0; i < SIZE; i++){
			for(int j = 0; j < SIZE; j++){
				String text = fields[i][j].getText();
				if(isValid(text)){
					if(text.length() == 1 && !text.equals(" ")){
						sudoku.setNode(i, j, Integer.parseInt(text));
					} else if(text.equals(" ") || text.length()==0){
						sudoku.setNode(i, j, 0);
					}
				} else {
					JOptionPane.showMessageDialog(this, "Please enter numbers!");
					return;
				}
			}
		}
	}
	
	/**
	 * Begins a new sudoku problem 
	 * @param difficulty The difficulty of the new problem
	 */
	public void getNewGame(Difficulty difficulty) {
		this.sudoku = Generator.constructBoard(difficulty);
		Scanner st = new Scanner(sudoku.getProblem());

		for (int i = 0; i < SIZE; i++) {
			for (int j = 0; j < SIZE; j++) {
				int number = st.nextInt();
				if (number != 0) {
					fields[i][j].setText(Integer.toString(number));
					fields[i][j].setEditable(false);
					fields[i][j].setForeground(Color.BLACK);
				} else {
					fields[i][j].setText("");
					fields[i][j].setEditable(true);
					fields[i][j].setForeground(new Color(0 , 8, 235));
				}
				fields[i][j].setFont(LARGE_FONT);
			}
		}
	}

	/**
	 * Sets the grid into paused mode
	 */
	public void showPause() {
		for (int i = 0; i < SIZE; i++) {
			for (int j = 0; j < SIZE; j++) {
				userBoard[i][j] = fields[i][j].getText();
				fields[i][j].setText(" ");
			}
		}
		fields[4][1].setText("P");
		fields[4][2].setText("A");
		fields[4][3].setText("U");
		fields[4][4].setText("S");
		fields[4][5].setText("E");
		fields[4][6].setText("D");
		fields[4][7].setText("!");
	}

	/**
	 * Sets the game into resume
	 */
	public void showResume() {
		for (int i = 0; i < SIZE; i++) {
			for (int j = 0; j < SIZE; j++) {
				fields[i][j].setText(userBoard[i][j]);
			}
		}
	}
	
	/**
	 * Returns the amount of assists remaining
	 * @return amount of assists remaining
	 */
	public int getAssists(){
		return sudoku.getRemainingAssists();
	}
	
	/*
	 * Returns true if the string is a valid input for the grid
	 */
	private boolean isValid(String string){
		for(int i = 0; i < string.length(); i++){
			if(string.charAt(i) < '0' || string.charAt(i) > '9' || string.charAt(i) != ' '){
				return true;
			}
		}
		return true;
	}
}