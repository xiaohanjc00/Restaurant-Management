import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.*;
import javafx.stage.Stage;

public class menu extends Application {

    public static void main(String[] args) {
        launch(args);
    }

    @Override
    public void start(Stage primaryStage) {
        Pane root = new VBox();
        menuBar(root);
        tableView1(root);

        Scene scene = new Scene(root);
        primaryStage.setTitle("Restaurant Management");
        primaryStage.setScene(scene);
        primaryStage.show();
    }

    public void menuBar(Pane parent)
    {
        MenuBar menubar = new MenuBar();
        parent.getChildren().add(menubar);

        Menu toolsMenu = new Menu("Tools");
        Menu helpMenu = new Menu("Help");
        Menu aboutMenu = new Menu("About");

        menubar.getMenus().addAll(toolsMenu,helpMenu,aboutMenu);
    }

    public void tableView1(Pane parent)
    {
        TableView<Client> table1 = new TableView();
        parent.getChildren().add(table1);

        TableColumn<Client,String> column1 = new TableColumn<>("name");
        column1.setCellValueFactory(new PropertyValueFactory<Client, String>("name"));

        TableColumn<Client, Integer> column2 = new TableColumn<>("Table Number");
        column2.setCellValueFactory(new PropertyValueFactory<Client, Integer>("tableNumber"));

        TableColumn<Client, Integer> column3 = new TableColumn<>("Number of Person");
        column3.setCellValueFactory(new PropertyValueFactory<Client, Integer>("nnumberOfPerson"));

        TableColumn<Client, Integer> column4 = new TableColumn<>("Phone Number");
        column4.setCellValueFactory(new PropertyValueFactory<Client, Integer>("phoneNumber"));

        table1.getColumns().addAll(column1, column2, column3, column4);
    }
}
