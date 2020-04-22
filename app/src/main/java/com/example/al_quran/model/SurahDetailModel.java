package com.example.al_quran.model;

import java.util.List;

public class SurahDetailModel {
    String name,name_latin,number_of_ayah,terjemahan,source;
    List<AyatModel> ayat;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getName_latin() {
        return name_latin;
    }

    public void setName_latin(String name_latin) {
        this.name_latin = name_latin;
    }

    public String getNumber_of_ayah() {
        return number_of_ayah;
    }

    public void setNumber_of_ayah(String number_of_ayah) {
        this.number_of_ayah = number_of_ayah;
    }

    public String getTerjemahan() {
        return terjemahan;
    }

    public void setTerjemahan(String terjemahan) {
        this.terjemahan = terjemahan;
    }

    public String getSource() {
        return source;
    }

    public void setSource(String source) {
        this.source = source;
    }

    public List<AyatModel> getAyat() {
        return ayat;
    }

    public void setAyat(List<AyatModel> ayat) {
        this.ayat = ayat;
    }
}
