package com.example.app_listgridspinnerr;

import android.content.Intent;
import android.os.Bundle;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

public class ListActivity extends AppCompatActivity {

    private final String[] villes = {
            "tanger", "asila", "laaraych",
            "chefchaouen", "fes", "marakech", "asfi",
            "sidi benour", "el jadida", "casablanca",
            "el hajeb", "agadir", "essaouira"
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_list);

        ListView listView = findViewById(R.id.list);
        ArrayAdapter<String> adapter = new ArrayAdapter<>(
                this,
                android.R.layout.simple_list_item_1,
                villes
        );
        listView.setAdapter(adapter);

        listView.setOnItemClickListener(this::onCityClicked);
    }

    private void onCityClicked(AdapterView<?> parent, android.view.View view, int position, long id) {
        String city = ((TextView) view).getText().toString();
        Intent intent = new Intent(this, CityDetailActivity.class);
        intent.putExtra("CITY_NAME", city);
        startActivity(intent);
    }
}

