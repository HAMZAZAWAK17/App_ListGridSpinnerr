package com.example.app_listgridspinnerr;

import android.content.Intent;
import android.os.Bundle;
import android.view.MenuItem;
import android.widget.Toast;
import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBarDrawerToggle;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.view.GravityCompat;
import androidx.drawerlayout.widget.DrawerLayout;
import com.google.android.material.appbar.MaterialToolbar;
import com.google.android.material.navigation.NavigationView;

public class MainDrawerActivity extends AppCompatActivity {

    // Variables pour le Drawer
    private DrawerLayout drawerLayout;
    private NavigationView navigationView;
    private MaterialToolbar toolbar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main_drawer);

        // Initialiser les vues
        drawerLayout = findViewById(R.id.drawer_layout);
        navigationView = findViewById(R.id.nav_view);
        toolbar = findViewById(R.id.toolbar);

        // Configurer la toolbar
        setSupportActionBar(toolbar);

        // Créer le toggle pour ouvrir/fermer le drawer
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                this,
                drawerLayout,
                toolbar,
                R.string.app_name,  // Description pour l'ouverture
                R.string.app_name   // Description pour la fermeture
        );
        drawerLayout.addDrawerListener(toggle);
        toggle.syncState();

        // Gérer les clics sur les éléments du menu
        navigationView.setNavigationItemSelectedListener(new NavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                int id = item.getItemId();

                // Navigation vers les différentes activités
                if (id == R.id.nav_home) {
                    Toast.makeText(MainDrawerActivity.this, "Accueil", Toast.LENGTH_SHORT).show();
                    drawerLayout.closeDrawer(GravityCompat.START);
                    return true;
                }

                if (id == R.id.nav_villes) {
                    openScreen(ImageListActivity.class);
                    return true;
                }

                if (id == R.id.nav_grid) {
                    openScreen(GridActivity.class);
                    return true;
                }

                if (id == R.id.nav_map) {
                    openScreen(MapActivity.class);
                    return true;
                }

                if (id == R.id.nav_spinner) {
                    openScreen(SpinnerActivity.class);
                    return true;
                }

                if (id == R.id.nav_contact) {
                    openScreen(ContactActivity.class);
                    return true;
                }

                if (id == R.id.nav_about) {
                    openScreen(AboutActivity.class);
                    return true;
                }

                if (id == R.id.nav_quitter) {
                    finish();
                    Toast.makeText(MainDrawerActivity.this, "Application fermée", Toast.LENGTH_SHORT).show();
                    return true;
                }

                return false;
            }
        });
    }

    // Méthode pour ouvrir une nouvelle activité
    private void openScreen(Class<?> destination) {
        Intent intent = new Intent(this, destination);
        startActivity(intent);
        drawerLayout.closeDrawer(GravityCompat.START);
    }

    // Gérer le bouton retour pour fermer le drawer s'il est ouvert
    @Override
    public void onBackPressed() {
        if (drawerLayout.isDrawerOpen(GravityCompat.START)) {
            drawerLayout.closeDrawer(GravityCompat.START);
        } else {
            super.onBackPressed();
        }
    }
}



