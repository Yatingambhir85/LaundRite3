package com.example.laundrite;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

public class ProfileActivity extends AppCompatActivity {


    private TextView textviewWelcome, textviewName, textviewNumber, textviewEmail;

    private String fullName, email,number;
    private ImageView imageView;
    private FirebaseAuth authProfile;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_profile);



        textviewWelcome = findViewById(R.id.welcome);
        textviewName = findViewById(R.id.text_view_name);
        textviewEmail = findViewById(R.id.text_view_email);
        textviewNumber = findViewById(R.id.text_view_phone);

        authProfile = FirebaseAuth.getInstance();
        FirebaseUser firebaseUser = authProfile.getCurrentUser();

        if(firebaseUser == null){
            Toast.makeText(ProfileActivity.this,"Something went wrong! User's details are not available at the moment",Toast.LENGTH_LONG).show();
        }
        else{
            showUserProfile(firebaseUser);
        }
    }

    private void showUserProfile(FirebaseUser firebaseUser) {
        String userID = firebaseUser.getUid();


        // Extracting user reference from database for "registered users"
        DatabaseReference referenceProfile = FirebaseDatabase.getInstance().getReference("Registered Users");
        referenceProfile.child(userID).addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                ReadWriteUserDetails readUserDetails = snapshot.getValue(ReadWriteUserDetails.class);
                if(readUserDetails!=null){
                    fullName = readUserDetails.fullName;
                    email = firebaseUser.getEmail();
                    number = readUserDetails.number;
                    textviewWelcome.setText("Welcome "+fullName+"!");
                    textviewName.setText(fullName);
                    textviewEmail.setText(email);
                    textviewNumber.setText(number);

                }
                else{
                    showUserProfile(firebaseUser);
                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });
    }
}