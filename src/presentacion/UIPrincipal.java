/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package presentacion;

import control.Controlador;
import excepciones.RegistroAtencionesException;
import java.time.DateTimeException;
import modelo.Clase;
import static modelo.Clase.*;
import java.time.LocalDate;
import java.util.Scanner;

/**
 *
 * @author Julio Ignacio Monroy San Martin
 * @author Camilo Ignacio Vazques Rodriguez
 */
public class UIPrincipal {

    //atributos
    private static UIPrincipal instance;
    private Scanner tcld;

    //asociacion
    private Controlador control;

    //constructor 
    private UIPrincipal() {
        tcld = new Scanner(System.in);
        control = Controlador.getInstance();
    }

    //metodo singleton
    public static UIPrincipal getInstance() {
        if (instance == null) {
            instance = new UIPrincipal();
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
    private void inicia() {
        try {
            control.leeDatosPersistentes();
        } catch (RegistroAtencionesException ex) {
            System.out.println(ex.getMessage());
        } finally {
            tcld.useDelimiter("\n");
        }
    }

    private void menuMain() {
        int opcion = 0;
        do {
            System.out.println("\nMENU PRINCIPAL");
            System.out.println("==============");
            System.out.println("1. Crear un cliente");
            System.out.println("2. Crear un veterinario");
            System.out.println("3. Crear una mascota");
            System.out.println("4. Listar mascota de una clase");
            System.out.println("5. Agregar una atencion a una mascota");
            System.out.println("6. Listar atenciones de mascota de un cliente");
            System.out.println("7. Listar Nro.atenciones para una clase en un lapso de tiempo");
            System.out.println("8. Listar datos y nro.atenciones de veterinarios");
            System.out.println("9. Salir");
            System.out.print("\tIngrese opcion: ");

            try {
                opcion = Integer.parseInt(tcld.next());

                switch (opcion) {//solo para que tome el valor ingresado sin importar espacios
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
                        agregaAtencionAMascota();
                        break;
                    case 6:
                        listaAtencionesDeMascotasDeUnCliente();
                        break;
                    case 7:
                        muestraNroAtencionesPorClaseEntre();
                        break;
                    case 8:
                        //falta
                        listaDatosVeterinarios();
                        break;
                    case 9:
                        break;
                    default:
                        System.out.println("\nElija una opcion valida\n");
                }
            } catch (NumberFormatException e) {
                System.out.println("\nElija una opcion valida\n");
            }

        } while (opcion != 9);
    }

    private void creaCliente() {
        String rut, nombre, email;
        try {
            System.out.println("\nCreando un nuevo cliente...");
            //ingreso de datos

            System.out.print("Rut: ");
            rut = tcld.next().trim();
            System.out.print("Nombre: ");
            nombre = tcld.next().trim();
            System.out.print("Email: ");
            email = tcld.next().trim();

            //valido espacios vacíos
            if (!rut.isEmpty() && rut.indexOf("-") == rut.lastIndexOf("-") && rut.indexOf("-") != -1
                    && //validar si el rut termina o comienza en "-" o está vacio o no tiene nada despues del guión
                    !nombre.isEmpty() && nombre != null
                    && //validar si nombre está vacio
                    !email.isEmpty() && email != null && email.indexOf("@") == email.lastIndexOf("@") && email.lastIndexOf("@") != -1
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
            } else {
                System.out.println("\n\nUno o mas datos son NO validos\n");
                return;
            }

            //se confirma que los datos estan buenos ;)
            control.creaCliente(rut, nombre, email);
            System.out.println("\n\nEl cliente se ha creado satisfactoriamente\n");

        } catch (NumberFormatException e) {
            System.out.println("\n\nUno o mas datos son NO validos\n");
        } catch (RegistroAtencionesException e) {
            System.out.println("\nError! "+e.getMessage()+"\n");
        }
    }

    private void creaVeterinario() {
        try {                                    
            System.out.println("\nCreando un nuevo veterinario...");

            //Datos
            String rut, nombre, email, especialidad;

            //ingreso de datos
            System.out.print("Rut: ");
            rut = tcld.next().trim();
            System.out.print("Nombre: ");
            nombre = tcld.next().trim();
            System.out.print("Email: ");
            email = tcld.next().trim();
            System.out.print("Especialidad: ");
            especialidad = tcld.next().trim();

            if (!rut.isEmpty() && rut.indexOf("-") == rut.lastIndexOf("-") && rut != null && rut.indexOf("-") != -1
                    && //validar si el rut termina o comienza en "-" o está vacio o no tiene nada despues del guión
                    !nombre.isEmpty() && nombre != null
                    && !especialidad.isEmpty() && especialidad != null
                    && //validar si nombre está vacio
                    !email.isEmpty() && email != null && email.indexOf("@") == email.lastIndexOf("@") && email.lastIndexOf("@") != -1
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
            } else {
                System.out.println("\n\nUno o mas datos son NO validos\n");
                return;
            }

            //Todo validado, se envia datos para crear veterinario
            control.creaVeterinario(rut, nombre, email, especialidad);
            System.out.println("\n\nEl veterinario se ha creado satisfactoriamente\n");
        }catch (NumberFormatException e) {
            System.out.println("\n\nUno o mas datos son NO validos\n"); 
        }catch (RegistroAtencionesException ex) {
            System.out.println("\nError! " + ex.getMessage() + "\n");
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
            rutDueno = tcld.next().trim();
            System.out.print("Nombre de la mascota: ");
            nombre = tcld.next().trim();
            System.out.print("Fecha de nacimiento de la mascota (dd/mm/aaaa): ");
            fecha = leeFecha();
            clase = leeClase();
            System.out.print("Especie: ");
            especie = tcld.next().trim();
            System.out.print("Raza: ");
            raza = tcld.next().trim();

            if (clase != null && fecha != null && nombre != null && especie != null && raza != null
                    && !(nombre.equals("") && especie.equals("") && raza.equals(""))) {    //se verifica de que datos clase y fecha no esten vacios
                
                if (!(fecha.isBefore(LocalDate.now()))){
                    System.out.println("\n\nUno o mas datos son NO validos\n");
                    return;
                }
                //valida rut
                if (!rutDueno.isEmpty() && rutDueno.indexOf("-") == rutDueno.lastIndexOf("-") && rutDueno != null && rutDueno.indexOf("-") != -1) {
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
                } else {
                    System.out.println("\n\nUno o mas datos son NO validos\n");
                }
                //Todo validado, se envia datos para crear mascota
            } else {
                System.out.println("\n\nUno o mas datos son NO validos\n");
            }
            control.creaMascota(nombre, fecha, clase, especie, raza, rutDueno);
            System.out.println("\n\nNueva mascota, creada satisfactoriamente\n");

        }catch (NumberFormatException e) {
            System.out.println("\n\nUno o mas datos son NO validos\n"); 
        }catch (RegistroAtencionesException ex) {
            System.out.println("\nError! " + ex.getMessage() + "\n");
        }

    }

    private void listaMascotasDeUnaClase() { //imprimir String[][] que viene listo

        //inicio datos
        String[][] lista;
        Clase clase;

        //ingreso datos
        System.out.println("\nGenerando Listado de Mascotas de una clase...");
        clase = leeClase();

        if (clase != null) {    //se verifica de que datos clase no este vacio
            lista = control.listaMascotas(clase);
            if (lista.length != 0) {
                //imprimo la cabecera
                System.out.println("\nListado Mascotas de la Clase " + clase);
                System.out.println("--------------------------------" + ((clase == Reptil) ? "---" : ((clase == Mamifero) ? "-----" : " ")));
                System.out.printf("%-15s %-15s %-15s %-15s %-15s %-15s %-5s\n", "Nombre","Especie","Raza","Edad(anios)", "Rut dueno", "Nombre dueno", "Nro.At");

                //se imprime la matriz dada
                for (int i = 0; i < lista.length; i++) {
                    System.out.printf("%-15s %-15s %-15s %-15s %-15s %-15s %-5s\n", lista[i][0], lista[i][1], lista[i][2], lista[i][3], lista[i][4], lista[i][5], lista[i][6]);
                }
                System.out.println("\n");
                return;
            }
        }
        System.out.println("\nNo existen mascotas de la clase indicada\n");
    }
    
    private void agregaAtencionAMascota() {
        try {
            System.out.println("\nAgregando una nueva atencion a una mascota...");
            String[] rut = new String[2];
            String nombre;
            String diag;
            String obs;

            System.out.print("Rut dueno: ");
            rut[0] = tcld.next().trim(); //rut dueno
            System.out.print("Nombre de la mascota: ");
            nombre = tcld.next().trim();
            System.out.print("Rut veterinario: ");
            rut[1] = tcld.next().trim(); //rut vet
            System.out.print("Diagnostico: ");
            diag = tcld.next().trim();
            System.out.print("Observacion: ");
            obs = tcld.next().trim();

            //valido vacios o null de nombre, diagnostico y observaciones
            if (nombre == null || nombre.isEmpty() || nombre.length() == 0
                    || diag == null || diag.isEmpty() || diag.length() == 0
                    || obs == null || obs.isEmpty() || obs.length() == 0) {
                System.out.println("\n\nUno o mas datos son NO validos\n");
                return;
            }

            for (int i = 0; i < rut.length; i++) {
                boolean verificador = false;
                    if (!rut[i].isEmpty() && rut[i].indexOf("-") == rut[i].lastIndexOf("-") && rut[i] != null && rut[i].contains("-")) {
                        for (int k = 1; k < 10; k++) {
                            if (Character.getNumericValue(rut[i].charAt(rut[i].length() - 1)) == k
                                    || rut[i].charAt(rut[i].length() - 1) == 'k'
                                    || rut[i].charAt(rut[i].length() - 1) == 'K') {
                                verificador = true;
                            }
                        }
                        if (verificador) {
                            //ponerle puntos al rut si es que no los tiene
                            if (!rut[i].contains(".") && (rut[i].length() == 9 || rut[i].length() == 10)) {
                                if (rut[i].length() == 9) {
                                    rut[i] = rut[i].substring(0, 1) + "." + rut[i].substring(1, 4) + "." + rut[i].substring(4);
                                } else if (rut[i].length() == 10) {
                                    rut[i] = rut[i].substring(0, 2) + "." + rut[i].substring(2, 5) + "." + rut[i].substring(5);
                                }
                            }
                            //probar si los digitos son números
                            if ((rut[i].indexOf(".", rut[i].indexOf(".") + 1) == rut[i].lastIndexOf(".")) && (rut[i].indexOf(".") == 1
                                    || rut[i].indexOf(".") == 2)
                                    && (rut[i].lastIndexOf(".") == 6 || rut[i].lastIndexOf(".") == 5)) { //si tiene puntos entonces verificar que al menos tenga dos
                                Integer.parseInt(rut[i].split("-")[0].replace('.', '0'));
                            } else {
                                System.out.println("\n\nUno o mas datos son NO validos 1\n");
                                return;
                            }
                        } else {
                            System.out.println("\n\nUno o mas datos son NO validos2\n");
                            return;
                        }
                    } else {
                        System.out.println("\n\nUno o mas datos son NO validos3\n");
                        return;
                    }
            }
            control.agregaAtencion(rut[0], rut[1], nombre, diag, obs);
            System.out.println("La atencion a la mascota se ha agregado exitosamente");
        } catch (NumberFormatException e) {
            System.out.println("\n\nUno o mas datos son NO validos\n");
        } catch (RegistroAtencionesException ex) {
            System.out.println(ex.getMessage());
        }
    }
    
    private void listaAtencionesDeMascotasDeUnCliente() {
        //
        System.out.println("\nGenerando Listado de la Ultima Atencion de las Mascotas de un CLiente...");
       try { 
        String rut;
        String[][] datosAtenciones;

        System.out.print("Rut dueno:");
        rut = tcld.next().trim();

        if (rut.indexOf(".") == -1 && (rut.length() == 9 || rut.length() == 10)) {
            if (rut.length() == 9) {
                rut = rut.substring(0, 1) + "." + rut.substring(1, 4) + "." + rut.substring(4);
            } else if (rut.length() == 10) {
                rut = rut.substring(0, 2) + "." + rut.substring(2, 5) + "." + rut.substring(5);
            }
        }
        
            datosAtenciones = control.listaAtencionesMascotasDe(rut);

            if (datosAtenciones.length != 0) {
                System.out.println("\n\nListado de Atenciones de Mascotas del Cliente");
                System.out.println("----------------------------------------------");
                System.out.printf("%-15s %-10s %-20s %-20s %-15s %-20s\n","Nom.mascota","Fecha At.", "Diagnostico", "Observaciones", "Rut vet.", "Nombre Vet." );
                
                for (String[] datosArr : datosAtenciones) {
                    if (datosArr[1] != null) {
                        System.out.printf("%-15s %-10s %-20s %-20s %-15s %-20s\n",datosArr[0], datosArr[1], datosArr[2], datosArr[3], datosArr[4], datosArr[5]);
                    }else{
                        System.out.printf("%-15s %-10s %-20s %-20s %-15s %-20s\n",datosArr[0], "S/At.", "S/At.", "S/At.", "S/At.", "S/At.");
                    }
                }
                System.out.println("\n");
            } else {
                System.out.println("El cliente no presenta mascotas registradas");
            }
        } catch (RegistroAtencionesException ex) {
            System.out.println("Error" + ex.getMessage());
        }
    }
    
    private void muestraNroAtencionesPorClaseEntre(){
        System.out.println("Generando Listado Atenciones por Clase, Especie y Raza en un Lapso de Tiempo...");

        Clase clase = leeClase();
        LocalDate fechaInicio;
        LocalDate fechaFin;

        System.out.print("Fecha inicio del intervalo (dd/mm/aaaa): ");
        fechaInicio = leeFecha();
        System.out.print("Fecha fin del intervalo (dd/mm/aaaa): ");
        fechaFin = leeFecha();

        if (fechaInicio != null || fechaFin != null || clase != null) {
            if ((fechaInicio.isBefore(LocalDate.now()) || fechaInicio.equals(LocalDate.now()))
                    && (fechaFin.isBefore(LocalDate.now()) || fechaFin.equals(LocalDate.now()))) {
                int cantidad = control.calculaNroAtencionesPara(clase, fechaInicio, fechaFin);
                System.out.print("\n\nNumero Atenciones Registradas: " + cantidad);
            }
        } else {
            System.out.println("\n\nUno o mas datos son NO validos\n");
        }
    }

    private void listaDatosVeterinarios() {//imprimir String[][] que viene listo
        System.out.println("\nGenerando Listado de Veterinarios...\n\n");

        //inicio datos
        String[][] lista;

        //ingreso datos
        lista = control.listaDatosYNroAtencionesDeVeterinario();

        if (lista.length != 0) {
            //imprimo la cabecera (ojo la ortografia)
            System.out.println("Listado de Veterinarios");
            System.out.println("-----------------------");
            System.out.printf("%-15s %-20s %-25s %-20s %-10s\n", "Rut", "Nombre", "Email", "Especialidad", "Nro.At" );

            //se imprime la matriz dada
            for (int i = 0; i < lista.length; i++) {
                    System.out.printf("%-15s %-20s %-25s %-20s %-10s\n",lista[i][0], lista[i][1], lista[i][2], lista[i][3], lista[i][4]);
            }
        } else {
            System.out.println("No existen veterinarios registrados en el sistema.");
        }
    }

    private void finaliza() {
        try {
            control.escribeDatosPersistentes();
        } catch (RegistroAtencionesException e) {
            System.out.println(e.getMessage());
        } finally {
            System.out.println("\n\t***FIN DE LA EJECUCION***");
        }
    }

    private Clase leeClase() {
        String clas;

        //ingreso clase
        System.out.print("Clase (Ave, Mamifero, Pez, Reptil): ");
        clas = tcld.next().trim();

        if (clas.equalsIgnoreCase("ave")) {              //evalua si es ave, de ser asi se le da el valor correspondiente
            return Ave;
        } else if (clas.equalsIgnoreCase("mamifero")) {    //evalua si es mamifero, de ser asi se le da el valor correspondiente   
            return Mamifero;
        } else if (clas.equalsIgnoreCase("pez")) {         //evalua si es pez, de ser asi se le da el valor correspondiente
            return Pez;
        } else if (clas.equalsIgnoreCase("reptil")) {      //evalua si es reptil, de ser asi se le da el valor correspondiente
            return Reptil;
        }
        return null;
    }

    private LocalDate leeFecha() {

        //inicio datos de fechas
        int mes, año, dia;
        String fecha;

        // ingreso datos
        fecha = tcld.next().trim();

        //validar si los "/" estan bien posicionados y tiene el formato correcto11/11/
        if ((fecha.indexOf("/") == 2 || fecha.indexOf("/") == 1)
                && (fecha.lastIndexOf("/") == 5 || fecha.lastIndexOf("/") == 4 || fecha.lastIndexOf("/") == 3)
                && ((fecha.indexOf("/", fecha.indexOf("/") + 1) == fecha.lastIndexOf("/")))) {
            try {

                dia = Integer.parseInt(fecha.split("/")[0]);
                mes = Integer.parseInt(fecha.split("/")[1]);
                año = Integer.parseInt(fecha.split("/")[2]);
                return LocalDate.of(año, mes, dia);
            } catch (NumberFormatException e) {
                return null;
            } catch (DateTimeException e) {
                return null;
            }
        }
        return null;
    }
}
