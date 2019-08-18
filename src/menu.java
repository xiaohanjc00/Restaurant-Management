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
import javafx.scene.control.cell.TextFieldTableCell;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Pane;
import javafx.scene.layout.VBox;
import javafx.scene.text.Font;
import javafx.stage.Stage;
import javafx.util.converter.IntegerStringConverter;
import javafx.util.converter.LocalDateStringConverter;
import javafx.util.converter.LongStringConverter;
import org.h2.Driver;
import java.io.File;
import java.time.LocalDate;


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
    static TableView<Client> table1 = new TableView<>();
    static ObservableList<Client> observableClientList;
    String informationName;
    int informationTableNumber;
    int informationNumberOfPerson;
    long informationPhoneNumber;
    String informationComment;
    Label clientName;
    Label clientNumberOfPerson;
    Label clientTableNumber;
    Label clientPhoneNumber;
    Label clientComment;
    static ClientDatabase database;
    static DateController datePicker;
    static String suffix;
    static boolean isDay = true;

    @Override
    public void start(Stage primaryStage) {
        //Creation of the root pane
        Pane root = new HBox();
        //Creation of the two subpanels
            Pane tablePanel = new VBox();
            Pane informationPanel = new VBox();
         root.getChildren().addAll(tablePanel, informationPanel);

         //tablePanel components
        menuBar(tablePanel);      //Add menuBar
        createDatePicker(tablePanel); //Add date picker
        tableView1(tablePanel);   //Add tableView
        makeHorizontalPane(tablePanel);   //Add fieldsPane

        //informationPanel components
        createInformationPanel(informationPanel);  //Add information Panel

        //Creation of ClientList and observableClientList
        clientList = new ClientList();
        observableClientList = FXCollections.observableList(clientList.showClientList());

        //Creation of the Client database
        database = new ClientDatabase();
        database.createDatabase();  //create the database tables
        database.createSecondaryDatabase();
        database.startTable(datePicker.currentDate());      //start the tableview showing the values of the current date

        //Start table
        table1.setItems(observableClientList);  //set items from the oservablelist to the tableview
        table1.refresh();   //refresh tables for recent changes

        //Creation of the scene
        Scene scene = new Scene(root);
        scene.getStylesheets().add("edit.css");
        primaryStage.setTitle("Restaurant Management");
        primaryStage.setScene(scene);
        primaryStage.setMinWidth(1000);
        primaryStage.show();
    }

    /**
     * Add new clients to the client observable list
     * @param client
     */
    public static void addToObservableList(Client client){
        observableClientList.add(client);
    }

    /**
     * Create date picker
     * @param parent
     */
    public void createDatePicker(Pane parent){
        Pane dateBox = new HBox();

        datePicker = new DateController();

        Button dayButton = new Button("Day");
        dayButton.setOnAction(this::makeDay);
        Button nightButton = new Button("Night");
        nightButton.setOnAction(this::makeNight);

        dateBox.getChildren().addAll(datePicker.createDatePicker(),dayButton,nightButton);
        ((HBox) dateBox).setSpacing(20);

        suffix = datePicker.currentDate();  //set suffix to current date for the first time initialising the date controller

        parent.getChildren().add(dateBox);
    }

    /**
     * Create information panel
     * @param parent
     */
    public void createInformationPanel(Pane parent){

        Pane mainInformationBox = new VBox();   //Level 1
        parent.getChildren().add(mainInformationBox);

        Label informationTitle = new Label("INFORMATION");  //Level 2
        informationTitle.setFont(new Font("Potra", 20));

        Pane nameLine = new HBox(); //Level 2
        Label fixedName = new Label("Name:");   //Level 3
        clientName = new Label(informationName);    //Level 3
        nameLine.getChildren().addAll(fixedName, clientName);
        ((HBox) nameLine).setSpacing(20);

        Pane numberOfPersonLine = new HBox();   //Level 2
        Label fixedNumberOfPerson = new Label("Number of Person:"); //Level 3
        clientNumberOfPerson = new Label(informationNumberOfPerson + " ");  //Level 3
        numberOfPersonLine.getChildren().addAll(fixedNumberOfPerson, clientNumberOfPerson);
        ((HBox) numberOfPersonLine).setSpacing(20);

        Pane tableNumberLine = new HBox();  //Level 2
        Label fixedTableNumber = new Label("Table Number:");    //Level 3
        clientTableNumber = new Label(informationTableNumber + " ");    //Level 3
        tableNumberLine.getChildren().addAll(fixedTableNumber, clientTableNumber);
        ((HBox) tableNumberLine).setSpacing(20);

        Pane phoneNumberLine = new HBox();  //Level 2
        Label fixedPhoneNumber = new Label("Phone Number:");    //Level 3
        clientPhoneNumber = new Label(informationPhoneNumber + " ");    //Level 3
        phoneNumberLine.getChildren().addAll(fixedPhoneNumber, clientPhoneNumber);
        ((HBox) phoneNumberLine).setSpacing(20);

        Pane commentLine = new HBox();  //Level 2
        Label fixedComment = new Label("Comment:"); //Level 3
        clientComment = new Label(informationComment);  //Level 3
        commentLine.getChildren().addAll(fixedComment, clientComment);
        ((HBox) commentLine).setSpacing(20);

        Button deleteButton = new Button("Delete"); //Level 2
        deleteButton.setPrefWidth(70);
        deleteButton.setPrefHeight(20);
        deleteButton.setOnAction(this::deleteClient);   //Set delete client action


        ((VBox) mainInformationBox).setSpacing(30);

        mainInformationBox.getChildren().addAll(informationTitle, nameLine, tableNumberLine, numberOfPersonLine, phoneNumberLine, commentLine, deleteButton);
        mainInformationBox.setPadding(new Insets(20,0,0,20));
    }

    /**
     * Creation of the top menu bar
     * @param parent
     */
    public void menuBar(Pane parent)
    {
        //Creation of the menubar
        MenuBar menubar = new MenuBar();
        parent.getChildren().add(menubar);

        //Add tools, help and about menus
        Menu toolsMenu = new Menu("Tools");
        Menu helpMenu = new Menu("Help");
        Menu aboutMenu = new Menu("About");

        menubar.getMenus().addAll(toolsMenu,helpMenu,aboutMenu);
    }

    /**
     * Creation of the tableview
     * @param parent
     */
    public void tableView1(Pane parent)
    {
        //Creation of the tableView
        parent.getChildren().add(table1);   //add to parent
        table1.setColumnResizePolicy(TableView.CONSTRAINED_RESIZE_POLICY);

        table1.setEditable(true);

        //Name column
        TableColumn<Client, String> column1 = new TableColumn<>("name");
        column1.setCellValueFactory(new PropertyValueFactory<>("name"));

        column1.setCellFactory(TextFieldTableCell.<Client>forTableColumn());    //Create editing cell
        column1.setOnEditCommit(
                (TableColumn.CellEditEvent<Client,String> t) -> {
                    ((Client) t.getTableView().getItems().get(t.getTablePosition().getRow())
                    ).setName(t.getNewValue());
                    setLabels();    //Set information panel labels

                    suffix = datePicker.getSelectedDate();  //Set suffix as the selected date from the date picker
                    //Make the changes to the database
                    database.editName(t.getTableView().getItems().get(t.getTablePosition().getRow()).getName(), t.getTableView().getItems().get(t.getTablePosition().getRow()).getPhoneNumber());
                    database.printClients();    //Print out the current clients in the table
                }
        );

        //Table number column
        TableColumn<Client, Integer> column2 = new TableColumn<>("Table Number");
        column2.setCellValueFactory(new PropertyValueFactory<>("tableNumber"));

        column2.setCellFactory(TextFieldTableCell.<Client, Integer>forTableColumn(new IntegerStringConverter()));   //Create editing cell
        column2.setOnEditCommit(
                (TableColumn.CellEditEvent<Client,Integer> t) -> {
                    ((Client) t.getTableView().getItems().get(t.getTablePosition().getRow())
                    ).setTableNumber(Integer.parseInt(String.valueOf(t.getNewValue())));
                    setLabels();    // Set information panel labels

                    suffix = datePicker.getSelectedDate();  //Set suffix as the selected date from the date picker
                    //Make the changes to the database
                    database.editTableNumber(t.getTableView().getItems().get(t.getTablePosition().getRow()).getTableNumber(), t.getTableView().getItems().get(t.getTablePosition().getRow()).getPhoneNumber());
                    database.printClients();
                }
        );

        //Number of person column
        TableColumn<Client, Integer> column3 = new TableColumn<>("Nº of Person");
        column3.setCellValueFactory(new PropertyValueFactory<>("numberOfPerson"));

        column3.setCellFactory(TextFieldTableCell.<Client, Integer>forTableColumn(new IntegerStringConverter()));   //Create editing cell
        column3.setOnEditCommit(
                (TableColumn.CellEditEvent<Client,Integer> t) -> {
                    ((Client) t.getTableView().getItems().get(t.getTablePosition().getRow())
                    ).setNumberOfPerson(Integer.parseInt(String.valueOf(t.getNewValue())));
                    setLabels();    //Set information panel labels

                    suffix = datePicker.getSelectedDate();  //Set suffix as the selected date from the date picker
                    //Make the changes to the database
                    database.editNumberOfPerson(t.getTableView().getItems().get(t.getTablePosition().getRow()).getNumberOfPerson(), t.getTableView().getItems().get(t.getTablePosition().getRow()).getPhoneNumber());
                    database.printClients();
                }
        );

        //Phone number column
        TableColumn<Client, Long> column4 = new TableColumn<>("Phone Number");
        column4.setCellValueFactory(new PropertyValueFactory<>("phoneNumber"));

        column4.setCellFactory(TextFieldTableCell.<Client, Long>forTableColumn(new LongStringConverter()));   //Create editing cell
        column4.setOnEditCommit(
                (TableColumn.CellEditEvent<Client,Long> t) -> {
                    ((Client) t.getTableView().getItems().get(t.getTablePosition().getRow())
                    ).setPhoneNumber(Long.parseLong(String.valueOf( t.getNewValue())));
                    setLabels();    //Set information panel labels

                    suffix = datePicker.getSelectedDate();  //Set suffix as the selected date from the date picker
                    //Make the changes to the database
                    database.editPhoneNumber(t.getTableView().getItems().get(t.getTablePosition().getRow()).getPhoneNumber(),
                            t.getTableView().getItems().get(t.getTablePosition().getRow()).getName(),
                            t.getTableView().getItems().get(t.getTablePosition().getRow()).getTableNumber(),
                            t.getTableView().getItems().get(t.getTablePosition().getRow()).getNumberOfPerson(),
                            t.getTableView().getItems().get(t.getTablePosition().getRow()).getComment());
                    database.printClients();
                }
        );

        //Comment column
        TableColumn<Client, String> column5 = new TableColumn<>("Comment");
        column5.setCellValueFactory(new PropertyValueFactory<>("comment"));

        column5.setCellFactory(TextFieldTableCell.<Client>forTableColumn());    //Create editing cell
        column5.setOnEditCommit(
                (TableColumn.CellEditEvent<Client,String> t) -> {
                    ((Client) t.getTableView().getItems().get(t.getTablePosition().getRow())
                    ).setComment(t.getNewValue());
                    setLabels();    //Set information panel labels

                    suffix = datePicker.getSelectedDate();  //Set suffix as the selected date from the date picker
                    //Make the changes to the database
                    database.editComment(t.getTableView().getItems().get(t.getTablePosition().getRow()).getComment(), t.getTableView().getItems().get(t.getTablePosition().getRow()).getPhoneNumber());
                    database.printClients();
                }
        );
        //Add all columns to the table view
        table1.getColumns().addAll(column1,column2,column3,column4,column5);

        //When press a cell make editing cell true
        table1.setOnMousePressed(
                new EventHandler<javafx.scene.input.MouseEvent>() {
                    @Override
                    public void handle(javafx.scene.input.MouseEvent mouseEvent) {
                        if (mouseEvent.getClickCount() == 1) {
                            if (table1.getSelectionModel().isEmpty()) {  //Table cell selected is empty
                                informationName = null;
                                informationPhoneNumber = 1;
                                informationTableNumber = 0;
                                informationNumberOfPerson = 0;
                                informationComment = null;

                            } else {  //Selected a populated cell
                                informationName = table1.getSelectionModel().getSelectedItem().getName();
                                informationNumberOfPerson = table1.getSelectionModel().getSelectedItem().getNumberOfPerson();
                                informationTableNumber = table1.getSelectionModel().getSelectedItem().getTableNumber();
                                informationPhoneNumber = table1.getSelectionModel().getSelectedItem().getPhoneNumber();
                                informationComment = table1.getSelectionModel().getSelectedItem().getComment();
                            }
                            setLabels();    //Set information panel labels
                        }
                    }
                }
        );
    }

    /**
     * Creation of the horizontal pane for adding new clients
     * @param parent
     */
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

    /**
     * Set the new values of the fields to its labels in the information panel
     */
    public void setLabels()
    {
        clientName.setText(informationName);
        clientNumberOfPerson.setText(informationNumberOfPerson + " ");
        clientTableNumber.setText(informationTableNumber + " ");
        clientPhoneNumber.setText(informationPhoneNumber + " ");
        clientComment.setText(informationComment );
    }

    public boolean getisDay(){
        return isDay;
    }

    //ACTIONS

    /**
     * Adding client action
     * @param actionEvent
     */
    private void addClient(ActionEvent actionEvent) {   //"\""
        try {
            //Create new Client
            String newName = (nameField.getText());
            String newComment = (commentField.getText());
            int newTableNumber = Integer.parseInt(tableNumberField.getText());
            int newNumberOfPerson = Integer.parseInt(numberOfPersonField.getText());
            long newPhoneNumber = Long.parseLong(phoneNumberField.getText());
            Client newClient = new Client(newName, newTableNumber, newNumberOfPerson, newPhoneNumber, newComment);

            //Add the new client to it corresponding database table
            database.addClient(newClient, datePicker.getSelectedDate());

            database.printClients();    //Print out the clients in the current database table

            //Add new client to the observableList and set tables to appear the values from the list
            observableClientList.add(newClient);
            System.out.println(observableClientList.size());
            System.out.println(tableNumberField.getText());
            table1.setItems(observableClientList);
            table1.refresh();   //Refresh the items appearing in the table

            //Clear fields
            tableNumberField.clear();
            numberOfPersonField.clear();
            nameField.clear();
            phoneNumberField.clear();
            commentField.clear();
        }
        catch (Exception e) {
            Alert alert = new Alert(Alert.AlertType.WARNING);
            alert.setHeaderText(null);
            alert.setContentText("Please enter valid values");
            e.printStackTrace();

            alert.showAndWait();
        }
    }

    /**
     * Editing cell action
     * why cannot separate this event into an independent event instead of being inside the tableview method
     * @param event
     */
    public void cellEditing(MouseEvent event){
        new EventHandler<javafx.scene.input.MouseEvent>() {
            @Override
            public void handle(javafx.scene.input.MouseEvent mouseEvent) {
                if (mouseEvent.getClickCount() == 1) {
                    if (table1.getSelectionModel().isEmpty()) {  //Table cell selected is empty
                        informationName = null;
                        informationPhoneNumber = 1;
                        informationTableNumber = 0;
                        informationNumberOfPerson = 0;
                        informationComment = null;

                    } else {  //Selected a populated cell
                        informationName = table1.getSelectionModel().getSelectedItem().getName();
                        informationNumberOfPerson = table1.getSelectionModel().getSelectedItem().getNumberOfPerson();
                        informationTableNumber = table1.getSelectionModel().getSelectedItem().getTableNumber();
                        informationPhoneNumber = table1.getSelectionModel().getSelectedItem().getPhoneNumber();
                        informationComment = table1.getSelectionModel().getSelectedItem().getComment();
                    }
                    setLabels();    //Set information panel labels
                }
            }
        };
    }

    /**
     * Delete client action
     * @param event
     */
    private void deleteClient(ActionEvent event){
        try{
            //Recognise the selected client
            Client deletedClient = table1.getSelectionModel().getSelectedItem();
            //Add the client to the deleteclient database table and delete it from the original database table
            database.deleteClient(deletedClient, datePicker.getSelectedDate());

            //Print the clients left in the original database table
            database.printClients();
            //Print all the clients in the deletedclient database table
            database.printDeletedClients();

            //Remove the deleted client form the observable list
            observableClientList.remove(table1.getSelectionModel().getSelectedItem());

            table1.refresh();   //Refresh the table to make the deleted client disappear

            //Reset the information panel values
            informationName = null;
            informationComment = null;
            informationNumberOfPerson = 0;
            informationPhoneNumber = 0;
            informationTableNumber = 0;

            setLabels();    //Show information panel new values
        }
        catch(Exception e){
            Alert alert = new Alert(Alert.AlertType.WARNING);
            alert.setTitle("WARNING");
            alert.setHeaderText(null);
            alert.setContentText("Error");
        }
    }

    private void makeDay(ActionEvent event){
        isDay = true;
        System.out.println(datePicker.selectedDate);
        //System.out.println(getSelectedDate());
        //System.out.println(currentDate());
        menu.observableClientList.clear();
        menu.database.startTable(datePicker.getSelectedDate());
    }

    private void makeNight(ActionEvent event){
        isDay = false;
        System.out.println(datePicker.selectedDate);
        //System.out.println(getSelectedDate());
        //System.out.println(currentDate());
        menu.observableClientList.clear();
        menu.database.startTable(datePicker.getSelectedDate());
    }
}
