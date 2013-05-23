public class Interpreter
{
    public static boolean saveGame(final Sudoku game, final double time)
    {
        StringBuffer buffer = new StringBuffer();
        
        buffer.append("time = " + Double.toString(time));
        buffer.appendln();
        buffer.append("assist = " + Integer.toString(game.getAssist());
        buffer.appendln();
        buffer.append("board = { " + game.getBoard(Sudoku.Board.Problem) + " }");
        buffer.appendln();
        buffer.append("solution = { " + game.getBoard(true) + " }");
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