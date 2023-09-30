package com.example.mappe1;

import android.app.AlertDialog;
import android.app.Dialog;
import android.content.DialogInterface;
import android.os.Bundle;

import androidx.appcompat.app.AppCompatDialog;
import androidx.fragment.app.DialogFragment;

import java.util.Random;

public class FeilDialog extends DialogFragment {
    @Override
    public Dialog onCreateDialog(Bundle savedInstanceState) {

        //Henter tilbakemeldinger fra xml
        String[] feilTilbake = getResources().getStringArray(R.array.tilbakemeldingFeil);
        int randomIndex = new Random().nextInt(feilTilbake.length);
        String message = feilTilbake[randomIndex];

        AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());
        builder.setTitle(getResources().getString(R.string.feilDialogTittel))
                .setMessage(message)
                .setPositiveButton(getResources().getString(R.string.feilDialogPositive), new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {

                    }
                });
        return builder.create();
    }
}