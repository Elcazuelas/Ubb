/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package control;

import java.util.ArrayList;
import modelo.*;

/**
 *
 * @author ignac
 */
public class Controlador {
    //atributos
    private static Controlador instance;
    
    //asociaciones
    ArrayList<Cliente> cliente;
    ArrayList<Veterinario> veterinario;
    ArrayList<Mascota> mascota;
    
    //constructor
    private Controlador(){
        cliente = new ArrayList();
        veterinario = new ArrayList();
        mascota = new ArrayList();
    }
    
    //metodo singlenton
    public static Controlador getInstance(){
          if(instance== null){
            instance= new Controlador();
        }
        return instance;
    }
    
    //atributos
    public void creaCliente(String rut, String nombre, String email){
        cliente.add(new Cliente(new Persona(rut, nombre, email)));
        System.out.println(cliente.get(0).getRut());
    }
    /*
    public void creaVeterinario(String rut, String nombre, string email, String especialidad){
        
    }
    
    public void creaMascota(String nombre, LocalDate fechaNac, Clase clase, String especie, String raza, String rutDueno){
        
    }
    
    public String[][] listaMascotas(Clase clase){
        
    } 
    
    public String[][] listaDatosVeterinario(){
        
    }
    
    public void leeDatosPersistentes(){
        
    }
    
    public void escribeDatosPersistentes(){
        
    }
    
    private Cliente buscaCliente(String rut){
        
    }
    
    private Veterinario buscaVeterinario(String rut){
        
    }
    
    private Mascota buscaMascota(String rutCliente, String nombre){
        
    }*/
    
}

