package ec.edu.espe.icecreamdeve.model;

import com.google.gson.reflect.TypeToken;
import static ec.edu.espe.icecreamdeve.model.SaleNote.getScan;
import ec.edu.espe.icecreamdeve.utils.MDBManage;
import static ec.edu.espe.icecreamdeve.utils.MDBManage.connectToDataBase;
import ec.edu.espe.icecreamdeve.utils.UseJson;
import java.util.ArrayList;
import java.util.InputMismatchException;
import java.util.Scanner;

/**
 *
 * @author Carlos Hernandez, Mateo Iza, Juan Granda, Josue Guayasamin
 */
public class Client {

    private int id;
    private String name;
    private String email;
    private String cellphoneNumber;
    private boolean isNorth;
    private boolean isMajority;

    public Client(int id, String name, String email, String cellphoneNumber, boolean isNorth, boolean isMajority) {
        this.id = id;
        this.name = name;
        this.email = email;
        this.cellphoneNumber = cellphoneNumber;
        this.isNorth = isNorth;
        this.isMajority = isMajority;
    }

    private static int generateID(ArrayList<Client> clients) {
        int actualId = 0;
        for (Client currentClient : clients) {
            actualId = currentClient.getId();
        }
        return actualId + 1;
    }

    public static Client addClient(ArrayList<Client> clients) {
        Scanner scan = new Scanner(System.in);
        int newID = generateID(clients);
        String name = "";
        String email = "";
        String cellphoneNumber = "";
        boolean isNorth = false;
        boolean isMajority = false;
        boolean inputValid;

        do {
            try {

                do {
                    System.out.println("Enter the new name");
                    name = scan.nextLine();
                    if (!name.matches("[a-zA-Z]+")) {
                        System.out.println("Please, Enter the customer name.");
                        inputValid = false;
                    } else {
                        inputValid = true;
                    }
                } while (!inputValid);
                System.out.println("Enter the customer's email");
                email = scan.nextLine();
                System.out.println("Enter the customer's phone number");
                cellphoneNumber = scan.nextLine();
                System.out.println("¿The client is in the north? (true/false)");
                isNorth = scan.nextBoolean();
                System.out.println("¿The client is the majority? (true/false)");
                isMajority = scan.nextBoolean();
                inputValid = true;
            } catch (InputMismatchException e) {
                System.out.println("An error occurred");
                inputValid = false;
                scan.nextLine();
            }
        } while (!inputValid);
        scan.nextLine();
        Client newClient = new Client(newID, name, email, cellphoneNumber, isNorth, isMajority);
        MDBManage.registerClient(newClient);
      
        return  newClient;
        
    }


        public static void editClient(Client client, ArrayList<Client> clients) {
           Scanner scan = new Scanner(System.in);
           String newName;

           System.out.println("ID    Name             Email                        CellphoneNumber   isNorth  isMajority");
           System.out.println("-----------------------------------------------------------------------------------------");
           for (Client c : clients) {
               System.out.println(c);
           }

           int idToEdit;
           boolean inputValidation;

           while (true) {
               try {
                   do {
                       System.out.println("Enter the customer ID to edit1:");
                       idToEdit = Integer.parseInt(getScan().nextLine());
                       if (idToEdit > clients.size()) {
                           inputValidation = false;
                           System.out.println("Please, Enter the customer ID to edit");
                       } else {
                           inputValidation = true;
                       }
                   } while (!inputValidation);
                   break;
               } catch (NumberFormatException e) {
                   System.out.println("Invalid input. Enter the customer ID to edit");
               }
           }

           Client selectedClient = findClientById(clients, idToEdit);
           if (selectedClient != null) {
               do {
                   System.out.println("Enter the new name");
                   newName = scan.nextLine();
                   if (!newName.matches("[a-zA-Z]+")) {
                       System.out.println("Please, Enter the customer name.");
                       inputValidation = false;
                   } else {
                       inputValidation = true;
                   }
               } while (!inputValidation);

               selectedClient.setName(newName);
               System.out.println("Enter the new email");
               String newEmail = scan.nextLine();
               selectedClient.setEmail(newEmail);
               System.out.println("Enter the customer's new phone number"); 
               String newPhone = scan.nextLine();
               selectedClient.setCellphoneNumber(newPhone);
               System.out.println("Enter if the customer is in the north? (true/false)");
               boolean newNorth = Boolean.parseBoolean(scan.nextLine());
               selectedClient.setIsNorth(newNorth);
               System.out.println("¿The client is of majority age? (true/false)");
               boolean newIsMajority = Boolean.parseBoolean(scan.nextLine());
               selectedClient.setIsMajority(newIsMajority);

               // Llama a la función de MDBManage para editar en MongoDB y la lista local
               MDBManage.editClientMG(selectedClient, clients);

               System.out.println("Client edited successfully.");
           } else {
               System.out.println("Client not found.");
           }
       }




    // Otro método para buscar un cliente por ID
    private static Client findClientById(ArrayList<Client> clients, int id) {
        for (Client client : clients) {
            if (client.getId() == id) {
                return client;
            }
        }
        return null;
    }

    public static void menuClient(ArrayList<Client> clients) {
        UseJson<Client> jsonUtilClients = new UseJson<>();
        clients = jsonUtilClients.readFile("clientdata.json", new TypeToken<ArrayList<Client>>() {
        }.getType());

        int optionClient;
        while (true) {
            System.out.println("//////////CLIENT/////////");
            System.out.println("1.Enter Clients");
            System.out.println("2.Edit Clients");
            System.out.println("3.Show Clients");
            System.out.println("4.Delete Clients");
            System.out.println("5.Return to the main menu");

            if (getScan().hasNextInt()) {
                optionClient = getScan().nextInt();
                getScan().nextLine();
            } else {
                System.out.println("Invalid input. Please enter a number.");
                getScan().nextLine();
                continue;
            }

            switch (optionClient) {
                case 1:
                    clients.add(Client.addClient(clients));
                    System.out.println("Client added successfully!");
                    jsonUtilClients.writeFile("clientdata.json", clients);
                    break;
                case 2:
                     System.out.println("Enter the customer ID to edit:");
                     int idToEdit = Integer.parseInt(getScan().nextLine());

                     // Llamar al método findClientById para obtener el cliente seleccionado
                     Client selectedClient = Client.findClientById(clients, idToEdit);
                     if (selectedClient != null) {
                         try {
                             Client.editClient(selectedClient, clients);
                             jsonUtilClients.writeFile("clientdata.json", clients);
                             System.out.println("Client edited successfully.");
                         } catch (Exception e) {
                             System.out.println("An error occurred while editing the client: " + e.getMessage());
                         }
                     } else {
                         System.out.println("Client not found.");
                     }
                    break;
                case 3:
                    System.out.println("ID    Name             Email                        CellphoneNumber   isNorth  isMajority");
                    System.out.println("-----------------------------------------------------------------------------------------");
                    for (Client client : clients) {
                        System.out.println(client);
                    }
                    break;
                case 4:
                    System.out.println("ID    Name             Email                        CellphoneNumber   isNorth  isMajority");
                    System.out.println("-----------------------------------------------------------------------------------------");
                    for (Client c : clients) {
                         System.out.println(c);
                    }

                   System.out.println("Enter the customer ID to delete:");
                   int idToDelete = Integer.parseInt(getScan().nextLine());

                   // Llamar al método deleteClient para eliminar el cliente seleccionado
                   MDBManage.deleteClient(idToDelete, clients);
                   jsonUtilClients.writeFile("clientdata.json", clients);
                   System.out.println("Client deleted successfully.");
                    
                break;
                case 5:
                    return;
                default:
                    System.out.println("Invalid option. Please try again.");
            }
        }
    }

    @Override
    public String toString() {
        return String.format("\n%-5d %-15s %-30s %-16s %-10b %b",
                getId(), getName(), getEmail(), getCellphoneNumber(), isIsNorth(), isIsMajority());
    }

    /**
     * @return the id
     */
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getCellphoneNumber() {
        return cellphoneNumber;
    }

    public void setCellphoneNumber(String cellphoneNumber) {
        this.cellphoneNumber = cellphoneNumber;
    }

    /**
     * @return the isNorth
     */
    public boolean isIsNorth() {
        return isNorth;
    }

    /**
     * @param isNorth the isNorth to set
     */
    public void setIsNorth(boolean isNorth) {
        this.isNorth = isNorth;
    }

    /**
     * @return the isMajority
     */
    public boolean isIsMajority() {
        return isMajority;
    }

    /**
     * @param isMajority the isMajority to set
     */
    public void setIsMajority(boolean isMajority) {
        this.isMajority = isMajority;
    }
    
    public boolean getIsNorth() {
        return isNorth;
    }

    public boolean getIsMajority() {
        return isMajority;
    }


}
