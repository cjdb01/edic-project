import java.util.Random;

/**
 * 
 * @author Christopher Di Bella
 * @date 2013, June 2
 * The Sudoku game
 */
public class Sudoku
{
    private final int[][] m_solved;
    private int[][] m_toSolve;
    private final boolean[][] m_editable;

    private State m_state;
    int m_assist;
    
    public final static int NOT_SET = 0;
    public static enum Completeness { Complete, Incomplete, Invalid };
    
    /**
     * Main Sudoku constructor
     * @param toSolve The partially solved board that the player must solve
     * @param solved The completed Sudoku that the board is compared against
     * @param assist The number of assists (hints) the player is allowed
     */
    public Sudoku(int[][] toSolve, final int[][] solved, int assist)
    {
    	m_solved = new int[9][9];
    	m_toSolve = new int[9][9];
    	m_editable = new boolean[9][9];
    	m_assist = assist;
        m_state = new State(null, null, m_assist, null);
        
        for (int i = 0; i < 9; ++i)
        {
            System.arraycopy(toSolve[i], 0, m_toSolve[i], 0, 9);
            System.arraycopy(solved[i], 0, m_solved[i], 0, 9);
        }

        for (int i = 0; i != 9; ++i)
        {
            for (int j = 0; j != 9; ++j)
            {
                m_editable[i][j] = (m_toSolve[i][j] == 0);
            }
        }
        
        m_assist = assist;
    }
    
    /**
     * The load game constructor
     * @param board
     * @param toSolve The partially solved board that the player must solve
     * @param solved The completed Sudoku that the board is compared against
     * @param editable The positions that the user can edit
     * @param assist The number of assists (hints) the player is allowed
     */
    public Sudoku(int[][] board, final int[][] solution, final boolean[][] editable, int assist)
    {
        m_toSolve = board;
        m_solved = solution;
        m_editable = editable;
        m_assist = assist;
        m_state = new State(null, null, m_assist, null);
    }

    /**
     * Sets a node to a particular value
     * @param x The x-coordinate
     * @param y The y-coordinate
     * @param val The value at (x,y)
     * @return true if the value is in [1, 9]; false otherwise
     */
	public boolean setNode(int x, int y, int val)
    {
        if (m_editable[x][y] == true && val >= 0 && val < 10)
        {
            m_state = new State(m_state, null, m_assist, new Vector3D(x, y, val));
            m_toSolve[x][y] = val;
        }

        return (m_editable[x][y] && val >= 0 && val < 10);
    }

	/**
	 * @return m_toSolve in serialised String format
	 */
    public String getProblem()
    {
        return getBoard(false);
    }
    
    /**
     * @return m_solved in serialised String format
     */
    public String getSolution()
    {
        return getBoard(true);
    }

    /**
     * Gets the number of remaining assists
     * @return m_assist
     */
    public int getRemainingAssists()
    {
        return m_assist;
    }
    
    /**
     * Sets assists to a particular value (assist override)
     * @param assists The particular value
     */
    public void setAssists(int assists)
    {
        m_assist = assists;
    }
    
    /**
     * Increments assists
     * @return Prefix increment operator; new number of assists
     */
    public int addAssist()
    {
        return ++m_assist;
    }
    
    /**
     * Decrements assists
     * @return Prefix decrement operator; new number of assists
     */
    public int removeLife()
    {
        return --m_assist;
    }
    
    /**
     * Validates the board
     * @return Completeness.Complete if the m_toSolve completely matches m_solved
     *         Completeness.Incomplete if m_toSolve partially matches m_solved (excludes NOT_SET values)
     *         Completeness.Invalid if there is a value of m_toSolve that does not match m_solved (NOT_SET excluded)
     */
    public Completeness validate()
    {
        Completeness completeness = Completeness.Complete;
        for (int i = 0; i != 9; ++i)
        {
            for (int j = 0; j != 9; ++j)
            {
                if (m_toSolve[i][j] == NOT_SET)
                {
                	completeness = Completeness.Incomplete;
                }
                else if(m_toSolve[i][j] != m_solved[i][j] && m_toSolve[i][j] != NOT_SET)
                {
                    return Completeness.Invalid;
                }
            }
        }

        return completeness;
    }
    
    /**
     * Gives the player one correct answer
     * @return An (x,y) coordinate and the corresponding, correct value
     */
    public Vector3D getAssist()
    {
        int x;
        int y;
        Random generator = new Random();
        
        if (m_assist == 0)
        {
            return null;
        }
        
        do
        {
            x = generator.nextInt(9);
            y = generator.nextInt(9);
        }
        while (m_toSolve[x][y] != NOT_SET);
        
        --m_assist;
        m_state.loseAssist();
        
        m_toSolve[x][y] = m_solved[x][y];
        return new Vector3D(x, y, m_solved[x][y]);
    }
    
    /**
     * Reverts back to a previous game state
     * @return true if there is another undo; false otherwise
     */
    public boolean undo()
    {
        if (m_state.getPast() != null)
        {
            State temp = m_state;
            Vector3D v;
            
            m_state = m_state.getPast();
            
            v = m_state.getValue();
            m_assist = m_state.getAssist();
            m_toSolve[v.getX()][v.getY()] = v.getZ();
            m_state.setFuture(temp);
        }
        
        return (m_state.getPast() != null);
    }
    
    /**
     * Proceeds to a game state that existed after an undo call
     * @return true if there is another redo; false otherwise
     */
    public boolean redo()
    {
        if (m_state.getFuture() != null)
        {
            Vector3D v;
            
            m_state = m_state.getFuture();
            
            v = m_state.getValue();
            m_assist = m_state.getAssist();
            m_toSolve[v.getX()][v.getY()] = v.getZ();
        }
        
        return (m_state.getFuture() != null);
    }    
    
    /**
     * Returns a copy of the board
     * @param solved Decides if the board should be m_solved (true) or m_toSolve (false)
     * @return A serialised copy of the board
     */
    private String getBoard(boolean solved)
    {
        int[][] board = solved == true ? m_solved : m_toSolve;
        String str = new String();

        for (int i = 0; i != 9; ++i)
        {
            for (int j = 0; j != 9; ++j)
            {
                str = str.concat(Integer.toString(board[i][j]) + " ");
            }
        }
        
        return str;
    }
    
    public String getEditable()
    {
    	String editable = new String();
    	
    	for (int i = 0; i != 9; ++i)
    	{
    		for (int j = 0; j != 9; ++j)
    		{
    			editable += Boolean.toString(m_editable[i][j]);
    			editable += " ";
    		}
    	}
    	
    	return editable;
    }
    
    /*public boolean isComplete()
    {
        for (int i = 0; i != 9; ++i)
        {
            for (int j = 0; j != 9; ++j)
            {
                if (m_toSolve[i][j] != m_solved[i][j])
                {
                   return false;
                }
            }
        }
          
        return true;
    }*/
}