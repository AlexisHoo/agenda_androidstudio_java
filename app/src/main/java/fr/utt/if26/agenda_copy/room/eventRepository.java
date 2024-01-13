package fr.utt.if26.agenda_copy.room;

import android.app.Application;
import android.os.AsyncTask;

import androidx.lifecycle.LiveData;

import java.util.List;

import fr.utt.if26.agenda_copy.model.EventModel;

public class eventRepository {

    private EventDao eventDao;
    private LiveData<List<EventModel>> allEvents;
    public eventRepository(Application application){
        AppDatabase eventDB = AppDatabase.getInstance(application);
        eventDao = eventDB.getEventDAO();
        allEvents = eventDao.getAllEvents();
    }

    public void insert(EventModel eventModel){

    }

    public void delete(EventModel eventModel){

    }

    public void update(EventModel eventModel){

    }

    public void getEvent(int id){


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
}
