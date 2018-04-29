package game.playing.objects;

import java.awt.Color;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JProgressBar;

public class Dynamometer extends JPanel implements Runnable {

    final static int MAX_PROGRESS_BAR = 100;
    final static int MAX_FORCE = 100;

    public Dynamometer() {
        setLayout(null);
        setSize(100, 250);
        setVisible(true);
        
        JLabel label=new JLabel("Lực kế");
        label.setForeground(Color.red);
        label.setBounds(40,220,50,10);
        add(label);
        
        pb = new JProgressBar();
        pb.setBounds(50, 0, 10, 200);
        pb.setMinimum(0);
        pb.setMaximum(MAX_PROGRESS_BAR);
        pb.setForeground(Color.red);
        pb.setOrientation(JProgressBar.VERTICAL);
        add(pb);
    }

    private JProgressBar pb;
    private Thread th;
    private int force;

    public void display(int f) {
        force = f;
        System.out.println("dyna:");
        System.out.println(force);
        th = new Thread(this);
        th.start();
    }

    public void display(double f) {
        display((int) f);
    }

    @Override
    public void run() {
        if (force > MAX_FORCE) {
            force = MAX_FORCE;
        }
        for (int i = 0; i < force; i++) {
            try {
                pb.setValue(i);
                th.sleep(5);
            } catch (InterruptedException ex) {
                Logger.getLogger(Dynamometer.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
    }
}
