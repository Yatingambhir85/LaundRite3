package com.example.laundrite;

import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.app.DatePickerDialog;
import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;

import java.util.Calendar;

public class AddressActivity_Dry extends AppCompatActivity {

    Button checkout_btn;
    EditText et1,et2,et3,et4,et5,et6,et7;
    int year,month,day;
    String DOB;
    ImageView back;


    @SuppressLint("MissingInflatedId")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_address);


        checkout_btn = findViewById(R.id.checkout_btn);
        et1 = findViewById(R.id.flat_et);
        et2 = findViewById(R.id.societ_et);
        et3 = findViewById(R.id.landmark_et);
        et4 = findViewById(R.id.pincode_et);
        et5 = findViewById(R.id.city_et);
        et6 = findViewById(R.id.state_et);
        et7 = findViewById(R.id.pickup_et);
        back = findViewById(R.id.back_btn);

        final Calendar calendar = Calendar.getInstance();


        checkout_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                if((TextUtils.isEmpty(et1.getText()))&&(TextUtils.isEmpty(et2.getText()))&&(TextUtils.isEmpty(et3.getText()))&&(TextUtils.isEmpty(et4.getText()))&&(TextUtils.isEmpty(et5.getText()))&&(TextUtils.isEmpty(et6.getText()))&&(TextUtils.isEmpty(et7.getText()))) {
                    Toast.makeText(getApplicationContext(),"Field required",Toast.LENGTH_SHORT).show();
                }
                else {
                    Intent intent = new Intent(getApplicationContext(), ChekoutActivity.class);
                    startActivity(intent);
                    finish();
                }
            }
        });

        et7.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                year = calendar.get(Calendar.YEAR);
                month = calendar.get(Calendar.MONTH);
                day = calendar.get(Calendar.DAY_OF_MONTH);

                DatePickerDialog datePickerDialog = new DatePickerDialog(AddressActivity_Dry.this, new DatePickerDialog.OnDateSetListener() {
                    @Override
                    public void onDateSet(DatePicker view, int year, int month, int dayOfMonth) {
                        DOB = dayOfMonth+"/"+(month+1)+"/"+year;
                        et7.setText(DOB);

                    }
                },year,month,day);
                datePickerDialog.show();
            }
        });
        back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getApplicationContext(),CartActivity_dry.class);
                startActivity(intent);
                finish();
            }
        });



    }
}