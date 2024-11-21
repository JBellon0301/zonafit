package zona_fit.DAO;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

import zona_fit.connection.ConnectionDataBase;
import zona_fit.domain.Client;

public class ClientDao implements IClientDao {

    @Override
    public List<Client> listClients() {
        List<Client> clients = new ArrayList<>();
        PreparedStatement ps;
        ResultSet rs;
        Connection con = ConnectionDataBase.getConnection();
        var sql = "SELECT * FROM client ORDER BY id_client";
        try {
            ps = con.prepareStatement(sql);
            rs = ps.executeQuery();
            while(rs.next()){
                var client = new Client();
                client.setId_client(rs.getInt("id_client"));
                client.setName_client(rs.getString("name_client"));
                client.setLast_name_client(rs.getString("last_name_client"));
                client.setEmail_client(rs.getString("email_client"));
                client.setMembership_number(rs.getInt("membership_number"));
                client.setDate_registered(rs.getTimestamp("date_registered"));
                clients.add(client);
            }
        }catch (Exception e){
            System.out.println("Error listing Clients" + e.getMessage());
        }
        finally {
            try {
                con.close();
            } catch (Exception e) {
                System.err.println("Error to close connection: " + e.getMessage());
            }
        }
        return clients;
    }

    @Override
    public boolean searchClienteById(Client client) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'searchClienteById'");
    }

    @Override
    public boolean addClient(Client client) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'addClient'");
    }

    @Override
    public boolean modifyClient(Client client) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'modifyClient'");
    }

    @Override
    public boolean removeClient(Client client) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'removeClient'");
    }

    public static void main(String[] args) {
        System.err.println("*** Listing Clients ***");
        IClientDao clientDao = new ClientDao();
        var clients = clientDao.listClients();
        clients.forEach(System.out::println);
        
    }

}
