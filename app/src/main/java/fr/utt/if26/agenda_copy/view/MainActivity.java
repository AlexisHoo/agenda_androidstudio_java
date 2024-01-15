package fr.utt.if26.agenda_copy.view;

import static fr.utt.if26.agenda_copy.viewmodel.CalendarUtils.daysInMonthArray;
import static fr.utt.if26.agenda_copy.viewmodel.CalendarUtils.monthYearFromDate;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.room.Room;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.PopupMenu;
import android.widget.TextView;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import fr.utt.if26.agenda_copy.model.DayModel;
import fr.utt.if26.agenda_copy.model.EventModel;
import fr.utt.if26.agenda_copy.room.AppDatabase;
import fr.utt.if26.agenda_copy.viewmodel.CalendarUtils;
import fr.utt.if26.agenda_copy.R;
import fr.utt.if26.agenda_copy.viewmodel.calendarViewAdapter;
import fr.utt.if26.agenda_copy.viewmodel.eventViewModel;

import android.app.Application;
import android.widget.Toast;

import androidx.room.Room;

public class MainActivity extends AppCompatActivity implements calendarViewAdapter.onItemListener {
    private TextView monthYearText;
    private RecyclerView calendarRecyclerView;
    private Button nextMonth;
    private Button previousMonth;
    private ImageButton showMenuButton, eventButton;
    //private GestureDetector gestureDetector;

    private eventViewModel eventVM;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        //ROOM
        eventVM = new ViewModelProvider(this).get(eventViewModel .class);
        eventVM.getAllEvents().observe(this, new Observer<List<EventModel>>(){

            @Override
            public void onChanged(@NonNull List<EventModel> events){
                Toast.makeText(MainActivity.this, "OnChanged", Toast.LENGTH_SHORT).show();
            }

        });

        initWidgets();
        CalendarUtils.selectedDate = LocalDate.now();
        setMonthView();

        showMenuButton.setOnClickListener(v -> showPopupMenu(v));

        previousMonth.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                CalendarUtils.selectedDate = CalendarUtils.selectedDate.minusMonths(1);
                setMonthView();
            }
        });

        nextMonth.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                CalendarUtils.selectedDate = CalendarUtils.selectedDate.plusMonths(1);
                setMonthView();
            }
        });

        eventButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //startActivity(new Intent(getApplicationContext(), Event.class));
                Intent intent = new Intent(MainActivity.this, Event.class);
                startActivityForResult(intent, 1);

                //String toast = eventVM.getAllEvents().getValue().get( eventVM.getAllEvents().getValue().size() -1 ).getTitre();
              }
        });

    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        String title = data.getStringExtra("Titre");
        String description = data.getStringExtra("Description");
        String constance = data.getStringExtra("constance");
        String heure = data.getStringExtra("heure");
        Boolean allday = data.getBooleanExtra("allday", false);
        int notification = data.getIntExtra("notification", 15);
        String couleur = data.getStringExtra("couleur");


        EventModel eventModel = new EventModel(title, description, constance, heure, allday, notification, couleur, CalendarUtils.selectedDate.getYear(), CalendarUtils.selectedDate.getMonth().getValue(), CalendarUtils.selectedDate.getDayOfMonth());
        String anneeE = Integer.toString( CalendarUtils.selectedDate.getYear() );
        String moisE = Integer.toString( CalendarUtils.selectedDate.getMonth().getValue() );
        String jourE = Integer.toString( CalendarUtils.selectedDate.getDayOfMonth());
        //Log.d("EVENT CREEE",  "EVENT CREEE" +  anneeE + moisE + jourE );
        Toast.makeText(this, "EVENT CREEE" +  anneeE + moisE + jourE, Toast.LENGTH_SHORT).show();
        eventVM.insert(eventModel);
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
            return false;
        });


        popupMenu.show(); // Affichage du menu contextuel
    }

    private void setMonthView() {

        monthYearText.setText(monthYearFromDate(CalendarUtils.selectedDate));
        ArrayList<LocalDate> daysInMonth = daysInMonthArray(CalendarUtils.selectedDate);

        ArrayList<DayModel> dayEventList = new ArrayList<>();
        for(int i = 0 ; i <daysInMonth.size(); i++){

            if(daysInMonth.get(i) != null){

                //Event avec la date du jour
                int annee = daysInMonth.get(i).getYear();
                int mois = daysInMonth.get(i).getMonthValue();
                int jour = daysInMonth.get(i).getDayOfMonth();

                //Log.d("TEST", "DATE"+ annee + mois + jour );
                if(eventVM.getAllEvents().getValue() != null){
                    String anneeE = Integer.toString( eventVM.getAllEvents().getValue().get(0).getAnnee() );
                    String moisE = Integer.toString( eventVM.getAllEvents().getValue().get(0).getMois() );
                    String jourE = Integer.toString( eventVM.getAllEvents().getValue().get(0).getJour() );
                    //Log.d("TEST",  "DATE EVENT" +  anneeE + moisE + jourE );
                }


                EventModel event = eventVM.getEvent(annee, mois, jour);

                if (event != null){

                    String anneeE = Integer.toString( event.getAnnee() );
                    String moisE = Integer.toString( event.getMois() );
                    String jourE = Integer.toString( event.getJour() );
                    //Log.d("TEST",  "DATE EVENT" +  anneeE + moisE + jourE );
                    Toast.makeText(this, "EVENT TROUVE" +  anneeE + moisE + jourE, Toast.LENGTH_SHORT).show();
                    Log.d("EVENT TROUVE", event.getTitre().toString());
                    DayModel day = new DayModel(event.getTitre(), daysInMonth.get(i), event.getCouleur());
                    dayEventList.add(day);
                }else{

                    DayModel day = new DayModel("hhhhhh", daysInMonth.get(i), "#0000FF");
                    dayEventList.add(day);
                }

            }

            else{

                DayModel day = new DayModel("hhhhhh", null, "#0000FF");
                dayEventList.add(day);
                //Log.d("EVENT NON TROUVE", "EVENT NON TROUVE");
            }
        }


        calendarViewAdapter calendarAdapter = new calendarViewAdapter(dayEventList, this);
        RecyclerView.LayoutManager layoutManager = new GridLayoutManager(getApplicationContext(),7);
        calendarRecyclerView.setLayoutManager(layoutManager);
        calendarRecyclerView.setAdapter(calendarAdapter);

    }



    private void initWidgets() {

        previousMonth = findViewById(R.id.previousMonth);
        showMenuButton = findViewById(R.id.burgerButton);
        nextMonth = findViewById(R.id.nextMonth);
        calendarRecyclerView = findViewById(R.id.calendarRecyclerView);
        monthYearText = findViewById(R.id.monthYearTv);
        eventButton = findViewById(R.id.plus);

    }



    @Override
    public void onItemClick(int position, DayModel day) {

        if(day != null){
            CalendarUtils.selectedDate = day.getDate();
            setMonthView();
        }
    }
}