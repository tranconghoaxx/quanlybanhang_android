package com.example.quanly.Adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import com.example.quanly.Model.HoaDon;

import com.example.quanly.R;

import java.util.ArrayList;


public class CustomAdapterHoaDon extends ArrayAdapter {
    Context context;
    int resource;
    ArrayList<HoaDon>data;

    public CustomAdapterHoaDon(Context context, int resource, ArrayList<HoaDon>data) {
        super(context, resource);
        this.context = context;
        this.resource = resource;
        this.data = data;
    }

    @Override
    public int getCount() {
        return data.size();
    }
    private static class Holder {
        TextView tvMaHD;
        TextView tvNgayLapHD;
        TextView tvMaNV;
    }
    @Override
    public View getView(int position,View convertView, ViewGroup parent) {
        View view = convertView;
        Holder holder = null;
        if (view == null){
            holder = new Holder();
            view = LayoutInflater.from(context).inflate(resource,null);
            holder.tvMaHD = view.findViewById(R.id.tvMaHD);
            holder.tvNgayLapHD = view.findViewById(R.id.tvNgayLapHD);
            holder.tvMaNV = view.findViewById(R.id.tvMaNV);

            view.setTag(holder);
        }else {
            holder=(Holder)view.getTag();
        }
        final HoaDon hoaDon =data.get(position);
        holder.tvMaHD.setText(hoaDon.getMaHoaDon());
        holder.tvNgayLapHD.setText(hoaDon.getNgayLapHoaDon());
        holder.tvMaNV.setText(hoaDon.getMaNhanVien());
        return  view;


    }
}
