import java.util.HashMap;
/**
 * Write a description of class restaurant here.
 *
 * @author (your name)
 * @version (a version number or a date)
 */
public class Tables
{
    //Create HashMap of the tables available in the restaurant
    HashMap<Integer, Table> tables;
    
    /**
     * Constructor for objects of class restaurant
     */
    public Tables()
    {
        HashMap<Integer, Table> tables = new HashMap<Integer, Table>();
    
        //Create all the tables available in the restaurant
        Table table1 = new Table();
        Table table2 = new Table();
        Table table3 = new Table();
        Table table4 = new Table();
        Table table5 = new Table();
        Table table6 = new Table();
        Table table7 = new Table();
        Table table8 = new Table();
        Table table9 = new Table();
        Table table10 = new Table();
        Table table11 = new Table();
        Table table12 = new Table();
        Table table13 = new Table();
        Table table51 = new Table();
        Table table52 = new Table();
        Table table53 = new Table();
        Table table54 = new Table();
        Table table55 = new Table();
        Table table60= new Table();
        Table table100 = new Table();
        
        //Introduce all the tables into the HashMap
        tables.put(1, table1);
        tables.put(2, table2);
        tables.put(3, table3);
        tables.put(4, table4);
        tables.put(5, table5);
        tables.put(6, table6);
        tables.put(7, table7);
        tables.put(8, table8);
        tables.put(9, table9);
        tables.put(10, table10);
        tables.put(11, table11);
        tables.put(12, table12);
        tables.put(13, table13);
        tables.put(51, table51);
        tables.put(52, table52);
        tables.put(53, table53);
        tables.put(54, table54);
        tables.put(55, table55);
        tables.put(60, table60);
        tables.put(100, table100);
        
        //Change all the default number of people in each table accordingly
        table1.changeNumberOfPerson(2);
        table2.changeNumberOfPerson(2);
        table3.changeNumberOfPerson(4);
        table4.changeNumberOfPerson(4);
        table5.changeNumberOfPerson(4);
        table6.changeNumberOfPerson(4);
        table7.changeNumberOfPerson(4);
        table8.changeNumberOfPerson(4);
        table9.changeNumberOfPerson(2);
        table10.changeNumberOfPerson(2);
        table11.changeNumberOfPerson(2);
        table12.changeNumberOfPerson(4);
        table13.changeNumberOfPerson(4);
        table51.changeNumberOfPerson(2);
        table52.changeNumberOfPerson(4);
        table53.changeNumberOfPerson(4);
        table54.changeNumberOfPerson(2);
        table55.changeNumberOfPerson(2);
        table60.changeNumberOfPerson(2);
        table100.changeNumberOfPerson(2);
        
        table1.changeTableNumber(1);
        table2.changeTableNumber(2);
        table3.changeTableNumber(3);
        table4.changeTableNumber(4);
        table5.changeTableNumber(5);
        table6.changeTableNumber(6);
        table7.changeTableNumber(7);
        table8.changeTableNumber(8);
        table9.changeTableNumber(9);
        table10.changeTableNumber(10);
        table11.changeTableNumber(11);
        table12.changeTableNumber(12);
        table13.changeTableNumber(13);
        table51.changeTableNumber(51);
        table52.changeTableNumber(52);
        table53.changeTableNumber(53);
        table54.changeTableNumber(54);
        table55.changeTableNumber(55);
        table60.changeTableNumber(60);
        table100.changeTableNumber(100);
        
    }
    
    /**
     * @param the number of the table
     * @return The corresponding table to the table number
     */
    public Table searchTable(int unknownTable)
    {
        return tables.get(unknownTable);
    }

}
