package com.example.quanly.GiaoDien;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.content.DialogInterface;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.ListView;

import com.example.quanly.Adapter.CustomAdapterHoaDon;
import com.example.quanly.Database.DbHoaDon;
import com.example.quanly.Database.DbNhanVien;
import com.example.quanly.Model.HoaDon;
import com.example.quanly.Model.NhanVien;
import com.example.quanly.R;

import java.util.ArrayList;

public class MainHoaDon extends AppCompatActivity {
    //    back
    ImageView imgBack;
    Button btnXoaHD, btnSuaHD, btnThemHD,btnCleanHD;
    EditText txtMaHD, txtNgayLapHD, txtMaNV;
    ListView lvDanhSachHoaDon;

    int index = -1;
    CustomAdapterHoaDon apdapter;
    ArrayList<HoaDon> data_HD = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main_hoa_don);
        setControl();
        backControl();
        setEvent();
    }
    private void backControl() {
        imgBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onBackPressed();
            }
        });
    }
    private void setEvent() {
        HienThiDL();
        btnThemHD.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ThemDL();
                HienThiDL();
            }
        });
        btnXoaHD.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                HoaDon hoaDon = getHoaDon();
                DbHoaDon dbHoaDon = new DbHoaDon(getApplicationContext());
                dbHoaDon.Xoa(hoaDon);
                data_HD.remove(index);
                apdapter.notifyDataSetChanged();
            }
        });

        btnSuaHD.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                HoaDon hoaDon = data_HD.get(index);
                hoaDon.setMaHoaDon(txtMaHD.getText().toString());
                hoaDon.setNgayLapHoaDon(txtNgayLapHD.getText().toString());
                hoaDon.setMaNhanVien(txtMaNV.getText().toString());

                apdapter.notifyDataSetChanged();
                DbHoaDon dbHoaDon = new DbHoaDon(getApplicationContext());
                dbHoaDon.Sua(hoaDon);
            }
        });
        btnCleanHD.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                txtMaHD.setText("");
                txtNgayLapHD.setText("");
                txtMaNV.setText("");
            }
        });
        lvDanhSachHoaDon.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                HoaDon hoaDon = data_HD.get(position);
                txtMaHD.setText(hoaDon.getMaHoaDon());
                txtNgayLapHD.setText(hoaDon.getNgayLapHoaDon());
                txtMaNV.setText(hoaDon.getMaNhanVien());

                index = position;
            }
        });
    }


    private void HienThiDL() {
        DbHoaDon dbHoaDon = new DbHoaDon(this);
        data_HD = dbHoaDon.LayDL();
        apdapter = new CustomAdapterHoaDon(this, R.layout.listview_item_hoadon, data_HD);
        lvDanhSachHoaDon.setAdapter(apdapter);
    }

    private void ThemDL() {
        DbHoaDon dbHoaDon = new DbHoaDon(this);
        HoaDon hoaDon = new HoaDon();
        hoaDon.setMaHoaDon(txtMaHD.getText().toString());
        hoaDon.setNgayLapHoaDon(txtNgayLapHD.getText().toString());
        hoaDon.setMaNhanVien(txtMaNV.getText().toString());
        dbHoaDon.Them(hoaDon);
    }
    private HoaDon getHoaDon() {
        HoaDon hoaDon = new HoaDon();
        hoaDon.setMaHoaDon(txtMaHD.getText().toString());
        hoaDon.setNgayLapHoaDon(txtNgayLapHD.getText().toString());
        hoaDon.setMaNhanVien(txtMaNV.getText().toString());
        return hoaDon;
    }


    private void setControl() {
        imgBack = findViewById(R.id.imgBack);
        btnThemHD = findViewById(R.id.btnThemHD);
        btnCleanHD = findViewById(R.id.btncleanHD);
        btnXoaHD = findViewById(R.id.btnxoaHD);
        btnSuaHD = findViewById(R.id.btnsuaHD);

        txtMaHD = findViewById(R.id.txtMaHoaDon);
        txtNgayLapHD = findViewById(R.id.txtNgayLapHD);
        txtMaNV = findViewById(R.id.txtMaNVHD);

        lvDanhSachHoaDon = findViewById(R.id.lvDanhSachHD);
    }


}
