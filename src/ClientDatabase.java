import javax.print.DocFlavor;
import java.sql.*;

public class ClientDatabase {
    // JDBC driver name and database URL
    static final String JDBC_DRIVER = "org.h2.Driver";
    static final String DB_URL = "jdbc:h2:~/test4";

    //  Database credentials
    static final String USER = "sa";
    static final String PASS = "";

    private Client client;

    static Connection conn = null;
    static Statement stmt = null;

    private static DateController date;

    public static void main(String[] args) {
        createDatabase();
        test();
        printClients();
    }


    public static void startTable(){
        try {
            String sql = "select * from CLIENT" + menu.datePicker.currentDate();
            stmt.executeQuery(sql);
            System.out.println("Showing clients...");
            ResultSet rs = stmt.executeQuery(sql);

            while (rs.next()) {
                // Retrieve by column name
                String name = rs.getString("name");
                int tableNumber = rs.getInt("tableNumber");
                int numberOfPerson = rs.getInt("numberOfPerson");
                int phoneNumber = rs.getInt("phoneNumber");
                String comment = rs.getString("comment");

                Client newClient = new Client(name, tableNumber, numberOfPerson, phoneNumber, comment);
                menu.addToObservableList(newClient);
            }

        } catch (SQLException e) {
            e.printStackTrace();
            String sql2 =  "CREATE TABLE   CLIENT" + menu.datePicker.currentDate() +
                    " (name VARCHAR (255), " +
                    " tableNumber INTEGER, " +
                    " numberOfPerson INTEGER, " +
                    " phoneNumber INTEGER, " +
                    " comment VARCHAR(255)," +
                    " PRIMARY KEY ( phoneNumber,name ))";
            try {
                stmt.executeUpdate(sql2);
            }
            catch (SQLException ex) {
                ex.printStackTrace();
            }
            finally{
                String sql3 = "select * from CLIENT";
                try {
                    stmt.executeQuery(sql3);

                    System.out.println("Showing clients...");
                    ResultSet rs = stmt.executeQuery(sql3);

                    while (rs.next()) {
                        // Retrieve by column name
                        String name = rs.getString("name");
                        int tableNumber = rs.getInt("tableNumber");
                        int numberOfPerson = rs.getInt("numberOfPerson");
                        int phoneNumber = rs.getInt("phoneNumber");
                        String comment = rs.getString("comment");

                        Client newClient = new Client(name, tableNumber, numberOfPerson, phoneNumber, comment);
                        menu.addToObservableList(newClient);
                    }
                }
                catch (SQLException ex) {
                    ex.printStackTrace();
                }
            }
        }
    }

    public static void startTable(String suffix){
        try {
            String sql = "select * from CLIENT" + suffix;
            stmt.executeQuery(sql);
            System.out.println("Showing clients...");
            ResultSet rs = stmt.executeQuery(sql);

            while (rs.next()) {
                // Retrieve by column name
                String name = rs.getString("name");
                int tableNumber = rs.getInt("tableNumber");
                int numberOfPerson = rs.getInt("numberOfPerson");
                int phoneNumber = rs.getInt("phoneNumber");
                String comment = rs.getString("comment");

                Client newClient = new Client(name, tableNumber, numberOfPerson, phoneNumber, comment);
                menu.addToObservableList(newClient);
            }

        } catch (SQLException e) {
            e.printStackTrace();
            String sql2 =  "CREATE TABLE   CLIENT" + menu.datePicker.currentDate() +
                    " (name VARCHAR (255), " +
                    " tableNumber INTEGER, " +
                    " numberOfPerson INTEGER, " +
                    " phoneNumber INTEGER, " +
                    " comment VARCHAR(255)," +
                    " PRIMARY KEY ( phoneNumber,name ))";
            try {
                stmt.executeUpdate(sql2);
            }
            catch (SQLException ex) {
                ex.printStackTrace();
            }
            finally{
                String sql3 = "select * from CLIENT";
                try {
                    stmt.executeQuery(sql3);

                    System.out.println("Showing clients...");
                    ResultSet rs = stmt.executeQuery(sql3);

                    while (rs.next()) {
                        // Retrieve by column name
                        String name = rs.getString("name");
                        int tableNumber = rs.getInt("tableNumber");
                        int numberOfPerson = rs.getInt("numberOfPerson");
                        int phoneNumber = rs.getInt("phoneNumber");
                        String comment = rs.getString("comment");

                        Client newClient = new Client(name, tableNumber, numberOfPerson, phoneNumber, comment);
                        menu.addToObservableList(newClient);
                    }
                }
                catch (SQLException ex) {
                    ex.printStackTrace();
                }
            }
        }
    }

    public static void createDatabase(){
        try {
            // STEP 1: Register JDBC driver
            Class.forName(JDBC_DRIVER);

            //STEP 2: Open a connection
            System.out.println("Connecting to database...");
            conn = DriverManager.getConnection(DB_URL,USER,PASS);


            //STEP 3: Execute a query
            System.out.println("Creating table in given database...");
            stmt = conn.createStatement();
            String sql =  "CREATE TABLE   CLIENT " +
                    "(name VARCHAR (255), " +
                    " tableNumber INTEGER, " +
                    " numberOfPerson INTEGER, " +
                    " phoneNumber INTEGER, " +
                    " comment VARCHAR(255)," +
                    " PRIMARY KEY ( phoneNumber,name ))";
            stmt.executeUpdate(sql);
            createSecondaryDatabase();
            System.out.println("Created primary table in given database...");

        } catch(SQLException se) {
            //Handle errors for JDBC
            se.printStackTrace();
        } catch(Exception e) {
            //Handle errors for Class.forName
            e.printStackTrace();
        }
    }

    public static void createSecondaryDatabase(){
        try {
            String sql2 =  "CREATE TABLE DELETEDCLIENT " +
                    "(name VARCHAR (255), " +
                    " tableNumber INTEGER, " +
                    " numberOfPerson INTEGER, " +
                    " phoneNumber INTEGER, " +
                    " comment VARCHAR(255)," +
                    " PRIMARY KEY ( phoneNumber,name ))";

            stmt.executeUpdate(sql2);
            System.out.println("Created secondary table in given database...");

        } catch(SQLException se) {
            //Handle errors for JDBC
            se.printStackTrace();
        } catch(Exception e) {
            //Handle errors for Class.forName
            e.printStackTrace();
        }
    }

    public static void test(){
        try {
            String sql = "INSERT INTO CLIENT VALUES ('hola','122',12,123,'sdfg')";
            System.out.println("hello...");
            stmt.executeUpdate(sql);
            System.out.println("Done...");

        }
        catch(Exception e){

        }
    }

    public static void printClients() {
        try {

            String sql = "select * from CLIENT" + menu.datePicker.currentDate();
            stmt.executeQuery(sql);
            System.out.println("Showing clients...");
            ResultSet rs = stmt.executeQuery(sql);

            while (rs.next()) {
                // Retrieve by column name
                String name = rs.getString("name");
                int tableNumber = rs.getInt("tableNumber");
                int numberOfPerson = rs.getInt("numberOfPerson");
                int phoneNumber = rs.getInt("phoneNumber");
                String comment = rs.getString("comment");

                // Display values
                System.out.print("name: " + name);
                System.out.print(", tableNumber: " + tableNumber);
                System.out.print(", numberOfPerson: " + numberOfPerson);
                System.out.println(", phoneNumber: " + phoneNumber);
                System.out.println(", comment: " + comment);
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public static void printDeletedClients() {
        try {

            String sql = "select * from DELETEDCLIENT";
            stmt.executeQuery(sql);
            System.out.println("Showing deleted clients...");
            ResultSet rs = stmt.executeQuery(sql);

            while (rs.next()) {
                // Retrieve by column name
                String name = rs.getString("name");
                int tableNumber = rs.getInt("tableNumber");
                int numberOfPerson = rs.getInt("numberOfPerson");
                int phoneNumber = rs.getInt("phoneNumber");
                String comment = rs.getString("comment");

                // Display values
                System.out.print("name: " + name);
                System.out.print(", tableNumber: " + tableNumber);
                System.out.print(", numberOfPerson: " + numberOfPerson);
                System.out.println(", phoneNumber: " + phoneNumber);
                System.out.println(", comment: " + comment);
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void addClient(Client newClient){
        try{
            String sql2 = "INSERT INTO CLIENT" + menu.datePicker.currentDate() + " VALUES (" + "'" +
                    newClient.getName() + "'" + "," +"'" +
                    newClient.getTableNumber() + "'" + "," +"'" +
                    newClient.getNumberOfPerson() + "'" + "," +"'" +
                    newClient.getPhoneNumber() + "'" + "," +"'" +
                    newClient.getComment() + "'" +  ")";
            System.out.println("Inserting new values...");
            stmt.executeUpdate(sql2);
            System.out.println("Done...");
        }
        catch(Exception e){
        }
    }

    public void deleteClient(Client newClient){
        try{
            String sql = "INSERT INTO DELETEDCLIENT (name, tableNumber, numberOfPerson, phoneNumber, comment) " +
                    "SELECT * " +
                    "FROM CLIENT " +
                    "WHERE (name = " + "'" + newClient.getName() + "'" + " AND " +
                    "tableNumber = " + "'" + newClient.getTableNumber() + "'" + " AND " +
                    "numberOfPerson = " + "'" + newClient.getNumberOfPerson() + "'" + " AND " +
                    "phoneNumber = " + "'" + newClient.getPhoneNumber() + "'" + " AND " +
                    "comment = " + "'" + newClient.getComment() + "' )" ;
            String sql2 = "DELETE FROM CLIENT " +
                    "WHERE (name = " + "'" + newClient.getName() + "'" + " AND " +
                    "tableNumber = " + "'" + newClient.getTableNumber() + "'" + " AND " +
                    "numberOfPerson = " + "'" + newClient.getNumberOfPerson() + "'" + " AND " +
                    "phoneNumber = " + "'" + newClient.getPhoneNumber() + "'" + " AND " +
                    "comment = " + "'" + newClient.getComment() + "' )" ;

            stmt.executeUpdate(sql);
            stmt.executeUpdate(sql2);
            System.out.println("Deleted...");
        }
        catch(Exception e){
        }
    }

    public static void editName(String newName, int phoneNumber){
        try{
            String sql = "UPDATE CLIENT " +
                    "SET name = " + "'" + newName + "'" +
                    "WHERE phoneNumber = " + "'" + phoneNumber + "'";
            stmt.executeUpdate(sql);
        }
        catch(Exception e){
            e.printStackTrace();
        }
    }

    public static void editTableNumber(int newTableNumber, int phoneNumber){
        try{
            String sql = "UPDATE CLIENT " +
                    "SET tableNumber = " + "'" + newTableNumber + "'" +
                    "WHERE phoneNumber = " + "'" + phoneNumber + "'";
            stmt.executeUpdate(sql);
        }
        catch(Exception e){
            e.printStackTrace();
        }
    }

    public static void editNumberOfPerson(int newNumberOfPerson, int phoneNumber){
        try{
            String sql = "UPDATE CLIENT " +
                    "SET numberOfPerson = " + "'" + newNumberOfPerson + "'" +
                    "WHERE phoneNumber = " + "'" + phoneNumber + "'";
            stmt.executeUpdate(sql);
        }
        catch(Exception e){
            e.printStackTrace();
        }
    }

    public static void editPhoneNumber(int newPhoneNumber, String name, int tableNumber, int numberOfPerson, String comment){
        try{
            String sql = "UPDATE CLIENT " +
                    "SET phoneNumber = " + "'" + newPhoneNumber + "'" +
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

    public static void editComment(String newComment, int phoneNumber){
        try{
            String sql = "UPDATE CLIENT " +
                    "SET comment = " + "'" + newComment + "'" +
                    "WHERE phoneNumber = " + "'" + phoneNumber + "'";
            stmt.executeUpdate(sql);
        }
        catch(Exception e){
            e.printStackTrace();
        }
    }

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

