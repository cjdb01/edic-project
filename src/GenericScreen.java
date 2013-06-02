import javax.swing.*;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class GenericScreen extends JPanel{

	private JPanel buttonPanel;
	private JLabel title;
	
	public GenericScreen(){
		this.setLayout(new BorderLayout());
		title = new JLabel("Sudoku");
		title.setFont(new Font("text", Font.PLAIN, 25));
		title.setHorizontalAlignment(SwingConstants.CENTER);
		this.add(title, BorderLayout.NORTH);
		this.add(buttonPanel, BorderLayout.CENTER);
	}
	
	public void addButton(JButton button){
		buttonPanel.add(button);
	}
}