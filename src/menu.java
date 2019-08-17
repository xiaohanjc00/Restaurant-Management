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
import javafx.scene.layout.HBox;
import javafx.scene.layout.Pane;
import javafx.scene.layout.VBox;
import javafx.scene.text.Font;
import javafx.stage.Stage;
import javafx.util.converter.IntegerStringConverter;
import javafx.util.converter.LocalDateStringConverter;
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
    int informationPhoneNumber;
    String informationComment;
    Label clientName;
    Label clientNumberOfPerson;
    Label clientTableNumber;
    Label clientPhoneNumber;
    Label clientComment;
    File clientFile;
    static ClientDatabase database;
    LocalDate selectedDate;
    static DateController datePicker;
    static String suffix;

    @Override
    public void start(Stage primaryStage) {
        //Creation of the root pane
        Pane root = new HBox();
        Pane tablePanel = new VBox();
        Pane informationPanel = new VBox();
        root.getChildren().addAll(tablePanel, informationPanel);
        menuBar(tablePanel);      //Add menuBar
        createDatePicker(tablePanel);
        tableView1(tablePanel);   //Add tableView
        makeHorizontalPane(tablePanel);   //Add fieldsPane
        createInformationPanel(informationPanel);  //Add information Panel
        //Creation of ClientList and observableClientList
        clientList = new ClientList();
        observableClientList = FXCollections.observableList(clientList.showClientList());


        database = new ClientDatabase();
        database.createDatabase();
        database.startTable(datePicker.currentDate());
        table1.setItems(observableClientList);
        table1.refresh();

        //Creation of the scene
        Scene scene = new Scene(root);
        scene.getStylesheets().add("edit.css");
        primaryStage.setTitle("Restaurant Management");
        primaryStage.setScene(scene);
        primaryStage.setMinWidth(1000);
        primaryStage.show();
    }

    public static void addToObservableList(Client client){
        observableClientList.add(client);
    }

    public void createDatePicker(Pane parent){
        datePicker = new DateController();
        parent.getChildren().add(datePicker.createDatePicker());
        suffix = datePicker.currentDate();
    }

    public void createInformationPanel(Pane parent){

        Pane mainInformationBox = new VBox();
        parent.getChildren().add(mainInformationBox);

        Label informationTitle = new Label("INFORMATION");
        informationTitle.setFont(new Font("Potra", 20));

        Pane nameLine = new HBox();
        Label fixedName = new Label("Name:");
        clientName = new Label(informationName);
        nameLine.getChildren().addAll(fixedName, clientName);
        ((HBox) nameLine).setSpacing(20);

        Pane numberOfPersonLine = new HBox();
        Label fixedNumberOfPerson = new Label("Number of Person:");
        clientNumberOfPerson = new Label(informationNumberOfPerson + " ");
        numberOfPersonLine.getChildren().addAll(fixedNumberOfPerson, clientNumberOfPerson);
        ((HBox) numberOfPersonLine).setSpacing(20);

        Pane tableNumberLine = new HBox();
        Label fixedTableNumber = new Label("Table Number:");
        clientTableNumber = new Label(informationTableNumber + " ");
        tableNumberLine.getChildren().addAll(fixedTableNumber, clientTableNumber);
        ((HBox) tableNumberLine).setSpacing(20);

        Pane phoneNumberLine = new HBox();
        Label fixedPhoneNumber = new Label("Phone Number:");
        clientPhoneNumber = new Label(informationPhoneNumber + " ");
        phoneNumberLine.getChildren().addAll(fixedPhoneNumber, clientPhoneNumber);
        ((HBox) phoneNumberLine).setSpacing(20);

        Pane commentLine = new HBox();
        Label fixedComment = new Label("Comment:");
        clientComment = new Label(informationComment);
        commentLine.getChildren().addAll(fixedComment, clientComment);
        ((HBox) commentLine).setSpacing(20);

        Pane editButtons = new HBox();

        Button deleteButton = new Button("Delete");
        deleteButton.setPrefWidth(70);
        deleteButton.setPrefHeight(20);
        deleteButton.setOnAction(this::deleteClient);


        editButtons.getChildren().addAll(deleteButton);
        ((HBox) editButtons).setSpacing(20);

        ((VBox) mainInformationBox).setSpacing(30);

        mainInformationBox.getChildren().addAll(informationTitle, nameLine, tableNumberLine, numberOfPersonLine, phoneNumberLine, commentLine, editButtons);
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

        menubar.getMenus().addAll(toolsMenu,helpMenu,aboutMenu);
    }


    public void tableView1(Pane parent)
    {
        //Creation of the tableView
        parent.getChildren().add(table1);   //add to parent
        table1.setColumnResizePolicy(TableView.CONSTRAINED_RESIZE_POLICY);

        table1.setEditable(true);

        //Name column
        TableColumn<Client, String> column1 = new TableColumn<>("name");
        column1.setCellValueFactory(new PropertyValueFactory<>("name"));

        column1.setCellFactory(TextFieldTableCell.<Client>forTableColumn());
        column1.setOnEditCommit(
                (TableColumn.CellEditEvent<Client,String> t) -> {
                    ((Client) t.getTableView().getItems().get(t.getTablePosition().getRow())
                    ).setName(t.getNewValue());
                    setLabels();
                    suffix = datePicker.getSelectedDate();
                    database.editName(t.getTableView().getItems().get(t.getTablePosition().getRow()).getName(), t.getTableView().getItems().get(t.getTablePosition().getRow()).getPhoneNumber());
                    database.printClients();
                }
        );

        //Table number column
        TableColumn<Client, Integer> column2 = new TableColumn<>("Table Number");
        column2.setCellValueFactory(new PropertyValueFactory<>("tableNumber"));

        column2.setCellFactory(TextFieldTableCell.<Client, Integer>forTableColumn(new IntegerStringConverter()));
        column2.setOnEditCommit(
                (TableColumn.CellEditEvent<Client,Integer> t) -> {
                    ((Client) t.getTableView().getItems().get(t.getTablePosition().getRow())
                    ).setTableNumber(Integer.parseInt(String.valueOf(t.getNewValue())));
                    setLabels();
                    suffix = datePicker.getSelectedDate();
                    database.editTableNumber(t.getTableView().getItems().get(t.getTablePosition().getRow()).getTableNumber(), t.getTableView().getItems().get(t.getTablePosition().getRow()).getPhoneNumber());
                    database.printClients();
                }
        );

        //Number of person column
        TableColumn<Client, Integer> column3 = new TableColumn<>("Nº of Person");
        column3.setCellValueFactory(new PropertyValueFactory<>("numberOfPerson"));

        column3.setCellFactory(TextFieldTableCell.<Client, Integer>forTableColumn(new IntegerStringConverter()));
        column3.setOnEditCommit(
                (TableColumn.CellEditEvent<Client,Integer> t) -> {
                    ((Client) t.getTableView().getItems().get(t.getTablePosition().getRow())
                    ).setNumberOfPerson(Integer.parseInt(String.valueOf(t.getNewValue())));
                    setLabels();
                    suffix = datePicker.getSelectedDate();
                    database.editNumberOfPerson(t.getTableView().getItems().get(t.getTablePosition().getRow()).getNumberOfPerson(), t.getTableView().getItems().get(t.getTablePosition().getRow()).getPhoneNumber());
                    database.printClients();
                }
        );

        //Phone number column
        TableColumn<Client, Integer> column4 = new TableColumn<>("Phone Number");
        column4.setCellValueFactory(new PropertyValueFactory<>("phoneNumber"));

        column4.setCellFactory(TextFieldTableCell.<Client, Integer>forTableColumn(new IntegerStringConverter()));
        column4.setOnEditCommit(
                (TableColumn.CellEditEvent<Client,Integer> t) -> {
                    ((Client) t.getTableView().getItems().get(t.getTablePosition().getRow())
                    ).setPhoneNumber(Integer.parseInt(String.valueOf(t.getNewValue())));
                    setLabels();
                    suffix = datePicker.getSelectedDate();
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

        column5.setCellFactory(TextFieldTableCell.<Client>forTableColumn());
        column5.setOnEditCommit(
                (TableColumn.CellEditEvent<Client,String> t) -> {
                    ((Client) t.getTableView().getItems().get(t.getTablePosition().getRow())
                    ).setComment(t.getNewValue());
                    setLabels();
                    suffix = datePicker.getSelectedDate();
                    database.editComment(t.getTableView().getItems().get(t.getTablePosition().getRow()).getComment(), t.getTableView().getItems().get(t.getTablePosition().getRow()).getPhoneNumber());
                    database.printClients();
                }
        );


        table1.getColumns().addAll(column1,column2,column3,column4,column5);


        table1.setOnMousePressed(new EventHandler<javafx.scene.input.MouseEvent>() {
            @Override
            public void handle(javafx.scene.input.MouseEvent mouseEvent) {
                if(mouseEvent.getClickCount() == 1){
                    if(table1.getSelectionModel().isEmpty()) {
                        informationName = null;
                        informationPhoneNumber = 0;
                        informationTableNumber = 0;
                        informationNumberOfPerson = 0;
                        informationComment = null;

                    }
                    else {
                        informationName = table1.getSelectionModel().getSelectedItem().getName();
                        informationNumberOfPerson = table1.getSelectionModel().getSelectedItem().getNumberOfPerson();
                        informationTableNumber = table1.getSelectionModel().getSelectedItem().getTableNumber();
                        informationPhoneNumber = table1.getSelectionModel().getSelectedItem().getPhoneNumber();
                        informationComment = table1.getSelectionModel().getSelectedItem().getComment();
                    }
                    setLabels();

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
            Client newClient = new Client(newName, newTableNumber, newNumberOfPerson, newPhoneNumber, newComment);
            System.out.println(newClient.getName());
            database.addClient(newClient, datePicker.getSelectedDate());

            database.printClients();

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
            e.printStackTrace();

            alert.showAndWait();
        }

    }
    private void deleteClient(ActionEvent event){
        try{
            Client deletedClient = table1.getSelectionModel().getSelectedItem();
            database.deleteClient(deletedClient, datePicker.getSelectedDate());
            database.printClients();
            database.printDeletedClients();

            observableClientList.remove(table1.getSelectionModel().getSelectedItem());
            table1.refresh();
            informationName = null;
            informationComment = null;
            informationNumberOfPerson = 0;
            informationPhoneNumber = 0;
            informationTableNumber = 0;

            setLabels();
        }
        catch(Exception e){
            Alert alert = new Alert(Alert.AlertType.WARNING);
            alert.setTitle("WARNING");
            alert.setHeaderText(null);
            alert.setContentText("Error");
        }
    }

    public void setLabels()
    {
        clientName.setText(informationName);
        clientNumberOfPerson.setText(informationNumberOfPerson + " ");
        clientTableNumber.setText(informationTableNumber + " ");
        clientPhoneNumber.setText(informationPhoneNumber + " ");
        clientComment.setText(informationComment );
    }

}
