package zona_fit.presentation;


import java.util.Scanner;

import zona_fit.DAO.ClientDao;
import zona_fit.DAO.IClientDao;
import zona_fit.domain.Client;

public class FitZoneApp {
    public static void main(String[] args) {
        FitZoneApp();
    }

    public static void FitZoneApp(){
        var leave = false;
        var console = new Scanner(System.in);
        //We create an object from ClientDao class
        IClientDao clientDao = new ClientDao();
        while(!leave){
            try {
                var option = showMenu(console);
                leave = executeOptions(console, option, clientDao);
            } catch (Exception e) {
                System.err.println("Error to execute options: " + e.getMessage());
            }
            System.out.println();
        }

    }

    private static int showMenu(Scanner console){
        System.err.println("""
                *** Zone Fit (GYM) ***       
                1. List Clients
                2. Search Client
                3. Add Client
                4. Modify Client
                5. Remove Client
                6. Leave
                Chose and option:\s""");
        return Integer.parseInt(console.nextLine()); 
    }

    private static boolean executeOptions(Scanner console, int option,
                                          IClientDao clientDao){
        var leave = false;
        switch (option){
            case 1 -> { //Listing Clients
                System.err.println("--- List of Clients ---");
                var clients = clientDao.listClients();
                clients.forEach(System.out::println);
            }
            case 2 -> {//Search client by Id
                System.out.println("Put in the client Id to search");
                var id_Client = Integer.parseInt(console.nextLine());
                var client = new Client(id_Client);
                var founded = clientDao.searchClientById(client);
                if(founded)
                    System.out.println("Client founded: " + client);
                else
                    System.out.println("Client not founded: " + client);

            }
            case 3 -> {
                System.out.println("--- Add client ---");
                System.out.println("Name: ");
                var name = console.nextLine();
                System.out.println("Last Name: ");
                var last_name = console.nextLine();
                System.out.println("Email: ");
                var email = console.nextLine();
            
                // Crear el cliente sin el membership_number
                var client = new Client(name, last_name, email);
                var added = clientDao.addClient(client);
                if (added) {
                    System.out.println("Client added: " + client);
                } else {
                    System.out.println("Client not added: " + client);
                }
            }
            case 4 -> { // Modify client
                System.out.println("*** Modify Client ***");
                System.out.println("Id Cliente: ");
                int id_Client = Integer.parseInt(console.nextLine());
                System.out.println("Name: ");
                String name = console.nextLine();
                System.out.println("Last Name: ");
                String last_name = console.nextLine();
                System.out.println("Email: ");
                String email = console.nextLine();
            
                // Crear el objeto cliente con los datos proporcionados
                Client client = new Client(id_Client, name, last_name, email);
            
                // Llamar al método modifyClient desde clientDao para actualizar la base de datos
                boolean modified = clientDao.modifyClient(client);
                if (modified) {
                    System.out.println("Client modified: " + client);
                } else {
                    System.out.println("Client was not modified: " + client);
                }
            }
            
            case 5 ->{
                System.out.println(" *** Remove client *** ");
                System.out.println("Id client: ");
                var id_Client = Integer.parseInt(console.nextLine());
                var client = new Client(id_Client);
                var removed = clientDao.removeClient(client);
                if(removed)
                    System.out.println("Client removed: " + client);
                else
                    System.out.println("Client was not removed " + client);
            }
            case 6 ->{
                System.out.println("See you later!");
                leave = true;
            }
            default -> System.out.println("Option was not recognize: " + option);        
        }
        return leave;                                    
    }
}
