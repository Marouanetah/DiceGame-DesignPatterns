package persist;


import Core.HighScore;

public class Srkit extends PersistKit{

    @Override
    public HighScore makeKit() {
        HighScore hightScore = new HighScoreSr();
        return hightScore;
    }
 
}
