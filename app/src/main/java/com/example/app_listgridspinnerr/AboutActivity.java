package com.example.app_listgridspinnerr;

import android.os.Bundle;
import androidx.appcompat.app.AppCompatActivity;
import com.google.android.material.button.MaterialButton;

public class AboutActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_about);

        // Bouton retour
        MaterialButton btnRetour = findViewById(R.id.btnRetourAbout);
        btnRetour.setOnClickListener(v -> finish());
    }
}

