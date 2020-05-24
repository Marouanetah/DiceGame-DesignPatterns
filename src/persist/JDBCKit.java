package persist;

import Core.HighScore;

public class JDBCKit extends PersistKit{

    @Override
    public HighScore makeKit() {
        HighScore hightScore = new HighScoreJDBC();
        return hightScore;
    }

    

  
    
}
