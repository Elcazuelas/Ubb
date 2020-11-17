/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package modelo;

/**
 * 
 * @author Julio Monroy
 * @author Camilo Vazques 
 */
public class Persona {
    //atributos
    private String rut;
    private String nombre;
    private String email;
    
    //aosciacion
    Rol rol;
    
//constructor

    public Persona(String rut, String nom, String email) {
        this.rut = rut;
        nombre = nom;
        this.email = email;
    }
    
    public String getRut(){
        return rut;
    }
    
    public String getnombre(){
        return nombre;
    }
    
    public String getEmail(){
        return email;
    }
    
    public void agregaRol(Rol rol){
      this.rol=rol;
    }
    
    public boolean equals(Persona other){
        return getRut().equalsIgnoreCase(other.getRut());
    }

}
