import java.awt.*;
import java.swing.*;

public class GUI implements ActionListener
{
    private enum Button
    {
        // Left pane buttons
        Play, Profiles, Options, Help, Credits, Exit,
        // Right pane buttons
        NewGame, LoadGame,
        Kids, Easy, Medium, Hard, Expert,
        ReturnToMenu
    };
    private enum Panel { Left, Right };
    private enum Card { Menu, Game };
    
    public static void main(String args[])
    {
        // Variables and constants
        const int buttonWidth = 100;
        const int buttonHeight = 100;
        JFrame frame; // Main window
        JPanel pane, card, menu;
        JPanel[] panel;
        CardLayout layout;
        JButton[] buttons;
        GameScreen game;
        Difficulty difficulty;
        // Graphical user interface
        ActionListner listner = new GUI();
        
        // Set up the main window
        frame = new JFrame("Interactive Sudoku");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        pane = (JPanel)(frame.getContentPane());
        
        // Set up a temporary screen
        card = new JPanel();
        layout = new CardLayout();
        card.set(layout);
        
        // Set up the internal screens
        panel = new JPanel[2];
        panel[Panel.Left] = panelSetup(true, BoxLayout.Y_AXIS);
        panel[Panel.Right] = panelSetup(true, BoxLayout.Y_AXIS);
        menu = panelSetup(true, 0);
        
        // Setup all buttons
        buttons = new JButton[20];
       
        buttons[Button.Play] = buttonSetup("Play Sudoku", buttonWidth, buttonHeight);
        buttons[Button.Profiles] = buttonSetup("Profiles", buttonWidth, buttonHeight);
        buttons[Button.Options] = buttonSetup("Options", buttonWidth, buttonHeight);
        buttons[Button.Help] = buttonSetup("Help", buttonWidth, buttonHeight);
        buttons[Button.Credits] = buttonSetup("Credits", buttonWidth, buttonHeight);
        buttons[Button.Exit] = buttonSetup("Exit", buttonWidth, buttonHeight);
        buttons[Button.NewGame] = buttonSetup("New Game", buttonWidth, buttonHeight);
        buttons[Button.LoadGame] = buttonSetup("Load Game", buttonWidth, buttonHeight);
        buttons[Button.Kids] = buttonSetup("Kids", buttonWidth, buttonHeight);
        buttons[Button.Easy] = buttonSetup("Easy", buttonWidth, buttonHeight);
        buttons[Button.Medium] = buttonSetup("Medium", buttonWidth, buttonHeight);
        buttons[Button.Hard] = buttonSetup("Hard", buttonWidth, buttonHeight);
        buttons[Button.Expert] = buttonSetup("Expert", buttonWidth, buttonHeight);
        buttons[Button.ReturnToMenu] = buttonSetup("Menu", buttonWidth, buttonHeight);
        
        // Give buttons context
        panel[Panel.Left].add(buttons[Button.Play]);
        panel[Panel.Left].add(buttons[Button.Profiles]);
        panel[Panel.Left].add(buttons[Button.Options]);
        panel[Panel.Left].add(buttons[Button.Help]);
        panel[Panel.Left].add(buttons[Button.Credits]);
        panel[Panel.Left].add(buttons[Button.Exit]);
        
        // Decide whether to show right pane or not
        for (Button button : Button.values())
        {
            buttons[button].addActionListner(
                new ActionListner()
                {
                    public void actionPerformed(ActionEvent e)
                    {
                        // Show new/load game buttons
                        toggleRightPane(button);
                    }
                });
                
            toggleRightPane(button);
        }
        
        
    }
    
    private JPanel panelSetup(boolean box, int axis)
    {
        JPanel panel = new JPanel();
        panel.setLayout(box == true ? 
                        new BoxLayout(panel, axis) :
                        new BorderLayout());
        return panel;
    }
    
    private JButton buttonSetup(String text, int width, int height)
    {
        JButton button = new JButton(text);
        button.setPreferredSize(new Dimension(width, height));
        
        return button;
    }
}