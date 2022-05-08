package com.example.orvosi;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.util.Log;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

import java.util.ArrayList;

public class BookingAppointmentActivity extends AppCompatActivity {
    private FirebaseUser user;
    private FirebaseAuth regAuth;
    private static final String LOG_TAG = BookingAppointmentActivity.class.getName();

    private RecyclerView recyclerView;
    private ArrayList<BookingItem> ItemList;
    private BookingAdapter adapter;

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
    }
}