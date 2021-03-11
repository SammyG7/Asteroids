

import java.awt.Graphics;
import java.awt.Polygon;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author RP4K
 */
public class Lives {
    Polygon life1;
  
    Polygon life2;
    Polygon life3;
  
    public Lives(){
   life1=new Polygon();
   life1.addPoint(5,5);
   life1.addPoint(25,5);
   life1.addPoint(15,25);
   life2=new Polygon();
   life2.addPoint(30,5);
   life2.addPoint(50,5);
   life2.addPoint(40,25);
   life3=new Polygon();
   life3.addPoint(55,5);
   life3.addPoint(75,5);
   life3.addPoint(65,25);
   
   
    }
   public void draw(Graphics g, int lives){
       if(lives > 2){     
      g.drawPolygon(life3);
      }
       if(lives > 1){
        g.drawPolygon(life2);
       }
       if(lives > 0){
        g.drawPolygon(life1);   
       }
       
      
     
   }
}
