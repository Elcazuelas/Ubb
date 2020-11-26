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
public class Cliente extends  Rol{
        //asociacion
    private ArrayList<Mascota> mascota;
    
    //constructor
    public Cliente(Persona persona) {
        super(persona);
        mascota=new ArrayList();
    }
    
    //metodos
    public Mascota[] getMascota(){
        return  mascota.toArray(new Mascota[0]);
    }
    
    public void agregaMascota(Mascota mascota){
       this.mascota.add(mascota);
    }
    
    public boolean tieneMascotas(){
        return mascota.isEmpty();
        }
   }
        
