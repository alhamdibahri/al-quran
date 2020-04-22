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
import com.example.al_quran.model.AsmaulHusnaModel;

import java.util.ArrayList;
import java.util.List;

public class AsmaulHusnaAdapter extends BaseAdapter implements Filterable {
    Context context;
    List<AsmaulHusnaModel> list, filterd;

    public AsmaulHusnaAdapter(Context context, List<AsmaulHusnaModel> list) {
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
    public boolean isEnabled(int position) {
        return false;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        if (convertView == null){
            LayoutInflater inflater =LayoutInflater.from(this.context);
            convertView = inflater.inflate(R.layout.list_asmaul_husna, null);
        }

        AsmaulHusnaModel asmaul = filterd.get(position);

        TextView txtname = convertView.findViewById(R.id.asmaul_husna);
        TextView txtnama = convertView.findViewById(R.id.nama);
        TextView txtterjemahan = convertView.findViewById(R.id.terjemahan);

        txtname.setText(asmaul.getArab());
        txtnama.setText(asmaul.getNama());
        txtterjemahan.setText(asmaul.getIndonesia());

        return convertView;
    }

    @Override
    public Filter getFilter() {
        AsmaulFilter filter = new AsmaulFilter();
        return filter;
    }

    private class AsmaulFilter extends Filter{

        @Override
        protected FilterResults performFiltering(CharSequence constraint) {
            List<AsmaulHusnaModel> filterData = new ArrayList<AsmaulHusnaModel>();
            FilterResults results = new FilterResults();
            String filterString = constraint.toString().toLowerCase();
            for (AsmaulHusnaModel asmaul: list){
                if(asmaul.getNama().toLowerCase().contains(filterString)){
                    filterData.add(asmaul);
                }
            }
            results.count = filterData.size();
            results.values = filterData;
            return results;
        }

        @Override
        protected void publishResults(CharSequence constraint, FilterResults results) {
            filterd = (List<AsmaulHusnaModel>) results.values;
            notifyDataSetChanged();
        }
    }
}
