/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author RP4K
 */
public class Rock extends VectorSprite {
    int size = 1;
    public Rock(){   
    super(new int[]{0,6,10,20,20,18,22,18,10,6,-4,-30,-30,-28,-28}, new int[]{30,34,34,16,4,-4,-30,-34,-30,-34,-36,-4,2,4,30}); 
    //super(new int[]{0,21,32,32}, new int []{2,2,23,2});
 
    speedx = Math.random() * 6 - 3;
    speedy = Math.random() * 6 - 3;
  
}
   public Rock(int size){
     this();
     for(int i=0;i<shape.npoints;i++){
         shape.xpoints[i] = dshape.xpoints[i] / size;
        shape.ypoints[i] = dshape.ypoints[i] / size;  
     }
     this.size = size;
   }
    @Override
    public void updatePosition(){       
        angle += 0.02;
   
        super.updatePosition();
    }
    public Rock away(int xrad, int yrad){   
    double randAngle;
    randAngle = Math.random()*(2*Math.PI);
    xpos = Math.cos(randAngle)*200 + xrad;
    ypos = Math.sin(randAngle)*200 + yrad;
        
        return this;   
    }
 
}
