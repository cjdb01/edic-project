import javax.swing.*;
import java.awt.*;
import java.util.Scanner;
import javax.swing.JOptionPane;


public class SudokuGrid extends JPanel {

	JTextField[][] fields;
	Sudoku board;
	String[][] userBoard;
	final int SIZE = 9;
	final int FIELD_WIDTH = 2;
	final Font LARGE_FONT = new Font("text", Font.PLAIN, 18); // font for numbers given in problem
	private static final long serialVersionUID = 1L;

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
						TextDocumentListener(fields[i][j], board, i, j));
				
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

	public void displayAssist() {
		Vector3D hint = board.getAssist();
		if (hint != null) {
			fields[hint.getX()][hint.getY()].setText(Integer.toString(hint
					.getZ()));
		} else {
			JOptionPane.showMessageDialog(this, "No more assists!");

		}
	}

	public void validate() {

		if (board.validate()) {
			JOptionPane.showMessageDialog(this, "Well done, keep going");
		} else {
			board.printSudoku();
			JOptionPane.showMessageDialog(this, "Somethings Wrong");
		}
	}

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

	public void getNewGame(Difficulty difficulty) {
		this.board = Generator.constructBoard(difficulty);
		Scanner st = new Scanner(board.getProblem());

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

	public void showResume() {
		for (int i = 0; i < SIZE; i++) {
			for (int j = 0; j < SIZE; j++) {
				fields[i][j].setText(userBoard[i][j]);
			}
		}
	}
}