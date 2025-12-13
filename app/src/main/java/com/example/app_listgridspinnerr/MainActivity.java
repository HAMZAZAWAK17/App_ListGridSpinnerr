package com.example.app_listgridspinnerr;

import android.content.Intent;
import android.media.MediaPlayer;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.Button;
import android.widget.VideoView;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.cardview.widget.CardView;

public class MainActivity extends AppCompatActivity {

    private VideoView backgroundVideo;
    private View logoCard, appTitle, appSubtitle, featuresLayout, swipeIndicator;
    private CardView startButtonCard;
    private Button btnLetsStart;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_main);

        // Initialize views
        backgroundVideo = findViewById(R.id.backgroundVideo);
        logoCard = findViewById(R.id.logoCard);
        appTitle = findViewById(R.id.appTitle);
        appSubtitle = findViewById(R.id.appSubtitle);
        featuresLayout = findViewById(R.id.featuresLayout);
        startButtonCard = findViewById(R.id.startButtonCard);
        swipeIndicator = findViewById(R.id.swipeIndicator);
        btnLetsStart = findViewById(R.id.btnLetsStart);

        // Setup background video
        setupBackgroundVideo();

        // Setup animations
        setupAnimations();

        // Setup button click listener
        btnLetsStart.setOnClickListener(v -> {
            // Scale animation
            Animation scaleDown = AnimationUtils.loadAnimation(this, R.anim.button_scale_down);
            v.startAnimation(scaleDown);

            v.postDelayed(() -> {
                // Navigate to HomeActivity
                Intent intent = new Intent(MainActivity.this, HomeActivity.class);
                startActivity(intent);
                overridePendingTransition(android.R.anim.fade_in, android.R.anim.fade_out);
                finish(); // Close splash screen
            }, 200);
        });
    }

    private void setupBackgroundVideo() {
        try {
            String videoPath = "android.resource://" + getPackageName() + "/" + R.raw.casablanca;
            backgroundVideo.setVideoURI(Uri.parse(videoPath));

            backgroundVideo.setOnPreparedListener(new MediaPlayer.OnPreparedListener() {
                @Override
                public void onPrepared(MediaPlayer mp) {
                    mp.setLooping(true);
                    mp.setVolume(0f, 0f);
                    backgroundVideo.start();
                }
            });

            backgroundVideo.setOnCompletionListener(new MediaPlayer.OnCompletionListener() {
                @Override
                public void onCompletion(MediaPlayer mp) {
                    backgroundVideo.start();
                }
            });

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private void setupAnimations() {
        // Logo zoom in
        Animation zoomIn = AnimationUtils.loadAnimation(this, R.anim.zoom_in);
        logoCard.startAnimation(zoomIn);

        // Title slide down
        Animation titleAnim = AnimationUtils.loadAnimation(this, R.anim.fade_slide_down);
        titleAnim.setStartOffset(400);
        appTitle.startAnimation(titleAnim);

        // Subtitle slide down
        Animation subtitleAnim = AnimationUtils.loadAnimation(this, R.anim.fade_slide_down);
        subtitleAnim.setStartOffset(600);
        appSubtitle.startAnimation(subtitleAnim);

        // Features fade in
        Animation featuresAnim = AnimationUtils.loadAnimation(this, R.anim.zoom_in);
        featuresAnim.setStartOffset(800);
        featuresLayout.startAnimation(featuresAnim);

        // Button slide up
        Animation buttonAnim = AnimationUtils.loadAnimation(this, R.anim.zoom_in);
        buttonAnim.setStartOffset(1000);
        startButtonCard.startAnimation(buttonAnim);

        // Swipe indicator pulse
        Animation pulseAnim = AnimationUtils.loadAnimation(this, R.anim.zoom_in);
        pulseAnim.setStartOffset(1200);
        swipeIndicator.startAnimation(pulseAnim);
    }

    @Override
    protected void onResume() {
        super.onResume();
        if (backgroundVideo != null && !backgroundVideo.isPlaying()) {
            backgroundVideo.start();
        }
    }

    @Override
    protected void onPause() {
        super.onPause();
        if (backgroundVideo != null && backgroundVideo.isPlaying()) {
            backgroundVideo.pause();
        }
    }
}
