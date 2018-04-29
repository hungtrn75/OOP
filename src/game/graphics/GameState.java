
package game.graphics;

import game.controllers.GameManager;
import javax.swing.JPanel;

public abstract class GameState extends JPanel{
    public enum STATE {
        START_MENU,
        NEW_GAME,
        PLAYING_GAME,
        
    }
    public abstract void createComponents();
    protected GameManager gameManager;
}
