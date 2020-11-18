/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package control;

/**
 *
 * @author ignac
 */
public class Controlador {
    //atributos
    private static Controlador instance;
    
    //constructor
    private class Controlador(){
        
    }
    
    //operaciones
    public static Controlador getIntance(){
        
    }
    
    public void creaCliente(String rut, String nombre, String email){
        
    }
    
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
        
    }
    
}

