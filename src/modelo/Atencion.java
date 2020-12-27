/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package modelo;

import java.io.Serializable;
import java.time.LocalDate;

/**
 *
 * @author ignac
 */
public class Atencion implements Serializable{
    
    //atributos
    private LocalDate fecha;
    private String diagnostico;
    private String observaciones;
    
    //relaciones
    private Veterinario veterinario;
    private Mascota mascota;
    
    //constructor 
    public Atencion(LocalDate fecha, String diagn, String obs, Veterinario vet, Mascota masc){
        this.fecha=fecha;
        diagnostico=diagn;
        observaciones=obs;
        veterinario=vet;
        mascota=masc;
        
        veterinario.agregaAtencion(this);
        mascota.agregaAtencion(this);
    }

    public LocalDate getFecha() {
        return fecha;
    }

    public String getDiagnostico() {
        return diagnostico;
    }

    public String getObservaciones() {
        return observaciones;
    }

    public Veterinario getVeterinario() {
        return veterinario;
    }

    public Mascota getMascota() {
        return mascota;
    }
    
    public boolean estaEntre(LocalDate fechaInicio, LocalDate fechaFin){
        if (fecha.isAfter(fechaInicio)&& fecha.isBefore(fechaFin)){
            return true;
        }
        return false;
    }
}
