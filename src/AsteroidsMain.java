/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Main.java to edit this template
 */

/**
 *
 * @author Sam
 */
public class AsteroidsMain {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        Asteroids game = new Asteroids();
        
        game.init();
        game.start();
        //while(true){
        
            //game.update(game.offg); //Replace with getter
        //}
        /*
        game.update(game.offg);
        game.update(game.offg);
        game.update(game.offg);
        game.update(game.offg);
        */
        /*
        int timer = 0;
        while(true){
            timer += 1;
            if(timer%40==0){
                game.update(game.offg);
            }
        }
    */
    }
    
}
