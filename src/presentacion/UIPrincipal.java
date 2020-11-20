/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package presentacion;
import control.Controlador;
import java.util.Scanner;

/**
 * 
 * @author Julio Monroy - Camilo Vazques
 */
public class UIPrincipal {
    //atributos
    private static UIPrincipal instance;
    private Scanner tdd;
    
    //asociacion
    Controlador control;
    
    //constructor 
    private UIPrincipal(){
        tdd=new Scanner(System.in);
        control=Controlador.getInstance();
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
        UIPrincipal principal = UIPrincipal.getInstance();
        principal.menuMain();
    }
    
    //metodos
    /* private inicia(){
    } */
    
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
            System.out.print("        Ingrese onpcion: ");
            opcion=tdd.nextInt();
            
            switch (opcion){
                case 1:
                    creaCliente();
                    break;
                case 2:
                    //creaVeterinario();
                    break;
                case 3:
                    //creaMascota();
                    break;
                case 4:
                    //listasMascotasDeUnaClase();
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
                    //listaDatosVeterinario();
                    break;
                case 10:
                    //agrego opcion 10 para ñadir un default
                    System.out.println("Adios :(");
                    return;
                default:
                    System.out.println("Elija una opcion valida");
            }
        }while(true);
    }

    private void creaCliente() {
        System.out.println("Creando un nuevo cliente...");
        //ingreso de datos
       String rut, nombre, email;
        System.out.print("Rut: ");
        rut=tdd.next();
        
        //para poder  ocupar el "nextLine" a la hora de ingresar el nombre se usa el "skip("\n") para consumir el enter guardado anteriormente" 
        tdd.skip("\n");
        
        System.out.print("Nombre: ");
        nombre=tdd.nextLine();
        System.out.print("Email: ");
        email=tdd.next();
        
        //valido espacios vacíos
        if (rut.trim().isEmpty() || nombre.trim().isEmpty() || email.trim().isEmpty() ) {
            System.out.println("Uno o mas datos son NO validos");
            return;
        }else{
            control.creaCliente(rut, nombre, email);
        }

    }
    
}
