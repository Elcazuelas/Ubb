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
public class Prueb {
    public static ArrayList<Veterinario> veterinarios=new ArrayList();
    public static void main(String[] args) {
        veterinarios.add(new Veterinario(new Persona("213", "Jaun", "juan@gmail.com"), "peo"));
        
        if(veterinarios.get(0).equals(new Veterinario(new Persona("213", "Jaun", "juan@gmail.com"), "peo"))){         System.out.println("Son iguales");
            System.out.println("Son iguales");
        }
        
    }

}
