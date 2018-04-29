/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package game.graphics;

import game.controllers.GameManager;
import game.playing.objects.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

/**
 *
 * @author Dell
 */
public class PlayingGame extends GameState implements ActionListener{
    public PlayingGame(GameManager gameManager){
        super();
        this.gameManager = gameManager;
        setLayout(null);
    }

    private Wheel wheel;
    private Dynamometer dynamometer;
    private int resultOfWheel;      // điểm hiện tại lấy từ nón

    public void setResultOfWheel(int resultOfWheel) {
        this.resultOfWheel = resultOfWheel;
    }
    @Override
    public void createComponents() {
        // hiển thị lực kế
        dynamometer = new Dynamometer();
        dynamometer.setLocation(975, 30);
        add(dynamometer);
        //nón
        wheel = new Wheel();
        wheel.setLocation(700,30);
        wheel.setPlayingGameState(this);
        wheel.enableWheel();
        add(wheel);
        setVisible(true);
    }
    
    public void checkWheel() {
        //may man = 1, chia doi = 2, gap doi = 3, mat luot = 4, them luot = 5; 
            wheel.enableWheel();
    }
    public void continueWheel() {
        wheel.enableWheel();
    }
    
    public void updateDynamometer(double f) {
        dynamometer.display(f);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
    
}
