package com.noc.smsverify.object;

/**
 * Created by dung on 6/12/2017.
 */

public class LogObj {
    String ez;
    String isdn;
    String ngay;
    String loai;
    String status;
    String ghichu;

    public LogObj() {
    }

    public LogObj(String ez, String isdn, String ngay, String loai, String status, String ghichu) {
        this.ez = ez;
        this.isdn = isdn;
        this.ngay = ngay;
        this.loai = loai;
        this.status = status;
        this.ghichu = ghichu;
    }

    public String getEz() {
        return ez;

    }

    public void setEz(String ez) {
        this.ez = ez;
    }

    public String getIsdn() {
        return isdn;
    }

    public void setIsdn(String isdn) {
        this.isdn = isdn;
    }

    public String getNgay() {
        return ngay;
    }

    public void setNgay(String ngay) {
        this.ngay = ngay;
    }

    public String getLoai() {
        return loai;
    }

    public void setLoai(String loai) {
        this.loai = loai;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getGhichu() {
        return ghichu;
    }

    public void setGhichu(String ghichu) {
        this.ghichu = ghichu;
    }
}
