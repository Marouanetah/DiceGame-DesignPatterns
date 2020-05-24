package main;

import UI.MainUI;
import javax.swing.JFrame;

public class main {
       
    public static void main(String[] args) {

        JFrame f=new JFrame();
        MainUI pf = new MainUI();
        f.setTitle("DiceGameISGA!");
        f.add(pf.Display());
        f.pack();
        f.setSize(300,400);
        f.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        f.setVisible(true);
        f.setLocationRelativeTo(null);
        f.setResizable(false);
        
    }
    
}
