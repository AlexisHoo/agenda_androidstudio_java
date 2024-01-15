package fr.utt.if26.agenda_copy.viewmodel;

import android.app.Application;
import android.content.Context;
import android.content.DialogInterface;
import android.graphics.Color;
import android.os.Bundle;
import android.util.Log;
import android.widget.Button;
import android.widget.TextView;

import androidx.activity.result.IntentSenderRequest;
import androidx.annotation.NonNull;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;
import androidx.room.Room;
import androidx.room.RoomDatabase;
import androidx.sqlite.db.SupportSQLiteDatabase;

import java.io.Console;
import java.util.List;

import fr.utt.if26.agenda_copy.R;
import fr.utt.if26.agenda_copy.model.EventModel;
import fr.utt.if26.agenda_copy.room.AppDatabase;
import fr.utt.if26.agenda_copy.room.eventRepository;
import fr.utt.if26.agenda_copy.view.Event;

public class eventViewModel extends AndroidViewModel {


    private eventRepository repository;
    private LiveData<List<EventModel>> allEvents;
    //private LiveData<EventModel> event;

    public eventViewModel(@NonNull Application application){

        super(application);

        repository = new eventRepository(application);
        allEvents = repository.getAllEvents();
    }

    public void insert(EventModel eventModel){

        repository.insert(eventModel);
    }

    public void update(EventModel eventModel){

        repository.update(eventModel);
    }

    public void delete(EventModel eventModel){

        repository.delete(eventModel);
    }

    public LiveData<List<EventModel>> getAllEvents (){
        return allEvents;
    }

    public List<EventModel> getEvent(int annee, int mois, int jour){
        return repository.getEvent(annee, mois, jour);
    }
}
