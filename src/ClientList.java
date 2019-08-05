import javafx.beans.Observable;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

import java.util.*;

public class ClientList{
    private ObservableList<Client> clientList;

    public ClientList() {clientList = FXCollections.observableArrayList();
    }

        public void addClientToList(Client client){
            clientList.add(client);
            System.out.println("1");
        }

        public List<Client> showClientList(){
            return clientList;
        }

}
