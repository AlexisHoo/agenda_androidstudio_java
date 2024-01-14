package fr.utt.if26.agenda_copy.view;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;
import androidx.lifecycle.ViewModelProviderGetKt;
import androidx.room.Room;
import androidx.room.RoomDatabase;
import androidx.sqlite.db.SupportSQLiteDatabase;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import java.util.List;

import fr.utt.if26.agenda_copy.R;
import fr.utt.if26.agenda_copy.model.EventModel;
import fr.utt.if26.agenda_copy.room.AppDatabase;
import fr.utt.if26.agenda_copy.viewmodel.eventAddUtils;
import fr.utt.if26.agenda_copy.viewmodel.eventViewModel;

public class Event extends AppCompatActivity {

    eventAddUtils eventUtils;
    private CheckBox checkBox;
    private LinearLayout constance_layout, heure_layout, notification_layout, couleur_layout, description_layout;
    private TextView txt_constance, txt_heure, txt_couleur, txt_notification;
    private Button couleur_button, enregistrer;
    private ImageButton exit;
    EditText titre, description;

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
                    AlertDialog.Builder dialog = eventUtils.afficherDialogConstance(Event.this, options, 0, txt_heure, couleur_button);
                    dialog.show();

                } else if (view == constance_layout) {
                    final String[] options = {"Une seule fois", "Tous les jours", "Toutes les semaines", "Tous les mois", "Tous les ans"};
                    AlertDialog.Builder dialog = eventUtils.afficherDialogConstance(Event.this, options, 1, txt_constance, couleur_button);
                    dialog.show();

                } else if (view == couleur_layout) {
                    final String[] options = {"Couleur par défaut", "Tomate", "Basilic", "Flamant Rose"};
                    AlertDialog.Builder dialog = eventUtils.afficherDialogConstance(Event.this, options, 2, txt_couleur, couleur_button);
                    dialog.show();

                } else if (view == notification_layout) {
                final String[] options = {"15 minutes avant", "30 minutes avant", "1 heure avant", "24 heures avant"};
                AlertDialog.Builder dialog = eventUtils.afficherDialogConstance(Event.this, options, 3, txt_notification, couleur_button);
                dialog.show();

                } else if (view == enregistrer) {


                    //startActivity(new Intent( getApplicationContext(), MainActivity.class));

                    Intent data = new Intent();
                    data.putExtra("Titre", titre.getText().toString());
                    data.putExtra("Description", description.getText().toString());
                    data.putExtra("constance",eventUtils.choix_radiobutton[0]);
                    data.putExtra("heure", eventUtils.choix_radiobutton[1]);
                    data.putExtra("allday", checkBox.isChecked());
                    data.putExtra("notification", new Integer(eventUtils.choix_radiobutton[3].substring(0,2)));
                    data.putExtra("couleur", eventUtils.couleur);

                    setResult(RESULT_OK, data);
                    finish();

                } else if (view == exit) {

                    //startActivity(new Intent( getApplicationContext(), MainActivity.class));
                    finish();
                }
            }
        };

        heure_layout.setOnClickListener(onClickListener);
        constance_layout.setOnClickListener(onClickListener);
        couleur_layout.setOnClickListener(onClickListener);
        notification_layout.setOnClickListener(onClickListener);
        enregistrer.setOnClickListener(onClickListener);
        exit.setOnClickListener(onClickListener);
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
        couleur_button = findViewById(R.id.couleur_button);
        enregistrer = findViewById(R.id.enregistrer);
        titre = findViewById(R.id.titre);
        description = findViewById(R.id.description);
        exit = findViewById(R.id.exit);

    }
}