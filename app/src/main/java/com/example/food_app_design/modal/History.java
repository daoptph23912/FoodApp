package com.example.food_app_design.modal;

public class History {
    private String maDonHang;
    private String hoTen;
    private String soDienThoai;
    private String diaChiNhan;
    private int soLuongMua;
    private String thucDon;
    private String ngayDatHang;
    private  int tongTien;
    private  String thanhToan;
    private int userId;

    public History() {
    }

    public History(String maDonHang, String hoTen, String soDienThoai, String diaChiNhan, int soLuongMua, String thucDon, String ngayDatHang, int tongTien, String thanhToan, int userId) {
        this.maDonHang = maDonHang;
        this.hoTen = hoTen;
        this.soDienThoai = soDienThoai;
        this.diaChiNhan = diaChiNhan;
        this.soLuongMua = soLuongMua;
        this.thucDon = thucDon;
        this.ngayDatHang = ngayDatHang;
        this.tongTien = tongTien;
        this.thanhToan = thanhToan;
        this.userId = userId;
    }

    public String getMaDonHang() {
        return maDonHang;
    }

    public void setMaDonHang(String maDonHang) {
        this.maDonHang = maDonHang;
    }

    public String getHoTen() {
        return hoTen;
    }

    public void setHoTen(String hoTen) {
        this.hoTen = hoTen;
    }

    public String getSoDienThoai() {
        return soDienThoai;
    }

    public void setSoDienThoai(String soDienThoai) {
        this.soDienThoai = soDienThoai;
    }

    public String getDiaChiNhan() {
        return diaChiNhan;
    }

    public void setDiaChiNhan(String diaChiNhan) {
        this.diaChiNhan = diaChiNhan;
    }

    public int getSoLuongMua() {
        return soLuongMua;
    }

    public void setSoLuongMua(int soLuongMua) {
        this.soLuongMua = soLuongMua;
    }

    public String getThucDon() {
        return thucDon;
    }

    public void setThucDon(String thucDon) {
        this.thucDon = thucDon;
    }

    public String getNgayDatHang() {
        return ngayDatHang;
    }

    public void setNgayDatHang(String ngayDatHang) {
        this.ngayDatHang = ngayDatHang;
    }

    public int getTongTien() {
        return tongTien;
    }

    public void setTongTien(int tongTien) {
        this.tongTien = tongTien;
    }

    public String getThanhToan() {
        return thanhToan;
    }

    public void setThanhToan(String thanhToan) {
        this.thanhToan = thanhToan;
    }

    public int getUserId() {
        return userId;
    }

    public void setUserId(int userId) {
        this.userId = userId;
    }
}
