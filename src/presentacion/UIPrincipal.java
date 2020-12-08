/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package presentacion;
import control.Controlador;
import static modelo.Clase.*;
import java.time.LocalDate;
import java.util.Scanner;
import modelo.Clase;

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
         tcld=new Scanner(System.in).useDelimiter("");
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
                    System.out.println("Adios, suerte!! :D");
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
            rut=tcld.next().trim();
            System.out.print("Nombre: ");
            nombre=tcld.nextLine().trim();
            System.out.print("Email: ");
            email=tcld.next().trim();
        
                //valido espacios vacíos
            if (rut.isEmpty()|| rut.indexOf("-") != rut.lastIndexOf("-") || rut.endsWith("-") ||  rut.startsWith("-") || //validar si el rut termina o comienza en "-" o está vacio o no tiene nada despues del guión
                nombre.isEmpty() || //validar si nombre está vacio
                email.isEmpty() ||   email.indexOf("@") != email.lastIndexOf("@") || email.endsWith("@")  || email.startsWith("@") || //validar si email está vacio o tiene solo un @ o está mal posicionado
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
       
        } catch (Exception e) {       //preguntar julio por throw
        }
        
    }//fin crea cliente

    private void creaVeterinario() {                                    //preguntar julio solo valido espacios vacios
        System.out.println("\nCreando un nuevo veterinario...");
        
        //Datos
        String rut, nombre, email, especialidad;
        
        //ingreso de datos
        System.out.print("Rut: ");
        rut=tcld.next().trim();
        System.out.print("Nombre: ");
        nombre=tcld.nextLine().trim();
        System.out.print("Email: ");
        email=tcld.next().trim();
        System.out.print("Especialidad: ");
        especialidad = tcld.next().trim();
        
        if (rut.isEmpty()|| rut.indexOf("-") != rut.lastIndexOf("-") || rut.endsWith("-") ||  rut.startsWith("-") || //validar si el rut termina o comienza en "-" o está vacio o no tiene nada despues del guión
                nombre.isEmpty() || //validar si nombre está vacio
                email.isEmpty() ||   email.indexOf("@") != email.lastIndexOf("@") || email.endsWith("@")  || email.startsWith("@") || //validar si email está vacio o tiene solo un @ o está mal posicionado
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
        String rutSindigito; //rutSindigito es el tur sin el "-" ni el numero que siguiente
            
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
                (rut.split("-")[0].lastIndexOf(".") - rut.split("-")[0].indexOf(".")-1)==3 && 
                ( rut.split("-")[0].indexOf(".") == 1 ||  rut.split("-")[0].indexOf(".") == 2)   ) {
              
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
        
        //verificar que nombre no contenga caracteres invalidos
        for (int i = 0; i < nombre.length(); i++) {
            char c = nombre.charAt(i);
            // Si no está entre a y z, ni entre A y Z, ni es un espacio
            if (!((c >= 'a' && c <= 'z') || (c >= 'A' && c <= 'Z') || c == ' ')) {
                System.out.println("\n\nUno o mas datos son NO validos\n");
                return;
            }
        }
        
        //verificar que especialidad no contenga caracteres invalidos
        for (int i = 0; i < especialidad.length(); i++) {
            char c = especialidad.charAt(i);
            // Si no está entre a y z, ni entre A y Z, ni es un espacio
            if (!((c >= 'a' && c <= 'z') || (c >= 'A' && c <= 'Z') || c == ' ')) {
                System.out.println("\n\nUno o mas datos son NO validos\n");
                return;
            }
        }
                
        //Todo validado, se envia datos para crear veterinario
        control.creaVeterinario(rut, nombre, email, especialidad);
        System.out.println("\n\nEl cliente se ha creado satisfactoriamente\n");            
        
    }//fin creaVet

    private void creaMascota() {
        System.out.println("\nCreando una nueva mascota...");
        
        //Datos
        String nombre, especie, raza, rutDueno;
        Clase clase;
        LocalDate fecha;
        
        //ingreso de datos
        System.out.print("Rut dueno: ");
        rutDueno= tcld.next().trim();
        System.out.print("Nombre de la mascota: ");
        nombre=tcld.nextLine().trim();
        fecha=leeFecha();  
        clase=leeClase();
        System.out.print("Especie: ");
        especie=tcld.next().trim();
        System.out.print("Raza: ");
        raza=tcld.next().trim();
        
        if (clase==null || fecha==null){    //se verifica de que datos clase y fecha no esten vacios
            return;
        }
        
        
        //verificar que nombre no contenga caracteres invalidos
        for (int i = 0; i < nombre.length(); i++) {
            char c = nombre.charAt(i);
            // Si no está entre a y z, ni entre A y Z, ni es un espacio
            if (!((c >= 'a' && c <= 'z') || (c >= 'A' && c <= 'Z') || c == ' ')) {
                System.out.println("\n\nUno o mas datos son NO validos\n");
                return;
            }
        }
        
        //verificar que especie no contenga caracteres invalidos
        for (int i = 0; i < especie.length(); i++) {
            char c = especie.charAt(i);
            // Si no está entre a y z, ni entre A y Z, ni es un espacio
            if (!((c >= 'a' && c <= 'z') || (c >= 'A' && c <= 'Z') || c == ' ')) {
                System.out.println("\n\nUno o mas datos son NO validos\n");
                return;
            }
        }
        
        //verificar que raza no contenga caracteres invalidos
        for (int i = 0; i < raza.length(); i++) {
            char c = raza.charAt(i);
            // Si no está entre a y z, ni entre A y Z, ni es un espacio
            if (!((c >= 'a' && c <= 'z') || (c >= 'A' && c <= 'Z') || c == ' ')) {
                System.out.println("\n\nUno o mas datos son NO validos\n");
                return;
            }
        }
        
        //Todo validado, se envia datos para crear mascota
        control.creaMascota(nombre, fecha, clase, especie, raza, rutDueno);
        System.out.println("\n\nNueva mascota, creada satisfactoriamente\n");
        
        
    }
    
        
    private void listaMascotasDeUnaClase(){ //imprimir String[][] que viene listo
        
        //inicio datos
        String[][] lista;
        Clase clase;
        
        //ingreso datos
        clase=leeClase();
        lista=control.listaMascotas(clase);
        
        if (clase==null){    //se verifica de que datos clase no este vacio
            System.out.println("No existen mascotas de la clase indicada");
            return;
        }
        
        //imprimo la cabecera (ojo la ortografia)
        System.out.println("Listado Mascotas de la Clase " + clase);
        System.out.println("--------------------------------" + ((clase==Reptil)? "---":((clase==Mamifero)? "-----":" ")));
        System.out.print("\nNombre        \tEspecie        \tRaza           \tEdad(anios)    \tNombre dueno");
        
        //se imprime la matriz dada
        for(int i=0;i<lista.length;i++){
            
            System.out.println(" "); //se imprime espacio para hacer un salto de linea
            
            for (int j=0;j<lista[i].length;j++){
                
                System.out.print(lista[i][j]);
                int contador=(15-lista[i][j].length());
              
                    //se ordena dependiendo de la cantidad de letras
                while(contador!=0){                
                  System.out.print(" "); 
                  contador--;
                }
                System.out.print("\t");
            }
        }
    }
        
    private void listaDatosVeterinarios(){//imprimir String[][] que viene listo
        System.out.println("\nGenerando Listado de Veterinarios...\n\n");
        
        //inicio datos
        String[][] lista;
        Clase clase;
        
        //ingreso datos
        clase=leeClase();
        lista=control.listaDatosVeterinario();
        
        //imprimo la cabecera (ojo la ortografia)
        System.out.println("Listado de Veterinarios");
        System.out.println("-----------------------");
        System.out.print("\nRut                          \tNombre                        \tEmail                         \tEspecialidad");
        
        //se imprime la matriz dada
        for(int i=0;i<lista.length;i++){
            
            System.out.println(" "); //se imprime espacio para hacer un salto de linea
            
            for (int j=0;j<lista[i].length;j++){
                
                System.out.print(lista[i][j]);
                int contador=(30-lista[i][j].length());
              
                    //se ordena dependiendo de la cantidad de letras
                while(contador>0){                
                    System.out.print(" "); 
                    contador--;
                }
                System.out.print("\t");
            }
        }
    }
/*    
    private void finaliza(){
        
    }
*/
    
    // ver si esta bien
    private Clase leeClase(){
        
        //la variable clase es la clase que retorna y la clas es la que lee cual clase quiere.
        Clase clase;
        String clas;
        
        //ingreso clase
        System.out.print("Clase (Ave, Mamifero, Pez, Reptil): ");
        clas=tcld.next().trim();
        
        if (clas.equalsIgnoreCase("ave")){              //evalua si es ave, de ser asi se le da el valor correspondiente
            clase=Ave;
            
        }else if(clas.equalsIgnoreCase("mamifero")){    //evalua si es mamifero, de ser asi se le da el valor correspondiente   
            clase=Mamifero;
            
        }else if(clas.equalsIgnoreCase("pez")){         //evalua si es pez, de ser asi se le da el valor correspondiente
            clase=Pez;
            
        }else if(clas.equalsIgnoreCase("reptil")){      //evalua si es reptil, de ser asi se le da el valor correspondiente
            clase=Reptil;
            
        }else{
            return null;        
        }
        
        return clase;
    }
    
    private LocalDate leeFecha(){
        
    //inicio datos de fechas
    int mes, año, dia;
    String fecha;
    LocalDate fechaNac;
    
    // ingreso datos
    System.out.print("Fecha de nacimiento de la mascota (dd/mm/aaaa): ");
    fecha=tcld.nextLine().trim();
    
    //validar si los "/" estan bien posicionados y tiene el formato correcto
    if ((fecha.indexOf("/")) == 2 &&
        (fecha.lastIndexOf("/") - fecha.indexOf("/"))==3 &&
        (fecha.length() - 1 - fecha.lastIndexOf("/")) == 4){
        
        try{
            
        dia=Integer.parseInt(fecha.split("/")[0]);
        mes=Integer.parseInt(fecha.split("/")[1]);
        año=Integer.parseInt(fecha.split("/")[2]);
        }catch(Exception e){
            //devolver error
            return null;
        }
        
        fechaNac=LocalDate.of(año, mes, dia);
        
    }else{
        return null;
    }
    
    return fechaNac;
    }      
}
