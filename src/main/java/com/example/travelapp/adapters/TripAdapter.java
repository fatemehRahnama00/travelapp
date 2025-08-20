package com.example.travelapp.adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;
import com.example.travelapp.R;
import com.example.travelapp.models.Trip;
import com.squareup.picasso.Picasso;
import java.util.List;

public class TripAdapter extends RecyclerView.Adapter<TripAdapter.ViewHolder> {

    private List<Trip> trips;
    private Context context;

    public TripAdapter(Context context, List<Trip> trips) {
        this.context = context;
        this.trips = trips;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.item_trip, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        Trip trip = trips.get(position);
        holder.tvDestination.setText(trip.getDestination());
        holder.tvDate.setText(trip.getDate());
        if (trip.getImageUrl() != null && !trip.getImageUrl().isEmpty()) {
            Picasso.get().load(trip.getImageUrl()).into(holder.ivTripImage);
        }
    }

    @Override
    public int getItemCount() {
        return trips.size();
    }

    public static class ViewHolder extends RecyclerView.ViewHolder {
        TextView tvDestination, tvDate;
        ImageView ivTripImage;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            tvDestination = itemView.findViewById(R.id.tv_destination);
            tvDate = itemView.findViewById(R.id.tv_date);
            ivTripImage = itemView.findViewById(R.id.iv_trip_image);
        }
    }
}