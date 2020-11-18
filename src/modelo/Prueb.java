/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package modelo;

import java.time.LocalDate;
import java.util.ArrayList;

/**
 * 
 * @author Julio Monroy - Camilo Vazques
 */
public class Prueb {
    public static void main(String[] args) {
   
        Cliente client =new Cliente(new Persona("123","juanito", "a"));
        System.out.println(client.getPersona().getnombre());
        
        
        
        
         Mascota masc=new Mascota("juni", LocalDate.of(2000, 11, 21),Clase.Ave, "a","raza",client);
         System.out.println(masc.getEdad());
                }
}
