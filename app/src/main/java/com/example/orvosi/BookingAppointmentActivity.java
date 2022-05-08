package com.example.orvosi;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.annotation.SuppressLint;
import android.content.res.TypedArray;
import android.os.Bundle;
import android.util.Log;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

import java.sql.Array;
import java.util.ArrayList;

public class BookingAppointmentActivity extends AppCompatActivity {
    private FirebaseUser user;
    private FirebaseAuth regAuth;
    private static final String LOG_TAG = BookingAppointmentActivity.class.getName();

    private RecyclerView recyclerView;
    private ArrayList<BookingItem> ItemList;
    private BookingAdapter adapter;
    private int gridNumber = 1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_booking_appointment);
        regAuth = FirebaseAuth.getInstance();
        user = FirebaseAuth.getInstance().getCurrentUser();
        if (user != null) {
            Log.d(LOG_TAG,"Hitelesített felhasználó!");
        } else {
            Log.d(LOG_TAG,"Nem hitelesített felhasználó!");
            finish();
        }
        
        recyclerView = findViewById(R.id.recyclerView);
        recyclerView.setLayoutManager(new GridLayoutManager(this, gridNumber));
        ItemList = new ArrayList<>();

        adapter = new BookingAdapter(this, ItemList);
        recyclerView.setAdapter(adapter);

        initializeData();
    }
        @SuppressLint("NotifyDataSetChanged")
        private void initializeData() {
            String[] diseases = getResources().getStringArray(R.array.disease_item_names);
            String[] doctors = getResources().getStringArray(R.array.doctor_item_names);
            String[] consultingHours = getResources().getStringArray(R.array.consulting_hours_item);
            TypedArray doctorsRating = getResources().obtainTypedArray(R.array.doctors_rates);

            ItemList.clear();

            for (int i = 0; i < diseases.length; i++) {
                ItemList.add(new BookingItem(diseases[i], doctors[i], consultingHours[i], doctorsRating.getFloat(i,0)));
            }

            adapter.notifyDataSetChanged();

        }
}