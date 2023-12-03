package ec.edu.espe.icecream.view;

import java.util.Scanner;

/**
 *
 * @author Carlos Hernandez
 */
public class IceCreamSystem {
static Scanner scan = new Scanner(System.in);
    public static void main(String[] args) {
        int option=0;
        do{
            System.out.println("///////Ice Cream System/////////");
            System.out.println("1.Show Inventory");
            System.out.println("2.Add Invoice");
            System.out.println("3.Show clients");
            System.out.println("4.Generate a SaleNote");
            System.out.println("5.Show the business report");
            System.out.println("6. Exit");
            option=scan.nextInt();
            scan.nextLine();
            switch(option){
                case 1:
                    //Aqui deberiamos desarrollar una funcion que muestre el arhivo Json del inventario
                    break;
                case 2:
                    //Aqui debemos crear la funcion de crear notas de venta 
                    break;
                case 3:
                    //Aui debemos mostrar la lista de clientes del json
                    break;
                case 4:
                    //Aqui creamos la funcion de crear una nota de venta o factura
                    break;
                case 5:
                    //Mostrar el reporte de ventas en funcion de los invoice y las notesale
                    break;
                case 6:
                    break;
            }
            
        }while(option!=6);
        
        
        
    }

}
