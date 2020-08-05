package com.example.quanly.Adapter;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.quanly.GiaoDien.MainActivityChiTiet;
import com.example.quanly.Model.NhanVien;
import com.example.quanly.R;

import java.util.ArrayList;


public class CustomAdapterNhanVien extends ArrayAdapter {
    Context context;
    int resource;
    ArrayList<NhanVien>data;

    public CustomAdapterNhanVien(Context context, int resource, ArrayList<NhanVien>data) {
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
        ImageView imgGT,imgDetail;
        TextView tvHoTen;
        TextView maNhanVien;
        TextView dienThoai;
    }
    @Override
    public View getView(int position,View convertView, ViewGroup parent) {
       View view = convertView;
        Holder holder = null;
        if (view == null){
            holder = new Holder();
            view = LayoutInflater.from(context).inflate(resource,null);
            holder.imgGT = view.findViewById(R.id.imgGT);
            holder.imgDetail = view.findViewById(R.id.imgDetail);
            holder.tvHoTen = view.findViewById(R.id.tvHoten);
            holder.maNhanVien = view.findViewById(R.id.tvMaNV);
            holder.dienThoai = view.findViewById(R.id.tvDienThoai);
            view.setTag(holder);
        }else {
            holder=(Holder)view.getTag();
        }
        final  NhanVien nhanVien =data.get(position);
        if(nhanVien.isGioiTinh()){
            holder.imgGT.setImageResource(R.drawable.nam);
        }else {
            holder.imgGT.setImageResource(R.drawable.nu);
        }

        holder.tvHoTen.setText(nhanVien.getHoTen());
        holder.maNhanVien.setText(nhanVien.getMaNhanVien());
        holder.dienThoai.setText(nhanVien.getDienThoai());
        holder.imgDetail.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Intent intent = new Intent((Activity) context, MainActivityChiTiet.class);
                Bundle bundle = new Bundle();
                bundle.putString("manhanvien", nhanVien.getMaNhanVien());
                intent.putExtras(bundle);
                ((Activity) context).startActivity(intent);
            }
        });
        return  view;


    }
}
