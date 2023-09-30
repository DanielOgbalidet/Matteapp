package com.example.mappe1;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class MainActivity extends AppCompatActivity {
    Button startSpill, omSpill, preferanser;

    //Starter aktivitet
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        startSpill = findViewById(R.id.StartSpill);
        omSpill = findViewById(R.id.OmSpill);
        preferanser = findViewById(R.id.Preferanser);

        //Starter spill
        startSpill.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getApplicationContext(), SpillActivity.class);
                startActivity(intent);
            }
        });

        //Viser preferanser
        preferanser.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getApplicationContext(), PreferanserActivity.class);
                startActivity(intent);
            }
        });

        //Viser om spill
        omSpill.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getApplicationContext(), OmSpillActivity.class);
                startActivity(intent);
            }
        });
    }
}