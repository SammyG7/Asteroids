/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author RP4K
 */
public class WinScreen extends VectorSprite{
    
    public WinScreen(){
        super (new int[]{0,5,15,5,10,0,-10,-5,-15,-5}, new int[]{0,5,5,11,15,13,15,11,5,5});
        speedx = Math.random() * 10 - 5;
        speedy = Math.random() * 10 - 5;
    }
    @Override
    public void updatePosition(){
    angle += 0.2;
    super.updatePosition();
    
}
}
