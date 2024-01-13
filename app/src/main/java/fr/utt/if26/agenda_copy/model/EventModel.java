package fr.utt.if26.agenda_copy.model;
import androidx.room.Entity;
import androidx.room.PrimaryKey;


@Entity(tableName = "events")
public class EventModel {

    @PrimaryKey(autoGenerate = true)
    public int event_id;

    private final String couleur;
    private String titre, description, constance, heure;
    private boolean allday;
    private int notification;

    public String getCouleur() {
        return couleur;
    }

    public long getId() {
        return event_id;
    }

    public void setId(int id) {
        this.event_id = event_id;
    }

    public EventModel(String titre, String description, String constance, String heure, boolean allday, int notification, String couleur) {
        this.titre = titre;
        this.description = description;
        this.constance = constance;
        this.heure = heure;
        this.allday = allday;
        this.notification = notification;
        this.couleur = couleur;
    }

    public String getTitre() {
        return titre;
    }

    public void setTitre(String titre) {
        this.titre = titre;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getConstance() {
        return constance;
    }

    public void setConstance(String constance) {
        this.constance = constance;
    }

    public String getHeure() {
        return heure;
    }

    public void setHeure(String heure) {
        this.heure = heure;
    }

    public boolean isAllday() {
        return allday;
    }

    public void setAllday(boolean allday) {
        this.allday = allday;
    }

    public int getNotification() {
        return notification;
    }

    public void setNotification(int notification) {
        this.notification = notification;
    }
}
