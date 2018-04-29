
package game.graphics;

import game.controllers.GameManager;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.*;


public class StartMenu extends GameState implements ActionListener{
    private JButton bNewGame;
    private JButton bExit;
    
    public StartMenu(GameManager gameManager){
//        super();
        this.gameManager = gameManager;
        setLayout(null);
    }
    
    public void createComponents(){
        
        bNewGame = new JButton("Start Game");
        bExit = new JButton("Exit Game");
        add(bNewGame);
        add(bExit);
        bNewGame.setBounds(400, 350, 240, 60);
        bExit.setBounds(400, 420, 240, 60);
        bNewGame.addActionListener(this);
        bExit.addActionListener(this);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        JButton b = (JButton)e.getSource();
        if(b == bExit)
            System.exit(0);
        else if(b == bNewGame){
            gameManager.updateState(GameState.STATE.NEW_GAME);
        }
           
            
    }

}
    
