package UI;

import Core.Player;

import java.awt.BorderLayout;
import java.awt.Panel;
import java.util.Observable;
import java.util.Observer;

import javax.swing.JLabel;

//import sun.awt.VerticalBagLayout;

public class PlayerView extends Panel implements Observer {

    private JLabel name;
    private JLabel score;
    private Player player;

    public PlayerView(Player player) {
    	
    	
    	setLayout(new BorderLayout());
    	    	
    	System.out.println("PlayerView Interface");
    	
        name = new JLabel(player.getName(), JLabel.CENTER);
        name.setFont(new java.awt.Font("Arial", 1, 20));
        
        score = new JLabel("score : " + player.getScore(), JLabel.CENTER);
        score.setForeground(new java.awt.Color(0, 102, 51));
        score.setFont(new java.awt.Font("Tahoma", 1, 18));
               
        
        this.add(name, BorderLayout.PAGE_START);
        this.add(score, BorderLayout.PAGE_END);
        
    }

    @Override
    public void update(Observable o, Object o1) {
        score.setText("score: " + ((Player) o).getScore());
    }

}
