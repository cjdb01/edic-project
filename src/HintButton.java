import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JButton;

public class HintButton extends JButton implements ActionListener {
	
	private static final long serialVersionUID = 1L;
	SudokuGrid grid;
	/**
	 * Creates a new clear button.
	 *
	 * @param sp
	 *            the sudoku panel the button is going to interact with
	 */
	public HintButton(SudokuGrid grid) {
		super("Get Hint!");
		this.grid = grid;
		addActionListener(this);
	}
    
	/**
	 * Called when the button is clicked.
	 */
	@Override
	public void actionPerformed(ActionEvent e) {
		grid.displayHint();
	}
}