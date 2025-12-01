package com.example.app_listgridspinnerr;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import java.util.ArrayList;

public class ImageListActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_image_list);

        // 1. Créer la liste des données
        ArrayList<City> cities = new ArrayList<>();
        cities.add(new City("Tanger", R.drawable.tanger, "Tanger est une ville portuaire marocaine sur le détroit de Gibraltar."));
        cities.add(new City("Asila", R.drawable.asila, "Asilah est une ville fortifiée sur la côte nord-ouest du Maroc."));
        cities.add(new City("Laaraych", R.drawable.laaraych, "Larache est une ville portuaire du nord du Maroc."));
        cities.add(new City("Chefchaouen", R.drawable.chefchaouen_maroc, "Chefchaouen est une ville remarquable par ses bâtiments bleus."));
        cities.add(new City("Fès", R.drawable.fes, "Fès est la capitale culturelle du Maroc."));
        cities.add(new City("Rabat", R.drawable.chefchaouen_maroc, "Rabat, la capitale du Maroc, est située sur la côte atlantique."));
        cities.add(new City("Marrakech", R.drawable.fes, "Marrakech, la ville rouge, est célèbre pour ses souks et ses jardins."));

        // 2. Configurer le RecyclerView
        RecyclerView recyclerView = findViewById(R.id.citiesRecyclerView);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));

        // 3. Créer l'adapter et gérer le clic
        CityRecyclerAdapter adapter = new CityRecyclerAdapter(cities, city -> {
            Intent intent = new Intent(ImageListActivity.this, CityDetailActivity.class);
            intent.putExtra("city", city);
            startActivity(intent);
        });

        recyclerView.setAdapter(adapter);

        // 4. Gérer le clic sur le bouton de retour
        Button backButton = findViewById(R.id.backToMainButton);
        backButton.setOnClickListener(v -> finish());
    }
}



