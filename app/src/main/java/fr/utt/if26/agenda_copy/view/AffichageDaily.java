package fr.utt.if26.agenda_copy.view;

import static fr.utt.if26.agenda_copy.viewmodel.CalendarUtils.monthYearFromDate;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageButton;
import android.widget.PopupMenu;
import android.widget.TextView;
import android.widget.Toast;

import java.util.List;

import fr.utt.if26.agenda_copy.R;
import fr.utt.if26.agenda_copy.model.EventModel;
import fr.utt.if26.agenda_copy.viewmodel.CalendarUtils;
import fr.utt.if26.agenda_copy.viewmodel.SelectEvent;
import fr.utt.if26.agenda_copy.viewmodel.eventAdapter;

public class AffichageDaily extends AppCompatActivity implements SelectEvent{

    ImageButton burgerButton;
    TextView monthYearTv, dateOfTheDay, eventAnnouncement;

    RecyclerView eventRecycler;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_affichage_daily);
        initWidgets();

        Intent intent = getIntent();
        List<EventModel> eventList = (List<EventModel>) intent.getSerializableExtra("listeEvenements"); // Utilisez "parcelable" si vous avez implémenté Parcelable
        int tt = eventList.size();
        initShow(tt);

        //recyclerview
        eventRecycler.setLayoutManager(new LinearLayoutManager((this)));
        eventRecycler.setHasFixedSize(true);

        eventAdapter adapterEvent = new eventAdapter(this);
        eventRecycler.setAdapter(adapterEvent);
        adapterEvent.setListOfEvents(eventList);

        burgerButton.setOnClickListener(v -> showPopupMenu(v));

        /*
        adapterEvent.setOnItemClickListener(new eventAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(int position) {
                Intent intent = new Intent(AffichageDaily.this, AffichaeEvent.class);
                intent.putExtra("EXTRA_KEY", adapterEvent.getItemId(position));
                startActivity(intent);
            }
        });

         */

    }


    private void initShow(int eventNumber) {

        monthYearTv.setText( String.valueOf(monthYearFromDate(CalendarUtils.selectedDate) ));
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
        PopupMenu popupMenu = new PopupMenu(this, v); // Création du PopupMenu associé au bouton
        popupMenu.getMenuInflater().inflate(R.menu.menu_main, popupMenu.getMenu());

        // Gestion des clics sur les éléments du menu
        popupMenu.setOnMenuItemClickListener(item -> {
            if (item.getItemId() == R.id.menu_planning) {
                // Action à effectuer pour l'Option 1
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


        popupMenu.show(); // Affichage du menu contextuel
    }

    @Override
    public void onItemClicked(EventModel event) {

        Intent intent = new Intent(AffichageDaily.this, AffichaeEvent.class);
        intent.putExtra("EXTRA_KEY", event);
        startActivity(intent);
    }

}