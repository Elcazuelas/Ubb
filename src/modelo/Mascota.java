package modelo;

import java.time.LocalDate;

enum Clase{
    Ave,
    Mamifero,
    Pez,
    Reptil
}

/**
 *
 * @author Julio Monroy
 * @author Camilo Vazques 
 */
public class Mascota {
    //atributos
    private String nombre;
    private LocalDate fechaNac;  //creo que falta algo
    private Clase clase;
    private String especie;
    private String raza;

    //relaciones
    private Cliente dueno;

    //constructor
    public Mascota(String nom, LocalDate fNac, Clase clase, String esp, String raza, Cliente dueno){
        nombre=nom;
        fechaNac=fNac;
        this.clase=clase;
        especie=esp;
        this.raza=raza;
    
        this.dueno=dueno;

    }

    //operaciones
    public String getNombre(){
        return nombre;
    }

    public int getEdad(){
         if(fechaNac.getDayOfMonth() < LocalDate.now().getDayOfMonth()){
         }
    }

    public Cliente getDueno(){
        return dueno;
    }

    public Clase getClase(){
        return clase;
    }

    public String getEspecie(){
       return especie;
    }

    public String getRaza(){
    return raza;
    }
}