import javafx.beans.Observable;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

import java.util.*;

public class ClientList{
    private static ArrayList<Client> arrayClient;

    public ClientList() {
        arrayClient = new ArrayList<>();

    }

        public void addClientToList(Client client){
            arrayClient.add(client);
            System.out.println("1");
        }

        public List<Client> showClientList(){
            return arrayClient;
        }


}
