package com.example.app_listgridspinnerr;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.PorterDuff;
import android.graphics.PorterDuffXfermode;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.cardview.widget.CardView;
import androidx.viewpager2.widget.ViewPager2;

import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.BitmapDescriptorFactory;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.LatLngBounds;
import com.google.android.gms.maps.model.Marker;
import com.google.android.gms.maps.model.MarkerOptions;

import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Displays four Moroccan cities on a Google Map with circular image markers.
 * Shows detailed city information card with image gallery when marker is
 * clicked.
 */
public class MapActivity extends AppCompatActivity implements OnMapReadyCallback {

    private CardView cityInfoCard;
    private ImageView cityImage;
    private TextView cityTitle;
    private TextView cityDescription;
    private ViewPager2 imageViewPager;
    private ImageButton btnCloseCard;
    private ImageButton btnPrevImage;
    private ImageButton btnNextImage;
    private TextView imageCounter;

    // City data storage
    private Map<String, CityInfo> cityDataMap = new HashMap<>();

    // City information class
    private static class CityInfo {
        String name;
        int imageRes;
        String description;
        List<Integer> galleryImages;

        CityInfo(String name, int imageRes, String description, List<Integer> galleryImages) {
            this.name = name;
            this.imageRes = imageRes;
            this.description = description;
            this.galleryImages = galleryImages;
        }
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_map);

        // Initialize views
        cityInfoCard = findViewById(R.id.cityInfoCardContainer);
        cityImage = cityInfoCard.findViewById(R.id.cityImage);
        cityTitle = cityInfoCard.findViewById(R.id.cityTitle);
        cityDescription = cityInfoCard.findViewById(R.id.cityDescription);
        imageViewPager = cityInfoCard.findViewById(R.id.imageViewPager);
        btnCloseCard = cityInfoCard.findViewById(R.id.btnCloseCard);
        btnPrevImage = cityInfoCard.findViewById(R.id.btnPrevImage);
        btnNextImage = cityInfoCard.findViewById(R.id.btnNextImage);
        imageCounter = cityInfoCard.findViewById(R.id.imageCounter);

        // Initialize city data
        initializeCityData();

        // Close button listener
        btnCloseCard.setOnClickListener(v -> cityInfoCard.setVisibility(View.GONE));

        // Setup map
        SupportMapFragment mapFragment = (SupportMapFragment) getSupportFragmentManager()
                .findFragmentById(R.id.map_fragment);
        if (mapFragment != null) {
            mapFragment.getMapAsync(this);
        }
    }

    private void initializeCityData() {
        // Casablanca gallery - using existing images for now
        List<Integer> casablancaImages = Arrays.asList(
                R.drawable.hassan2,
                R.drawable.hassan2,
                R.drawable.hassan2,
                R.drawable.hassan2,
                R.drawable.hassan2,
                R.drawable.hassan2);

        cityDataMap.put("Casablanca", new CityInfo(
                "Casablanca - Mosquée Hassan II",
                R.drawable.hassan2,
                "La Mosquée Hassan II est l'une des plus grandes mosquées au monde. " +
                        "Située sur la côte atlantique de Casablanca, elle peut accueillir 25 000 fidèles " +
                        "à l'intérieur et 80 000 sur son esplanade. Son minaret de 210 mètres est le plus haut du monde.",
                casablancaImages));

        // Rabat gallery
        List<Integer> rabatImages = Arrays.asList(
                R.drawable.hassanrabat,
                R.drawable.hassanrabat,
                R.drawable.hassanrabat,
                R.drawable.hassanrabat,
                R.drawable.hassanrabat,
                R.drawable.hassanrabat);

        cityDataMap.put("Rabat", new CityInfo(
                "Rabat - Tour Hassan",
                R.drawable.hassanrabat,
                "La Tour Hassan est un minaret d'une mosquée du XIIe siècle à Rabat. " +
                        "Haute de 44 mètres, elle devait atteindre 60 mètres. Le site abrite également " +
                        "le Mausolée Mohammed V, un chef-d'œuvre de l'architecture marocaine moderne.",
                rabatImages));

        // Marrakech gallery
        List<Integer> marrakechImages = Arrays.asList(
                R.drawable.mosquee_koutoubia_marrakech,
                R.drawable.mosquee_koutoubia_marrakech,
                R.drawable.mosquee_koutoubia_marrakech,
                R.drawable.mosquee_koutoubia_marrakech,
                R.drawable.mosquee_koutoubia_marrakech,
                R.drawable.mosquee_koutoubia_marrakech);

        cityDataMap.put("Marrakech", new CityInfo(
                "Marrakech - Koutoubia",
                R.drawable.mosquee_koutoubia_marrakech,
                "La Koutoubia est le monument le plus célèbre de Marrakech. " +
                        "Son minaret de 77 mètres domine la médina et sert de modèle à la Giralda de Séville. " +
                        "Construite au XIIe siècle, elle est un symbole de l'art almohade.",
                marrakechImages));

        // Tanger gallery
        List<Integer> tangerImages = Arrays.asList(
                R.drawable.tangerkasbah,
                R.drawable.tangerkasbah,
                R.drawable.tangerkasbah,
                R.drawable.tangerkasbah,
                R.drawable.tangerkasbah,
                R.drawable.tangerkasbah);

        cityDataMap.put("Tanger", new CityInfo(
                "Tanger - Kasbah",
                R.drawable.tangerkasbah,
                "La Kasbah de Tanger surplombe le détroit de Gibraltar. " +
                        "Cette forteresse historique offre une vue panoramique sur la mer Méditerranée et l'océan Atlantique. "
                        +
                        "Elle abrite le palais du Sultan et des jardins magnifiques.",
                tangerImages));
    }

    @Override
    public void onMapReady(@NonNull GoogleMap googleMap) {
        // Add city markers with circular images
        addCityMarker(googleMap, "Casablanca", 33.5731, -7.5898, R.drawable.hassan2);
        addCityMarker(googleMap, "Rabat", 34.0209, -6.8416, R.drawable.hassanrabat);
        addCityMarker(googleMap, "Marrakech", 31.6295, -7.9811, R.drawable.mosquee_koutoubia_marrakech);
        addCityMarker(googleMap, "Tanger", 35.7595, -5.8340, R.drawable.tangerkasbah);

        // Fit all markers on screen with padding
        LatLngBounds bounds = new LatLngBounds.Builder()
                .include(new LatLng(33.5731, -7.5898))
                .include(new LatLng(34.0209, -6.8416))
                .include(new LatLng(31.6295, -7.9811))
                .include(new LatLng(35.7595, -5.8340))
                .build();
        googleMap.moveCamera(CameraUpdateFactory.newLatLngZoom(new LatLng(32.8, -6.5), 5.5f));
        googleMap.animateCamera(CameraUpdateFactory.newLatLngBounds(bounds, 180));

        googleMap.getUiSettings().setZoomControlsEnabled(true);
        googleMap.getUiSettings().setMapToolbarEnabled(true);

        // Set marker click listener
        googleMap.setOnMarkerClickListener(marker -> {
            String cityKey = (String) marker.getTag();
            if (cityKey != null && cityDataMap.containsKey(cityKey)) {
                showCityInfo(cityDataMap.get(cityKey));
            }
            return true;
        });

        // Hide card when map is clicked
        googleMap.setOnMapClickListener(latLng -> cityInfoCard.setVisibility(View.GONE));
    }

    private void addCityMarker(GoogleMap map, String cityKey, double lat, double lng, int imageRes) {
        LatLng position = new LatLng(lat, lng);

        // Create circular bitmap from image
        Bitmap circularBitmap = getCircularBitmap(imageRes, 120);

        Marker marker = map.addMarker(new MarkerOptions()
                .position(position)
                .title(cityDataMap.get(cityKey).name)
                .icon(BitmapDescriptorFactory.fromBitmap(circularBitmap)));

        // Store city key as tag
        if (marker != null) {
            marker.setTag(cityKey);
        }
    }

    private Bitmap getCircularBitmap(int imageRes, int size) {
        // Load the original bitmap
        Bitmap originalBitmap = BitmapFactory.decodeResource(getResources(), imageRes);

        // Create a scaled bitmap
        Bitmap scaledBitmap = Bitmap.createScaledBitmap(originalBitmap, size, size, false);

        // Create output bitmap
        Bitmap output = Bitmap.createBitmap(size, size, Bitmap.Config.ARGB_8888);
        Canvas canvas = new Canvas(output);

        // Draw white circle background
        Paint paint = new Paint();
        paint.setAntiAlias(true);
        paint.setColor(0xFFFFFFFF);
        canvas.drawCircle(size / 2f, size / 2f, size / 2f, paint);

        // Draw the image in circular shape
        paint.setXfermode(new PorterDuffXfermode(PorterDuff.Mode.SRC_IN));
        canvas.drawBitmap(scaledBitmap, 0, 0, paint);

        // Draw green border
        paint.setXfermode(null);
        paint.setStyle(Paint.Style.STROKE);
        paint.setStrokeWidth(6);
        paint.setColor(0xFF4CAF50);
        canvas.drawCircle(size / 2f, size / 2f, size / 2f - 3, paint);

        return output;
    }

    private void showCityInfo(CityInfo cityInfo) {
        // Set city information
        cityImage.setImageResource(cityInfo.imageRes);
        cityTitle.setText(cityInfo.name);
        cityDescription.setText(cityInfo.description);

        // Setup image gallery
        if (cityInfo.galleryImages != null && !cityInfo.galleryImages.isEmpty()) {
            // Create and set adapter
            ImageGalleryAdapter adapter = new ImageGalleryAdapter(cityInfo.galleryImages);
            imageViewPager.setAdapter(adapter);

            // Update counter
            updateImageCounter(1, cityInfo.galleryImages.size());

            // Setup ViewPager2 page change callback
            imageViewPager.registerOnPageChangeCallback(new ViewPager2.OnPageChangeCallback() {
                @Override
                public void onPageSelected(int position) {
                    super.onPageSelected(position);
                    updateImageCounter(position + 1, cityInfo.galleryImages.size());
                }
            });

            // Navigation buttons
            btnPrevImage.setOnClickListener(v -> {
                int currentItem = imageViewPager.getCurrentItem();
                if (currentItem > 0) {
                    imageViewPager.setCurrentItem(currentItem - 1, true);
                }
            });

            btnNextImage.setOnClickListener(v -> {
                int currentItem = imageViewPager.getCurrentItem();
                if (currentItem < cityInfo.galleryImages.size() - 1) {
                    imageViewPager.setCurrentItem(currentItem + 1, true);
                }
            });
        }

        // Show the card with animation
        cityInfoCard.setVisibility(View.VISIBLE);
        cityInfoCard.setAlpha(0f);
        cityInfoCard.animate()
                .alpha(1f)
                .setDuration(300)
                .start();
    }

    private void updateImageCounter(int current, int total) {
        imageCounter.setText(current + " / " + total);
    }
}
