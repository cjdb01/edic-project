/**
 * The previous/forward states
 * @author Christopher Di Bella
 */
public class State
{
    private final State m_past;
    private State m_future;
    
    private Vector3D m_value;
    private int m_assist;
    
    /**
     * Constructor
     * @param past The previous state
     * @param future The future state (usually null here)
     * @param hints The number of hints left at this point in the game
     * @param value The last value modified
     */
    public State(State past, State future, int hints, Vector3D value)
    {
        m_past = past;
        m_future = future;
        m_value = value;
    }
    
    /**
     * Sets the future state
     * @param future The state that this state precedes
     */
    public void setFuture(State future)
    {
        m_future = future;
    }
    
    /**
     * Gets the previous state
     * @return m_past
     */
    public State getPast()
    {
        return m_past;
    }
    
    /**
     * The state after this one
     * @return m_future
     */
    public State getFuture()
    {
        return m_future;
    }
    
    /**
     * Gets the value associated with this state
     * @return m_value
     */
    public Vector3D getValue()
    {
        return m_value;
    }
    
    /**
     * Gets the number of assists associated with this state
     * @return m_assist
     */
    public int getAssist()
    {
        return m_assist;
    }
    
    /**
     * Reduces the number of assists by one
     */
    public void loseAssist()
    {
        subtractAssist(true);
    }
    
    /**
     * Reduces the number of assists by one
     * @param backward Determines if the path is going back in time or forward
     */
    private void subtractAssist(boolean backward)
    {
        --m_assist;
        
        if (backward == true)
        {
            if (m_past != null)
            {
                m_past.subtractAssist(true);
            }
        }
        
        if (m_future != null)
        {
            m_future.subtractAssist(false);
        }
    }
}