package com.example.autok;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;

public class MainActivity extends AppCompatActivity {

    private Button Keres, Felvetel;
    private EditText Gyarto;
    private DBHelper db;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        init();

        Keres.setOnClickListener(view -> {
            Intent felvetel = new Intent(MainActivity.this, SearchResultActivity.class);
            startActivity(felvetel);
            finish();
        });

        Felvetel.setOnClickListener(view -> {
            Intent felvetel = new Intent(MainActivity.this, InsertActivity.class);
            startActivity(felvetel);
            finish();
        });
    }

    private void init(){
        Keres=findViewById(R.id.btn_kereses);
        Felvetel=findViewById(R.id.btn_felvetel);
        Gyarto=findViewById(R.id.edit_gyarto);
        db = new DBHelper(this);
    }
}