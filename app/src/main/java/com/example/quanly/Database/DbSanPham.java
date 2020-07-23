package com.example.quanly.Database;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import com.example.quanly.Model.SanPham;

import java.util.ArrayList;

public class DbSanPham {
    DbHelper dbHelper;

    public DbSanPham(Context context) {
        dbHelper = new DbHelper(context);
    }

    public void Them(SanPham sanPham) {
        SQLiteDatabase db = dbHelper.getWritableDatabase();
        ContentValues values = new ContentValues();
        //masanpham text, tensanpham text, dvtinh text, dongia text
        values.put("masanpham", sanPham.getMaSP());
        values.put("tensanpham", sanPham.getTenSP());
        values.put("dvtinh", sanPham.getDvTinh());
        values.put("dongia", sanPham.getDonGia());

        db.insert("SanPham", null, values);
    }

    public void Xoa(SanPham sanPham) {

        SQLiteDatabase db = dbHelper.getWritableDatabase();
        String sql = "Delete from SanPham where masanpham= '" + sanPham.getMaSP() + "'";
        db.execSQL(sql);

    }

    public void Sua(SanPham sanPham) {
        SQLiteDatabase db = dbHelper.getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put("masanpham", sanPham.getMaSP());
        values.put("tensanpham", sanPham.getTenSP());
        values.put("dvtinh", sanPham.getDvTinh());
        values.put("dongia", sanPham.getDonGia());

        db.update("SanPham", values, "masanpham = '" + sanPham.getMaSP() + "'", null);
    }

    public ArrayList<SanPham> LayDL() {
        ArrayList<SanPham> data = new ArrayList<>();
        String sql = "select * from SanPham";
        SQLiteDatabase db = dbHelper.getReadableDatabase();
        Cursor cursor = db.rawQuery(sql, null);

        try {
            cursor.moveToFirst();
            do {
                SanPham sanPham = new SanPham();
                sanPham.setMaSP(cursor.getString(0));
                sanPham.setTenSP(cursor.getString(1));
                sanPham.setDvTinh(cursor.getString(2));
                sanPham.setDonGia(cursor.getString(3));

                data.add(sanPham);
            } while (cursor.moveToNext());
        } catch (Exception ex) {

        }
        return data;
    }

}
