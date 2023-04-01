
package com.example.laundrite;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class RegistrationActivity extends AppCompatActivity {

    TextView alreadyhaveanaccount;
    EditText input_email,input_password,input_confirmpassword,input_name,input_phone;
    Button btnRegister;
    String emailPattern = "[a-zA-Z0-9._-]+@[a-z]+\\.+[a-z]+";
    ProgressDialog progressDialog;

    FirebaseAuth mAuth;
    FirebaseUser mUser;
    FirebaseDatabase firebaseDatabase;
    DatabaseReference reference;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_registration);
        alreadyhaveanaccount=findViewById(R.id.alreadyhaveanaccount);
        input_email=findViewById(R.id.input_email);
        input_password=findViewById(R.id.input_password);
        input_confirmpassword=findViewById(R.id.input_confirmpassword);
        input_name=findViewById(R.id.input_name);
        input_phone=findViewById(R.id.input_phone);
        btnRegister=findViewById(R.id.btn_login);
        progressDialog=new ProgressDialog(this);
        mAuth=FirebaseAuth.getInstance();
        mUser= mAuth.getCurrentUser();

        alreadyhaveanaccount.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(RegistrationActivity.this,MainActivity.class));
            }
        });

        btnRegister.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                PerformAuth();
            }
        });
    }

    private void PerformAuth() {
        String email=input_email.getText().toString();
        String password=input_password.getText().toString();
        String confirmpassword=input_confirmpassword.getText().toString();
        String name=input_name.getText().toString();
        String number=input_phone.getText().toString();


        if(!email.matches(emailPattern)){
            input_email.setError("Enter Correct Email");
            Toast.makeText(getApplicationContext(),"Please enter correct email",Toast.LENGTH_SHORT).show();
            input_email.requestFocus();
        }
        else if(password.isEmpty() || password.length() < 6){
            input_password.setError("Enter 6 length password");
            Toast.makeText(getApplicationContext(),"Please enter correct password",Toast.LENGTH_SHORT).show();
            input_password.requestFocus();
        }
        else if(!password.equals(confirmpassword)){
            input_confirmpassword.setError("Password not match");
            Toast.makeText(getApplicationContext(),"Password not match",Toast.LENGTH_SHORT).show();
            input_confirmpassword.requestFocus();
        }
        else if(name.equals(null)){
            input_name.setError("Enter valid Name");
            Toast.makeText(getApplicationContext(),"Please enter correct Name",Toast.LENGTH_SHORT).show();
            input_name.requestFocus();
        }
        else if(number.isEmpty() || number.length()<10){
            input_phone.setError("Invalid Phone Number");
            Toast.makeText(getApplicationContext(),"Please enter correct number",Toast.LENGTH_SHORT).show();
            input_phone.requestFocus();
        }
        else{

            progressDialog.setMessage("Please wait while Registeration!");
            progressDialog.setTitle("Registeration");
            progressDialog.setCanceledOnTouchOutside(false);
            progressDialog.show();
                registerUser(name,number,email,password);
        }
    }

    private void registerUser(String name, String number, String email, String password) {
        mAuth = FirebaseAuth.getInstance();
        //Create user profile
        mAuth.createUserWithEmailAndPassword(email,password).addOnCompleteListener(RegistrationActivity.this, new OnCompleteListener<AuthResult>() {
            @Override
            public void onComplete(@NonNull Task<AuthResult> task) {
                if (task.isSuccessful()){

                    Toast.makeText(RegistrationActivity.this,"User Registeration Successful",Toast.LENGTH_SHORT).show();
                    FirebaseUser firebaseUser = mAuth.getCurrentUser();

                    // Enter user data into Firebase realtime database
                    ReadWriteUserDetails writeUserDetails = new ReadWriteUserDetails(name,number,email,password);

                    //Extracting User reference from database for Registered Users
                    DatabaseReference referenceProfile = FirebaseDatabase.getInstance().getReference("Registered Users");
                    referenceProfile.child(firebaseUser.getUid()).setValue(writeUserDetails).addOnCompleteListener(new OnCompleteListener<Void>() {
                        @Override
                        public void onComplete(@NonNull Task<Void> task) {
                            if (task.isSuccessful()){
                                firebaseUser.sendEmailVerification();

                                Intent intent = new Intent(getApplicationContext(),MainActivity.class);
                                intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP|Intent.FLAG_ACTIVITY_CLEAR_TASK|Intent.FLAG_ACTIVITY_NEW_TASK);
                                startActivity(intent);
                                finish();
                            }
                            else{
                                Toast.makeText(RegistrationActivity.this,"User Registeration Failed. Please try again",Toast.LENGTH_SHORT).show();
                            }

                        }
                    });

                }
            }
        });
    }
}