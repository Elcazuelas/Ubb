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
 * @author ignac
 */
public class Controlador {
    //atributos
    private static Controlador instance;
    
    //Relacion
    ArrayList<Cliente> clientes;
    ArrayList<Veterinario> veterinarios;
    ArrayList<Mascota> mascotas;
    ControladorPersistencia cPersistencia;
    
    //constructor
    private Controlador(){
        clientes = new ArrayList();
        veterinarios = new ArrayList();
        mascotas = new ArrayList();
        cPersistencia = ControladorPersistencia.getInstance();
    }
    
    //metodo singlenton
    public static Controlador getInstance(){
          if(instance== null){
            instance= new Controlador();
        }
        return instance;
    }
    
    //atributos
    public void creaCliente(String rut, String nombre, String email) throws RegistroAtencionesException{
        if (buscaCliente(rut) == null) {
            Veterinario vet = buscaVeterinario(rut);
            if (vet != null) {
                clientes.add(new Cliente(vet.getPersona()));
            }else{
                clientes.add(new Cliente(new Persona(rut, nombre, email)));
            }
        }else{
            throw new RegistroAtencionesException("El cliente ya existe");
        }
        
    }//fin crea cliente
    
    public void creaVeterinario(String rut, String nombre, String email, String especialidad) throws  RegistroAtencionesException{
        if (buscaVeterinario(rut) == null) {
            Cliente clien = buscaCliente(rut);
            if (clien != null) {
                veterinarios.add(new Veterinario(clien.getPersona(), especialidad));
            }else{
                veterinarios.add(new Veterinario(new Persona(rut, nombre, email), especialidad));
            }
        }else{
            throw new RegistroAtencionesException("El veterinario ya existe");
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
            throw new RegistroAtencionesException("El cliente no existe");
        }
    }
    
    public String[][] listaMascotas(Clase clase){ 
        ArrayList<Mascota> masConClase = new ArrayList<>();
        for (Mascota mascota : mascotas) {
            if (mascota.getClase() == clase) {
                masConClase.add(mascota);
            }
        }
        String[][] datosMasc = new  String[masConClase.size()][5];
        for (int i = 0; i < datosMasc.length; i++) {
                datosMasc[i][0]=masConClase.get(i).getNombre();
                datosMasc[i][1]=masConClase.get(i).getEspecie();
                datosMasc[i][2]=masConClase.get(i).getRaza();
                datosMasc[i][3]=masConClase.get(i).getEdad()+"";
                datosMasc[i][4]=masConClase.get(i).getDueno().getPersona().getnombre();
        }
        return datosMasc;
    }
    
    public String[][] listaDatosVeterinario(){
        String[][] vets = new String[veterinarios.size()][4];
        for (int i = 0; i < vets.length; i++) {
            vets[i][0] = veterinarios.get(i).getRut();
            vets[i][1] = veterinarios.get(i).getPersona().getnombre();
            vets[i][2] = veterinarios.get(i).getPersona().getEmail();
            vets[i][3] = veterinarios.get(i).getEspecialidad();
        }
        return vets;
    }
    
    public void leeDatosPersistentes() throws RegistroAtencionesException{
        Cliente[] clienteArr = cPersistencia. leeClientes();
        for (Cliente cliente : clienteArr) {
            clientes.add(cliente);
            mascotas.addAll(Arrays.asList(cliente.getMascota()));
        }
        //agrega vets
        veterinarios.addAll(Arrays.asList(cPersistencia.leeVeterinario()));
        
    }
    
    public void escribeDatosPersistentes() throws RegistroAtencionesException{      
        cPersistencia.escribeClientes(clientes.toArray(new Cliente[0]));
        cPersistencia.escribeVeterinarios(veterinarios.toArray(new Veterinario[0]));
  
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
        Cliente cliente = buscaCliente(nombre);
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
}//fin clase

