package com.example.quanly.GiaoDien;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.content.DialogInterface;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;

import com.example.quanly.Adapter.CustomAdapterNhanVien;
import com.example.quanly.Database.DbNhanVien;
import com.example.quanly.Model.NhanVien;
import com.example.quanly.R;

import java.util.ArrayList;

public class MainHoaDon<pri> extends AppCompatActivity {

    Button btnXoa, btnSua, btnThem,btnClean;
    EditText txtMaSV, txtHoTen, txtDiaChi;
    ListView lvDanhSachSV;

    int index = -1;
    CustomAdapterNhanVien apdapter;
    ArrayList<NhanVien> data_SV = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main_hoa_don);
        setControl();
        setEvent();
    }

    private void setEvent() {

//        HienThiDL();


        btnThem.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {


                ThemDL();
//                HienThiDL();
            }
        });
        btnXoa.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                NhanVien nhanVien = getSinhVien();
                DbNhanVien dbSinhVien = new DbNhanVien(getApplicationContext());
                dbSinhVien.Xoa(nhanVien);
                data_SV.remove(index);
                apdapter.notifyDataSetChanged();
            }
        });

//        lvDanhSachSV.setOnItemClickListener(new AdapterView.OnItemClickListener() {
//            @Override
//            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
//                SinhVien sinhVien = data_SV.get(position);
//                txtMaSV.setText(sinhVien.getMaSV());
//                txtDiaChi.setText(sinhVien.getDiaChi());
//                txtHoTen.setText(sinhVien.getTenSV());
//
//
//
//                index = position;
//            }
//        });
        btnSua.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                NhanVien sinhVien = data_SV.get(index);
                sinhVien.setMaNhanVien(txtMaSV.getText().toString());
                sinhVien.setHoTen(txtHoTen.getText().toString());
                sinhVien.setDienThoai(txtDiaChi.getText().toString());

                apdapter.notifyDataSetChanged();
            }
        });
        btnClean.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                txtMaSV.setText("");
                txtHoTen.setText("");
                txtDiaChi.setText("");

            }
        });
    }


//    private void HienThiDL() {
//        DBNhaThuoc dbNhaThuoc = new DBNhaThuoc(this);
//        data_SV = dbNhaThuoc.LayDL();
//        apdapter = new CustomApdapterSV(this, R.layout.listview_item, data_SV);
//        lvDanhSachSV.setAdapter(apdapter);
//    }

    private void ThemDL() {
        DbNhanVien dbNhanVien = new DbNhanVien(this);

        NhanVien sinhVien = new NhanVien();
        sinhVien.setMaNhanVien(txtMaSV.getText().toString());
        sinhVien.setHoTen(txtHoTen.getText().toString());
        sinhVien.setDienThoai(txtDiaChi.getText().toString());
        dbNhanVien.Them(sinhVien);


    }


    private NhanVien getSinhVien() {
        NhanVien sinhVien = new NhanVien();
        sinhVien.setMaNhanVien(txtMaSV.getText().toString());
        sinhVien.setHoTen(txtHoTen.getText().toString());
        sinhVien.setDienThoai(txtDiaChi.getText().toString());
        return sinhVien;
    }


    private void setControl() {
        btnThem = findViewById(R.id.btnThem);
        btnClean = findViewById(R.id.btnclean);
        btnXoa = findViewById(R.id.btnxoa);
        btnSua = findViewById(R.id.btnsua);
        txtMaSV = findViewById(R.id.txtMa);
        txtHoTen = findViewById(R.id.txtHoTen);
        txtDiaChi = findViewById(R.id.txtDiaChi);
        lvDanhSachSV = findViewById(R.id.lvDanhSach);
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
