package com.example.app_listgridspinnerr;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.DrawableRes;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.content.ContextCompat;

import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.BitmapDescriptorFactory;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.LatLngBounds;
import com.google.android.gms.maps.model.MarkerOptions;

import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.drawable.Drawable;

/**
 * Displays four Moroccan cities on a Google Map with custom markers.
 */
public class MapActivity extends AppCompatActivity implements OnMapReadyCallback {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_map);

        SupportMapFragment mapFragment =
                (SupportMapFragment) getSupportFragmentManager().findFragmentById(R.id.map_fragment);
        if (mapFragment != null) {
            mapFragment.getMapAsync(this);
        }
    }

    @Override
    public void onMapReady(@NonNull GoogleMap googleMap) {
        // Four Moroccan cities with coordinates
        addCityMarker(googleMap, "Casablanca - Mosquée Hassan II", 33.5731, -7.5898, R.drawable.ic_casa_hassan2);
        addCityMarker(googleMap, "Rabat - Tour Hassan", 34.0209, -6.8416, R.drawable.ic_rabat_tour_hassan);
        addCityMarker(googleMap, "Marrakech - Koutoubia", 31.6295, -7.9811, R.drawable.ic_marrakech_koutoubia);
        addCityMarker(googleMap, "Tanger - Kasbah", 35.7595, -5.8340, R.drawable.ic_tanger_kasbah);

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
    }

    private void addCityMarker(GoogleMap map, String title, double lat, double lng, @DrawableRes int drawableId) {
        LatLng position = new LatLng(lat, lng);
        map.addMarker(new MarkerOptions()
                .position(position)
                .title(title)
                .icon(BitmapDescriptorFactory.fromBitmap(getBitmapFromVector(drawableId))));
    }

    private Bitmap getBitmapFromVector(@DrawableRes int drawableId) {
        Drawable drawable = ContextCompat.getDrawable(this, drawableId);
        if (drawable == null) return Bitmap.createBitmap(1, 1, Bitmap.Config.ARGB_8888);

        int width = drawable.getIntrinsicWidth() > 0 ? drawable.getIntrinsicWidth() : 96;
        int height = drawable.getIntrinsicHeight() > 0 ? drawable.getIntrinsicHeight() : 96;

        Bitmap bitmap = Bitmap.createBitmap(width, height, Bitmap.Config.ARGB_8888);
        Canvas canvas = new Canvas(bitmap);
        drawable.setBounds(0, 0, canvas.getWidth(), canvas.getHeight());
        drawable.draw(canvas);
        return bitmap;
    }
}

