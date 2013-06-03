import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;

import javax.swing.JFormattedTextField;

/**
 * An implementation of PropertyChangeListener used to update the supplied Sudoku
 * when a JFormattedTextField's "value" property changes
 * 
 * @author Nat Jensen
 *
 */
public class ValueListener implements PropertyChangeListener {
	private int row;
	private int column;
	private Sudoku sudoku;
	
	/**
	 * Each editable JFormattedTextField in SudokuGrid should be assigned a ValueListener.
	 *
	 * @param sudoku the Sudoku which should be updated.
	 * @param row the row index of the text field used to call sudoku.setNode()
	 * @param column the column index of the text field used to call sudoku.setNode()
	 */
	public ValueListener(Sudoku sudoku, int row, int column){
		this.row = row;		this.column = column;
		this.sudoku = sudoku;
	}
	
	/**
	 * Should be called when the SudokuGrid JFormattedTextField's "value"
	 * property changes. Updates the supplied Sudoku according to the value changes.
	 */
	@Override
	public void propertyChange(PropertyChangeEvent evt) {
		Number numVal = (Number) evt.getNewValue();
		if(numVal == null){
			sudoku.setNode(row, column, Sudoku.NOT_SET);
		} else {
			Integer intVal = numVal.intValue();
			if(intVal > 0 && intVal < 10){
				sudoku.setNode(row, column, intVal);
			} else {
				sudoku.setNode(row, column, Sudoku.NOT_SET);
				if(intVal == 0){
					((JFormattedTextField)evt.getSource()).setValue(null);
				}
			}
		}
	}

}