package fr.utt.if26.agenda_copy.viewmodel;

import android.view.View;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.time.LocalDate;
import java.util.ArrayList;

import fr.utt.if26.agenda_copy.R;
import fr.utt.if26.agenda_copy.model.DayModel;

public class calendarViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener{

    public final TextView dayOfMonth, event_text;
    //private final ArrayList<LocalDate> days;
    public final View parentView;
    private final calendarViewAdapter.onItemListener onItemListener;

    private final ArrayList<DayModel> dayEventList;

    public calendarViewHolder(@NonNull View itemView, calendarViewAdapter.onItemListener onItemListener, ArrayList<DayModel> dayEventList) {
        super(itemView);
        dayOfMonth = itemView.findViewById(R.id.cellDayText);
        event_text = itemView.findViewById(R.id.first_event);
        parentView = itemView.findViewById(R.id.parentView);
        this.onItemListener = onItemListener;
        itemView.setOnClickListener(this);
        this.dayEventList = dayEventList;
    }

    @Override
    public void onClick(View view) {
        onItemListener.onItemClick(getAdapterPosition(), dayEventList.get(getAdapterPosition()) );
    }
}
