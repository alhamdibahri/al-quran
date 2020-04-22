package com.example.al_quran.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.Filter;
import android.widget.Filterable;
import android.widget.TextView;

import com.example.al_quran.R;
import com.example.al_quran.model.SurahModel;

import java.util.ArrayList;
import java.util.List;

public class SurahAdapter extends BaseAdapter implements Filterable {
    Context context;
    List<SurahModel> list, filterd;

    public SurahAdapter(Context context, List<SurahModel> list) {
        this.context = context;
        this.list = list;
        this.filterd = list;
    }

    @Override
    public int getCount() {
        return filterd.size();
    }

    @Override
    public Object getItem(int position) {
        return filterd.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        if (convertView == null){
            LayoutInflater inflater =LayoutInflater.from(this.context);
            convertView = inflater.inflate(R.layout.list_surah, null);
        }

        SurahModel surah = filterd.get(position);

        TextView txtname = convertView.findViewById(R.id.name_surah);
        TextView txtnama = convertView.findViewById(R.id.nama_surah);
        TextView txtterjemahan = convertView.findViewById(R.id.terjemahan);

        TextView id_surah = convertView.findViewById(R.id.id_surah);

        txtname.setText(surah.getName());
        txtnama.setText(surah.getName_latin());
        txtterjemahan.setText("(" + surah.getTerjemahan() + ": " + surah.getNumber_of_ayah() + " Ayat )");

        id_surah.setText(surah.getId_surah());
        return convertView;
    }

    @Override
    public Filter getFilter() {
        SurahFilter filter = new SurahFilter();
        return filter;
    }

    private class SurahFilter extends Filter{

        @Override
        protected FilterResults performFiltering(CharSequence constraint) {
            List<SurahModel> filterData = new ArrayList<SurahModel>();
            FilterResults results = new FilterResults();
            String filterString = constraint.toString().toLowerCase();
            for (SurahModel srh: list){
                if(srh.getName_latin().toLowerCase().contains(filterString) || srh.getTerjemahan().toLowerCase().contains(filterString)){
                    filterData.add(srh);
                }
            }
            results.count = filterData.size();
            results.values = filterData;
            return results;
        }

        @Override
        protected void publishResults(CharSequence constraint, FilterResults results) {
            filterd = (List<SurahModel>) results.values;
            notifyDataSetChanged();
        }
    }
}
