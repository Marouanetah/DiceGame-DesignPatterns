package Core;

import UI.PlayerView;
import java.awt.Color;
import java.awt.Component;
import java.util.Observable;
import java.util.Observer;


public class Player  extends Observable{
    
    private String name;
    private int Score = 0;

    public Player() {
    }
    
    public Player(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }
    public int getScore() {
        return Score;
    }

    public void setScore(int Score) {
        this.Score = Score;
        this.setChanged();
        this.notifyObservers();
    }

      public void setName(String name) {
       
        this.setName(name);
        this.setChanged();
        this.notifyObservers();
    }
    
    public Component display() {
        Component c = new PlayerView(this);
		this.addObserver((Observer) c);
                
		return c;
    
    }
 
    
}
