package persist;

import Core.Entry;
import Core.HighScore;
import java.sql.ResultSet;
import java.util.Enumeration;

public class HighScoreJDBC extends HighScore {

    private final String SERVEUR = "localhost";
    private final String USER = "root";
    private final String PASSWORD = "abc*123";
    private final String BDD = "dicegame";

    private SQLHelper sql = null;

    public HighScoreJDBC() {
        sql = new SQLHelper(SERVEUR, USER, PASSWORD, BDD);
    }
    
    public void save() {
        sql.connexion();
        
        String DELETE = "TRUNCATE TABLE highscore";
        boolean b = sql.executeRequest(DELETE);
        System.out.println("truncate " + b);

        for (Enumeration e = entries.elements(); e.hasMoreElements();) {
            Entry entry = (Entry) e.nextElement();
            
            String INSERT = "INSERT INTO highscore (name, Score) VALUES ('" + entry.getName() + "', " + entry.getScore() + ");";
            sql.executeRequest(INSERT);
            System.out.println("Enregistré avec Succès!");
        }
        sql.close();
    }

    public void load() {
        sql.connexion();

        try {
            String SELECT = "SELECT * FROM highscore";
            sql.executeRequest(SELECT);
            ResultSet rs;
            while ((rs = sql.fetchArray()) != null) {
                this.add(new Entry(rs.getString(1), rs.getInt(2)));
            }
            sortEntries();
            System.out.println("Liste JDBC Chargé avec Succès!");
        } catch (Exception e) {
            e.printStackTrace();
        }
        
        sql.close();
    }

    

    
}
