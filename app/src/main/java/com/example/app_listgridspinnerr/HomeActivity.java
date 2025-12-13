package com.example.app_listgridspinnerr;

import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.Button;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

public class HomeActivity extends AppCompatActivity {

    private Button btnList, btnSpin, btnGrid, btnImageList, btnDrawer;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_home);

        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.homeContent), (v, insets) -> {
            v.setPadding(
                    insets.getInsets(WindowInsetsCompat.Type.systemBars()).left,
                    insets.getInsets(WindowInsetsCompat.Type.systemBars()).top,
                    insets.getInsets(WindowInsetsCompat.Type.systemBars()).right,
                    insets.getInsets(WindowInsetsCompat.Type.systemBars()).bottom);
            return insets;
        });

        // Initialize buttons
        btnList = findViewById(R.id.btnList);
        btnSpin = findViewById(R.id.btnSpin);
        btnGrid = findViewById(R.id.btnGrid);
        btnImageList = findViewById(R.id.btnImageList);
        btnDrawer = findViewById(R.id.btnDrawer);

        // Setup animations
        setupAnimations();

        // Setup button click listeners
        setupButtonListeners(btnList, ListActivity.class);
        setupButtonListeners(btnSpin, SpinnerActivity.class);
        setupButtonListeners(btnGrid, GridActivity.class);
        setupButtonListeners(btnImageList, ImageListActivity.class);
        setupButtonListeners(btnDrawer, MainDrawerActivity.class);
    }

    private void setupAnimations() {
        Animation slideInLeft = AnimationUtils.loadAnimation(this, R.anim.slide_in_left);
        Animation slideInRight = AnimationUtils.loadAnimation(this, R.anim.slide_in_right);

        // Animate buttons alternating
        slideInLeft.setStartOffset(100);
        btnList.startAnimation(slideInLeft);

        Animation btn2 = AnimationUtils.loadAnimation(this, R.anim.slide_in_right);
        btn2.setStartOffset(200);
        btnSpin.startAnimation(btn2);

        Animation btn3 = AnimationUtils.loadAnimation(this, R.anim.slide_in_left);
        btn3.setStartOffset(300);
        btnGrid.startAnimation(btn3);

        Animation btn4 = AnimationUtils.loadAnimation(this, R.anim.slide_in_right);
        btn4.setStartOffset(400);
        btnImageList.startAnimation(btn4);

        Animation btn5 = AnimationUtils.loadAnimation(this, R.anim.slide_in_left);
        btn5.setStartOffset(500);
        btnDrawer.startAnimation(btn5);
    }

    private void setupButtonListeners(Button button, Class<?> destination) {
        button.setOnClickListener(v -> {
            Animation scaleDown = AnimationUtils.loadAnimation(this, R.anim.button_scale_down);
            v.startAnimation(scaleDown);

            v.postDelayed(() -> {
                Animation scaleUp = AnimationUtils.loadAnimation(this, R.anim.button_scale_up);
                v.startAnimation(scaleUp);
                openScreen(destination);
            }, 150);
        });
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_test, menu);
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        int id = item.getItemId();

        if (id == R.id.menu_contact) {
            openScreen(ContactActivity.class);
            Toast.makeText(this, "Menu Contact cliqué", Toast.LENGTH_SHORT).show();
            return true;
        }

        if (id == R.id.menu_about) {
            openScreen(AboutActivity.class);
            Toast.makeText(this, "Menu About cliqué", Toast.LENGTH_SHORT).show();
            return true;
        }

        if (id == R.id.menu_map) {
            openScreen(MapActivity.class);
            Toast.makeText(this, "Menu Map cliqué", Toast.LENGTH_SHORT).show();
            return true;
        }

        if (id == R.id.menu_quitter) {
            finish();
            Toast.makeText(this, "Application fermée", Toast.LENGTH_SHORT).show();
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    private void openScreen(Class<?> destination) {
        Intent intent = new Intent(this, destination);
        startActivity(intent);
        overridePendingTransition(android.R.anim.fade_in, android.R.anim.fade_out);
    }

    @Override
    public void onBackPressed() {
        // Prevent going back to splash screen
        Intent intent = new Intent(Intent.ACTION_MAIN);
        intent.addCategory(Intent.CATEGORY_HOME);
        intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
        startActivity(intent);
    }
}
