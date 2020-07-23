package com.example.quanly.Adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import com.example.quanly.Model.ChiTietHoaDon;
import com.example.quanly.Model.HoaDon;
import com.example.quanly.R;

import java.util.ArrayList;

public class CustomAdapterChiTiet extends ArrayAdapter {
    Context context;
    int resource;
    ArrayList<ChiTietHoaDon> data;

    public CustomAdapterChiTiet(Context context, int resource, ArrayList<ChiTietHoaDon>data) {
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
        TextView tvMaHDCT;
        TextView tvMaSPHDCT;
        TextView tvSoluongCT;
    }
    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        View view = convertView;
        CustomAdapterChiTiet.Holder holder = null;
        if (view == null){
            holder = new CustomAdapterChiTiet.Holder();
            view = LayoutInflater.from(context).inflate(resource,null);
            holder.tvMaHDCT = view.findViewById(R.id.tvMaHD);
            holder.tvMaSPHDCT = view.findViewById(R.id.tvNgayLapHD);
            holder.tvSoluongCT = view.findViewById(R.id.tvMaNV);

            view.setTag(holder);
        }else {
            holder=(CustomAdapterChiTiet.Holder)view.getTag();
        }
        final ChiTietHoaDon chiTietHoaDon =data.get(position);
        holder.tvMaHDCT.setText(chiTietHoaDon.getMaHD());
        holder.tvMaSPHDCT.setText(chiTietHoaDon.getMaSP());
        holder.tvSoluongCT.setText(chiTietHoaDon.getSoluongSP());
        return  view;


    }
}
