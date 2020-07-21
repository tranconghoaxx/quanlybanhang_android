package com.example.quanly.Adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;
import com.example.quanly.Model.SanPham;
import com.example.quanly.R;

import java.util.ArrayList;

public class CustomAdapterSanPham  extends ArrayAdapter {
    Context context;
    int resource;
    ArrayList<SanPham> data;

    public CustomAdapterSanPham(Context context, int resource, ArrayList<SanPham>data) {
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
        TextView tvMaSP;
        TextView tvTenSP;
        TextView tvDVTinh;
        TextView tvDonGia;
    }
    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        View view = convertView;
        CustomAdapterSanPham.Holder holder = null;
        if (view == null){
            holder = new CustomAdapterSanPham.Holder();
            view = LayoutInflater.from(context).inflate(resource,null);
            holder.tvMaSP = view.findViewById(R.id.tvMaSP);
            holder.tvTenSP = view.findViewById(R.id.tvTenSP);
            holder.tvDVTinh = view.findViewById(R.id.tvDonViTinh);
            holder.tvDonGia = view.findViewById(R.id.tvDonGia);

            view.setTag(holder);
        }else {
            holder=(CustomAdapterSanPham.Holder)view.getTag();
        }
        final SanPham sanPham =data.get(position);
        holder.tvMaSP.setText(sanPham.getMaSP());
        holder.tvTenSP.setText(sanPham.getTenSP());
        holder.tvDVTinh.setText(sanPham.getDvTinh());
        holder.tvDonGia.setText(sanPham.getDonGia());
        return  view;


    }
}
