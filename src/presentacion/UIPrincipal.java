/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package presentacion;
import control.Controlador;
import excepciones.RegistroAtencionesException;
import static modelo.Clase.*;
import java.time.LocalDate;
import java.util.Scanner;
import java.util.logging.Level;
import java.util.logging.Logger;
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
        principal.inicia();
        principal.menuMain();
        principal.finaliza();
    }
    
    //metodos
    private void inicia()   {
           try {
                control.leeDatosPersistentes();
            } catch (RegistroAtencionesException ex) {
                System.out.println(ex.getMessage());
            }finally{
                tcld.useDelimiter("\n");
            }
    } //listo
    
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
            System.out.print("\tIngrese opcion: ");
            
            try {
                opcion = Integer.parseInt(tcld.next());
            
                switch (opcion){//solo para que tome el valor ingresado sin importar espacios
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
                        listaMascotasDeUnaClase();
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
                        listaDatosVeterinarios();
                        break;
                    case 10:
                        break;
                    default:
                        System.out.println("\nElija una opcion valida\n");
                }
            } catch (NumberFormatException e) {
                System.out.println("\nElija una opcion valida\n");
            }
        }while(opcion != 10);
    }//listo

    private void creaCliente() {
       String rut, nombre, email;
        try {    
            System.out.println("\nCreando un nuevo cliente...");
            //ingreso de datos
            
            System.out.print("Rut: ");
            rut=tcld.next().trim();
            System.out.print("Nombre: ");
            nombre=tcld.next().trim();
            System.out.print("Email: ");
            email=tcld.next().trim();
        
                //valido espacios vacíos
            if (!(rut.isEmpty()|| rut.indexOf("-") != rut.lastIndexOf("-") || rut.endsWith("-") ||  rut.startsWith("-") || //validar si el rut termina o comienza en "-" o está vacio o no tiene nada despues del guión
                nombre.isEmpty() || //validar si nombre está vacio
                email.isEmpty() ||   email.indexOf("@") != email.lastIndexOf("@") || email.endsWith("@")  || email.startsWith("@") || //validar si email está vacio o tiene solo un @ o está mal posicionado
                !(email.lastIndexOf(".") > email.indexOf("@")  && email.lastIndexOf(".")-1 != email.indexOf("@"))) || email.endsWith(".")) { //validar que el email tenga un . despues del @ y que tenga texto antes y despues de él
            
                //validar Rut termina de 0-9 o k
                boolean verificador=false;                       
                for(int i=0 ; i < 10; i++){
                    if (rut.split("-")[1].equalsIgnoreCase(Integer.toString(i)) || rut.split("-")[1].equalsIgnoreCase("k")) {
                        verificador=true;
                    }
                }
                if (verificador) {
                    //ponerle puntos al rut si es que no los tiene
                    if (rut.indexOf(".")==-1 && (rut.length() == 9 || rut.length() == 10)){  
                        if (rut.length() == 9){
                            rut = rut.substring(0,1) + "." + rut.substring(1,4) + "." + rut.substring(4);
                        }else if(rut.length() == 10){
                            rut= rut.substring(0,2) + "." + rut.substring(2,5) + "." + rut.substring(5);
                        }
                    }
                    //probar si los digitos son números
                    if((rut.indexOf(".",rut.indexOf(".") + 1) == rut.lastIndexOf(".")) && (rut.indexOf(".")== 1 || rut.indexOf(".")== 2) && (rut.lastIndexOf(".")== 6 || rut.lastIndexOf(".")== 5)){ //si tiene puntos entonces verificar que al menos tenga dos
                        Integer.parseInt(rut.split("-")[0].replace('.', '0'));
                    }else{
                        System.out.println("\n\nUno o mas datos son NO validos\n");
                        return;
                    } 
                }else{
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
            }else{
                System.out.println("\n\nUno o mas datos son NO validos\n");
                return;
            }
        
            //se confirma que los datos estan buenos ;)
            control.creaCliente(rut, nombre, email);
            System.out.println("\n\nEl cliente se ha creado satisfactoriamente\n");
       
        }catch (NumberFormatException e) {
            System.out.println("\n\nUno o mas datos son NO validos\n");
        } 
        catch (RegistroAtencionesException e) {
            System.out.println(e.getMessage());
        }
    }//fin crea cliente

    private void creaVeterinario(){                                            
        try {                                    //preguntar julio solo valido espacios vacios
            System.out.println("\nCreando un nuevo veterinario...");
        
            //Datos
            String rut, nombre, email, especialidad;
        
            //ingreso de datos
            System.out.print("Rut: ");
            rut=tcld.next().trim();
            System.out.print("Nombre: ");
            nombre=tcld.next().trim();
            System.out.print("Email: ");
            email=tcld.next().trim();
            System.out.print("Especialidad: ");
            especialidad = tcld.next().trim();
        
            if (!rut.isEmpty() && rut.indexOf("-") == rut.lastIndexOf("-")
                    && //validar si el rut termina o comienza en "-" o está vacio o no tiene nada despues del guión
                    !nombre.isEmpty()
                    &&
                    !especialidad.isEmpty()
                    && //validar si nombre está vacio
                    !email.isEmpty() && email.indexOf("@") == email.lastIndexOf("@")
                    && //validar si email está vacio o tiene solo un @ o está mal posicionado
                    email.lastIndexOf(".") > email.indexOf("@") && email.lastIndexOf(".") - 1 != email.indexOf("@") && !email.endsWith(".")) { //validar que el email tenga un . despues del @ y que tenga texto antes y despues de él
                    
                //validar Rut termina de 0-9 o k
                boolean verificador = false;
                for (int i = 0; i < 10; i++) {
                    if (rut.split("-")[1].equalsIgnoreCase(Integer.toString(i)) || rut.split("-")[1].equalsIgnoreCase("k")) {
                        verificador = true;
                    }
                }
                if (verificador) {
                    //ponerle puntos al rut si es que no los tiene
                    if (rut.indexOf(".") == -1 && (rut.length() == 9 || rut.length() == 10)) {
                        if (rut.length() == 9) {
                            rut = rut.substring(0, 1) + "." + rut.substring(1, 4) + "." + rut.substring(4);
                        } else if (rut.length() == 10) {
                            rut = rut.substring(0, 2) + "." + rut.substring(2, 5) + "." + rut.substring(5);
                        }
                    }
                    //probar si los digitos son números
                    if ((rut.indexOf(".", rut.indexOf(".") + 1) == rut.lastIndexOf(".")) && (rut.indexOf(".") == 1 || rut.indexOf(".") == 2) && (rut.lastIndexOf(".") == 6 || rut.lastIndexOf(".") == 5)) { //si tiene puntos entonces verificar que al menos tenga dos
                        Integer.parseInt(rut.split("-")[0].replace('.', '0'));
                    } else {
                        System.out.println("\n\nUno o mas datos son NO validos\n");
                        return;
                    }
                } else {
                    System.out.println("\n\nUno o mas datos son NO validos\n");
                    return;
                }
                //verificar que nombre y especialidad no contenga caracteres invalidos
                String nomEspecialidad = nombre + especialidad; 
                for (int i = 0; i < nomEspecialidad.length(); i++) {
                    char c = nomEspecialidad.charAt(i);
                    // Si no está entre a y z, ni entre A y Z, ni es un espacio
                    if (!((c >= 'a' && c <= 'z') || (c >= 'A' && c <= 'Z') || c == ' ')) {//no funciona con ñ
                        System.out.println("\n\nUno o mas datos son NO validos\n");
                        return;
                    }
                }
            } else {
                System.out.println("\n\nUno o mas datos son NO validos\n");
                return;
            }
                
            //Todo validado, se envia datos para crear veterinario
            control.creaVeterinario(rut, nombre, email, especialidad);
            System.out.println("\n\nEl veterinario se ha creado satisfactoriamente\n");            
        }catch (RegistroAtencionesException ex) {
            System.out.println(ex.getMessage());
        }
    }

    private void creaMascota() {
        System.out.println("\nCreando una nueva mascota...");
        
        try {
        //Datos
        String nombre, especie, raza, rutDueno;
        Clase clase;
        LocalDate fecha;
        
            //ingreso de datos
            System.out.print("Rut dueno: ");
            rutDueno= tcld.next().trim();
            System.out.print("Nombre de la mascota: ");
            nombre=tcld.next().trim();
            fecha=leeFecha();  
            clase=leeClase();
            System.out.print("Especie: ");
            especie=tcld.next().trim();
            System.out.print("Raza: ");
            raza=tcld.next().trim();
        
            if (clase!=null || fecha !=null){    //se verifica de que datos clase y fecha no esten vacios
                String[] verificar = new String[3];
                verificar[0] = nombre;
                verificar[1] = especie;
                verificar[2] = raza;
                //verificar que nombre, especie y raza no contenga caracteres invalidos
                for (String Averificar : verificar) {
                        for (int i = 0; i < Averificar.length(); i++) {
                        char c = nombre.charAt(i);
                        // Si no está entre a y z, ni entre A y Z, ni es un espacio
                        if (!((c >= 'a' && c <= 'z') || (c >= 'A' && c <= 'Z') || c == ' ')) {
                            System.out.println("\n\nUno o mas datos son NO validos\n");
                            return;
                        }
                    }   
                }
                //valida rut
                 if (!rutDueno.isEmpty() && rutDueno.indexOf("-") == rutDueno.lastIndexOf("-")) {
                    boolean verificador = false;
                    for (int i = 0; i < 10; i++) {
                        if (rutDueno.split("-")[1].equalsIgnoreCase(Integer.toString(i)) || rutDueno.split("-")[1].equalsIgnoreCase("k")) {
                            verificador = true;
                        }
                    }
                    if (verificador) {
                        //ponerle puntos al rut si es que no los tiene
                        if (rutDueno.indexOf(".") == -1 && (rutDueno.length() == 9 || rutDueno.length() == 10)) {
                            if (rutDueno.length() == 9) {
                                rutDueno = rutDueno.substring(0, 1) + "." + rutDueno.substring(1, 4) + "." + rutDueno.substring(4);
                            } else if (rutDueno.length() == 10) {
                                rutDueno = rutDueno.substring(0, 2) + "." + rutDueno.substring(2, 5) + "." + rutDueno.substring(5);
                            }
                        }
                        //probar si los digitos son números
                        if ((rutDueno.indexOf(".", rutDueno.indexOf(".") + 1) == rutDueno.lastIndexOf(".")) && (rutDueno.indexOf(".") == 1
                                || rutDueno.indexOf(".") == 2)
                                && (rutDueno.lastIndexOf(".") == 6 || rutDueno.lastIndexOf(".") == 5)) { //si tiene puntos entonces verificar que al menos tenga dos
                            Integer.parseInt(rutDueno.split("-")[0].replace('.', '0'));
                        } else {
                            System.out.println("\n\nUno o mas datos son NO validos\n");
                            return;
                        }
                    } else {
                        System.out.println("\n\nUno o mas datos son NO validos\n");
                        return;
                    }
                }
                //Todo validado, se envia datos para crear mascota
                control.creaMascota(nombre, fecha, clase, especie, raza, rutDueno);
                System.out.println("\n\nNueva mascota, creada satisfactoriamente\n");
            }else{
                System.out.println("\n\nUno o mas datos son NO validos\n");
            }
        } catch (RegistroAtencionesException e) {
            System.out.println(e.getMessage());
        }    
    }
    
        
    private void listaMascotasDeUnaClase(){ //imprimir String[][] que viene listo
        
        //inicio datos
        String[][] lista;
        Clase clase;
        
        //ingreso datos
        System.out.println("\nGenerando Listado de Mascotas de una clase...");
        clase=leeClase();
        
        if (clase!=null){    //se verifica de que datos clase no este vacio
            lista=control.listaMascotas(clase);
            if (lista.length!=0) {
                //imprimo la cabecera (ojo la ortografia)
                System.out.println("\nListado Mascotas de la Clase " + clase);
                System.out.println("--------------------------------" + ((clase==Reptil)? "---":((clase==Mamifero)? "-----":" ")));
                System.out.print("\nNombre        \tEspecie        \tRaza           \tEdad(anios)    \tNombre dueno");
        
                //se imprime la matriz dada
                for(int i=0;i<lista.length;i++){
                    System.out.println(""); //se imprime espacio para hacer un salto de linea
                    for (int j=0;j<lista[i].length;j++){
                        System.out.print(lista[i][j]);
                        int contador=(15-lista[i][j].length());
                        //se ordena dependiendo de la cantidad de letras
                        while(contador>0){                
                            System.out.print(" "); 
                            contador--;
                        }
                        System.out.print("\t");
                    }
                }
            return;
            }
        }
        System.out.println("\nNo existen mascotas de la clase indicada\n");
    }
        
    private void listaDatosVeterinarios(){//imprimir String[][] que viene listo
        System.out.println("\nGenerando Listado de Veterinarios...\n\n");
        
        //inicio datos
        String[][] lista;
        
        //ingreso datos
        lista=control.listaDatosVeterinario();
        
        if (lista.length!=0) {
            //imprimo la cabecera (ojo la ortografia)
            System.out.println("Listado de Veterinarios");
            System.out.println("-----------------------");
            System.out.print("\nRut\t\tNombre\t\tEmail\t\tEspecialidad\n");
        
            //se imprime la matriz dada
            for(int i=0;i<lista.length;i++){
                for (int j=0;j<lista[i].length;j++){
                    System.out.print(lista[i][j] + ((lista[i][j].length() >= 8)? "\t" : "\t\t"));
                }
                System.out.println("");
            }
        }else{
            System.out.println("No existen veterinarios registrados en el sistema.");
        }
    }
    
    private void finaliza(){
        try {
            control.escribeDatosPersistentes();
        } catch (RegistroAtencionesException e) {
            System.out.println(e.getMessage());
        }finally{
            System.out.println("\n\t***FIN DE LA EJECUCION***");
        }
    }
    
    // ver si esta bien
    private Clase leeClase(){
        String clas;
        
        //ingreso clase
        System.out.print("Clase (Ave, Mamifero, Pez, Reptil): ");
        clas=tcld.next().trim();
        
        if (clas.equalsIgnoreCase("ave")){              //evalua si es ave, de ser asi se le da el valor correspondiente
            return Ave;
            
        }else if(clas.equalsIgnoreCase("mamifero")){    //evalua si es mamifero, de ser asi se le da el valor correspondiente   
            return Mamifero;
            
        }else if(clas.equalsIgnoreCase("pez")){         //evalua si es pez, de ser asi se le da el valor correspondiente
            return Pez;
            
        }else if(clas.equalsIgnoreCase("reptil")){      //evalua si es reptil, de ser asi se le da el valor correspondiente
            return Reptil;
            
        }
        return null;        
    }
    
    private LocalDate leeFecha(){
        
    //inicio datos de fechas
    int mes, año, dia;
    String fecha;
    LocalDate fechaNac;
    
    // ingreso datos
    System.out.print("Fecha de nacimiento de la mascota (dd/mm/aaaa): ");
    fecha=tcld.next().trim();
    
    //validar si los "/" estan bien posicionados y tiene el formato correcto
    if ((fecha.indexOf("/")) == 2 &&
        (fecha.lastIndexOf("/") - fecha.indexOf("/"))==3 &&
        (fecha.length() - 1 - fecha.lastIndexOf("/")) == 4){
        
        try{
            
        dia=Integer.parseInt(fecha.split("/")[0]);
        mes=Integer.parseInt(fecha.split("/")[1]);
        año=Integer.parseInt(fecha.split("/")[2]);
        }catch(NumberFormatException e){
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
