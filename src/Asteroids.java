/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

//import java.applet.Applet;
import java.awt.Color;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.util.ArrayList;
import javax.swing.Timer;

import java.awt.*; 
import javax.swing.*;
import java.awt.image.BufferedImage;

/**
 *
 * @author RP4K
 */
//public class Asteroids extends JFrame implements KeyListener, ActionListener {
public class Asteroids extends JPanel implements KeyListener, ActionListener {

    int[] x = {15, 30, 0};
    int[] y;
    SpaceShip ship;
    Timer gameTimer;
    //Image offscreen;
    BufferedImage offscreen;
    Graphics offg;
    ArrayList<Rock> rocks;
    boolean buttons[] = {false, false, false, false};
    Lives life;
    int stringColour;
    int stringTime;
    boolean stringVisible;
    ArrayList<WinScreen> stars;
    boolean cheatScreen;
    boolean resetB;
    //ArrayList <Lives> livesD;
    
    JFrame frame;
    JLabel emptyLabel;

    /**
     * Initialization method that will be called after the applet is loaded into
     * the browser.
     */
    public void init() {
        /*
        frame = new JFrame("FrameDemo");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        emptyLabel = new JLabel();
        frame.getContentPane().add(emptyLabel, BorderLayout.CENTER);
        //frame.setSize(getWidth(), getHeight());
        //frame.setSize(800, 600);
        frame.pack();
        frame.setVisible(true);
        */
        
        this.setSize(800, 600);
        y = new int[3];
        y[0] = 0;
        y[1] = 30;
        y[2] = 30;
        //this.addKeyListener(this);
        ship = new SpaceShip();
        gameTimer = new Timer(20, this);
        //gameTimer.addActionListener(this);
        //offscreen = createImage(getWidth(), getHeight());
        offscreen = new BufferedImage(getWidth(), getHeight(), BufferedImage.TYPE_INT_RGB);
        offg = offscreen.getGraphics();
        
        rocks = new ArrayList();
        for (int i = 0; i < 6; i++) {
            rocks.add(new Rock().away((int) ship.xpos, (int) ship.ypos));
        }
        stars = new ArrayList();
        life = new Lives();
        for (int i = 0; i < 30; i++) {
            stars.add(new WinScreen());
        }
//        livesD = new ArrayList();
        for (int i = 0; i < 3; i++) {
            //  livesD.add(new Lives());
        }
        // Tx=ODO start asynchronous download of heavy resources
        
        frame = new JFrame("FrameDemo");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        emptyLabel = new JLabel();
        frame.getContentPane().add(emptyLabel, BorderLayout.CENTER);
        frame.addKeyListener(this);
        //frame.setSize(getWidth(), getHeight());
        //frame.setSize(800, 600);
        frame.pack();
        frame.setVisible(true);
    }

    @Override
    public void paint(Graphics g) {
        //boolean fullDead = false;
        //Should run if init is being weird (it is)
        /*
        if (offscreen == null) {
            offscreen = createImage(getWidth(), getHeight());
            offg = offscreen.getGraphics();
            offg.drawOval(10,10,250,250);
        }
        */
        if (rocks.isEmpty() == true) {

            offg.setColor(Color.LIGHT_GRAY);
            offg.fillRect(0, 0, 800, 600);
            offg.setColor(Color.BLACK);
            offg.setFont(getFont().deriveFont(50f));
            offg.drawString("GOOD JOB", 250, 300);
            if (stringTime % 12000 == 0) {
                stringColour += 1;
            }
            if (stringColour == 0) {
                offg.setColor(Color.ORANGE);
            } else {
                if (stringColour == 1) {
                    offg.setColor(Color.RED);
                } else {
                    if (stringColour == 2) {
                        offg.setColor(Color.BLUE);
                    }
                }
            }
            if (stringColour > 2) {
                stringColour = 0;
            }
            offg.drawString("Press R To Play Again", 150, 400);

            offg.setColor(Color.darkGray);
            ship.draw(offg);

            offg.setColor(Color.YELLOW);
            for (WinScreen s : stars) {
                s.draw(offg);

            }

        } else {
            if (cheatScreen == true) {
                restart();
                offg.setColor(Color.ORANGE);
                offg.fillRect(0, 0, 800, 600);
                offg.setColor(Color.BLACK);
                offg.setFont(getFont().deriveFont(50f));
                offg.drawString("CHEATS", 300, 80);
                offg.setFont(getFont().deriveFont(20f));
                offg.drawString("W = Win", 80, 120);
                offg.drawString("D = Die", 80, 160);
                offg.drawString("R = Restart", 80, 200);
                offg.drawString("M = Machine Gun", 80, 240);
                offg.drawString("B = Infinite Range", 80, 280);
            } else {
                if (ship.sLife > -1) {
                    stringTime = 0;
                    offg.setColor(Color.BLACK);
                    offg.fillRect(0, 0, 800, 600);
                    offg.setColor(Color.GREEN);

                    for (Rock r : rocks) {
                        r.draw(offg);
                    }
                    if (ship.vincible == false) {
                        offg.setColor(Color.BLUE);
                    }
                    ship.draw(offg);
                    offg.setColor(Color.RED);
                    life.draw(offg, ship.sLife);
                    offg.setFont(getFont().deriveFont(14f));
                    offg.drawString("Press Q For Cheats", 5, 40);
                } else {
                    stringTime += 27;
                    offg.setColor(Color.RED);
                    offg.fillRect(0, 0, 800, 600);
                    //fullDead = true;
                    // }
                    //if (fullDead = true){
                    offg.setColor(Color.BLACK);
                    offg.setFont(getFont().deriveFont(50f));
                    offg.drawString("YOU FAILED", 250, 250);
                    if (stringTime % 450 == 0) {
                        stringVisible = !stringVisible;
                    }
                    if (stringVisible == false) {
                        offg.drawString("PRESS R TO TRY AGAIN", 100, 350);
                    }
                }

            }
        }

        JPanel pane = new JPanel() {
            @Override
            protected void paintComponent(Graphics g) {
                super.paintComponent(g);
                g.drawImage(offscreen, 0, 0, this);
            }
        };
        
        //g.drawImage(offscreen, 0, 0, this);

       frame.add(pane);
       //frame.pack();
       frame.setSize(getWidth(), getHeight());
       frame.setVisible(true);
       
       repaint();
    }

    // TODO overwrite start(), stop() and destroy() methods
    @Override
    public void keyTyped(KeyEvent ke) {

    }

    @Override
    public void keyPressed(KeyEvent ke) {

        if (ke.getKeyCode() == KeyEvent.VK_LEFT) {
            //ship.angle += -0.2;
            System.out.println("Pressed");
            buttons[0] = true;
        }
        if (ke.getKeyCode() == KeyEvent.VK_RIGHT) {
            //ship.angle += 0.2; 
            buttons[2] = true;
        }
        if (ke.getKeyCode() == KeyEvent.VK_UP) {
            //ship.thrust();
            buttons[1] = true;
        }
        if (ke.getKeyCode() == KeyEvent.VK_SPACE) {
            buttons[3] = true;
        }
        if (ke.getKeyCode() == KeyEvent.VK_D) {
            if (rocks.isEmpty() == false) {
                ship.sLife = -1;
            }
        }
        if (ke.getKeyCode() == KeyEvent.VK_R) {
            restart();
        }
        if (ke.getKeyCode() == KeyEvent.VK_W) {
            rocks.removeAll(rocks);
        }
        if (ke.getKeyCode() == KeyEvent.VK_Q) {
            cheatScreen = !cheatScreen;
        }
        if (ke.getKeyCode() == KeyEvent.VK_M) {
            ship.machinegun = !ship.machinegun;
        }
        if (ke.getKeyCode() == KeyEvent.VK_B) {
//           resetB = false;
            ship.range = !ship.range;
            for (Bullet b : ship.bullets) {
                ship.bullets.removeAll(ship.bullets);
            }

        }
    }

    @Override
    public void keyReleased(KeyEvent ke) {
        if (ke.getKeyCode() == KeyEvent.VK_LEFT) {
            //ship.angle += -0.2;
            buttons[0] = false;
        }
        if (ke.getKeyCode() == KeyEvent.VK_RIGHT) {
            //ship.angle += 0.2; 
            buttons[2] = false;
        }
        if (ke.getKeyCode() == KeyEvent.VK_UP) {
            //ship.thrust();
            buttons[1] = false;
        }
        if (ke.getKeyCode() == KeyEvent.VK_SPACE) {
            buttons[3] = false;
        }

    }

    @Override
    public void update(Graphics g) {
        paint(g);
        //System.out.println("Hello");

    }

    @Override
    public void actionPerformed(ActionEvent ae) {
//        Bullet goneB = null;
//        goneB = new Bullet();
        //System.out.println("Action");
        Rock gone = null;
        Rock rAdd = null;
        WinScreen goneS = null;
        ship.updatePosition();
        for (WinScreen s : stars) {
            s.updatePosition();
        }
        buttonAction();
        for (Rock r : rocks) {
            r.updatePosition();
            if (collision(ship, r)) {
                ship.xpos = 400;
                ship.ypos = 300;
                ship.speedx = 0;
                ship.speedy = 0;
                ship.dead = true;
                ship.flash = 0;
                if (ship.vincible == true) {
                    ship.sLife += -1;
                }
                //ship.visible = false;
                ship.vincible = false;
            }
        }
        if (ship.vincible == false) {
            if (ship.flash > 21) {
                if (canSpawn() == true) {
                    ship.vincible = true;
                    ship.dead = false;
                    ship.visible = true;
                }
            }
        }
        for (Rock r : rocks) {
            for (Bullet b : ship.bullets) {
                if (collision(b, r)) {
                    gone = r;
                    b.time = 26;
                }
            }
        }
        for (WinScreen s : stars) {
            for (Bullet b : ship.bullets) {
                if (collision(b, s)) {
                    goneS = s;
                }
            }
        }
        if (gone != null) {
            rocks.remove(gone);
            if (gone.size < 3) {
                for (int i = 0; i < 2; i++) {
                    rAdd = new Rock(gone.size + 1);
                    rAdd.xpos = gone.xpos;
                    rAdd.ypos = gone.ypos;

                    rocks.add(rAdd);
                }
            }
        }
        if (goneS != null) {
            stars.remove(goneS);
        }
        if (ship.dead == true) {
            ship.flash += 1;
        }

        if (ship.dead == true) {
            if (ship.flash % 7 == 0) {
                ship.visible = !ship.visible;
            }
        }

//               if (resetB == false){
//             for(Bullet b: ship.bullets){
//                 goneB = b;
//             
//               }
//         }
//           if(goneB != null){
//           ship.bullets.remove(goneB);
//           resetB = true;
//           }

    /******/ update(offg);
    }

    //@Override
    public void start() {
        gameTimer.start();

    }

    //@Override
    public void stop() {
        gameTimer.stop();
    }

    public boolean collision(VectorSprite obj1, VectorSprite obj2) {
        for (int i = 0; i < obj1.dshape.npoints; i++) {
            if (obj2.dshape.contains(obj1.dshape.xpoints[i], obj1.dshape.ypoints[i])) {
                return true;
            }
        }

        return false;
    }

    public boolean canSpawn() {
        for (Rock r : rocks) {
            if (ship.distance(r) < 150) {
                return false;
            }
        }
        return true;
    }

    public void buttonAction() {
        if (buttons[0]) {
            ship.angle += -0.15;
        }
        if (buttons[2]) {
            ship.angle += 0.15;
        }
        if (buttons[1]) {
            ship.thrust();
        }
        if (buttons[3]) {
            ship.shoot();
        }

    }

    public void restart() {
        ship.sLife = 3;
        rocks.removeAll(rocks);
        ship.xpos = 400;
        ship.ypos = 300;
        ship.speedx = 0;
        ship.speedy = 0;
        for (int i = 0; i < 6; i++) {
            rocks.add(new Rock().away((int) ship.xpos, (int) ship.ypos));
        }

    }

}
