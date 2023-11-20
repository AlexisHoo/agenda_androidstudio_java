package fr.utt.if26.agenda_copy;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

public class calendarAdapterWeek extends RecyclerView.Adapter<calendarViewHolder> {

    private final ArrayList<LocalDate> daysOfMonth;
    private final onItemListener onItemListener;

    public calendarViewAdapter(ArrayList<String> daysOfMonth, calendarViewAdapter.onItemListener onItemListener) {
        this.daysOfMonth = daysOfMonth;
        this.onItemListener = onItemListener;
    }

    @NonNull
    @Override
    public calendarViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(parent.getContext());
        View view = inflater.inflate(R.layout.calendar_cell, parent, false);
        ViewGroup.LayoutParams layoutParams = view.getLayoutParams();
        layoutParams.height = (int) (parent.getHeight() * 0.1666666);

        return new calendarViewHolder(view, onItemListener);
    }

    @Override
    public void onBindViewHolder(@NonNull calendarViewHolder holder, int position) {
        holder.dayOfMonth.setText(daysOfMonth.get(position));
    }

    @Override
    public int getItemCount() {
        return daysOfMonth.size();
    }

    public interface onItemListener{
        void onItemClick(int position, String dayText);
    }
}

