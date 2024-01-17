package fr.utt.if26.agenda_copy.viewmodel;

import android.app.Application;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;

import fr.utt.if26.agenda_copy.model.EventModel;
import fr.utt.if26.agenda_copy.room.eventRepository;

public class AffichageEventVM extends AndroidViewModel {

    private eventRepository repository;

    public void delete(EventModel eventModel){

        repository.delete(eventModel);
    }

    public AffichageEventVM(@NonNull Application application) {
        super(application);
        repository = new eventRepository(application);
    }
}
