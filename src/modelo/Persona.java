/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package modelo;

import java.util.ArrayList;

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
    
    //asociacion
    ArrayList<Rol> rol;
    
//constructor

    public Persona(String rut, String nom, String email) {
        this.rut = rut;
        nombre = nom;
        this.email = email;
        
        rol=new ArrayList();
    }
    
    //metodos
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
      if(this.rol.size() < 2){ //el if se ocupa para controlar que no se agreguen más de dos roles
          this.rol.add(rol);
      }
    }
    
    public boolean equals(Persona other){
        return getRut().equalsIgnoreCase(other.getRut());
    }

}
