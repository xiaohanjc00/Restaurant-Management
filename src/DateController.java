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
    LocalDate selectedDate;
    DatePicker date;


    public DateController() {
        createDatePicker();
    }

    public DatePicker createDatePicker() {
        date = new DatePicker(LocalDate.now());

        EventHandler<ActionEvent> event = new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent e) {
                selectedDate = date.getValue();
                System.out.println(selectedDate);
                System.out.println(getSelectedDate());
                System.out.println(currentDate());
                menu.observableClientList.clear();
                menu.database.startTable(getSelectedDate());
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
        return finalDate;
    }

    public String currentDate(){
        SimpleDateFormat formatter = new SimpleDateFormat("yyyyMMdd");
        Date date = new Date(System.currentTimeMillis());
        return(formatter.format(date));
    }

    public String dateSelecter(){
        return null;
    }
}
