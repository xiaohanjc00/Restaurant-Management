import javafx.application.Application;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.*;
import javafx.stage.Stage;

import javax.print.DocFlavor;
import java.sql.Driver;
import java.util.List;

public class menu extends Application {

    public static void main(String[] args) {
        launch(args);
    }

    TextField nameField = new TextField();
    TextField tableNumberField = new TextField();
    TextField numberOfPersonField = new TextField();
    TextField phoneNumberField = new TextField();
    TextField commentField = new TextField();
    ClientList clientList;
    TableView<Client> table1;

    @Override
    public void start(Stage primaryStage) {
        Pane root = new VBox();
        menuBar(root);
        tableView1(root);
        makeHorizontalPane(root);
        clientList = new ClientList();

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
        table1 = new TableView();
        parent.getChildren().add(table1);
        table1.setColumnResizePolicy(TableView.CONSTRAINED_RESIZE_POLICY);

        TableColumn<Client, String> column1 = new TableColumn<>("name");
        column1.setCellValueFactory(new PropertyValueFactory<>("name"));

        TableColumn<Client, Integer> column2 = new TableColumn<>("Table Number");
        column2.setCellValueFactory(new PropertyValueFactory<>("tableNumber"));

        TableColumn<Client, Integer> column3 = new TableColumn<>("Nº of Person");
        column3.setCellValueFactory(new PropertyValueFactory<>("numberOfPerson"));

        TableColumn<Client, Integer> column4 = new TableColumn<>("Phone Number");
        column4.setCellValueFactory(new PropertyValueFactory<>("phoneNumber"));

        TableColumn<Client, String> column5 = new TableColumn<>("Comment");
        column5.setCellValueFactory(new PropertyValueFactory<>("comment"));

        table1.getColumns().addAll(column1,column2,column3,column4,column5);
    }

    public void makeHorizontalPane(Pane parent)
    {
        Pane accessBox = new HBox();
        accessBox.setPadding(new Insets(10,20,20,50));


        Pane informationBox = new VBox();

        Pane firstLineInformation = new HBox();
        firstLineInformation.setPadding(new Insets(5));

        parent.getChildren().addAll(accessBox, informationBox);


        nameField.setPromptText("Name");
        nameField.setAlignment(Pos.CENTER);


        tableNumberField.setPromptText("Table Number");
        tableNumberField.setPrefWidth(100);
        tableNumberField.setAlignment(Pos.CENTER);


        numberOfPersonField.setPromptText("Nº of Person");
        numberOfPersonField.setPrefWidth(100);
        numberOfPersonField.setAlignment(Pos.CENTER);


        phoneNumberField.setPromptText("Phone Number");
        phoneNumberField.setAlignment(Pos.CENTER);


        commentField.setPromptText("Comment");
        commentField.setAlignment(Pos.CENTER);

        Button addButton = new Button("Add");
        addButton.setPrefWidth(100);
        addButton.setPrefHeight(60);
        addButton.setOnAction(this::addClient);

        //add all components to its pane
        firstLineInformation.getChildren().addAll(nameField, tableNumberField, numberOfPersonField, phoneNumberField);
        informationBox.getChildren().addAll(firstLineInformation, commentField);
        accessBox.getChildren().addAll(informationBox, addButton);

        //Set spacing
        ((HBox) accessBox).setSpacing(30);
        ((HBox) firstLineInformation).setSpacing(5);


    }


    private void addClient(ActionEvent actionEvent)
    {
        int newTableNumber = Integer.parseInt(tableNumberField.getText());
        int newNumberOfPerson = Integer.parseInt(numberOfPersonField.getText());
        int newPhoneNumber = Integer.parseInt(phoneNumberField.getText());
        Client newClient = new Client(newTableNumber,newNumberOfPerson,nameField.getText(),newPhoneNumber,commentField.getText());
        clientList.addClientToList(newClient);
        table1.getItems().add(newClient);
        table1.refresh();
    }

}
