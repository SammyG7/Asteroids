
import java.awt.Graphics;
import java.util.ArrayList;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author RP4K
 */
public class SpaceShip extends VectorSprite {
    final float FUEL= 0.2f;
    ArrayList<Bullet>bullets;
    int cooldown = 0;
   
    public SpaceShip(){
        super();
        bullets = new ArrayList();
        
    }
    
            public void thrust(){
        //if (vincible == true){
        speedx+=Math.cos(angle)*FUEL;
        speedy+=Math.sin(angle)*FUEL;
       // }
        }
            @Override
            public void draw(Graphics g){
              super.draw(g);
                for(Bullet b: bullets ){
                   b.draw(g);
               } 
            }
     
            @Override
           public void updatePosition(){        
               super.updatePosition();
           Bullet gone = null;
           for(Bullet b: bullets){
              
               b.decay();
               b.updatePosition();
                if (b.time > 25){
                   gone = b;
              //b.visible = false;
          }
                
           }
         
           if (gone != null){
               bullets.remove(gone);
           }
        if (machinegun == false){   
         cooldown = cooldown -1;
        }
        if (machinegun == true){
            cooldown = 0;
        }
         
         
        
           }
           public void shoot(){
         if (vincible==true){
        if (cooldown < 1){
         Bullet tempBullet;
         tempBullet = new Bullet();
         tempBullet.xpos = xpos;
         tempBullet.ypos = ypos;
         tempBullet.angle = angle;
         tempBullet.speedx = 15 * Math.cos(angle);
         tempBullet.speedy = 15 * Math.sin(angle);
         tempBullet.range = range;
         cooldown = 15;
         bullets.add(tempBullet);
         }
             }
         
              
          
           }
            
}
