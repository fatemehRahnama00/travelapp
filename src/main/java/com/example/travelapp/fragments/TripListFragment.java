package com.example.travelapp.fragments;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import com.example.travelapp.R;
import com.example.travelapp.adapters.TripAdapter;
import com.example.travelapp.models.Trip;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.QueryDocumentSnapshot;
import java.util.ArrayList;
import java.util.List;

public class TripListFragment extends Fragment {

    private RecyclerView rvTrips;
    private TripAdapter adapter;
    private List<Trip> tripList;
    private FirebaseFirestore db;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.activity_trip_list, container, false);

        rvTrips = view.findViewById(R.id.rv_trips);
        tripList = new ArrayList<>();
        adapter = new TripAdapter(getContext(), tripList);
        rvTrips.setLayoutManager(new LinearLayoutManager(getContext()));
        rvTrips.setAdapter(adapter);

        db = FirebaseFirestore.getInstance();
        loadTrips();

        return view;
    }

    private void loadTrips() {
        db.collection("trips").get().addOnCompleteListener(task -> {
            if (task.isSuccessful()) {
                tripList.clear();
                for (QueryDocumentSnapshot document : task.getResult()) {
                    Trip trip = document.toObject(Trip.class);
                    trip.setId(document.getId());
                    tripList.add(trip);
                }
                adapter.notifyDataSetChanged();
            }
        });
    }
}