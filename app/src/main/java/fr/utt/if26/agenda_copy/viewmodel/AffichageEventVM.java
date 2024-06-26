package fr.utt.if26.agenda_copy.viewmodel;

import android.app.Application;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;

import java.util.List;

import fr.utt.if26.agenda_copy.model.EventModel;
import fr.utt.if26.agenda_copy.room.eventRepository;

public class AffichageEventVM extends AndroidViewModel {

    private eventRepository repository;

    public void delete(EventModel eventModel){

        repository.delete(eventModel);
    }

    public void update(EventModel eventModel){

        repository.update(eventModel);
    }

    public AffichageEventVM(@NonNull Application application) {
        super(application);
        repository = new eventRepository(application);
    }

    public LiveData<List<EventModel>> getEventLive(int annee, int mois, int jour){
        return repository.getEventLive(annee, mois, jour);
    }
}
