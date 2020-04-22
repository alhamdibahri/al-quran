package com.example.al_quran;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ListView;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import com.example.al_quran.adapter.DetailSurahAdapter;
import com.example.al_quran.adapter.SurahAdapter;
import com.example.al_quran.koneksi.ApiService;
import com.example.al_quran.koneksi.RetrofitClient;
import com.example.al_quran.model.AyatModel;
import com.example.al_quran.model.SurahModel;
import com.example.al_quran.response.ResponseDetailSurah;
import com.example.al_quran.response.ResponseSurah;
import com.tapadoo.alerter.Alerter;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class DetailSurah extends AppCompatActivity {

    private static final String TAG = "DetailSurah";

    ApiService apiService;
    Call<ResponseDetailSurah> call;

    @BindView(R.id.listayat)
    ListView listayat;
    private DetailSurahAdapter adapter;
    @BindView(R.id.myProgressbar)
    ProgressBar progressBar;
//    @BindView(R.id.name_surah)
//    TextView name_surah;
//    @BindView(R.id.nama_surah)
//    TextView nama_surah;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail_surah);

        ButterKnife.bind(this);

        apiService = RetrofitClient.createService(ApiService.class);

        call = apiService.surah_detail(getIntent().getStringExtra("id_surah"));

        progressBar.setIndeterminate(true);
        progressBar.setVisibility(View.VISIBLE);

        call.enqueue(new Callback<ResponseDetailSurah>() {
            @Override
            public void onResponse(Call<ResponseDetailSurah> call, Response<ResponseDetailSurah> response) {
                getSupportActionBar().setTitle(response.body().getValues().getName_latin() + " - " + response.body().getValues().getTerjemahan());
                progressBar.setVisibility(View.GONE);
                isiData(response.body().getValues().getAyat());
            }

            @Override
            public void onFailure(Call<ResponseDetailSurah> call, Throwable t) {
                progressBar.setVisibility(View.GONE);
                Alerter.create(DetailSurah.this)
                        .setTitle("Error")
                        .setText("Periksa Koneksi Anda")
                        .setIcon(R.drawable.ic_error_black_24dp)
                        .setDuration(3000)
                        .setBackgroundColor(R.color.danger)
                        .show();
            }
        });
    }

    private void isiData(List<AyatModel> ayat){
        adapter = new DetailSurahAdapter(this, ayat);
        listayat.setAdapter(adapter);
    }
}
