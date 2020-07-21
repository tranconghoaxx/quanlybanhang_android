package com.example.quanly.Database;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import com.example.quanly.Model.HoaDon;


import java.util.ArrayList;

public class DbHoaDon {
    DbHelper dbHelper;

    public DbHoaDon(Context context) {
        dbHelper = new DbHelper(context);
    }

    public void Them(HoaDon hoaDon) {
        SQLiteDatabase db = dbHelper.getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put("mahoadon", hoaDon.getMaHoaDon());
        values.put("ngaylaphoadon", hoaDon.getNgayLapHoaDon());
        values.put("manhanvien", hoaDon.getMaNhanVien());

        db.insert("HoaDon", null, values);
    }

    public void Xoa(HoaDon hoaDon) {

        SQLiteDatabase db = dbHelper.getWritableDatabase();
        String sql = "Delete from HoaDon where mahoadon= '" + hoaDon.getMaHoaDon() + "'";
        db.execSQL(sql);

    }

    public void Sua(HoaDon hoaDon) {
        SQLiteDatabase db = dbHelper.getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put("mahoadon", hoaDon.getMaHoaDon());

        values.put("ngaylaphoadon", hoaDon.getNgayLapHoaDon());
        values.put("manhanvien", hoaDon.getMaNhanVien());

        db.update("HoaDon", values, "mahoadon = '" + hoaDon.getMaHoaDon() + "'", null);
    }

    public ArrayList<HoaDon> LayDL() {
        ArrayList<HoaDon> data = new ArrayList<>();
        String sql = "select * from HoaDon";
        SQLiteDatabase db = dbHelper.getReadableDatabase();
        Cursor cursor = db.rawQuery(sql, null);
        try {
            cursor.moveToFirst();
            do {
                HoaDon hoaDon = new HoaDon();
                hoaDon.setMaHoaDon(cursor.getString(0));
                hoaDon.setNgayLapHoaDon(cursor.getString(1));
                hoaDon.setMaNhanVien(cursor.getString(2));

                data.add(hoaDon);
            } while (cursor.moveToNext());
        } catch (Exception ex) {

        }
        return data;
    }





}
