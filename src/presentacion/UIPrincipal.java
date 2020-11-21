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
    private Scanner tcld;
    
    //asociacion
    Controlador control;
    
    //constructor 
    private UIPrincipal(){
        tcld=new Scanner(System.in);
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
        String opcion;
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
            System.out.print("\tIngrese opcion: ");
            opcion=tcld.nextLine();
            
            switch (opcion.trim()){//solo para que tome el valor ingresado sin importar espacios
                case "1":
                    creaCliente();
                    break;
                case "2":
                    creaVeterinario();
                    break;
                case "3":
                    creaMascota();
                    break;
                case "4":
                    //listasMascotasDeUnaClase();
                    break;
                case "5":
                    //falta
                    System.out.println("Estamos trabjando para esta opcion");
                    break;
                case "6":
                    //falta
                    System.out.println("Estamos trabjando para esta opcion");
                    break;
                case "7":
                    //falta
                    System.out.println("Estamos trabjando para esta opcion");
                    break;
                case "8":
                    //falta
                    System.out.println("Estamos trabjando para esta opcion");
                    break;
                case "9":
                    //listaDatosVeterinario();
                    break;
                case "10":
                    //agrego opcion 10 para ñadir un default
                    System.out.println("Adios :(");
                    return;
                default:
                    System.out.println("\nElija una opcion valida\n");
            }
        }while(true);
    }

    private void creaCliente() {
        try {
            
            System.out.println("\nCreando un nuevo cliente...");
            //ingreso de datos
            /*Para poder dar la opción a una persona de ingresar un blanco en alguno de los datos se optó por usar next Line*/
            String rut, nombre, email;
            System.out.print("Rut: ");
            rut=tcld.nextLine().trim();
            System.out.print("Nombre: ");
            nombre=tcld.nextLine().trim();
            System.out.print("Email: ");
            email=tcld.nextLine().trim();
        
        //valido espacios vacíos
         if (rut.isEmpty()|| rut.indexOf(" ")!=-1 || rut.indexOf("-") != rut.lastIndexOf("-") || rut.endsWith("-") ||  rut.startsWith("-") || //validar si el rut termina o comienza en "-" o está vacio o no tiene nada despues del guión
                    nombre.isEmpty() || //validar si nombre está vacio
                    email.isEmpty() || email.indexOf(" ")!=-1 ||   email.indexOf("@") != email.lastIndexOf("@") || email.endsWith("@")  || email.startsWith("@") || //validar si email está vacio o tiene solo un @ o está mal posicionado
                    !(email.lastIndexOf(".") > email.indexOf("@")  && email.lastIndexOf(".")-1 != email.indexOf("@")) || email.endsWith(".") || email.indexOf(".",email.indexOf("@")) != email.lastIndexOf(".") ) { //validar que el email tenga un . despues del @ y que tenga texto antes y despues de él
            
                System.out.println("\n\nUno o mas datos son NO validos\n");
                return;
            }
        
            //validar Rut termina de 0-9 o k
            boolean verificador=false;                       
            for(int i=0 ; i < 10; i++){
    if (rut.split("-")[1].equalsIgnoreCase(Integer.toString(i)) || rut.split("-")[1].equalsIgnoreCase("k")) {
                    verificador=true;
                }
            }
            if (!verificador) {
                System.out.println("\n\nUno o mas datos son NO validos\n");
                return;
         }
        
            //validar que el rut tenga el tamaño necesario
            String rutSindigito;
            if (rut.split("-")[0].indexOf(".")==-1) {//verificar si el rut no tiene puntos
            
                if (rut.split("-")[0].length() != 7 && rut.split("-")[0].length() != 8 ) {//verificar que tenga la cantidad necesaria de numeros
                System.out.println("\n\nUno o mas datos son NO validos\n");
                return;
                }else{
                    rutSindigito=rut.split("-")[0];
                }
            
            }else if(rut.split("-")[0].indexOf(".",rut.split("-")[0].indexOf(".") + 1) == rut.split("-")[0].lastIndexOf(".")){ //si tiene puntos entonces verificar que al menos tenga dos
            //validar que los puntos estén bien posicionados
                if ((rut.split("-")[0].length() - 1 - rut.split("-")[0].lastIndexOf(".")) == 3 &&
                     (rut.split("-")[0].lastIndexOf(".") - rut.split("-")[0].indexOf(".")-1)==3 && ( rut.split("-")[0].indexOf(".") == 1 ||  rut.split("-")[0].indexOf(".") == 2)   ) {
              
                    rutSindigito=rut.split("-")[0].replace('.', '0');
                
                }else{
                System.out.println("\n\nUno o mas datos son NO validos\n");
                 return;
                }
            
            }else{
                System.out.println("\n\nUno o mas datos son NO validos\n");
                return;
            }
        
        //validar que el rut sin digitos sean numeros
            try {
                Integer.parseInt(rutSindigito);
            } catch (NumberFormatException e) {
                 System.out.println("\n\nUno o mas datos son NO validos\n");
                 return;
            }
        
        
                //se confirma que los datos estan buenos ;)
             control.creaCliente(rut, nombre, email);
             System.out.println("\n\nEl cliente se ha creado satisfactoriamente\n");
       
        } catch (Exception e) {
        }
        
    }//fin crea cliente

    private void creaVeterinario() {
        System.out.println("\nCreando un nuevo veterinario...");
        
        //ingreso de datos
       String rut, nombre, email, especialidad;
        System.out.print("Rut: ");
        rut=tcld.next();
        
        //para poder  ocupar el "nextLine" a la hora de ingresar el nombre se usa el "skip("\n") para consumir el enter guardado anteriormente" 

        
        System.out.print("Nombre: ");
        nombre=tcld.skip("\n").nextLine();
        System.out.print("Email: ");
        email=tcld.next();
        System.out.print("Especialidad: ");
        especialidad = tcld.next();
        
        //valido espacios vacíos
        if (rut.trim().isEmpty() || nombre.trim().isEmpty() || email.trim().isEmpty() || especialidad.trim().isEmpty()) {
            System.out.println("\n\nUno o mas datos son NO validos\n");
            return;
        }else{
            control.creaVeterinario(rut, nombre, email, especialidad);
            System.out.println("\n\nEl cliente se ha creado satisfactoriamente\n");            
        }
    }//fin creaVet

    private void creaMascota() {
        System.out.println("\nCreando una nueva mascota...");
        
        //ingreso de datos
        
        
    }
    
    
}
