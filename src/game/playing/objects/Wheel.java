package game.playing.objects;

import game.graphics.PlayingGame;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.ImageIcon;
import javax.swing.JOptionPane;
import javax.swing.JPanel;

public class Wheel extends JPanel {

    //constructor
    public Wheel() {
        super();
        setSize(300, 300);
        mouseHandler = new MouseHandler();
    }
   
    public void enableWheel() {
        addMouseListener(mouseHandler);
    }
    
    public void disableWheel() {
        removeMouseListener(mouseHandler);
    }
    
    private MouseHandler mouseHandler;
    
    private PlayingGame playingGameState;

    public void setPlayingGameState(PlayingGame playingGameState) {
        this.playingGameState = playingGameState;
    }

    private double currentAngle;
    private double force;

    static final double DELTA = Math.PI / 48;
    static final double MAXFORCE = 10;

    //may man = 1, chia doi = 2, gap doi = 3, mat luot = 4, them luot = 5; 
    private static final int valueMap[] = {900, 200, 1, 500, 100, 2, 200, 400, 100,
        600, 3, 4, 200, 700, 400, 4, 800, 200,
        5, 400, 300, 800, 3, 100};

    public void forceCalculate(long x1, long x2, long y1, long y2, long t1, long t2) {
        //f = m*a = m * s/t^2           assume that m = 1
        force = 500 * Math.sqrt((x1 - x2) * (x1 - x2) + (y1 - y2) * (y1 - y2)) / ((t2 - t1) * (t2 - t1));
        
        //set max value
        if (force > MAXFORCE) {
            force = MAXFORCE;
        }
        System.out.println("force");
        System.out.println(force);
        playingGameState.updateDynamometer(force * 50);
    }

    //get force (pass force value to Dynamometer)
    public double getForce() {
        return force;
    }

    //assume angle ~ force
    public double angleCalculate(double force) {
        double angle = force * 5;
        if (angle>45) angle=45;
        System.out.println("angle");
        System.out.println(angle);
        return angle;
    }

    public int scoreCalculate() {
        int index = (int) (((currentAngle )/ (4 * DELTA)) % 24);   //calculate index of value in value map
        System.out.println("index");
        System.out.println(index);
        int score = valueMap[index];
        JOptionPane.showMessageDialog(null,"Diem ban dat duoc la "+score);
        return score;
    }

    //create new threat implements rotation
    public void rotate(double angle) {
        Runnable rotateThread = new RotateThread(angle);
        Thread rotate = new Thread(rotateThread);
        rotate.start();
    }

    public class RotateThread implements Runnable {

        double angle;

        //constructor
        public RotateThread(double x) {
            angle = x;
        }

        @Override
        public void run() {
//            JOptionPane.showMessageDialog(null, "luc: " + force);
            //specify how many loop will be execute
            int n = (int) (angle / DELTA);

            //set default sleep time = 10 milisec
            long sleepTime = 5;
            int j = 0;

            for (int i = 0; i < n; i++) {
                currentAngle += DELTA / 2;    //rotate DELTA/2
                repaint();

                //slow down the wheel's rotation
                try {
                    if (i <= 4 * n / 5) {
                        Thread.sleep(sleepTime);
                    } else {
                        j += 1;
                        if (j == 3) {
                            j = 0;
                            sleepTime += 1;
                        }
                        Thread.sleep(sleepTime);
                    }
                } catch (InterruptedException ex) {
                    Logger.getLogger(Wheel.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
            System.out.println("current");
            System.out.println(currentAngle);
            int score = scoreCalculate();
            playingGameState.setResultOfWheel(score);
            playingGameState.checkWheel();
        }
    }

    //draw the wheel rotated specified angle
    @Override
    public void paintComponent(Graphics g) {
        super.paintComponent(g);
        Graphics2D g2d = (Graphics2D) g;
        
        // ve cai kim
        Image cursorImage = new ImageIcon("images/cursor.png").getImage();
        g2d.drawImage(cursorImage, getWidth() / 2 , 0, this);
        
        // ve cai non
        Image wheelImage = new ImageIcon("images/hat.png").getImage();     //get Wheel image
        g2d.rotate(currentAngle, getWidth() / 2, getHeight() / 2);    //rotate the wheel image

        //draw wheel at center of the panel
        int x = (getWidth() - wheelImage.getWidth(this) / 2) / 2;
        int y = (getHeight() - wheelImage.getHeight(this) / 2) / 2;
        g2d.drawImage(wheelImage, x, y, wheelImage.getHeight(this) / 2, wheelImage.getWidth(this) / 2, this);
    }

    //mouse 
    private class MouseHandler implements MouseListener, Runnable {

        //position and time of mouse when pressed and released
        long x1, x2, y1, y2, t1, t2;

        @Override
        public void mouseClicked(MouseEvent e) {
        }

        @Override
        public void mousePressed(MouseEvent e) {
            //get mouse Pressed coordinate and time
            x1 = e.getX();
            y1 = e.getY();
            t1 = System.currentTimeMillis();
        }

        @Override
        public void mouseReleased(MouseEvent e) {
            //get mouse release coordinate and time
            x2 = e.getX();
            y2 = e.getY();
            t2 = System.currentTimeMillis();

            //calculate the force and angle then rotate the wheel
            forceCalculate(x1, x2, y1, y2, t1, t2);
            rotate(angleCalculate(force*10));
            
            disableWheel();
        }

        @Override
        public void mouseEntered(MouseEvent e) {
        }

        @Override
        public void mouseExited(MouseEvent e) {
        }

        @Override
        public void run() {
        }
    }
}
