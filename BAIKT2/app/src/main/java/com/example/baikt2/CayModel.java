package com.example.baikt2;

public class CayModel {

    String tenKH,tenTG, dacTinh,mauLa,hinhAnh,moTa;
    CayModel(){}

    public CayModel(String tenKH, String tenTG, String dacTinh, String mauLa, String hinhAnh, String moTa) {
        this.tenKH = tenKH;
        this.tenTG = tenTG;
        this.dacTinh = dacTinh;
        this.mauLa = mauLa;
        this.hinhAnh = hinhAnh;
        this.moTa = moTa;
    }

    public String getTenKH() {
        return tenKH;
    }

    public void setTenKH(String tenKH) {
        this.tenKH = tenKH;
    }

    public String getTenTG() {
        return tenTG;
    }

    public void setTenTG(String tenTG) {
        this.tenTG = tenTG;
    }

    public String getDacTinh() {
        return dacTinh;
    }

    public void setDacTinh(String dacTinh) {
        this.dacTinh = dacTinh;
    }

    public String getMauLa() {
        return mauLa;
    }

    public void setMauLa(String mauLa) {
        this.mauLa = mauLa;
    }

    public String getHinhAnh() {
        return hinhAnh;
    }

    public void setHinhAnh(String hinhAnh) {
        this.hinhAnh = hinhAnh;
    }

    public String getMoTa() {
        return moTa;
    }

    public void setMoTa(String moTa) {
        this.moTa = moTa;
    }
}