import javax.swing.*;
import java.awt.*;
import java.text.DecimalFormat;
import java.text.DecimalFormatSymbols;
import java.text.NumberFormat;
import java.util.Scanner;
import javax.swing.JOptionPane;
import javax.swing.text.DefaultFormatterFactory;
import javax.swing.text.MaskFormatter;
import javax.swing.text.NumberFormatter;

/**
 * A Textbox based Sudoku Grid class that stores a sudoku problem
 * @author aydinitil
 *
 */
public class SudokuGrid extends JPanel {

	JFormattedTextField[][] fields;
	Sudoku board;
	String[][] userBoard;
	final int SIZE = 9;
	final int FIELD_WIDTH = 2;
	final Font LARGE_FONT = new Font("text", Font.PLAIN, 18); // font for numbers given in problem
	private static final long serialVersionUID = 1L;
	private NumberFormat editFormat;
	private NumberFormat displayFormat;

	
	/**
	 * Constructs a SudokuGrid
	 */
	public SudokuGrid() {
		super();
		DecimalFormatSymbols customSymbol = new DecimalFormatSymbols();
		customSymbol.setGroupingSeparator(' ');
		editFormat = new DecimalFormat("#####");
		editFormat.setParseIntegerOnly(true);
		editFormat.setMaximumIntegerDigits(5);
		//spaces displayed between numbers
		displayFormat = new DecimalFormat("#,#,#,#,#",customSymbol);
		displayFormat.setParseIntegerOnly(true);
		displayFormat.setMaximumIntegerDigits(5);
		fields = new JFormattedTextField[SIZE][SIZE];
		this.setLayout(new GridLayout(SIZE, SIZE));
		userBoard = new String[SIZE][SIZE];
		// paint each subgrid with a different colour
		for (int i = 0; i < SIZE; i++) {
			for (int j = 0; j < SIZE; j++) {
				fields[i][j] = new JFormattedTextField(new DefaultFormatterFactory(new NumberFormatter(displayFormat), new NumberFormatter(displayFormat), new NumberFormatter(editFormat)));
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

	private MaskFormatter createFormatter(String s){
		MaskFormatter formatter = null;
		try{
			formatter = new MaskFormatter(s);
		} catch (java.text.ParseException exc){
			System.err.println("Invalid formatter: " + exc.getMessage());
			System.exit(-1);
		}
		return formatter;
	}
	
	/**
	 * Displays an assist on the grid
	 */
	public void displayAssist() {
		Vector3D hint = board.getAssist();
		if (hint != null) {
			fields[hint.getX()][hint.getY()].setValue(hint
					.getZ());
		} else {
			JOptionPane.showMessageDialog(this, "No more assists!");

		}
	}

	public String getStatus() {

		if (sudoku.validate() == Sudoku.Completeness.Invalid) {
			return "Something is wrong!";
		} else if(sudoku.validate() == Sudoku.Completeness.Incomplete){
			return "Everything is OK, keep going!";
		} else {
			return "YOU WIN!";	
		}
	}

	/**
	 * Clears all editable fields of the grid
	 */
	public void clear() {
		for (int i = 0; i < SIZE; i++) {
			for (int j = 0; j < SIZE; j++) {
				if (fields[i][j].isEditable()) {
					fields[i][j].setValue(null);
					fields[i][j].setFont(LARGE_FONT);
				}
			}
		}

	}
	
	/**
	 * Begins a new sudoku problem 
	 * @param difficulty The difficulty of the new problem
	 */
	public void getNewGame(Difficulty difficulty) {
		this.board = Generator.constructBoard(difficulty);
		Scanner st = new Scanner(board.getProblem());

		for (int i = 0; i < SIZE; i++) {
			for (int j = 0; j < SIZE; j++) {
				int number = st.nextInt();
				if (number != 0) {
					fields[i][j].setValue(number);
					fields[i][j].setEditable(false);
					fields[i][j].setForeground(Color.BLACK);
				} else {
					fields[i][j].addPropertyChangeListener("value",new ValueListener(board, i, j));
					fields[i][j].setValue(null);
					fields[i][j].setEditable(true);
					fields[i][j].setForeground(new Color(0 , 8, 235));
				}
				fields[i][j].setFont(LARGE_FONT);
			}
		}
		st.close();
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
		for (int i = 1; i <= 7; i++){
			fields[4][i].setForeground(Color.BLACK);
		}
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
	
	public Sudoku getGame(){
		return sudoku;
	}
}