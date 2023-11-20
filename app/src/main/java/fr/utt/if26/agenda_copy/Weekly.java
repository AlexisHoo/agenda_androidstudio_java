package fr.utt.if26.agenda_copy;

import static fr.utt.if26.agenda_copy.CalendarUtils.daysInMonthArray;
import static fr.utt.if26.agenda_copy.CalendarUtils.daysInWeekArray;
import static fr.utt.if26.agenda_copy.CalendarUtils.monthYearFromDate;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.TextView;
import android.widget.Toast;

import java.time.LocalDate;
import java.util.ArrayList;
//1:47

public class Weekly extends AppCompatActivity implements calendarViewAdapter.onItemListener{

    private Button previousWeek, nextWeek;
    private ImageButton showMenuButton;
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

    }

    @Override
    public void onItemClick(int position, String dayText) {

        String message = "Selected Date " + dayText + " " + monthYearFromDate(CalendarUtils.selectedDate);
        Toast.makeText(this, message, Toast.LENGTH_LONG).show();

    }
}