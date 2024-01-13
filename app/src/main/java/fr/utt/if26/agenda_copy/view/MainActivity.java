package fr.utt.if26.agenda_copy.view;

import static fr.utt.if26.agenda_copy.viewmodel.CalendarUtils.daysInMonthArray;
import static fr.utt.if26.agenda_copy.viewmodel.CalendarUtils.monthYearFromDate;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.room.Room;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.PopupMenu;
import android.widget.TextView;

import java.time.LocalDate;
import java.util.ArrayList;

import fr.utt.if26.agenda_copy.room.AppDatabase;
import fr.utt.if26.agenda_copy.viewmodel.CalendarUtils;
import fr.utt.if26.agenda_copy.R;
import fr.utt.if26.agenda_copy.viewmodel.calendarViewAdapter;
import android.app.Application;
import androidx.room.Room;

public class MainActivity extends AppCompatActivity implements calendarViewAdapter.onItemListener {
    private TextView monthYearText;
    private RecyclerView calendarRecyclerView;
    private Button nextMonth;
    private Button previousMonth;
    private ImageButton showMenuButton, eventButton;
    //private GestureDetector gestureDetector;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        //ROOM
        //setContentView(R.layout.activity_main);
        //AppDatabase appDatabase = Room.databaseBuilder(getApplicationContext(), AppDatabase.class, "mydatabase").build();

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
                startActivity(new Intent(getApplicationContext(), Event.class));
            }
        });

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

        calendarViewAdapter calendarAdapter = new calendarViewAdapter(daysInMonth, this);
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
    public void onItemClick(int position, LocalDate date) {

        if(date != null){
            CalendarUtils.selectedDate = date;
            setMonthView();
        }
    }
}