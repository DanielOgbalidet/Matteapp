package com.example.mappe1;

import android.app.AlertDialog;
import android.app.Dialog;
import android.content.DialogInterface;
import android.os.Bundle;

import androidx.appcompat.app.AppCompatDialog;
import androidx.fragment.app.DialogFragment;

import java.util.Random;

public class RiktigDialog extends DialogFragment {
    @Override
    public Dialog onCreateDialog(Bundle savedInstanceState) {

        //Henter gode tilbakemeldinger fra xml
        String[] riktigTilbake = getResources().getStringArray(R.array.tilbakemeldingRiktig);
        int randomIndex = new Random().nextInt(riktigTilbake.length);
        String message = riktigTilbake[randomIndex];

        AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());
        builder.setTitle(getResources().getString(R.string.riktigDialogTittel))
                .setMessage(message)
                .setPositiveButton(getResources().getString(R.string.riktigDialogPositive), new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {

                    }
                });
        return builder.create();
    }
}
