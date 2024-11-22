package zona_fit.DAO;

import java.util.List;

import zona_fit.domain.Client;

public interface IClientDao {
    List<Client> listClients();
    boolean searchClientById(Client client);
    boolean addClient(Client client);
    boolean modifyClient(Client client);
    boolean removeClient(Client client);

}
