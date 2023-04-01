package com.example.laundrite;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.app.ProgressDialog;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

import com.facebook.FacebookSdk;
import com.facebook.appevents.AppEventsLogger;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.Query;
import com.google.firebase.database.ValueEventListener;

public class MainActivity extends AppCompatActivity {

    TextView createnewaccount;
    EditText input_email, input_password;
    Button btnLogin;
    String emailPattern = "[a-zA-Z0-9._-]+@[a-z]+\\.+[a-z]+";
    ProgressDialog progressDialog;

    FirebaseAuth mAuth;
    FirebaseUser mUser;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        createnewaccount = findViewById(R.id.createnewaccount);

        input_email = findViewById(R.id.input_email);
        input_password = findViewById(R.id.input_password);
        btnLogin = findViewById(R.id.btn_login);
        progressDialog = new ProgressDialog(this);
        mAuth = FirebaseAuth.getInstance();
        mUser = mAuth.getCurrentUser();

        createnewaccount.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(MainActivity.this, RegistrationActivity.class));
            }
        });

        btnLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                performLogin();
            }
        });
    }

    private void performLogin() {
        String email = input_email.getText().toString();
        String password = input_password.getText().toString();


        if (!email.matches(emailPattern)) {
            input_email.setError("Enter Correct Email");
            input_email.requestFocus();
        } else if (password.isEmpty() || password.length() < 6) {
            input_password.setError("Enter proper password");
        } else {

            final String email_data = input_email.getText().toString();
            final String password_data = input_password.getText().toString();

            FirebaseDatabase firebaseDatabase = FirebaseDatabase.getInstance();
            DatabaseReference databaseReference = firebaseDatabase.getReference("datauser");

            Query check_email = databaseReference.orderByChild("email").equalTo(email_data);
            progressDialog.setMessage("Please wait while Login!");
            progressDialog.setTitle("Login");
            progressDialog.setCanceledOnTouchOutside(false);
            progressDialog.show();

            check_email.addListenerForSingleValueEvent(new ValueEventListener() {
                @Override
                public void onDataChange(@NonNull DataSnapshot snapshot) {
                    if(snapshot.exists()){
                        input_email.setError(null);
                        String passwrdcheck = snapshot.child(email_data).child("password").getValue(String.class);
                        if(passwrdcheck.equals(password_data)){
                            input_password.setError(null);
                            progressDialog.dismiss();
                            sendUserToNextActivity();
                            Toast.makeText(MainActivity.this,"Login Successful",Toast.LENGTH_SHORT).show();
                        }
                        else{
                            input_password.setError("Wrong Password");
                        }
                    }
                    else{
                        input_email.setError("User does not exists");

                    }
                }
                @Override
                public void onCancelled(@NonNull DatabaseError error) {

                }
            });

            mAuth.signInWithEmailAndPassword(email, password).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                @Override
                public void onComplete(@NonNull Task<AuthResult> task) {
                    if(task.isSuccessful()){
                        progressDialog.dismiss();
                        sendUserToNextActivity();
                        Toast.makeText(MainActivity.this,"Login Successful",Toast.LENGTH_SHORT).show();
                    }
                    else{
                        progressDialog.dismiss();
                        Toast.makeText(MainActivity.this,""+task.getException(),Toast.LENGTH_SHORT).show();
                    }
                }
            });

        }
    }
    private void sendUserToNextActivity() {
        Intent intent = new Intent(MainActivity.this,HomeActivity.class);
        intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK|Intent.FLAG_ACTIVITY_NEW_TASK);
        startActivity(intent);
        finish();
    }
}