import javafx.application.Application;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.*;
import javafx.scene.text.Font;
import javafx.stage.Stage;


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
    TableView<Client> table1 = new TableView<>();
    private static ObservableList<Client> observableClientList;
    String informationName;
    Label clientName;

    @Override
    public void start(Stage primaryStage) {
        //Creation of the root pane
        Pane root = new HBox();
        Pane tablePanel = new VBox();
        Pane informationPanel = new VBox();
        root.getChildren().addAll(tablePanel, informationPanel);
        menuBar(tablePanel);      //Add menuBar
        tableView1(tablePanel);   //Add tableView
        makeHorizontalPane(tablePanel);   //Add fieldsPane
        createInformationPanel(informationPanel);  //Add information Panel

        //Creation of ClientList and observableClientList
        clientList = new ClientList();
        observableClientList = FXCollections.observableList(clientList.showClientList());

        //Creation of the scene
        Scene scene = new Scene(root);
        scene.getStylesheets().add("edit.css");
        primaryStage.setTitle("Restaurant Management");
        primaryStage.setScene(scene);
        primaryStage.setMinWidth(1000);
        primaryStage.show();
    }

    public void createInformationPanel(Pane parent){

        Pane mainInformationBox = new VBox();
        parent.getChildren().add(mainInformationBox);

        Label informationTitle = new Label("INFORMATION");
        informationTitle.setFont(new Font("Potra", 20));
        clientName = new Label(informationName);
        ((VBox) mainInformationBox).setSpacing(50);

        mainInformationBox.getChildren().addAll(informationTitle, clientName);
        mainInformationBox.setPadding(new Insets(20,0,0,20));
    }


    public void menuBar(Pane parent)
    {
        //Creation of the menubar
        MenuBar menubar = new MenuBar();
        parent.getChildren().add(menubar);

        //Add tools, help and about menus
        Menu toolsMenu = new Menu("Tools");
        Menu helpMenu = new Menu("Help");
        Menu aboutMenu = new Menu("About");
        Menu deleteMenu = new Menu("Delete");
        Menu editMenu = new Menu("Edit");

        menubar.getMenus().addAll(toolsMenu,helpMenu,aboutMenu, deleteMenu, editMenu);
    }


    public void tableView1(Pane parent)
    {
        //Creation of the tableView
        parent.getChildren().add(table1);   //add to parent
        table1.setColumnResizePolicy(TableView.CONSTRAINED_RESIZE_POLICY);

        //Name column
        TableColumn<Client, String> column1 = new TableColumn<>("name");
        column1.setCellValueFactory(new PropertyValueFactory<>("name"));
        //Table number column
        TableColumn<Client, Integer> column2 = new TableColumn<>("Table Number");
        column2.setCellValueFactory(new PropertyValueFactory<>("tableNumber"));
        //Number of person column
        TableColumn<Client, Integer> column3 = new TableColumn<>("Nº of Person");
        column3.setCellValueFactory(new PropertyValueFactory<>("numberOfPerson"));
        //Phone number column
        TableColumn<Client, Integer> column4 = new TableColumn<>("Phone Number");
        column4.setCellValueFactory(new PropertyValueFactory<>("phoneNumber"));
        //Comment column
        TableColumn<Client, String> column5 = new TableColumn<>("Comment");
        column5.setCellValueFactory(new PropertyValueFactory<>("comment"));


        table1.getColumns().addAll(column2,column3,column1,column4,column5);


        table1.setOnMousePressed(new EventHandler<javafx.scene.input.MouseEvent>() {
            @Override
            public void handle(javafx.scene.input.MouseEvent mouseEvent) {
                if(mouseEvent.getClickCount() == 1){
                    if(table1.getSelectionModel().isEmpty()) { informationName = null;}
                    else {informationName = table1.getSelectionModel().getSelectedItem().getName();}
                    clientName.setText(informationName);
                }
            }
        });
    }

    public void makeHorizontalPane(Pane parent)
    {
        //Main pane
        Pane mainBox = new HBox();
        mainBox.setPadding(new Insets(10,20,20,50));

        //VBox for the information fields
        Pane fieldBox = new VBox();

        //First line for the field box - include name, phone number, table number
        Pane firstLineInformation = new HBox();
        firstLineInformation.setPadding(new Insets(5));

        //Add all panes to parent
        parent.getChildren().addAll(mainBox, fieldBox);

        //Create fields for the creation of new clients
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

        //Create Add button
        Button addButton = new Button("Add");
        addButton.setPrefWidth(100);
        addButton.setPrefHeight(60);
        addButton.setOnAction(this::addClient);

        //Add all components to its pane
        firstLineInformation.getChildren().addAll(nameField, tableNumberField, numberOfPersonField, phoneNumberField);
        fieldBox.getChildren().addAll(firstLineInformation, commentField);
        mainBox.getChildren().addAll(fieldBox, addButton);

        //Set spacing
        ((HBox) mainBox).setSpacing(30);
        ((HBox) firstLineInformation).setSpacing(5);
    }


    private void addClient(ActionEvent actionEvent) {   //"\""
        try {
            //Create new Client
            String newName = (nameField.getText());
            String newComment = (commentField.getText());
            int newTableNumber = Integer.parseInt(tableNumberField.getText());
            int newNumberOfPerson = Integer.parseInt(numberOfPersonField.getText());
            int newPhoneNumber = Integer.parseInt(phoneNumberField.getText());
            Client newClient = new Client(newTableNumber, newNumberOfPerson, newName, newPhoneNumber, newComment);

            //Add new client to the observableList and set tables to appear the values from the list
            observableClientList.add(newClient);
            table1.setItems(observableClientList);
            table1.refresh();   //Refresh the items appearing in the table

            //Clear fields
            tableNumberField.clear();
            numberOfPersonField.clear();
            nameField.clear();
            phoneNumberField.clear();
            commentField.clear();
        } catch (Exception e) {
            Alert alert = new Alert(Alert.AlertType.WARNING);
            alert.setTitle("WARNING");
            alert.setHeaderText(null);
            alert.setContentText("Please enter valid values");

            alert.showAndWait();
        }

    }

}
