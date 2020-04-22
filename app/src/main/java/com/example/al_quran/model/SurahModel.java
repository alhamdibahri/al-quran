package com.example.al_quran.model;

public class SurahModel {
    String id_surah, name, name_latin, number_of_ayah, terjemahan, source;

    public String getId_surah() {
        return id_surah;
    }

    public void setId_surah(String id_surah) {
        this.id_surah = id_surah;
    }

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
}
