package fr.utt.if26.agenda_copy.view;

import static fr.utt.if26.agenda_copy.viewmodel.CalendarUtils.daysInWeekArray;
import static fr.utt.if26.agenda_copy.viewmodel.CalendarUtils.monthYearFromDate;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.PopupMenu;
import android.widget.TextView;

import java.time.LocalDate;
import java.util.ArrayList;

import fr.utt.if26.agenda_copy.viewmodel.CalendarUtils;
import fr.utt.if26.agenda_copy.R;
import fr.utt.if26.agenda_copy.viewmodel.calendarViewAdapter;
//1:47

public class Weekly extends AppCompatActivity implements calendarViewAdapter.onItemListener {

    private Button previousWeek, nextWeek;
    private ImageButton showMenuButton, eventButton;
    private RecyclerView calendarRecyclerViewWeek;
    private TextView weekTV;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_weekly);
        initWidgets();
        setWeekView();


        previousWeek.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                CalendarUtils.selectedDate = CalendarUtils.selectedDate.minusWeeks(1);
                setWeekView();
            }
        });

        nextWeek.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                CalendarUtils.selectedDate = CalendarUtils.selectedDate.plusWeeks(1);
                setWeekView();
            }
        });

        eventButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(getApplicationContext(), Event.class));
            }
        });

        showMenuButton.setOnClickListener(v -> showPopupMenu(v));
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
                // Action à effectuer pour l'Option 2
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


    private void setWeekView() {

        weekTV.setText(monthYearFromDate(CalendarUtils.selectedDate));
        ArrayList<LocalDate> days = daysInWeekArray(CalendarUtils.selectedDate);

        calendarViewAdapter calendarAdapter = new calendarViewAdapter(days, this);
        RecyclerView.LayoutManager layoutManager = new GridLayoutManager(getApplicationContext(),7);
        calendarRecyclerViewWeek.setLayoutManager(layoutManager);
        calendarRecyclerViewWeek.setAdapter(calendarAdapter);

    }

    private void initWidgets() {

        previousWeek = findViewById(R.id.previousWeek);
        nextWeek = findViewById(R.id.nextWeek);
        showMenuButton = findViewById(R.id.burgerButton);
        calendarRecyclerViewWeek = findViewById(R.id.calendarRecyclerView_week);
        weekTV = findViewById(R.id.weekTv);
        eventButton = findViewById(R.id.plus);

    }

    @Override
    public void onItemClick(int position, LocalDate date) {

        CalendarUtils.selectedDate = date;
        setWeekView();
    }
}