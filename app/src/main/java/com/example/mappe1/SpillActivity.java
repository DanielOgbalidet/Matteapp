package com.example.mappe1;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.res.Configuration;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class SpillActivity extends AppCompatActivity {

    TextView regnestykke;
    EditText input;
    Button knappEn;
    Button knappTo;
    Button knappTre;
    Button knappFire;
    Button knappFem;
    Button knappSeks;
    Button knappSyv;
    Button knappAtte;
    Button knappNi;
    Button knappNull;
    Button sjekkSvar;
    Button slett;
    int randomIndex;
    int counter = 1;
    List<Integer> randomList = new ArrayList<>();

    //Starter spill
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.spill);
        regnestykke = findViewById(R.id.visRegnestykke);
        input = findViewById(R.id.Input);
        knappEn = findViewById(R.id.knapp_en);
        knappTo = findViewById(R.id.knapp_to);
        knappTre = findViewById(R.id.knapp_tre);
        knappFire = findViewById(R.id.knapp_fire);
        knappFem = findViewById(R.id.knapp_fem);
        knappSeks = findViewById(R.id.knapp_seks);
        knappSyv = findViewById(R.id.knapp_syv);
        knappAtte = findViewById(R.id.knapp_atte);
        knappNi = findViewById(R.id.knapp_ni);
        knappNull = findViewById(R.id.knapp_null);
        sjekkSvar = findViewById(R.id.knapp_sjekk_svar);
        slett = findViewById(R.id.knapp_slett);

        //Henter arrays fra xml. Henter regnestykker og svar
        String[] regnestykkerArray = getResources().getStringArray(R.array.regnestykker);
        String[] svarArray = getResources().getStringArray(R.array.svar);

        //Finner random rengestykke
        randomIndex = new Random().nextInt(regnestykkerArray.length);
        String randomRegnestykke = regnestykkerArray[randomIndex];
        regnestykke.setText(randomRegnestykke);

        //Legger til liste slik at samme regnestykke ikke kommer opp igjen
        randomList.add(randomIndex);

        //Henter antall spørsmål fra preferanser
        SharedPreferences settings = getSharedPreferences("mysettings", Context.MODE_PRIVATE);
        int antallSporsmal = settings.getInt("antallSporsmal", 5);

        knappEn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                input.append("1");
            }
        });
        knappTo.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                input.append("2");
            }
        });
        knappTre.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                input.append("3");
            }
        });
        knappFire.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                input.append("4");
            }
        });
        knappFem.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                input.append("5");
            }
        });
        knappSeks.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                input.append("6");
            }
        });
        knappSyv.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                input.append("7");
            }
        });
        knappAtte.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                input.append("8");
            }
        });
        knappNi.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                input.append("9");
            }
        });
        knappNull.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                input.append("0");
            }
        });
        slett.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                input.setText("");
            }
        });
        //Sjekker om svaret til bruker er riktig
        sjekkSvar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //Stopper etter alle spørsmål er vist
                if (counter >= antallSporsmal) {
                    openDialogFerdig();
                }
                String svarString = input.getText().toString();
                int svarBruker = 0;
                if (!svarString.equals("")) {
                    svarBruker = Integer.parseInt(svarString);
                }
                int svar = Integer.parseInt(svarArray[randomIndex]);
                //Hvis bruker har rett
                if (svarBruker == svar) {
                    openDialogRiktig();
                    randomIndex = new Random().nextInt(regnestykkerArray.length);
                    while (randomList.contains(randomIndex)) {
                        randomIndex = new Random().nextInt(regnestykkerArray.length);
                    }
                    randomList.add(randomIndex);
                    String randomRegnestykke = regnestykkerArray[randomIndex];
                    regnestykke.setText(randomRegnestykke);
                    counter++;
                //Hvis bruker har feil
                } else {
                    openDialogFeil();
                }
                input.setText("");
            }
        });
    }

    //Når tilbakeknapp er trykket på
    @Override
    public void onBackPressed() {
        openDialogAvslutt();
    }

    //Viser dialogboks for riktig svar
    public void openDialogRiktig() {
        RiktigDialog riktigDialog = new RiktigDialog();
        riktigDialog.show(getSupportFragmentManager(), "Riktig");
    }

    //Viser dialogboks for feil svar
    public void openDialogFeil() {
        FeilDialog feilDialog = new FeilDialog();
        feilDialog.show(getSupportFragmentManager(), "Feil");
    }

    //Viser dialogboks når spillet er over
    public void openDialogFerdig() {
        FerdigDialog ferdigDialog = new FerdigDialog();
        ferdigDialog.show(getSupportFragmentManager(), "Ferdig");
    }

    //Viser dialogboks når bruker avslutter
    public void openDialogAvslutt() {
        AvsluttDialog avsluttDialog = new AvsluttDialog();
        avsluttDialog.show(getSupportFragmentManager(), "Avslutt");
    }
}
