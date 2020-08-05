package com.example.quanly.GiaoDien;

import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.EditText;

import com.example.quanly.Database.DbNhanVien;
import com.example.quanly.Model.NhanVien;
import com.example.quanly.R;

import java.util.ArrayList;

public class MainActivityChiTiet extends AppCompatActivity {
    EditText txtMaSV, txtHoTen, txtDiaChi;
    ArrayList<NhanVien> data_SV = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main_chi_tiet);
        setConTrol();
        setEvent();
        ActionBar actionBar = getSupportActionBar();
        actionBar.setDisplayHomeAsUpEnabled(true);

    }

    private void setEvent() {
        String ma = getIntent().getExtras().getString("manhanvien");
        DbNhanVien dbNhanVien = new DbNhanVien(this);
        data_SV = dbNhanVien.LayDL(ma);
        txtMaSV.setText(data_SV.get(0).getMaNhanVien());
        txtHoTen.setText(data_SV.get(0).getHoTen());
        txtDiaChi.setText(data_SV.get(0).getDienThoai());
    }


    private void setConTrol() {
        txtMaSV = findViewById(R.id.txtMa);
        txtHoTen = findViewById(R.id.txtHoTen);
        txtDiaChi = findViewById(R.id.txtDiaChi);


    }
}