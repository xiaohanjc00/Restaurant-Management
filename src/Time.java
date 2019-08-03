import java.util.Calendar;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
public class Time
{
    private int hour;
    private int minute;
    
    /**
     * Constructor for objects of class time
     */
    public Time()
    {
        this.hour=hour;
        this.minute=minute;
    }
    
    public void parseProcess(int hour, int minute)
    {
        DateFormat bb = new SimpleDateFormat("HH:mm");
        String toBeParsed = (hour + ":" + minute);
        try{
            Date date = bb.parse(toBeParsed);
        }
        catch(Exception e) {
            System.out.println("Cannot be parsed");
        }
    }
    
}
