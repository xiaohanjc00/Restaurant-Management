import javafx.beans.property.*;

public class Client
{
    private SimpleIntegerProperty tableNumber;
    private SimpleIntegerProperty numberOfPerson;
    private SimpleStringProperty name;
    private int hour;
    private int minute;
    private SimpleLongProperty phoneNumber;
    private SimpleStringProperty comment;
    private SimpleStringProperty time;
    private SimpleBooleanProperty arrived;
    //private Time time;
    private Table table;
    private Tables tables;


    public Client(String name, int tableNumber, int numberOfPerson, long phoneNumber, String comment, String time, boolean arrived)
    {
        this.tableNumber = new SimpleIntegerProperty(tableNumber);
        this.numberOfPerson = new SimpleIntegerProperty(numberOfPerson);
        this.name = new SimpleStringProperty(name);
        this.hour = hour;
        this.minute = minute;
        this.phoneNumber = new SimpleLongProperty(phoneNumber);
        this.comment = new SimpleStringProperty(comment);
        this.time = new SimpleStringProperty(time);
        this.arrived = new SimpleBooleanProperty(arrived);
        table=null;
    }

    //name
    public String getName() {
        return name.get();
    }

    public void setName(String newName) {
        name.set(newName);
    }

    //tableNumber
    public int getTableNumber() {
            return tableNumber.get();
        }

    public void setTableNumber(int newTableNumber) {
        tableNumber.set(newTableNumber);
    }

    //numberOfPerson
    public int getNumberOfPerson() {
            return numberOfPerson.get();
        }

    public void setNumberOfPerson(int newNumberOfPerson){
            numberOfPerson.set(newNumberOfPerson);
        }

     //phoneNumber
     public long getPhoneNumber() {
                return phoneNumber.get();
            }

     public void setPhoneNumber(long newPhoneNumber){
            phoneNumber.set(newPhoneNumber);
        }

     //comment
     public String getComment() {
                    return comment.get();
                }

     public void setComment(String newComment){
            comment.set(newComment);
        }

    //time
    public String getTime() {
        return time.get();
    }

    public void setTime(String newTime) {
        time.set(newTime);
    }

    //arrived
    public BooleanProperty arrivedProperty(){return arrived;}

    public boolean getArrived() {
        return arrived.get();
    }

    public void setArrived(boolean newArrived) {
        arrived.set(newArrived);
    }


    /**
     *Add a new name for the client
     * @param  newName of the client

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
    */
}
