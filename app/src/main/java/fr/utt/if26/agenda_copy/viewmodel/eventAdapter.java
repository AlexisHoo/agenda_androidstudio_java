package fr.utt.if26.agenda_copy.viewmodel;

import android.graphics.Color;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageButton;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;
import java.util.List;

import fr.utt.if26.agenda_copy.R;
import fr.utt.if26.agenda_copy.model.EventModel;

public class eventAdapter extends RecyclerView.Adapter<eventAdapter.eventHolder>{

    private List<EventModel> listOfEvents = new ArrayList<>();
    private SelectEvent selectEvent;

    public eventAdapter(SelectEvent selectEvent){
        this.selectEvent = selectEvent;
    }

    @NonNull
    @Override
    public eventHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.event_cell, parent, false);

        return new eventHolder(itemView);
    }

    @Override
    public void onBindViewHolder(@NonNull eventHolder holder, int position) {
        EventModel event = listOfEvents.get(position);
        holder.button.setText(event.getTitre());
        holder.button.setBackgroundColor(Color.parseColor(event.getCouleur()));
        holder.button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                selectEvent.onItemClicked(event);
            }
        });
    }

    @Override
    public int getItemCount() {
        return listOfEvents.size();
    }

    public void setListOfEvents(List<EventModel> listOfEvents){
        this.listOfEvents = listOfEvents;
        notifyDataSetChanged();
    }

    class eventHolder extends RecyclerView.ViewHolder{
        private Button button;

        public eventHolder(@NonNull View itemView) {
            super(itemView);
            button = itemView.findViewById(R.id.button);

        }
    }

}
