import java.util.ArrayList;
/**
 * Write a description of class table here.
 *
 * @Xiaohan Jiang Chen
 * @V1.0
 */
public class Table
{
    private int tableNumber;
    private int numberOfPerson;  //default number of person for the table
    private boolean occupied;
    private ArrayList<Client> clients;
    
    public Table()
    {
        this.tableNumber = tableNumber;
        this.numberOfPerson = numberOfPerson;
        occupied = false;
        ArrayList<Client> clients = new ArrayList<Client>();
    }
    
    public void addNewClient(int tableNumber, int numberOfPerson, String name, int hour, int minute, int phoneNumber, String comment)
    {
        Client client = new Client(name, tableNumber,numberOfPerson,phoneNumber,comment, null, false);
        clients.add(client);
    }
    
    public int numberOfClients()
    {
        return clients.size();
    }
    
    public int tableNumber()
    {
        return tableNumber;
    }   
    
    public void changeTableNumber(int newTableNumber)
    {
        tableNumber = newTableNumber;
    }
    
    public void changeNumberOfPerson(int number)
    {
        numberOfPerson = number;
    }
    
    public boolean isOccupied()
    {
        return occupied;
    }
    
    public void addHour(int hourNumber)
    {
        boolean withinRange = false;
        while(withinRange!=true)
        {
            if(hourNumber<24 && hourNumber>=0){
                withinRange = true;
            }
            else{
                System.out.println("Introduce a valid hour");
            }
        }
    }
    
    public void addMinute(int minuteNumber)
    {
        boolean withinRange = false;
        while(withinRange!=true)
        {
            if(minuteNumber<60 && minuteNumber>=0){
                withinRange = true;
            }
            else{
                System.out.println("Introduce a valid minute");
            }
        }
    }
    
    public boolean checkHour(int hourNumber, int minuteNumber)
    {
        boolean withinRange = false;
        if((hourNumber>=13 && minuteNumber>=30) && (hourNumber<16)){
            withinRange = true;
        }
        if((hourNumber>=14 && minuteNumber>=0) && (hourNumber<16)){
            withinRange = true;
        }
        if((hourNumber>=20 && minuteNumber>=30) && (hourNumber<24)){
            withinRange = true;
        }
        if((hourNumber>=21 && minuteNumber>=00) && (hourNumber<24)){
            withinRange = true;
        }
        return withinRange;
    }
    
    //public void clearTable()
    //{
     //   occupied = false;
    //    numberOfPerson = 0;
    //    comment = null;
    //    name = null;
    //    hour = 0;
    //    minute = 0;
    //}
}
