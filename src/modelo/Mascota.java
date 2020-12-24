package modelo;

import java.io.Serializable;
import java.time.LocalDate;
import java.util.ArrayList;

/**
 *
 * @author Julio Monroy
 * @author Camilo Vazques
 */

public class Mascota implements Serializable {

    //atributos
    private String nombre;
    private LocalDate fechaNac;
    private Clase clase;
    private String especie;
    private String raza;

    //relaciones
    private Cliente dueno;
    private ArrayList<Atencion> atenRecibidas;
    
    //constructor
    public Mascota(String nom, LocalDate fNac, Clase clase, String esp, String raza, Cliente dueno) {
        nombre = nom;
        fechaNac = fNac;
        this.clase = clase;
        especie = esp;
        this.raza = raza;
        this.dueno = dueno;
        this.dueno.agregaMascota(this);
    }

    //metodos
    public String getNombre() {
        return nombre;
    }

    public int getEdad() {
        if (fechaNac.getYear() == LocalDate.now().getYear()) {
            return 0;
        } else if (fechaNac.getMonthValue() > LocalDate.now().getMonthValue()
                || (fechaNac.getMonthValue() == LocalDate.now().getMonthValue()
                && fechaNac.getDayOfMonth() > LocalDate.now().getDayOfMonth())) {
            return LocalDate.now().compareTo(fechaNac) - 1;
        }
        return LocalDate.now().compareTo(fechaNac);
    }

    public Cliente getDueno() {
        return dueno;
    }

    public Clase getClase() {
        return clase;
    }

    public String getEspecie() {
        return especie;
    }

    public String getRaza() {
        return raza;
    }
    
    public void agregaAtencion(Atencion atencion){
        atenRecibidas.add(atencion);
    }
    
    public Atencion[] getAtenciones(){
        return atenRecibidas.toArray(new Atencion[0]);
    }
}
