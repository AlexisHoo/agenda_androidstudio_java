package fr.utt.if26.agenda_copy.view;

import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.ViewModelProvider;

import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.TextView;
import android.widget.Toast;

import fr.utt.if26.agenda_copy.R;
import fr.utt.if26.agenda_copy.model.EventModel;
import fr.utt.if26.agenda_copy.viewmodel.AffichageEventVM;
import fr.utt.if26.agenda_copy.viewmodel.eventViewModel;

public class AffichaeEvent extends AppCompatActivity {

    EditText titre, description;
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

                Intent intent = new Intent(AffichaeEvent.this, Event.class);
                startActivity(intent);
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

    private void setAffichage(EventModel event) {

        titre.setText(event.getTitre());
        description.setText(event.getDescription());
        int couleur = Color.parseColor(event.getCouleur());
        couleur_button.setBackgroundColor(couleur);

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