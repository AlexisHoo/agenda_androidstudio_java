package fr.utt.if26.agenda_copy.viewmodel;



import android.graphics.Color;
import android.graphics.drawable.GradientDrawable;
import android.graphics.drawable.RotateDrawable;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.lifecycle.ViewModelProvider;
import androidx.lifecycle.ViewModelStoreOwner;
import androidx.recyclerview.widget.RecyclerView;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import fr.utt.if26.agenda_copy.R;
import fr.utt.if26.agenda_copy.model.DayModel;
import fr.utt.if26.agenda_copy.model.EventModel;
import fr.utt.if26.agenda_copy.viewmodel.CalendarUtils;
import fr.utt.if26.agenda_copy.viewmodel.calendarViewHolder;

public class calendarViewAdapter extends RecyclerView.Adapter<calendarViewHolder> {
    private eventViewModel eventVM;

    //private final ArrayList<LocalDate> days;

    private final ArrayList<DayModel> dayEventList;
    private final onItemListener onItemListener;

    public calendarViewAdapter(ArrayList<DayModel> dayEventList, onItemListener onItemListener) {
        this.dayEventList = dayEventList;
        this.onItemListener = onItemListener;
    }

    @NonNull
    @Override
    public calendarViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(parent.getContext());
        View view = inflater.inflate(R.layout.calendar_cell, parent, false);
        ViewGroup.LayoutParams layoutParams = view.getLayoutParams();

        if(dayEventList.size() > 15)
            layoutParams.height = (int) (parent.getHeight() * 0.2);
        else
            layoutParams.height = (int) parent.getHeight();

        return new calendarViewHolder(view, onItemListener, dayEventList);
    }

    @Override
    public void onBindViewHolder(@NonNull calendarViewHolder holder, int position) {
        final DayModel day = dayEventList.get(position);

        if(day.getDate() == null)
            holder.dayOfMonth.setText("");
        else{

            holder.dayOfMonth.setText(String.valueOf(day.getDate().getDayOfMonth()));
            int couleurInt = Color.parseColor(day.getCouleur());
            holder.event_text.setText(day.getTitle());
            holder.event_text.setTextColor(Color.WHITE);
            
            if(!day.getTitle().equals("")){

                GradientDrawable border = new GradientDrawable();
                border.setColor(couleurInt);
                border.setStroke(2, Color.BLACK);
                border.setCornerRadius(10);

                holder.event_text.setBackground(border);
                holder.event_text.setWidth(120);
                holder.event_text.setHeight(40);

                if(contientChiffres(day.getTitle())){
                    holder.number_event.setWidth(120);
                    holder.number_event.setHeight(40);
                    holder.number_event.setBackgroundColor(couleurInt);
                    holder.number_event.setText( day.getTitle().substring(day.getTitle().length() - 4) );
                    holder.number_event.setBackground(border);
                    Log.d("HHHHHHHHHH", "CONTIENT CHIFFRES");
                }
            }



            if(day.getDate().equals(CalendarUtils.selectedDate)){

                int couleurFond = Color.parseColor("#B9D5E2");
                holder.parentView.setBackgroundColor(couleurFond);
            }
        }

    }


    @Override
    public int getItemCount() {
        return dayEventList.size();
    }

    private static boolean contientChiffres(String chaine) {
        Pattern pattern = Pattern.compile(".*\\d.*");
        Matcher matcher = pattern.matcher(chaine);
        return matcher.find();
    }

    public interface onItemListener{
        void onItemClick(int position, DayModel day);
    }
}

