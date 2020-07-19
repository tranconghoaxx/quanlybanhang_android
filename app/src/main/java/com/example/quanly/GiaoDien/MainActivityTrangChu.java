package com.example.quanly.GiaoDien;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.ListView;

import com.example.quanly.R;

public class MainActivityTrangChu extends AppCompatActivity {
    Button btntrangNhanVien,btntrangHoaDon,btntrangChiTietHoaDon,btntrangSanPham;
    ListView lvDanhSachSV;
    int index = -1;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main_trang_chu);
        setControl();
        setEvent();

    }

    private void setEvent() {
        btntrangNhanVien.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent =   new Intent(MainActivityTrangChu.this, MainActivity.class);
                Bundle bundle = new Bundle();
                intent.putExtras(bundle);
                startActivity(intent);
            }
        });

        btntrangHoaDon.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent =   new Intent(MainActivityTrangChu.this, MainHoaDon.class);
                Bundle bundle = new Bundle();
                intent.putExtras(bundle);
                startActivity(intent);
            }
        });

        btntrangChiTietHoaDon.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent =   new Intent(MainActivityTrangChu.this, MainChiTietHoaDon.class);
                Bundle bundle = new Bundle();
                intent.putExtras(bundle);
                startActivity(intent);
            }
        });

        btntrangSanPham.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent =   new Intent(MainActivityTrangChu.this, MainSanPham.class);
                Bundle bundle = new Bundle();
                intent.putExtras(bundle);
                startActivity(intent);
            }
        });


    }
    private void setControl() {
        btntrangNhanVien = findViewById(R.id.btnTrangNhanVien);
        btntrangHoaDon = findViewById(R.id.btnTrangHoaDon);
        btntrangChiTietHoaDon = findViewById(R.id.btnTrangChiTietHD);
        btntrangSanPham = findViewById(R.id.btnTrangSanPham);

        lvDanhSachSV = findViewById(R.id.lvDanhSach);

    }
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_actionbar, menu);
        return super.onCreateOptionsMenu(menu);
    }

    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case R.id.mnLuu:
                AlertDialog.Builder builder1 = new AlertDialog.Builder(MainActivityTrangChu.this);
                builder1.setMessage("Đã In");
                AlertDialog alertDialogSave = builder1.create();
                alertDialogSave.show();
                break;

            case R.id.mnThoat:
                AlertDialog.Builder builder = new AlertDialog.Builder(MainActivityTrangChu.this);
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