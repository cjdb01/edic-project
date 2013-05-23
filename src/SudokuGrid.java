import javax.swing.*;
import java.awt.*;
import java.util.Scanner;

public class SudokuGrid extends JPanel{
    
	JTextField[][] fields;
	Board board;
	final int SIZE = 9;
	private static final long serialVersionUID = 1L;
	
	public SudokuGrid(Board board) {
		super();
		fields = new JTextField[SIZE][SIZE];
		this.board = board;
		this.setLayout(new GridLayout(SIZE, SIZE));
		Font text = new Font("text", Font.PLAIN, 25);
		Scanner st = new Scanner(board.getBoard());
        
		// paint each subgrid with a different colour
		for (int i = 0; i < SIZE; i++) {
			for (int j = 0; j < SIZE; j++) {
				fields[i][j] = new JTextField(2);
				fields[i][j].setBorder(BorderFactory
                                       .createLineBorder(Color.black));
				fields[i][j].setFont(text);
				fields[i][j].setHorizontalAlignment(JTextField.CENTER);
				
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
				int number = st.nextInt();
				if (number != 0) {
					fields[i][j].setText(Integer.toString(number));
					fields[i][j].setEditable(false);
				} else {
					fields[i][j].setEditable(true);
				}
				this.add(fields[i][j]);
				
			}
		}
		setBorder(BorderFactory.createLineBorder(Color.black));
	}
	
	public void displayHint(){
		Vector3D hint = board.getHint();
		fields[hint.getX()][hint.getY()].setText(Integer.toString(hint.getZ()));
	}
}