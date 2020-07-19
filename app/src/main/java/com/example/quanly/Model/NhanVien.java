package com.example.quanly.Model;

public class NhanVien {
    String hoTen, maNhanVien,dienThoai;
    boolean gioiTinh;

    public String getHoTen() {
        return hoTen;
    }

    public void setHoTen(String hoTen) {
        this.hoTen = hoTen;
    }

    public String getMaNhanVien() {
        return maNhanVien;
    }

    public void setMaNhanVien(String maNhanVien) {
        this.maNhanVien = maNhanVien;
    }

    public String getDienThoai() {
        return dienThoai;
    }

    public void setDienThoai(String dienThoai) {
        this.dienThoai = dienThoai;
    }

    public boolean isGioiTinh() {
        return gioiTinh;
    }

    public void setGioiTinh(boolean gioiTinh) {
        this.gioiTinh = gioiTinh;
    }

    @Override
    public String toString() {
        return "NhanVien{" +
                "hoTen='" + hoTen + '\'' +
                ", maNhanVien='" + maNhanVien + '\'' +
                ", dienThoai='" + dienThoai + '\'' +
                ", gioiTinh=" + gioiTinh +
                '}';
    }
}
