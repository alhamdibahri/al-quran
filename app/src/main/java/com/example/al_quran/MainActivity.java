package com.example.al_quran;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;

import com.example.al_quran.model.AyatModel;

import butterknife.ButterKnife;
import butterknife.OnClick;

public class MainActivity extends AppCompatActivity {

    private  static int SPLASH_TIME_OUT = 4000;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        ButterKnife.bind(this);

    }

    @OnClick(R.id.btnSurah)
    void surah(){
        startActivity(new Intent(MainActivity.this, Surah.class));
    }

    @OnClick(R.id.btnAsmaul)
    void asmaul(){
        startActivity(new Intent(MainActivity.this, AsmaulHusna.class));
    }

    @OnClick(R.id.btnDoa)
    void doa(){
        startActivity(new Intent(MainActivity.this, Doa.class));
    }

    @OnClick(R.id.btnKursi)
    void ayat_kursi(){
        startActivity(new Intent(MainActivity.this, AyatKursi.class));
    }
}
