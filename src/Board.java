import java.util.Random;

public class Sudoku
{
    private final int[][] m_solved;
    private int[][] m_toSolve;
    private final boolean[][] m_editable;

    private State m_state;
    
    int m_lives;
    int m_assist;
    
    public Sudoku(int[][] toSolve, final int[][] solved, int lives)
    {
    	m_solved = new int[9][9];
    	m_toSolve = new int[9][9];
    	m_editable = new boolean[9][9];
    	m_assist = 5;
        //m_state = new 
        
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
        
        m_lives = lives;
    }

	public boolean setNode(int x, int y, int val)
    {
        if (m_editable[x][y] == true && val >= 0 && val < 10)
        {
            m_state = new State(m_state, null, m_lives, m_assist, new Vector3D(x, y, val));
            m_toSolve[x][y] = val;
        }

        return (m_editable[x][y] && val >= 0 && val < 10);
    }

    public String getProblem()
    {
        return getBoard(false);
    }
    
    public String getSolution()
    {
        return getBoard(true);
    }

    int getLives()
    {
        return m_lives;
    }
    
    void setLives(int lives)
    {
        m_lives = lives;
    }
    
    int addLife()
    {
        return ++m_lives;
    }
    
    int removeLife()
    {
        return --m_lives;
    }
    
    Vector3D getHint()
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
        while (m_toSolve[x][y] != 0);
        
        --m_assist;
        
        m_toSolve[x][y] = m_solved[x][y];
        return new Vector3D(x, y, m_solved[x][y]);
    }
    
    void undo()
    {
        State temp = m_state;
        Vector3D v;
        
        m_state = m_state.getPast();
        
        v = m_state.getValue();
        m_lives = m_state.getLives();
        m_assist = m_state.getHints();
        m_toSolve[v.getX()][v.getY()] = v.getZ();
        m_state.setFuture(temp);
    }
    
    void redo()
    {
        Vector3D v;
        
        m_state = m_state.getFuture();
        
        v = m_state.getValue();
        m_lives = m_state.getLives();
        m_assist = m_state.getHints();
        m_toSolve[v.getX()][v.getY()] = v.getZ();
    }

    private String getBoard(boolean solved)
    {
        int[][] board = solved == true ? sm_solved : m_toSolve;
        String str = new String();

        for (int i = 0; i != 9; ++i)
        {
            for (int j = 0; j != 9; ++j)
            {
                str = str.concat(Integer.toString(boards[i][j]) + " ");
            }
        }
        
        return str;
    }    
}
