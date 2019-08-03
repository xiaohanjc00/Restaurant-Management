import java.text.SimpleDateFormat;
import java.text.DateFormat;
import java.util.Date;
import java.util.Calendar;
public class aaa
{
    // instance variables - replace the example below with your own
    private int x;
    private Date date;
    private int hour;
    private int minute;
    /**
     * Constructor for objects of class aaa
     */
    public aaa(int hour, int minute)
    {
        this.hour=hour;
        this.minute=minute;
        DateFormat bb = new SimpleDateFormat("HH:mm");
        String toBeParsed = (hour + ":" + minute);
        //System.out.println(toBeParsed);
        //Date date;
        try{
            Date date = bb.parse(toBeParsed);
        }
        catch(Exception e) {
            System.out.println("aa");
        }
        
    }

  
}
