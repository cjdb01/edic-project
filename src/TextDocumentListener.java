import javax.swing.*;
import java.awt.*;

import javax.swing.event.DocumentEvent;
import javax.swing.event.DocumentListener;
import javax.swing.text.Document;


/**
 * A custom DocumentListener that is able to modify font size and color as input is entered
 * @author aydinitil
 *
 */
public class TextDocumentListener implements DocumentListener{
	
	private JTextField textField;
	
	public TextDocumentListener(JTextField textField){
		this.textField = textField;
	}
	
	/**
	 * Does nothing
	 */
	@Override
	public void changedUpdate(DocumentEvent e) {
		// does nothing
	}
	
	/**
	 * Resizes the text, and changes font color when more text is entered
	 */
	@Override
	public void insertUpdate(DocumentEvent e) {
		Document d = (Document)e.getDocument();
		if(d.getLength() == 2){
			Font font = new Font("text", Font.PLAIN, 15);
			textField.setFont(font);
			textField.setForeground(Color.RED);
		} else if (d.getLength() > 2){
			Font font = new Font("text", Font.PLAIN, 13);
			textField.setFont(font);
			textField.setForeground(Color.RED);
		}
	}

	/**
	 * Resizes text and changes font color appropriately when text is removed
	 */
	@Override
	public void removeUpdate(DocumentEvent e) {
		Document d = (Document)e.getDocument();
		if(d.getLength() < 2){
			Font font = new Font("text", Font.PLAIN, 18);
			textField.setFont(font);
			if(textField.isEditable()){
			textField.setForeground(new Color(0 , 8, 235));
			}
		}
		
	}
}
	