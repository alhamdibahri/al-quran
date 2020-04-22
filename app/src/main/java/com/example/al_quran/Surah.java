package com.example.al_quran;

import androidx.appcompat.app.AppCompatActivity;
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout;

import android.app.SearchManager;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.ProgressBar;
import android.widget.SearchView;
import android.widget.TextView;

import com.example.al_quran.adapter.SurahAdapter;
import com.example.al_quran.koneksi.ApiService;
import com.example.al_quran.koneksi.RetrofitClient;
import com.example.al_quran.model.SurahModel;
import com.example.al_quran.response.ResponseSurah;
import com.tapadoo.alerter.Alerter;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;

public class Surah extends AppCompatActivity implements SearchView.OnQueryTextListener {

    private static final String TAG = "Surah";

    @BindView(R.id.listsurah)
    ListView listsurah;
    private SurahAdapter adapter;
    @BindView(R.id.myProgressbar)
    ProgressBar progressBar;

    ApiService apiService;
    Call<ResponseSurah> call;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_surah);

        ButterKnife.bind(this);

        apiService = RetrofitClient.createService(ApiService.class);

        progressBar.setIndeterminate(true);
        progressBar.setVisibility(View.VISIBLE);

        listsurah.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                TextView id_surah = (TextView)view.findViewById(R.id.id_surah);
                Intent i = new Intent(Surah.this, DetailSurah.class);
                i.putExtra("id_surah", id_surah.getText());
                startActivity(i);
            }
        });

        call = apiService.surah();
        call.enqueue(new Callback<ResponseSurah>() {
            @Override
            public void onResponse(Call<ResponseSurah> call, Response<ResponseSurah> response) {
                if(response.isSuccessful()){
                    progressBar.setVisibility(View.GONE);
                    isiData(response.body().getValues());
                }
            }
            @Override
            public void onFailure(Call<ResponseSurah> call, Throwable t) {
                progressBar.setVisibility(View.GONE);
                Alerter.create(Surah.this)
                        .setTitle("Error")
                        .setText("Periksa Koneksi Anda")
                        .setIcon(R.drawable.ic_error_black_24dp)
                        .setDuration(3000)
                        .setBackgroundColor(R.color.danger)
                        .show();
            }
        });
    }

    private void isiData(List<SurahModel> surah){
        adapter = new SurahAdapter(this, surah);
        listsurah.setAdapter(adapter);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.search, menu);
        SearchManager searchManager = (SearchManager)getSystemService(Context.SEARCH_SERVICE);
        SearchView searchView = (SearchView) menu.findItem(R.id.search).getActionView();
        searchView.setSearchableInfo(searchManager.getSearchableInfo(getComponentName()));
        searchView.setIconifiedByDefault(false);
        searchView.setOnQueryTextListener(this);
        searchView.setQueryHint("Cari Surat");
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onQueryTextSubmit(String query) {
        return false;
    }

    @Override
    public boolean onQueryTextChange(String newText) {
        adapter.getFilter().filter(newText);
        return true;
    }
}
