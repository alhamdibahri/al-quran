package com.example.al_quran;

import androidx.appcompat.app.AppCompatActivity;

import android.app.SearchManager;
import android.content.Context;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.View;
import android.widget.ExpandableListView;
import android.widget.ListView;
import android.widget.ProgressBar;
import android.widget.SearchView;

import com.example.al_quran.adapter.DoaAdapter;
import com.example.al_quran.adapter.SurahAdapter;
import com.example.al_quran.koneksi.ApiService;
import com.example.al_quran.koneksi.RetrofitClient;
import com.example.al_quran.model.DoaModel;
import com.example.al_quran.model.SurahModel;
import com.example.al_quran.response.ResponseDoa;
import com.tapadoo.alerter.Alerter;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class Doa extends AppCompatActivity implements SearchView.OnQueryTextListener{

    @BindView(R.id.listdoa)
    ExpandableListView listdoa;
    private DoaAdapter adapter;
    @BindView(R.id.myProgressbar)
    ProgressBar progressBar;

    ApiService apiService;
    Call<ResponseDoa> call;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_doa);
        ButterKnife.bind(this);

        apiService = RetrofitClient.createService(ApiService.class);

        progressBar.setIndeterminate(true);
        progressBar.setVisibility(View.VISIBLE);

        call = apiService.doa();
        call.enqueue(new Callback<ResponseDoa>() {
            @Override
            public void onResponse(Call<ResponseDoa> call, Response<ResponseDoa> response) {
                if(response.isSuccessful()){
                    progressBar.setVisibility(View.GONE);
                    isiData(response.body().getValues());
                }
            }
            @Override
            public void onFailure(Call<ResponseDoa> call, Throwable t) {
                progressBar.setVisibility(View.GONE);
                Alerter.create(Doa.this)
                        .setTitle("Error")
                        .setText("Periksa Koneksi Anda")
                        .setIcon(R.drawable.ic_error_black_24dp)
                        .setDuration(3000)
                        .setBackgroundColor(R.color.danger)
                        .show();
            }
        });


    }

    private void isiData(List<DoaModel> doa){
        adapter = new DoaAdapter(this, doa);
        listdoa.setAdapter(adapter);
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
        searchView.setQueryHint("Cari Doa");
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
