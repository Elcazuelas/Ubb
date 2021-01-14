/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package control;

import excepciones.RegistroAtencionesException;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Arrays;
import modelo.*;
import persistencia.ControladorPersistencia;

/**
 *
 * @author Julio Ignacio Monroy San Martin
 * @author Camilo Ignacio Vazques Rodriguez
 */
public class Controlador {
    //atributos
    private static Controlador instance;
    
    //Relacion
    private ArrayList<Cliente> clientes;
    private ArrayList<Veterinario> veterinarios;
    private ArrayList<Mascota> mascotas;
    private ArrayList<Atencion> atenciones;
    
    private ControladorPersistencia cPersistencia;
    
    //constructor
    private Controlador(){
        clientes = new ArrayList<>();
        veterinarios = new ArrayList<>();
        mascotas = new ArrayList<>();
        atenciones = new ArrayList<>();
        cPersistencia = ControladorPersistencia.getInstance();
    }
    
    //metodo singlenton
    public static Controlador getInstance(){
          if(instance== null){
            instance= new Controlador();
        }
        return instance;
    }
    
    //metodos
    public void creaCliente(String rut, String nombre, String email) throws RegistroAtencionesException{
        if (buscaCliente(rut) == null) {
            Veterinario vet = buscaVeterinario(rut);
            if (vet != null) {
                clientes.add(new Cliente(vet.getPersona()));
            }else{
                clientes.add(new Cliente(new Persona(rut, nombre, email)));
            }
        }else{
            throw new RegistroAtencionesException("Ya existe cliente con el rut dado");
        }
        
    }
    
    public void creaVeterinario(String rut, String nombre, String email, String especialidad) throws  RegistroAtencionesException{
        if (buscaVeterinario(rut) == null) {
            Cliente clien = buscaCliente(rut);
            if (clien != null) {
                veterinarios.add(new Veterinario(clien.getPersona(), especialidad));
            }else{
                veterinarios.add(new Veterinario(new Persona(rut, nombre, email), especialidad));
            }
        }else{
            throw new RegistroAtencionesException("Ya existe veterinario con el rut dado");
        }
        
    }
    
    public void creaMascota(String nombre, LocalDate fechaNac, Clase clase, String especie, String raza, String rutDueno) throws RegistroAtencionesException{
        Cliente cliente= buscaCliente(rutDueno);
        if (cliente != null) {
            if (buscaMascota(rutDueno, nombre) == null) {
                mascotas.add(new Mascota(nombre, fechaNac , clase, especie, raza, cliente));
            }else{
                throw new RegistroAtencionesException("El cliente ya tiene una mascota con el mismo nombre");
            }
        }else{
            throw new RegistroAtencionesException("No existe cliente con el rut dado");
        }
    }
    
    public String[][] listaMascotas(Clase clase){ 
        ArrayList<Mascota> masConClase = new ArrayList<>();
        for (Mascota mascota : mascotas) {
            if (mascota.getClase() == clase) {
                masConClase.add(mascota);
            }
        }
        String[][] datosMasc = new  String[masConClase.size()][7];
        for (int i = 0; i < datosMasc.length; i++) {
                datosMasc[i][0]=masConClase.get(i).getNombre();
                datosMasc[i][1]=masConClase.get(i).getEspecie();
                datosMasc[i][2]=masConClase.get(i).getRaza();
                datosMasc[i][3]=masConClase.get(i).getEdad()+"";
                datosMasc[i][4]=masConClase.get(i).getDueno().getRut();
                datosMasc[i][5]=masConClase.get(i).getDueno().getPersona().getnombre();
                datosMasc[i][6] = masConClase.get(i).getAtenciones().length + "";  
                
                
        }
        return datosMasc;
    }
    
    public void agregaAtencion(String rutCliente, String rutVet, String nomMascota, String diagnostico, String observacion)throws RegistroAtencionesException{
        if (buscaCliente(rutCliente) != null) {
            Mascota mascota = buscaMascota(rutCliente, nomMascota);
            if (mascota != null) {
                Veterinario vet = buscaVeterinario(rutVet);
                if (vet != null) {
                    atenciones.add(new Atencion(LocalDate.now(), diagnostico, observacion, vet, mascota));
                }else{
                    throw new RegistroAtencionesException("No existe veterinario con el rut dado");
                }
            }else{
                throw new RegistroAtencionesException("No existe mascota con el nombre dado");
            }
        }else{
            throw new RegistroAtencionesException("No existe cliente con el rut dado");
        }
    }
    
    public String[][] listaAtencionesMascotasDe(String rutCliente) throws RegistroAtencionesException {
        int aux = 0;
        String[][] datos;
        Mascota[] mascotasCliente;
        Cliente cliente = buscaCliente(rutCliente);
        if (cliente != null) {
            mascotasCliente = cliente.getMascota();
            if (mascotasCliente.length != 0) {

                for (Mascota mascota : mascotasCliente) {
                    if (mascota.getAtenciones().length == 0) {
                        aux++;
                    } else {
                        aux += mascota.getAtenciones().length;
                    }
                }
                datos = new String[aux][6];
                aux = 0;
                for (int i = 0; i < mascotasCliente.length; i++) {
                    datos[aux][0] = mascotasCliente[i].getNombre();
                    if (mascotasCliente[i].getAtenciones().length == 0) {
                        aux++;
                    } else {
                        for (Atencion atencion : mascotasCliente[i].getAtenciones()) {
                            datos[aux][1] = atencion.getFecha() + "";
                            datos[aux][2] = atencion.getDiagnostico();
                            datos[aux][3] = atencion.getObservaciones();
                            datos[aux][4] = atencion.getVeterinario().getRut();
                            datos[aux][5] = atencion.getVeterinario().getPersona().getnombre();
                            if ((aux + 1) != datos.length) {
                                aux++;
                                datos[aux][0] = "";
                            }
                        }
                    }
                }
                return datos;
            } else {
                return new String[0][0];
            }
        } else {
            throw new RegistroAtencionesException("No existe cliente con el rut dado");
        }
    }//duda
        
    public int calculaNroAtencionesPara(Clase clase, LocalDate fechaIni, LocalDate fechaFin){
        int nroAtenciones = 0;
        for (Mascota mascota : mascotas) {
            if (mascota.getClase() == clase) {
                for (Atencion atencion : mascota.getAtenciones()) {
                    if (atencion.estaEntre(fechaIni, fechaFin)) {
                        nroAtenciones++;
                    }
                }
            }
        }
        return nroAtenciones;
    }
    
    public String[][] listaDatosYNroAtencionesDeVeterinario(){
        String[][] vets = new String[veterinarios.size()][5];
        for (int i = 0; i < vets.length; i++) {
            vets[i][0] = veterinarios.get(i).getRut();
            vets[i][1] = veterinarios.get(i).getPersona().getnombre();
            vets[i][2] = veterinarios.get(i).getPersona().getEmail();
            vets[i][3] = veterinarios.get(i).getEspecialidad();
            vets[i][4] = veterinarios.get(i).getAtenciones().length + "";
            
        }
        return vets;
    }
    
    public void leeDatosPersistentes() throws RegistroAtencionesException{
        Cliente[] clienteArr = cPersistencia. leeClientes();
        for (Cliente cliente : clienteArr) {
            clientes.add(cliente);
            for (Mascota mascota : cliente.getMascota()) {
                mascotas.add(mascota);
                for (Atencion atencion : mascota.getAtenciones()) {
                    atenciones.add(atencion);
                    if (buscaVeterinario(atencion.getVeterinario().getRut()) == null) {
                        veterinarios.add(atencion.getVeterinario());
                    }
                }
            }
        }
        //agrega vets
        veterinarios.addAll(Arrays.asList(cPersistencia.leeVeterinario()));
        
    }
    
    public void escribeDatosPersistentes() throws RegistroAtencionesException{      
        ArrayList<Veterinario> vetSinAtenciones = new ArrayList<>();
        for (Veterinario veterinario : veterinarios) {
            if (!veterinario.tieneAtenciones()) {
                vetSinAtenciones.add(veterinario);
            }
        }
        cPersistencia.escribeVeterinarios(vetSinAtenciones.toArray(new Veterinario[0]));
        cPersistencia.escribeClientes(clientes.toArray(new Cliente[0]));
    }
    
    private Cliente buscaCliente(String rut){
        for (Cliente cliente : clientes) {
            if (cliente.getRut().equalsIgnoreCase(rut)) {
                return cliente;
            }
        }
        return null;
    }
    
    private Veterinario buscaVeterinario(String rut){
        for (Veterinario veterinario : veterinarios) {
            if (veterinario.getRut().equalsIgnoreCase(rut)) {
                return veterinario;
            }
        }
        return null;
    }
    
    private Mascota buscaMascota(String rutCliente, String nombre){
        Cliente cliente = buscaCliente(rutCliente);
        if (cliente != null) {
            if (cliente.getMascota().length > 0) {
                for (Mascota mascota : cliente.getMascota()) {
                    if (mascota.getNombre().equalsIgnoreCase(nombre)) {
                        return mascota;
                    }
                }
            }
        }
        return null;
    }
}