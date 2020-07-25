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
import com.example.quanly.Adapter.CustomAdapterSanPham;
import com.example.quanly.Database.DbHoaDon;
import com.example.quanly.Database.DbSanPham;
import com.example.quanly.Model.HoaDon;
import com.example.quanly.Model.SanPham;
import com.example.quanly.R;

import java.util.ArrayList;

public class MainSanPham extends AppCompatActivity {

    ImageView imgBack;
    Button btnXoaSP, btnSuaSP, btnThemSP,btnCleanSP;
    EditText txtMaSP, txtTenSP, txtDVTinh,txtDonGia;
    ListView lvDanhSachSanPham;

    int index = -1;
    CustomAdapterSanPham apdapter;
    ArrayList<SanPham> data_SP = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main_san_pham);
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
        btnThemSP.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ThemDL();
                HienThiDL();
            }
        });
        btnXoaSP.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                SanPham sanPham = getSanPham();
                DbSanPham dbSanPham = new DbSanPham(getApplicationContext());
                dbSanPham.Xoa(sanPham);
                data_SP.remove(index);
                apdapter.notifyDataSetChanged();
            }
        });

        btnSuaSP.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                SanPham sanPham = data_SP.get(index);
                sanPham.setMaSP(txtMaSP.getText().toString());
                sanPham.setTenSP(txtTenSP.getText().toString());
                sanPham.setDvTinh(txtDVTinh.getText().toString());
                sanPham.setDonGia(txtDonGia.getText().toString());

                apdapter.notifyDataSetChanged();
            }
        });
        btnCleanSP.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                txtMaSP.setText("");
                txtTenSP.setText("");
                txtDVTinh.setText("");
                txtDonGia.setText("");
            }
        });
        lvDanhSachSanPham.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                SanPham sanPham = data_SP.get(position);
                txtMaSP.setText(sanPham.getMaSP());
                txtTenSP.setText(sanPham.getTenSP());
                txtDVTinh.setText(sanPham.getDvTinh());
                txtDonGia.setText(sanPham.getDonGia());

                index = position;
            }
        });
    }


    private void HienThiDL() {
        DbSanPham dbSanPham = new DbSanPham(this);
        data_SP = dbSanPham.LayDL();
        apdapter = new CustomAdapterSanPham(this, R.layout.listview_item_sanpham, data_SP);
        lvDanhSachSanPham.setAdapter(apdapter);
    }

    private void ThemDL() {
        DbSanPham dbSanPham = new DbSanPham(this);
        SanPham sanPham = new SanPham();
        sanPham.setMaSP(txtMaSP.getText().toString());
        sanPham.setTenSP(txtTenSP.getText().toString());
        sanPham.setDvTinh(txtDVTinh.getText().toString());
        sanPham.setDonGia(txtDonGia.getText().toString());

        dbSanPham.Them(sanPham);
    }
    private SanPham getSanPham() {
        SanPham sanPham = new SanPham();
        sanPham.setMaSP(txtMaSP.getText().toString());
        sanPham.setTenSP(txtTenSP.getText().toString());
        sanPham.setDvTinh(txtDVTinh.getText().toString());
        sanPham.setDonGia(txtDonGia.getText().toString());

        return sanPham;
    }


    private void setControl() {
        imgBack = findViewById(R.id.imgBack);
        btnThemSP = findViewById(R.id.btnThemSP);
        btnCleanSP = findViewById(R.id.btncleanSP);
        btnXoaSP = findViewById(R.id.btnxoaSP);
        btnSuaSP = findViewById(R.id.btnsuaSP);

        txtMaSP = findViewById(R.id.txtMaSP);
        txtTenSP = findViewById(R.id.txtTenSP);
        txtDVTinh = findViewById(R.id.txtDonViSP);
        txtDonGia = findViewById(R.id.txtDonGiaSP);

        lvDanhSachSanPham = findViewById(R.id.lvDanhSachSP);
    }
}
