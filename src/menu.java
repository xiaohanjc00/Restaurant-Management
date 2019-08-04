import javafx.application.Application;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
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
        makeHorizontalPane(root);

        Scene scene = new Scene(root);
        scene.getStylesheets().add("edit.css");
        primaryStage.setTitle("Restaurant Management");
        primaryStage.setScene(scene);
        primaryStage.setMinWidth(800);
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
        table1.setColumnResizePolicy(TableView.CONSTRAINED_RESIZE_POLICY);

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

    public void makeHorizontalPane(Pane parent)
    {
        Pane accessBox = new HBox();
        accessBox.setPadding(new Insets(20));
        parent.getChildren().add(accessBox);

        TextField nameField = new TextField();
        nameField.setPromptText("Name");
        //nameField.setPadding(new Insets(20));
        //nameField.setBorder(new Border(20));
        nameField.setAlignment(Pos.CENTER);

        TextField tableNumberField = new TextField();
        tableNumberField.setPromptText("Table Number");
        tableNumberField.setPrefWidth(100);
        tableNumberField.setAlignment(Pos.CENTER);

        TextField numberOfPersonField = new TextField();
        numberOfPersonField.setPromptText("Nº of Person");
        numberOfPersonField.setPrefWidth(100);
        numberOfPersonField.setAlignment(Pos.CENTER);

        TextField phoneNumberField = new TextField();
        phoneNumberField.setPromptText("Phone Number");
        phoneNumberField.setAlignment(Pos.CENTER);

        Button addButton = new Button("Add");
        addButton.setPrefWidth(100);
        addButton.setPrefHeight(50);

        accessBox.getChildren().addAll(nameField, tableNumberField, numberOfPersonField, phoneNumberField, addButton);
        ((HBox) accessBox).setSpacing(30);
    }
}
