package fr.utt.if26.agenda_copy.view;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;

import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.TextView;
import android.widget.Toast;

import java.io.Serializable;
import java.util.List;

import fr.utt.if26.agenda_copy.R;
import fr.utt.if26.agenda_copy.model.EventModel;
import fr.utt.if26.agenda_copy.viewmodel.AffichageEventVM;
import fr.utt.if26.agenda_copy.viewmodel.CalendarUtils;
import fr.utt.if26.agenda_copy.viewmodel.eventViewModel;

public class AffichaeEvent extends AppCompatActivity {

    TextView titre, description;
    Button couleur_button;
    ImageButton exit, supprimer, modifier;
    TextView notification;
    EventModel event;

    private AffichageEventVM affichageEventVM;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_affichae_event);

        initWidgets();



        //ROOM
        affichageEventVM = new ViewModelProvider(this).get(AffichageEventVM.class);

        Intent intent = getIntent();
        if (intent != null && intent.hasExtra("EXTRA_KEY")) {
            event = (EventModel) intent.getSerializableExtra("EXTRA_KEY");
            setAffichage(event);

            //Toast.makeText(this, "Valeur extra : " + valeurExtra, Toast.LENGTH_SHORT).show();
        } else {
            Toast.makeText(this, "Aucune valeur extra trouvée", Toast.LENGTH_SHORT).show();
        }

        affichageEventVM.getEventLive(event.getAnnee(), event.getMois(), event.getJour()).observe(this, new Observer<List<EventModel>>(){
            @Override
            public void onChanged(@NonNull List<EventModel> events){
                setAffichage(event);
            }

        });

        supprimer.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {

                //supprimer de la base de données
                affichageEventVM.delete(event);
                finish();
            }
        });

        modifier.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Log.d("AIE", "On APPUIE SUR MODIFIER");
                Intent intent = new Intent(AffichaeEvent.this, Event.class);
                intent.putExtra("event", (Serializable) event);
                startActivityForResult(intent, 2);
            }
        });


        exit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                //supprimer de la base de données
                finish();
            }
        });


    }
    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if (data != null) {
            String titre = data.getStringExtra("Titre");
            if (titre.equals("")) {
                event.setTitre("Sans titre");
            } else {
                event.setTitre(data.getStringExtra("Titre"));
            }
            event.setDescription(data.getStringExtra("Description"));
            event.setConstance(data.getStringExtra("constance"));
            event.setHeure(data.getStringExtra("heure"));
            event.setAllday(data.getBooleanExtra("allday", false));
            event.setNotification(data.getIntExtra("notification", 15));
            event.setCouleur(data.getStringExtra("couleur"));

            affichageEventVM.update(event);
            Log.d("Modifer", "EVENT MODIFIE");
        }
    }

    private void setAffichage(EventModel event) {

        titre.setText(event.getTitre());
        description.setText(event.getDescription());
        int couleur = Color.parseColor(event.getCouleur());
        couleur_button.setBackgroundColor(couleur);
        if(event.getNotification() == 15 && event.getNotification() == 15){

            notification.setText(String.valueOf(event.getNotification()) + " minutes avant");
        }
        else if(event.getNotification() == 1){
            notification.setText(String.valueOf(event.getNotification()) + " heure avant");
        }
        else{
            notification.setText(String.valueOf(event.getNotification()) + " heures avant");
        }
    }

    private void initWidgets() {

        titre = findViewById(R.id.titre);
        couleur_button = findViewById(R.id.couleur_button);
        notification = findViewById(R.id.notification);
        description = findViewById(R.id.description);
        exit = findViewById(R.id.exit);
        modifier = findViewById(R.id.modifier);
        supprimer = findViewById(R.id.supprimer);
    }
}