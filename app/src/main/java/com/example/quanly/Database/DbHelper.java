package com.example.quanly.Database;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;


public class DbHelper extends SQLiteOpenHelper {
    public DbHelper(Context context) {
        super(context, "QLNhanVien", null, 1);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        String sql = "create table NhanVien(hoten text, manhanvien text, dienthoai text, gioitinh text)";
        String sqlTableHoaDon = "create table HoaDon(mahoadon text, ngaylaphoadon text, manhanvien text)";
        String sqlTableSanPham = "create table SanPham(dongia text, dvtinh text, masanpham text, tensanpham text)";
        String sqlTableChiTiet = "create table ChiTiet(masanpham text, mahoadon text, soluong text)";
        db.execSQL(sql);
        db.execSQL(sqlTableHoaDon);
        db.execSQL(sqlTableSanPham);
        db.execSQL(sqlTableChiTiet);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

    }
}
