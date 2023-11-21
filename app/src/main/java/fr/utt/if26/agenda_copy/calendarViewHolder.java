package fr.utt.if26.agenda_copy;

import android.view.View;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.time.LocalDate;
import java.util.ArrayList;

public class calendarViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener{

    public final TextView dayOfMonth;
    private final ArrayList<LocalDate> days;
    public final View parentView;
    private final calendarViewAdapter.onItemListener onItemListener;

    public calendarViewHolder(@NonNull View itemView, calendarViewAdapter.onItemListener onItemListener, ArrayList<LocalDate> days) {
        super(itemView);
        dayOfMonth = itemView.findViewById(R.id.cellDayText);
        parentView = itemView.findViewById(R.id.parentView);
        this.onItemListener = onItemListener;
        itemView.setOnClickListener(this);
        this.days = days;
    }

    @Override
    public void onClick(View view) {
        onItemListener.onItemClick(getAdapterPosition(), days.get(getAdapterPosition()) );
    }
}
