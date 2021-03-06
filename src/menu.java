import javafx.animation.Animation;
import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.application.Application;
import javafx.beans.InvalidationListener;
import javafx.beans.property.SimpleBooleanProperty;
import javafx.beans.property.SimpleObjectProperty;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.collections.ListChangeListener;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.event.Event;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.control.cell.CheckBoxTableCell;
import javafx.scene.control.cell.ComboBoxTableCell;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.control.cell.TextFieldTableCell;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Pane;
import javafx.scene.layout.VBox;
import javafx.scene.text.Font;
import javafx.stage.Stage;
import javafx.util.Callback;
import javafx.util.Duration;
import javafx.util.converter.IntegerStringConverter;
import javafx.util.converter.LongStringConverter;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.*;


public class menu extends Application {


    public static void main(String[] args) {
        launch(args);
    }

    TextField nameField = new TextField();
    TextField tableNumberField = new TextField();
    TextField numberOfPersonField = new TextField();
    TextField phoneNumberField = new TextField();
    TextField commentField = new TextField();
    static ComboBox timeChooser;
    ClientList clientList;
    static TableView<Client> table1 = new TableView<>();
    static ObservableList<Client> observableClientList;
    String informationName;
    int informationTableNumber;
    int informationNumberOfPerson;
    long informationPhoneNumber;
    String informationComment;
    String informationTime;
    Label clientName;
    Label clientNumberOfPerson;
    Label clientTableNumber;
    Label clientPhoneNumber;
    Label clientComment;
    Label clientTime;
    static ClientDatabase database;
    static DateController datePicker;
    static String suffix;
    static boolean isDay = true;
    static Label dateLabel;

    static TableColumn<Client, String> column6;

    static ObservableList<String> choiceBox;

    @Override
    public void start(Stage primaryStage) {
        //Creation of the root pane
        Pane root = new HBox();
        //Creation of the two subpanels
        Pane tablePanel = new VBox();
        Pane informationPanel = new VBox();
        root.getChildren().addAll(tablePanel, informationPanel);

        //tablePanel components
        createMenuBar(tablePanel);      //Add menuBar
        createDatePicker(tablePanel); //Add date picker
        createTableView(tablePanel);   //Add tableView
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
        primaryStage.setMinWidth(1050);
        primaryStage.show();

    }

    /**
     * Add new clients to the client observable list
     *
     * @param client
     */
    public static void addToObservableList(Client client) {
        observableClientList.add(client);
    }

    /**
     * Create date picker
     *
     * @param parent
     */
    public void createDatePicker(Pane parent) {
        Pane dateBox = new HBox();

        datePicker = new DateController();
        datePicker.createDatePicker();
        datePicker.startDatePicker();

        dateLabel = new Label(datePicker.printDateText());
        dateLabel.setFont(new Font("Potra", 20));
        dateLabel.setMaxWidth(390);
        dateLabel.setMinWidth(390);

        Button dayButton = new Button("Day  ");
        dayButton.setOnAction(this::makeDayAction);
        Button nightButton = new Button("Night");
        nightButton.setOnAction(this::makeNightAction);

        Pane buttonBox = new HBox();
        buttonBox.getChildren().addAll(dayButton, nightButton);

        dateBox.getChildren().addAll(datePicker.createDatePicker(), dateLabel, buttonBox);
        ((HBox) dateBox).setSpacing(60);

        suffix = datePicker.currentDate();  //set suffix to current date for the first time initialising the date controller
        //datePicker.startDatePicker();

        parent.getChildren().add(dateBox);
    }

    /**
     * Create information panel (Right panel)
     *
     * @param parent
     */
    public void createInformationPanel(Pane parent) {

        Pane mainInformationBox = new VBox();   //Level 1
        parent.getChildren().add(mainInformationBox);

        Label informationTitle = new Label("INFORMATION");  //Level 2
        informationTitle.setFont(new Font("Potra", 20));

        createClock(mainInformationBox); //Level 2

        Pane nameLine = new HBox(); //Level 2
        Label fixedName = new Label("Name:");   //Level 3
        fixedName.setMinWidth(100);
        fixedName.setMaxWidth(100);
        clientName = new Label(informationName);    //Level 3
        nameLine.getChildren().addAll(fixedName, clientName);
        ((HBox) nameLine).setSpacing(20);

        Pane numberOfPersonLine = new HBox();   //Level 2
        Label fixedNumberOfPerson = new Label("Number of Person:"); //Level 3
        fixedNumberOfPerson.setMinWidth(100);
        fixedNumberOfPerson.setMaxWidth(100);
        clientNumberOfPerson = new Label(informationNumberOfPerson + " ");  //Level 3
        numberOfPersonLine.getChildren().addAll(fixedNumberOfPerson, clientNumberOfPerson);
        ((HBox) numberOfPersonLine).setSpacing(20);

        Pane tableNumberLine = new HBox();  //Level 2
        Label fixedTableNumber = new Label("Table Number:");    //Level 3
        fixedTableNumber.setMinWidth(100);
        fixedTableNumber.setMaxWidth(100);
        clientTableNumber = new Label(informationTableNumber + " ");    //Level 3
        tableNumberLine.getChildren().addAll(fixedTableNumber, clientTableNumber);
        ((HBox) tableNumberLine).setSpacing(20);

        Pane phoneNumberLine = new HBox();  //Level 2
        Label fixedPhoneNumber = new Label("Phone Number:");    //Level 3
        fixedPhoneNumber.setMinWidth(100);
        fixedPhoneNumber.setMaxWidth(100);
        clientPhoneNumber = new Label(informationPhoneNumber + " ");    //Level 3
        phoneNumberLine.getChildren().addAll(fixedPhoneNumber, clientPhoneNumber);
        ((HBox) phoneNumberLine).setSpacing(20);

        Pane commentLine = new HBox();  //Level 2
        Label fixedComment = new Label("Comment:"); //Level 3
        fixedComment.setMinWidth(100);
        fixedComment.setMaxWidth(100);
        clientComment = new Label(informationComment);  //Level 3
        commentLine.getChildren().addAll(fixedComment, clientComment);
        ((HBox) commentLine).setSpacing(20);

        Pane timeLine = new HBox();  //Level 2
        Label fixedTime = new Label("Time:"); //Level 3
        fixedTime.setMinWidth(100);
        fixedTime.setMaxWidth(100);
        clientTime = new Label(informationComment);  //Level 3
        timeLine.getChildren().addAll(fixedTime, clientTime);
        ((HBox) timeLine).setSpacing(20);

        Button deleteButton = new Button("Delete"); //Level 2
        deleteButton.setPrefWidth(70);
        deleteButton.setPrefHeight(20);
        deleteButton.setOnAction(this::deleteClientAction);   //Set delete client action


        ((VBox) mainInformationBox).setSpacing(25);

        mainInformationBox.getChildren().addAll(informationTitle, nameLine, tableNumberLine, numberOfPersonLine, phoneNumberLine, commentLine, timeLine, deleteButton);
        mainInformationBox.setPadding(new Insets(0, 0, 0, 20));
    }

    public void createClock(Pane parent) {
        Label time = new Label();
        parent.getChildren().add(time);
        time.setFont(new Font("Potra", 30));
        time.setPadding(new Insets(20,0,0,0));

        Timeline clock = new Timeline(new KeyFrame(Duration.ZERO, e -> {
            DateTimeFormatter formatter = DateTimeFormatter.ofPattern("HH:mm:ss");
            time.setText(LocalDateTime.now().format(formatter));
        }),
                new KeyFrame(Duration.seconds(1))
        );
        clock.setCycleCount(Animation.INDEFINITE);
        clock.play();
}

    /**
     * Creation of the top menu bar
     * @param parent
     */
    public void createMenuBar(Pane parent)
    {
        //Creation of the menubar
        MenuBar menubar = new MenuBar();
        parent.getChildren().add(menubar);
        menubar.getStyleClass().add("menubar");

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
    public void createTableView(Pane parent)
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
                    database.editName(t.getTableView().getItems().get(t.getTablePosition().getRow()).getName(),
                            t.getTableView().getItems().get(t.getTablePosition().getRow()).getPhoneNumber());
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
                    database.editTableNumber(t.getTableView().getItems().get(t.getTablePosition().getRow()).getTableNumber(),
                            t.getTableView().getItems().get(t.getTablePosition().getRow()).getPhoneNumber());
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
                    database.editNumberOfPerson(t.getTableView().getItems().get(t.getTablePosition().getRow()).getNumberOfPerson(),
                            t.getTableView().getItems().get(t.getTablePosition().getRow()).getPhoneNumber());
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
                    database.editComment(t.getTableView().getItems().get(t.getTablePosition().getRow()).getComment(),
                            t.getTableView().getItems().get(t.getTablePosition().getRow()).getPhoneNumber());
                    database.printClients();
                }
        );

        column6 = new TableColumn<>("Time");
        column6.setCellValueFactory(new PropertyValueFactory<>("time"));
        choiceBox = FXCollections.observableList(createChoiceBoxList());
        column6.setCellFactory(ComboBoxTableCell.forTableColumn(choiceBox));
        column6.setOnEditCommit(
                (TableColumn.CellEditEvent<Client,String> t) -> {
                    ((Client) t.getTableView().getItems().get(t.getTablePosition().getRow())).setTime(t.getNewValue());
                    System.out.println(((Client) t.getTableView().getItems().get(t.getTablePosition().getRow())).getTime());
                    setLabels();    //Set information panel labels

                    suffix = datePicker.getSelectedDate();  //Set suffix as the selected date from the date picker
                    //Make the changes to the database
                    database.editTime(t.getTableView().getItems().get(t.getTablePosition().getRow()).getTime(),
                            t.getTableView().getItems().get(t.getTablePosition().getRow()).getPhoneNumber());
                    database.printClients();

                }
        );
        // "Selected" column
        TableColumn<Client, Boolean> column7 = new TableColumn<Client, Boolean>("selected");

        /*
        column7.setCellValueFactory(new Callback<TableColumn.CellDataFeatures<Client, Boolean>, ObservableValue<Boolean>>() {
        Client client0;
            @Override
            public ObservableValue<Boolean> call(TableColumn.CellDataFeatures<Client, Boolean> arrived) {
                    Client client0 = arrived.getValue();

                    CheckBox checkBox = new CheckBox();

                    checkBox.selectedProperty().setValue(client0.getArrived());



                    checkBox.selectedProperty().addListener(new ChangeListener<Boolean>() {
                        public void changed(ObservableValue<? extends Boolean> ov,
                                            Boolean old_val, Boolean new_val) {

                            client0.setArrived(new_val);

                        }
                    });
                    return client0.arrivedProperty();
            }

         */







        column7.setCellValueFactory(
                new Callback<TableColumn.CellDataFeatures<Client, Boolean>, ObservableValue<Boolean>>() {
                @Override
                    public ObservableValue<Boolean> call(TableColumn.CellDataFeatures<Client, Boolean> param) {


                        column7.setOnEditCommit(
                            (TableColumn.CellEditEvent<Client, Boolean> t) -> {
                                ((Client) t.getTableView().getItems().get(t.getTablePosition().getRow())
                                ).setArrived(t.getNewValue());
                                setLabels();    //Set information panel labels

                                suffix = datePicker.getSelectedDate();  //Set suffix as the selected date from the date picker
                                //Make the changes to the database
                                database.editArrived(t.getTableView().getItems().get(t.getTablePosition().getRow()).getArrived(),
                                        t.getTableView().getItems().get(t.getTablePosition().getRow()).getPhoneNumber());
                                database.printClients();
                            }
                        );

                    return param.getValue().arrivedProperty();

                }
         });


        column7.setCellValueFactory(cellData -> cellData.getValue().arrivedProperty());


        column7.setCellFactory(CheckBoxTableCell.forTableColumn(column7));


        ;






        //Add all columns to the table view
        table1.getColumns().addAll(column1,column2,column3,column4,column5, column6, column7);
        table1.getSortOrder().add(column6);

        //When press a cell make editing cell true
        tableViewAction();
    }


    public static List createChoiceBoxList() {
        List finalList = new ArrayList();
        List nightList = new ArrayList();
        nightList.add("20:30");
        nightList.add("20:45");
        nightList.add("21:00");
        nightList.add("21:15");
        nightList.add("21:30");
        nightList.add("21:45");
        nightList.add("22:00");
        nightList.add("22:15");
        nightList.add("22:30");
        nightList.add("22:45");
        nightList.add("23:00");
        nightList.add("23:15");
        nightList.add("23:30");
        nightList.add("23:45");
        nightList.add("24:00");

        List dayList = new ArrayList<>();
        dayList.add("13:30");
        dayList.add("13:45");
        dayList.add("14:00");
        dayList.add("14:15");
        dayList.add("14:30");
        dayList.add("14:45");
        dayList.add("15:00");
        dayList.add("15:15");
        dayList.add("15:30");
        dayList.add("15:45");
        dayList.add("16:00");

        if (isDay == true) {
            finalList = dayList;
        } else {
            finalList = nightList;
        }
        return finalList;
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
        Pane secondLineInformation = new HBox();
        secondLineInformation.setPadding(new Insets(5));

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


        timeChooser = new ComboBox(FXCollections.observableArrayList(createChoiceBoxList()));
        setAddingTimeRange();
        timeChooser.setPromptText("Time");

        //Create Add button
        Button addButton = new Button("Add");
        addButton.setPrefWidth(100);
        addButton.setPrefHeight(60);
        addButton.setOnAction(this::addClientAction);

        //Add all components to its pane
        firstLineInformation.getChildren().addAll(nameField, tableNumberField, numberOfPersonField, phoneNumberField);
        secondLineInformation.getChildren().addAll(commentField, timeChooser);
        fieldBox.getChildren().addAll(firstLineInformation, secondLineInformation);
        mainBox.getChildren().addAll(fieldBox, addButton);

        //Set spacing
        ((HBox) mainBox).setSpacing(30);
        ((HBox) firstLineInformation).setSpacing(5);
        ((HBox) secondLineInformation).setSpacing(5);
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
        clientTime.setText(informationTime);
    }

    public static void setColumnTimeRange(){
        choiceBox = FXCollections.observableList(createChoiceBoxList());
        column6.setCellFactory(ComboBoxTableCell.forTableColumn(choiceBox));
    }

    public static void setAddingTimeRange(){
        choiceBox = FXCollections.observableList(createChoiceBoxList());
        timeChooser.setItems(choiceBox);
    }


    //ACTIONS

    /**
     * Adding client action
     * @param actionEvent
     */
    private void addClientAction(ActionEvent actionEvent) {   //"\""
        try {
            //Create new Client
            String newName = (nameField.getText());
            String newComment = (commentField.getText());
            int newTableNumber = Integer.parseInt(tableNumberField.getText());
            int newNumberOfPerson = Integer.parseInt(numberOfPersonField.getText());
            long newPhoneNumber = Long.parseLong(phoneNumberField.getText());
            String newTime = timeChooser.getSelectionModel().getSelectedItem().toString();
            Client newClient = new Client(newName, newTableNumber, newNumberOfPerson, newPhoneNumber, newComment, newTime, false);

            //Add the new client to it corresponding database table
            database.addClient(newClient, datePicker.getSelectedDate());

            database.printClients();    //Print out the clients in the current database table

            //Add new client to the observableList and set tables to appear the values from the list
            observableClientList.add(newClient);
            System.out.println(observableClientList.size());
            System.out.println(tableNumberField.getText());
            table1.setItems(observableClientList);
            table1.getSortOrder().add(column6);
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
    public void cellEditingAction(MouseEvent event){
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
                        informationTime = null;

                    } else {  //Selected a populated cell
                        informationName = table1.getSelectionModel().getSelectedItem().getName();
                        informationNumberOfPerson = table1.getSelectionModel().getSelectedItem().getNumberOfPerson();
                        informationTableNumber = table1.getSelectionModel().getSelectedItem().getTableNumber();
                        informationPhoneNumber = table1.getSelectionModel().getSelectedItem().getPhoneNumber();
                        informationComment = table1.getSelectionModel().getSelectedItem().getComment();
                        informationTime = table1.getSelectionModel().getSelectedItem().getTime();
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
    private void deleteClientAction(ActionEvent event){
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
            table1.getSortOrder().add(column6);
            table1.refresh();   //Refresh the table to make the deleted client disappear

            //Reset the information panel values
            informationName = null;
            informationComment = null;
            informationNumberOfPerson = 0;
            informationPhoneNumber = 0;
            informationTableNumber = 0;
            informationTime = null;

            setLabels();    //Show information panel new values
        }
        catch(Exception e){
            Alert alert = new Alert(Alert.AlertType.WARNING);
            alert.setTitle("WARNING");
            alert.setHeaderText(null);
            alert.setContentText("Error");
        }
    }

    public void tableViewAction(){
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
                                informationTime = null;

                            } else {  //Selected a populated cell
                                informationName = table1.getSelectionModel().getSelectedItem().getName();
                                informationNumberOfPerson = table1.getSelectionModel().getSelectedItem().getNumberOfPerson();
                                informationTableNumber = table1.getSelectionModel().getSelectedItem().getTableNumber();
                                informationPhoneNumber = table1.getSelectionModel().getSelectedItem().getPhoneNumber();
                                informationComment = table1.getSelectionModel().getSelectedItem().getComment();
                                informationTime = table1.getSelectionModel().getSelectedItem().getTime();
                            }
                            setLabels();    //Set information panel labels
                        }
                    }
                });
    }

    private void makeDayAction(ActionEvent event){
        isDay = true;
        System.out.println(datePicker.selectedDate);
        //System.out.println(getSelectedDate());
        //System.out.println(currentDate());
        observableClientList.clear();
        database.startTable(datePicker.getSelectedDate());

        setColumnTimeRange();
        setAddingTimeRange();

        datePicker.setDateText();
    }

    private void makeNightAction(ActionEvent event){
        isDay = false;
        System.out.println(datePicker.selectedDate);
        //System.out.println(getSelectedDate());
        //System.out.println(currentDate());
        observableClientList.clear();
        database.startTable(datePicker.getSelectedDate());
        isDay = false;

        setColumnTimeRange();
        setAddingTimeRange();
        datePicker.setDateText();
    }
}

/*


                new Callback<TableColumn<Client, Boolean>, TableCell<Client, Boolean>>() {
                 @Override
                    public TableCell<Client, Boolean> call(TableColumn<Client, Boolean> booleanTableColumn) {
                        return new CheckBoxTableCell<>();
                    }
        });

 */
