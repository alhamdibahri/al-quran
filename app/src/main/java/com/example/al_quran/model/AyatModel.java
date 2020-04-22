package com.example.al_quran.model;

public class AyatModel {
    String id_ayat;
    String ayat;

    public String getTerjemahan() {
        return terjemahan;
    }

    public void setTerjemahan(String terjemahan) {
        this.terjemahan = terjemahan;
    }

    String terjemahan;

    public String getId_ayat() {
        return id_ayat;
    }

    public void setId_ayat(String id_ayat) {
        this.id_ayat = id_ayat;
    }

    public String getAyat() {
        return ayat;
    }

    public void setAyat(String ayat) {
        this.ayat = ayat;
    }
}
