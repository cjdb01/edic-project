/*public class Time
{
    private int m_hours;
    private int m_minutes;
    private int m_seconds;
    
    public Time()
    {
        m_hours = 0;
        m_minutes = 0;
        m_seconds = 0;
    }
    
    public Time(int hours, int minutes, int seconds)
    {
        m_hours = hours;
        m_minutes = minutes;
        m_seconds = seconds;
    }
    
    public int getHours()
    {
        return m_hours;
    }
    
    public int getMinutes()
    {
        return m_minutes;
    }
    
    public int getSeconds()
    {
        return m_seconds;
    }
    
    public void setTime(Time t)
    {
        m_hours = time.m_hours;
        m_minutes = time.m_minutes;
        m_seconds = time.m_seconds;
    }
    
    public boolean less(Time time)
    {
        if (m_hours < time.m_hours)
            return true;
        else if (m_hours == time.m_hours && m_minutes < time.m_minutes)
            return true;
        else if (m_hours == time.m_hours && m_minutes == time.m_minutes && m_seconds < time.m_seconds)
            return true;
        
        return false;
    }
    
    public boolean more(Time time)
    {
        if (m_hours > time.m_hours)
            return true;
        else if (m_hours == time.m_hours && m_minutes > time.m_minutes)
            return true;
        else if (m_hours == time.m_hours && m_minutes == time.m_minutes && m_seconds > time.m_seconds)
            return true;
            
        return false;
    }
    
    public boolean equals(Time t)
    {
        return (!less(t) && !more(t));
    }
}*/