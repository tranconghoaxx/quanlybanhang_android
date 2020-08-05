package com.example.quanly.Database;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import com.example.quanly.Model.NhanVien;

import java.util.ArrayList;


public class DbNhanVien {
    DbHelper dbHelper;

    public DbNhanVien(Context context) {
        dbHelper = new DbHelper(context);
    }

    public void Them(NhanVien nhanVien) {
        SQLiteDatabase db = dbHelper.getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put("hoten", nhanVien.getHoTen());
        values.put("manhanvien", nhanVien.getMaNhanVien());
        values.put("dienthoai", nhanVien.getDienThoai());
        if (nhanVien.isGioiTinh()) {
            values.put("gioitinh", "Nam");
        } else {
            values.put("gioitinh", "Nu");
        }
        db.insert("NhanVien", null, values);
    }

    public void Xoa(NhanVien nhanVien) {

        SQLiteDatabase db = dbHelper.getWritableDatabase();
        String sql = "Delete from NhanVien where hoten= '" + nhanVien.getHoTen() + "'";
        db.execSQL(sql);

    }

    public void Sua(NhanVien nhanVien) {
        SQLiteDatabase db = dbHelper.getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put("hoten", nhanVien.getHoTen());

        values.put("manhanvien", nhanVien.getMaNhanVien());
        values.put("dienthoai", nhanVien.getDienThoai());
        if (nhanVien.isGioiTinh()) {
            values.put("gioitinh", "Nam");
        } else {
            values.put("gioitinh", "Nu");
        }
        db.update("NhanVien", values, "hoten = '" + nhanVien.getHoTen() + "'", null);
    }


//    }
    public ArrayList<NhanVien> LayDL() {
        ArrayList<NhanVien> data = new ArrayList<>();
        String sql = "select * from NhanVien";
        SQLiteDatabase db = dbHelper.getReadableDatabase();
        Cursor cursor = db.rawQuery(sql, null);
        try {
            cursor.moveToFirst();
            do {
                NhanVien nhanVien = new NhanVien();
                nhanVien.setHoTen(cursor.getString(0));
                nhanVien.setMaNhanVien(cursor.getString(1));
                nhanVien.setDienThoai(cursor.getString(2));
                if (cursor.getString(3).toString().equals("Nam")) {
                    nhanVien.setGioiTinh(true);
                } else {
                    nhanVien.setGioiTinh(false);
                }
                data.add(nhanVien);
            } while (cursor.moveToNext());
        } catch (Exception ex) {

        }
        return data;
    }
    public ArrayList<NhanVien> LayDL(String ma)
    {
        ArrayList<NhanVien> data = new ArrayList<>();
        String sql="select * from NhanVien where manhanvien = '"+ma+"'";
        SQLiteDatabase db= dbHelper.getReadableDatabase();
        Cursor cursor = db.rawQuery(sql,null);

        try {
            cursor.moveToFirst();
            do {
                NhanVien sinhVien = new NhanVien();
                sinhVien.setMaNhanVien(cursor.getString(0));
                sinhVien.setHoTen(cursor.getString(1));
                sinhVien.setDienThoai(cursor.getString(2));
                data.add(sinhVien);
            }
            while (cursor.moveToNext());
        }
        catch (Exception ex)
        {

        }


        return  data;
    }
}
