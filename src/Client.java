import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.StringProperty;

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
    public Client(int tableNumber, int numberOfPerson, String name, int phoneNumber, String comment)
    {
        this.tableNumber = tableNumber;
        this.numberOfPerson = numberOfPerson;
        this.name = name;
        this.hour = hour;
        this.minute = minute;
        this.phoneNumber = phoneNumber;
        this.comment = comment;
        table=null;
    }

    //name
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    //tableNumber
    public int getTableNumber() {
            return tableNumber;
        }

    public void setTableNumber(int tableNumber) {
        this.tableNumber = tableNumber;
    }

    //numberOfPerson
    public int getNumberOfPerson() {
            return numberOfPerson;
        }

    public void setNumberOfPerson(int numberOfPerson){
            this.numberOfPerson = numberOfPerson;
        }

     //phoneNumber
     public int getPhoneNumber() {
                return phoneNumber;
            }

     public void setPhoneNumber(int phoneNumber){
            this.phoneNumber = phoneNumber;
        }

     //comment
     public String getComment() {
                    return comment;
                }

     public void setComment(String comment){
            this.comment = comment;
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
