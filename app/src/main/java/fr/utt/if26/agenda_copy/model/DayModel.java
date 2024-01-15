package fr.utt.if26.agenda_copy.model;

import java.time.LocalDate;

public class DayModel {

    private String title, couleur;


    private LocalDate date;

    public DayModel(String title, LocalDate date, String couleur) {
        this.title = title;
        this.date = date;
        this.couleur = couleur;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getCouleur() {
        return couleur;
    }

    public void setCouleur(String couleur) {
        this.couleur = couleur;
    }

    public LocalDate getDate() {
        return date;
    }

    public void setDate(LocalDate date) {
        this.date = date;
    }
}
