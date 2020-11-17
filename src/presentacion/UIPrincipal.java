/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package presentacion;
import java.util.Scanner;

/**
 * 
 * @author Julio Monroy - Camilo Vazques
 */
public class UIPrincipal {
    private static UIPrincipal instance;
    private Scanner tdd;
    
    //constructor 
    private UIPrincipal(){
        tdd=new Scanner(System.in);
    }
    
    //metodo singleton
    public static UIPrincipal getInstance(){
        if(instance== null){
            instance= new UIPrincipal();
        }
        return instance;
    }
    
    //main
    public static void main(String[] args) {
        
    }
    
    //metodos
    private inicia(){
    }
    
    private void menuMain(){
    }
    
}
