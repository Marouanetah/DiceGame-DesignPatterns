package Core;

import java.awt.Component;
import java.awt.Dimension;
import java.io.Serializable;
import java.util.Collections;
import java.util.Comparator;
import java.util.Vector;

import javax.swing.DefaultListCellRenderer;
import javax.swing.JList;
import javax.swing.JScrollPane;
import javax.swing.ListModel;
import javax.swing.SwingConstants;
import javax.swing.event.ListDataEvent;
import javax.swing.event.ListDataListener;

public abstract class HighScore implements Serializable, ListModel<Object> {

    public Vector<Entry> entries = new Vector<Entry>();
    protected Vector<ListDataListener> listeners = new Vector<ListDataListener>();
    public Player p;

    public abstract void save();

    public abstract void load();

    public void add(Entry entry) {
        //delete old entry for the player
        Vector<Entry> entriesCopy = (Vector<Entry>) entries.clone();
        for (Entry e : entriesCopy) {
            if (e.getName().equals(entry.getName())) {
                entries.removeElement(e);
            }
        }
        entries.addElement(entry);
        sortEntries();
        
        //set listener (will change the hisghscorelist if changed in the game)
        for (ListDataListener l : listeners) {
            l.intervalAdded(new ListDataEvent(this,
                    ListDataEvent.INTERVAL_ADDED, entries.size() - 1, entries
                    .size()));
        }

    }

    public Component display() {
        JList<?> jList = new JList<Object>(this);
        jList.setVisibleRowCount(5);
        JScrollPane scrollPane = new JScrollPane(jList);
        scrollPane.setPreferredSize(new Dimension(280, 90));
        DefaultListCellRenderer renderer = (DefaultListCellRenderer) jList.getCellRenderer();
        renderer.setHorizontalAlignment(SwingConstants.CENTER);
		Component c = scrollPane;
        return c;
    }

    public Entry getElementByName(String name) {
        for (int i = 0; i < getSize(); i++) {
            Entry en = getElementAt(i);
            if (en.getName().equals(name)) {
                return en;
            }
        }
        return null;
    }

    public void sortEntries() {
        //sort the entries by score
        Collections.sort(entries, new Comparator<Entry>() {
            @Override
            public int compare(Entry o1, Entry o2) {
                if (o1.getScore() < o2.getScore()) {
                    return 1;
                } else {
                    return -1;
                }
            }
        });
    }

    public Entry getElementAt(int index) {
        return entries.elementAt(index);
    }

    public int getSize() {
        return entries.size();
    }

    @Override
    public void addListDataListener(ListDataListener l) {
        listeners.add(l);
    }

    @Override
    public void removeListDataListener(ListDataListener l) {
        listeners.remove(l);
    }

}
