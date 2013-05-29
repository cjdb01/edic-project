import javax.swing.*;
import java.awt.*;
import javax.swing.event.DocumentEvent;
import javax.swing.event.DocumentListener;
import javax.swing.text.Document;

public class TextDocumentListener implements DocumentListener{
	
	JTextField textField;
	Sudoku sudoku;
	int i;
	int j;
	
	TextDocumentListener(JTextField textField, Sudoku sudoku, int i, int j){
		this.textField = textField;
		this.sudoku = sudoku;
		this.i = i;
		this.j = j;
	}

	@Override
	public void changedUpdate(DocumentEvent e) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void insertUpdate(DocumentEvent e) {
		Document d = (Document)e.getDocument();
		if(d.getLength() > 1){
			Font font = new Font("text", Font.PLAIN, 15);
			textField.setFont(font);
		}
		}
		
	

	@Override
	public void removeUpdate(DocumentEvent e) {
		Document d = (Document)e.getDocument();
		if(d.getLength() < 2){
			Font font = new Font("text", Font.PLAIN, 25);
			textField.setFont(font);
		}
		
	}
		
	}
	