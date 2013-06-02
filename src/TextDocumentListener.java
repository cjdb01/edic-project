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
	
	JTextField textField;
	
	TextDocumentListener(JTextField textField){
		this.textField = textField;
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
			textField.setForeground(Color.RED);
			
		}
	}

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
	