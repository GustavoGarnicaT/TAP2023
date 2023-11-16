
import java.util.HashSet;
import java.util.Set;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.ImageIcon;

/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */

/**
 *
 * @author Gustavo
 */
public class HiloVaraja extends Thread{
   private VentanaLoteria p;
   public int cartas = 0;
   Set<Integer> cartasGeneradas = new HashSet<>();
   private boolean ejecutaCodigo = true;
   private boolean PausarCiclo = true;
   
   public HiloVaraja(VentanaLoteria p) {
        this.p = p;        
    }
   
   public void run(){
   while(PausarCiclo){
          try {
           Thread.sleep(5000);
       } catch (InterruptedException ex) {
           Logger.getLogger(HiloVaraja.class.getName()).log(Level.SEVERE, null, ex);
    }
     if(ejecutaCodigo){
       do {
       cartas = (int) (Math.random() * 54) + 1;
       } while (!cartasGeneradas.add(cartas)); 
       p.Imagen.setIcon(new ImageIcon(this.getClass().getResource("/Loteria/" + cartas + ".png")));
  }//if
 }//while
}//run

   public void terminar(){
    PausarCiclo = false;
   } 

   public void pausar(){
   this.ejecutaCodigo = false;
   }
   
   public void ejecutarCodigo(){
   ejecutaCodigo = false;
   }
   
   public void despausar(){
   ejecutaCodigo = true;
   }
   
   public boolean estaPausado(){
        return ejecutaCodigo;
   }
    public boolean estaTerminado(){
         return PausarCiclo;
   }
}
