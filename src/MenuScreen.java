import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class MenuScreen extends JPanel{

	JButton options;
	JButton help;
	
	public MenuScreen(){
		options = new JButton("Options");
		help = new JButton("Help");
		this.add(options);
		this.add(help);
	}
}