package persist;

import Core.Entry;
import Core.HighScore;
import java.util.List;
import java.util.Vector;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

public class HighScoreMongo extends HighScore{

    EntityManagerFactory entityManagerFactory = Persistence.createEntityManagerFactory("miagePU");
    

    
    @Override
    public void save() {
        EntityManager entityManager = entityManagerFactory.createEntityManager();
        //delete old
        for(Entry e : entries){
            e = entityManager.merge(e);
            entityManager.remove(e);
        }
        //save
        entityManager.getTransaction().begin();
        for(Entry e : entries){
            entityManager.persist(e);
        }
        entityManager.getTransaction().commit();
        entityManager.close();
    }

    @Override
    public void load() {
        System.out.println("loading mongo");
        EntityManager entityManager = entityManagerFactory.createEntityManager();
        entityManager.getTransaction().begin();
        List<Entry> list = entityManager.createQuery("FROM Entry").getResultList();
        System.out.println("entrie size "+list.size());
        entries = new Vector<Entry>();
        for(Entry e : list){
            entries.add(e);
        }
        entityManager.close();
    }
    
}
