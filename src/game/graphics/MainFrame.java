package game.graphics;

import javax.swing.JFrame;


public class MainFrame extends JFrame{
    private static final int WINDOW_WIDTH = 1100;
    private static final int WINDOW_HEIGHT = 700;
    private static final int X_COORDINATE = 50;
    private static final int Y_COORDINATE = 0;
    public MainFrame(){
        super("Wheel Of Fortune");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocation(X_COORDINATE, Y_COORDINATE);
        setSize(WINDOW_WIDTH, WINDOW_HEIGHT);
        setVisible(true);
        
    }

}
