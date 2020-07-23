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

import com.example.quanly.Adapter.CustomAdapterChiTiet;

import com.example.quanly.Database.DbChiTiet;

import com.example.quanly.Model.ChiTietHoaDon;

import com.example.quanly.R;

import java.util.ArrayList;

public class MainChiTietHoaDon extends AppCompatActivity {
    Button btnXoaCT, btnSuaCT, btnThemCT,btnCleanCT;
    EditText txtMaHDCT, txtMaSPCT, txtSoLuongSPCT;
    ListView lvDanhSachChiTietHD;

    int index = -1;
    CustomAdapterChiTiet apdapter;
    ArrayList<ChiTietHoaDon> data_CTHD = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main_chi_tiet_hoa_don);
        setControl();
        setEvent();
    }

    private void setEvent() {
        HienThiDL();
        btnThemCT.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ThemDL();
                HienThiDL();
            }
        });
        btnXoaCT.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ChiTietHoaDon chiTietHoaDon = getChiTiet();
                DbChiTiet dbChiTiet = new DbChiTiet(getApplicationContext());
                dbChiTiet.Xoa(chiTietHoaDon);
                data_CTHD.remove(index);
                apdapter.notifyDataSetChanged();
            }
        });

        btnSuaCT.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ChiTietHoaDon chiTietHoaDon = data_CTHD.get(index);
                chiTietHoaDon.setMaHD(txtMaHDCT.getText().toString());
                chiTietHoaDon.setMaSP(txtMaSPCT.getText().toString());
                chiTietHoaDon.setSoluongSP(txtSoLuongSPCT.getText().toString());
                apdapter.notifyDataSetChanged();
            }
        });
        btnCleanCT.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                txtMaHDCT.setText("");
                txtMaSPCT.setText("");
                txtSoLuongSPCT.setText("");
            }
        });
        lvDanhSachChiTietHD.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                ChiTietHoaDon chiTietHoaDon = data_CTHD.get(position);
                txtMaHDCT.setText(chiTietHoaDon.getMaHD());
                txtMaSPCT.setText(chiTietHoaDon.getMaSP());
                txtSoLuongSPCT.setText(chiTietHoaDon.getSoluongSP());

                index = position;
            }
        });
    }


    private void HienThiDL() {
        DbChiTiet dbChiTiet = new DbChiTiet(this);
        data_CTHD = dbChiTiet.LayDL();
        apdapter = new CustomAdapterChiTiet(this, R.layout.listview_item_hoadon, data_CTHD);
        lvDanhSachChiTietHD.setAdapter(apdapter);
    }

    private void ThemDL() {
        DbChiTiet dbChiTiet = new DbChiTiet(this);
        ChiTietHoaDon chiTietHoaDon = new ChiTietHoaDon();
        chiTietHoaDon.setMaHD(txtMaHDCT.getText().toString());
        chiTietHoaDon.setMaSP(txtMaSPCT.getText().toString());
        chiTietHoaDon.setSoluongSP(txtSoLuongSPCT.getText().toString());

        dbChiTiet.Them(chiTietHoaDon);
    }
    private ChiTietHoaDon getChiTiet() {
        ChiTietHoaDon chiTietHoaDon = new ChiTietHoaDon();
        chiTietHoaDon.setMaHD(txtMaHDCT.getText().toString());
        chiTietHoaDon.setMaSP(txtMaSPCT.getText().toString());
        chiTietHoaDon.setSoluongSP(txtSoLuongSPCT.getText().toString());

        return chiTietHoaDon;
    }


    private void setControl() {
        btnThemCT = findViewById(R.id.btnThemCT);
        btnCleanCT = findViewById(R.id.btncleanCT);
        btnXoaCT = findViewById(R.id.btnxoaCT);
        btnSuaCT = findViewById(R.id.btnsuaCT);

        txtMaHDCT = findViewById(R.id.txtMaHDCT);
        txtMaSPCT = findViewById(R.id.txtMaSPCT);
        txtSoLuongSPCT = findViewById(R.id.txtSoLuongSPCT);

        lvDanhSachChiTietHD = findViewById(R.id.lvDanhSachChiTietHD);
    }

    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_actionbar, menu);
        return super.onCreateOptionsMenu(menu);
    }

    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case R.id.mnLuu:
                AlertDialog.Builder builder1 = new AlertDialog.Builder(MainChiTietHoaDon.this);
                builder1.setMessage("Đã In");
                AlertDialog alertDialogSave = builder1.create();
                alertDialogSave.show();
                break;

            case R.id.mnThoat:
                AlertDialog.Builder builder = new AlertDialog.Builder(MainChiTietHoaDon.this);
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
