package com.example.laundrite;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.TextView;

import java.util.Random;

public class ConfirmationActivity extends AppCompatActivity {

    TextView tv1;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_confirmation);

        tv1 = findViewById(R.id.textOrder);
        Random random = new Random();

        int val = random.nextInt(1000);
        tv1.setText(Integer.toString(val));
    }
}