public class State
{
    private final State m_pastState;
    private State m_futureState;
    
    private final Vector3D m_value;
    private final int m_lives;
    private final int m_hints;
    
    public State(State past, State future, int lives, int hints, Vector3D value)
    {
        m_past = past;
        m_future = future;
        m_lives = lives;
        m_hints = hints;
        m_value = value;
    }
    
    void setFuture(State future)
    {
        m_future = future;
    }
    
    State getPast()
    {
        return m_past;
    }
    
    State getFuture()
    {
        return m_future;
    }
    
    Vector3D getValue()
    {
        return m_value;
    }
    
    int getLives()
    {
        return m_lives;
    }
    
    int getHints()
    {
        return m_hints;
    }
}