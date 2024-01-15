package fr.utt.if26.agenda_copy.room;

import android.app.Application;
import android.os.AsyncTask;

import androidx.lifecycle.LiveData;
import androidx.room.Query;

import java.util.List;

import fr.utt.if26.agenda_copy.model.EventModel;

public class eventRepository {

    private EventDao eventDao;
    private LiveData<List<EventModel>> allEvents;
    private EventModel event;
    public eventRepository(Application application){
        AppDatabase eventDB = AppDatabase.getInstance(application);
        eventDao = eventDB.getEventDAO();
        allEvents = eventDao.getAllEvents();
    }

    public void insert(EventModel eventModel){

        new InsertEventAsyncTask(eventDao).execute(eventModel);

    }

    public void delete(EventModel eventModel){
        new DeleteEventAsyncTask(eventDao).execute(eventModel);
    }

    public void update(EventModel eventModel){
        new UpdateEventAsyncTask(eventDao).execute(eventModel);
    }

    //@Query("select events.titre from events where annee==:annee and mois==:mois and jour==:jour")
    public EventModel getEvent(int annee, int mois, int jour){

        return eventDao.getEvent(annee, mois, jour);
    }

    public LiveData<List<EventModel>> getAllEvents(){
        return allEvents;
    }

    private static class InsertEventAsyncTask extends AsyncTask<EventModel, Void,Void> {

        private EventDao eventDao;
        private InsertEventAsyncTask(EventDao eventDao){
            this.eventDao = eventDao;
        }
        @Override
        protected Void doInBackground(EventModel... eventModels){
            eventDao.insertEvent(eventModels[0]);
            return null;
        }
    }

    private static class UpdateEventAsyncTask extends AsyncTask<EventModel, Void,Void> {

        private EventDao eventDao;
        private UpdateEventAsyncTask(EventDao eventDao){
            this.eventDao = eventDao;
        }
        @Override
        protected Void doInBackground(EventModel... eventModels){
            eventDao.updateEvent(eventModels[0]);
            return null;
        }
    }

    private static class DeleteEventAsyncTask extends AsyncTask<EventModel, Void,Void> {

        private EventDao eventDao;
        private DeleteEventAsyncTask(EventDao eventDao){
            this.eventDao = eventDao;
        }
        @Override
        protected Void doInBackground(EventModel... eventModels){
            eventDao.deleteEvent(eventModels[0]);
            return null;
        }
    }
}
