/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package modelo;

/**
 * 
 * @author Julio Monroy - Camilo Vazques
 */
public class Veterinario extends Rol{
    private String especialidad;

    public Veterinario(Persona persona, String especialidad) {
        super(persona);
        this.especialidad=especialidad;
    }
    
    public String getEspecialidad(){
        return especialidad;
    }
    
    public boolean equals(Veterinario other){
        return this.getPersona().equals(other.getPersona()) && this.getEspecialidad().equalsIgnoreCase(other.especialidad);
    }
  
    
    

}
