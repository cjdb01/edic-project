import javax.swing.*;

import java.awt.event.*;

public class TimerField extends JPanel{
	
	private static final long serialVersionUID = 1L;
	private final JTextField textField;
	private final int FIELD_WIDTH = 4;
	private final int DELAY = 1000;
	ActionListener listener;
	private Timer t;
	private int seconds;
	private int minutes;
	
	public TimerField(){
		super();
		seconds = 00;
		minutes = 00;
		textField = new JTextField(FIELD_WIDTH);
		textField.setEditable(false);
		this.add(textField);
		textField.setText(Integer.toString(minutes) + "0:0" + Integer.toString(seconds));
		textField.setHorizontalAlignment(JTextField.CENTER);
		listener = new
		  ActionListener() {
			public void actionPerformed(ActionEvent event){
				seconds++;
				if(seconds == 60){
					minutes++;
					seconds = 0;
				}
				if(seconds < 10 && minutes < 10)
					textField.setText("0" + Integer.toString(minutes) + ":0" + Integer.toString(seconds));
				else if(seconds < 10 && minutes > 9)
					textField.setText(Integer.toString(minutes) + ":0" + Integer.toString(seconds));
				else if(seconds > 9 && minutes < 10)
					textField.setText("0" + Integer.toString(minutes) + ":" + Integer.toString(seconds));
				else 
					textField.setText(Integer.toString(minutes) + ":" + Integer.toString(seconds));
			}
		};
		t = new Timer(DELAY, listener);
	}
	public void start(){
		t.start();
	}
	public void pause(){
		t.stop();
	}
	public void restart(){
		seconds = -1;
		minutes = 0;
	}
	/**
	 * 
	 * @return Amount of time in seconds
	 */
	public int getTime(){
		return minutes * 60 + seconds;
	}
}