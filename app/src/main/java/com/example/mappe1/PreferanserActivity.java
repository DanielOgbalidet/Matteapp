package com.example.mappe1;

import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Spinner;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.app.AppCompatDelegate;
import androidx.core.os.LocaleListCompat;

public class PreferanserActivity extends AppCompatActivity {
    int antallSporsmal;
    String sprok;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.preferanser);

        //Lager nedtrekksmenyer
        Spinner spinnerSporsmal = findViewById(R.id.antallSporsmalSpinner);
        ArrayAdapter<CharSequence> adapterSporsmal = ArrayAdapter.createFromResource(this, R.array.antallSporsmalArray, android.R.layout.simple_spinner_item);
        adapterSporsmal.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinnerSporsmal.setAdapter(adapterSporsmal);

        Spinner spinnerSprok = findViewById(R.id.SprokSpinner);
        ArrayAdapter<CharSequence> adapterSprok = ArrayAdapter.createFromResource(this, R.array.sprokArray, android.R.layout.simple_spinner_item);
        adapterSprok.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinnerSprok.setAdapter(adapterSprok);
        SharedPreferences settings = getSharedPreferences("mysettings", Context.MODE_PRIVATE);
        //Slik at språk ikke byttes tilbake når man åpner preferanser
        spinnerSprok.setSelection(settings.getInt("spinnerState", 0));

        spinnerSporsmal.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            //Når noe er valgt i menyene vil det bli lagret i sharedPreferences
            public void onItemSelected(AdapterView<?> parent, View view, int pos, long id) {
                Object item = parent.getItemAtPosition(pos);
                antallSporsmal = Integer.parseInt(String.valueOf(item));

                SharedPreferences settings = getApplicationContext().getSharedPreferences("mysettings", Context.MODE_PRIVATE);
                SharedPreferences.Editor editor = settings.edit();
                editor.putInt("antallSporsmal", antallSporsmal);
                editor.apply();
            }
            public void onNothingSelected(AdapterView<?> parent) {
            }
        });

        spinnerSprok.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            public void onItemSelected(AdapterView<?> parent, View view, int pos, long id) {
                Object item = parent.getItemAtPosition(pos);
                sprok = String.valueOf(item);
                LocaleListCompat appLocale;
                if (sprok.equals("Tysk") || sprok.equals("Deutsch")) {
                    appLocale = LocaleListCompat.forLanguageTags("de-DE");
                } else {
                    appLocale = LocaleListCompat.forLanguageTags("no-NO");
                }
                AppCompatDelegate.setApplicationLocales(appLocale);

                SharedPreferences settings = getApplicationContext().getSharedPreferences("mysettings", Context.MODE_PRIVATE);
                SharedPreferences.Editor editor = settings.edit();
                editor.putInt("spinnerState", spinnerSprok.getSelectedItemPosition());
                editor.apply();
            }
            public void onNothingSelected(AdapterView<?> parent) {
            }
        });
    }
}
