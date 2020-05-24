package persist;


import Core.HighScore;

public class Xmlkit extends PersistKit {

    @Override
    public HighScore makeKit() {
        HighScore hightScore = new HighScoreXML();
        return hightScore;
    }

 
    
    
}
