package com.example.autok;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

public class SearchResultActivity extends AppCompatActivity {

    private TextView Talalatok;
    private Button Vissza;
    private DBHelper db;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_search_result);

        init();

        Bundle modellek=getIntent().getExtras();
        if (modellek!=null){
            String modell=modellek.getString("modellKulcs");
            Talalatok.setText(modell);
        }

        Vissza.setOnClickListener(view -> {
            Intent vissza = new Intent(SearchResultActivity.this, MainActivity.class);
            startActivity(vissza);
            finish();
        });

    }



    private void init(){
        Talalatok=findViewById(R.id.tv_eredmeny);
        Vissza=findViewById(R.id.btn_searchvissza);
        db = new DBHelper(this);

    }

}
