package com.example.app_listgridspinnerr;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;
import java.util.List;

public class CityRecyclerAdapter extends RecyclerView.Adapter<CityRecyclerAdapter.CityViewHolder> {

    private final List<City> cities;
    private final OnCityClickListener listener;

    public interface OnCityClickListener {
        void onCityClick(City city);
    }

    public CityRecyclerAdapter(List<City> cities, OnCityClickListener listener) {
        this.cities = cities;
        this.listener = listener;
    }

    @NonNull
    @Override
    public CityViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.layout_list, parent, false);
        return new CityViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull CityViewHolder holder, int position) {
        City city = cities.get(position);
        holder.bind(city, listener);
    }

    @Override
    public int getItemCount() {
        return cities.size();
    }

    static class CityViewHolder extends RecyclerView.ViewHolder {
        private final ImageView cityImageView;
        private final TextView cityNameTextView;

        public CityViewHolder(@NonNull View itemView) {
            super(itemView);
            cityImageView = itemView.findViewById(R.id.flag);
            cityNameTextView = itemView.findViewById(R.id.txt);
        }

        public void bind(final City city, final OnCityClickListener listener) {
            cityNameTextView.setText(city.getName());
            cityImageView.setImageResource(city.getImageResourceId());
            itemView.setOnClickListener(v -> listener.onCityClick(city));
        }
    }
}
