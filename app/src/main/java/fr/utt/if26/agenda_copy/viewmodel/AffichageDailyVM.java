package fr.utt.if26.agenda_copy.viewmodel;

import android.app.Application;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;

import java.util.List;

import fr.utt.if26.agenda_copy.model.EventModel;
import fr.utt.if26.agenda_copy.room.eventRepository;

public class AffichageDailyVM extends AndroidViewModel {

    private eventRepository repository;

    public List<EventModel> getEvent(int annee, int mois, int jour){
        return repository.getEvent(annee, mois, jour);
    }

    public LiveData<List<EventModel>> getEventLive(int annee, int mois, int jour){
        return repository.getEventLive(annee, mois, jour);
    }


    public AffichageDailyVM(@NonNull Application application) {
        super(application);
        repository = new eventRepository(application);
    }
}
