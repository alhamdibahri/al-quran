package com.example.al_quran.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.example.al_quran.R;
import com.example.al_quran.model.AyatModel;

import java.util.List;

public class DetailSurahAdapter extends BaseAdapter {

    Context context;
    List<AyatModel> list;

    public DetailSurahAdapter(Context context, List<AyatModel> ayat) {
        this.context = context;
        this.list = ayat;
    }

    @Override
    public int getCount() {
        return list.size();
    }

    @Override
    public Object getItem(int position) {
        return list.get(position);
    }

    @Override
    public boolean isEnabled(int position) {
        return false;
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        if (convertView == null){
            LayoutInflater inflater =LayoutInflater.from(this.context);
            convertView = inflater.inflate(R.layout.list_detail_surah, null);
        }

        AyatModel ayat = list.get(position);

        TextView txtayat = convertView.findViewById(R.id.ayat);
        TextView txtterjemahan = convertView.findViewById(R.id.terjemahan_ayat);

        txtayat.setText(ayat.getAyat());
        txtterjemahan.setText(ayat.getTerjemahan());

        return convertView;
    }
}
