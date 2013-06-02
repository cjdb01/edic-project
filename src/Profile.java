public class Profile
{
    private final String m_name;
    private int m_gamesPlayed;
    private int m_gamesSolved;
    private int m_gamesSaved;
    private int m_best;
    private int m_worst;
    private int[] m_times;
    
    public Profile(final String name)
    {
        m_name = name;
        m_gamesPlayed = 0;
        m_gamesSolved = 0;
        m_gamesSaved = 0;
        
        m_best = 0;
        m_times = new int[100];
        
        for (int i = 0; i != 100; ++i)
        {
        	m_times[i] = 0;
        }
    }
    
    public Profile(final String name, int played, int solved, int saved, int best, int worst, int[] times)
    {
        m_name = name;
        m_gamesPlayed = played;
        m_gamesSolved = solved;
        m_gamesSaved = saved;
        m_best = best;
        m_worst = worst;
        m_times = times;
    }
    
    public final String getName()
    {
        return m_name;
    }
    
    public int getGamesPlayed()
    {
        return m_gamesPlayed;
    }
    
    public int getGamesSolved()
    {
        return m_gamesSolved;
    }
    
    public int getGamesSaved()
    {
        return m_gamesSaved;
    }
    
    public int getBestTime()
    {
        return m_best;
    }
    
    public int getWorstTime()
    {
        return m_worst;
    }
    
    public int getAverageTime()
    {
        int average = 0;
        for (int t : m_times)
        {
            average += t;
        }
        
        return average / m_gamesSolved;
    }
    
    public int getTotalTime()
    {
        int total = 0;
        
        for (int t : m_times)
        {
            total += t;
        }
        
        return total;
    }
    
    public void insertTime(int t)
    {
        m_times[m_gamesSolved % 100] = t;
        
        if (t < m_best || m_best == 0)
        {
            m_best = t;
        }
        
        if (t > m_worst || m_worst == 0)
        {
            m_worst = t;
        }
    }
    
    public int incrementGamesPlayed()
    {
        return ++m_gamesPlayed;
    }
    
    public int incrementGamesSolved()
    {
        return ++m_gamesSolved;
    }
    
    public int incrementGamesSaved()
    {
        return ++m_gamesSaved;
    }
}