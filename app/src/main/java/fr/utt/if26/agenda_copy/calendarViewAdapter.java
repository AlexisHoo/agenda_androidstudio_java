package fr.utt.if26.agenda_copy;

import android.graphics.Color;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.time.LocalDate;
import java.util.ArrayList;

public class calendarViewAdapter extends RecyclerView.Adapter<calendarViewHolder> {

    private final ArrayList<LocalDate> days;
    private final onItemListener onItemListener;

    public calendarViewAdapter(ArrayList<LocalDate> days, onItemListener onItemListener) {
        this.days = days;
        this.onItemListener = onItemListener;
    }

    @NonNull
    @Override
    public calendarViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(parent.getContext());
        View view = inflater.inflate(R.layout.calendar_cell, parent, false);
        ViewGroup.LayoutParams layoutParams = view.getLayoutParams();

        if(days.size() > 15)
            layoutParams.height = (int) (parent.getHeight() * 0.1666666);
        else
            layoutParams.height = (int) parent.getHeight();

        return new calendarViewHolder(view, onItemListener, days);
    }

    @Override
    public void onBindViewHolder(@NonNull calendarViewHolder holder, int position) {
        final LocalDate date = days.get(position);

        if(date == null)
            holder.dayOfMonth.setText("");
        else{
            holder.dayOfMonth.setText(String.valueOf(date.getDayOfMonth()));
            if(date.equals(CalendarUtils.selectedDate)){

                holder.parentView.setBackgroundColor(Color.LTGRAY);
            }
        }

    }

    @Override
    public int getItemCount() {
        return days.size();
    }

    public interface onItemListener{
        void onItemClick(int position, LocalDate date);
    }
}

