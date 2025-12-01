package com.example.app_listgridspinnerr;

import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;
import androidx.appcompat.app.AppCompatActivity;
import com.google.android.material.appbar.CollapsingToolbarLayout;
import com.google.android.material.button.MaterialButton;
import com.google.android.material.floatingactionbutton.FloatingActionButton;

public class CityDetailActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_city_detail);

        // Récupérer les vues
        ImageView cityImageView = findViewById(R.id.cityImageView);
        TextView cityNameTextView = findViewById(R.id.cityNameTextView);
        TextView cityDescriptionTextView = findViewById(R.id.cityDescriptionTextView);
        MaterialButton backButton = findViewById(R.id.backButton);
        FloatingActionButton fabBack = findViewById(R.id.fabBack);
        CollapsingToolbarLayout collapsingToolbar = findViewById(R.id.collapsingToolbar);

        // Récupérer les données de la ville
        City city = (City) getIntent().getSerializableExtra("city");

        if (city != null) {
            cityImageView.setImageResource(city.getImageResourceId());
            cityNameTextView.setText(city.getName());
            cityDescriptionTextView.setText(city.getDescription());
            collapsingToolbar.setTitle(city.getName());
        }

        // Gérer le clic sur le bouton retour
        backButton.setOnClickListener(v -> finish());

        // Gérer le clic sur le bouton flottant
        fabBack.setOnClickListener(v -> finish());
    }
}
