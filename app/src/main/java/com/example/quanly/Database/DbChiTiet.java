package com.example.quanly.Database;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import com.example.quanly.Model.ChiTietHoaDon;


import java.util.ArrayList;

public class DbChiTiet {
    DbHelper dbHelper;

    public DbChiTiet(Context context) {
        dbHelper = new DbHelper(context);
    }

    public void Them(ChiTietHoaDon chiTietHoaDon) {
        SQLiteDatabase db = dbHelper.getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put("mahoadon", chiTietHoaDon.getMaHD());
        values.put("masanpham", chiTietHoaDon.getMaSP());
        values.put("soluong", chiTietHoaDon.getSoluongSP());

        db.insert("ChiTiet", null, values);
    }

    public void Xoa(ChiTietHoaDon chiTietHoaDon) {

        SQLiteDatabase db = dbHelper.getWritableDatabase();
        String sql = "Delete from ChiTiet where mahoadon= '" + chiTietHoaDon.getMaHD() + "'";
        db.execSQL(sql);

    }

    public void Sua(ChiTietHoaDon chiTietHoaDon) {
        SQLiteDatabase db = dbHelper.getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put("mahoadon", chiTietHoaDon.getMaHD());

        values.put("masanpham", chiTietHoaDon.getMaSP());
        values.put("soluong", chiTietHoaDon.getSoluongSP());

        db.update("ChiTiet", values, "mahoadon = '" + chiTietHoaDon.getMaHD() + "'", null);
    }

    public ArrayList<ChiTietHoaDon> LayDL() {
        ArrayList<ChiTietHoaDon> data = new ArrayList<>();
        String sql = "select * from ChiTiet";
        SQLiteDatabase db = dbHelper.getReadableDatabase();
        Cursor cursor = db.rawQuery(sql, null);
        try {
            cursor.moveToFirst();
            do {
                ChiTietHoaDon chiTietHoaDon = new ChiTietHoaDon();
                chiTietHoaDon.setMaHD(cursor.getString(0));
                chiTietHoaDon.setMaSP(cursor.getString(1));
                chiTietHoaDon.setSoluongSP(cursor.getString(2));

                data.add(chiTietHoaDon);
            } while (cursor.moveToNext());
        } catch (Exception ex) {

        }
        return data;
    }

}
