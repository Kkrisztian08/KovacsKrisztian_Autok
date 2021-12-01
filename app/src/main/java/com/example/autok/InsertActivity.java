package com.example.autok;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

public class InsertActivity extends AppCompatActivity {

    private EditText Gyarto, Modell, Uzembe;
    private Button UjFelvetel,Vissza;
    private DBHelper db;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_insert);

        init();

        UjFelvetel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String gyarto = Gyarto.getText().toString().trim();
                String modell = Modell.getText().toString().trim();
                String uzembe = Uzembe.getText().toString().trim();

                if (gyarto.isEmpty() || modell.isEmpty() || uzembe.isEmpty()){
                    Toast.makeText(getApplicationContext(), "Minden mező kitöltése kötelező", Toast.LENGTH_SHORT).show();
                }
                else{
                    try {
                        int UzembeInt = Integer.parseInt(uzembe);
                        if (UzembeInt < 1900){
                            Toast.makeText(getApplicationContext(), "Nem lehet 1900-nál kisebb az üzembehelyezés éve", Toast.LENGTH_SHORT).show();
                        }else{
                            if (db.insert(gyarto, modell, UzembeInt)){
                                Toast.makeText(getApplicationContext(), "Sikeres rögzítés",
                                        Toast.LENGTH_SHORT).show();
                            } else{
                                Toast.makeText(getApplicationContext(), "Sikeretelen rögzítés",
                                        Toast.LENGTH_SHORT).show();
                            }
                        }
                    }catch (NumberFormatException ex){
                        Toast.makeText(getApplicationContext(), "Az üzembehelyezési évenek számnak kell lennie",
                                Toast.LENGTH_SHORT).show();
                    }
                }
            }
        });

        Vissza.setOnClickListener(view -> {
            Intent vissza = new Intent(InsertActivity.this, MainActivity.class);
            startActivity(vissza);
            finish();
        });



    }

    private void init(){
        Gyarto=findViewById(R.id.edit_ujgyarto);
        Modell=findViewById(R.id.edit_ujmodell);
        Uzembe=findViewById(R.id.edit_ujuzembe);
        UjFelvetel=findViewById(R.id.btn_ujfelvetel);
        Vissza=findViewById(R.id.btn_insertvissza);
        db = new DBHelper(this);

    }
}
