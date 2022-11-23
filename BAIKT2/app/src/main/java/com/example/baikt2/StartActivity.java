package com.example.baikt2;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import androidx.appcompat.app.AppCompatActivity;

public class StartActivity extends AppCompatActivity {
    Button btnSIwc;
    Button btnSUwc;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_start);
        AnhXa();

        btnSIwc.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent myIntent=new Intent(StartActivity.this, LoginActivity.class);
                startActivity(myIntent);
            }
        });
        btnSUwc.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent myIntent=new Intent(StartActivity.this, SignUpActivity.class);
                startActivity(myIntent);
            }
        });
    }
    public void AnhXa(){
        btnSIwc = (Button) findViewById(R.id.buttonSIwelcome);
        btnSUwc = (Button) findViewById(R.id.buttonSUwelcome);
    }
}
