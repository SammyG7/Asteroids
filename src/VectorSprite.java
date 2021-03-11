
import java.awt.Color;
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
public class VectorSprite {
    Polygon shape;
    Polygon dshape;
    Polygon octShape;
    double xpos,ypos;
    Color color;
    double speedx,speedy;
    double angle;
    boolean visible = true; 
    int sLife = 3;
    boolean vincible = true;
    int flash = 14;
    boolean dead = false;
    boolean machinegun; 
    boolean range = true;
    boolean oct = false;
    public VectorSprite(){
    shape=new Polygon();
    shape.addPoint(15,0);
    shape.addPoint(-15,15);
    shape.addPoint(-15,-15);
    dshape=new Polygon();
    dshape.addPoint(0,15);
    dshape.addPoint(15,-15);
    dshape.addPoint(-15,-15);
    octShape = new Polygon();
    octShape.addPoint(-7,0);
    octShape.addPoint(-15,-10);
    octShape.addPoint(-15,-20);
    octShape.addPoint(-7,-30);
    octShape.addPoint(7,-30);
    octShape.addPoint(15,20);
    octShape.addPoint(15,10);
    octShape.addPoint(7,0);
        xpos=400;
        ypos=300;        
    }
    public VectorSprite(int[] xpoints, int[] ypoints){      
        this();
        shape=new Polygon(xpoints,ypoints,xpoints.length);
        dshape=new Polygon(xpoints,ypoints,xpoints.length);
    }
    
    public void draw(Graphics g){
      
       if(visible == true){
          if (sLife > -1){
              if (oct == false){
              g.drawPolygon(dshape);        
       }else{if(oct == true){
           g.drawPolygon(octShape);  
       }
       }
           
                 
        } 
    } 
//    public void drawFull(Graphics g){
//   
//     if(visible == true){
//          if (sLife > -1){
//              g.fillPolygon(dshape);        
//       }
//        } 
    
}
    public void updatePosition(){
    int newy;
    int newx;
    xpos+=speedx;
    ypos+=speedy;
    for(int i=0; i<shape.npoints;i++){
     //shape.xpoints[i]+=speedx;
     //shape.ypoints[i]+=speedy;
       newy =(int)Math.round(shape.ypoints[i]*Math.cos(angle)+shape.xpoints[i]*Math.sin(angle));
         newx =(int)Math.round(shape.xpoints[i]*Math.cos(angle)-shape.ypoints[i]*Math.sin(angle));
         dshape.ypoints[i] = newy;
         dshape.xpoints[i]= newx;
         dshape.invalidate();
        }
    if (this.xpos > 815){
        xpos=-14;
    }
    if (xpos <-15){
        xpos = 814;
    }
       if (ypos > 615){
           ypos = -14;
       } 
       if (ypos < -15){
           ypos = 614;
       }
    dshape.translate((int)xpos,(int)ypos);
    
    }
    public double distance(VectorSprite obj1){      
        return Math.sqrt(Math.pow(obj1.xpos - xpos,2) + Math.pow(obj1.ypos - ypos,2)); 
    
}
    
}
