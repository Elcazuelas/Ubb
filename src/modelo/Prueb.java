/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package modelo;

import control.Controlador;
import excepciones.RegistroAtencionesException;
import java.time.LocalDate;

/**
 * 
 * @author Julio Monroy - Camilo Vazques
 */
public class Prueb {
    public static void main(String[] args) {
                 Controlador control = Controlador.getInstance();
        try {
             /*
            control.creaCliente("1", "julio", "a@a.com");
            control.creaMascota("a", LocalDate.now(), Clase.Ave, "especie", "raza", "1");
            control.creaVeterinario("11", "Pancho", "a@a.a", "enfermo");
            control.escribeDatosPersistentes();
           */
            control.leeDatosPersistentes();
            for (String[] strings : control.listaDatosVeterinario()) {
                for (String string : strings) {
                    System.out.print(string+"\t");
                }
            }
        } catch (RegistroAtencionesException ex) {
            System.out.println("Error!" + ex.getMessage());
        }
                }
}
