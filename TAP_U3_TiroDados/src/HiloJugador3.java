
import static java.lang.Thread.sleep;
import java.util.logging.Level;
import java.util.logging.Logger;

/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */

/**
 *
 * @author Gustavo
 */
public class HiloJugador3 extends Thread{
    VentanaTiro p;
    private int Tiros;
    int Dados = 0;
    int Dados1 = 0;
    private boolean mantenerciclo = true;
    StringBuffer buffer1 = new StringBuffer();
   
    public HiloJugador3(VentanaTiro p) {
        this.p = p;
    }
public void run(){
    while(mantenerciclo){
       
      Dados = (int)(Math.random()*6)+1;
        buffer1.append("Tiro 1\n valor "+Dados+"\n");
      p.jTextPane3.setText(buffer1.toString());
      
       Dados1 = (int)(Math.random()*6)+1;
        buffer1.append("Tiro 2\n valor "+Dados1+"\n");
      p.jTextPane3.setText(buffer1.toString());
          
       p.total[2] = Dados+Dados1;
       buffer1.append("Total: "+p.total[2]);
       p.jTextPane3.setText(buffer1.toString());
      terminar();
  
      try {
            sleep(100);
        } catch (InterruptedException ex) {
            Logger.getLogger(HiloJugador1.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
}
public void terminar(){
    mantenerciclo = false;
   }  
    
}