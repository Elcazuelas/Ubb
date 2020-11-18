/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package presentacion;
import java.util.Scanner;

/**
 * 
 * @author Julio Monroy - Camilo Vazques
 */
public class UIPrincipal {
    private static UIPrincipal instance;
    private Scanner tdd;
    
    //constructor 
    private UIPrincipal(){
        tdd=new Scanner(System.in);
    }
    
    //metodo singleton
    public static UIPrincipal getInstance(){
        if(instance== null){
            instance= new UIPrincipal();
        }
        return instance;
    }
    
    //main
    public static void main(String[] args) {
        
    }
    
    //metodos
    private inicia(){
    }
    
    private void menuMain(){
        int opcion=0;
        do{
            System.out.println("MENU PRINCIPAL");
            System.out.println("==============");
            System.out.println("1.  Crear un cliente");
            System.out.println("2.  Crear un veterinario");
            System.out.println("3.  Crear una mascota");
            System.out.println("4.  Listar mascota de una clase");
            System.out.println("5.  Agregar una atencion a una mascota");
            System.out.println("6.  Listar ultima atencion de mascotas de un cliente");
            System.out.println("7.  Listar atenciones de un veterinario");
            System.out.println("8.  Listar Nro.atenciones para clase, especie y raza en un lapso de tiempo");
            System.out.println("9.  Listar datos y nro.atenciones de cada veterinario");
            System.out.println("10. Salir");
            System.out.print("        Ingrese onpcion:");
            opcion=tdd.nextInt();
            
            switch (opcion){
                case 1:
                    creaCliente();
                    break;
                case 2:
                    creaVeterinario();
                    break;
                case 3:
                    creaMascota();
                    break;
                case 4:
                    listasMascotasDeUnaClase();
                    break;
                case 5:
                    //falta
                    System.out.println("Estamos trabjando para esta opcion");
                    break;
                case 6:
                    //falta
                    System.out.println("Estamos trabjando para esta opcion");
                    break;
                case 7:
                    //falta
                    System.out.println("Estamos trabjando para esta opcion");
                    break;
                case 8:
                    //falta
                    System.out.println("Estamos trabjando para esta opcion");
                    break;
                case 9:
                    listaDatosVeterinario();
                    break;
                case 10:
                    //agrego opcion 10 para ñadir un default
                    System.out.println("Adios :(");
                default:
                    System.out.println("Elija una opcion valida");
            }
        }while(opcion!=10);
    }
    
}
