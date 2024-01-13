package fr.utt.if26.agenda_copy.room;

import android.content.Context;

import androidx.room.Database;
import androidx.room.Room;
import androidx.room.RoomDatabase;

import fr.utt.if26.agenda_copy.model.EventModel;

@Database(entities = {EventModel.class}, version = 1)
public abstract class AppDatabase extends RoomDatabase {

    private static AppDatabase instance;
    public abstract EventDao getEventDAO();

    public static synchronized AppDatabase getInstance(Context context){

        if(instance == null){
            instance = Room.databaseBuilder(context.getApplicationContext(),
                    AppDatabase.class, "events")
                    .fallbackToDestructiveMigration()
                    .build();
        }

        return instance;
    }

}

