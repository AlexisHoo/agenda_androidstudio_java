package fr.utt.if26.agenda_copy.view;

import static fr.utt.if26.agenda_copy.viewmodel.CalendarUtils.monthYearFromDate;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.PopupMenu;
import android.widget.TextView;
import android.widget.Toast;

import java.util.List;

import fr.utt.if26.agenda_copy.R;
import fr.utt.if26.agenda_copy.model.DayModel;
import fr.utt.if26.agenda_copy.model.EventModel;
import fr.utt.if26.agenda_copy.viewmodel.AffichageDailyVM;
import fr.utt.if26.agenda_copy.viewmodel.CalendarUtils;
import fr.utt.if26.agenda_copy.viewmodel.SelectEvent;
import fr.utt.if26.agenda_copy.viewmodel.eventAdapter;
import fr.utt.if26.agenda_copy.viewmodel.eventViewModel;

public class AffichageDaily extends AppCompatActivity implements SelectEvent{

    ImageButton burgerButton;
    TextView monthYearTv, eventAnnouncement;
    Button dateOfTheDay;

    RecyclerView eventRecycler;

    private AffichageDailyVM affichageDailyVM;
    private DayModel day;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_affichage_daily);
        initWidgets();

        affichageDailyVM = new ViewModelProvider(this).get(AffichageDailyVM.class);
        Intent intent = getIntent();
        day = (DayModel) intent.getSerializableExtra("listeEvenements"); // Utilisez "parcelable" si vous avez implémenté Parcelable

        eventAdapter adapterEvent = new eventAdapter(this);
        eventRecycler.setAdapter(adapterEvent);


        affichageDailyVM.getEventLive(day.getDate().getYear(), day.getDate().getMonthValue(), day.getDate().getDayOfMonth()).observe(this, new Observer<List<EventModel>>(){
            @Override
            public void onChanged(@NonNull List<EventModel> events){
                //Toast.makeText(AffichageDaily.this, "Live data getEvent", Toast.LENGTH_SHORT).show();
                int tt = events.size();
                initShow(tt);
                adapterEvent.setListOfEvents(events);
            }

        });


        //recyclerview
        eventRecycler.setLayoutManager(new LinearLayoutManager((this)));
        eventRecycler.setHasFixedSize(true);



        burgerButton.setOnClickListener(v -> showPopupMenu(v));

    }


    private void initShow(int eventNumber) {

        String title = monthYearFromDate(CalendarUtils.selectedDate);
        title = Character.toUpperCase(title.charAt(0)) + title.substring(1);

        monthYearTv.setText(title);
        dateOfTheDay.setText( String.valueOf(CalendarUtils.selectedDate.getDayOfMonth()));
        eventAnnouncement.setText("Il y a " + eventNumber + " event(s): ");
    }

    private void initWidgets() {
        burgerButton = findViewById(R.id.burgerButton);
        monthYearTv = findViewById(R.id.monthYearTv);
        dateOfTheDay = findViewById(R.id.dateOfTheDay);
        eventAnnouncement = findViewById(R.id.eventAnnouncement);
        eventRecycler = findViewById(R.id.eventRecycler);

    }

    private void showPopupMenu(View v) {
        PopupMenu popupMenu = new PopupMenu(this, v);
        popupMenu.getMenuInflater().inflate(R.menu.menu_main, popupMenu.getMenu());


        popupMenu.setOnMenuItemClickListener(item -> {
            if (item.getItemId() == R.id.menu_planning) {

                return true;
            } else if (item.getItemId() == R.id.menu_jour) {

                return true;
            }
            else if (item.getItemId() == R.id.menu_semaine) {

                startActivity(new Intent( this, Weekly.class));
                return true;
            }
            else if (item.getItemId() == R.id.menu_mois) {

                startActivity(new Intent( this, MainActivity.class));
                return true;
            }
            return false;
        });


        popupMenu.show();
    }

    @Override
    public void onItemClicked(EventModel event) {

        Intent intent = new Intent(AffichageDaily.this, AffichaeEvent.class);
        intent.putExtra("EXTRA_KEY", event);
        startActivity(intent);
    }

}