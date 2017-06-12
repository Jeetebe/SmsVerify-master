package com.noc.smsverify.object;

import java.io.Serializable;

/**
 * Created by dung on 6/9/2017.
 */

public class CollimgObj implements Serializable{
    String imgtruoc;
    String imgsau;
    String imgphieu;
    String imganhnguoi;
    String imgchuky;

    public String getImgtruoc() {
        return imgtruoc;
    }

    public void setImgtruoc(String imgtruoc) {
        this.imgtruoc = imgtruoc;
    }

    public String getImgsau() {
        return imgsau;
    }

    public void setImgsau(String imgsau) {
        this.imgsau = imgsau;
    }

    public String getImgphieu() {
        return imgphieu;
    }

    public void setImgphieu(String imgphieu) {
        this.imgphieu = imgphieu;
    }

    public String getImganhnguoi() {
        return imganhnguoi;
    }

    public void setImganhnguoi(String imganhnguoi) {
        this.imganhnguoi = imganhnguoi;
    }

    public String getImgchuky() {
        return imgchuky;
    }

    public void setImgchuky(String imgchuky) {
        this.imgchuky = imgchuky;
    }

    public CollimgObj() {
        this.imgtruoc="";
        this.imgsau="";
        this.imgphieu="";
        this.imganhnguoi="";
        this.imgchuky="";
    }
}
