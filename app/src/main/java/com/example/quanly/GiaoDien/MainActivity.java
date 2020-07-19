package com.example.quanly.GiaoDien;

import android.content.DialogInterface;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.RadioButton;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import com.example.quanly.Adapter.CustomAdapterNhanVien;
import com.example.quanly.Database.DbNhanVien;
import com.example.quanly.Model.NhanVien;
import com.example.quanly.R;

import java.util.ArrayList;


public class MainActivity extends AppCompatActivity {
    EditText txtHoTen,txtMaNV,txtDienThoai;
    RadioButton radNam,radNu;
    Button btnThem,btnXoa,btnSua,btnLamMoi;
    ListView lvDanhSach;
    CustomAdapterNhanVien adapterNhanVien;
    int index = -1;
    ArrayList<NhanVien>data_NV = new ArrayList<>();
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        setControl();
        setEvent();
    }

    private void setEvent() {
        HienThiDL();
        btnThem.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ThemDL();
                HienThiDL();
            }
        });
        btnXoa.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                NhanVien nhanVien = getNhaVien();
                DbNhanVien dbNhanVien = new DbNhanVien(getApplicationContext());
                dbNhanVien.Xoa(nhanVien);
                data_NV.remove(index);
                adapterNhanVien.notifyDataSetChanged();
            }
        });
        btnSua.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                NhanVien nhanVien = data_NV.get(index);
                nhanVien.setHoTen(txtHoTen.getText().toString());
                nhanVien.setMaNhanVien(txtMaNV.getText().toString());
                nhanVien.setDienThoai(txtDienThoai.getText().toString());
                nhanVien.setGioiTinh(radNam.isChecked());
                adapterNhanVien.notifyDataSetChanged();
            }
        });
        btnLamMoi.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                txtHoTen.setText("");
                txtMaNV.setText("");
                txtDienThoai.setText("");
                radNam.setChecked(true);
            }
        });
        lvDanhSach.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                NhanVien nhanVien = data_NV.get(position);
                txtHoTen.setText(nhanVien.getHoTen());
                txtMaNV.setText(nhanVien.getMaNhanVien());
                txtDienThoai.setText(nhanVien.getDienThoai());
                if (nhanVien.isGioiTinh()){
                    radNam.setChecked(true);
                }else {
                    radNu.setChecked(true);
                }
                index = position;
            }
        });

    }

    private NhanVien getNhaVien() {
        NhanVien nhanVien = new NhanVien();
        nhanVien.setHoTen(txtHoTen.getText().toString());
        nhanVien.setMaNhanVien(txtMaNV.getText().toString());
        nhanVien.setDienThoai(txtDienThoai.getText().toString());
        nhanVien.setGioiTinh(radNam.isChecked());
        return nhanVien;
    }

    private void ThemDL() {
        DbNhanVien dbNhanVien = new DbNhanVien(this);
        NhanVien nhanVien = new NhanVien();
        nhanVien.setHoTen(txtHoTen.getText().toString());
        nhanVien.setMaNhanVien(txtMaNV.getText().toString());
        nhanVien.setDienThoai(txtDienThoai.getText().toString());
        nhanVien.setGioiTinh(radNam.isChecked());
        dbNhanVien.Them(nhanVien);
    }

    private void HienThiDL() {
        DbNhanVien dbNhanVien = new DbNhanVien(this);
        data_NV = dbNhanVien.LayDL();
        adapterNhanVien = new CustomAdapterNhanVien(this,R.layout.listview_item,data_NV);
        lvDanhSach.setAdapter(adapterNhanVien);
    }

    private void setControl() {
        txtHoTen = findViewById(R.id.txtHoTen);
        txtMaNV = findViewById(R.id.txtMa);
        txtDienThoai = findViewById(R.id.txtDienThoai);
        radNam = findViewById(R.id.radNam);
        radNu = findViewById(R.id.radNu);
        btnThem = findViewById(R.id.btnThem);
        btnXoa = findViewById(R.id.btnxoa);
        btnSua = findViewById(R.id.btnsua);
        btnLamMoi = findViewById(R.id.btnclean);
        lvDanhSach = findViewById(R.id.lvDanhSach);
    }
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_actionbar, menu);
        return super.onCreateOptionsMenu(menu);
    }

    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case R.id.mnLuu:
                AlertDialog.Builder builder1 = new AlertDialog.Builder(MainActivity.this);
                builder1.setMessage("Đã In");
                AlertDialog alertDialogSave = builder1.create();
                alertDialogSave.show();
                break;

            case R.id.mnThoat:
                AlertDialog.Builder builder = new AlertDialog.Builder(MainActivity.this);
                builder.setTitle("Thông báo");
                builder.setMessage("Bạn có muốn thoát?");
                builder.setPositiveButton("OK", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        finish();
                    }
                });

                builder.setNegativeButton("Cancel", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        dialog.cancel();
                    }
                });

                AlertDialog alertDialog = builder.create();
                alertDialog.show();
                break;

            case R.id.mnNgonngu:

        }
        return super.onOptionsItemSelected(item);
    }
}