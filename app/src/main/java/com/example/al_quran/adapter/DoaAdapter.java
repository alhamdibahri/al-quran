package com.example.al_quran.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseExpandableListAdapter;
import android.widget.Filter;
import android.widget.Filterable;
import android.widget.SearchView;
import android.widget.TextView;

import com.example.al_quran.R;
import com.example.al_quran.model.DoaModel;

import java.util.ArrayList;
import java.util.List;

public class DoaAdapter extends BaseExpandableListAdapter implements Filterable {

    Context context;
    List<DoaModel> list, filterd;

    public DoaAdapter(Context context, List<DoaModel> list) {
        this.context = context;
        this.list = list;
        this.filterd = list;
    }

    @Override
    public int getGroupCount() {
        return filterd.size();
    }

    @Override
    public int getChildrenCount(int groupPosition) {
        return 1;
    }

    @Override
    public Object getGroup(int groupPosition) {
        return filterd.get(groupPosition);
    }

    @Override
    public Object getChild(int groupPosition, int childPosition) {
        return null;
    }


    @Override
    public long getGroupId(int groupPosition) {
        return groupPosition;
    }

    @Override
    public long getChildId(int groupPosition, int childPosition) {
        return 0;
    }


    @Override
    public boolean hasStableIds() {
        return true;
    }

    @Override
    public View getGroupView(int groupPosition, boolean isExpanded, View convertView, ViewGroup parent) {
        if (convertView == null){
            LayoutInflater inflater =LayoutInflater.from(this.context);
            convertView = inflater.inflate(R.layout.list_group_doa, null);
        }
        DoaModel doa = filterd.get(groupPosition);
        TextView namadoa = convertView.findViewById(R.id.namadoa);
        namadoa.setText(doa.getNama_doa());

        return convertView;
    }

    @Override
    public View getChildView(int groupPosition, int childPosition, boolean isLastChild, View convertView, ViewGroup parent) {
        if (convertView == null){
            LayoutInflater inflater =LayoutInflater.from(this.context);
            convertView = inflater.inflate(R.layout.list_item_doa, null);
        }
        DoaModel doa = filterd.get(groupPosition);
        TextView namadoa = convertView.findViewById(R.id.arab);
        TextView latin = convertView.findViewById(R.id.latin);
        TextView terjemahan = convertView.findViewById(R.id.terjemahan);
        namadoa.setText(doa.getArab());
        latin.setText(doa.getLatin());
        terjemahan.setText(doa.getTerjemahan());
        return convertView;
    }

    @Override
    public boolean isChildSelectable(int groupPosition, int childPosition) {
        return false;
    }

    @Override
    public Filter getFilter() {
        DoaFilter filter = new DoaFilter();
        return filter;
    }

    private class DoaFilter extends Filter{

        @Override
        protected FilterResults performFiltering(CharSequence constraint) {
            List<DoaModel> filterData = new ArrayList<DoaModel>();
            FilterResults results = new FilterResults();
            String filterString = constraint.toString().toLowerCase();
            for (DoaModel doa: list){
                if(doa.getNama_doa().toLowerCase().contains(filterString)){
                    filterData.add(doa);
                }
            }
            results.count = filterData.size();
            results.values = filterData;
            return results;
        }

        @Override
        protected void publishResults(CharSequence constraint, FilterResults results) {
            filterd = (List<DoaModel>) results.values;
            notifyDataSetChanged();
        }
    }
}
