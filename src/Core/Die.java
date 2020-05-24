package Core;

import util.Randomizer;
import UI.DieView;
import java.awt.Component;
import java.util.Observable;
import java.util.Observer;

public class Die extends Observable {

    private int faceValue = 1;

    public int roll() {
        int r = Randomizer.getInstance().getValue();
        setFaceValue(r);
        return getFaceValue();
    }

    public Component display() {
        Component c = new DieView(this);
        this.addObserver((Observer) c);
        return c;
    }

    public int getFaceValue() {
        return faceValue;
    }

    public void setFaceValue(int faceValue) {
        this.faceValue = faceValue;
        this.setChanged();
        this.notifyObservers();
    }

}
