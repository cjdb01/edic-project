public class Interpreter
{
    public static boolean saveGame(final Sudoku game, final double time, final String filename)
    {
        StringBuffer buffer = new StringBuffer();
        
        buffer.append("time = " + Double.toString(time) + "\n");
        buffer.append("assist = " + Integer.toString(game.getAssist()) + "\n");
        buffer.append("board = { " + game.getProblem() + " }\n");
        buffer.append("solution = { " + game.getSolution() + " }\n");
        buffer.append("editable = { " + game.getEditable() + " }");
        PrintWriter writer = new PrintWriter(filename, "UTF-8");
        writer.print(buffer.toString());
        writer.close();
    }
    
    public static Sudoku loadGame(String filename)
    {
        int assists;
        int[][] board;
        int[][] solution;
        boolean[][] editable;
        double time;
        
        Scanner scanner = new Scanner(new FileReader(filename));
        StringTokenizer token;
        
        try
        {
            token = new StringTokenizer(scanner.nextLine(), " ");
            token.nextToken();
            token.nextToken();
            token.nextToken();
            time = Double.parseDouble(token.nextToken());
            
            token = new StringTokenizer(scanner.nextLine(), " ");
            token.nextToken();
            token.nextToken();
            token.nextToken();
            assists = Integer.parseInt(token.nextToken());
            
            token = new StringTokenizer(scanner.nextLine(), " ");
            board = textToSudoku(token);
            
            token = new StringTokenizer(scanner.nextLine(), " ");
            solution = textToSudoku(token);
            
            token = new StringTokenizer(scanner.nextLine(), " ");
            editable = textToSudoku(token);
        }
        catch (Exception ex)
        {
        }
        
        return Sudoku(board, solution, editable, assist);
    }
    
    private <T> T[][] textToSudoku(StringTokenizer token)
    {
        T[][] board = new T[9][9];
        
        for (int i = 0; i != 3; ++i)
        {
            token.nextToken();
        }
        
        for (int i = 0; i != 9; ++i)
        {
            for (int j = 0; j != 9; ++j)
            {
                board[i][j] = Integer.parseInt(token.nextToken());
            }
        }
        
        return board;
    }
    
    public static boolean saveProfile(Profile profile)
    {
        
    }
    
    public static Profile loadProfile(String filename)
    {
        
    }
}