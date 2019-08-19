import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.control.DatePicker;
import javafx.scene.layout.Pane;

import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Date;

public class DateController {
    static LocalDate selectedDate;
    DatePicker date;


    public DateController() {

    }

    public static String getDayOfWeek(){
       return String.valueOf(selectedDate.getDayOfWeek());
    }
    public static String getDay(){
        String newDate = String.valueOf(selectedDate);
        return newDate.substring(8, 10);
    }
    public static String getMonth() {
        return String.valueOf(selectedDate.getMonth());
    }
    public static String getYear() {
        String newDate = String.valueOf(selectedDate);
        return newDate.substring(0, 4);
    }

    public void startDatePicker(){
        date = new DatePicker(LocalDate.now());
        selectedDate = date.getValue();
    }

    public DatePicker createDatePicker() {
        date = new DatePicker(LocalDate.now());

        EventHandler<ActionEvent> event = new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent e) {
                menu.isDay = true;
                selectedDate = date.getValue();
                System.out.println(selectedDate);
                System.out.println(getSelectedDate());
                System.out.println(selectedDate.getDayOfWeek());
                //System.out.println(currentDate());
                menu.observableClientList.clear();
                menu.database.startTable(getSelectedDate());
                setDateText();
                menu.setAddingTimeRange();
            }
        };
        date.setOnAction(event);
        return date;
    }

    public String getSelectedDate() {
        String finalDate = null;
        try{
            String newDate = String.valueOf(selectedDate);
            String sub1 = newDate.substring(0, 4);
            String sub2 = newDate.substring(5, 7);
            String sub3 = newDate.substring(8, 10);
            finalDate = (sub1 + sub2 + sub3);}
        catch(Exception e){
            SimpleDateFormat formatter = new SimpleDateFormat("yyyyMMdd");
            Date date = new Date(System.currentTimeMillis());
            finalDate = (formatter.format(date));
        }
        if(menu.isDay == true){
            finalDate = finalDate + "D";
        }
        else{
            finalDate = finalDate + "N";
        }
        return finalDate;
    }

    public String currentDate(){
        SimpleDateFormat formatter = new SimpleDateFormat("yyyyMMdd");
        Date date = new Date(System.currentTimeMillis());
        String finalDate = formatter.format(date);
        if(menu.isDay == true){
            finalDate = finalDate + "D";
        }
        else{
            finalDate = finalDate + "N";
        }
        return finalDate;
    }


    public String dateSelecter(){
        return null;
    }

    public static String printTimeOfDay(){
        String finalPrint = null;
        if(menu.isDay == true){
            finalPrint = "DAY";
        }
        else{
            finalPrint = "NIGHT";
        }
        return finalPrint;
    }

    public static void setDateText(){
         menu.dateLabel.setText(getDayOfWeek() + " " +
                getDay() + " " +
                getMonth() + " " +
                getYear() + "   " +
                printTimeOfDay());
    }

    public  String printDateText(){
        return (getDayOfWeek() + " " +
                getDay() + " " +
                getMonth() + " " +
                getYear() + "   " +
                printTimeOfDay());
    }
}
