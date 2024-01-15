package fr.utt.if26.agenda_copy.room;

import android.content.Context;
import android.os.AsyncTask;

import androidx.annotation.NonNull;
import androidx.room.Database;
import androidx.room.Room;
import androidx.room.RoomDatabase;
import androidx.sqlite.db.SupportSQLiteDatabase;

import fr.utt.if26.agenda_copy.model.EventModel;

@Database(entities = {EventModel.class}, version = 2)
public abstract class AppDatabase extends RoomDatabase {

    private static AppDatabase instance;
    public abstract EventDao getEventDAO();

    public static synchronized AppDatabase getInstance(Context context){

        if(instance == null){
            instance = Room.databaseBuilder(context.getApplicationContext(),
                    AppDatabase.class, "events")
                    .fallbackToDestructiveMigration()
                    .addCallback(roomCallback)
                    .build();
        }

        return instance;
    }

    private static RoomDatabase.Callback roomCallback = new RoomDatabase.Callback(){
        @Override
        public void onCreate(@NonNull SupportSQLiteDatabase db) {
            super.onCreate(db);
            new PopulateDbAsyncTask(instance).execute();

        }
    };

    private static class PopulateDbAsyncTask extends AsyncTask<Void, Void,Void> {

        private EventDao eventDao;
        private PopulateDbAsyncTask(AppDatabase db){
            eventDao = db.getEventDAO();

        }
        @Override
        protected Void doInBackground(Void... voids){

            eventDao.insertEvent(new EventModel("Anniversaire Alexis", "...", "tous les jours", "Europe", true, 15, "#0000FF", 2024, 01, 5));
            return null;
        }
    }

}

