import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.control.DatePicker;
import javafx.scene.layout.Pane;

import java.time.LocalDate;

public class DateController {
    LocalDate selectedDate;
    DatePicker date;

    public DateController() {

    }

    public DatePicker createDatePicker() {
        date = new DatePicker();

        EventHandler<ActionEvent> event = new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent e) {
                selectedDate = date.getValue();
                System.out.println(selectedDate);
            }
        };

        date.setOnAction(event);
        return date;
    }
}

