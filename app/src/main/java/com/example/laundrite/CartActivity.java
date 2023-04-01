package com.example.laundrite;

import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.media.Image;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

public class CartActivity extends AppCompatActivity {

    Button increment_tshirt,decrement_tshirt,incremental_shirt,decremental_shirt,incremental_jeans,decremental_jeans,incremental_shorts,decremental_shorts;;
    TextView display_tshirt,display_shirt,display_jeans,display_shorts,tx_total_tshirt,tx_total_shirt,tx_total_jeans,tx_total_shorts;
    Button address_btn;
    int count = 0;
    int b1,b2,b3,b4,c;
    ImageView back;

    @SuppressLint("MissingInflatedId")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.item_cart);

        increment_tshirt = findViewById(R.id.incremental_btn_tshirt);
        decrement_tshirt = findViewById(R.id.decremental_btn_tshirt);
        incremental_shirt = findViewById(R.id.incremental_btn_shirt);
        decremental_shirt = findViewById(R.id.decremental_btn_shirt);
        incremental_jeans = findViewById(R.id.incremental_btn_jeans);
        decremental_jeans = findViewById(R.id.decremental_btn_jeans);
        incremental_shorts = findViewById(R.id.incremental_btn_shorts);
        decremental_shorts = findViewById(R.id.decremental_btn_shorts);
        display_tshirt = findViewById(R.id.txdisplay_tshirt);
        display_shirt = findViewById(R.id.txdisplay_shirt);
        display_jeans = findViewById(R.id.txdisplay_jeans);
        display_shorts = findViewById(R.id.txdisplay_shorts);
        tx_total_tshirt = findViewById(R.id.tx_total_tshirt);
        tx_total_shirt = findViewById(R.id.tx_total_shirt);
        tx_total_jeans = findViewById(R.id.tx_total_jeans);
        tx_total_shorts = findViewById(R.id.tx_total_shorts);
        address_btn = findViewById(R.id.address_btn_dry);
        back = findViewById(R.id.back_btn);


        increment_tshirt.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                count ++;
                display_tshirt.setText(""+count);
                int a = Integer.parseInt(display_tshirt.getText().toString());
                b1 = a*15;
                tx_total_tshirt.setText("Total: Rs "+b1);

            }
        });

        decrement_tshirt.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(count<=0) count=0;
                else count--;
                display_tshirt.setText(""+count--);
                int a = Integer.parseInt(display_tshirt.getText().toString());
                b1=a*15;
                tx_total_tshirt.setText("Total: Rs "+b1);
            }
        });

        incremental_shirt.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                count ++;
                display_shirt.setText(""+count);
                int a = Integer.parseInt(display_shirt.getText().toString());
                b2 = a*18;
                tx_total_shirt.setText("Total: Rs "+b2);
            }
        });

        decremental_shirt.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(count<=0) count=0;
                else count--;
                display_shirt.setText(""+count--);
                int a = Integer.parseInt(display_shirt.getText().toString());
                b2 = a*18;
                tx_total_shirt.setText("Total: Rs "+b2);
            }
        });

        incremental_jeans.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                count ++;
                display_jeans.setText(""+count);
                int a = Integer.parseInt(display_jeans.getText().toString());
                b3 = a*20;
                tx_total_jeans.setText("Total: Rs "+b3);
            }
        });

        decremental_jeans.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(count<=0) count=0;
                else count--;
                display_jeans.setText(""+count--);
                int a = Integer.parseInt(display_jeans.getText().toString());
                b3 = a*20;
                tx_total_jeans.setText("Total: Rs "+b3);
            }
        });
        incremental_shorts.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                count ++;
                display_shorts.setText(""+count);
                int a = Integer.parseInt(display_shorts.getText().toString());
                b4 = a*10;
                tx_total_shorts.setText("Total: Rs "+b4);

            }
        });

        decremental_shorts.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(count<=0) count=0;
                else count--;
                display_shorts.setText(""+count--);
                int a = Integer.parseInt(display_shorts.getText().toString());
                b4 = a*10;
                tx_total_shorts.setText("Total: Rs "+b4);
            }
        });



        address_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                c = b1+b2+b3+b4;
                Toast.makeText(getApplicationContext(),"Grand Total: Rs. "+c,Toast.LENGTH_SHORT).show();
                Intent intent = new Intent(getApplicationContext(), AddressActivity_Normal.class);
                startActivity(intent);
                finish();
            }
        });
        back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getApplicationContext(),HomeActivity.class);
                startActivity(intent);
                finish();
            }
        });






    }
}