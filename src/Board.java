public class Board
{
    private final int[][] m_solved;
    private int[][] m_toSolve;
    private final boolean[][] m_editable;

    public Board(int[][] toSolve, final int[][] solved)
    {
    	m_solved = new int[9][9];
    	m_toSolve = new int[9][9];
    	m_editable = new boolean[9][9];
    	
        System.arraycopy(toSolve, 0, m_toSolve, 0, 81);
        System.arraycopy(m_solved, 0, m_solved, 0, 81);

        for (int i = 0; i != 9; ++i)
        {
            for (int j = 0; j != 9; ++j)
            {
                m_editable[i][j] = (m_toSolve[i][j] == 0);
            }
        }
    }

	public boolean setNode(int x, int y, int val)
    {
        if (m_editable[x][y] == true && val >= 0 && val < 10)
        {
            m_toSolve[x][y] = val;
        }

        return m_editable[x][y];
    }

    public String getBoard()
    {
        String str = new String();

        for (int i = 0; i != 9; ++i)
        {
            for (int j = 0; j != 9; ++j)
            {
                str.append(Integer(m_toSolve[i][j]).toString() + " ");
            }
        }
    }

    
}
