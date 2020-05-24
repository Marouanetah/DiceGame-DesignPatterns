package util;

import java.util.Random;

public class Randomizer {

    private Random rand;

    private Randomizer() {
        rand = new Random();
    }

    private static Randomizer instance = null;

    public static synchronized Randomizer getInstance() {
        if (instance == null) {
            instance = new Randomizer();
        }
        return instance;

    }

    public int getValue() {
        return rand.nextInt(6) + 1;

    }

}
