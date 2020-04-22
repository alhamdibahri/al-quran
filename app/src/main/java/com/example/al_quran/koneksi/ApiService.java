package com.example.al_quran.koneksi;

import com.example.al_quran.response.ResponseAsmaulHusna;
import com.example.al_quran.response.ResponseDetailSurah;
import com.example.al_quran.response.ResponseDoa;
import com.example.al_quran.response.ResponseSurah;

import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Path;
import retrofit2.http.Query;

public interface ApiService {

    @GET("index.php")
    Call<ResponseSurah> surah();

    @GET("detail_surah.php")
    Call<ResponseDetailSurah> surah_detail(@Query("id_surah") String id_surah);

    @GET("asmaul_husna.php")
    Call<ResponseAsmaulHusna> asmaul_husna();

    @GET("doa_harian.php")
    Call<ResponseDoa> doa();
}
