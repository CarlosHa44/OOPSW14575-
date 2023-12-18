package ec.edu.espe.icecream.model;

import static ec.edu.espe.icecream.model.Invoice.scan;
import com.google.gson.reflect.TypeToken;
import ec.edu.espe.icecream.utils.UseJson;
import java.util.ArrayList;
import java.util.InputMismatchException;
import java.util.Scanner;

/**
 *
 * @author Carlos
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
                System.out.println("Enter the customer name");
                name = scan.nextLine();
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
        return new Client(newID, name, email, cellphoneNumber, isNorth, isMajority);
    }

    public static void editClient(ArrayList<Client> clients) {
        Scanner scan = new Scanner(System.in);
        System.out.println("Enter the customer id to edit");
        int idToEdit = scan.nextInt();
        scan.nextLine();

        for (Client client : clients) {
            if (client.getId() == idToEdit) {
                System.out.println("Enter the new name");
                String newName = scan.nextLine();
                client.setName(newName);
                System.out.println("Enter the new email");
                String newEmail = scan.nextLine();
                client.setEmail(newEmail);
                System.out.println("Enter the customer's new phone number");
                String newPhone = scan.nextLine();
                client.setCellphoneNumber(newPhone);
                System.out.println("Enter if the customer is in the north? (true/false)");
                boolean newNorth = scan.nextBoolean();
                client.setIsNorth(newNorth);
                System.out.println("¿The client is of majority age? (true/false)");
                boolean newIsMajority = scan.nextBoolean();
                client.setIsMajority(newIsMajority);

                System.out.println("Client edited successfully.");
                return;
            }
        }

        System.out.println("Client not found.");
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
            System.out.println("4.Return to the main menu");

            if (scan.hasNextInt()) {
                optionClient = scan.nextInt();
                scan.nextLine();  
            } else {
                System.out.println("Invalid input. Please enter a number.");
                scan.nextLine();  
                continue;  
            }

            switch (optionClient) {
                case 1:
                    clients.add(Client.addClient(clients));
                    jsonUtilClients.writeFile("clientdata.json", clients);
                    break;
                case 2:
                    Client.editClient(clients);
                    jsonUtilClients.writeFile("clientdata.json", clients);
                    break;
                case 3:
                    System.out.println("ID    Name             Email                        CellphoneNumber   isNorth  isMajority");
                    System.out.println("-----------------------------------------------------------------------------------------");
                        for (Client client : clients) {
                            System.out.println(client);
                        }
                    break;
                case 4:
                    return;
                default:
                    System.out.println("Invalid option. Please try again.");
            }
        }
    }

    @Override
    public String toString() {
        return String.format("%-5d %-15s %-30s %-16s %-10b %b",
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

    public boolean isIsNorth() {
        return isNorth;
    }

    public void setIsNorth(boolean isNorth) {
        this.isNorth = isNorth;
    }

    public boolean isIsMajority() {
        return isMajority;
    }

    public void setIsMajority(boolean isMajority) {
        this.isMajority = isMajority;
    }

}
