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
import android.widget.ListView;

import com.example.quanly.Adapter.CustomAdapterHoaDon;
import com.example.quanly.Database.DbHoaDon;
import com.example.quanly.Database.DbNhanVien;
import com.example.quanly.Model.HoaDon;
import com.example.quanly.Model.NhanVien;
import com.example.quanly.R;

import java.util.ArrayList;

public class MainHoaDon extends AppCompatActivity {

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
        setEvent();
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
        btnThemHD = findViewById(R.id.btnThemHD);
        btnCleanHD = findViewById(R.id.btncleanHD);
        btnXoaHD = findViewById(R.id.btnxoaHD);
        btnSuaHD = findViewById(R.id.btnsuaHD);

        txtMaHD = findViewById(R.id.txtMaHoaDon);
        txtNgayLapHD = findViewById(R.id.txtNgayLapHD);
        txtMaNV = findViewById(R.id.txtMaNVHD);

        lvDanhSachHoaDon = findViewById(R.id.lvDanhSachHD);
    }

    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_actionbar, menu);
        return super.onCreateOptionsMenu(menu);
    }

    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case R.id.mnLuu:
                AlertDialog.Builder builder1 = new AlertDialog.Builder(MainHoaDon.this);
                builder1.setMessage("Đã In");
                AlertDialog alertDialogSave = builder1.create();
                alertDialogSave.show();
                break;

            case R.id.mnThoat:
                AlertDialog.Builder builder = new AlertDialog.Builder(MainHoaDon.this);
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

//            case R.id.mnChuyen:
//                Intent intent = new Intent(MainActivity.this,Main2Activity.class);
//                startActivity(intent);
//                break;
//            default:
//                throw new IllegalStateException("Unexpected value: " + item.getItemId());
        }
        return super.onOptionsItemSelected(item);
    }
}
