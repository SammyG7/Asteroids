/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author RP4K
 */
public class Bullet extends VectorSprite {
    int time;
   
    public Bullet(){
    super (new int[] {0,7}, new int[] {0,0});
   
    
}
    public void decay(){
       if (range == false){ 
        time += 1;
       }
      
    }
    
}
