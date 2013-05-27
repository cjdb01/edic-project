public class Interpreter
{
    public static boolean saveGame(final Sudoku game, final double time, final String filename)
    {
        StringBuffer buffer = new StringBuffer();
        
        buffer.append("time = " + Double.toString(time) + "\n");
        buffer.append("assist = " + Integer.toString(game.getAssist()) + "\n");
        buffer.append("board = { " + game.getBoard(Sudoku.Board.Problem) + " }\n");
        buffer.append("solution = { " + game.getBoard(true) + " }");
        
        PrintWriter writer = new PrintWriter(filename, "UTF-8");
        writer.print(buffer.toString());
        writer.close();
    }
    
    public static Sudoku loadGame(String filename)
    {
        
    }
    
    public static boolean saveProfile(Profile profile)
    {
        
    }
    
    public static Profile loadProfile(String filename)
    {
        
    }
}