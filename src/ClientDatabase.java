import java.sql.*;

public class ClientDatabase {
    // JDBC driver name and database URL
    static final String JDBC_DRIVER = "org.h2.Driver";
    static final String DB_URL = "jdbc:h2:~/test40";

    //  Database credentials
    static final String USER = "sa";
    static final String PASS = "";

    private Client client;

    static Connection conn = null;
    static Statement stmt = null;

    public static void main(String[] args) {
        createDatabase();
        test();
        printClients();
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
            System.out.println("Created table in given database...");

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

            String sql = "select * from CLIENT";
            stmt.executeQuery(sql);
            System.out.println("Processing...");
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
            String sql2 = "INSERT INTO CLIENT VALUES (" + "'" +
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

