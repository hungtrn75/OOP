
package game.controllers;

import game.graphics.GameState;
import game.graphics.MainFrame;
import game.graphics.NewGame;
import game.graphics.PlayingGame;
import game.graphics.StartMenu;
import javax.swing.*;
import objects.Players;

public class GameManager {
    public Players[] players;
    
    private JFrame gameFrame;
    private NewGame newGame;
    private StartMenu startMenu;
    private GameState gameState;
    private PlayingGame playingGame;
    
    public GameManager(){
        players = new Players[3];
        this.gameFrame = new MainFrame();
        this.newGame = new NewGame(this);
        this.startMenu = new StartMenu(this);
        this.playingGame = new PlayingGame(this);
    }
    public void updateState(GameState.STATE state){
        gameFrame.getContentPane().removeAll();
        if (state == GameState.STATE.NEW_GAME){
            gameState = newGame;
        }
        else if (state == GameState.STATE.START_MENU){
            gameState = startMenu;
        }
        else if (state == GameState.STATE.PLAYING_GAME){
            gameState = playingGame;
        }
        gameState.createComponents();
        gameFrame.getContentPane().add(gameState);
        gameFrame.getContentPane().revalidate();
        gameFrame.getContentPane().repaint();
        
        gameFrame.setVisible(true);
    }
}
