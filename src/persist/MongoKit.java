package persist;

import Core.HighScore;

public class MongoKit extends PersistKit {
    
    @Override
    public HighScore makeKit() {
        HighScore hightScore = new HighScoreMongo();
        return hightScore;
    }

}
