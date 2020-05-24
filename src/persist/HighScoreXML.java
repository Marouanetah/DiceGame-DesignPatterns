package persist;

import Core.HighScore;
import java.beans.XMLDecoder;
import java.beans.XMLEncoder;
import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.Serializable;

public class HighScoreXML extends HighScore implements Serializable {

    private final String filename = "highscores.xml";

    public HighScoreXML() {
        //create file if not exist
        File yourFile = new File(filename);
        if (!yourFile.exists()) {
            try {
                yourFile.createNewFile();
                System.out.println("Fichier Créé XML");
            } catch (IOException ex) {
                System.out.println("Impossible de Créer le Fichier XML : Erreur - " + ex.getMessage());
            }
        }
    }

    @Override
    public void save() {
        XMLEncoder e = null;
        try {
            e = new XMLEncoder(new BufferedOutputStream(new FileOutputStream(filename)));
            e.writeObject(this);
            e.flush();
            System.out.println("Enregistré avec Succès!");
        } catch (Exception ex) {
            ex.printStackTrace();
        } finally {
            e.close();
        }
    }

    @Override
    public void load() {
        HighScore highScore = null;
        XMLDecoder decoder = null;
        try {
            FileInputStream inputStream = new FileInputStream(filename);
            if(inputStream.available() <= 0)
                return;
            decoder = new XMLDecoder(inputStream);
            highScore = (HighScore) decoder.readObject();
            this.entries = highScore.entries;
            sortEntries();
            System.out.println("Liste XML Chargé avec Succès!");
        } catch (Exception ex) {
            ex.printStackTrace();
        } finally {
            if (decoder != null) {
                decoder.close();
            }
        }
    }
}
