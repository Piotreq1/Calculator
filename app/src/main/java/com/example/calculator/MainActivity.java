package com.example.calculator;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {
    Button b_simple, b_advanced, b_about, b_exit;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getSupportActionBar().hide();
        setContentView(R.layout.start_menu);

        b_simple = findViewById(R.id.button_simple);
        b_advanced = findViewById(R.id.button_advanced);
        b_about = findViewById(R.id.button_about);
        b_exit = findViewById(R.id.button_exit);

        b_simple.setOnClickListener(this);
        b_advanced.setOnClickListener(this);
        b_about.setOnClickListener(this);
        b_exit.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.button_simple:
                Intent intent = new Intent(this, SimpleActivity.class);
                startActivity(intent);
                break;
            case R.id.button_advanced:
                intent = new Intent(this, AdvancedActivity.class);
                startActivity(intent);
                break;
            case R.id.button_about:
                Toast.makeText(getApplicationContext(), "Piotr Sitarek 235969", Toast.LENGTH_LONG).show();
                break;
            case R.id.button_exit:
                finish();
                break;
        }
    }

}