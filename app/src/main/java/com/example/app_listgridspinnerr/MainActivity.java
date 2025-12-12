package com.example.app_listgridspinnerr;

import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.Button;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_main);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            v.setPadding(
                    insets.getInsets(WindowInsetsCompat.Type.systemBars()).left,
                    insets.getInsets(WindowInsetsCompat.Type.systemBars()).top,
                    insets.getInsets(WindowInsetsCompat.Type.systemBars()).right,
                    insets.getInsets(WindowInsetsCompat.Type.systemBars()).bottom
            );
            return insets;
        });

        Button listButton = findViewById(R.id.btnList);
        Button spinnerButton = findViewById(R.id.btnSpin);
        Button gridButton = findViewById(R.id.btnGrid);
        Button imageListButton = findViewById(R.id.btnImageList);
        Button drawerButton = findViewById(R.id.btnDrawer);

        listButton.setOnClickListener(v -> openScreen(ListActivity.class));
        spinnerButton.setOnClickListener(v -> openScreen(SpinnerActivity.class));
        gridButton.setOnClickListener(v -> openScreen(GridActivity.class));
        imageListButton.setOnClickListener(v -> openScreen(ImageListActivity.class));
        drawerButton.setOnClickListener(v -> openScreen(MainDrawerActivity.class));
    }

    // ÉTAPE 1 : Créer le menu (inflater le fichier menu_test.xml)
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Charger le fichier menu dans l'activité
        getMenuInflater().inflate(R.menu.menu_test, menu);
        return super.onCreateOptionsMenu(menu);
    }

    // ÉTAPE 2 : Gérer les clics sur les éléments du menu
    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        // Récupérer l'ID de l'élément cliqué
        int id = item.getItemId();

        // Menu Contact
        if (id == R.id.menu_contact) {
            // Naviguer vers l'activité Contact
            openScreen(ContactActivity.class);
            Toast.makeText(this, "Menu Contact cliqué", Toast.LENGTH_SHORT).show();
            return true;
        }

        // Menu About
        if (id == R.id.menu_about) {
            // Naviguer vers l'activité About
            openScreen(AboutActivity.class);
            Toast.makeText(this, "Menu About cliqué", Toast.LENGTH_SHORT).show();
            return true;
        }

        // Menu Map
        if (id == R.id.menu_map) {
            openScreen(MapActivity.class);
            Toast.makeText(this, "Menu Map cliqué", Toast.LENGTH_SHORT).show();
            return true;
        }

        // Menu Quitter
        if (id == R.id.menu_quitter) {
            // Fermer l'application
            finish();
            Toast.makeText(this, "Application fermée", Toast.LENGTH_SHORT).show();
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    private void openScreen(Class<?> destination) {
        Intent intent = new Intent(this, destination);
        startActivity(intent);
    }
}
