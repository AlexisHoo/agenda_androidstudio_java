package fr.utt.if26.agenda_copy.viewmodel;

import android.content.Context;
import android.content.DialogInterface;
import android.graphics.Color;
import android.util.Log;
import android.widget.Button;
import android.widget.TextView;

import androidx.activity.result.IntentSenderRequest;
import androidx.appcompat.app.AlertDialog;

import java.io.Console;

import fr.utt.if26.agenda_copy.R;
import fr.utt.if26.agenda_copy.model.EventModel;

public class eventViewModel {
    public static String[] choix_radiobutton = {"Tous les jours", "Heure normale d'Europe centrale", "Couleur par défaut","15"};
    public static String[] choix_couleur = {"#0000FF","#D50000","#0B8043","#E67C73"};
    public static String couleur = "#0000FF";
    public static AlertDialog.Builder afficherDialogConstance(Context context, String[] options, int choix, TextView txt, Button button) {
        AlertDialog.Builder builder = new AlertDialog.Builder(context);

        int index = 0;
        for (int i=0; i<options.length; i++){

            if(options[i] == choix_radiobutton[choix]){

                index = i;
            }
        }
        builder.setSingleChoiceItems(options, index, new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                choix_radiobutton[choix] = options[which];
                txt.setText(options[which]);
                if(choix == 2) {
                    button.setBackgroundColor(Color.parseColor(choix_couleur[which]));
                    couleur = choix_couleur[which];
                }
                dialog.dismiss();
            }
        });

        return builder;
    }


    public static void creerEvent(String titre, String description, Boolean box){

        EventModel eventModel = new EventModel(titre, description, eventViewModel.choix_radiobutton[0], eventViewModel.choix_radiobutton[1], box, new Integer(eventViewModel.choix_radiobutton[3].substring(0,2)), eventViewModel.couleur);

    }
}
