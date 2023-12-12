package com.example.phonenumberauthentication;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

public class DashboardActivity extends AppCompatActivity {
    private TextView phoneNumberUser;
    private Button logOutBtn;
    private FirebaseAuth mAuth;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_dashboard);

        phoneNumberUser = findViewById(R.id.phoneNumberUser);
        logOutBtn = findViewById(R.id.logOutBtn);

//        initialize the firebase auth
        mAuth = FirebaseAuth.getInstance();
        checkCurrentUser();

        logOutBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mAuth.signOut();
                checkCurrentUser();
                startActivity(new Intent(DashboardActivity.this, MainActivity.class));
                Toast.makeText(DashboardActivity.this, "Logged out successfully!", Toast.LENGTH_SHORT).show();
            }
        });
    }

    private void checkCurrentUser() {
        FirebaseUser firebaseUser = mAuth.getCurrentUser();
        if (firebaseUser != null) {
            String phoneNumber = firebaseUser.getPhoneNumber();
            phoneNumberUser.setText(phoneNumber);
        } else {
            finish();
        }
    }
}