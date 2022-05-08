package com.example.orvosi;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;

public class RegistrationActivity extends AppCompatActivity {
    private static final String LOG_TAG = RegistrationActivity.class.getName();
    private static final String PREF_KEY = MainActivity.class.getPackage().toString();
    //private static final int SECRET_KEY = 1;

    EditText usernameRegister;
    EditText userEmailRegister;
    EditText passwordRegister;
    EditText passwordAgainRegister;
    EditText phoneNumberRegister;
    EditText addressRegister;

    private SharedPreferences preferences;
    private FirebaseAuth regAuth;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_registration);

        //Bundle bundle = getIntent().getExtras();
        //int secret_key = bundle.getInt("SECRET_KEY");
        int secret_key = getIntent().getIntExtra("SECRET_KEY", 0);

        if(secret_key != 1) {
            finish();
        }

        usernameRegister = findViewById(R.id.usernameRegister);
        userEmailRegister = findViewById(R.id.userEmailRegister);
        passwordRegister = findViewById(R.id.passwordRegister);
        passwordAgainRegister = findViewById(R.id.passwordAgainRegister);
        phoneNumberRegister = findViewById(R.id.phoneNumberRegister);
        addressRegister = findViewById(R.id.addressRegister);

        preferences = getSharedPreferences(PREF_KEY, MODE_PRIVATE);
        String username = preferences.getString("userName", "");
        String password = preferences.getString("password", "");

        usernameRegister.setText(username);
        passwordRegister.setText(password);
        passwordAgainRegister.setText(password);

        regAuth = FirebaseAuth.getInstance();


        Log.i(LOG_TAG,"onCreate");
    }

    public void register(View view) {
        String userName = usernameRegister.getText().toString();
        String email = userEmailRegister.getText().toString();
        String password = passwordRegister.getText().toString();
        String passwordConfirm = passwordAgainRegister.getText().toString();

        if (!password.equals(passwordConfirm)) {
            Log.e(LOG_TAG, "Nem egyezezik a jelszó és a megerősítése");
            return;
        }

        String phoneNumber = phoneNumberRegister.getText().toString();
        String address = addressRegister.getText().toString();

        Log.i(LOG_TAG, "Regisztrált: "+ userName + "e-mail: " + email);

        regAuth.createUserWithEmailAndPassword(email, password).addOnCompleteListener(this, new OnCompleteListener<AuthResult>() {
            @Override
            public void onComplete(@NonNull Task<AuthResult> task) {
                if (task.isSuccessful()) {
                    Log.d(LOG_TAG, "Sikeres regisztráció");
                    startBooking();
                } else {
                    Log.d(LOG_TAG, "Sikertelen regisztráció");
                    Toast.makeText(RegistrationActivity.this,"Sikertelen regisztráció" + task.getException().getMessage(), Toast.LENGTH_LONG).show();
                }
            }
        });

    }

    public void cancel(View view) {
        finish();
    }

    private void startBooking(/* registered user data */) {

        Intent intent = new Intent(this, BookingAppointmentActivity.class);
        startActivity(intent);
    }

    @Override
    public void addContentView(View view, ViewGroup.LayoutParams params) {
        super.addContentView(view, params);
        Log.i(LOG_TAG,"onCreate");
    }

    @Override
    protected void onStart() {
        super.onStart();
        Log.i(LOG_TAG,"onStart");
    }

    @Override
    protected void onStop() {
        super.onStop();
        Log.i(LOG_TAG,"onStop");
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        Log.i(LOG_TAG,"onDestroy");
    }

    @Override
    protected void onPause() {
        super.onPause();
        Log.i(LOG_TAG,"onPause");
    }

    @Override
    protected void onResume() {
        super.onResume();
        Log.i(LOG_TAG,"onResume");
    }

    @Override
    protected void onRestart() {
        super.onRestart();
        Log.i(LOG_TAG,"onRestart");
    }
}