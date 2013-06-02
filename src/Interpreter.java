import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Scanner;
import java.util.StringTokenizer;

/**
 * Saves/Loads games
 * @author Christopher Di Bella
 *
 */
public class Interpreter
{
    private static final String saveMaster = "save_master";
    
    /**
     * Saves the current Sudoku to file
     * @param game The current Sudoku board
     * @param time The time taken
     * @param filename The file to be saved to
     */
    public static void saveGame(final Sudoku game, final int time, final String filename)
    {
        StringBuffer buffer = new StringBuffer();
        
        buffer.append("time = " + Integer.toString(time) + "\n");
        buffer.append("assist = " + Integer.toString(game.getRemainingAssists()) + "\n");
        buffer.append("board = { " + game.getProblem() + " }\n");
        buffer.append("solution = { " + game.getSolution() + " }\n");
        buffer.append("editable = { " + game.getEditable() + "}");
        
        try
        {
            PrintWriter writer = new PrintWriter(filename, "UTF-8");
            writer.print(buffer.toString());
            writer.close();
            
            ArrayList<String> saveGames = retrieveSavedGames();
            saveGames.add(filename);
            writer = new PrintWriter(saveMaster, "UTF-8");
            
            for (String str : saveGames)
            {
                writer.println(str);
            }
            
            writer.close();
        }
        catch (Exception ex)
        {
            // Don't do anything here... there shouldn't be an exception
        }
    }
    
    /**
     * Retrieves all save game filenames
     * @return An array list of all save game filenames (strings)
     */
    public static ArrayList<String> retrieveSavedGames()
    {
        ArrayList<String> saveGames = new ArrayList<String>();
        try
        {
            Scanner scanner = new Scanner(new FileReader(saveMaster));
            while (scanner.hasNext())
            {
                saveGames.add(scanner.nextLine());
            }
            scanner.close();
        }
        catch (FileNotFoundException ex)
        {
            // Just return an empty list...
        }
        
        return saveGames;
    }
    
    /**
     * Loads a game from file
     * @param filename The filename of the game
     * @return The game associated with the file
     */
    public static Sudoku loadGame(final String filename)
    {
        int assists = 0;
        int time = 0;
        int[][] board = null;
        int[][] solution = null;
        boolean[][] editable = null;
        
        Scanner scanner;
        StringTokenizer token;
        
        try
        {
        	scanner = new Scanner(new FileReader(filename));
        	
            while (scanner.hasNext())
            {
                token = new StringTokenizer(scanner.nextLine(), " ");
                
                if (token.nextToken().equals("time"))
                {
                    token.nextToken();
                    time = Integer.parseInt(token.nextToken());
                }
                else if (token.nextToken().equals("assist"))
                {
                    token.nextToken();
                    assists = Integer.parseInt(token.nextToken());
                }
                else if (token.nextToken().equals("board"))
                {
                    board = textToSudoku(token);
                }
                else if (token.nextToken().equals("solution"))
                {
                    solution = textToSudoku(token);
                }
                else if (token.nextToken().equals("editable"))
                {
                    editable = textToEditable(token);
                }
            }
            
            scanner.close();
        }
        catch (Exception ex)
        {
        	// Shouldn't be an exception here
        }
        
        return new Sudoku(board, solution, editable, assists);
    }
    
    /**
     * De-serialises a text file
     * @param token The line that is to be deserialised
     * @return The Sudoku
     */
    private static int[][] textToSudoku(StringTokenizer token)
    {
        int[][] board = new int[9][9];
        
        token.nextToken();
        token.nextToken();
        
        for (int i = 0; i != 9; ++i)
        {
            for (int j = 0; j != 9; ++j)
            {
                board[i][j] = Integer.parseInt(token.nextToken());
            }
        }
        
        return board;
    }
    
    /**
     * De-serialises editable fields from text
     * @param token The line that is to be deserialised
     * @return Sudoku editable fields
     */
    private static boolean[][] textToEditable(StringTokenizer token)
    {
        boolean[][] board = new boolean[9][9];
        
        token.nextToken();
        token.nextToken();
        
        for (int i = 0; i != 9; ++i)
        {
            for (int j = 0; j != 9; ++j)
            {
                board[i][j] = Boolean.parseBoolean(token.nextToken());
            }
        }
        
        return board;
    }
}