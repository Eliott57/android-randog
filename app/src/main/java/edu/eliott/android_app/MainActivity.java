package edu.eliott.android_app;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        getSupportActionBar().setTitle("RanDog");

        Button monBouton = findViewById(R.id.button8);

        monBouton.setOnClickListener(view ->
                startActivity(new Intent(this, ResultatImage.class)));

    }
}