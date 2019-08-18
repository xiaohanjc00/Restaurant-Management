import javax.print.DocFlavor;
import java.sql.*;

public class ClientDatabase {
    //JDBC driver name and database URL
    static final String JDBC_DRIVER = "org.h2.Driver";
    static final String DB_URL = "jdbc:h2:~/test6";

    //Database credentials
    static final String USER = "sa";
    static final String PASS = "";

    private Client client;

    static Connection conn = null;
    static Statement stmt = null;

    private static DateController date;

    public static void main(String[] args) {
        createDatabase();
        createSecondaryDatabase();
        printClients();
    }

    /**
     * Show the values in the current day's database table
     * if no such table, create one
     */
    public static void startTable(String suffix){
        try {
            String sql = "select * from CLIENT" + suffix;   //If database table exist, the show all the clients in the table
            stmt.executeQuery(sql);
            System.out.println("Loading CLIENT" + suffix + " clients...");
            ResultSet rs = stmt.executeQuery(sql);

            while (rs.next()) {
                // Retrieve by column name
                String name = rs.getString("name");
                int tableNumber = rs.getInt("tableNumber");
                int numberOfPerson = rs.getInt("numberOfPerson");
                long phoneNumber = rs.getLong("phoneNumber");
                String comment = rs.getString("comment");

                //Create new client with the information above from the database
                Client newClient = new Client(name, tableNumber, numberOfPerson, phoneNumber, comment);
                menu.addToObservableList(newClient);     //Add the new client to the observable list in the menu class
            }

        } catch (SQLException e) {
            e.printStackTrace();
            //If no such table exist, create the table
            String sql2 =  "CREATE TABLE   CLIENT" + suffix +
                    " (name VARCHAR (255), " +
                    " tableNumber INTEGER, " +
                    " numberOfPerson INTEGER, " +
                    " phoneNumber BIGINT, " +
                    " comment VARCHAR(255)," +
                    " PRIMARY KEY ( phoneNumber,name ))";
            try {
                System.out.println("Creating new table for the current date...");
                stmt.executeUpdate(sql2);
                System.out.println("Table created...");
                System.out.println("Loading CLIENT" + suffix + " clients...");
            }
            catch (SQLException ex) {
                ex.printStackTrace();
            }
        }
    }



    /**
     * Initial connection to the database
     */
    public static void createDatabase(){
        try {
            // STEP 1: Register JDBC driver
            Class.forName(JDBC_DRIVER);

            //STEP 2: Open a connection
            System.out.println("Connecting to database...");
            conn = DriverManager.getConnection(DB_URL,USER,PASS);

            stmt = conn.createStatement();

        } catch(SQLException se) {  //Handle errors for JDBC
            //se.printStackTrace();
        } catch(Exception e) {  //Handle errors for Class.forName
            //e.printStackTrace();
        }
    }

    /**
     * Create new table for the deleted clients
     */
    public static void createSecondaryDatabase(){
        try {
            String sql2 =  "CREATE TABLE DELETEDCLIENT " +
                    "(name VARCHAR (255), " +
                    " tableNumber INTEGER, " +
                    " numberOfPerson INTEGER, " +
                    " phoneNumber BIGINT, " +
                    " comment VARCHAR(255)," +
                    " PRIMARY KEY ( phoneNumber,name ))";

            stmt.executeUpdate(sql2);
            System.out.println("Created table for the deleted clients...");

        } catch(SQLException se) {  //Handle errors for JDBC
            se.printStackTrace();
        } catch(Exception e) {  ////Handle errors for Class.forName
            e.printStackTrace();
        }
    }

    /**
     * Print out the clients in the current date table
     */
    public static void printClients() {
        try {
            String sql = "select * from CLIENT" + menu.datePicker.getSelectedDate();
            stmt.executeQuery(sql);
            System.out.println("");
            System.out.println("=====================================================================================");
            System.out.println("Showing clients...");
            ResultSet rs = stmt.executeQuery(sql);

            while (rs.next()) {
                // Retrieve by column name
                String name = rs.getString("name");
                int tableNumber = rs.getInt("tableNumber");
                int numberOfPerson = rs.getInt("numberOfPerson");
                long phoneNumber = rs.getLong("phoneNumber");
                String comment = rs.getString("comment");

                // Display values
                System.out.println("---------------------------------------------------------------------------------");
                System.out.print("|name: " + name);
                System.out.print(", tableNumber: " + tableNumber);
                System.out.print(", numberOfPerson: " + numberOfPerson);
                System.out.println(", phoneNumber: " + phoneNumber);
                System.out.println("|, comment: " + comment);
                System.out.println("---------------------------------------------------------------------------------");


            }

        } catch (SQLException e) {
            //e.printStackTrace();
        }
    }

    /**
     * Print out the clients in the deleted client table
     */
    public static void printDeletedClients() {
        try {
            String sql = "select * from DELETEDCLIENT";
            stmt.executeQuery(sql);
            System.out.println("");
            System.out.println("=====================================================================================");
            System.out.println("Showing deleted clients...");
            ResultSet rs = stmt.executeQuery(sql);

            while (rs.next()) {
                // Retrieve by column name
                String name = rs.getString("name");
                int tableNumber = rs.getInt("tableNumber");
                int numberOfPerson = rs.getInt("numberOfPerson");
                long phoneNumber = rs.getLong("phoneNumber");
                String comment = rs.getString("comment");

                // Display values
                System.out.println("---------------------------------------------------------------------------------");
                System.out.print("|name: " + name);
                System.out.print(", tableNumber: " + tableNumber);
                System.out.print(", numberOfPerson: " + numberOfPerson);
                System.out.println(", phoneNumber: " + phoneNumber);
                System.out.println("|, comment: " + comment);
                System.out.println("---------------------------------------------------------------------------------");
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    /**
     * Add new clients to the corresponding database table
     * @param newClient
     * @param suffix
     */
    public void addClient(Client newClient, String suffix){
        try{
            String sql2 = "INSERT INTO CLIENT" + suffix + " VALUES (" + "'" +
                    newClient.getName() + "'" + "," +"'" +
                    newClient.getTableNumber() + "'" + "," +"'" +
                    newClient.getNumberOfPerson() + "'" + "," +"'" +
                    newClient.getPhoneNumber() + "'" + "," +"'" +
                    newClient.getComment() + "'" +  ")";
            System.out.println("");
            System.out.println("=====================================================================================");
            System.out.println("Inserting new values...");
            System.out.println("=====================================================================================");
            System.out.println("");
            stmt.executeUpdate(sql2);
            System.out.println("Done...");
            System.out.println("=====================================================================================");
            System.out.println("");
        }
        catch(Exception e){
        }
    }

    /**
     * Add new clients to the deleted client database table
     * @param newClient
     * @param suffix
     */
    public void deleteClient(Client newClient, String suffix){
        try{
            String sql = "INSERT INTO DELETEDCLIENT (name, tableNumber, numberOfPerson, phoneNumber, comment) " +
                    "SELECT * " +
                    "FROM CLIENT" + suffix +
                    " WHERE (name = " + "'" + newClient.getName() + "'" + " AND " +
                    "tableNumber = " + "'" + newClient.getTableNumber() + "'" + " AND " +
                    "numberOfPerson = " + "'" + newClient.getNumberOfPerson() + "'" + " AND " +
                    "phoneNumber = " + "'" + newClient.getPhoneNumber() + "'" + " AND " +
                    "comment = " + "'" + newClient.getComment() + "' )" ;
            String sql2 = "DELETE FROM CLIENT" + suffix +
                    " WHERE (name = " + "'" + newClient.getName() + "'" + " AND " +
                    "tableNumber = " + "'" + newClient.getTableNumber() + "'" + " AND " +
                    "numberOfPerson = " + "'" + newClient.getNumberOfPerson() + "'" + " AND " +
                    "phoneNumber = " + "'" + newClient.getPhoneNumber() + "'" + " AND " +
                    "comment = " + "'" + newClient.getComment() + "' )" ;

            stmt.executeUpdate(sql);
            stmt.executeUpdate(sql2);
            System.out.println("");
            System.out.println("================================================================");
            System.out.println("Deleted...");
            System.out.println("================================================================");
            System.out.println("");
        }
        catch(Exception e){
        }
    }

    /**
     * Edit the name value for a client in the selected date's table
     * @param newName
     * @param phoneNumber
     */
    public static void editName(String newName, long phoneNumber){
        try{
            String sql = "UPDATE CLIENT" + menu.suffix +
                    " SET name = " + "'" + newName + "'" +
                    "WHERE phoneNumber = " + "'" + phoneNumber + "'";
            stmt.executeUpdate(sql);
        }
        catch(Exception e){
            e.printStackTrace();
        }
    }

    /**
     * Edit the table number value for a client in the selected date's table
     * @param newTableNumber
     * @param phoneNumber
     */
    public static void editTableNumber(int newTableNumber, long phoneNumber){
        try{
            String sql = "UPDATE CLIENT" + menu.suffix +
                    " SET tableNumber = " + "'" + newTableNumber + "'" +
                    "WHERE phoneNumber = " + "'" + phoneNumber + "'";
            stmt.executeUpdate(sql);
        }
        catch(Exception e){
            e.printStackTrace();
        }
    }

    /**
     * Edit the number of person value for a client in the selected date's table
     * @param newNumberOfPerson
     * @param phoneNumber
     */
    public static void editNumberOfPerson(int newNumberOfPerson, long phoneNumber){
        try{
            String sql = "UPDATE CLIENT" + menu.suffix +
                    " SET numberOfPerson = " + "'" + newNumberOfPerson + "'" +
                    "WHERE phoneNumber = " + "'" + phoneNumber + "'";
            stmt.executeUpdate(sql);
        }
        catch(Exception e){
            e.printStackTrace();
        }
    }

    /**
     * Edit the phone number value for a client in the selected date's table
     * @param newPhoneNumber
     * @param name
     * @param tableNumber
     * @param numberOfPerson
     * @param comment
     */
    public static void editPhoneNumber(long newPhoneNumber, String name, int tableNumber, int numberOfPerson, String comment){
        try{
            String sql = "UPDATE CLIENT" + menu.suffix +
                    " SET phoneNumber = " + "'" + newPhoneNumber + "'" +
                    "WHERE name = " + "'" + name + "'" + " AND " +
                    "tableNumber = " + "'" + tableNumber + "'" + " AND " +
                    "numberOfPerson = " + "'" +  numberOfPerson + "'" + " AND " +
                    "comment = " + "'" + comment + "'";
            stmt.executeUpdate(sql);
        }
        catch(Exception e){
            e.printStackTrace();
        }
    }

    /**
     * Edit the comment value for a client in the selected date's table
     * @param newComment
     * @param phoneNumber
     */
    public static void editComment(String newComment, long phoneNumber){
        try{
            String sql = "UPDATE CLIENT" + menu.suffix +
                    " SET comment = " + "'" + newComment + "'" +
                    "WHERE phoneNumber = " + "'" + phoneNumber + "'";
            stmt.executeUpdate(sql);
        }
        catch(Exception e){
            e.printStackTrace();
        }
    }

    /**
     * Closing the database process
     */
    public static void closeDatabase(){
        try{
            stmt.close();
            conn.close();
        }
        catch(SQLException se) {
            //Handle errors for JDBC
            se.printStackTrace();
        }
        catch(Exception e) {
            //Handle errors for Class.forName
            e.printStackTrace();
        }

        //finally block used to close resources
        try{
            if(stmt!=null) stmt.close();
        }
        catch(SQLException se2) {
        } // nothing we can do
        try {
            if(conn!=null) conn.close();
        }
        catch(SQLException se){
            se.printStackTrace();
        } //end finally try

        System.out.println("Goodbye!");
    }
}