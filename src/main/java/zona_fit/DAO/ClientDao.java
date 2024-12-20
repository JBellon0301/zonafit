package zona_fit.DAO;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

import zona_fit.connection.ConnectionDataBase;
import static zona_fit.connection.ConnectionDataBase.getConnection;
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
    public boolean searchClientById(Client client) {
        PreparedStatement ps;
        ResultSet rs;
        var con = getConnection();
        var sql = "SELECT * FROM client WHERE id_client = ?";
        try{
            ps = con.prepareStatement(sql);
            ps.setInt(1, client.getId_client());
            rs = ps.executeQuery();
            if (rs.next()) {
                client.setName_client(rs.getString("name_client"));
                client.setLast_name_client(rs.getString("last_name_client"));
                client.setEmail_client(rs.getString("email_client"));
                client.setMembership_number(rs.getInt("membership_number"));
                client.setDate_registered(rs.getTimestamp("date_registered"));
                return true;
            }
        }catch (Exception e){
            System.out.println("Error searching client by ID: " + e.getMessage());
        }
        finally {
            try {
                con.close();
            } catch (Exception e) {
                System.err.println("Error to close connection: " + e.getMessage());
            }
        }
        return false;
    }
    

    @Override
public boolean addClient(Client client) {
    PreparedStatement ps;
    Connection con = getConnection();
    String sql = "INSERT INTO client (name_client, last_name_client, email_client, date_registered) "
               + "VALUES (?, ?, ?, ?)";
    try {
        ps = con.prepareStatement(sql);
        ps.setString(1, client.getName_client());
        ps.setString(2, client.getLast_name_client());
        ps.setString(3, client.getEmail_client());
        ps.setTimestamp(4, new java.sql.Timestamp(client.getDate_registered().getTime()));
        ps.execute();
        return true;
    } catch (Exception e) {
        System.out.println("Error adding client: " + e.getMessage());
    } finally {
        try {
            con.close();
        } catch (Exception e) {
            System.out.println("Error closing connection: " + e.getMessage());
        }
    }
    return false;
}


    public boolean modifyClient(Client client) {
        PreparedStatement ps;
        Connection con = getConnection();
        String sql = "UPDATE client SET name_client = ?, last_name_client = ?, email_client = ? WHERE id_client = ?";
        try {
            ps = con.prepareStatement(sql);
            ps.setString(1, client.getName_client());
            ps.setString(2, client.getLast_name_client());
            ps.setString(3, client.getEmail_client());
            ps.setInt(4, client.getId_client());
            
            int rowsUpdated = ps.executeUpdate();
            return rowsUpdated > 0;  // Devuelve true si se actualizó al menos un registro
        } catch (Exception e) {
            System.out.println("Error modifying client: " + e.getMessage());
        } finally {
            try {
                con.close();
            } catch (Exception e) {
                System.out.println("Error closing connection: " + e.getMessage());
            }
        }
        return false;
    }


    @Override
    public boolean removeClient(Client client) {
            PreparedStatement ps;
            Connection con = getConnection();
            String sql = "DELETE FROM client WHERE id_client = ? ";
            try {
                ps = con.prepareStatement(sql);
                ps.setInt(1, client.getId_client());
                ps.execute();
                return true;

            } catch (Exception e) {
                System.out.println("Error to remove client: " + e.getMessage());
            }
            finally{
                try{
                    con.close();
                }catch (Exception e){
                    System.out.println("Error to close connection" + e.getMessage());
                }
            }
            return false;
    }

    public static void main(String[] args) {
        IClientDao clientDao = new ClientDao();
        /* search client by Id 
        var client1 = new Client(2);
        System.out.println("Client before the search: " + client1);
        var founded = clientDao.searchClientById(client1);
        if(founded)
            System.out.println("Client founded: "+ client1);
        else
            System.out.println("The client was not founded: " + client1);   */  
        
        //Add client
        /* var newClient = new Client("Cleare", "Screwish", "cscrewish@email.com", 10004);
        var added = clientDao.addClient(newClient);
        if(added)
            System.out.println("Client added: " + newClient);
        else
            System.out.println("Client was not added: " + newClient); 
         */
        /* Modify client 
         var modifyClient = new Client(1, "Jeremias", "Belmont", "jbelmont@email.com", 10001, null);
        var modified = clientDao.modifyClient(modifyClient);
        if(modified)
            System.out.println("Client modified: " + modifyClient);
        else
            System.out.println("Client was not modified: " + modifyClient); */

        //Remove client
        /* IClientDao clientDao = new ClientDao();
        var clientRemoved = new Client(3);
        var removed = clientDao.removeClient(clientRemoved);
        if(removed)
            System.out.println("Cliente removed: " + clientRemoved);
        else
            System.out.println("Client was not removed" + clientRemoved);   */  

        //Listing clients
        System.out.println("*** Listing Clients ***");
        var clients = clientDao.listClients();
        clients.forEach(System.out::println);
    }
}