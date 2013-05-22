import javax.swing.*;
import java.awt.*;
import java.util.Scanner;

public class SudokuGrid extends JPanel{

	JTextField[][] fields;
	Board board;
	final int GRID_SIZE = 9;
	final int CONTAIN_SIZE = 3;
	
	
	private static final long serialVersionUID = 1L;
	
	public SudokuGrid(Board board){
		
		super();
		fields = new JTextField[GRID_SIZE][GRID_SIZE];
		this.board = board;
		this.setLayout(new GridLayout(GRID_SIZE, GRID_SIZE));
		Font text = new Font("text", Font.PLAIN, 25);
		Scanner sc = new Scanner(board.getBoard());
		
		//paint each subgrid with a different colour
		
		for(int i = 0; i < GRID_SIZE; i++){
			for(int j = 0; j < GRID_SIZE; j++){
				fields[i][j] = new JTextField(2);
				fields[i][j].setBorder(BorderFactory.createLineBorder(Color.black));
				fields[i][j].setFont(text);
				int number = sc.nextInt();
				if(number != 0){
					fields[i][j].setText(Integer.toString(number));
					fields[i][j].setEditable(false);
				} else {
					fields[i][j].setEditable(true);
				}
				this.add(fields[i][j]);
			}
		}
	}
}