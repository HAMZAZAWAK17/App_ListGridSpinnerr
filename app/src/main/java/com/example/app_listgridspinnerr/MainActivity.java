package com.example.app_listgridspinnerr;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;

import androidx.activity.EdgeToEdge;
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

        listButton.setOnClickListener(v -> openScreen(ListActivity.class));
        spinnerButton.setOnClickListener(v -> openScreen(SpinnerActivity.class));
        gridButton.setOnClickListener(v -> openScreen(GridActivity.class));
        imageListButton.setOnClickListener(v -> openScreen(ImageListActivity.class));
    }

    private void openScreen(Class<?> destination) {
        Intent intent = new Intent(this, destination);
        startActivity(intent);
    }
}