import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;


public class ValueListener implements PropertyChangeListener {
	private int row;
	private int column;
	private Sudoku sudoku;
	public ValueListener(Sudoku sudoku, int row, int column){
		this.row = row;		this.column = column;
		this.sudoku = sudoku;
	}
	
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
			}
		}
	}

}