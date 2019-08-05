import java.util.*;

public class ClientList{
    private ArrayList<Client> clientList;

    public ClientList() {
        clientList = new ArrayList<Client>();
    }


        public void addClientToList(Client client){
            clientList.add(client);
        }

        public ArrayList<Client> showClientList(){
            return clientList;
        }

}
