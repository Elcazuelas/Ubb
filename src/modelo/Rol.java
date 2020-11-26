/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package modelo;

import java.io.Serializable;

/**
 * 
 * @author Julio Monroy - Camilo Vazques
 */
public class Rol implements Serializable {
    //asocicion
    private Persona persona;
   
    //conctructor
    public Rol(Persona persona){
        this.persona=persona;
        persona.agregaRol(this);
    }
    
    public String getRut(){
        return persona.getRut();
    }

    public Persona getPersona() {
        return persona;
    }
    
    public boolean equals(Rol other){
      return   this.getPersona().equals(other.getPersona());
    } 
    
    
}
