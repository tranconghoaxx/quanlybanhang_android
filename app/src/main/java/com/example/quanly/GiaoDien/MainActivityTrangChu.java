package com.example.quanly.GiaoDien;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.ListView;

import com.example.quanly.Adapter.MyRecyclerViewAdapter;
import com.example.quanly.Model.CardViewModel;
import com.example.quanly.R;

import java.util.ArrayList;
import java.util.List;
import java.util.Vector;

public class MainActivityTrangChu extends AppCompatActivity {
    Button btntrangNhanVien,btntrangHoaDon,btntrangChiTietHoaDon,btntrangSanPham,btntrangThongKe;
    ListView lvDanhSachSV;
    int index = -1;

    private Vector<CardViewModel> data;
    RecyclerView recyclerView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main_trang_chu);
        initRecycleViews();
        setControl();
        setEvent();
    }
    void initRecycleViews(){
        recyclerView = (RecyclerView) findViewById(R.id.recyclerView);
        //Initiation of data
        data = new Vector<CardViewModel>();
        data.add(new CardViewModel("Cửa Hàng 1", R.drawable.bach_hoa_xanh));
        data.add(new CardViewModel("Cửa Hàng 2", R.drawable.coop_food));
        data.add(new CardViewModel("Cửa Hàng 3", R.drawable.vinmart_14));
        data.add(new CardViewModel("Cửa Hàng 4", R.drawable.bach_hoa_xanh));

        //Setup Recycler View
        LinearLayoutManager layoutManager = new LinearLayoutManager(this);
        layoutManager.setOrientation(LinearLayoutManager.HORIZONTAL);
        //GridLayoutManager layoutManager = new GridLayoutManager(this, 3);
        recyclerView.setLayoutManager(layoutManager);

        MyRecyclerViewAdapter adapter = new MyRecyclerViewAdapter(R.layout.cardview_item_cuahang, data);
        recyclerView.setAdapter(adapter);
    }

    private void setEvent() {
        btntrangNhanVien.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent =   new Intent(MainActivityTrangChu.this, MainActivityNhanVien.class);
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
        btntrangThongKe.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent =   new Intent(MainActivityTrangChu.this, MainActivityThongKe.class);
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
        btntrangThongKe = findViewById(R.id.btnTrangThongKe);
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

        }
        return super.onOptionsItemSelected(item);
    }
}