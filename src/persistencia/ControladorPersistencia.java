/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package persistencia;

import excepciones.RegistroAtencionesException;
import java.io.EOFException;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.ArrayList;
import modelo.Cliente;
import modelo.Veterinario;

/**
 * 
 * @author Julio Monroy - Camilo Vazques
 */
public class ControladorPersistencia {
    //atributo 
    private static ControladorPersistencia instance;
    
    //constructor
    private ControladorPersistencia(){
    }
    
    //singlenton
    public static ControladorPersistencia getInstance(){
        if(instance== null){
            instance= new ControladorPersistencia();
        }
        return instance;
    }
    
    //metodos
    public  Cliente[] leeClientes() throws RegistroAtencionesException{
        ObjectInputStream archivo = null;
        ArrayList<Cliente> clientes = new ArrayList<>();
        try { 
            archivo = new ObjectInputStream(new FileInputStream("Clientes.obj"));
            while (true) {
                clientes.add((Cliente)archivo.readObject());
            }
        } catch (EOFException ex) {
            try {
                archivo.close();
            } catch (IOException ex2) {
                throw new RegistroAtencionesException("Problemas al cerrar el archivo Clientes.obj");
            }
            return clientes.toArray(new Cliente[0]);
        } catch (FileNotFoundException ex) {
            throw new RegistroAtencionesException("Problemas al abrir el archivo Clientes.obj");
        } catch (IOException | ClassNotFoundException ex) {
            throw new RegistroAtencionesException("Problemas al leer el archivo Clientes.obj");
        }
    }
    
    public Veterinario[] leeVeterinario() throws RegistroAtencionesException{
       ObjectInputStream archivo = null;
        ArrayList<Veterinario> veterinarios = new ArrayList<>();
        try { 
            archivo = new ObjectInputStream(new FileInputStream("Veterinarios.obj"));
            while (true) {
                veterinarios.add((Veterinario)archivo.readObject());
            }
        } catch (EOFException ex) {
            try {
                archivo.close();
            } catch (IOException ex2) {
                throw new RegistroAtencionesException("Problemas al cerrar el archivo Veterinarios.obj");
            }
            return veterinarios.toArray(new Veterinario[0]);
        } catch (FileNotFoundException ex) {
            throw new RegistroAtencionesException("Problemas al abrir el archivo Veterinarios.obj");
        } catch (IOException | ClassNotFoundException ex) {
            throw new RegistroAtencionesException("Problemas al leer el archivo Veterinarios.obj");
        }
    }
    
    public void escribeClientes(Cliente[] clientes) throws RegistroAtencionesException{
        
        ObjectOutputStream archivo = null;
        try {
            archivo = new ObjectOutputStream(new FileOutputStream("Clientes.obj"));
            for (Cliente cliente : clientes) {
                archivo.writeObject(cliente);
            }
        } catch (FileNotFoundException ex) {
            throw new RegistroAtencionesException("Problemas al abrir el archivo Clientes.obj");
        } catch (IOException ex) {
            throw new RegistroAtencionesException("Problemas al grabar en el archivo Clientes.obj");
        } finally {
            try {
                archivo.close();
            } catch (IOException ex) {
                throw new RegistroAtencionesException("Problemas al cerrar el archivo Clientes.obj");
            }
        }
    }
    
    public void escribeVeterinarios(Veterinario[] vets) throws RegistroAtencionesException {
        
        ObjectOutputStream archivo = null;
        try {
            archivo = new ObjectOutputStream(new FileOutputStream("Veterinarios.obj"));
            for (Veterinario vet : vets) {
                archivo.writeObject(vet);    
            }
        } catch (FileNotFoundException ex) {
            throw new RegistroAtencionesException("Problemas al abrir el archivo Veterinarios.obj");
        } catch (IOException ex) {
            throw new RegistroAtencionesException("Problemas al grabar en el archivo Veterinarios.obj");
        } finally {
            try {
                archivo.close();
            } catch (IOException ex) {
                throw new RegistroAtencionesException("Problemas al cerrar el archivo Veterinarios.obj");
            }
        }
    }

    
}//fin clase
