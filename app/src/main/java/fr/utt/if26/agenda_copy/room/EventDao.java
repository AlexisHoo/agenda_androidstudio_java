package fr.utt.if26.agenda_copy.room;

import androidx.lifecycle.LiveData;
import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.Query;
import androidx.room.Update;

import java.util.List;

import fr.utt.if26.agenda_copy.model.EventModel;

@Dao
public interface EventDao {
    @Insert
    public void insertEvent(EventModel event);

    @Update
    public void updateEvent(EventModel event);

    @Delete
    public void deleteEvent(EventModel event);

    @Query("select * from events")
    public LiveData<List<EventModel>> getAllEvents();

    @Query("select * from events where annee = :annee and mois = :mois and jour = :jour")
    public EventModel getEvent(int annee, int mois, int jour);

}

