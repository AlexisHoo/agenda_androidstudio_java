package fr.utt.if26.agenda_copy.view;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.TextView;

import fr.utt.if26.agenda_copy.R;
import fr.utt.if26.agenda_copy.model.EventModel;
import fr.utt.if26.agenda_copy.viewmodel.eventViewModel;

public class Event extends AppCompatActivity {

    private CheckBox checkBox;
    private LinearLayout constance_layout, heure_layout, notification_layout, couleur_layout, description_layout;
    private TextView txt_constance, txt_heure, txt_couleur, txt_notification;
    private Button couleur_button, enregistrer;
    EditText titre, description;
    public Event() {
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_event);
        initWidgets();

        View.OnClickListener onClickListener = new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                if (view == heure_layout) {
                    final String[] options = {"Heure normale d'Europe centrale", "Heure normale d'Amérique Centrale"};
                    AlertDialog.Builder dialog = eventViewModel.afficherDialogConstance(Event.this, options, 0, txt_heure, couleur_button);
                    dialog.show();

                } else if (view == constance_layout) {
                    final String[] options = {"Une seule fois", "Tous les jours", "Toutes les semaines", "Tous les mois", "Tous les ans"};
                    AlertDialog.Builder dialog = eventViewModel.afficherDialogConstance(Event.this, options, 1, txt_constance, couleur_button);
                    dialog.show();

                } else if (view == couleur_layout) {
                    final String[] options = {"Couleur par défaut", "Tomate", "Basilic", "Flamant Rose"};
                    AlertDialog.Builder dialog = eventViewModel.afficherDialogConstance(Event.this, options, 2, txt_couleur, couleur_button);
                    dialog.show();

                } else if (view == notification_layout) {
                final String[] options = {"15 minutes avant", "30 minutes avant", "1 heure avant", "24 heures avant"};
                AlertDialog.Builder dialog = eventViewModel.afficherDialogConstance(Event.this, options, 3, txt_notification, couleur_button);
                dialog.show();

                } else if (view == enregistrer) {

                    EventModel eventModel = new EventModel(titre.getText().toString(), description.getText().toString(), eventViewModel.choix_radiobutton[0], eventViewModel.choix_radiobutton[1], checkBox.isChecked(), new Integer(eventViewModel.choix_radiobutton[3].substring(0,2)), eventViewModel.couleur);
                }
            }
        };

        heure_layout.setOnClickListener(onClickListener);
        constance_layout.setOnClickListener(onClickListener);
        couleur_layout.setOnClickListener(onClickListener);
        notification_layout.setOnClickListener(onClickListener);
        enregistrer.setOnClickListener(onClickListener);
    }

    private void initWidgets() {

        checkBox = findViewById(R.id.myCheckBox);
        heure_layout = findViewById(R.id.layout_heure);

        constance_layout = findViewById(R.id.layout_constance);
        notification_layout = findViewById(R.id.layout_notification);
        couleur_layout = findViewById(R.id.layout_couleur);
        description_layout = findViewById(R.id.layout_description);

        txt_constance = findViewById(R.id.txt_constance);
        txt_heure = findViewById(R.id.txt_heure);
        txt_couleur = findViewById(R.id.txt_couleur);
        txt_notification = findViewById(R.id.txt_notification);
        enregistrer = findViewById(R.id.couleur_button);
        titre = findViewById(R.id.titre);
        description = findViewById(R.id.description);

    }
}