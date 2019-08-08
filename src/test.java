import javafx.application.Application;
import javafx.geometry.Insets;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.layout.VBox;
import javafx.scene.text.Font;
import javafx.stage.Stage;

public class test extends Application { //Class for running the program

    private TableView table = new TableView();
    public static void main(String[] args) {
        launch(args);
    }

    @Override //Application class is abstract, need to be overridden
    public void start(Stage stage){

        Scene scene = new Scene(new Group()); //Create scene
        stage.setTitle("Table View Sample");
        stage.setWidth(300);
        stage.setHeight(500);

        final Label label = new Label("Address Book"); //Create title for the table
        label.setFont(new Font("Arial", 20));

        table.setEditable(true); //Set table editable

        TableColumn firstNameCol = new TableColumn("First Name"); //First column
        TableColumn lastNameCol = new TableColumn("Last Name"); //Second Column
        TableColumn emailCol = new TableColumn("Email"); //Third Column

        table.getColumns().addAll(firstNameCol, lastNameCol, emailCol);

        final VBox vbox = new VBox();  //Create a pane
        vbox.setSpacing(5);
        vbox.setPadding(new Insets(10,0,0,10));
        vbox.getChildren().addAll(label, table);

        ((Group) scene.getRoot()).getChildren().addAll(vbox); //add all panes

        stage.setScene(scene); //set scene for the stage
        stage.show(); //show the stage
    }

}











/**
    final Label label = new Label("Address Book");
        label.setFont(new Font("Arial", 20));

                table.setEditable(true);

                TableColumn firstNameCol = new TableColumn("First Name");
                TableColumn lastNameCol = new TableColumn("Last Name");
                TableColumn emailCol = new TableColumn("Email");

                table.getColumns().addAll(firstNameCol, lastNameCol, emailCol);

final VBox vbox = new VBox();
        vbox.setSpacing(5);
        vbox.setPadding(new Insets(10,0,0,10));
        vbox.getChildren().addAll(label, table);


*/