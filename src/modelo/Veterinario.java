/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package modelo;

import java.util.ArrayList;

/**
 * 
 * @author Julio Monroy - Camilo Vazques
 */
public class Veterinario extends Rol{
    private String especialidad;
    private ArrayList<Atencion> atenciones;

    public Veterinario(Persona persona, String especialidad) {
        super(persona);
        this.especialidad=especialidad;
        
        this.atenciones = new ArrayList<>();
    }
    
    public String getEspecialidad(){
        return especialidad;
    }
    
    public void agregaAtencion(Atencion atencion){
        atenciones.add(atencion);
    }
    
    public Atencion[] getAtenciones(){
        return atenciones.toArray(new Atencion[0]);
    }
    
    public boolean tieneAtenciones(){
        return !atenciones.isEmpty();
    }
    
    public boolean equals(Veterinario other){
        return this.getPersona().equals(other.getPersona()) && this.getEspecialidad().equalsIgnoreCase(other.especialidad);
    }
  
    
    

}
