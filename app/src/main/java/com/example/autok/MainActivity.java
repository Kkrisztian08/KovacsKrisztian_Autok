package com.example.autok;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.database.Cursor;
import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

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
            String gyarto = Gyarto.getText().toString().trim();
            Cursor adat=db.modellKeres(gyarto);
            StringBuilder sb=new StringBuilder();
            if (gyarto.isEmpty()){
                Toast.makeText(getApplicationContext(), "A gyártó mező kitöltése kötelező!", Toast.LENGTH_SHORT).show();
            }else {
                if (!(adat.getCount()==0)) {
                    while (adat.moveToNext()){
                        sb.append("Modell: ").append(adat.getString(0));
                        sb.append(System.lineSeparator());
                    }
                }else {
                    sb.append("Nem található rekord a következő adattal: ").append(gyarto);
                }
                Intent felvetel = new Intent(MainActivity.this, SearchResultActivity.class);
                String modell = sb.toString();
                felvetel.putExtra("modellKulcs", modell);
                startActivity(felvetel);
                finish();
            }

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