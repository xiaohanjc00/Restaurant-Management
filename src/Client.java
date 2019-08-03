
/**
 * Write a description of class client here.
 *
 * @Xiaohan Jiang Chen
 * @version 1.0 (24-07-19)
 */
public class Client
{
    private int tableNumber;
    private int numberOfPerson;
    private String name;
    private int hour;
    private int minute;
    private int phoneNumber;
    private String comment;
    private Time time;
    private Table table;
    private Tables tables;
    public Client(int tableNumber, int numberOfPerson, String name, int hour, int minute, int phoneNumber, String comment)
    {
        this.tableNumber = tableNumber;
        this.numberOfPerson = numberOfPerson;
        this.name = name;
        this.hour = hour;
        this.minute = minute;
        this.phoneNumber = phoneNumber;
        this.comment = comment;
        table=null;
        //time.parseProcess(hour, minute);
        //addTable(tableNumber);
        
    }
    
    //public void addTable(int newTableNumber)
    //{
    //    tableNumber = newTableNumber;
    //    table = tables.searchTable(newTableNumber);
    //}
    
    public void addClientToTable()
    {
        
    }
    
    /**
     *Add a new name for the client
     * @param  newName of the client
     */
    public void addName(String newName)
    {
        name = newName;
    }
    
    public int addSinglePerson()
    {
        numberOfPerson+=1;
        return numberOfPerson;
    }
    
    public int addPerson(int newPeople)
    {
        numberOfPerson+=newPeople;
        return numberOfPerson;
    }
    
    public int removeSinglePerson()
    {
        numberOfPerson-=1;  
        return numberOfPerson;
    }
    
    public int removePerson(int removedPeople)
    {
        numberOfPerson-=removedPeople;
        return numberOfPerson;
    }
    
    public void addComment(String newComment)
    {
        comment=newComment;
    }
    
    public void removeComment()
    {
        comment=null;
    }
    
    public int tableNumber()
    {
        return tableNumber;
    }
}
