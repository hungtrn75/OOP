
package game.graphics;

import game.controllers.GameManager;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.*;
import objects.Players;

public class NewGame extends GameState implements ActionListener{
    private JFrame gameFrame;
    
    private JButton bPlay;
    private JTextField tfName1;
    private JTextField tfName2;
    private JTextField tfName3;
    

    private JLabel label1;
    private JLabel label2;
    private JLabel label3;
    private JLabel label4;
    
    public NewGame(GameManager gameManager){
//        super();
        this.gameManager = gameManager;
        setLayout(null);
    }
    
    public void createComponents(){
        
        tfName1 = new JTextField();
        tfName2 = new JTextField();
        tfName3 = new JTextField();
        
        bPlay = new JButton("Play");
        label1 = new JLabel("Player 1");
        label2 = new JLabel("Player 2");
        label3 = new JLabel("Player 3");
        label4 = new JLabel();
        tfName1.setBounds(170, 400, 150, 30);
        tfName2.setBounds(445, 400, 150, 30);
        tfName3.setBounds(720, 400, 150, 30);
        
        bPlay.setBounds(890, 480, 80, 40);
        label1.setBounds(170, 375, 100, 25);
        label2.setBounds(445, 375, 100, 25);
        label3.setBounds(720, 375, 100, 25);
        
        
        add(bPlay);
        add(tfName1);
        add(tfName2);
        add(tfName3);
        add(label1);
        add(label2);
        add(label3);
        add(label4);
        setVisible(true);
        label4.setVisible(false);
        
        bPlay.addActionListener(this);
    }
    


    @Override
    public void actionPerformed(ActionEvent e) {
        JButton a = (JButton)e.getSource();
        String name[] = new String[3];
        name[0] = tfName1.getText();
        name[1] = tfName2.getText();
        name[2] = tfName3.getText();
        if(a == bPlay){
            
            if(name[0].equals("") || name[1].equals("") || name[2].equals("")){
                JOptionPane.showMessageDialog(null, "Please Fill In All 3 Players' Names!", "Warning!", JOptionPane.WARNING_MESSAGE);
                
                
            }else{
                for(int i=0;i<=2;i++){
                    gameManager.players[i] = new Players();
                    gameManager.players[i].setName(name[i]);
                    
                }  
                gameManager.updateState(STATE.PLAYING_GAME);
            }
        }
        
        
    }
}
