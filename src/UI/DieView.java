package UI;

import Core.Die;
import java.awt.Color;
import java.awt.Panel;
import java.util.Observable;
import java.util.Observer;
import javax.swing.ImageIcon;
import javax.swing.JLabel;

public class DieView extends Panel implements Observer {

    private JLabel d;
    private Die die;

    public DieView(Die die) {
    	
    	System.out.println("DieView Interface");
    	
        d = new JLabel(new ImageIcon(getClass().getResource("/images/" + die.getFaceValue() + ".png")));

        this.add(d);

        this.setBackground(Color.white);
        // this.setForeground(Color.WHITE);

    }

    @Override
    public void update(Observable o, Object o1) {

        d.setIcon(new ImageIcon(getClass().getResource("/images/" + ((Die) o).getFaceValue() + ".png")));

    }

}
